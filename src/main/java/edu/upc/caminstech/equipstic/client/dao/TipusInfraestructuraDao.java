package edu.upc.caminstech.equipstic.client.dao;

import java.util.List;
import java.util.Optional;

import edu.upc.caminstech.equipstic.TipusInfraestructura;

/**
 * Interfície d'ús intern de la llibreria.
 */
public interface TipusInfraestructuraDao {

    List<TipusInfraestructura> getTipusInfraestructura();

    List<TipusInfraestructura> getTipusInfraestructuraByCategoria(long idCategoria);

    Optional<TipusInfraestructura> getTipusInfraestructuraBycodi(String codi);

    List<TipusInfraestructura> getTipusInfraestructuraByNom(String nom);

    Optional<TipusInfraestructura> getTipusInfraestructuraById(long idTipus);

}
