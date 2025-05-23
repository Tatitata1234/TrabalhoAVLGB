package org.example.entity;

import java.time.LocalDate;

public class Pessoa {
    private String cpf;
    private String rg;
    private String nome;
    private LocalDate nascimento;
    private String cidadeNascimento;

    public Pessoa(String cpf, String rg, String nome, LocalDate nascimento, String cidade) {
        this.cpf = cpf;
        this.rg = rg;
        this.nome = nome;
        this.nascimento = nascimento;
        this.cidadeNascimento = cidade;
    }

    @Override
    public String toString() {
        return String.format("Pessoa {cpf='%s', rg='%s', nome='%s', nascimento=%s, cidade='%s'}",
                cpf, rg, nome, nascimento, cidadeNascimento);
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
}
