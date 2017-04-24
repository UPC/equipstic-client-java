package edu.upc.caminstech.equipstic.client.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;

import edu.upc.caminstech.equipstic.TipusInfraestructura;
import edu.upc.caminstech.equipstic.client.EquipsTicClientConfiguration;
import edu.upc.caminstech.equipstic.client.Response;

/**
 * Classe d'ús intern de la llibreria.
 */
@Repository
public class TipusInfraestructuraDaoImpl extends RestDao implements TipusInfraestructuraDao {

    private static final ParameterizedTypeReference<Response<TipusInfraestructura>> RESPONSE_TIPUSINFRAESTRUCTURA_TYPEREF = //
            new ParameterizedTypeReference<Response<TipusInfraestructura>>() {
            };

    private static final ParameterizedTypeReference<Response<List<TipusInfraestructura>>> RESPONSE_LIST_TIPUSINFRAESTRUCTURA_TYPEREF = //
            new ParameterizedTypeReference<Response<List<TipusInfraestructura>>>() {
            };

    @Autowired
    public TipusInfraestructuraDaoImpl(EquipsTicClientConfiguration config) {
        super(config);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getTipusInfraestructura")
    public List<TipusInfraestructura> getTipusInfraestructura() {
        List<TipusInfraestructura> result = get("/tipusInfraestructura", RESPONSE_LIST_TIPUSINFRAESTRUCTURA_TYPEREF);
        return sorted(result);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getTipusInfraestructuraByCategoria")
    public List<TipusInfraestructura> getTipusInfraestructuraByCategoria(long idCategoria) {
        List<TipusInfraestructura> result = get("/tipusInfraestructura/cerca/categoria/{idCategoria}",
                RESPONSE_LIST_TIPUSINFRAESTRUCTURA_TYPEREF, idCategoria);
        return sorted(result);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getTipusInfraestructuraByCodi")
    public TipusInfraestructura getTipusInfraestructuraBycodi(String codi) {
        if (codi == null) {
            throw new IllegalArgumentException("El codi del tipus no pot ser null");
        }
        return get("/tipusInfraestructura/cerca/codi/{codi}", RESPONSE_TIPUSINFRAESTRUCTURA_TYPEREF, codi);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getTipusInfraestructuraByNom")
    public List<TipusInfraestructura> getTipusInfraestructuraByNom(String nom) {
        if (nom == null) {
            throw new IllegalArgumentException("El nom del tipus no pot ser null");
        }
        List<TipusInfraestructura> result = get("/tipusInfraestructura/cerca/nom/{nom}",
                RESPONSE_LIST_TIPUSINFRAESTRUCTURA_TYPEREF, nom);
        return sorted(result);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getTipusInfraestructuraById")
    public TipusInfraestructura getTipusInfraestructuraById(long idTipus) {
        return get("/tipusInfraestructura/{id}", RESPONSE_TIPUSINFRAESTRUCTURA_TYPEREF, idTipus);
    }

}
