package org.example.entity;

public class No<T extends Comparable> {

    private T chave;

    private int valor;

    private No<T> esquerda;

    private No<T> direita;

    private int altura;

    private int ponto;

    private No<T> pai;

    public No() {
        this.esquerda = null;
        this.direita = null;
    }

    public No(T chave, int valor) {
        this(chave, null, null, valor);
    }

    public No(T chave, No<T> esquerda, No<T> direita, int valor) {
        this.chave = chave;
        this.esquerda = esquerda;
        this.direita = direita;
        this.valor = valor;
        this.altura = 0;
        this.ponto = 0;
    }

    public T getChave() {
        return chave;
    }

    public void setChave(T chave) {
        this.chave = chave;
    }

    public No<T> getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(No<T> esquerda) {
        this.esquerda = esquerda;
    }

    public No<T> getDireita() {
        return direita;
    }

    public void setDireita(No<T> direita) {
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

    public No<T> getPai() {
        return pai;
    }

    public void setPai(No<T> pai) {
        this.pai = pai;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
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
