package edu.upc.caminstech.equipstic.client.dao;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestTemplate;

import edu.upc.caminstech.equipstic.SistemaOperatiu;
import edu.upc.caminstech.equipstic.client.Response;

public class SistemaOperatiuDaoImpl extends RestDao implements SistemaOperatiuDao {

    public SistemaOperatiuDaoImpl(URI baseUri, RestTemplate restTemplate) {
        super(baseUri, restTemplate);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getSistemesOperatius")
    public List<SistemaOperatiu> getSistemesOperatius() {
        List<SistemaOperatiu> result = get("/sistemaOperatiu",
                new ParameterizedTypeReference<Response<List<SistemaOperatiu>>>() {
                });
        return (result != null) ? sorted(result) : new ArrayList<>();
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getSistemesOperatiusByCategoria")
    public List<SistemaOperatiu> getSistemesOperatiusByCategoria(long idCategoria) {
        List<SistemaOperatiu> result = get("/sistemaOperatiu/cerca/categoria/{idCategoria}",
                new ParameterizedTypeReference<Response<List<SistemaOperatiu>>>() {
                }, idCategoria);
        return (result != null) ? sorted(result) : new ArrayList<>();
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getSistemesOperatiusByCodi")
    public List<SistemaOperatiu> getSistemesOperatiusByCodi(String codi) {
        if (StringUtils.isBlank(codi)) {
            throw new IllegalArgumentException("parameter 'codi' can not be blank");
        }
        List<SistemaOperatiu> result = get("/sistemaOperatiu/cerca/codi/{codi}",
                new ParameterizedTypeReference<Response<List<SistemaOperatiu>>>() {
                }, codi);
        return (result != null) ? sorted(result) : new ArrayList<>();
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getSistemesOperatiusByNom")
    public List<SistemaOperatiu> getSistemesOperatiusByNom(String nom) {
        if (StringUtils.isBlank(nom)) {
            throw new IllegalArgumentException("parameter 'nom' can not be blank");
        }
        List<SistemaOperatiu> result = get("/sistemaOperatiu/cerca/nom/{nom}",
                new ParameterizedTypeReference<Response<List<SistemaOperatiu>>>() {
                }, nom);
        return (result != null) ? sorted(result) : new ArrayList<>();
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getSistemaOperatiuById")
    public SistemaOperatiu getSistemaOperatiuById(long idSistemaOperatiu) {
        return get("/sistemaOperatiu/{id}", new ParameterizedTypeReference<Response<SistemaOperatiu>>() {
        }, idSistemaOperatiu);
    }

}
