package edu.upc.caminstech.equipstic.client.dao;

import java.util.List;
import java.util.Optional;

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

    private static final ParameterizedTypeReference<Response<List<Ambit>>> RESPONSE_LIST_AMBIT_TYPEREF = //
            new ParameterizedTypeReference<Response<List<Ambit>>>() {
            };

    private static final ParameterizedTypeReference<Response<Ambit>> RESPONSE_AMBIT_TYPEREF = //
            new ParameterizedTypeReference<Response<Ambit>>() {
            };

    @Autowired
    public AmbitDaoImpl(EquipsTicClientConfiguration config) {
        super(config);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getAmbits")
    public List<Ambit> getAmbits() {
        List<Ambit> result = get("/ambit", RESPONSE_LIST_AMBIT_TYPEREF);
        return sorted(result);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getAmbitsByNom")
    public List<Ambit> getAmbitsByNom(String nomAmbit) {
        if (nomAmbit == null) {
            throw new IllegalArgumentException("El nom de l'àmbit no pot ser null");
        }
        List<Ambit> result = get("/ambit/cerca/nom/{nom}", RESPONSE_LIST_AMBIT_TYPEREF, nomAmbit);
        return sorted(result);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getAmbitById")
    public Optional<Ambit> getAmbitById(long idAmbit) {
        Ambit result = get("/ambit/{id}", RESPONSE_AMBIT_TYPEREF, idAmbit);
        return Optional.ofNullable(result);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getAmbitsByCodi")
    public List<Ambit> getAmbitsByCodi(String codiAmbit) {
        if (StringUtils.isBlank(codiAmbit)) {
            throw new IllegalArgumentException("El codi de l'àmbit no pot ser null");
        }
        List<Ambit> result = get("/ambit/cerca/codi/{codi}", RESPONSE_LIST_AMBIT_TYPEREF, codiAmbit);
        return sorted(result);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getAmbitsByCategoria")
    public List<Ambit> getAmbitsByCategoria(long idCategoria) {
        List<Ambit> result = get("/ambit/cerca/categoria/{idCategoria}", RESPONSE_LIST_AMBIT_TYPEREF, idCategoria);
        return sorted(result);
    }

}
