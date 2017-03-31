package edu.upc.caminstech.equipstic.client;

import java.util.List;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

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

/**
 * Implementació del client amb una <em>caché</em> gestionada per Spring
 * Framework.
 * <p>
 * Aquest client guarda els resultats del servidor en una caché, i en les crides
 * següents retorna el resultat cachejat, si és possible, evitant crides al
 * servidor. En qualsevol moment es pot forçar un <em>refresc</em> de la caché
 * fent servir el mètode {@link #refrescaCache()}.
 * <p>
 * Noteu que les crides que fan modificacions (alta, baixa i modificació
 * d'infraestructura) no estàn cachejades.
 * <p>
 * <strong>Nota sobre la implementació</strong>
 * <p>
 * Les crides al servidor es resolen per delegació a un client prèviament
 * instanciat i configurat, que es passa com a paràmetre al constructor.
 * <p>
 * Aquesta implementació fa servir un
 * {@link org.springframework.cache.CacheManager} de Spring Framework que s'ha
 * de passar com a paràmetre al constructor. Vegeu <a href=
 * "http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-caching">la
 * documentació de Spring</a> per veure com configurar un
 * {@link org.springframework.cache.CacheManager} a la vostra aplicació.
 * <p>
 * Per tal que funcioni, primer haureu d'activar el suport de caché via
 * anotacions a la vostra aplicació, amb l'anotació
 * {@link org.springframework.cache.annotation.EnableCaching}, i després
 * instanciar el client com un {@code Bean} per poder injectar-hi el
 * {@code CacheManager}:
 * <p>
 * 
 * <pre>
 * &#64;Configuration
 * &#64;EnableCaching
 * public class AppConfig {
 * 
 *     &#64;Autowired
 *     CacheManager cacheManager;
 * 
 *     &#64;Bean
 *     EquipsTicClientSpringCachedImpl getClient() {
 *         EquipsTicClient client = new EquipsTicClient(apiUrl, soaUser, soaPass);
 *         return new EquipsTicClientSpringCachedImpl(client, cacheManager);
 *     }
 * }
 * </pre>
 * 
 * <p>
 * <strong>NOTA:</strong>En cas que no feu servir Spring Framework, podeu fer
 * servir aquesta classe com a exemple per veure com implementar la vostra
 * propia versió de client «amb caché», implementant el <em>backend</em> de
 * caché que desitjeu.
 *
 * @see EquipsTicClient
 * @see EquipsTicClientImpl
 */
public class EquipsTicClientSpringCachedImpl implements EquipsTicClient {

    /*
     * Com que el CacheManager pot gestionar altres cachés alienes al client,
     * s'aplica un prefix propi als noms de les cachés, per evitar col·lisions
     * amb altres cachés alienes al client. Això és una particularitat dels
     * CacheManager de Spring.
     */
    private final String cachePrefix = "cached-equipstic-client-";

    private final CacheManager cacheManager;

    private final EquipsTicClient client;

    /**
     * Constructor del client amb caché.
     * 
     * @param client
     *            un client, prèviament instanciat, que volem "cachejar" (les
     *            crides al servidor es resoldran per delegació a aquest client)
     * @param cacheManager
     *            el gestor de cachés que ha de fer servir el client
     */
    public EquipsTicClientSpringCachedImpl(EquipsTicClient client, CacheManager cacheManager) {
        this.client = client;
        this.cacheManager = cacheManager;
    }

    /**
     * Força el buidat de la caché del client.
     */
    public void refrescaCache() {
        cacheManager.getCacheNames() //
                .parallelStream() //
                .filter(name -> name.startsWith(cachePrefix)) //
                .forEach(name -> cacheManager.getCache(name).clear());
    }

    /** {@inheritDoc} */
    @Override
    @Cacheable(value = cachePrefix + "ambits", sync = true)
    public List<Ambit> getAmbits() {
        return client.getAmbits();
    }

    /** {@inheritDoc} */
    @Override
    @Cacheable(value = cachePrefix + "ambitsByNom", sync = true)
    public List<Ambit> getAmbitsByNom(String nomAmbit) {
        return client.getAmbitsByNom(nomAmbit);
    }

