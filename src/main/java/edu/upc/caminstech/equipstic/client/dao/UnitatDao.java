package edu.upc.caminstech.equipstic.client.dao;

import java.util.List;
import java.util.Optional;

import edu.upc.caminstech.equipstic.Unitat;

/**
 * Interfície d'ús intern de la llibreria.
 */
public interface UnitatDao {

    List<Unitat> getUnitats();

    List<Unitat> getUnitatByIdentificador(String identificador);

    List<Unitat> getUnitatsByNom(String nom);

    Optional<Unitat> getUnitatById(long idUnitat);

    List<Unitat> getUnitatsByNomAndIdentificadorAndCodi(String nom, String identificador, String codiUnitat);

}
