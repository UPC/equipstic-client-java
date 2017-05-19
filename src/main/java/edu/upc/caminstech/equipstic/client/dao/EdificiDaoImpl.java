package edu.upc.caminstech.equipstic.client.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;

import edu.upc.caminstech.equipstic.Edifici;
import edu.upc.caminstech.equipstic.client.EquipsTicClientConfiguration;
import edu.upc.caminstech.equipstic.client.Response;
import edu.upc.caminstech.equipstic.util.NullSafe;

/**
 * Classe d'ús intern de la llibreria.
 */
@Repository
public class EdificiDaoImpl extends RestDao implements EdificiDao {

    @Autowired
    public EdificiDaoImpl(EquipsTicClientConfiguration config) {
        super(config);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getEdificis")
    public List<Edifici> getEdificis() {
        List<Edifici> result = get("/edifici", new ParameterizedTypeReference<Response<List<Edifici>>>() {
        });
        return NullSafe.sorted(result);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getEdificiById")
    public Optional<Edifici> getEdificiById(long idEdifici) {
        Edifici e = get("/edifici/{id}", new ParameterizedTypeReference<Response<Edifici>>() {
        }, idEdifici);
        return Optional.ofNullable(e);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getEdificiByCodiAndCodiCampus")
    public Optional<Edifici> getEdificiByCodiAndCodiCampus(String codiEdifici, String codiCampus) {
        if (codiEdifici == null) {
            throw new IllegalArgumentException("El codi de l'edifici no pot ser null");
        }
        if (codiCampus == null) {
            throw new IllegalArgumentException("El codi del campus no pot ser null");
        }
        Edifici e = get("/edifici/cerca/codi/{codi}/codicampus/{codiCampus}",
                new ParameterizedTypeReference<Response<Edifici>>() {
                }, codiEdifici, codiCampus);
        return Optional.ofNullable(e);
    }

}
