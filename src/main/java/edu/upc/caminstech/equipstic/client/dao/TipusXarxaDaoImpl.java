package edu.upc.caminstech.equipstic.client.dao;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestTemplate;

import edu.upc.caminstech.equipstic.TipusXarxa;
import edu.upc.caminstech.equipstic.client.Response;

public class TipusXarxaDaoImpl extends RestDao implements TipusXarxaDao {

    public TipusXarxaDaoImpl(URI baseUri, RestTemplate restTemplate) {
        super(baseUri, restTemplate);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getTipusXarxa")
    public List<TipusXarxa> getTipusXarxa() {
        List<TipusXarxa> result = get("/tipusXarxa", new ParameterizedTypeReference<Response<List<TipusXarxa>>>() {
        });
        return (result != null) ? result : new ArrayList<>();
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getTipusXarxaById")
    public TipusXarxa getTipusXarxaById(long idTipusXarxa) {
        return get("/tipusXarxa/{id}", new ParameterizedTypeReference<Response<TipusXarxa>>() {
        }, idTipusXarxa);
    }

}
