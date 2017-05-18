package edu.upc.caminstech.equipstic.client.dao;

import java.util.List;
import java.util.Optional;

import edu.upc.caminstech.equipstic.TipusUs;

/**
 * Interfície d'ús intern de la llibreria.
 */
public interface TipusUsDao {

    List<TipusUs> getTipusUs();

    List<TipusUs> getTipusUsByUnitat(long idUnitat);

    Optional<TipusUs> getTipusUsById(long idTipusUs);

}
