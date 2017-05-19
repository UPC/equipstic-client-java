package edu.upc.caminstech.equipstic.util;

import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Operacions null-safe amb col·leccions.
 */
public class NullSafe {

    private NullSafe() {
        throw new UnsupportedOperationException("Aquesta classe no és instanciable.");
    }

    /**
     * Retorna una llista amb els elements d'una col·lecció ordenats.
     * 
     * @param c
     *            la col·lecció que conté els elements a ordenar (pot ser
     *            {@code null}).
     * @return una llista amb els elements de {@code c} ordenats segons
     *         {@code T.compareTo()}. Si {@code c} és <code>null</code>, retorna
     *         una llista buida.
     */
    public static <T extends Comparable<T>> List<T> sorted(Collection<T> c) {
        if (c == null) {
            return Collections.emptyList();
        }
        return c.stream().sorted().collect(toList());
    }

}
