package edu.upc.caminstech.equipstic.client.dao;

import java.util.List;

import edu.upc.caminstech.equipstic.Unitat;

public interface UnitatDao {

    List<Unitat> getUnitats();

    Unitat getUnitatByIdentificador(String identificador);

    List<Unitat> getUnitatsByNom(String nom);

    Unitat getUnitatById(long idUnitat);

    List<Unitat> getUnitatsByNomAndIdentificadorAndCodi(String nom, String identificador, String codiUnitat);

}
