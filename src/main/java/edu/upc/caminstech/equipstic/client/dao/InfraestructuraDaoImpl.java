package edu.upc.caminstech.equipstic.client.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientResponseException;

import edu.upc.caminstech.equipstic.Infraestructura;
import edu.upc.caminstech.equipstic.client.EquipsTicClientConfiguration;
import edu.upc.caminstech.equipstic.client.Response;
import edu.upc.caminstech.equipstic.client.exception.EquipsTicClientException;
import edu.upc.caminstech.equipstic.client.exception.UnauthorizedException;
import edu.upc.caminstech.equipstic.util.NullSafe;

/**
 * Classe d'ús intern de la llibreria.
 */
@Repository
public class InfraestructuraDaoImpl extends RestDao implements InfraestructuraDao {

    private static final String GET_INFRAESTRUCTURES_BY_UNITAT = "getInfraestructuresByUnitat";
    private static final String GET_INFRAESTRUCTURA_BY_ID = "getInfraestructuraById";
    private static final String GET_INFRAESTRUCTURA_BY_MARCA_AND_NUMERO_DE_SERIE = "getInfraestructuraByMarcaAndNumeroDeSerie";

    private static final ParameterizedTypeReference<Response<Infraestructura>> RESPONSE_INFRAESTRUCTURA_TYPEREF = //
            new ParameterizedTypeReference<Response<Infraestructura>>() {
            };

    private static final ParameterizedTypeReference<Response<List<Infraestructura>>> RESPONSE_LIST_INFRAESTRUCTURA_TYPEREF = //
            new ParameterizedTypeReference<Response<List<Infraestructura>>>() {
            };

    private static final ParameterizedTypeReference<Response<Object>> RESPONSE_OBJECT_TYPEREF = //
            new ParameterizedTypeReference<Response<Object>>() {
            };

    @Autowired
    public InfraestructuraDaoImpl(EquipsTicClientConfiguration config) {
        super(config);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + GET_INFRAESTRUCTURA_BY_MARCA_AND_NUMERO_DE_SERIE)
    public Optional<Infraestructura> getInfraestructuraByMarcaAndNumeroDeSerie(long idMarca, String sn,
            boolean ambDetalls) {
        Assert.notNull(sn, "El número de sèrie no pot ser null");
        Infraestructura i = get("/infraestructura/cerca/marca/{idMarca}/sn/{sn}", RESPONSE_INFRAESTRUCTURA_TYPEREF,
                idMarca, sn);
        if (i != null && ambDetalls) {
            return getInfraestructuraById(i.getIdentificador(), ambDetalls);
        }
        return Optional.ofNullable(i);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + GET_INFRAESTRUCTURA_BY_ID)
    public Optional<Infraestructura> getInfraestructuraById(long id, boolean ambDetalls) {
        String url = ambDetalls ? "/infraestructura/{id}/detall" : "/infraestructura/{id}";
        Infraestructura i = get(url, RESPONSE_INFRAESTRUCTURA_TYPEREF, id);
        return Optional.ofNullable(i);
    }

    @Override
    @Cacheable(CacheUtils.PREFIX + GET_INFRAESTRUCTURES_BY_UNITAT)
    public List<Infraestructura> getInfraestructuresByUnitat(long idUnitat) {
        try {
            List<Infraestructura> result = get("/infraestructura/cerca/unitat/{idUnitat}",
                    RESPONSE_LIST_INFRAESTRUCTURA_TYPEREF, idUnitat);
            return NullSafe.sorted(result);
        } catch (EquipsTicClientException e) {
            if (HttpStatus.BAD_REQUEST.equals(e.getStatus().orElse(null))) {
                String msg = String.format(
                        "No teniu privilegis per consultar les infraestructures de la unitat [idUnitat: %s]", idUnitat);
                throw UnauthorizedException.of(msg, e);
            }
            throw e;
        }
    }

    @Override
    @CacheEvict(cacheNames = { CacheUtils.PREFIX + GET_INFRAESTRUCTURA_BY_MARCA_AND_NUMERO_DE_SERIE,
            CacheUtils.PREFIX + GET_INFRAESTRUCTURA_BY_ID,
            CacheUtils.PREFIX + GET_INFRAESTRUCTURES_BY_UNITAT }, allEntries = true)
    public Infraestructura altaInfraestructura(Infraestructura infraestructura) {
        HttpEntity<Infraestructura> req = preparaRequest(infraestructura);

        ResponseEntity<Response<Infraestructura>> rp = getRestTemplate().exchange(getBaseUri() + "/infraestructura",
                HttpMethod.POST, req, RESPONSE_INFRAESTRUCTURA_TYPEREF);

        Response<Infraestructura> response = rp.getBody();
        if (response.isSuccess()) {
            return response.getData();
        }

        throw new EquipsTicClientException(rp, "Error en crear la infraestructura: " + response.getMessage());
    }

    @Override
    @CacheEvict(cacheNames = { CacheUtils.PREFIX + GET_INFRAESTRUCTURA_BY_MARCA_AND_NUMERO_DE_SERIE,
            CacheUtils.PREFIX + GET_INFRAESTRUCTURA_BY_ID,
            CacheUtils.PREFIX + GET_INFRAESTRUCTURES_BY_UNITAT }, allEntries = true)
    public void baixaInfraestructura(long id) {
        delete("/infraestructura/{id}", RESPONSE_OBJECT_TYPEREF, id);
    }

    @Override
    @CacheEvict(cacheNames = { CacheUtils.PREFIX + GET_INFRAESTRUCTURA_BY_MARCA_AND_NUMERO_DE_SERIE,
            CacheUtils.PREFIX + GET_INFRAESTRUCTURA_BY_ID,
            CacheUtils.PREFIX + GET_INFRAESTRUCTURES_BY_UNITAT }, allEntries = true)
    public Infraestructura modificaInfraestructura(Infraestructura infraestructura) {
        HttpEntity<Infraestructura> req = preparaRequest(infraestructura);

        ResponseEntity<Response<Infraestructura>> rp = null;
        try {
            rp = getRestTemplate().exchange(getBaseUri() + "/infraestructura/{id}", HttpMethod.PUT, req,
                    RESPONSE_INFRAESTRUCTURA_TYPEREF, infraestructura.getIdentificador());
        } catch (HttpClientErrorException e) {
            throw new EquipsTicClientException("Error en modificar la infraestructura", e);
        } catch (RestClientResponseException e) {
            logger.error(
                    "EXCEPCIÓ INESPERADA: [rawStatusCode: {}, statusText: {}, responseBodyAsString: {}, responseHeaders: {}]",
                    e.getRawStatusCode(), e.getStatusText(), e.getResponseBodyAsString(), e.getResponseHeaders());
            throw e;
        }

        Response<Infraestructura> response = rp.getBody();
        if (response.isSuccess()) {
            return response.getData();
        }
        throw new EquipsTicClientException(rp, "Error en modificar la infraestructura: " + response.getMessage());
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