    /** {@inheritDoc} */
    @Override
    @Cacheable(value = cachePrefix + "ambitsById", sync = true)
    public Ambit getAmbitById(long idAmbit) {
        return client.getAmbitById(idAmbit);
    }

    /** {@inheritDoc} */
    @Override
    @Cacheable(value = cachePrefix + "campus", sync = true)
    public List<Campus> getCampus() {
        return client.getCampus();
    }

    /** {@inheritDoc} */
    @Override
    @Cacheable(value = cachePrefix + "campusByCodi", sync = true)
    public Campus getCampusByCodi(String codiCampus) {
        return client.getCampusByCodi(codiCampus);
    }

    /** {@inheritDoc} */
    @Override
    @Cacheable(value = cachePrefix + "campusById", sync = true)
    public Campus getCampusById(long idCampus) {
        return client.getCampusById(idCampus);
    }

    /** {@inheritDoc} */
    @Override
    @Cacheable(value = cachePrefix + "categories", sync = true)
    public List<Categoria> getCategories() {
        return client.getCategories();
    }

    /** {@inheritDoc} */
    @Override
    @Cacheable(value = cachePrefix + "categoriaById", sync = true)
    public Categoria getCategoriaById(long idCategoria) {
        return client.getCategoriaById(idCategoria);
    }

    /** {@inheritDoc} */
    @Override
    @Cacheable(value = cachePrefix + "edificis", sync = true)
    public List<Edifici> getEdificis() {
        return client.getEdificis();
    }

    /** {@inheritDoc} */
    @Override
    @Cacheable(value = cachePrefix + "edificiById", sync = true)
    public Edifici getEdificiById(long idEdifici) {
        return client.getEdificiById(idEdifici);
    }

    /** {@inheritDoc} */
    @Override
    @Cacheable(value = cachePrefix + "edificiByCodiAndCodiCampus", sync = true)
    public Edifici getEdificiByCodiAndCodiCampus(String codiEdifici, String codiCampus) {
        return client.getEdificiByCodiAndCodiCampus(codiEdifici, codiCampus);
    }

    /** {@inheritDoc} */
    @Override
    @Cacheable(value = cachePrefix + "estats", sync = true)
    public List<Estat> getEstats() {
        return client.getEstats();
    }

    /** {@inheritDoc} */
    @Override
    @Cacheable(value = cachePrefix + "estatByCodi", sync = true)
    public Estat getEstatByCodi(String codiEstat) {
        return client.getEstatByCodi(codiEstat);
    }

    /** {@inheritDoc} */
    @Override
    @Cacheable(value = cachePrefix + "estatsByNom", sync = true)
    public List<Estat> getEstatsByNom(String nomEstat) {
        return client.getEstatsByNom(nomEstat);
    }

    /** {@inheritDoc} */
    @Override
    @Cacheable(value = cachePrefix + "estatById", sync = true)
    public Estat getEstatById(long idEstat) {
        return client.getEstatById(idEstat);
    }

    /** {@inheritDoc} */
    @Override
    @Cacheable(value = cachePrefix + "marques", sync = true)
    public List<Marca> getMarques() {
        return client.getMarques();
    }

    /** {@inheritDoc} */
    @Override
    @Cacheable(value = cachePrefix + "marquesByNom", sync = true)
    public List<Marca> getMarquesByNom(String nom) {
        return client.getMarquesByNom(nom);
    }

    /** {@inheritDoc} */
    @Override
    @Cacheable(value = cachePrefix + "marcaById", sync = true)
    public Marca getMarcaById(long idMarca) {
        return client.getMarcaById(idMarca);
    }

    /** {@inheritDoc} */
    @Override
    @Cacheable(value = cachePrefix + "tipusUs", sync = true)
    public List<TipusUs> getTipusUs() {
        return client.getTipusUs();
    }

    /** {@inheritDoc} */
    @Override
    @Cacheable(value = cachePrefix + "tipusUsByUnitat", sync = true)
    public List<TipusUs> getTipusUsByUnitat(long idUnitat) {
        return client.getTipusUsByUnitat(idUnitat);
    }

