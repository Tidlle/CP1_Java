package br.com.fiap.entity;

import java.util.Calendar;

public class FuncionarioSenior extends Funcionario {

    public FuncionarioSenior() {
    }

    public FuncionarioSenior(int id, String nome, double horaTrabalhada, double valorHora, Calendar dataCadastro) {
        super(id, nome, horaTrabalhada, valorHora, dataCadastro);
    }

    public double calcularBonus() {
        return getHoraTrabalhada() / 15;
    }

    public double calcularValorBonus() {
        return calcularBonus() * 1000.0;
    }

    public double calcularSalarioFinal() {
        return (getValorHora() * getHoraTrabalhada()) + calcularValorBonus();
    }

    @Override
    public double calcularSalario() {
        return (getValorHora() * getHoraTrabalhada()) + calcularValorBonus();
    }

    @Override
    public String imprimirInformacoes() {
        return "Funcionario Senior" +
                "\nID: " + getId() +
                "\nNome: " + getNome() +
                "\nHoras Trabalhadas: " + getHoraTrabalhada() +
                "\nValor da Hora Trabalhada: " + getValorHora() +
                "\nBônus: R$ " + calcularValorBonus() +
                "\nSalário Final: R$ " + calcularSalario();
    }
}
