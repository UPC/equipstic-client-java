package edu.upc.caminstech.equipstic.client.dao;

import java.util.List;

import edu.upc.caminstech.equipstic.Campus;

public interface CampusDao {

    List<Campus> getCampus();

    Campus getCampusByCodi(String codiCampus);

    Campus getCampusById(long idCampus);

}
