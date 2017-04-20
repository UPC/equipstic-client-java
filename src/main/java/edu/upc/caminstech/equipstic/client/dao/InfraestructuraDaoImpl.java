package edu.upc.caminstech.equipstic.client.dao;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import edu.upc.caminstech.equipstic.Infraestructura;
import edu.upc.caminstech.equipstic.client.EquipsTicClientConfiguration;
import edu.upc.caminstech.equipstic.client.EquipsTicClientException;
import edu.upc.caminstech.equipstic.client.Response;

/**
 * Classe d'ús intern de la llibreria.
 */
@Repository
public class InfraestructuraDaoImpl extends RestDao implements InfraestructuraDao {

    @Autowired
    public InfraestructuraDaoImpl(EquipsTicClientConfiguration config) {
        super(config);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getInfraestructuraByMarcaAndNumeroDeSerie")
    public Infraestructura getInfraestructuraByMarcaAndNumeroDeSerie(long idMarca, String sn, boolean ambDetalls) {
        Assert.notNull(sn, "El número de sèrie no pot ser null");
        Infraestructura i = get("/infraestructura/cerca/marca/{idMarca}/sn/{sn}",
                new ParameterizedTypeReference<Response<Infraestructura>>() {
                }, idMarca, sn);
        if (i != null && ambDetalls) {
            return getInfraestructuraById(i.getIdentificador(), ambDetalls);
        }
        return i;
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getInfraestructuraById")
    public Infraestructura getInfraestructuraById(long id, boolean ambDetalls) {
        String url = ambDetalls ? "/infraestructura/{id}/detalls" : "/infraestructura/{id}";
        Infraestructura i = get(url, new ParameterizedTypeReference<Response<Infraestructura>>() {
        }, id);
        return i;
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + "getInfraestructuresByUnitat")
    public List<Infraestructura> getInfraestructuresByUnitat(long idUnitat) {
        List<Infraestructura> result = get("/infraestructura/cerca/unitat/{idUnitat}",
                new ParameterizedTypeReference<Response<List<Infraestructura>>>() {
                }, idUnitat);
        return sorted(result);
    }

    @Override
    @CacheEvict(cacheNames = { CacheUtils.PREFIX + "getInfraestructuraByMarcaAndNumeroDeSerie",
            CacheUtils.PREFIX + "getInfraestructuraById", CacheUtils.PREFIX + "getInfraestructuresByUnitat" })
    public Infraestructura altaInfraestructura(Infraestructura infraestructura) {
        HttpEntity<Infraestructura> req = preparaRequest(infraestructura);

        ResponseEntity<Response<Infraestructura>> rp = getRestTemplate().exchange(getBaseUri() + "/infraestructura",
                HttpMethod.POST, req, new ParameterizedTypeReference<Response<Infraestructura>>() {
                });

        Response<Infraestructura> response = rp.getBody();
        if (response.isSuccess()) {
            return response.getData();
        }

        throw new EquipsTicClientException(response, "Error en crear la infraestructura: " + response.getMessage());
    }

    @Override
    @CacheEvict(cacheNames = { CacheUtils.PREFIX + "getInfraestructuraByMarcaAndNumeroDeSerie",
            CacheUtils.PREFIX + "getInfraestructuraById", CacheUtils.PREFIX + "getInfraestructuresByUnitat" })
    public void baixaInfraestructura(long id) {
        /*
         * Fem servir 'Object' com a tipus parametritzat perquè en el DELETE
         * l'objecte inclós a la Response és null i no ens importa el seu tipus.
         */
        ResponseEntity<Response<Object>> rp = getRestTemplate().exchange(getBaseUri() + "/infraestructura/{id}",
                HttpMethod.DELETE, null, new ParameterizedTypeReference<Response<Object>>() {
                }, id);
        Response<Object> response = rp.getBody();
        if (!response.isSuccess()) {
            throw new EquipsTicClientException(response,
                    "Error en esborrar la infraestructura: " + response.getMessage());
        }
    }

    @Override
    @CacheEvict(cacheNames = { CacheUtils.PREFIX + "getInfraestructuraByMarcaAndNumeroDeSerie",
            CacheUtils.PREFIX + "getInfraestructuraById", CacheUtils.PREFIX + "getInfraestructuresByUnitat" })
    public Infraestructura modificaInfraestructura(Infraestructura infraestructura) {
        HttpEntity<Infraestructura> req = preparaRequest(infraestructura);

        ResponseEntity<Response<Infraestructura>> rp = getRestTemplate().exchange(
                getBaseUri() + "/infraestructura/{id}", HttpMethod.PUT, req,
                new ParameterizedTypeReference<Response<Infraestructura>>() {
                }, infraestructura.getIdentificador());

        Response<Infraestructura> response = rp.getBody();
        if (response.isSuccess()) {
            return response.getData();
        }
        throw new EquipsTicClientException(response, "Error en modificar la infraestructura: " + response.getMessage());
    }

    /**
     * Mètode auxiliar per crear una petició HTTP.
     * <p>
     * La petició es crearà amb els headers (Accept, Content-Type) i el body
     * adients.
     * 
     * @param infraestructura
     *            la infraestructura que s'afegirà al body en format JSON. No
     *            pot ser {@code null}.
     * @return
     */
    private HttpEntity<Infraestructura> preparaRequest(Infraestructura infraestructura) {
        if (infraestructura == null) {
            throw new IllegalArgumentException("La infraestructura no pot ser null");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new HttpEntity<>(infraestructura, headers);
    }

}
