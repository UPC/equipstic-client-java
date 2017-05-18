package edu.upc.caminstech.equipstic.client.dao;

import java.util.List;
import java.util.Optional;

import edu.upc.caminstech.equipstic.Infraestructura;

/**
 * Interfície d'ús intern de la llibreria.
 */
public interface InfraestructuraDao {

    Optional<Infraestructura> getInfraestructuraByMarcaAndNumeroDeSerie(long idMarca, String sn, boolean ambDetalls);

    Optional<Infraestructura> getInfraestructuraById(long id, boolean ambDetalls);

    List<Infraestructura> getInfraestructuresByUnitat(long idUnitat);

    Infraestructura altaInfraestructura(Infraestructura infraestructura);

    void baixaInfraestructura(long id);

    Infraestructura modificaInfraestructura(Infraestructura infraestructura);

}
