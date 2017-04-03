package edu.upc.caminstech.equipstic.client.dao;

import java.util.List;

import edu.upc.caminstech.equipstic.Marca;

/**
 * Interfície d'ús intern de la llibreria.
 */
public interface MarcaDao {

    List<Marca> getMarques();

    List<Marca> getMarquesByNom(String nom);

    Marca getMarcaById(long idMarca);

}
