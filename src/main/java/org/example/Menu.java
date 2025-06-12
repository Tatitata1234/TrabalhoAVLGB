package org.example;

import org.example.entity.Arvore;
import org.example.entity.No;
import org.example.entity.Pessoa;
import org.example.exception.DataInicioDepoisDataFimException;
import org.example.util.LeituraDeArquivo;
import org.example.util.OrdenacaoUtil;
import org.example.util.Teclado;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.example.util.MenuUtils.*;

public class Menu {

    private Menu() {
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Pessoa> pessoas = LeituraDeArquivo.leDadosECriaPessoas("data/nomes.csv");

        Arvore<LocalDate> arvoreNascimento = new Arvore<>();
        Arvore<String> arvoreNome = new Arvore<>();
        Arvore<String> arvoreCpf = new Arvore<>();

        for (int i = 0; i < pessoas.size(); i++) {
            arvoreNascimento.inserirEBalancearAVL(pessoas.get(i).getNascimento(), i);
            arvoreNome.inserirEBalancearAVL(pessoas.get(i).getNome(), i);
            arvoreCpf.inserirEBalancearAVL(pessoas.get(i).getCpf(), i);
        }


        System.out.println(MENU_TITULO);
        int op = -1;
        do {
            try {
                System.out.println(MENU_OPCOES);
                op = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Não foi digitado um número");
                sc.nextLine();
            }

            executaOP(op, pessoas, arvoreNome, arvoreCpf, arvoreNascimento);

        } while (op != 0);
    }

    private static void executaOP(
            int op,
            List<Pessoa> pessoas,
            Arvore<String> arvoreNome,
            Arvore<String> arvoreCpf,
            Arvore<LocalDate> arvoreNascimento
    ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        switch (op) {
            case 1 -> {
                try {
                    String input = Teclado.leString("Digite o cpf de quem deseja buscar:\n");
                    procuraEImprimePessoaPorCPF(pessoas, arvoreCpf, input);
                } catch (Exception e) {
                    System.err.println("CPF inválido: " + e.getMessage());
                }
            }
            case 2 -> {
                try {
                    String input = Teclado.leString("Digite o Nome de quem deseja buscar:\n");
                    procuraEImprimePessoasPorNome(pessoas, arvoreNome, input);
                } catch (Exception e) {
                    System.err.println("Nome inválido: " + e.getMessage());
                }
            }
            case 3 -> {
                try {
                    String stringInicio = Teclado.leString(
                            "Digite a data inicial que deseja procurar:\nex.: dd/MM/yyyy\n"
                    );
                    String stringFinal = Teclado.leString(
                            "Digite a data final que deseja procurar:\nex.: dd/MM/yyyy\n"
                    );
                    LocalDate inicio = LocalDate.parse(stringInicio, formatter);
                    LocalDate fim = LocalDate.parse(stringFinal, formatter);
                    if (fim.isBefore(inicio)) {
                        throw new DataInicioDepoisDataFimException("A data de fim precisa ser depois da data de início");
                    }
                    procuraEImprimePessoasPorData(pessoas, arvoreNascimento, inicio, fim);
                } catch (Exception e) {
                    System.err.println("Data inválida: " + e.getMessage());
                }
            }
            default -> {
                System.err.println("Opção inválida");
            }
        }
    }






}