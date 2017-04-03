package edu.upc.caminstech.equipstic.client.dao;

import java.util.List;

import edu.upc.caminstech.equipstic.Estat;

/**
 * Interfície d'ús intern de la llibreria.
 */
public interface EstatDao {

    List<Estat> getEstats();

    Estat getEstatByCodi(String codiEstat);

    List<Estat> getEstatsByNom(String nomEstat);

    Estat getEstatById(long idEstat);

}
