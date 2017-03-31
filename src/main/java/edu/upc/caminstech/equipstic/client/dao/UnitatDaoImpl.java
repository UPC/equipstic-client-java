package edu.upc.caminstech.equipstic.client.dao;

import java.net.URI;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestTemplate;

import edu.upc.caminstech.equipstic.Unitat;
import edu.upc.caminstech.equipstic.client.Response;

public class UnitatDaoImpl extends RestDao implements UnitatDao {

    public UnitatDaoImpl(URI baseUri, RestTemplate restTemplate) {
        super(baseUri, restTemplate);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getUnitats")
    public List<Unitat> getUnitats() {
        List<Unitat> result = get("/unitat", new ParameterizedTypeReference<Response<List<Unitat>>>() {
        });
        return sorted(result);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getUnitatByIdentificador")
    public Unitat getUnitatByIdentificador(String identificador) {
        if (identificador == null) {
            throw new IllegalArgumentException("L'identificador de la unitat no pot ser null");
        }

        return get("/unitat/cerca/identificador/{identificador}", new ParameterizedTypeReference<Response<Unitat>>() {
        }, identificador);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getUnitatsByNom")
    public List<Unitat> getUnitatsByNom(String nom) {
        if (nom == null) {
            throw new IllegalArgumentException("El nom de la unitat no pot ser null");
        }
        List<Unitat> result = get("/unitat/cerca/nom/{nom}", new ParameterizedTypeReference<Response<List<Unitat>>>() {
        }, nom);
        return sorted(result);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getUnitatById")
    public Unitat getUnitatById(long idUnitat) {
        return get("/unitat/{id}", new ParameterizedTypeReference<Response<Unitat>>() {
        }, idUnitat);
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
                new ParameterizedTypeReference<Response<List<Unitat>>>() {
                }, nom, identificador, codiUnitat);

        return sorted(result);
    }

}
