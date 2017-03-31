package edu.upc.caminstech.equipstic.client.dao;

import java.util.List;

import edu.upc.caminstech.equipstic.Estat;

public interface EstatDao {

    List<Estat> getEstats();

    Estat getEstatByCodi(String codiEstat);

    List<Estat> getEstatsByNom(String nomEstat);

    Estat getEstatById(long idEstat);

}
