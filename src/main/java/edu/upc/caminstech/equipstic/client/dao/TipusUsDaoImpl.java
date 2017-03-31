package edu.upc.caminstech.equipstic.client.dao;

import java.net.URI;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestTemplate;

import edu.upc.caminstech.equipstic.TipusUs;
import edu.upc.caminstech.equipstic.client.Response;

public class TipusUsDaoImpl extends RestDao implements TipusUsDao {

    public TipusUsDaoImpl(URI baseUri, RestTemplate restTemplate) {
        super(baseUri, restTemplate);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getTipusUs")
    public List<TipusUs> getTipusUs() {
        List<TipusUs> result = get("/tipusUs", new ParameterizedTypeReference<Response<List<TipusUs>>>() {
        });
        return sorted(result);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getTipusUsByUnitat")
    public List<TipusUs> getTipusUsByUnitat(long idUnitat) {
        List<TipusUs> result = get("/tipusUs/cerca/unitat/{idUnitat}",
                new ParameterizedTypeReference<Response<List<TipusUs>>>() {
                }, idUnitat);
        return sorted(result);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getTipusUsById")
    public TipusUs getTipusUsById(long idTipusUs) {
        return get("/tipusUs/{idTipusUs}", new ParameterizedTypeReference<Response<TipusUs>>() {
        }, idTipusUs);
    }

}
