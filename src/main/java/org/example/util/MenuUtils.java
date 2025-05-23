package org.example.util;

import org.example.entity.Arvore;

import java.util.Scanner;

public class MenuUtils {

    private MenuUtils(){}

    public static Arvore criaArvore(Arvore arvore) {
        Scanner scan = new Scanner(System.in);
        int escolha;

        if (arvore.isNotEmpty()) {
            System.out.println("Arvore ja criada...");
            System.out.println("Deseja Criar outra árvore ?\n1 - Sim\nQualquer outro número - Não");
            escolha = scan.nextInt();
            if (escolha == 1) {
                arvore.clear();
                System.out.println("Criando árvore, digite o valor a ser inserido:\n");
                arvore.inserirEBalancearAVL(scan.nextInt());
            }
        } else {
            System.out.println("Criando árvore...");
            System.out.println("Digite o número que deseja inserir");
            arvore.inserirEBalancearAVL(scan.nextInt());
        }
        return arvore;
    }

    public static Arvore insere(Arvore arvore) {
        Scanner scan = new Scanner(System.in);
        int escolha;
        if (arvore.isNotEmpty()) {
            System.out.println("Digite o número que deseja inserir");
            escolha = scan.nextInt();
            while (arvore.procura(escolha) != null) {
                System.out.println("Esse nó ja está na árvore, digite outro número:\n");
                escolha = scan.nextInt();
            }

            arvore.inserirEBalancearAVL(escolha);
        } else {
            System.out.println("Árvore ainda não criada...");
        }
        return arvore;
    }

    public static void procura(Arvore arvore) {

        Scanner scan = new Scanner(System.in);
        int escolha;

        if (arvore.isNotEmpty()) {
            System.out.println("Digite o número que deseja procurar");
            escolha = scan.nextInt();

            while (arvore.procura(escolha) == null) {

                System.out.println("Esse valor não existe na árvore, digite outro número:\n");
                escolha = scan.nextInt();

            }
            System.out.println(arvore.procura(escolha).toString());

        } else {

            System.out.println("Árvore ainda não criada");

        }
    }

    public static Arvore exclui(Arvore arvore) {
        int escolha;
        Scanner scan = new Scanner(System.in);
        if (arvore.isNotEmpty()) {
            System.out.println("Digite o número que deseja excluir");
            escolha = scan.nextInt();
            while (arvore.procura(escolha) == null) {
                System.out.println("Esse nó não existe na árvore, digite outro número:\n");
                escolha = scan.nextInt();
            }
            arvore.excluir(escolha);
        } else {
            System.out.println("Árvore ainda não criada");
        }
        return arvore;
    }

    public static void caminha(Arvore arvore) {
        Scanner scan = new Scanner(System.in);
        int escolha;
        if (arvore.isNotEmpty()) {
            System.out.println("""
                    Como deseja visualizar o caminho da arvore?
                    1 - Pré-Ordem
                    2 - Em  Ordem
                    3 - Pós-Ordem
                    Digite qual sua escolha:
                    """);
            escolha = scan.nextInt();
            while (escolha < 1 || escolha > 3) {
                System.out.println("Digite um valor válido:");
                escolha = scan.nextInt();
            }
            switch (escolha) {
                case 1 -> {
                    arvore.preorder();
                    System.out.println(arvore.getBuilderPreOrder());

                    arvore.cleanBuilderPreOrder();
                }
                case 2 -> {
                    arvore.inorder();
                    System.out.println(arvore.getBuilderInOrder());

                    arvore.cleanBuilderInOrder();
                }
                case 3 -> {
                    arvore.postorder();
                    System.out.println(arvore.getBuilderPostOrder());

                    arvore.cleanBuilderPostOrder();
                }
                default -> System.out.println("erro ao caminhar");
            }
        } else
            System.out.println("A árvore está vazia...");
    }

}
