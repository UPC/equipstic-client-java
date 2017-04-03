package edu.upc.caminstech.equipstic.client.dao;

import java.util.List;

import edu.upc.caminstech.equipstic.Edifici;

/**
 * Interfície d'ús intern de la llibreria.
 */
public interface EdificiDao {

    List<Edifici> getEdificis();

    Edifici getEdificiById(long idEdifici);

    Edifici getEdificiByCodiAndCodiCampus(String codiEdifici, String codiCampus);

}
