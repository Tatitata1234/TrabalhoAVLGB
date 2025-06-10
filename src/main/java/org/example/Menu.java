package org.example;

import org.example.entity.Arvore;
import org.example.entity.No;
import org.example.entity.Pessoa;
import org.example.util.OrdenacaoUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

   private Menu() {}

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
    
    public static void imprimeArvore(No knot, StringBuilder prefixo) {
        prefixo.repeat(' ',knot.getAltura()*2);
        System.out.println(prefixo.toString() );
    }

    public static void main(String[] args) {
        
      List<Pessoa> pessoas = new ArrayList();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      pessoas.add(new Pessoa("16373783788", "6759433242", "Rodrigo Correia", LocalDate.parse("30/11/2001", formatter), "Arroio do Meio"));
      pessoas.add(new Pessoa("38470245012", "7916164950", "Marcelo Dias", LocalDate.parse("29/05/1988", formatter), "Santa Vitória do Palmar"));
      pessoas.add(new Pessoa("71182356812", "6492475931", "Renata Tavares", LocalDate.parse("18/08/2005", formatter), "Veranópolis"));
      pessoas.add(new Pessoa("80835498952", "8758363131", "Noemi Medeiros", LocalDate.parse("15/08/1984", formatter), "Gramado"));
      pessoas.add(new Pessoa("87538081728", "5201822785", "Caio Soares", LocalDate.parse("02/02/1979", formatter), "Erechim"));
      pessoas.add(new Pessoa("38837702785", "4628384608", "Ana Aguiar", LocalDate.parse("14/04/1984", formatter), "Eldorado do Sul"));
      pessoas.add(new Pessoa("65521843342", "5012880168", "Roberto Braga", LocalDate.parse("07/01/1967", formatter), "Palmeira das Missões"));
      Arvore<LocalDate> arvoreNome = new Arvore();

      for(int i = 0; i < pessoas.size(); ++i) {
         arvoreNome.inserirEBalancearAVL(((Pessoa)pessoas.get(i)).getNascimento(), i);
      }

      LocalDate inicio = LocalDate.parse("07/01/1966", formatter);
      LocalDate fim = LocalDate.parse("02/02/1980", formatter);
      List<Integer> pessoasIdList = arvoreNome.procuraPorDataNascimento(inicio, fim);
      List<Pessoa> pessoasList = new ArrayList();
      Iterator var9 = pessoasIdList.iterator();

      while(var9.hasNext()) {
         Integer p = (Integer)var9.next();
         pessoasList.add((Pessoa)pessoas.get(p));
      }

      OrdenacaoUtil.quickSortPorNascimento(pessoasList, 0, pessoasList.size() - 1);

      Pessoa var11;
      for(var9 = pessoasList.iterator(); var9.hasNext(); var11 = (Pessoa)var9.next()) {
      }

      StringBuilder s = new StringBuilder();
      Menu.imprimeArvore(arvoreNome.getRaiz(), s);
      System.out.println("para");
    }

        
}