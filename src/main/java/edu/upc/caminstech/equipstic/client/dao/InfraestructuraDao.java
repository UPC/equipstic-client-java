package edu.upc.caminstech.equipstic.client.dao;

import java.util.List;

import edu.upc.caminstech.equipstic.Infraestructura;

public interface InfraestructuraDao {

    Infraestructura getInfraestructuraByMarcaAndNumeroDeSerie(long idMarca, String sn);

    Infraestructura getInfraestructuraById(long id);

    List<Infraestructura> getInfraestructuresByUnitat(long idUnitat);

    Infraestructura altaInfraestructura(Infraestructura infraestructura);

    void baixaInfraestructura(long id);

    Infraestructura modificaInfraestructura(Infraestructura infraestructura);

}
