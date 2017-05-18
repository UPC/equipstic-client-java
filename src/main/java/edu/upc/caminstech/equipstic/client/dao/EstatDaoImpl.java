package edu.upc.caminstech.equipstic.client.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;

import edu.upc.caminstech.equipstic.Estat;
import edu.upc.caminstech.equipstic.client.EquipsTicClientConfiguration;
import edu.upc.caminstech.equipstic.client.Response;

/**
 * Classe d'ús intern de la llibreria.
 */
@Repository
public class EstatDaoImpl extends RestDao implements EstatDao {

    @Autowired
    public EstatDaoImpl(EquipsTicClientConfiguration config) {
        super(config);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getEstats")
    public List<Estat> getEstats() {
        List<Estat> result = get("/estat", new ParameterizedTypeReference<Response<List<Estat>>>() {
        });
        return sorted(result);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getEstatsByCodi")
    public Optional<Estat> getEstatByCodi(String codiEstat) {
        if (codiEstat == null) {
            throw new IllegalArgumentException("El codi de l'estat no pot ser null");
        }
        Estat e = get("/estat/cerca/codi/{codi}", new ParameterizedTypeReference<Response<Estat>>() {
        }, codiEstat);
        return Optional.ofNullable(e);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getEstatsByNom")
    public List<Estat> getEstatsByNom(String nomEstat) {
        if (nomEstat == null) {
            throw new IllegalArgumentException("El nom de l'estat no pot ser null");
        }
        List<Estat> result = get("/estat/cerca/nom/{nom}", new ParameterizedTypeReference<Response<List<Estat>>>() {
        }, nomEstat);
        return ObjectUtils.defaultIfNull(result, new ArrayList<>());
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getEstatById")
    public Optional<Estat> getEstatById(long idEstat) {
        Estat e = get("/estat/{id}", new ParameterizedTypeReference<Response<Estat>>() {
        }, idEstat);
        return Optional.ofNullable(e);
    }

}
