package edu.upc.caminstech.equipstic.client.dao;

/**
 * Classe d'ús intern de la llibreria.
 */
public abstract class CacheUtils {

    /**
     * Un prefix comú per als noms de les cachés d'un client amb caché.
     */
    public static final String PREFIX = "equipstic-client-";

    private CacheUtils() {
        // constructor privat; classe no instanciable
    }
}
