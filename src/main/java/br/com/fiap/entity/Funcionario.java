package br.com.fiap.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.fiap.annotation.Descricao;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="TDS_TB_FUNCIONARIO")
@Descricao(descricao = "TDS_TB_FUNCIONARIO")
@SequenceGenerator(name="funcionario", sequenceName = "SQ_TDS_TB_FUNCIONARIO", allocationSize = 1)
public class Funcionario {

    @Id
    @Column(name="id_funcionario")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "funcionario")
    private int id;

    @Column(name="nm_funcionario", nullable = false, length = 100)
    private String nome;

    @Column(name="hr_trabalhada")
    private double horaTrabalhada;

    @Column(name="vl_hr_trabalhada", precision = 10, scale = 3)
    private double valorHora;

    @CreationTimestamp //Criar a data atual automaticamente no cadastro
    @Temporal(TemporalType.TIMESTAMP) //Gravar a data e hora no banco
    @Column(name="dt_cadastro", updatable = false)
    private Calendar dataCadastro;


    public Funcionario() {}

    public Funcionario(int id, String nome, double horaTrabalhada, double valorHora, Calendar dataCadastro) {
        super();
        this.id = id;
        this.nome = nome;
        this.horaTrabalhada = horaTrabalhada;
        this.valorHora = valorHora;
        this.dataCadastro = dataCadastro;
    }

    @PostPersist //Executa o metodo apos o persist
    private void executar() {
        System.out.println("Executando o método..");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getHoraTrabalhada() { return horaTrabalhada; }

    public void setHoraTrabalhada(double horaTrabalhada) { this.horaTrabalhada = horaTrabalhada; }

    public double getValorHora() { return valorHora; }

    public void setValorHora(double valorHora) { this.valorHora = valorHora; }

    public Calendar getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Calendar dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public double calcularSalario(){
        return valorHora * horaTrabalhada;
    }

    public String imprimirInformacoes() {
        return "Funcionario" +
                "\nID: " + id +
                "\nNome: " + nome +
                "\nHoras Trabalhadas: " + horaTrabalhada +
                "\nValor da Hora Trabalhada: " + valorHora;
    }
}