    /** {@inheritDoc} */
    @Override
    @Cacheable(value = cachePrefix + "tipusUs", sync = true)
    public TipusUs getTipusUsById(long idTipusUs) {
        return client.getTipusUsById(idTipusUs);
    }

    /** {@inheritDoc} */
    @Override
    @Cacheable(value = cachePrefix + "tipusInfraestructura", sync = true)
    public List<TipusInfraestructura> getTipusInfraestructura() {
        return client.getTipusInfraestructura();
    }

    /** {@inheritDoc} */
    @Override
    @Cacheable(value = cachePrefix + "tipusInfraestructuraByCategoria", sync = true)
    public List<TipusInfraestructura> getTipusInfraestructuraByCategoria(long idCategoria) {
        return client.getTipusInfraestructuraByCategoria(idCategoria);
    }

    /** {@inheritDoc} */
    @Override
    @Cacheable(value = cachePrefix + "tipusInfraestructuraByCodi", sync = true)
    public TipusInfraestructura getTipusInfraestructuraBycodi(String codi) {
        return client.getTipusInfraestructuraBycodi(codi);
    }

    /** {@inheritDoc} */
    @Override
    @Cacheable(value = cachePrefix + "tipusInfraestructuraByNom", sync = true)
    public List<TipusInfraestructura> getTipusInfraestructuraByNom(String nom) {
        return client.getTipusInfraestructuraByNom(nom);
    }

    /** {@inheritDoc} */
    @Override
    @Cacheable(value = cachePrefix + "tipusInfraestructuraById", sync = true)
    public TipusInfraestructura getTipusInfraestructuraById(long idTipus) {
        return client.getTipusInfraestructuraById(idTipus);
    }

    /** {@inheritDoc} */
    @Override
    @Cacheable(value = cachePrefix + "tipusXarxa", sync = true)
    public List<TipusXarxa> getTipusXarxa() {
        return client.getTipusXarxa();
    }

    /** {@inheritDoc} */
    @Override
    @Cacheable(value = cachePrefix + "tipusXarxaByid", sync = true)
    public TipusXarxa getTipusXarxaById(long idTipusXarxa) {
        return client.getTipusXarxaById(idTipusXarxa);
    }

    /** {@inheritDoc} */
    @Override
    @Cacheable(value = cachePrefix + "unitats", sync = true)
    public List<Unitat> getUnitats() {
        return client.getUnitats();
    }

    /** {@inheritDoc} */
    @Override
    @Cacheable(value = cachePrefix + "unitatByIdentificador", sync = true)
    public Unitat getUnitatByIdentificador(String identificador) {
        return client.getUnitatByIdentificador(identificador);
    }

    /** {@inheritDoc} */
    @Override
    @Cacheable(value = cachePrefix + "unitatsByNom", sync = true)
    public List<Unitat> getUnitatsByNom(String nom) {
        return client.getUnitatsByNom(nom);
    }

    /** {@inheritDoc} */
    @Override
    @Cacheable(value = cachePrefix + "unitatsByNomAndIdentificadorAndCodi", sync = true)
    public List<Unitat> getUnitatsByNomAndIdentificadorAndCodi(String nom, String identificador, String codiUnitat) {
        return client.getUnitatsByNomAndIdentificadorAndCodi(nom, identificador, codiUnitat);
    }

    /** {@inheritDoc} */
    @Override
    @Cacheable(value = cachePrefix + "unitatsById", sync = true)
    public Unitat getUnitatById(long idUnitat) {
        return client.getUnitatById(idUnitat);
    }

    /** {@inheritDoc} */
    @Override
    @Cacheable(value = cachePrefix + "infraestructuraByMarcaAndNumeroDeSerie", sync = true)
    public Infraestructura getInfraestructuraByMarcaAndNumeroDeSerie(long idMarca, String sn) {
        return client.getInfraestructuraByMarcaAndNumeroDeSerie(idMarca, sn);
    }

    /** {@inheritDoc} */
    @Override
    @Cacheable(value = cachePrefix + "infraestructuraById", sync = true)
    public Infraestructura getInfraestructuraById(long id) {
        return client.getInfraestructuraById(id);
    }

