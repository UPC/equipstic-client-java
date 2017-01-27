package edu.upc.caminstech.equipstic.client;

import java.net.URI;
import java.util.List;
import java.util.TimeZone;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;

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

/**
 * Implementació del client amb una <em>caché</em> gestionada per Spring
 * Framework.
 * <p>
 * Aquest client guarda els resultats del servidor en una caché, i en les crides
 * següents retorna el resultat cachejat, si és possible, evitant crides al
 * servidor. En qualsevol moment es pot forçar un <em>refresc</em> de la caché
 * fent servir el mètode {@link #refrescaCache()}.
 * <p>
 * <strong>Nota sobre la implementació</strong>
 * <p>
 * Aquesta implementació fa servir un {@link CacheManager} de Spring Framework
 * que s'ha de passar com a paràmetre al constructor. Vegeu <a href=
 * "http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-caching">la
 * documentació de Spring</a> per veure com configurar un {@link CacheManager} a
 * la vostra aplicació.
 * <p>
 * Per tal que funcioni, primer haureu d'activar el suport de caché via
 * anotacions a la vostra aplicació, amb l'anotació {@link EnableCaching}, i
 * després instanciar el client com un {@code Bean} per poder injectar-hi el
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
 *     CachedEquipsTicClient getClient() {
 *         return new CachedEquipsTicClient(apiUrl, soaUser, soaPass, cacheManager);
 *     }
 * }
 * </pre>
 * 
 * <p>
 * <strong>En cas que no feu servir Spring Framework, podeu fer servir aquesta
 * classe com a exemple per veure com implementar la vostra propia versió de
 * client «amb caché», fent servir el <em>backend</em> de caché que desitjeu.
 * 
 * @see EquipsTicClient
 */
public class CachedEquipsTicClient extends EquipsTicClient {

    /*
     * Com que el CacheManager pot gestionar altres cachés alienes al client,
     * s'aplica un prefix propi als noms de les cachés, per evitar col·lisions
     * amb altres cachés alienes al client. Això és una particularitat dels
     * CacheManager de Spring.
     */
    private final String cachePrefix = "cached-equipstic-client-";

    private final CacheManager cacheManager;

    /**
     * Constructor del client amb caché.
     * 
     * @param baseUri
     *            la URL de la API, tal com indica la documentació del bus SOA.
     *            Es pot fer servir tant la URL de desenvolupament com la de
     *            producció.
     * @param username
     *            el nostre usuari al bus SOA (ha de tenir accés a la API).
     * @param password
     *            la nostra contrasenya al bus SOA.
     * @param cacheManager
     *            el gestor de cachés que ha de fer servir el client.
     * @see EquipsTicClient#EquipsTicClient(URI, String, String)
     */
    public CachedEquipsTicClient(URI baseUri, String username, String password, CacheManager cacheManager) {
        super(baseUri, username, password);
        this.cacheManager = cacheManager;
    }

