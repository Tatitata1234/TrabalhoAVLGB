package org.example.entity.dto;

public class PessoaDTO {
    private int id;

    private int iteracoes;

    public PessoaDTO(int id, int iteracoes) {
        this.id = id;
        this.iteracoes = iteracoes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIteracoes() {
        return iteracoes;
    }

    public void setIteracoes(int iteracoes) {
        this.iteracoes = iteracoes;
    }
}
