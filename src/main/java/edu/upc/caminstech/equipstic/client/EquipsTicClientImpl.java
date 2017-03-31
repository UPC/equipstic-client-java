package edu.upc.caminstech.equipstic.client;

import java.net.URI;
import java.util.List;
import java.util.TimeZone;

import org.springframework.web.client.RestTemplate;

import edu.upc.caminstech.equipstic.Ambit;
import edu.upc.caminstech.equipstic.Campus;
import edu.upc.caminstech.equipstic.Categoria;
import edu.upc.caminstech.equipstic.Edifici;
import edu.upc.caminstech.equipstic.Estat;
import edu.upc.caminstech.equipstic.Infraestructura;
import edu.upc.caminstech.equipstic.Marca;
import edu.upc.caminstech.equipstic.SistemaOperatiu;
import edu.upc.caminstech.equipstic.TipusInfraestructura;
import edu.upc.caminstech.equipstic.TipusUs;
import edu.upc.caminstech.equipstic.TipusXarxa;
import edu.upc.caminstech.equipstic.Unitat;
import edu.upc.caminstech.equipstic.UsuariInfraestructura;
import edu.upc.caminstech.equipstic.client.dao.AmbitDao;
import edu.upc.caminstech.equipstic.client.dao.AmbitDaoImpl;
import edu.upc.caminstech.equipstic.client.dao.CampusDao;
import edu.upc.caminstech.equipstic.client.dao.CampusDaoImpl;
import edu.upc.caminstech.equipstic.client.dao.CategoriaDao;
import edu.upc.caminstech.equipstic.client.dao.CategoriaDaoImpl;
import edu.upc.caminstech.equipstic.client.dao.EdificiDao;
import edu.upc.caminstech.equipstic.client.dao.EdificiDaoImpl;
import edu.upc.caminstech.equipstic.client.dao.EstatDao;
import edu.upc.caminstech.equipstic.client.dao.EstatDaoImpl;
import edu.upc.caminstech.equipstic.client.dao.InfraestructuraDao;
import edu.upc.caminstech.equipstic.client.dao.InfraestructuraDaoImpl;
import edu.upc.caminstech.equipstic.client.dao.MarcaDao;
import edu.upc.caminstech.equipstic.client.dao.MarcaDaoImpl;
import edu.upc.caminstech.equipstic.client.dao.SistemaOperatiuDao;
import edu.upc.caminstech.equipstic.client.dao.SistemaOperatiuDaoImpl;
import edu.upc.caminstech.equipstic.client.dao.TipusInfraestructuraDao;
import edu.upc.caminstech.equipstic.client.dao.TipusInfraestructuraDaoImpl;
import edu.upc.caminstech.equipstic.client.dao.TipusUsDao;
import edu.upc.caminstech.equipstic.client.dao.TipusUsDaoImpl;
import edu.upc.caminstech.equipstic.client.dao.TipusXarxaDao;
import edu.upc.caminstech.equipstic.client.dao.TipusXarxaDaoImpl;
import edu.upc.caminstech.equipstic.client.dao.UnitatDao;
import edu.upc.caminstech.equipstic.client.dao.UnitatDaoImpl;
import edu.upc.caminstech.equipstic.client.dao.UsuariInfraestructuraDao;
import edu.upc.caminstech.equipstic.client.dao.UsuariInfraestructuraDaoImpl;

/**
 * Una implementació per defecte del client.
 * <p>
 * Molt probablement preferiu la versió amb <em>caché</em>
 * {@link EquipsTicClientSpringCachedImpl}.
 * <p>
 * Exemple d'utilització:
 * <p>
 * <code>
 * URI uri = URI.create("https://example.com/paht_to_api"); //veure manual SOA <br>
 * EquipsTicClientImpl client = new EquipsTicClientImpl(uri, "soa_user", "soa_password")); <br>
 * List&lt;Campus&gt; campus = client.getCampus();
 * </code>
 * 
 * @see EquipsTicClient
 */
public class EquipsTicClientImpl implements EquipsTicClient {

    private final AmbitDao ambitDao;
    private final CampusDao campusDao;
    private final CategoriaDao categoriaDao;
    private final EdificiDao edificiDao;
    private final EstatDao estatDao;
    private final InfraestructuraDao infraestructuraDao;
    private final MarcaDao marcaDao;
    private final SistemaOperatiuDao sistemaOperatiuDao;
    private final TipusInfraestructuraDao tipusInfraestructuraDao;
    private final TipusUsDao tipusUsDao;
    private final TipusXarxaDao tipusXarxaDao;
    private final UnitatDao unitatDao;
    private final UsuariInfraestructuraDao usuariInfraestructuraDao;

    /**
     * El TimeZone que fa servir el servidor d'EquipsTIC.
     */
    private static final TimeZone EQUIPSTIC_SERVER_TIMEZONE = TimeZone.getTimeZone("Europe/Madrid");

