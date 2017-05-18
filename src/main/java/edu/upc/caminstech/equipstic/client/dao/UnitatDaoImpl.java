package edu.upc.caminstech.equipstic.client.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;

import edu.upc.caminstech.equipstic.Unitat;
import edu.upc.caminstech.equipstic.client.EquipsTicClientConfiguration;
import edu.upc.caminstech.equipstic.client.Response;

/**
 * Classe d'ús intern de la llibreria.
 */
@Repository
public class UnitatDaoImpl extends RestDao implements UnitatDao {

    private static final ParameterizedTypeReference<Response<Unitat>> RESPONSE_UNITAT_TYPEREF = //
            new ParameterizedTypeReference<Response<Unitat>>() {
            };

    private static final ParameterizedTypeReference<Response<List<Unitat>>> RESPONSE_LIST_UNITAT_TYPEREF = //
            new ParameterizedTypeReference<Response<List<Unitat>>>() {
            };

    @Autowired
    public UnitatDaoImpl(EquipsTicClientConfiguration config) {
        super(config);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getUnitats")
    public List<Unitat> getUnitats() {
        List<Unitat> result = get("/unitat", RESPONSE_LIST_UNITAT_TYPEREF);
        return sorted(result);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getUnitatByIdentificador")
    public List<Unitat> getUnitatByIdentificador(String identificador) {
        if (identificador == null) {
            throw new IllegalArgumentException("L'identificador de la unitat no pot ser null");
        }

        return get("/unitat/cerca/identificador/{identificador}", RESPONSE_LIST_UNITAT_TYPEREF, identificador);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getUnitatsByNom")
    public List<Unitat> getUnitatsByNom(String nom) {
        if (nom == null) {
            throw new IllegalArgumentException("El nom de la unitat no pot ser null");
        }
        List<Unitat> result = get("/unitat/cerca/nom/{nom}", RESPONSE_LIST_UNITAT_TYPEREF, nom);
        return sorted(result);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getUnitatById")
    public Optional<Unitat> getUnitatById(long idUnitat) {
        Unitat u = get("/unitat/{id}", RESPONSE_UNITAT_TYPEREF, idUnitat);
        return Optional.ofNullable(u);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getUnitatsByNomAndIdentificadorAndCodi")
    public List<Unitat> getUnitatsByNomAndIdentificadorAndCodi(String nom, String identificador, String codiUnitat) {
        if (nom == null) {
            throw new IllegalArgumentException("El nom de la unitat no pot ser null");
        }
        if (identificador == null) {
            throw new IllegalArgumentException("L'identificador de la unitat no pot ser null");
        }
        if (codiUnitat == null) {
            throw new IllegalArgumentException("El codi de la unitat no pot ser null");
        }

        List<Unitat> result = get("/unitat/cerca/nom/{nom}/identificador/{identificador}/codi/{codi}",
                RESPONSE_LIST_UNITAT_TYPEREF, nom, identificador, codiUnitat);

        return sorted(result);
    }

}
