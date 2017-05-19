package edu.upc.caminstech.equipstic.client.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;

import edu.upc.caminstech.equipstic.SistemaOperatiu;
import edu.upc.caminstech.equipstic.client.EquipsTicClientConfiguration;
import edu.upc.caminstech.equipstic.client.Response;
import edu.upc.caminstech.equipstic.util.NullSafe;

/**
 * Classe d'ús intern de la llibreria.
 */
@Repository
public class SistemaOperatiuDaoImpl extends RestDao implements SistemaOperatiuDao {

    private static final ParameterizedTypeReference<Response<SistemaOperatiu>> RESPONSE_SISTEMAOPERATIU_TYPEREF = //
            new ParameterizedTypeReference<Response<SistemaOperatiu>>() {
            };

    private static final ParameterizedTypeReference<Response<List<SistemaOperatiu>>> RESPONSE_LIST_SISTEMAOPERATIU_TYPEREF = //
            new ParameterizedTypeReference<Response<List<SistemaOperatiu>>>() {
            };

    @Autowired
    public SistemaOperatiuDaoImpl(EquipsTicClientConfiguration config) {
        super(config);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getSistemesOperatius")
    public List<SistemaOperatiu> getSistemesOperatius() {
        List<SistemaOperatiu> result = get("/sistemaOperatiu", RESPONSE_LIST_SISTEMAOPERATIU_TYPEREF);
        return (result != null) ? NullSafe.sorted(result) : new ArrayList<>();
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getSistemesOperatiusByCategoria")
    public List<SistemaOperatiu> getSistemesOperatiusByCategoria(long idCategoria) {
        List<SistemaOperatiu> result = get("/sistemaOperatiu/cerca/categoria/{idCategoria}",
                RESPONSE_LIST_SISTEMAOPERATIU_TYPEREF, idCategoria);
        return (result != null) ? NullSafe.sorted(result) : new ArrayList<>();
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getSistemesOperatiusByCodi")
    public List<SistemaOperatiu> getSistemesOperatiusByCodi(String codi) {
        if (StringUtils.isBlank(codi)) {
            throw new IllegalArgumentException("parameter 'codi' can not be blank");
        }
        List<SistemaOperatiu> result = get("/sistemaOperatiu/cerca/codi/{codi}", RESPONSE_LIST_SISTEMAOPERATIU_TYPEREF,
                codi);
        return (result != null) ? NullSafe.sorted(result) : new ArrayList<>();
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getSistemesOperatiusByNom")
    public List<SistemaOperatiu> getSistemesOperatiusByNom(String nom) {
        if (StringUtils.isBlank(nom)) {
            throw new IllegalArgumentException("parameter 'nom' can not be blank");
        }
        List<SistemaOperatiu> result = get("/sistemaOperatiu/cerca/nom/{nom}", RESPONSE_LIST_SISTEMAOPERATIU_TYPEREF,
                nom);
        return (result != null) ? NullSafe.sorted(result) : new ArrayList<>();
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getSistemaOperatiuById")
    public Optional<SistemaOperatiu> getSistemaOperatiuById(long idSistemaOperatiu) {
        SistemaOperatiu so = get("/sistemaOperatiu/{id}", RESPONSE_SISTEMAOPERATIU_TYPEREF, idSistemaOperatiu);
        return Optional.ofNullable(so);
    }

}