    /**
     * Crea una nova instància del client.
     * <p>
     * El client retornat codifica/descodifica les dates fent servir el TimeZone
     * que utilitza el servidor EquipsTIC de la UPC. Si voleu configurar un
     * altre {@code TimeZone}, feu servir el
     * {@link #EquipsTicClientImpl(URI, String, String, TimeZone) constructor
     * alternatiu}.
     *
     * @param baseUri
     *            la URL de la API, tal com indica la documentació del bus SOA.
     *            Es pot fer servir tant la URL de desenvolupament com la de
     *            producció.
     * @param username
     *            el nostre usuari al bus SOA (ha de tenir accés a la API).
     * @param password
     *            la nostra contrasenya al bus SOA.
     */
    public EquipsTicClientImpl(URI baseUri, String username, String password) {
        this(baseUri, username, password, EQUIPSTIC_SERVER_TIMEZONE);
    }

    /**
     * Crea una nova instància del client.
     *
     * @param baseUri
     *            la URL de la API, tal com indica la documentació del bus SOA.
     *            Es pot fer servir tant la URL de desenvolupament com la de
     *            producció.
     * @param username
     *            el nostre usuari al bus SOA (ha de tenir accés a la API).
     * @param password
     *            la nostra contrasenya al bus SOA.
     * @param timeZone
     *            el {@code TimeZone} que fa servir el servidor d'EquipsTIC.
     */
    public EquipsTicClientImpl(URI baseUri, String username, String password, TimeZone timeZone) {
        RestTemplate restTemplate = EquipsTicRestTemplateBuilder.createRestTemplate(baseUri, username, password,
                timeZone);

        ambitDao = new AmbitDaoImpl(baseUri, restTemplate);
        campusDao = new CampusDaoImpl(baseUri, restTemplate);
        categoriaDao = new CategoriaDaoImpl(baseUri, restTemplate);
        edificiDao = new EdificiDaoImpl(baseUri, restTemplate);
        estatDao = new EstatDaoImpl(baseUri, restTemplate);
        marcaDao = new MarcaDaoImpl(baseUri, restTemplate);
        sistemaOperatiuDao = new SistemaOperatiuDaoImpl(baseUri, restTemplate);
        tipusInfraestructuraDao = new TipusInfraestructuraDaoImpl(baseUri, restTemplate);
        tipusUsDao = new TipusUsDaoImpl(baseUri, restTemplate);
        tipusXarxaDao = new TipusXarxaDaoImpl(baseUri, restTemplate);
        unitatDao = new UnitatDaoImpl(baseUri, restTemplate);
        usuariInfraestructuraDao = new UsuariInfraestructuraDaoImpl(baseUri, restTemplate);

        infraestructuraDao = new InfraestructuraDaoImpl(baseUri, restTemplate, edificiDao, estatDao, marcaDao,
                sistemaOperatiuDao, tipusInfraestructuraDao, unitatDao, usuariInfraestructuraDao);
    }

    @Override
    public List<Ambit> getAmbits() {
        return ambitDao.getAmbits();
    }

    @Override
    public List<Ambit> getAmbitsByNom(String nomAmbit) {
        return ambitDao.getAmbitsByNom(nomAmbit);
    }

    @Override
    public Ambit getAmbitById(long idAmbit) {
        return ambitDao.getAmbitById(idAmbit);
    }

    @Override
    public List<Campus> getCampus() {
        return campusDao.getCampus();
    }

    @Override
    public Campus getCampusByCodi(String codiCampus) {
        return campusDao.getCampusByCodi(codiCampus);
    }

    @Override
    public Campus getCampusById(long idCampus) {
        return campusDao.getCampusById(idCampus);
    }

    @Override
    public List<Categoria> getCategories() {
        return categoriaDao.getCategories();
    }

    @Override
    public Categoria getCategoriaById(long idCategoria) {
        return categoriaDao.getCategoriaById(idCategoria);
    }

    @Override
    public List<Edifici> getEdificis() {
        return edificiDao.getEdificis();
    }

    @Override
    public Edifici getEdificiById(long idEdifici) {
        return edificiDao.getEdificiById(idEdifici);
    }

    @Override
    public Edifici getEdificiByCodiAndCodiCampus(String codiEdifici, String codiCampus) {
        return edificiDao.getEdificiByCodiAndCodiCampus(codiEdifici, codiCampus);
    }

    @Override
    public List<Estat> getEstats() {
        return estatDao.getEstats();
    }

    @Override
    public Estat getEstatByCodi(String codiEstat) {
        return estatDao.getEstatByCodi(codiEstat);

    }

    @Override
    public List<Estat> getEstatsByNom(String nomEstat) {
        return estatDao.getEstatsByNom(nomEstat);
    }

    @Override
    public Estat getEstatById(long idEstat) {
        return estatDao.getEstatById(idEstat);
    }

    @Override
    public List<Marca> getMarques() {
        return marcaDao.getMarques();
    }

    @Override
    public List<Marca> getMarquesByNom(String nom) {
        return marcaDao.getMarquesByNom(nom);
    }

    @Override
    public Marca getMarcaById(long idMarca) {
        return marcaDao.getMarcaById(idMarca);
    }

