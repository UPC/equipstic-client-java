package edu.upc.caminstech.equipstic.client.dao;

import java.util.List;
import java.util.Optional;

import edu.upc.caminstech.equipstic.Edifici;

/**
 * Interfície d'ús intern de la llibreria.
 */
public interface EdificiDao {

    List<Edifici> getEdificis();

    Optional<Edifici> getEdificiById(long idEdifici);

    Optional<Edifici> getEdificiByCodiAndCodiCampus(String codiEdifici, String codiCampus);

}
