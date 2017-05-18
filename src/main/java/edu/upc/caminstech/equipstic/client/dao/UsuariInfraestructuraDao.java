package edu.upc.caminstech.equipstic.client.dao;

import java.util.List;
import java.util.Optional;

import edu.upc.caminstech.equipstic.UsuariInfraestructura;

/**
 * Interfície d'ús intern de la llibreria.
 */
public interface UsuariInfraestructuraDao {

    Optional<UsuariInfraestructura> getUsuariInfraestructura(long idUsuariInfraestructura);

    List<UsuariInfraestructura> getUsuarisInfraestructura();

    List<UsuariInfraestructura> getUsuarisInfraestructuraByNom(String nom);

}
