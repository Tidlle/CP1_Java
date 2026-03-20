package br.com.fiap.entity;

import java.util.Calendar;

public class FuncionarioEstagiario extends Funcionario{

    public FuncionarioEstagiario() {
    }

    public FuncionarioEstagiario(int id, String nome, double horaTrabalhada, double valorHora, Calendar dataCadastro) {
        super(id, nome, horaTrabalhada, valorHora, dataCadastro);
    }

    public double calcularSalarioFinal() {
        return (getValorHora() * getHoraTrabalhada()) - 500;
    }

    @Override
    public double calcularSalario() {
        return (getValorHora() * getHoraTrabalhada()) - 500;
    }

    @Override
    public String imprimirInformacoes() {
        return "Funcionario Estagiario" +
                "\nID: " + getId() +
                "\nNome: " + getNome() +
                "\nHoras Trabalhadas: " + getHoraTrabalhada() +
                "\nValor da Hora Trabalhada: " + getValorHora() +
                "\nDesconto: R$ 500.0" +
                "\nSalário Final: R$ " + calcularSalario();
    }
}
