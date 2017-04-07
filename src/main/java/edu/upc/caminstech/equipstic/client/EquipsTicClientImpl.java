package edu.upc.caminstech.equipstic.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

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
 * Implementació bàsica d'un client de la API EquipsTIC.
 * <p>
 * La manera d'instanciar un client depén de si feu servir
 * <a href="https://projects.spring.io/spring-framework/">Spring Framework</a>
 * en la vostra aplicació o no.
 * <h2>Si no feu servir Spring Framework</h2>
 * <p>
 * Exemple d'utilització si no feu servir l'Spring Framework:
 * 
 * <pre>
 * EquipsTicClientConfiguration config = new EquipsTicClientConfiguration(baseUri, username, password);
 * EquipsTicClient client = new EquipsTicClientImpl(config);
 * 
 * List&lt;Campus&gt; campus = client.getCampus();
 * Unitat unitat = client.getUnitatByIdentificador("UTGAC");
 * String nom = unitat.getNom();
 * ...
 * </pre>
 * 
 * <h2>Si feu servir Spring Framework</h2> La llibreria
 * {@code equipstic-client-java} és compatible amb Spring, de tal forma que, si
 * definiu un <em>Spring Bean</em> de tipus {@link EquipsTicClientConfiguration}
 * en la vostra aplicació, Spring ho detectarà i automàticament disposareu d'un
 * {@link EquipsTicClient} que podeu injectar en la vostra aplicació. A més, si
 * teniu configurada la caché amb Spring (per exemple amb
 * l'anotació @{@link EnableCaching}), el client la farà servir.
 * <p>
 * Configuració via Spring Framework (exemple):
 * 
 * <pre>
 * &#64;Configuration
 * &#64;EnableCaching
 * &#64;ComponentScan(basePackageClasses = { EquipsTicClient.class })
 * public class LaMevaConfiguracio {
 * 
 *     &#64;Bean
 *     EquipsTicClientConfiguration getClientConfig( //
 *             &#64;Value("${equipstic.soa.url}") String baseUri, //
 *             &#64;Value("${equipstic.soa.username}") String username, //
 *             &#64;Value("${equipstic.soa.password}") String password) throws URISyntaxException {
 * 
 *         return new EquipsTicClientConfiguration(baseUri, username, password);
 *     }
 * }
 * </pre>
 * 
 * Noteu que cal incloure l'anotació @{@link ComponentScan} per tal que Spring
 * autodetecti els components de la llibreria equipstic-client-java necessaris
 * per configurar el Bean del client.
 * <p>
 * Després, a la vostra aplicació (exemple):
 * 
 * <pre>
 * 
 * &#64;Service
 * public class ElMeuServei {
 * 
 *     &#64;Autowired
 *     private EquipsTicClient client;
 * 
 *     public void elMeuMetode() {
 *         List&lt;Edifici&gt; edificis = client.getEdificis();
 *         ...
 *     }
 * }
 * </pre>
 * 
 * @see EquipsTicClient
 * @see EquipsTicClientConfiguration
 */
@Component
public class EquipsTicClientImpl implements EquipsTicClient {

    @Autowired
    private AmbitDao ambitDao;

    @Autowired
    private CampusDao campusDao;

    @Autowired
    private CategoriaDao categoriaDao;

    @Autowired
    private EdificiDao edificiDao;

    @Autowired
    private EstatDao estatDao;

    @Autowired
    private InfraestructuraDao infraestructuraDao;

    @Autowired
    private MarcaDao marcaDao;

    @Autowired
    private SistemaOperatiuDao sistemaOperatiuDao;

    @Autowired
    private TipusInfraestructuraDao tipusInfraestructuraDao;

    @Autowired
    private TipusUsDao tipusUsDao;

    @Autowired
    private TipusXarxaDao tipusXarxaDao;

    @Autowired
    private UnitatDao unitatDao;

    @Autowired
    private UsuariInfraestructuraDao usuariInfraestructuraDao;

    public EquipsTicClientImpl() {
    }

    /**
     * Constructor a partir d'una configuració.
     * 
     * @param config
     *            la configuració del client.
     * @see EquipsTicClientConfiguration
     */
    public EquipsTicClientImpl(EquipsTicClientConfiguration config) {
        ambitDao = new AmbitDaoImpl(config);
        campusDao = new CampusDaoImpl(config);
        categoriaDao = new CategoriaDaoImpl(config);
        edificiDao = new EdificiDaoImpl(config);
        estatDao = new EstatDaoImpl(config);
        marcaDao = new MarcaDaoImpl(config);
        sistemaOperatiuDao = new SistemaOperatiuDaoImpl(config);
        tipusInfraestructuraDao = new TipusInfraestructuraDaoImpl(config);
        tipusUsDao = new TipusUsDaoImpl(config);
        tipusXarxaDao = new TipusXarxaDaoImpl(config);
        unitatDao = new UnitatDaoImpl(config);
        usuariInfraestructuraDao = new UsuariInfraestructuraDaoImpl(config);

        infraestructuraDao = new InfraestructuraDaoImpl(config, edificiDao, estatDao, marcaDao, sistemaOperatiuDao,
                tipusInfraestructuraDao, unitatDao, usuariInfraestructuraDao);
    }

    @Autowired
    public void setAmbitDao(AmbitDao ambitDao) {
        this.ambitDao = ambitDao;
    }

    @Autowired
    public void setCampusDao(CampusDao campusDao) {
        this.campusDao = campusDao;
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
    public List<Unitat> getUnitatsByIdentificador(String identificador) {
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
