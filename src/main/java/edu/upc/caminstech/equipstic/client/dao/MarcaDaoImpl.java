package edu.upc.caminstech.equipstic.client.dao;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestTemplate;

import edu.upc.caminstech.equipstic.Marca;
import edu.upc.caminstech.equipstic.client.Response;

public class MarcaDaoImpl extends RestDao implements MarcaDao {

    private Logger logger = LoggerFactory.getLogger(MarcaDaoImpl.class);

    public MarcaDaoImpl(URI baseUri, RestTemplate restTemplate) {
        super(baseUri, restTemplate);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getMarques")
    public List<Marca> getMarques() {
        logger.debug("getMarques()");
        List<Marca> result = get("/marca", new ParameterizedTypeReference<Response<List<Marca>>>() {
        });
        return (result != null) ? result : new ArrayList<>();
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getMarquesByNom")
    public List<Marca> getMarquesByNom(String nom) {
        logger.debug("getMarquesByNom()");
        if (nom == null) {
            throw new IllegalArgumentException("El nom de la marca no pot ser null");
        }
        List<Marca> result = get("/marca/cerca/nom/{nom}", new ParameterizedTypeReference<Response<List<Marca>>>() {
        }, nom);
        return (result != null) ? result : new ArrayList<>();
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getMarcaById")
    public Marca getMarcaById(long idMarca) {
        logger.debug("getMarcaById()");
        return get("/marca/{id}", new ParameterizedTypeReference<Response<Marca>>() {
        }, idMarca);
    }

}
