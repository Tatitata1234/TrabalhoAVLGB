package org.example.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pessoa {
    private String cpf;
    private String rg;
    private String nome;
    private LocalDate nascimento;
    private String cidadeNascimento;

    private int iteracoes;

    public Pessoa(String cpf, String rg, String nome, LocalDate nascimento, String cidade) {
        this.cpf = cpf;
        this.rg = rg;
        this.nome = nome;
        this.nascimento = nascimento;
        this.cidadeNascimento = cidade;
    }

    @Override
    public String toString() {
        return String.format(
                "\nNome =\t\t\t%s,\n" +
                        "Iterações =\t\t%s,\n" +
                        "CPF =\t\t\t%s,\n" +
                        "RG =\t\t\t%s,\n" +
                        "Nascimento =\t%s,\n" +
                        "Cidade =\t\t%s\n\n",
                nome != null ? nome : "null",
                iteracoes,
                cpf != null ? cpf : "null",
                rg != null ? rg : "null",
                nascimento != null ? nascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "null",
                cidadeNascimento != null ? cidadeNascimento : "null"
        );
    }

    public String toStringLista() {
        if (nome != null && nome.length() <= 12){
            nome += "\t";
        }
        if (nome != null && nome.length() <= 15){
            nome += "\t";
        }
        return String.format(
                "Nome = %s,\t" +
                        "Iterações = %s,\t" +
                        "CPF = %s,\t" +
                        "RG = %s,\t" +
                        "Nascimento = %s,\t" +
                        "Cidade = %s\n",
                nome != null ? nome : "null",
                iteracoes,
                cpf != null ? cpf : "null",
                rg != null ? rg : "null",
                nascimento != null ? nascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "null",
                cidadeNascimento != null ? cidadeNascimento : "null"
        );
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public String getCidadeNascimento() {
        return cidadeNascimento;
    }

    public void setCidadeNascimento(String cidadeNascimento) {
        this.cidadeNascimento = cidadeNascimento;
    }

    public int getIteracoes() {
        return iteracoes;
    }

    public void setIteracoes(int iteracoes) {
        this.iteracoes = iteracoes;
    }
}
