package org.example.entity;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Arvore<T extends Comparable> {
    private No<T> raiz = null;

    private StringBuilder builderInOrder = new StringBuilder();

    private StringBuilder builderPostOrder = new StringBuilder();

    private StringBuilder builderPreOrder = new StringBuilder();

    private List<Pessoa> pessoaNomeInOrderList = new ArrayList<>();

    private List<Pessoa> pessoaNascimentoInOrderList = new ArrayList<>();

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

    public void preorder() {
        preorder(raiz);
    }

    private void preorder(No<T> no) {
        if (no != null) {
            builderPreOrder.append(no.getChave()).append(" ");
            preorder(no.getEsquerda());
            preorder(no.getDireita());
        }
    }

    public void inorder() {
        inorder(raiz);
    }

    private void inorder(No<T> no) {

        if (no != null) {
            inorder(no.getEsquerda());
            builderInOrder.append(no.getChave()).append(" ");
            inorder(no.getDireita());
        }
    }

    public void postorder() {
        postorder(raiz);
    }

    private void postorder(No<T> no) {
        if (no != null) {
            postorder(no.getEsquerda());
            postorder(no.getDireita());
            builderPostOrder.append(no.getChave()).append(" ");
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

    public String getBuilderInOrder() {
        return builderInOrder.toString().trim();
    }

    public void cleanBuilderInOrder() {
        this.builderInOrder = new StringBuilder();
    }

    public String getBuilderPostOrder() {
        return builderPostOrder.toString().trim();
    }

    public void cleanBuilderPostOrder() {
        this.builderPostOrder = new StringBuilder();
    }

    public String getBuilderPreOrder() {
        return builderPreOrder.toString().trim();
    }

    public void cleanBuilderPreOrder() {
        this.builderPreOrder = new StringBuilder();
    }

    public No<T> inserirAVL(T el, Pessoa valor) {
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

    public void inserirEBalancearAVL(T el, Pessoa valor) {
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

    public Pessoa procuraPorCpf(T cpf) {
        return procura(cpf).getValor();
    }

    public void procuraNomeInorderList(String letra) {
        procuraNomeInorderList(letra, raiz);
    }

    private void procuraNomeInorderList(String letra, No<T> no) {

        if (no != null) {
            procuraNomeInorderList(letra, no.getEsquerda());
            if (((String) no.getChave()).startsWith(letra)) {
                pessoaNomeInOrderList.add(no.getValor());
            }
            procuraNomeInorderList(letra, no.getDireita());
        }
    }
    public List<Pessoa> procuraPorNome(String letra) {
        cleanPessoaInOrderList();
        procuraNomeInorderList(letra);
        return pessoaNomeInOrderList;
    }

    public void cleanPessoaInOrderList() {
        this.pessoaNomeInOrderList = new ArrayList<>();
    }

    public List<Pessoa> procuraPorDataNascimento(LocalDate inicio, LocalDate fim) {
        cleanPessoaNascimentoInOrderList();
        procuraNascimentoInorderList(inicio, fim);
        return pessoaNascimentoInOrderList;
    }

    public void procuraNascimentoInorderList(LocalDate inicio, LocalDate fim) {
        procuraNascimentoInorderList(inicio, fim, raiz);
    }

    private void procuraNascimentoInorderList(LocalDate inicio, LocalDate fim, No<T> no) {

        if (no != null) {
            procuraNascimentoInorderList(inicio, fim, no.getEsquerda());
            if (((LocalDate) no.getChave()).isAfter(inicio) && ((LocalDate) no.getChave()).isBefore(fim)) {
                pessoaNascimentoInOrderList.add(no.getValor());
            }
            procuraNascimentoInorderList(inicio, fim, no.getDireita());
        }
    }

    public void cleanPessoaNascimentoInOrderList() {
        this.pessoaNascimentoInOrderList = new ArrayList<>();
    }
}
