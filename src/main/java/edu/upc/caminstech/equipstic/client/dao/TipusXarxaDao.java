package edu.upc.caminstech.equipstic.client.dao;

import java.util.List;

import edu.upc.caminstech.equipstic.TipusXarxa;

/**
 * Interfície d'ús intern de la llibreria.
 */
public interface TipusXarxaDao {

    List<TipusXarxa> getTipusXarxa();

    TipusXarxa getTipusXarxaById(long idTipusXarxa);

}
