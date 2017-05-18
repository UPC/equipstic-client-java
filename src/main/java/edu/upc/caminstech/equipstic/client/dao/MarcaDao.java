package edu.upc.caminstech.equipstic.client.dao;

import java.util.List;
import java.util.Optional;

import edu.upc.caminstech.equipstic.Marca;

/**
 * Interfície d'ús intern de la llibreria.
 */
public interface MarcaDao {

    List<Marca> getMarques();

    List<Marca> getMarquesByNom(String nom);

    Optional<Marca> getMarcaById(long idMarca);

}
