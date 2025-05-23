package org.example;

import org.example.entity.Arvore;
import org.example.entity.No;
import org.example.util.MenuUtils;

import java.util.Scanner;

public class Menu {

    public static final String MENU_OPCOES = """
                    
            1 - Criar uma Árvore
            2 - Inserir um valor
            3 - Excluir um valor
            4 - Buscar um valor
            5 - Caminhar pela árvore
            6 - Limpar a árvore
            7 - Sair
            Digite a Opção:
                    
            """;
    public static final String TITULO = ("""
              
            --r-----------------------------------------------------------------------------------------------------
              
              /$$$$$$                                                           /$$$$$$  /$$    /$$ /$$     \s
             /$$__  $$                                                         /$$__  $$| $$   | $$| $$     \s
            | $$  \\ $$  /$$$$$$  /$$    /$$ /$$$$$$   /$$$$$$   /$$$$$$       | $$  \\ $$| $$   | $$| $$     \s
            | $$$$$$$$ /$$__  $$|  $$  /$$//$$__  $$ /$$__  $$ /$$__  $$      | $$$$$$$$|  $$ / $$/| $$     \s
            | $$__  $$| $$  \\__/ \\  $$/$$/| $$  \\ $$| $$  \\__/| $$$$$$$$      | $$__  $$ \\  $$ $$/ | $$     \s
            | $$  | $$| $$        \\  $$$/ | $$  | $$| $$      | $$_____/      | $$  | $$  \\  $$$/  | $$     \s
            | $$  | $$| $$         \\  $/  |  $$$$$$/| $$      |  $$$$$$$      | $$  | $$   \\  $/   | $$$$$$$$
            |__/  |__/|__/          \\_/    \\______/ |__/       \\_______/      |__/  |__/    \\_/    |________/
                                                                                                            \s
                                                                                                            \s
                                                                                                            \s
                            
            Integrantes: Thais Landfeldt, Victório Faraco, João Trajano
            -------------------------------------------------------------------------------------------------------
            """);

    private Menu() {}

    public static void main(String[] args) {
        Menu.run();
    }

    public static void arvoreString(Arvore a) {
        StringBuilder arvere = new StringBuilder();
        No no = a.getRaiz();
        int h = 0;
        arvere.append("   ");
        arvere.append(no.getChave()).append("\n");

        arvoreString(no.getEsquerda(), h + 1, arvere);
        arvoreString(no.getDireita(), h + 1, arvere);

        System.out.println(arvere);
    }

    public static StringBuilder arvoreString(No no, int h, StringBuilder arvere) {
        if (no == null) {
            return arvere;
        }
        for (int i = 0; i < h; i++) {
            arvere.append("   ");
        }

        arvere.append("|->").append(no.getChave()).append("\n");

        arvoreString(no.getEsquerda(), h + 1, arvere);
        arvoreString(no.getDireita(), h + 1, arvere);

        return arvere;
    }

    public static void run() {
        Scanner scan = new Scanner(System.in);
        int option;
        Arvore arvore = new Arvore();
        System.out.println(TITULO);

        do {

            if (arvore.isNotEmpty()) {
                arvoreString(arvore);
            }

            System.out.println(MENU_OPCOES);
            option = scan.nextInt();

            switch (option) {
                case 1 -> arvore = MenuUtils.criaArvore(arvore);
                case 2 -> arvore = MenuUtils.insere(arvore);
                case 3 -> arvore = MenuUtils.exclui(arvore);
                case 4 -> MenuUtils.procura(arvore);
                case 5 -> MenuUtils.caminha(arvore);
                case 6 -> {
                    arvore.clear();
                    System.out.println("Árvore Limpa...");
                }
                case 7 -> System.out.println("Saindo...\n\n\n");
                default -> System.out.println("Opção inválida...");
            }

        } while (option != 7);

        scan.close();
    }

}