package org.example.entity;


import java.util.Objects;

public class Arvore {
    private No raiz = null;

    private StringBuilder builderInOrder = new StringBuilder();

    private StringBuilder builderPostOrder = new StringBuilder();

    private StringBuilder builderPreOrder = new StringBuilder();

    public No procura(Integer el) {
        return procura(this.raiz, el);
    }

    private No procura(No no, Integer el) {
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

    private void preorder(No no) {
        if (no != null) {
            builderPreOrder.append(no.getChave()).append(" ");
            preorder(no.getEsquerda());
            preorder(no.getDireita());
        }
    }

    public void inorder() {
        inorder(raiz);
    }

    private void inorder(No no) {

        if (no != null) {
            inorder(no.getEsquerda());
            builderInOrder.append(no.getChave()).append(" ");
            inorder(no.getDireita());
        }
    }

    public void postorder() {
        postorder(raiz);
    }

    private void postorder(No no) {
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

    public No getRaiz() {
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

    public No inserirAVL(Integer el) {
        No no = raiz;
        No anterior = null;

        if (procura(el) != null)
            return null;

        No novoNo = new No(el);
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

    public void inserirEBalancearAVL(Integer el) {
        No noInserido = inserirAVL(el);
        if (noInserido != null) {
            balancearAvl(noInserido);
        }
        corrigeAltura(noInserido);
    }

    private void balancearAvl(No folha) {
        if (folha == raiz) {
            return;
        }
        No pai = folha.getPai();
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

    private No maiorNoSubArvore(No raiz) {
        if (raiz.getDireita() == null)
            return raiz;

        // busca o elemento mais à diretia de uma subárvore
        No noBusca = raiz.getDireita();
        while (noBusca.getDireita() != null) {
            noBusca = noBusca.getDireita();
        }
        return noBusca;
    }

    private void excluirPorCopia(No no) {
        No raizSubEsquerda = no.getEsquerda();
        No noMaiorEsquerda = maiorNoSubArvore(raizSubEsquerda);
        int chaveMaior = noMaiorEsquerda.getChave();

        no.setChave(chaveMaior);

        excluirQuandoUmFilhoOuMenos(noMaiorEsquerda);

        balancearAvl(noMaiorEsquerda);
    }

    private void excluirQuandoUmFilhoOuMenos(No no) {
        No pai = no.getPai();
        No filho = no.getDireita() != null ? no.getDireita() : no.getEsquerda();

        if (pai == null) {
            this.raiz = filho;
        } else {
            if (no.getChave() > pai.getChave()) {
                pai.setDireita(filho);
            } else {
                pai.setEsquerda(filho);
            }
        }

        balancearAvl(Objects.requireNonNullElse(filho, no));
    }

    public void excluir(Integer el) {
        No desejado = procura(el);
        if (desejado == null)
            return;
        if (desejado.getDireita() != null && desejado.getEsquerda() != null) {
            excluirPorCopia(desejado);
        } else {
            excluirQuandoUmFilhoOuMenos(desejado);
        }

    }

    private void rotaSimplesEsquerda(No pai) {
        No filhoDir = pai.getDireita();
        No filhoEsqFilhoDir = filhoDir.getEsquerda();
        No vo = pai.getPai();

        filhoDir.setPai(pai.getPai());
        filhoDir.setEsquerda(pai);
        pai.setPai(filhoDir);
        pai.setDireita(filhoEsqFilhoDir);

        if (pai == raiz) {
            raiz = filhoDir;
        } else {
            if (vo.getDireita() == pai) {
                vo.setDireita(filhoDir);
            } else {
                vo.setEsquerda(filhoDir);
            }
        }
    }

    private void rotaSimplesDireita(No pai) {
        No filhoEsq = pai.getEsquerda();
        No filhoDirFilhoEsq = filhoEsq.getDireita();
        No vo = pai.getPai();

        filhoEsq.setPai(pai.getPai());
        filhoEsq.setDireita(pai);
        pai.setPai(filhoEsq);
        pai.setEsquerda(filhoDirFilhoEsq);

        if (pai == raiz) {
            raiz = filhoEsq;
        } else {
            if (vo.getDireita() == pai) {
                vo.setDireita(filhoEsq);
            } else {
                vo.setEsquerda(filhoEsq);
            }
        }
    }

    public int calculaAltura(No raizAtual) {
        if (raizAtual == null) {
            return -1;
        } else {
            int esq = calculaAltura(raizAtual.getEsquerda());
            int dir = calculaAltura(raizAtual.getDireita());
            if (esq > dir)
                return esq + 1;
            else
                return dir + 1;
        }
    }

    private void corrigeAltura(No novoNo) {
        No proximo = novoNo;
        while (!Objects.equals(proximo, null)) {
            int altura = calculaAltura(proximo);
            altura++;
            proximo.setAltura(altura);
            proximo.setPonto(0);
            proximo = proximo.getPai();
        }
    }

    private void doBalanceamento(No pai, int diferenca) {
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
        corrigeAltura(pai);
    }
}
