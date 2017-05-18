package edu.upc.caminstech.equipstic.client.dao;

import java.util.List;
import java.util.Optional;

import edu.upc.caminstech.equipstic.TipusXarxa;

/**
 * Interfície d'ús intern de la llibreria.
 */
public interface TipusXarxaDao {

    List<TipusXarxa> getTipusXarxa();

    Optional<TipusXarxa> getTipusXarxaById(long idTipusXarxa);

}
