package edu.upc.caminstech.equipstic.client.dao;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestTemplate;

import edu.upc.caminstech.equipstic.Estat;
import edu.upc.caminstech.equipstic.client.Response;

public class EstatDaoImpl extends RestDao implements EstatDao {

    public EstatDaoImpl(URI baseUri, RestTemplate restTemplate) {
        super(baseUri, restTemplate);
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
    public Estat getEstatByCodi(String codiEstat) {
        if (codiEstat == null) {
            throw new IllegalArgumentException("El codi de l'estat no pot ser null");
        }
        return get("/estat/cerca/codi/{codi}", new ParameterizedTypeReference<Response<Estat>>() {
        }, codiEstat);
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
    public Estat getEstatById(long idEstat) {
        return get("/estat/{id}", new ParameterizedTypeReference<Response<Estat>>() {
        }, idEstat);
    }

}
