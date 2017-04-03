package edu.upc.caminstech.equipstic.client.dao;

import java.util.List;

import edu.upc.caminstech.equipstic.Categoria;

/**
 * Interfície d'ús intern de la llibreria.
 */
public interface CategoriaDao {

    List<Categoria> getCategories();

    Categoria getCategoriaById(long idCategoria);

}