    /**
     * Constructor del client amb caché.
     * 
     * @param baseUri
     *            la URL de la API, tal com indica la documentació del bus SOA.
     *            Es pot fer servir tant la URL de desenvolupament com la de
     *            producció.
     * @param username
     *            el nostre usuari al bus SOA (ha de tenir accés a la API).
     * @param password
     *            la nostra contrasenya al bus SOA.
     * @param cacheManager
     *            el gestor de cachés que ha de fer servir el client.
     * @see EquipsTicClient#EquipsTicClient(URI, String, String)
     */
    public CachedEquipsTicClient(URI baseUri, String username, String password, TimeZone timeZone,
            CacheManager cacheManager) {
        super(baseUri, username, password, timeZone);
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

    @Override
    @Cacheable(value = cachePrefix + "ambits")
    public List<Ambit> getAmbits() {
        return super.getAmbits();
    }

    @Override
    @Cacheable(cachePrefix + "ambitsByNom")
    public List<Ambit> getAmbitsByNom(String nomAmbit) {
        return super.getAmbitsByNom(nomAmbit);
    }

    @Override
    @Cacheable(cachePrefix + "ambitsById")
    public Ambit getAmbitById(long idAmbit) {
        return super.getAmbitById(idAmbit);
    }

    @Override
    @Cacheable(cachePrefix + "campus")
    public List<Campus> getCampus() {
        return super.getCampus();
    }

    @Override
    @Cacheable(cachePrefix + "campusByCodi")
    public Campus getCampusByCodi(String codiCampus) {
        return super.getCampusByCodi(codiCampus);
    }

    @Override
    @Cacheable(cachePrefix + "campusById")
    public Campus getCampusById(long idCampus) {
        return super.getCampusById(idCampus);
    }

    @Override
    @Cacheable(cachePrefix + "categories")
    public List<Categoria> getCategories() {
        return super.getCategories();
    }

    @Override
    @Cacheable(cachePrefix + "categoriaById")
    public Categoria getCategoriaById(long idCategoria) {
        return super.getCategoriaById(idCategoria);
    }

    @Override
    @Cacheable(cachePrefix + "edificis")
    public List<Edifici> getEdificis() {
        return super.getEdificis();
    }

    @Override
    @Cacheable(cachePrefix + "edificiById")
    public Edifici getEdificiById(long idEdifici) {
        return super.getEdificiById(idEdifici);
    }

    @Override
    @Cacheable(cachePrefix + "edificiByCodiAndCodiCampus")
    public Edifici getEdificiByCodiAndCodiCampus(String codiEdifici, String codiCampus) {
        return super.getEdificiByCodiAndCodiCampus(codiEdifici, codiCampus);
    }

    @Override
    @Cacheable(cachePrefix + "estats")
    public List<Estat> getEstats() {
        return super.getEstats();
    }

    @Override
    @Cacheable(cachePrefix + "estatByCodi")
    public Estat getEstatByCodi(String codiEstat) {
        return super.getEstatByCodi(codiEstat);
    }

    @Override
    @Cacheable(cachePrefix + "estatsByNom")
    public List<Estat> getEstatsByNom(String nomEstat) {
        return super.getEstatsByNom(nomEstat);
    }

    @Override
    @Cacheable(cachePrefix + "estatById")
    public Estat getEstatById(long idEstat) {
        return super.getEstatById(idEstat);
    }

    @Override
    @Cacheable(cachePrefix + "marques")
    public List<Marca> getMarques() {
        return super.getMarques();
    }

    @Override
    @Cacheable(cachePrefix + "marquesByNom")
    public List<Marca> getMarquesByNom(String nom) {
        return super.getMarquesByNom(nom);
    }

    @Override
    @Cacheable(cachePrefix + "marcaById")
    public Marca getMarcaById(long idMarca) {
        return super.getMarcaById(idMarca);
    }

    @Override
    @Cacheable(cachePrefix + "tipusUs")
    public List<TipusUs> getTipusUs() {
        return super.getTipusUs();
    }

    @Override
    @Cacheable(cachePrefix + "tipusUsByUnitat")
    public List<TipusUs> getTipusUsByUnitat(long idUnitat) {
        return super.getTipusUsByUnitat(idUnitat);
    }

    @Override
    @Cacheable(cachePrefix + "tipusUs")
    public TipusUs getTipusUsById(long idTipusUs) {
        return super.getTipusUsById(idTipusUs);
    }

    @Override
    @Cacheable(cachePrefix + "tipusInfraestructura")
    public List<TipusInfraestructura> getTipusInfraestructura() {
        return super.getTipusInfraestructura();
    }

    @Override
    @Cacheable(cachePrefix + "tipusInfraestructuraByCategoria")
    public List<TipusInfraestructura> getTipusInfraestructuraByCategoria(long idCategoria) {
        return super.getTipusInfraestructuraByCategoria(idCategoria);
    }

    @Override
    @Cacheable(cachePrefix + "tipusInfraestructuraByCodi")
    public TipusInfraestructura getTipusInfraestructuraBycodi(String codi) {
        return super.getTipusInfraestructuraBycodi(codi);
    }

    @Override
    @Cacheable(cachePrefix + "tipusInfraestructuraByNom")
    public List<TipusInfraestructura> getTipusInfraestructuraByNom(String nom) {
        return super.getTipusInfraestructuraByNom(nom);
    }

    @Override
    @Cacheable(cachePrefix + "tipusInfraestructuraById")
    public TipusInfraestructura getTipusInfraestructuraById(long idTipus) {
        return super.getTipusInfraestructuraById(idTipus);
    }

    @Override
    @Cacheable(cachePrefix + "tipusXarxa")
    public List<TipusXarxa> getTipusXarxa() {
        return super.getTipusXarxa();
    }

    @Override
    @Cacheable(cachePrefix + "tipusXarxaByid")
    public TipusXarxa getTipusXarxaById(long idTipusXarxa) {
        return super.getTipusXarxaById(idTipusXarxa);
    }

    @Override
    @Cacheable(cachePrefix + "unitats")
    public List<Unitat> getUnitats() {
        return super.getUnitats();
    }

    @Override
    @Cacheable(cachePrefix + "unitatByIdentificador")
    public Unitat getUnitatByIdentificador(String identificador) {
        return super.getUnitatByIdentificador(identificador);
    }

    @Override
    @Cacheable(cachePrefix + "unitatsByNom")
    public List<Unitat> getUnitatsByNom(String nom) {
        return super.getUnitatsByNom(nom);
    }

    @Override
    @Cacheable(cachePrefix + "unitatsByNomAndIdentificadorAndCodi")
    public List<Unitat> getUnitatsByNomAndIdentificadorAndCodi(String nom, String identificador, String codiUnitat) {
        return super.getUnitatsByNomAndIdentificadorAndCodi(nom, identificador, codiUnitat);
    }

    @Override
    @Cacheable(cachePrefix + "unitatsById")
    public Unitat getUnitatById(long idUnitat) {
        return super.getUnitatById(idUnitat);
    }

    @Override
    @Cacheable(cachePrefix + "infraestructuraByMarcaAndNumeroDeSerie")
    public Infraestructura getInfraestructuraByMarcaAndNumeroDeSerie(long idMarca, String sn) {
        return super.getInfraestructuraByMarcaAndNumeroDeSerie(idMarca, sn);
    }

    @Override
    @Cacheable(cachePrefix + "infraestructuraById")
    public Infraestructura getInfraestructuraById(long id) {
        return super.getInfraestructuraById(id);
    }

    @Override
    @Cacheable(cachePrefix + "sistemesOperatius")
    public List<SistemaOperatiu> getSistemesOperatius() {
        return super.getSistemesOperatius();
    }

    @Override
    @Cacheable(cachePrefix + "sistemesOperatiusByCategoria")
    public List<SistemaOperatiu> getSistemesOperatiusByCategoria(long idCategoria) {
        return super.getSistemesOperatiusByCategoria(idCategoria);
    }

    @Override
    @Cacheable(cachePrefix + "sistemesOperatiusByCodi")
    public List<SistemaOperatiu> getSistemesOperatiusByCodi(String codi) {
        return super.getSistemesOperatiusByCodi(codi);
    }

    @Override
    @Cacheable(cachePrefix + "sistemesOperatiusByNom")
    public List<SistemaOperatiu> getSistemesOperatiusByNom(String nom) {
        return super.getSistemesOperatiusByNom(nom);
    }

    @Override
    @Cacheable(cachePrefix + "sistemaOperatiuById")
    public SistemaOperatiu getSistemaOperatiuById(long idSistemaOperatiu) {
        return super.getSistemaOperatiuById(idSistemaOperatiu);
    }
}
