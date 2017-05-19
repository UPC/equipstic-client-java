package edu.upc.caminstech.equipstic.client.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;

import edu.upc.caminstech.equipstic.TipusInfraestructura;
import edu.upc.caminstech.equipstic.client.EquipsTicClientConfiguration;
import edu.upc.caminstech.equipstic.client.Response;
import edu.upc.caminstech.equipstic.util.NullSafe;

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
        return NullSafe.sorted(result);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getTipusInfraestructuraByCategoria")
    public List<TipusInfraestructura> getTipusInfraestructuraByCategoria(long idCategoria) {
        List<TipusInfraestructura> result = get("/tipusInfraestructura/cerca/categoria/{idCategoria}",
                RESPONSE_LIST_TIPUSINFRAESTRUCTURA_TYPEREF, idCategoria);
        return NullSafe.sorted(result);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getTipusInfraestructuraByCodi")
    public Optional<TipusInfraestructura> getTipusInfraestructuraBycodi(String codi) {
        if (codi == null) {
            throw new IllegalArgumentException("El codi del tipus no pot ser null");
        }
        TipusInfraestructura t = get("/tipusInfraestructura/cerca/codi/{codi}", RESPONSE_TIPUSINFRAESTRUCTURA_TYPEREF,
                codi);
        return Optional.ofNullable(t);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getTipusInfraestructuraByNom")
    public List<TipusInfraestructura> getTipusInfraestructuraByNom(String nom) {
        if (nom == null) {
            throw new IllegalArgumentException("El nom del tipus no pot ser null");
        }
        List<TipusInfraestructura> result = get("/tipusInfraestructura/cerca/nom/{nom}",
                RESPONSE_LIST_TIPUSINFRAESTRUCTURA_TYPEREF, nom);
        return NullSafe.sorted(result);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getTipusInfraestructuraById")
    public Optional<TipusInfraestructura> getTipusInfraestructuraById(long idTipus) {
        TipusInfraestructura t = get("/tipusInfraestructura/{id}", RESPONSE_TIPUSINFRAESTRUCTURA_TYPEREF, idTipus);
        return Optional.ofNullable(t);
    }

}
