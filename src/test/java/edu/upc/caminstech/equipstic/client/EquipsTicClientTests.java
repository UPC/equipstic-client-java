package edu.upc.caminstech.equipstic.client;

import static org.junit.Assert.*;

import java.net.URI;
import java.util.List;

import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.util.StringUtils;

import edu.upc.caminstech.equipstic.Campus;
import edu.upc.caminstech.equipstic.Edifici;
import edu.upc.caminstech.equipstic.Estat;
import edu.upc.caminstech.equipstic.Infraestructura;
import edu.upc.caminstech.equipstic.Marca;
import edu.upc.caminstech.equipstic.TipusInfraestructura;
import edu.upc.caminstech.equipstic.TipusUs;
import edu.upc.caminstech.equipstic.TipusXarxa;
import edu.upc.caminstech.equipstic.Unitat;

/**
 * Tests d'integració per a la classe {@link EquipsTicClient}.
 */
public class EquipsTicClientTests {

    // environment variable names

    private static final String ENV_API_URL = "EQUIPSTIC_SOA_URL";
    private static final String ENV_USERNAME_VAR = "EQUIPSTIC_SOA_USERNAME";
    private static final String ENV_PASSWORD_VAR = "EQUIPSTIC_SOA_PASSWORD";

    private static final String envApiUrl = System.getenv(ENV_API_URL);
    private static final String envUsername = System.getenv(ENV_USERNAME_VAR);
    private static final String envPassword = System.getenv(ENV_PASSWORD_VAR);

    private static final long ID_CATEGORIA_SERVIDOR = 1;
    private static final long ID_ESTAT_EN_GARANTIA_CENTRALITZAT = 1;
    private static final long ID_TIPUS_IMPRESSORA = 6;
    private static final long ID_TIPUS_XARXA_TRONCAL = 1;
    private static final long ID_TIPUS_US_DOCENCIA = 34;
    private static final long ID_CAMPUS_NORD = 1;
    private static final long ID_MARCA_IBM = 45;
    private static final long ID_UNITAT_UTGAC = 79;
    private static final String CODI_CAMPUS_NORD = "ND";
    private static final String NOM_TIPUS_IMPRESSORA = "Impressora";
    private static final String CODI_TIPUS_IMPRESSORA = "IMPRESSORA";
    private static final String NOM_MARCA_IBM = "IBM";
    private static final String NOM_ESTAT_BAIXA = "Baixa";
    private static final String CODI_ESTAT_BAIXA = "BAIXA";
    private static final String CODI_EDIFICI_VERTEX = "VX";
    private static final String CODI_UNITAT_UTGAC = "171";
    private static final String NOM_UNITAT_UTGAC = "Utg de l'Àmbit de Camins";
    private static final String IDENTIFICADOR_UNITAT_UTGAC = "UTGAC";

    private static EquipsTicClient client;

    @BeforeClass
    public static void setUp() throws RecursNoTrobatException {
        /*
         * Els tests d'aquesta classe només s'executaran en cas que estiguin
         * definides les variables d'entorn necessàries per a la configuració
         * del client.
         */
        checkCredentialsDefined();

        URI baseUri = URI.create(envApiUrl);
        client = new EquipsTicClient(baseUri, envUsername, envPassword);
    }

    private static void checkCredentialsDefined() {
        String msg = String.format("No s'han definit les variables d'entorn %s, %s i %s", ENV_API_URL, ENV_USERNAME_VAR,
                ENV_PASSWORD_VAR);
        boolean missingVariables = StringUtils.isEmpty(envApiUrl) || StringUtils.isEmpty(envUsername)
                || StringUtils.isEmpty(envPassword);

        // si això no es compleix, s'ignoraran els tests d'aquesta classe
        Assume.assumeFalse(msg, missingVariables);
    }

    @Test
    public void getAmbitShouldNotThrowException() {
        client.getAmbits();
    }

    @Test
    public void getCampusShouldNotThrowException() {
        client.getCampus();
    }

