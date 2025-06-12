package org.example.util;

import org.example.entity.Arvore;
import org.example.entity.Pessoa;
import org.example.entity.dto.PessoaDTO;
import org.example.exception.NomeNaoEncontradoException;
import org.example.exception.PessoaNaoEncontradaException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MenuUtils {

    private MenuUtils(){}

    public static void procuraEImprimePessoasPorData(List<Pessoa> pessoas, Arvore<LocalDate> arvore, LocalDate inicio, LocalDate fim) {
        Map<Integer, Integer> pessoasIdList = arvore.procuraPorDataNascimento(inicio, fim, 1);
        List<Pessoa> pessoasList = new ArrayList<>();
        for (int i = 0; i < pessoasIdList.size(); i++) {
            Pessoa p = pessoas.get(pessoasIdList.keySet().stream().toList().get(i));
            p.setIteracoes(pessoasIdList.values().stream().toList().get(i));
            pessoasList.add(p);
        }
        if (pessoasList.isEmpty()) {
            throw new PessoaNaoEncontradaException("Nenhuma pessoa encontrada com nascimento entre essas datas");
        }

        OrdenacaoUtil.quickSortPorNascimento(pessoasList, 0, pessoasList.size() - 1);

        imprimeLista(pessoasList);
    }

    public static void procuraEImprimePessoaPorCPF(List<Pessoa> pessoas, Arvore<String> arvore, String cpf) {

        PessoaDTO pessoa = arvore.procuraPorCpf(cpf, 1, true);
        Pessoa p = pessoas.get(pessoa.getId());
        p.setIteracoes(pessoa.getIteracoes());
        System.out.println(p);
    }

    public static void procuraEImprimePessoasPorNome(List<Pessoa> pessoas, Arvore<String> arvore, String nome) {
        Map<Integer, Integer> pessoasIdList = arvore.procuraPorNome(nome, 1);

        List<Pessoa> pessoasList = new ArrayList<>();
        for (int i = 0; i < pessoasIdList.size(); i++) {
            Pessoa p = pessoas.get(pessoasIdList.keySet().stream().toList().get(i));
            p.setIteracoes(pessoasIdList.values().stream().toList().get(i));
            pessoasList.add(p);
        }

        if (pessoasIdList.isEmpty()) {
            throw new NomeNaoEncontradoException("Nome não encontrado");
        }

        OrdenacaoUtil.quickSortPorNome(pessoasList, 0, pessoasList.size() - 1);

        imprimeLista(pessoasList);
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

    private static void imprimeLista(List<Pessoa> pessoasList) {
        System.out.println();
        for (Pessoa p : pessoasList) {
            System.out.print(p.toStringLista());
        }
    }
}
