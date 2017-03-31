package edu.upc.caminstech.equipstic.client.dao;

import java.util.List;

import edu.upc.caminstech.equipstic.Categoria;

public interface CategoriaDao {

    List<Categoria> getCategories();

    Categoria getCategoriaById(long idCategoria);

}
