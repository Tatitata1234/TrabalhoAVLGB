package org.example.util;

import org.example.entity.Pessoa;

import java.util.Collections;
import java.util.List;

public class OrdenacaoUtil {
    public static void quickSortPorNome(List<Pessoa> lista, int lo, int hi) {
        int i = lo;
        int j = hi;
        Pessoa pivot = lista.get((lo + hi) / 2); // pivô é uma Pessoa

        while (i <= j) {
            // avança até encontrar um nome >= ao pivô
            while (lista.get(i).getNome().compareTo(pivot.getNome()) < 0) i++;

            // recua até encontrar um nome <= ao pivô
            while (lista.get(j).getNome().compareTo(pivot.getNome()) > 0) j--;

            if (i <= j) {
                // troca os elementos
                Collections.swap(lista, i, j);
                i++;
                j--;
            }
        }

        if (lo < j) quickSortPorNome(lista, lo, j); // ordena parte inferior
        if (i < hi) quickSortPorNome(lista, i, hi); // ordena parte superior
    }

    public static void quickSortPorNascimento(List<Pessoa> lista, int lo, int hi) {
        int i = lo, j = hi;
        Pessoa pivot = lista.get((lo + hi) / 2); // pivô baseado no nascimento

        while (i <= j) {
            // Avança enquanto a data de nascimento for menor que a do pivô
            while (lista.get(i).getNascimento().isBefore(pivot.getNascimento())) i++;

            // Retrocede enquanto a data de nascimento for maior que a do pivô
            while (lista.get(j).getNascimento().isAfter(pivot.getNascimento())) j--;

            if (i <= j) {
                Collections.swap(lista, i, j); // troca as posições
                i++;
                j--;
            }
        }

        if (lo < j) quickSortPorNascimento(lista, lo, j);
        if (i < hi) quickSortPorNascimento(lista, i, hi);
    }
}
