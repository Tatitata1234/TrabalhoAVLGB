package org.example.entity;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Arvore<T extends Comparable> {
    private No<T> raiz = null;

    private List<Integer> pessoaNomeInOrderList = new ArrayList<>();

    private List<Integer> pessoaNascimentoInOrderList = new ArrayList<>();

    public No<T> procura(T el) {
        return procura(this.raiz, el);
    }

    private No<T> procura(No<T> no, T el) {
        if (no == null) {
            return null;
        }

        if (Objects.equals(el, no.getChave())) {
            return no;
        } else if (el.compareTo(no.getChave()) < 0) {
            return procura(no.getEsquerda(), el);
        } else {
            return procura(no.getDireita(), el);
        }
    }

    public void clear() {
        this.raiz = null;
    }

    public boolean isNotEmpty() {
        return this.raiz != null;
    }

    public No<T> getRaiz() {
        return this.raiz;
    }

    public No<T> inserirAVL(T el, int valor) {
        No<T> no = raiz;
        No<T> anterior = null;

        if (procura(el) != null)
            return null;

        No<T> novoNo = new No<>(el, valor);
        if (raiz == null) {
            raiz = novoNo;
            raiz.setAltura(1);
            return novoNo;
        }

        while (no != null) {
            anterior = no;

            if (el.compareTo(no.getChave()) < 0) {
                no = no.getEsquerda();
            } else {
                no = no.getDireita();
            }
        }

        assert anterior != null;

        novoNo.setPai(anterior);
        if (anterior.getChave().compareTo(el) < 0) {
            anterior.setDireita(novoNo);
        } else {
            anterior.setEsquerda(novoNo);
        }

        corrigeAltura(novoNo);
        return novoNo;
    }

    public void inserirEBalancearAVL(T el, int valor) {
        No<T> noInserido = inserirAVL(el, valor);
        if (noInserido != null) {
            balancearAvl(noInserido);
        }
        corrigeAltura(noInserido);
    }

    private void balancearAvl(No<T> folha) {
        if (folha == raiz) {
            return;
        }
        No<T> pai = folha.getPai();
        while (pai != null) {
            int filhoEsqAltura = pai.getEsquerda() != null ? pai.getEsquerda().getAltura() : 0;
            int filhoDirAltura = pai.getDireita() != null ? pai.getDireita().getAltura() : 0;
            int diferenca = filhoEsqAltura - filhoDirAltura;
            pai.setPonto(diferenca);
            if (Math.abs(diferenca) >= 2) {
                doBalanceamento(pai, diferenca);
            }

            pai = pai.getPai();
        }
    }

    private void rotaSimplesEsquerda(No<T> pai) {
        No<T> filhoDir = pai.getDireita();
        No<T> filhoEsqFilhoDir = filhoDir.getEsquerda();
        No<T> vo = pai.getPai();

        filhoDir.setPai(vo);
        filhoDir.setEsquerda(pai);
        pai.setPai(filhoDir);
        pai.setDireita(filhoEsqFilhoDir);

        if (filhoEsqFilhoDir != null) {
            filhoEsqFilhoDir.setPai(pai);
        }


        if (pai == raiz) {
            raiz = filhoDir;
        } else {
            if (vo.getDireita() == pai) {
                vo.setDireita(filhoDir);
            } else {
                vo.setEsquerda(filhoDir);
            }
        }

        corrigeAltura(pai);
        corrigeAltura(filhoDir);
    }

    private void rotaSimplesDireita(No<T> pai) {
        No<T> filhoEsq = pai.getEsquerda();
        No<T> filhoDirFilhoEsq = filhoEsq.getDireita();
        No<T> vo = pai.getPai();

        filhoEsq.setPai(vo);
        filhoEsq.setDireita(pai);
        pai.setPai(filhoEsq);
        pai.setEsquerda(filhoDirFilhoEsq);

        if (filhoDirFilhoEsq != null) {
            filhoDirFilhoEsq.setPai(pai);
        }

        if (pai == raiz) {
            raiz = filhoEsq;
        } else {
            if (vo.getDireita() == pai) {
                vo.setDireita(filhoEsq);
            } else {
                vo.setEsquerda(filhoEsq);
            }
        }

        corrigeAltura(pai);
        corrigeAltura(filhoEsq);
    }

    private void corrigeAltura(No<T> no) {
        while (no != null) {
            int alturaEsq = (no.getEsquerda() != null) ? no.getEsquerda().getAltura() : 0;
            int alturaDir = (no.getDireita() != null) ? no.getDireita().getAltura() : 0;
            no.setAltura(1 + Math.max(alturaEsq, alturaDir));
            no = no.getPai();
        }
    }

    private void doBalanceamento(No<T> pai, int diferenca) {
        if (diferenca > 0) {
            if (pai.getEsquerda().getPonto() >= 0) {
                rotaSimplesDireita(pai);
            } else {
                rotaSimplesEsquerda(pai.getEsquerda());
                rotaSimplesDireita(pai);
            }
        } else {
            if (pai.getDireita().getPonto() <= 0) {
                rotaSimplesEsquerda(pai);

            } else {
                rotaSimplesDireita(pai.getDireita());
                rotaSimplesEsquerda(pai);
            }
        }
    }

    public int procuraPorCpf(T cpf) {
        return procura(cpf).getValor();
    }

    public List<Integer> procuraPorNome(String letra) {
        cleanPessoaInOrderList();
        procuraPorNome((No<String>) raiz, letra);
        return pessoaNomeInOrderList;
    }

    public void cleanPessoaInOrderList() {
        this.pessoaNomeInOrderList = new ArrayList<>();
    }

    public List<Integer> procuraPorDataNascimento(LocalDate inicio, LocalDate fim) {
        cleanPessoaNascimentoInOrderList();
        procuraPorNascimento((No<LocalDate>) raiz, inicio, fim);
        return pessoaNascimentoInOrderList;
    }

    public void cleanPessoaNascimentoInOrderList() {
        this.pessoaNascimentoInOrderList = new ArrayList<>();
    }

    private void procuraPorNome(No<String> no, String letra) {
        if (no == null) {
            return ;
        }

        if (no.getChave().startsWith(letra)) {
            pessoaNomeInOrderList.add(no.getValor());
        }
        procuraPorNome(no.getEsquerda(), letra);
        procuraPorNome(no.getDireita(), letra);
    }

    private void procuraPorNascimento(No<LocalDate> no, LocalDate inicio, LocalDate fim) {
        if (no == null) {
            return ;
        }

        if (no.getChave().isAfter(inicio) && no.getChave().isBefore(fim)) {
            pessoaNascimentoInOrderList.add(no.getValor());
        }
        procuraPorNascimento(no.getEsquerda(), inicio, fim);
        procuraPorNascimento(no.getDireita(), inicio, fim);
    }

}
