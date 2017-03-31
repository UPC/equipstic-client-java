package edu.upc.caminstech.equipstic.client.dao;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import edu.upc.caminstech.equipstic.Edifici;
import edu.upc.caminstech.equipstic.Estat;
import edu.upc.caminstech.equipstic.Infraestructura;
import edu.upc.caminstech.equipstic.Marca;
import edu.upc.caminstech.equipstic.SistemaOperatiu;
import edu.upc.caminstech.equipstic.TipusInfraestructura;
import edu.upc.caminstech.equipstic.Unitat;
import edu.upc.caminstech.equipstic.UsuariInfraestructura;
import edu.upc.caminstech.equipstic.client.EquipsTicClientException;
import edu.upc.caminstech.equipstic.client.Response;

public class InfraestructuraDaoImpl extends RestDao implements InfraestructuraDao {

    private final EdificiDao edificiDao;
    private final EstatDao estatDao;
    private final MarcaDao marcaDao;
    private final SistemaOperatiuDao sistemaOperatiuDao;
    private final TipusInfraestructuraDao tipusInfraestructuraDao;
    private final UnitatDao unitatDao;
    private final UsuariInfraestructuraDao usuariInfraestructuraDao;

    public InfraestructuraDaoImpl(URI baseUri, RestTemplate restTemplate, EdificiDao edificiDao, EstatDao estatDao,
            MarcaDao marcaDao, SistemaOperatiuDao sistemaOperatiuDao, TipusInfraestructuraDao tipusInfraestructuraDao,
            UnitatDao unitatDao, UsuariInfraestructuraDao usuariInfraestructuraDao) {

        super(baseUri, restTemplate);

        this.edificiDao = edificiDao;
        this.estatDao = estatDao;
        this.marcaDao = marcaDao;
        this.sistemaOperatiuDao = sistemaOperatiuDao;
        this.tipusInfraestructuraDao = tipusInfraestructuraDao;
        this.unitatDao = unitatDao;
        this.usuariInfraestructuraDao = usuariInfraestructuraDao;
    }

    @Override
    public Infraestructura getInfraestructuraByMarcaAndNumeroDeSerie(long idMarca, String sn) {
        Assert.notNull(sn, "El número de sèrie no pot ser null");
        Infraestructura i = get("/infraestructura/cerca/marca/{idMarca}/sn/{sn}",
                new ParameterizedTypeReference<Response<Infraestructura>>() {
                }, idMarca, sn);
        ompleCampsNoInicialitzatsInfraestructura(i);
        return i;
    }

    @Override
    public Infraestructura getInfraestructuraById(long id) {
        Infraestructura i = get("/infraestructura/{id}", new ParameterizedTypeReference<Response<Infraestructura>>() {
        }, id);
        ompleCampsNoInicialitzatsInfraestructura(i);
        return i;
    }

    @Override
    public List<Infraestructura> getInfraestructuresByUnitat(long idUnitat) {
        List<Infraestructura> result = get("/infraestructura/cerca/unitat/{idUnitat}",
                new ParameterizedTypeReference<Response<List<Infraestructura>>>() {
                }, idUnitat);
        if (result != null) {
            result.stream().forEach(i -> ompleCampsNoInicialitzatsInfraestructura(i));
        }
        return sorted(result);
    }

    @Override
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

    /**
     * Inicialitza els atributs d'una infraestructura que corresponen a objectes
     * JSON que només tenen inicialitzat l'identificador.
     * 
     * @param infra
     *            la infraestructura
     */
    private void ompleCampsNoInicialitzatsInfraestructura(Infraestructura infra) {
        if (infra == null) {
            return;
        }
        Marca marca = marcaDao.getMarcaById(infra.getMarca().getIdMarca());
        TipusInfraestructura tipusInfraestructura = tipusInfraestructuraDao
                .getTipusInfraestructuraById(infra.getTipusInfraestructura().getIdTipus());
        Estat estat = estatDao.getEstatById(infra.getEstat().getIdEstat());
        Unitat unitat = unitatDao.getUnitatById(infra.getUnitat().getIdUnitat());
        Edifici edifici = edificiDao.getEdificiById(infra.getEdifici().getIdEdifici());
        Estat estatValidacio = estatDao.getEstatById(infra.getEstatValidacio().getIdEstat());
        Unitat unitatGestora = unitatDao.getUnitatById(infra.getUnitatGestora().getIdUnitat());
        Unitat unitatDestinataria = (infra.getUnitatDestinataria() != null)
                ? unitatDao.getUnitatById(infra.getUnitatDestinataria().getIdUnitat()) : null;
        SistemaOperatiu sistemaOperatiu = (infra.getSistemaOperatiu() != null)
                ? sistemaOperatiuDao.getSistemaOperatiuById(infra.getSistemaOperatiu().getIdSistemaOperatiu()) : null;
        UsuariInfraestructura usuariInfraestructura = (infra.getUsuariInfraestructura() != null)
                ? usuariInfraestructuraDao
                        .getUsuariInfraestructura(infra.getUsuariInfraestructura().getIdUsuariInfraestructura())
                : null;

        infra.setMarca(marca);
        infra.setTipusInfraestructura(tipusInfraestructura);
        infra.setEstat(estat);
        infra.setUnitat(unitat);
        infra.setEdifici(edifici);
        infra.setEstatValidacio(estatValidacio);
        infra.setUnitatGestora(unitatGestora);
        infra.setUnitatDestinataria(unitatDestinataria);
        infra.setSistemaOperatiu(sistemaOperatiu);
        infra.setUsuariInfraestructura(usuariInfraestructura);
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

    @Override
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
}
