package org.example;

import org.example.entity.Pessoa;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ArvorePessoaTest {
    @Test
    void devefazerTudo(){
        List<Pessoa> pessoas = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        pessoas.add(new Pessoa("16373783788", "6759433242", "Rodrigo Correia", LocalDate.parse("30/11/2001", formatter), "Arroio do Meio"));
        pessoas.add(new Pessoa("38470245012", "7916164950", "Marcelo Dias", LocalDate.parse("29/05/1988", formatter), "Santa Vitória do Palmar"));
        pessoas.add(new Pessoa("71182356812", "6492475931", "Renata Tavares", LocalDate.parse("18/08/2005", formatter), "Veranópolis"));
        pessoas.add(new Pessoa("80835498952", "8758363131", "Noemi Medeiros", LocalDate.parse("15/08/1984", formatter), "Gramado"));
        pessoas.add(new Pessoa("87538081728", "5201822785", "Caio Soares", LocalDate.parse("02/02/1979", formatter), "Erechim"));
        pessoas.add(new Pessoa("38837702785", "4628384608", "Ana Aguiar", LocalDate.parse("14/04/1984", formatter), "Eldorado do Sul"));
        pessoas.add(new Pessoa("65521843342", "5012880168", "Roberto Braga", LocalDate.parse("07/01/1967", formatter), "Palmeira das Missões"));

        for (Pessoa p : pessoas) {
            System.out.println(p);
        }
    }

    @Test
    void debugerroaula(){

    }
}
