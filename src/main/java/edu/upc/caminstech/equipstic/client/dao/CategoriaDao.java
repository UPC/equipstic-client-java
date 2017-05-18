package edu.upc.caminstech.equipstic.client.dao;

import java.util.List;
import java.util.Optional;

import edu.upc.caminstech.equipstic.Categoria;

/**
 * Interfície d'ús intern de la llibreria.
 */
public interface CategoriaDao {

    List<Categoria> getCategories();

    Optional<Categoria> getCategoriaById(long idCategoria);

}
