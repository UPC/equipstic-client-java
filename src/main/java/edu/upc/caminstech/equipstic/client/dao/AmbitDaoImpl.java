package edu.upc.caminstech.equipstic.client.dao;

import java.net.URI;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestTemplate;

import edu.upc.caminstech.equipstic.Ambit;
import edu.upc.caminstech.equipstic.client.Response;

public class AmbitDaoImpl extends RestDao implements AmbitDao {

    public AmbitDaoImpl(URI baseUri, RestTemplate restTemplate) {
        super(baseUri, restTemplate);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getAmbits")
    public List<Ambit> getAmbits() {
        List<Ambit> result = get("/ambit", new ParameterizedTypeReference<Response<List<Ambit>>>() {
        });
        return sorted(result);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getAmbitsByNom")
    public List<Ambit> getAmbitsByNom(String nomAmbit) {
        if (nomAmbit == null) {
            throw new IllegalArgumentException("El nom de l'àmbit no pot ser null");
        }
        List<Ambit> result = get("/ambit/cerca/nom/{nom}", new ParameterizedTypeReference<Response<List<Ambit>>>() {
        }, nomAmbit);
        return sorted(result);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getAmbitById")
    public Ambit getAmbitById(long idAmbit) {
        return get("/ambit/{id}", new ParameterizedTypeReference<Response<Ambit>>() {
        }, idAmbit);
    }

}
