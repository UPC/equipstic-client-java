package edu.upc.caminstech.equipstic.client;

import java.util.List;
import java.util.Optional;

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
import edu.upc.caminstech.equipstic.client.exception.EquipsTicClientException;
import edu.upc.caminstech.equipstic.client.exception.UnauthorizedException;

/**
 * Interfície base per als clients d'EquipsTIC.
 * <p>
 * Recull només les funcionalitats definides a la API d'EquipsTIC.
 * <p>
 * Per consistència, es recomana que les implementacions d'aquesta interfície
 * segueixin les següents convencions:
 * <ul>
 * <li>Les operacions mai no retornen <code>null</code>. En el cas d'operacions
 * de consulta, es retorna un {@link Optional} que contindrà un valor o no, en
 * funció de si l'objecte demanat existeix o no.</li>
 * <li>Les operacions han de generar una {@link EquipsTicClientException} (o
 * alguna subclasse d'aquesta) en cas que el servidor EquipsTIC retorni algun
 * error.</li>
 * </ul>
 */
public interface EquipsTicClient {

    /**
     * Retorna els àmbits existents.
     */
    List<Ambit> getAmbits();

    /**
     * Cerca d'àmbits per nom.
     */
    List<Ambit> getAmbitsByNom(String nomAmbit);

    /**
     * Retorna l'àmbit amb l'identificador donat.
     */
    Optional<Ambit> getAmbitById(long idAmbit);

    /**
     * Cerca d'àmbits per codi.
     */
    List<Ambit> getAmbitsByCodi(String codiAmbit);

    /**
     * Cerca d'àmbits per categoria.
     */
    List<Ambit> getAmbitsByCategoria(long idCategoria);

    /**
     * Retorna tots els campus existents.
     */
    List<Campus> getCampus();

    /**
     * Retorna el campus amb el codi donat.
     */
    Optional<Campus> getCampusByCodi(String codiCampus);

    /**
     * Retorna el campus amb l'identificador donat.
     */
    Optional<Campus> getCampusById(long idCampus);

    /**
     * Retorna totes les categories existents.
     */
    List<Categoria> getCategories();

    /**
     * Retorna una categoria a partir del seu identificador.
     */
    Optional<Categoria> getCategoriaById(long idCategoria);

    /**
     * Retorna tots els edificis existents.
     */
    List<Edifici> getEdificis();

    /**
     * Retorna un edifici a partir del seu identificador.
     */
    Optional<Edifici> getEdificiById(long idEdifici);

    /**
     * Retorna un edifici a partir del codi i el campus.
     */
    Optional<Edifici> getEdificiByCodiAndCodiCampus(String codiEdifici, String codiCampus);

    /**
     * Retorna tots els estats existents.
     */
    List<Estat> getEstats();

    /**
     * Retorna un estat a partir del seu codi.
     */
    Optional<Estat> getEstatByCodi(String codiEstat);

    /**
     * Cerca d'estats a partir d'un nom.
     */
    List<Estat> getEstatsByNom(String nomEstat);

    /**
     * Retorna un estat a partir del seu identificador.
     */
    Optional<Estat> getEstatById(long idEstat);

    /**
     * Retorna totes les marques existents.
     */
    List<Marca> getMarques();

    /**
     * Cerca de marques pel nom.
     */
    List<Marca> getMarquesByNom(String nom);

    /**
     * Retorna una marca a partir del seu identificador.
     */
    Optional<Marca> getMarcaById(long idMarca);

    /**
     * Retorna tots els tipus d'ús existents.
     */
    List<TipusUs> getTipusUs();

    /**
     * Cerca tipus d'ús a partir d'una unitat.
     */
    List<TipusUs> getTipusUsByUnitat(long idUnitat);

    /**
     * Retorna un tipus d'ús a partir del seu identificador.
     */
    Optional<TipusUs> getTipusUsById(long idTipusUs);

    /**
     * Retorna tots els tipus d'infraestructura existents.
     */
    List<TipusInfraestructura> getTipusInfraestructura();

    /**
     * Cerca de tipus d'infraestructura per categoria.
     */
    List<TipusInfraestructura> getTipusInfraestructuraByCategoria(long idCategoria);

    /**
     * Retorna un tipus d'infraestructura a partir del seu codi.
     */
    Optional<TipusInfraestructura> getTipusInfraestructuraBycodi(String codi);

    /**
     * Cerca de tipus d'infraestructura a partir del seu nom.
     */
    List<TipusInfraestructura> getTipusInfraestructuraByNom(String nom);

    /**
     * Retorna un tipus d'infraestructura a partir del seu identificador.
     */
    Optional<TipusInfraestructura> getTipusInfraestructuraById(long idTipus);

    /**
     * Retorna tots els tipus de xarxa existents.
     */
    List<TipusXarxa> getTipusXarxa();

    /**
     * Retorna un tipus de xarxa a partir del seu identificador.
     */
    Optional<TipusXarxa> getTipusXarxaById(long idTipusXarxa);

    /**
     * Retorna totes les unitats existents.
     */
    List<Unitat> getUnitats();