    @Test
    public void getCampusByCodi() {
        Campus campus = client.getCampusByCodi(CODI_CAMPUS_NORD);
        assertEquals(CODI_CAMPUS_NORD, campus.getCodi());
    }

    @Test
    public void getCampusById() {
        Campus campus = client.getCampusById(ID_CAMPUS_NORD);
        assertEquals(ID_CAMPUS_NORD, campus.getIdCampus());
    }

    @Test
    public void getCategoriesShouldNotThrowException() {
        client.getCategories();
    }

    @Test
    public void getCategoriaById() {
        client.getCategoriaById(1);
    }

    @Test
    public void getEdificis() {
        List<Edifici> edificis = client.getEdificis();
        assertTrue(edificis.size() > 0);
    }

    @Test
    public void getEdificiById() {
        Edifici edifici = client.getEdificiById(1);

        assertNotNull(edifici);
        assertEquals(1, edifici.getIdEdifici());
        assertNotNull(edifici.getCampus());
    }

    @Test
    public void getEdificiByCodiAndCodiCampus() {
        String codiEdifici = CODI_EDIFICI_VERTEX;
        String codiCampus = CODI_CAMPUS_NORD;
        Edifici edifici = client.getEdificiByCodiAndCodiCampus(codiEdifici, codiCampus);

        assertNotNull(edifici);
        assertEquals(codiEdifici, edifici.getCodi());
        assertEquals(codiCampus, edifici.getCampus().getCodi());
    }

    @Test
    public void getEstats() {
        List<Estat> estats = client.getEstats();
        assertNotNull(estats);
    }

    @Test
    public void getEstatByCodi() {
        String codi = CODI_ESTAT_BAIXA;
        Estat estat = client.getEstatByCodi(codi);
        assertNotNull(estat);
        assertEquals(codi, estat.getCodi());
    }

    @Test
    public void getEstatsByNom() {
        List<Estat> estats = client.getEstatsByNom(NOM_ESTAT_BAIXA);
        assertNotNull(estats);
    }

    @Test
    public void getEstatById() {
        long idEstat = ID_ESTAT_EN_GARANTIA_CENTRALITZAT;
        Estat estat = client.getEstatById(idEstat);
        assertNotNull(estat);
        assertEquals(idEstat, estat.getIdEstat());
    }

    @Test
    public void getMarques() {
        assertFalse(client.getMarques().isEmpty());
    }

    @Test
    public void getMarquesByNom() {
        List<Marca> marques = client.getMarquesByNom(NOM_MARCA_IBM);
        assertFalse(marques.isEmpty());
        for (Marca m : marques) {
            assertEquals(NOM_MARCA_IBM, m.getNom());
        }
    }

    @Test
    public void getMarcaById() {
        Marca marca = client.getMarcaById(ID_MARCA_IBM);
        assertEquals(ID_MARCA_IBM, marca.getIdMarca());
    }

    @Test
    public void getTipusInfraestructura() {
        List<TipusInfraestructura> tipus = client.getTipusInfraestructura();
        TipusInfraestructura t = tipus.get(0);

        assertFalse(tipus.isEmpty());
        assertNotNull(t.getCategoriaInfraestructura());
    }

    @Test
    public void getTipusInfraestructuraByCategoria() {
        long idCategoria = ID_CATEGORIA_SERVIDOR;
        assertFalse(client.getTipusInfraestructuraBycategoria(idCategoria).isEmpty());
    }

    @Test
    public void getTipusInfraestructuraByCodi() {
        assertEquals(CODI_TIPUS_IMPRESSORA, client.getTipusInfraestructuraBycodi(CODI_TIPUS_IMPRESSORA).getCodi());
    }

    @Test
    public void getTipusInfraestructuraByNom() {
        assertFalse(client.getTipusInfraestructuraByNom(NOM_TIPUS_IMPRESSORA).isEmpty());
    }

    @Test
    public void getTipusInfraestructuraById() {
        long id = ID_TIPUS_IMPRESSORA;
        assertEquals(id, client.getTipusInfraestructuraById(id).getIdTipus());
    }

