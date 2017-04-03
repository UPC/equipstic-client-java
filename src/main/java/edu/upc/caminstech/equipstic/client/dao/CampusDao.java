package edu.upc.caminstech.equipstic.client.dao;

import java.util.List;

import edu.upc.caminstech.equipstic.Campus;

/**
 * Interfície d'ús intern de la llibreria.
 */
public interface CampusDao {

    List<Campus> getCampus();

    Campus getCampusByCodi(String codiCampus);

    Campus getCampusById(long idCampus);

}