    /** {@inheritDoc} */
    @Override
    @Cacheable(value = cachePrefix + "infraestructuresByUnitat", sync = true)
    public List<Infraestructura> getInfraestructuresByUnitat(long idUnitat) {
        return client.getInfraestructuresByUnitat(idUnitat);
    }

    /**
     * {@inheritDoc}
     *
     * Aquesta crida no està cachejada.
     * 
     * @see EquipsTicClient#altaInfraestructura(Infraestructura)
     */
    @Override
    @CacheEvict(cacheNames = { cachePrefix + "infraestructuraByMarcaAndNumeroDeSerie",
            cachePrefix + "infraestructuraById", cachePrefix + "infraestructuresByUnitat" })
    public Infraestructura altaInfraestructura(Infraestructura infraestructura) {
        return client.altaInfraestructura(infraestructura);
    }

    /**
     * {@inheritDoc}
     *
     * Aquesta crida no està cachejada.
     * 
     * @see EquipsTicClient#baixaInfraestructura(long)
     */
    @Override
    @CacheEvict(cacheNames = { cachePrefix + "infraestructuraByMarcaAndNumeroDeSerie",
            cachePrefix + "infraestructuraById", cachePrefix + "infraestructuresByUnitat" })
    public void baixaInfraestructura(long id) {
        client.baixaInfraestructura(id);
    }

    /**
     * {@inheritDoc}
     *
     * Aquesta crida no està cachejada.
     * 
     * @see EquipsTicClient#modificaInfraestructura(Infraestructura)
     */
    @Override
    @CacheEvict(cacheNames = { cachePrefix + "infraestructuraByMarcaAndNumeroDeSerie",
            cachePrefix + "infraestructuraById", cachePrefix + "infraestructuresByUnitat" })
    public Infraestructura modificaInfraestructura(Infraestructura infraestructura) {
        return client.modificaInfraestructura(infraestructura);
    }

    /** {@inheritDoc} */
    @Override
    @Cacheable(value = cachePrefix + "sistemesOperatius", sync = true)
    public List<SistemaOperatiu> getSistemesOperatius() {
        return client.getSistemesOperatius();
    }

    /** {@inheritDoc} */
    @Override
    @Cacheable(value = cachePrefix + "sistemesOperatiusByCategoria", sync = true)
    public List<SistemaOperatiu> getSistemesOperatiusByCategoria(long idCategoria) {
        return client.getSistemesOperatiusByCategoria(idCategoria);
    }

    /** {@inheritDoc} */
    @Override
    @Cacheable(value = cachePrefix + "sistemesOperatiusByCodi", sync = true)
    public List<SistemaOperatiu> getSistemesOperatiusByCodi(String codi) {
        return client.getSistemesOperatiusByCodi(codi);
    }

    /** {@inheritDoc} */
    @Override
    @Cacheable(value = cachePrefix + "sistemesOperatiusByNom", sync = true)
    public List<SistemaOperatiu> getSistemesOperatiusByNom(String nom) {
        return client.getSistemesOperatiusByNom(nom);
    }

    /** {@inheritDoc} */
    @Override
    @Cacheable(value = cachePrefix + "sistemaOperatiuById", sync = true)
    public SistemaOperatiu getSistemaOperatiuById(long idSistemaOperatiu) {
        return client.getSistemaOperatiuById(idSistemaOperatiu);
    }

    /** {@inheritDoc} */
    @Override
    @Cacheable(value = cachePrefix + "usuariInfraestructuraById", sync = true)
    public UsuariInfraestructura getUsuariInfraestructura(long idUsuariInfraestructura) {
        return client.getUsuariInfraestructura(idUsuariInfraestructura);
    }

    /** {@inheritDoc} */
    @Override
    @Cacheable(value = cachePrefix + "usuarisInfraestructura", sync = true)
    public List<UsuariInfraestructura> getUsuarisInfraestructura() {
        return client.getUsuarisInfraestructura();
    }

    /** {@inheritDoc} */
    @Override
    @Cacheable(value = cachePrefix + "usuarisInfraestructuraByNom", sync = true)
    public List<UsuariInfraestructura> getUsuarisInfraestructuraByNom(String nom) {
        return client.getUsuarisInfraestructuraByNom(nom);
    }

}