    @Override
    public List<TipusUs> getTipusUs() {
        return tipusUsDao.getTipusUs();
    }

    @Override
    public List<TipusUs> getTipusUsByUnitat(long idUnitat) {
        return tipusUsDao.getTipusUsByUnitat(idUnitat);
    }

    @Override
    public TipusUs getTipusUsById(long idTipusUs) {
        return tipusUsDao.getTipusUsById(idTipusUs);
    }

    @Override
    public List<TipusInfraestructura> getTipusInfraestructura() {
        return tipusInfraestructuraDao.getTipusInfraestructura();
    }

    @Override
    public List<TipusInfraestructura> getTipusInfraestructuraByCategoria(long idCategoria) {
        return tipusInfraestructuraDao.getTipusInfraestructuraByCategoria(idCategoria);
    }

    @Override
    public TipusInfraestructura getTipusInfraestructuraBycodi(String codi) {
        return tipusInfraestructuraDao.getTipusInfraestructuraBycodi(codi);
    }

    @Override
    public List<TipusInfraestructura> getTipusInfraestructuraByNom(String nom) {
        return tipusInfraestructuraDao.getTipusInfraestructuraByNom(nom);
    }

    @Override
    public TipusInfraestructura getTipusInfraestructuraById(long idTipus) {
        return tipusInfraestructuraDao.getTipusInfraestructuraById(idTipus);
    }

    @Override
    public List<TipusXarxa> getTipusXarxa() {
        return tipusXarxaDao.getTipusXarxa();
    }

    @Override
    public TipusXarxa getTipusXarxaById(long idTipusXarxa) {
        return tipusXarxaDao.getTipusXarxaById(idTipusXarxa);
    }

    @Override
    public List<Unitat> getUnitats() {
        return unitatDao.getUnitats();
    }

    @Override
    public Unitat getUnitatByIdentificador(String identificador) {
        return unitatDao.getUnitatByIdentificador(identificador);
    }

    @Override
    public List<Unitat> getUnitatsByNom(String nom) {
        return unitatDao.getUnitatsByNom(nom);
    }

    @Override
    public List<Unitat> getUnitatsByNomAndIdentificadorAndCodi(String nom, String identificador, String codiUnitat) {
        return unitatDao.getUnitatsByNomAndIdentificadorAndCodi(nom, identificador, codiUnitat);
    }

    @Override
    public Unitat getUnitatById(long idUnitat) {
        return unitatDao.getUnitatById(idUnitat);
    }

    @Override
    public UsuariInfraestructura getUsuariInfraestructura(long idUsuariInfraestructura) {
        return usuariInfraestructuraDao.getUsuariInfraestructura(idUsuariInfraestructura);
    }

    @Override
    public List<UsuariInfraestructura> getUsuarisInfraestructura() {
        return usuariInfraestructuraDao.getUsuarisInfraestructura();
    }

    @Override
    public List<UsuariInfraestructura> getUsuarisInfraestructuraByNom(String nom) {
        return usuariInfraestructuraDao.getUsuarisInfraestructuraByNom(nom);
    }

    @Override
    public Infraestructura getInfraestructuraByMarcaAndNumeroDeSerie(long idMarca, String sn) {
        return infraestructuraDao.getInfraestructuraByMarcaAndNumeroDeSerie(idMarca, sn);
    }

    @Override
    public Infraestructura getInfraestructuraById(long id) {
        return infraestructuraDao.getInfraestructuraById(id);
    }

    @Override
    public List<Infraestructura> getInfraestructuresByUnitat(long idUnitat) {
        return infraestructuraDao.getInfraestructuresByUnitat(idUnitat);
    }

    @Override
    public Infraestructura altaInfraestructura(Infraestructura infraestructura) {
        return infraestructuraDao.altaInfraestructura(infraestructura);
    }

    @Override
    public void baixaInfraestructura(long id) {
        infraestructuraDao.baixaInfraestructura(id);
    }

    @Override
    public Infraestructura modificaInfraestructura(Infraestructura infraestructura) {
        return infraestructuraDao.modificaInfraestructura(infraestructura);
    }

    @Override
    public List<SistemaOperatiu> getSistemesOperatius() {
        return sistemaOperatiuDao.getSistemesOperatius();
    }

    @Override
    public List<SistemaOperatiu> getSistemesOperatiusByCategoria(long idCategoria) {
        return sistemaOperatiuDao.getSistemesOperatiusByCategoria(idCategoria);
    }

    @Override
    public List<SistemaOperatiu> getSistemesOperatiusByCodi(String codi) {
        return sistemaOperatiuDao.getSistemesOperatiusByCodi(codi);
    }

    @Override
    public List<SistemaOperatiu> getSistemesOperatiusByNom(String nom) {
        return sistemaOperatiuDao.getSistemesOperatiusByNom(nom);
    }

    @Override
    public SistemaOperatiu getSistemaOperatiuById(long idSistemaOperatiu) {
        return sistemaOperatiuDao.getSistemaOperatiuById(idSistemaOperatiu);
    }

}
