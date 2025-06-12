package org.example;

import org.example.entity.Arvore;
import org.example.entity.No;
import org.example.entity.Pessoa;
import org.example.util.LeituraDeArquivo;
import org.example.util.OrdenacaoUtil;
import org.example.util.Teclado;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu <T extends Comparable>{

   private Menu() {}

   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);

      List<Pessoa> pessoas = new ArrayList<>();
      pessoas = LeituraDeArquivo.leDadosECriaPessoas("data/nomes.csv");

      Arvore<LocalDate> arvoreNascimento = new Arvore<>();
      for (int i = 0; i < pessoas.size(); i++) {
         arvoreNascimento.inserirEBalancearAVL(pessoas.get(i).getNascimento(), i);
      }

      Arvore<String> arvoreNome = new Arvore<>();
      for (int i = 0; i < pessoas.size(); i++) {
         arvoreNome.inserirEBalancearAVL(pessoas.get(i).getNome(), i);
      }

      Arvore<String> arvoreCpf = new Arvore<>();
      for (int i = 0; i < pessoas.size(); i++) {
         arvoreCpf.inserirEBalancearAVL(pessoas.get(i).getCpf(), i);
      }


      System.out.println(Menu.MENU_TITULO);
      int op = -1;
      do {
         try{
            System.out.println(Menu.MENU_OPCOES);
            op = sc.nextInt();
         }catch (Exception e){
            System.out.println("Não foi digitado um número");
            sc.nextLine();
         }

         executaOP(op, pessoas, arvoreNome, arvoreCpf, arvoreNascimento);

      }while (op!=0);
   }

   private static void executaOP(
           int op,
           List<Pessoa> pessoas,
           Arvore<String> arvoreNome,
           Arvore<String> arvoreCpf,
           Arvore<LocalDate> arvoreNascimento
   ) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      switch(op){
         case 1:
            try {
               String input = Teclado.leString("Digite o cpf de quem deseja buscar:\n");
               procuraEImprimePessoaPorCPF(pessoas, arvoreCpf, input);
            }catch (Exception e){
               System.out.println("CPF inválido: " + e.getMessage());
            }
            break;
         case 2:
            try {
               String input = Teclado.leString("Digite o Nome de quem deseja buscar:\n");
               procuraEImprimePessoasPorNome(pessoas, arvoreNome, input);
            }catch (Exception e){
               System.out.println("Nome inválido: " + e.getMessage());
            }
            break;
         case 3:
            try {
               String stringInicio = Teclado.leString(
                       "Digite a data inicial que deseja procurar:\nex.: dd/MM/yyyy\n"
               );
               String stringFinal = Teclado.leString(
                       "Digite a data final que deseja procurar:\nex.: dd/MM/yyyy\n"
               );
               LocalDate inicio = LocalDate.parse(stringInicio, formatter);
               LocalDate fim = LocalDate.parse(stringFinal, formatter);
               procuraEImprimePessoasPorData(pessoas, arvoreNascimento, inicio, fim);
            }catch (Exception e){
               System.out.println("Data inválida: " + e.getMessage());
            }
            break;
      }
   }

   public static final String MENU_OPCOES = """

        1 - Procura por CPF
        2 - Procura por Nome
        3 - Procura por Data
        0 - Sair
        Digite a Opção:

        """;


   public static final String MENU_TITULO = """

        -------------------------------------------------------------------------------------------------------
        ▄▄▄▄▄▄▄ ▄▄▄▄▄▄   ▄▄   ▄▄ ▄▄▄▄▄▄▄ ▄▄▄▄▄▄   ▄▄▄▄▄▄▄    ▄▄▄▄▄▄▄ ▄▄▄▄▄▄▄ ▄▄▄▄▄▄▄ ▄▄▄▄▄▄▄ ▄▄▄▄▄▄▄ ▄▄▄▄▄▄ 
        █       █   ▄  █ █  █ █  █       █   ▄  █ █       █  █       █       █       █       █       █      █
        █   ▄   █  █ █ █ █  █▄█  █   ▄   █  █ █ █ █    ▄▄▄█  █    ▄  █    ▄▄▄█  ▄▄▄▄▄█  ▄▄▄▄▄█   ▄   █  ▄   █
        █  █▄█  █   █▄▄█▄█       █  █ █  █   █▄▄█▄█   █▄▄▄   █   █▄█ █   █▄▄▄█ █▄▄▄▄▄█ █▄▄▄▄▄█  █ █  █ █▄█  █
        █       █    ▄▄  █       █  █▄█  █    ▄▄  █    ▄▄▄█  █    ▄▄▄█    ▄▄▄█▄▄▄▄▄  █▄▄▄▄▄  █  █▄█  █      █
        █   ▄   █   █  █ ██     ██       █   █  █ █   █▄▄▄   █   █   █   █▄▄▄ ▄▄▄▄▄█ █▄▄▄▄▄█ █       █  ▄   █
        █▄▄█ █▄▄█▄▄▄█  █▄█ █▄▄▄█ █▄▄▄▄▄▄▄█▄▄▄█  █▄█▄▄▄▄▄▄▄█  █▄▄▄█   █▄▄▄▄▄▄▄█▄▄▄▄▄▄▄█▄▄▄▄▄▄▄█▄▄▄▄▄▄▄█▄█ █▄▄█


        Integrantes: Thais Landfeldt, Victório Faraco, João Trajano
        -------------------------------------------------------------------------------------------------------
           """;
   public static void procuraEImprimePessoasPorData(List<Pessoa> pessoas,Arvore<LocalDate> arvore, LocalDate inicio, LocalDate fim) {
      List<Integer> pessoasIdList = arvore.procuraPorDataNascimento(inicio, fim, 1, true);
      List<Pessoa> pessoasList = new ArrayList<>();
      for (Integer p : pessoasIdList) {
         pessoasList.add(pessoas.get(p));
      }
      OrdenacaoUtil.quickSortPorNascimento(pessoasList, 0, pessoasList.size() - 1);
      for (Pessoa p : pessoasList) {
         System.out.println(p);
      }
   }
   public static void procuraEImprimePessoaPorCPF(List<Pessoa> pessoas, Arvore<String> arvore, String cpf){

      int pessoaId = arvore.procuraPorCpf(cpf,1,true);
      System.out.println(pessoas.get(pessoaId));
   }
   public static void procuraEImprimePessoasPorNome(List<Pessoa> pessoas, Arvore<String> arvore, String nome){
      List<Integer> pessoasIdList = arvore.procuraPorNome(nome, 1, true);


      List<Pessoa> pessoasList = new ArrayList<>();
      for (Integer p : pessoasIdList) {
         pessoasList.add(pessoas.get(p));
      }
      OrdenacaoUtil.quickSortPorNome(pessoasList, 0, pessoasList.size() - 1);
      for (Pessoa p : pessoasList) {
         System.out.println(p);
      }
   }


        
}