package edu.upc.caminstech.equipstic.client.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;

import edu.upc.caminstech.equipstic.TipusUs;
import edu.upc.caminstech.equipstic.client.EquipsTicClientConfiguration;
import edu.upc.caminstech.equipstic.client.Response;

/**
 * Classe d'ús intern de la llibreria.
 */
@Repository
public class TipusUsDaoImpl extends RestDao implements TipusUsDao {

    private static final ParameterizedTypeReference<Response<List<TipusUs>>> RESPONSE_LIST_TIPUSUS_TYPEREF = //
            new ParameterizedTypeReference<Response<List<TipusUs>>>() {
            };

    @Autowired
    public TipusUsDaoImpl(EquipsTicClientConfiguration config) {
        super(config);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getTipusUs")
    public List<TipusUs> getTipusUs() {
        List<TipusUs> result = get("/tipusUs", RESPONSE_LIST_TIPUSUS_TYPEREF);
        return sorted(result);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getTipusUsByUnitat")
    public List<TipusUs> getTipusUsByUnitat(long idUnitat) {
        List<TipusUs> result = get("/tipusUs/cerca/unitat/{idUnitat}", RESPONSE_LIST_TIPUSUS_TYPEREF, idUnitat);
        return sorted(result);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getTipusUsById")
    public Optional<TipusUs> getTipusUsById(long idTipusUs) {
        TipusUs t = get("/tipusUs/{idTipusUs}", new ParameterizedTypeReference<Response<TipusUs>>() {
        }, idTipusUs);
        return Optional.ofNullable(t);
    }

}