    /**
     * Retorna una unitat a partir de l'identificador (sigles UPC).
     * <p>
     * No s'ha de confondre amb el mètode {@link #getUnitatById(long)}. Aquest
     * mètode cerca a partir de l'atribut "identificador" de la classe
     * {@link Unitat}, que correspón a les sigles de la unitat, per exemple
     * "ETSECCPB".
     *
     * @see Unitat#getIdentificador()
     */
    List<Unitat> getUnitatsByIdentificador(String identificador);

    /**
     * Cerca d'unitats a partir del nom.
     */
    List<Unitat> getUnitatsByNom(String nom);

    /**
     * Cerca d'unitats a partir del nom, l'identificador i el codi.
     */
    List<Unitat> getUnitatsByNomAndIdentificadorAndCodi(String nom, String identificador, String codiUnitat);

    /**
     * Retorna una unitat a partir de l'identificador intern EquipsTIC.
     * 
     * @param idUnitat
     *            l'identificador intern d'unitat (atenció: no és el mateix que
     *            el codi d'unitat que fa servir la UPC).
     */
    Optional<Unitat> getUnitatById(long idUnitat);

    /**
     * Retorna una infraestructura a partir de la marca i el número de sèrie.
     * 
     * @param idMarca
     *            l'identificador de la marca
     * @param sn
     *            el número de sèrie
     * @param ambDetalls
     *            indica si l'objecte retornat ha d'ha estar completament
     *            inicialitzat. Si és {@code false}, els atributs complexos
     *            només tindran inicialitzat l'identificador (i la resta
     *            d'atributs a null). Altrament, els atributs estaran
     *            completament inicialitzats, però l'operació pot ser més
     *            costosa perquè caldrà fer més crides al servidor d'EquipsTIC.
     */
    Optional<Infraestructura> getInfraestructuraByMarcaAndNumeroDeSerie(long idMarca, String sn, boolean ambDetalls);

    /**
     * Retorna una infraestructura a partir del seu identificador.
     * 
     * @param id
     *            l'identificador de l'estructura a obtenir.
     * @param ambDetalls
     *            indica si l'objecte retornat ha d'ha estar completament
     *            inicialitzat. Si és {@code false}, els atributs complexos
     *            només tindran inicialitzat l'identificador (i la resta
     *            d'atributs a null). Altrament, els atributs estaran
     *            completament inicialitzats, però l'operació pot ser més
     *            costosa perquè caldrà fer més crides al servidor d'EquipsTIC.
     */
    Optional<Infraestructura> getInfraestructuraById(long id, boolean ambDetalls);

    /**
     * Cerca d'infraestructures a partir d'una unitat.
     * <p>
     * 
     * @throws UnauthorizedException
     *             si l'usuari no és un gestor de la unitat donada.
     */
    List<Infraestructura> getInfraestructuresByUnitat(long idUnitat);

    /**
     * Dóna d'alta una nova infraestructura.
     * 
     * @param infraestructura
     *            La infraestructura a crear.
     * @return la infraestructura creada, amb l'identificador assignat (mai serà
     *         null).
     * @throws UnauthorizedException
     *             si no tenim permís d'administració a la unitat de la
     *             infraestructura.
     * @throws EquipsTicClientException
     *             en qualsevol altre error durant l'alta.
     */
    Infraestructura altaInfraestructura(Infraestructura infraestructura);

    /**
     * Dóna de baixa (esborra) una infraestructura a partir del seu
     * identificador.
     * 
     * @throws UnauthorizedException
     *             si no tenim permís d'administració a la unitat de la
     *             infraestructura.
     */
    void baixaInfraestructura(long id);

    /**
     * Modifica una infraestructura.
     *
     * @param infraestructura
     *            la nova infraestructura que substituirà l'antiga
     * @return la infraestructura un cop modificada (mai serà {@code null}).
     * @throws EquipsTicClientException
     *             en cas d'error durant la modificació.
     * @throws UnauthorizedException
     *             si no tenim permís d'administració sobre la unitat de la
     *             infraestructura.
     */
    Infraestructura modificaInfraestructura(Infraestructura infraestructura);

    /**
     * Retorna tots els sistemes operatius inventariats.
     */
    List<SistemaOperatiu> getSistemesOperatius();

    /**
     * Cerca de sistemes operatius a partir de la categoria.
     */
    List<SistemaOperatiu> getSistemesOperatiusByCategoria(long idCategoria);

    /**
     * Cerca de sistemes operatius a partir del seu codi.
     */
    List<SistemaOperatiu> getSistemesOperatiusByCodi(String codi);

    /**
     * Cerca de sistemes operatius a partir del seu nom.
     */
    List<SistemaOperatiu> getSistemesOperatiusByNom(String nom);

    /**
     * Cerca de sistema operatiu a partir del seu identificador.
     */
    Optional<SistemaOperatiu> getSistemaOperatiuById(long idSistemaOperatiu);

    /**
     * Obté un {@link UsuariInfraestructura} a partir del seu identificador.
     */
    Optional<UsuariInfraestructura> getUsuariInfraestructura(long idUsuariInfraestructura);

    /**
     * Obté tot els {@link UsuariInfraestructura} existents.
     */
    List<UsuariInfraestructura> getUsuarisInfraestructura();

    /**
     * Cerca d'{@link UsuariInfraestructura} per nom.
     */
    List<UsuariInfraestructura> getUsuarisInfraestructuraByNom(String nom);
}
