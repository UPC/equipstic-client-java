package edu.upc.caminstech.equipstic.client.dao;

import java.util.List;
import java.util.Optional;

import edu.upc.caminstech.equipstic.Campus;

/**
 * Interfície d'ús intern de la llibreria.
 */
public interface CampusDao {

    List<Campus> getCampus();

    Optional<Campus> getCampusByCodi(String codiCampus);

    Optional<Campus> getCampusById(long idCampus);

}
