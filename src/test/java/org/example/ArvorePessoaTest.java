package org.example;

import org.example.entity.Arvore;
import org.example.entity.Pessoa;
import org.example.util.LeituraDeArquivo;
import org.example.util.OrdenacaoUtil;
import org.example.entity.dto.PessoaDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.example.util.MenuUtils.*;

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
        for (int i = 0; i < pessoas.size(); i++) {
            arvoreCpf.inserirEBalancearAVL(pessoas.get(i).getCpf(), i);
        }
        PessoaDTO pessoaId = arvoreCpf.procuraPorCpf("65521843342", 1);
        System.out.println(pessoas.get(pessoaId.getId()));
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
        for (int i = 0; i < pessoas.size(); i++) {
            arvoreNome.inserirEBalancearAVL(pessoas.get(i).getNome(), i);
        }
        Map<Integer, Integer> pessoasIdList = arvoreNome.procuraPorNome("R",1);

        List<Pessoa> pessoasList = new ArrayList<>();
        for (Integer p : pessoasIdList.keySet()) {
            pessoasList.add(pessoas.get(p));
        }
        OrdenacaoUtil.quickSortPorNome(pessoasList, 0, pessoasList.size() - 1);
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
        for (int i = 0; i < pessoas.size(); i++) {
            arvoreNome.inserirEBalancearAVL(pessoas.get(i).getNascimento(), i);
        }
        LocalDate inicio = LocalDate.parse("07/01/1966", formatter);
        LocalDate fim = LocalDate.parse("02/02/1980", formatter);
        Map<Integer, Integer> pessoasIdList = arvoreNome.procuraPorDataNascimento(inicio, fim,0);


        List<Pessoa> pessoasList = new ArrayList<>();
        for (Integer p : pessoasIdList.keySet()) {
            pessoasList.add(pessoas.get(p));
        }
        OrdenacaoUtil.quickSortPorNascimento(pessoasList, 0, pessoasList.size() - 1);
        for (Pessoa p : pessoasList) {
            System.out.println(p);
        }
        System.out.println("para");
    }

    @Test
    void deveLerArquivo() {
        List<Pessoa> pessoas = new ArrayList<>();

        pessoas = LeituraDeArquivo.leDadosECriaPessoas("data/nomes.csv");

        System.out.println("para");
    }

    @Test
    void deveImprimir() {

        List<Pessoa> pessoas = new ArrayList<>();

        pessoas = LeituraDeArquivo.leDadosECriaPessoas("data/nomes.csv");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");



        LocalDate inicio = LocalDate.parse("01/01/1966", formatter);
        LocalDate fim = LocalDate.parse("31/12/1980", formatter);

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

        System.out.println("\nPessoas Por data de nascimento:\n");
        procuraEImprimePessoasPorData(pessoas, arvoreNascimento,inicio, fim );
        System.out.println("\nAqui acabou a lista por data\n");

        System.out.println("\nPessoas Por nome:\n");
        procuraEImprimePessoasPorNome(pessoas, arvoreNome,"João");
        System.out.println("\nAqui acabou a lista de nome\n");

        System.out.println("\nPessoa com tal CPF:\n");
        procuraEImprimePessoaPorCPF(pessoas, arvoreCpf, "65521843342");
        System.out.println("\nAqui achou a tal pessoa\n");

    }
}
