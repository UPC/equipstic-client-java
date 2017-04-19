package edu.upc.caminstech.equipstic.client.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;

import edu.upc.caminstech.equipstic.Ambit;
import edu.upc.caminstech.equipstic.client.EquipsTicClientConfiguration;
import edu.upc.caminstech.equipstic.client.Response;

/**
 * Classe d'ús intern de la llibreria.
 */
@Repository
public class AmbitDaoImpl extends RestDao implements AmbitDao {

    @Autowired
    public AmbitDaoImpl(EquipsTicClientConfiguration config) {
        super(config);
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

    @Override
    @Cacheable(CacheUtils.PREFIX + "getAmbitsByCodi")
    public List<Ambit> getAmbitsByCodi(String codiAmbit) {
        if (StringUtils.isBlank(codiAmbit)) {
            throw new IllegalArgumentException("El codi de l'àmbit no pot ser null");
        }
        List<Ambit> result = get("/ambit/cerca/codi/{codi}", new ParameterizedTypeReference<Response<List<Ambit>>>() {
        }, codiAmbit);
        return sorted(result);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getAmbitsByCategoria")
    public List<Ambit> getAmbitsByCategoria(long idCategoria) {
        List<Ambit> result = get("/ambit/cerca/categoria/{idCategoria}",
                new ParameterizedTypeReference<Response<List<Ambit>>>() {
                }, idCategoria);
        return sorted(result);
    }

}
