package edu.upc.caminstech.equipstic.client.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;

import edu.upc.caminstech.equipstic.Campus;
import edu.upc.caminstech.equipstic.client.EquipsTicClientConfiguration;
import edu.upc.caminstech.equipstic.client.Response;

/**
 * Classe d'ús intern de la llibreria.
 */
@Repository
public class CampusDaoImpl extends RestDao implements CampusDao {

    private static final ParameterizedTypeReference<Response<List<Campus>>> RESPONSE_LIST_CAMPUS_TYPEREF = //
            new ParameterizedTypeReference<Response<List<Campus>>>() {
            };

    private static final ParameterizedTypeReference<Response<Campus>> RESPONSE_CAMPUS_TYPEREF = //
            new ParameterizedTypeReference<Response<Campus>>() {
            };

    @Autowired
    public CampusDaoImpl(EquipsTicClientConfiguration config) {
        super(config);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getCampus")
    public List<Campus> getCampus() {
        List<Campus> result = get("/campus", RESPONSE_LIST_CAMPUS_TYPEREF);
        return sorted(result);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getCampusByCodi")
    public Campus getCampusByCodi(String codiCampus) {
        if (codiCampus == null) {
            throw new IllegalArgumentException("El codi del campus no pot ser null");
        }
        return get("/campus/cerca/codi/{codi}", RESPONSE_CAMPUS_TYPEREF, codiCampus);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getCampusById")
    public Campus getCampusById(long idCampus) {
        return get("/campus/{id}", RESPONSE_CAMPUS_TYPEREF, idCampus);
    }

}
