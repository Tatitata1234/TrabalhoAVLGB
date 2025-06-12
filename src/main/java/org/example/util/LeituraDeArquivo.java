package org.example.util;

import org.example.entity.Pessoa;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LeituraDeArquivo {

    private static final String DELIMITER = ";";

    private LeituraDeArquivo() {}

    public static List<String[]> leDados(String nomeArquivo) {
        String workingDirectory = System.getProperty("user.dir");
        List<String[]> linhas = new ArrayList<>();
        String fullPath = workingDirectory + File.separator + nomeArquivo;
        File arquivo = new File(fullPath);
        try (Scanner leitor = new Scanner(arquivo)) {
            String linha = leitor.nextLine(); // precisa pular o "cabeçalho"
            while (leitor.hasNextLine()) {
                linha = leitor.nextLine();
                String[] dados = linha.split(DELIMITER);
                linhas.add(dados);
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace(); // não vai dar erro - la garantia soy yo
        }

        return linhas;
    }

    public static List<Pessoa> criaPessoas(List<String[]> linhas) {
        List<Pessoa> pessoas = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (String[] linha : linhas) {
            pessoas.add(new Pessoa(linha[0], linha[1], linha[2], LocalDate.parse(linha[3], formatter), linha[4]));
        }
        return pessoas;
    }

    public static List<Pessoa> leDadosECriaPessoas(String nomeArquivo) {

        List<String[]> linhas = leDados(nomeArquivo);

        return criaPessoas(linhas);
    }
}
