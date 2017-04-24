package edu.upc.caminstech.equipstic.client.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;

import edu.upc.caminstech.equipstic.Marca;
import edu.upc.caminstech.equipstic.client.EquipsTicClientConfiguration;
import edu.upc.caminstech.equipstic.client.Response;

/**
 * Classe d'ús intern de la llibreria.
 */
@Repository
public class MarcaDaoImpl extends RestDao implements MarcaDao {

    protected final Logger logger = LoggerFactory.getLogger(MarcaDaoImpl.class);

    private static final ParameterizedTypeReference<Response<List<Marca>>> RESPONSE_LIST_MARCA_TYPEREF = //
            new ParameterizedTypeReference<Response<List<Marca>>>() {
            };

    private static final ParameterizedTypeReference<Response<Marca>> RESPONSE_MARCA_TYPEREF = //
            new ParameterizedTypeReference<Response<Marca>>() {
            };

    @Autowired
    public MarcaDaoImpl(EquipsTicClientConfiguration config) {
        super(config);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getMarques")
    public List<Marca> getMarques() {
        List<Marca> result = get("/marca", RESPONSE_LIST_MARCA_TYPEREF);
        return (result != null) ? result : new ArrayList<>();
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getMarquesByNom")
    public List<Marca> getMarquesByNom(String nom) {
        if (nom == null) {
            throw new IllegalArgumentException("El nom de la marca no pot ser null");
        }
        List<Marca> result = get("/marca/cerca/nom/{nom}", RESPONSE_LIST_MARCA_TYPEREF, nom);
        return (result != null) ? result : new ArrayList<>();
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getMarcaById")
    public Marca getMarcaById(long idMarca) {
        return get("/marca/{id}", RESPONSE_MARCA_TYPEREF, idMarca);
    }

}
