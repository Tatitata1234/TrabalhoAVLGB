package org.example.util;

import org.example.entity.Pessoa;

import java.util.Collections;
import java.util.List;

public class OrdenacaoUtil {

    private OrdenacaoUtil() {}

    public static void quickSortPorNome(List<Pessoa> lista, int lo, int hi) {
        int i = lo;
        int j = hi;
        Pessoa pivot = lista.get((lo + hi) / 2);

        while (i <= j) {
            while (lista.get(i).getNome().compareTo(pivot.getNome()) < 0) i++;

            while (lista.get(j).getNome().compareTo(pivot.getNome()) > 0) j--;

            if (i <= j) {
                Collections.swap(lista, i, j);
                i++;
                j--;
            }
        }

        if (lo < j) quickSortPorNome(lista, lo, j);
        if (i < hi) quickSortPorNome(lista, i, hi);
    }

    public static void quickSortPorNascimento(List<Pessoa> lista, int lo, int hi) {
        int i = lo;
        int j = hi;
        Pessoa pivot = lista.get((lo + hi) / 2);

        while (i <= j) {

            while (lista.get(i).getNascimento().isBefore(pivot.getNascimento())) i++;

            while (lista.get(j).getNascimento().isAfter(pivot.getNascimento())) j--;

            if (i <= j) {
                Collections.swap(lista, i, j);
                i++;
                j--;
            }
        }

        if (lo < j) quickSortPorNascimento(lista, lo, j);
        if (i < hi) quickSortPorNascimento(lista, i, hi);
    }
}
