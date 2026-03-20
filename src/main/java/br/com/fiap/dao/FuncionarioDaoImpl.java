package br.com.fiap.dao;

import javax.persistence.EntityManager;

import br.com.fiap.entity.Funcionario;
import br.com.fiap.exception.CommitException;
import br.com.fiap.exception.IdNaoEncontradoException;
import br.com.fiap.reflection.SqlGenerator;

public class FuncionarioDaoImpl implements FuncionarioDao {

    private EntityManager em;

    public FuncionarioDaoImpl(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Funcionario funcionario) {
        System.out.println("SQL CREATE:");
        System.out.println(SqlGenerator.gerarInsert(funcionario));

        em.getTransaction().begin();
        em.persist(funcionario);
    }

    public void atualizar(Funcionario funcionario) throws IdNaoEncontradoException {
        buscarPorId(funcionario.getId());

        System.out.println("SQL UPDATE:");
        System.out.println(SqlGenerator.gerarUpdate(funcionario));

        em.getTransaction().begin();
        em.merge(funcionario);
    }

    public void remover(int id) throws IdNaoEncontradoException {
        Funcionario funcionario = buscarPorId(id);

        System.out.println("SQL DELETE:");
        System.out.println(SqlGenerator.gerarDelete(Funcionario.class, id));

        em.getTransaction().begin();
        em.remove(funcionario);
    }

    public Funcionario buscarPorId(int id) throws IdNaoEncontradoException {
        System.out.println("SQL READ:");
        System.out.println(SqlGenerator.gerarSelectPorId(Funcionario.class, id));

        Funcionario funcionario = em.find(Funcionario.class, id);

        if (funcionario == null) {
            throw new IdNaoEncontradoException("Funcionário não encontrado");
        }

        return funcionario;
    }

    public void commit() throws CommitException {
        try {
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new CommitException();
        }
    }
}