    @Test
    public void getTipusUs() {
        assertFalse(client.getTipusUs().isEmpty());
    }

    @Test
    public void getTipusUsByUnitat() {
        Unitat unitat = new Unitat(ID_UNITAT_UTGAC, CODI_UNITAT_UTGAC, IDENTIFICADOR_UNITAT_UTGAC, NOM_UNITAT_UTGAC);
        List<TipusUs> tipus = client.getTipusUsByUnitat(unitat.getIdUnitat());
        for (TipusUs t : tipus) {
            assertEquals(unitat, t.getUnitat());
        }
    }

    @Test
    public void getTipusUsById() {
        TipusUs tipus = client.getTipusUsById(ID_TIPUS_US_DOCENCIA);
        assertEquals(ID_TIPUS_US_DOCENCIA, tipus.getIdTipusUs());
    }

    @Test
    public void getTipusXarxa() {
        assertFalse(client.getTipusXarxa().isEmpty());
    }

    @Test
    public void getTipusXarxaById() {
        TipusXarxa tipus = client.getTipusXarxaById(ID_TIPUS_XARXA_TRONCAL);
        assertEquals(ID_TIPUS_XARXA_TRONCAL, tipus.getIdTipusXarxa());
    }

    @Test
    public void getUnitats() {
        assertFalse(client.getUnitats().isEmpty());
    }

    @Test
    public void getUnitatByIdentificador() {
        String identificador = IDENTIFICADOR_UNITAT_UTGAC;
        assertEquals(identificador, client.getUnitatByIdentificador(identificador).getIdentificador());
    }

    @Test
    public void getUnitatsByNom() {
        List<Unitat> unitats = client.getUnitatsByNom(NOM_UNITAT_UTGAC);
        assertFalse(unitats.isEmpty());
        for (Unitat u : unitats) {
            assertEquals(NOM_UNITAT_UTGAC, u.getNom());
        }
    }

    @Test
    public void getUnitatById() {
        long id = ID_UNITAT_UTGAC;
        assertEquals(id, client.getUnitatById(id).getIdUnitat());
    }

    @Test
    public void getUnitatsByNomAndIdentificadorAndCodi() {
        String nom = NOM_UNITAT_UTGAC;
        String identificador = IDENTIFICADOR_UNITAT_UTGAC;
        String codi = CODI_UNITAT_UTGAC;
        List<Unitat> unitats = client.getUnitatsByNomAndIdentificadorAndCodi(nom, identificador, codi);
        assertFalse(unitats.isEmpty());
        for (Unitat u : unitats) {
            assertEquals(nom, u.getNom());
            assertEquals(identificador, u.getIdentificador());
            assertEquals(codi, u.getCodiUnitat());
        }
    }

    @Test
    public void getInfraestructuraById() {
        long id = 16137;
        Infraestructura infraestructura = client.getInfraestructuraById(id);
        assertNotNull(infraestructura);
    }

    @Test(expected = RecursNoTrobatException.class)
    public void getInfraestructuraByIdNotFound() {
        client.getInfraestructuraById(123);
    }

    @Test
    public void getInfraestructuraByMarcaAndNumeroDeSerie() {
        long idMarca = 2;
        String sn = "7MQ48Z1";
        Infraestructura infraestructura = client.getInfraestructuraByMarcaAndNumeroDeSerie(idMarca, sn);
        assertNotNull(infraestructura);
    }

    @Test
    public void altaInfraestructura() {
        Infraestructura i = infraestructuraFixture();
        client.altaInfraestructura(i);
    }

    public Infraestructura infraestructuraFixture() {
        Infraestructura i = new Infraestructura();
        i.setMarca(client.getMarcaById(ID_MARCA_IBM));
        i.setNumeroSerie("1234-test");
        i.setTipusInfraestructura(client.getTipusInfraestructuraBycodi(CODI_TIPUS_IMPRESSORA));
        i.setEstat(client.getEstatById(ID_ESTAT_EN_GARANTIA_CENTRALITZAT));
        return i;
    }
}
