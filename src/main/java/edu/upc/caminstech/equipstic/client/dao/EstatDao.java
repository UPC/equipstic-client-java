package edu.upc.caminstech.equipstic.client.dao;

import java.util.List;
import java.util.Optional;

import edu.upc.caminstech.equipstic.Estat;

/**
 * Interfície d'ús intern de la llibreria.
 */
public interface EstatDao {

    List<Estat> getEstats();

    Optional<Estat> getEstatByCodi(String codiEstat);

    List<Estat> getEstatsByNom(String nomEstat);

    Optional<Estat> getEstatById(long idEstat);

}
