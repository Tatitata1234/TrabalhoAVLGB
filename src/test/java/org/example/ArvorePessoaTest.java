package org.example;

import org.example.entity.Arvore;
import org.example.entity.Pessoa;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

class ArvorePessoaTest {
    @Test
    void deveMontarArvorePorCPFEBuscar(){
        List<Pessoa> pessoas = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        pessoas.add(new Pessoa("16373783788", "6759433242", "Rodrigo Correia", LocalDate.parse("30/11/2001", formatter), "Arroio do Meio"));
        pessoas.add(new Pessoa("38470245012", "7916164950", "Marcelo Dias", LocalDate.parse("29/05/1988", formatter), "Santa Vitória do Palmar"));
        pessoas.add(new Pessoa("71182356812", "6492475931", "Renata Tavares", LocalDate.parse("18/08/2005", formatter), "Veranópolis"));
        pessoas.add(new Pessoa("80835498952", "8758363131", "Noemi Medeiros", LocalDate.parse("15/08/1984", formatter), "Gramado"));
        pessoas.add(new Pessoa("87538081728", "5201822785", "Caio Soares", LocalDate.parse("02/02/1979", formatter), "Erechim"));
        pessoas.add(new Pessoa("38837702785", "4628384608", "Ana Aguiar", LocalDate.parse("14/04/1984", formatter), "Eldorado do Sul"));
        pessoas.add(new Pessoa("65521843342", "5012880168", "Roberto Braga", LocalDate.parse("07/01/1967", formatter), "Palmeira das Missões"));

        Arvore<String> arvoreCpf = new Arvore<>();
        for (Pessoa p : pessoas) {
            arvoreCpf.inserirEBalancearAVL(p.getCpf(), p);
        }
        Pessoa pessoa = arvoreCpf.procuraPorCpf("65521843342");
        System.out.println(pessoa);
        System.out.println("para");
    }

    @Test
    void deveMontarArvorePorNomeEBuscar(){
        List<Pessoa> pessoas = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        pessoas.add(new Pessoa("16373783788", "6759433242", "Rodrigo Correia", LocalDate.parse("30/11/2001", formatter), "Arroio do Meio"));
        pessoas.add(new Pessoa("38470245012", "7916164950", "Marcelo Dias", LocalDate.parse("29/05/1988", formatter), "Santa Vitória do Palmar"));
        pessoas.add(new Pessoa("71182356812", "6492475931", "Renata Tavares", LocalDate.parse("18/08/2005", formatter), "Veranópolis"));
        pessoas.add(new Pessoa("80835498952", "8758363131", "Noemi Medeiros", LocalDate.parse("15/08/1984", formatter), "Gramado"));
        pessoas.add(new Pessoa("87538081728", "5201822785", "Caio Soares", LocalDate.parse("02/02/1979", formatter), "Erechim"));
        pessoas.add(new Pessoa("38837702785", "4628384608", "Ana Aguiar", LocalDate.parse("14/04/1984", formatter), "Eldorado do Sul"));
        pessoas.add(new Pessoa("65521843342", "5012880168", "Roberto Braga", LocalDate.parse("07/01/1967", formatter), "Palmeira das Missões"));

        Arvore<String> arvoreNome = new Arvore<>();
        for (Pessoa p : pessoas) {
            arvoreNome.inserirEBalancearAVL(p.getNome(), p);
        }
        List<Pessoa> pessoasList = arvoreNome.procuraPorNome("R");
        for (Pessoa p : pessoasList) {
            System.out.println(p);
        }
        System.out.println("para");
    }

    @Test
    void deveMontarArvorePorNascimentoEBuscar(){
        List<Pessoa> pessoas = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        pessoas.add(new Pessoa("16373783788", "6759433242", "Rodrigo Correia", LocalDate.parse("30/11/2001", formatter), "Arroio do Meio"));
        pessoas.add(new Pessoa("38470245012", "7916164950", "Marcelo Dias", LocalDate.parse("29/05/1988", formatter), "Santa Vitória do Palmar"));
        pessoas.add(new Pessoa("71182356812", "6492475931", "Renata Tavares", LocalDate.parse("18/08/2005", formatter), "Veranópolis"));
        pessoas.add(new Pessoa("80835498952", "8758363131", "Noemi Medeiros", LocalDate.parse("15/08/1984", formatter), "Gramado"));
        pessoas.add(new Pessoa("87538081728", "5201822785", "Caio Soares", LocalDate.parse("02/02/1979", formatter), "Erechim"));
        pessoas.add(new Pessoa("38837702785", "4628384608", "Ana Aguiar", LocalDate.parse("14/04/1984", formatter), "Eldorado do Sul"));
        pessoas.add(new Pessoa("65521843342", "5012880168", "Roberto Braga", LocalDate.parse("07/01/1967", formatter), "Palmeira das Missões"));

        Arvore<LocalDate> arvoreNome = new Arvore<>();
        for (Pessoa p : pessoas) {
            arvoreNome.inserirEBalancearAVL(p.getNascimento(), p);
        }
        LocalDate inicio = LocalDate.parse("07/01/1966", formatter);
        LocalDate fim = LocalDate.parse("02/02/1980", formatter);
        List<Pessoa> pessoasList = arvoreNome.procuraPorDataNascimento(inicio, fim);
        for (Pessoa p : pessoasList) {
            System.out.println(p);
        }
        System.out.println("para");
    }
}
