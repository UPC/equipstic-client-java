package edu.upc.caminstech.equipstic.client.dao;

import java.util.List;

import edu.upc.caminstech.equipstic.Ambit;

public interface AmbitDao {

    List<Ambit> getAmbits();

    List<Ambit> getAmbitsByNom(String nomAmbit);

    Ambit getAmbitById(long idAmbit);

}
