package edu.upc.caminstech.equipstic.client.dao;

import java.net.URI;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestTemplate;

import edu.upc.caminstech.equipstic.Campus;
import edu.upc.caminstech.equipstic.client.Response;

public class CampusDaoImpl extends RestDao implements CampusDao {

    public CampusDaoImpl(URI baseUri, RestTemplate restTemplate) {
        super(baseUri, restTemplate);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getCampus")
    public List<Campus> getCampus() {
        List<Campus> result = get("/campus", new ParameterizedTypeReference<Response<List<Campus>>>() {
        });
        return sorted(result);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getCampusByCodi")
    public Campus getCampusByCodi(String codiCampus) {
        if (codiCampus == null) {
            throw new IllegalArgumentException("El codi del campus no pot ser null");
        }
        return get("/campus/cerca/codi/{codi}", new ParameterizedTypeReference<Response<Campus>>() {
        }, codiCampus);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getCampusById")
    public Campus getCampusById(long idCampus) {
        return get("/campus/{id}", new ParameterizedTypeReference<Response<Campus>>() {
        }, idCampus);
    }

}
