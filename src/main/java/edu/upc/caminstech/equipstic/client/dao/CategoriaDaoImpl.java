package edu.upc.caminstech.equipstic.client.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;

import edu.upc.caminstech.equipstic.Categoria;
import edu.upc.caminstech.equipstic.client.EquipsTicClientConfiguration;
import edu.upc.caminstech.equipstic.client.Response;
import edu.upc.caminstech.equipstic.util.NullSafe;

/**
 * Classe d'ús intern de la llibreria.
 */
@Repository
public class CategoriaDaoImpl extends RestDao implements CategoriaDao {

    @Autowired
    public CategoriaDaoImpl(EquipsTicClientConfiguration config) {
        super(config);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getCategories")
    public List<Categoria> getCategories() {
        List<Categoria> result = get("/categoria", new ParameterizedTypeReference<Response<List<Categoria>>>() {
        });
        return NullSafe.sorted(result);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getCategoriaById")
    public Optional<Categoria> getCategoriaById(long idCategoria) {
        Categoria c = get("/categoria/{id}", new ParameterizedTypeReference<Response<Categoria>>() {
        }, idCategoria);
        return Optional.ofNullable(c);
    }

}
