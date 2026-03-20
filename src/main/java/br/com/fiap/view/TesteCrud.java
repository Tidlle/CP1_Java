package br.com.fiap.view;

import br.com.fiap.dao.FuncionarioDao;
import br.com.fiap.dao.FuncionarioDaoImpl;
import br.com.fiap.entity.Funcionario;
import br.com.fiap.entity.FuncionarioSenior;
import br.com.fiap.exception.CommitException;
import br.com.fiap.exception.IdNaoEncontradoException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Calendar;

public class TesteCrud {

    public static void main(String[] args) {

        EntityManagerFactory fabrica = null;
        EntityManager em = null;

        try {
            fabrica = Persistence.createEntityManagerFactory("oracle");
            em = fabrica.createEntityManager();

            FuncionarioDao dao = new FuncionarioDaoImpl(em);

            Calendar dataCadastro = Calendar.getInstance();

            Funcionario funcionario = new Funcionario(
                    0,
                    "Carlos Silva",
                    47,
                    100,
                    dataCadastro
            );

            System.out.println("========================================");
            System.out.println("TESTE CREATE");
            System.out.println("========================================");
            dao.cadastrar(funcionario);
            dao.commit();

            System.out.println("Funcionário cadastrado com sucesso.");
            System.out.println(funcionario.imprimirInformacoes());

            int idGerado = funcionario.getId();

            System.out.println("\n========================================");
            System.out.println("TESTE READ");
            System.out.println("========================================");
            Funcionario funcionarioBuscado = dao.buscarPorId(idGerado);
            System.out.println("Funcionário encontrado:");
            System.out.println(funcionarioBuscado.imprimirInformacoes());

            System.out.println("\n========================================");
            System.out.println("TESTE UPDATE");
            System.out.println("========================================");
            funcionarioBuscado.setNome("Carlos Silva Atualizado");
            funcionarioBuscado.setHoraTrabalhada(60);
            funcionarioBuscado.setValorHora(120);

            dao.atualizar(funcionarioBuscado);
            dao.commit();

            Funcionario funcionarioAtualizado = dao.buscarPorId(idGerado);
            System.out.println("Funcionário atualizado com sucesso:");
            System.out.println(funcionarioAtualizado.imprimirInformacoes());

            System.out.println("\n========================================");
            System.out.println("TESTE DELETE");
            System.out.println("========================================");
            dao.remover(idGerado);
            dao.commit();

            System.out.println("Funcionário removido com sucesso.");

            System.out.println("\n========================================");
            System.out.println("CONFIRMAÇÃO DO DELETE");
            System.out.println("========================================");

            try {
                dao.buscarPorId(idGerado);
            } catch (IdNaoEncontradoException e) {
                System.out.println("Confirmação: funcionário não encontrado após delete.");
            }

        } catch (CommitException e) {
            System.out.println("Erro ao realizar commit.");
            e.printStackTrace();
        } catch (IdNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro geral no sistema:");
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
            if (fabrica != null) {
                fabrica.close();
            }
        }
    }
}
