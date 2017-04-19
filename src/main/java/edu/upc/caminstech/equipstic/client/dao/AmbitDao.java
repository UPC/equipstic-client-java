package edu.upc.caminstech.equipstic.client.dao;

import java.util.List;

import edu.upc.caminstech.equipstic.Ambit;

/**
 * Interfície d'ús intern de la llibreria.
 */
public interface AmbitDao {

    List<Ambit> getAmbits();

    List<Ambit> getAmbitsByNom(String nomAmbit);

    Ambit getAmbitById(long idAmbit);

    List<Ambit> getAmbitsByCodi(String codiAmbit);

    List<Ambit> getAmbitsByCategoria(long idCategoria);

}
