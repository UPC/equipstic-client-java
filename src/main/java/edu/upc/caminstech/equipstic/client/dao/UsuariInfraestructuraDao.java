package edu.upc.caminstech.equipstic.client.dao;

import java.util.List;

import edu.upc.caminstech.equipstic.UsuariInfraestructura;

public interface UsuariInfraestructuraDao {

    UsuariInfraestructura getUsuariInfraestructura(long idUsuariInfraestructura);

    List<UsuariInfraestructura> getUsuarisInfraestructura();

    List<UsuariInfraestructura> getUsuarisInfraestructuraByNom(String nom);

}
