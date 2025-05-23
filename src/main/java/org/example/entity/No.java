package org.example.entity;

public class No {

    private Integer chave;

    private No esquerda;

    private No direita;

    private int altura;

    private int ponto;

    private No pai;

    public No() {
        this.esquerda = null;
        this.direita = null;
    }

    public No(Integer chave) {
        this(chave, null, null);
    }

    public No(Integer chave, No esquerda, No direita) {
        this.chave = chave;
        this.esquerda = esquerda;
        this.direita = direita;
        this.altura = 0;
        this.ponto = 0;
    }

    public Integer getChave() {
        return chave;
    }

    public void setChave(Integer chave) {
        this.chave = chave;
    }

    public No getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(No esquerda) {
        this.esquerda = esquerda;
    }

    public No getDireita() {
        return direita;
    }

    public void setDireita(No direita) {
        this.direita = direita;
    }

    public int getAltura() {
        if (this.direita == null && this.esquerda == null) {
            return 1;
        }
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getPonto() {
        return ponto;
    }

    public void setPonto(int ponto) {
        this.ponto = ponto;
    }

    public No getPai() {
        return pai;
    }

    public void setPai(No pai) {
        this.pai = pai;
    }

    @Override
    public String toString() {
        int p;
        int esq;
        int dir;
        if (this.esquerda == null) {
            esq = 0;
        } else
            esq = this.esquerda.getAltura();
        if (this.direita == null) {
            dir = 0;
        } else
            dir = this.direita.getAltura();
        p = esq - dir;
        return "\nNó: " + this.chave + "\nAltura: " + this.altura + "\nPontos: " + p + "\n";
    }
}
