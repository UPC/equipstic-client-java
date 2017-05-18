package edu.upc.caminstech.equipstic.client.dao;

import java.util.List;
import java.util.Optional;

import edu.upc.caminstech.equipstic.Ambit;

/**
 * Interfície d'ús intern de la llibreria.
 */
public interface AmbitDao {

    List<Ambit> getAmbits();

    List<Ambit> getAmbitsByNom(String nomAmbit);

    Optional<Ambit> getAmbitById(long idAmbit);

    List<Ambit> getAmbitsByCodi(String codiAmbit);

    List<Ambit> getAmbitsByCategoria(long idCategoria);

}
