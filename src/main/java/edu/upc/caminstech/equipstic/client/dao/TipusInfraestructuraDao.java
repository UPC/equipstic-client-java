package edu.upc.caminstech.equipstic.client.dao;

import java.util.List;

import edu.upc.caminstech.equipstic.TipusInfraestructura;

/**
 * Interfície d'ús intern de la llibreria.
 */
public interface TipusInfraestructuraDao {

    List<TipusInfraestructura> getTipusInfraestructura();

    List<TipusInfraestructura> getTipusInfraestructuraByCategoria(long idCategoria);

    TipusInfraestructura getTipusInfraestructuraBycodi(String codi);

    List<TipusInfraestructura> getTipusInfraestructuraByNom(String nom);

    TipusInfraestructura getTipusInfraestructuraById(long idTipus);

}
