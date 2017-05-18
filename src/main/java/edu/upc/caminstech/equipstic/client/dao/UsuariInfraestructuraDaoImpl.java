package edu.upc.caminstech.equipstic.client.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;

import edu.upc.caminstech.equipstic.UsuariInfraestructura;
import edu.upc.caminstech.equipstic.client.EquipsTicClientConfiguration;
import edu.upc.caminstech.equipstic.client.Response;

/**
 * Classe d'ús intern de la llibreria.
 */
@Repository
public class UsuariInfraestructuraDaoImpl extends RestDao implements UsuariInfraestructuraDao {

    private static final ParameterizedTypeReference<Response<List<UsuariInfraestructura>>> RESPONSE_LIST_USUARIINFRAESTRUCTURA_TYPEREF = //
            new ParameterizedTypeReference<Response<List<UsuariInfraestructura>>>() {
            };

    private static final ParameterizedTypeReference<Response<UsuariInfraestructura>> RESPONSE_USUARIINFRAESTRUCTURA_TYPEREF = //
            new ParameterizedTypeReference<Response<UsuariInfraestructura>>() {
            };

    @Autowired
    public UsuariInfraestructuraDaoImpl(EquipsTicClientConfiguration config) {
        super(config);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getUsuariInfraestructura")
    public Optional<UsuariInfraestructura> getUsuariInfraestructura(long idUsuariInfraestructura) {
        UsuariInfraestructura u = get("/usuariInfraestructura/{idUsuariInfraestructura}",
                RESPONSE_USUARIINFRAESTRUCTURA_TYPEREF, idUsuariInfraestructura);
        return Optional.ofNullable(u);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getUsuarisInfraestructura")
    public List<UsuariInfraestructura> getUsuarisInfraestructura() {
        List<UsuariInfraestructura> result = get("/usuariInfraestructura", RESPONSE_LIST_USUARIINFRAESTRUCTURA_TYPEREF);
        return sorted(result);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getUsuarisInfraestructuraByNom")
    public List<UsuariInfraestructura> getUsuarisInfraestructuraByNom(String nom) {
        if (nom == null) {
            throw new IllegalArgumentException("El nom de l'usuari no pot ser null");
        }
        List<UsuariInfraestructura> result = get("/usuariInfraestructura/cerca/nom/{nom}",
                RESPONSE_LIST_USUARIINFRAESTRUCTURA_TYPEREF, nom);
        return sorted(result);
    }

}
