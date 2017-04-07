package edu.upc.caminstech.equipstic.client;

import java.util.List;

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
 * Interfície base per als clients d'EquipsTIC.
 * <p>
 * Recull només les funcionalitats definides a la API d'EquipsTIC.
 * <p>
 * Per consistència, es recomana que les implementacions d'aquesta interfície
 * segueixin les següents convencions:
 * <ul>
 * <li>Les operacions que retornin llistes mai no retornen {@code null}: quan no
 * hi ha resultats, retornen una llista buida.</li>
 * <li>Les operacions que retornin un sol objecte retornen {@code null} si
 * l'objecte no existeix (no generen cap excepció per aquest motiu).</li>
 * <li>Les operacions generen una excepció del tipus
 * {@link IllegalArgumentException} quan detecten errors en els paràmetres
 * <em>abans</em> de fer la crida al servidor.
 * <li>Les operacions generen una excepció de tipus
 * {@link EquipsTicClientException} en cas d'error en la crida al servidor.</li>
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
    Ambit getAmbitById(long idAmbit);

    /**
     * Retorna tots els campus existents.
     */
    List<Campus> getCampus();

    /**
     * Retorna el campus amb el codi donat.
     */
    Campus getCampusByCodi(String codiCampus);

    /**
     * Retorna el campus amb l'identificador donat.
     */
    Campus getCampusById(long idCampus);

    /**
     * Retorna totes les categories existents.
     */
    List<Categoria> getCategories();

    /**
     * Retorna una categoria a partir del seu identificador.
     */
    Categoria getCategoriaById(long idCategoria);

    /**
     * Retorna tots els edificis existents.
     */
    List<Edifici> getEdificis();

    /**
     * Retorna un edifici a partir del seu identificador.
     */
    Edifici getEdificiById(long idEdifici);

    /**
     * Retorna un edifici a partir del codi i el campus.
     */
    Edifici getEdificiByCodiAndCodiCampus(String codiEdifici, String codiCampus);

    /**
     * Retorna tots els estats existents.
     */
    List<Estat> getEstats();

    /**
     * Retorna un estat a partir del seu codi.
     */
    Estat getEstatByCodi(String codiEstat);

    /**
     * Cerca d'estats a partir d'un nom.
     */
    List<Estat> getEstatsByNom(String nomEstat);

    /**
     * Retorna un estat a partir del seu identificador.
     */
    Estat getEstatById(long idEstat);

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
    Marca getMarcaById(long idMarca);

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
    TipusUs getTipusUsById(long idTipusUs);

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
    TipusInfraestructura getTipusInfraestructuraBycodi(String codi);

    /**
     * Cerca de tipus d'infraestructura a partir del seu nom.
     */
    List<TipusInfraestructura> getTipusInfraestructuraByNom(String nom);

    /**
     * Retorna un tipus d'infraestructura a partir del seu identificador.
     */
    TipusInfraestructura getTipusInfraestructuraById(long idTipus);

    /**
     * Retorna tots els tipus de xarxa existents.
     */
    List<TipusXarxa> getTipusXarxa();

    /**
     * Retorna un tipus de xarxa a partir del seu identificador.
     */
    TipusXarxa getTipusXarxaById(long idTipusXarxa);

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
    Unitat getUnitatById(long idUnitat);

    /**
     * Retorna una infraestructura a partir de la marca i el número de sèrie.
     */
    Infraestructura getInfraestructuraByMarcaAndNumeroDeSerie(long idMarca, String sn);

    /**
     * Retorna una infraestructura a partir del seu identificador.
     */
    Infraestructura getInfraestructuraById(long id);

    /**
     * Cerca d'infraestructures a partir d'una unitat.
     */
    List<Infraestructura> getInfraestructuresByUnitat(long idUnitat);

    /**
     * Dóna d'alta una nova infraestructura.
     * 
     * @param infraestructura
     *            La infraestructura a crear.
     * @return la infraestructura creada, amb l'identificador assignat.
     */
    Infraestructura altaInfraestructura(Infraestructura infraestructura);

    /**
     * Dóna de baixa (esborra) una infraestructura a partir del seu
     * identificador.
     */
    void baixaInfraestructura(long id);

    /**
     * Modifica una infraestructura.
     *
     * @param infraestructura
     *            la nova infraestructura que substituirà l'antiga
     * @return la infraestructura un cop modificada
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
    SistemaOperatiu getSistemaOperatiuById(long idSistemaOperatiu);

    /**
     * Obté un {@link UsuariInfraestructura} a partir del seu identificador.
     */
    UsuariInfraestructura getUsuariInfraestructura(long idUsuariInfraestructura);

    /**
     * Obté tot els {@link UsuariInfraestructura} existents.
     */
    List<UsuariInfraestructura> getUsuarisInfraestructura();

    /**
     * Cerca d'{@link UsuariInfraestructura} per nom.
     */
    List<UsuariInfraestructura> getUsuarisInfraestructuraByNom(String nom);
}
