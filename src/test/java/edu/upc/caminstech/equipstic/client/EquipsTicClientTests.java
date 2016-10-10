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
        String codiCampus = "ND"; // campus nord
        Campus campus = client.getCampusByCodi(codiCampus);
        assertEquals(codiCampus, campus.getCodi());
    }

    @Test
    public void getCampusById() {
        Campus campus = client.getCampusById(1);
        assertEquals(1, campus.getIdCampus());
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
        String codiEdifici = "VX"; // codi edifici Vertex
        String codiCampus = "ND"; // codi Campus Nord
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
        String codi = "BAIXA";
        Estat estat = client.getEstatByCodi(codi);
        assertNotNull(estat);
        assertEquals(codi, estat.getCodi());
    }

    @Test
    public void getEstatsByNom() {
        List<Estat> estats = client.getEstatsByNom("Baixa");
        assertNotNull(estats);
    }

    @Test
    public void getEstatById() {
        long idEstat = 1; // estat "en garantia centralitzat"
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
        String nom = "IBM";
        List<Marca> marques = client.getMarquesByNom(nom);
        assertFalse(marques.isEmpty());
        for (Marca m : marques) {
            assertEquals(nom, m.getNom());
        }
    }

    @Test
    public void getMarcaById() {
        long id = 45; // marca IBM
        Marca marca = client.getMarcaById(id);
        assertEquals(id, marca.getIdMarca());
    }

    @Test
    public void getTipusInfraestructura() {
        List<TipusInfraestructura> tipus = client.getTipusInfraestructura();
        TipusInfraestructura t = tipus.get(0);

        assertFalse(tipus.isEmpty());
        System.out.println(t);
        assertNotNull(t.getCategoriaInfraestructura());
    }

    @Test
    public void getTipusInfraestructuraByCategoria() {
        long idCategoria = 1; // categoria Servidor
        assertFalse(client.getTipusInfraestructuraBycategoria(idCategoria).isEmpty());
    }

    @Test
    public void getTipusInfraestructuraByCodi() {
        assertEquals("IMPRESSORA", client.getTipusInfraestructuraBycodi("IMPRESSORA").getCodi());
    }

    @Test
    public void getTipusInfraestructuraByNom() {
        assertFalse(client.getTipusInfraestructuraByNom("Impressora").isEmpty());
    }

    @Test
    public void getTipusInfraestructuraById() {
        long id = 6; // tipus Impressora
        assertEquals(id, client.getTipusInfraestructuraById(id).getIdTipus());
    }

    @Test
    public void getTipusUs() {
        assertFalse(client.getTipusUs().isEmpty());
    }

    @Test
    public void getTipusUsByUnitat() {
        Unitat unitat = new Unitat(79, "171", "UTGAC", "Utg de l'Àmbit de Camins");
        List<TipusUs> tipus = client.getTipusUsByUnitat(unitat.getIdUnitat());
        for (TipusUs t : tipus) {
            assertEquals(unitat, t.getUnitat());
        }
    }

    @Test
    public void getTipusUsById() {
        long id = 34; // tipus us "docència"
        TipusUs tipus = client.getTipusUsById(id);
        assertEquals(id, tipus.getIdTipusUs());
    }

    @Test
    public void getTipusXarxa() {
        assertFalse(client.getTipusXarxa().isEmpty());
    }

    @Test
    public void getTipusXarxaById() {
        long idTipus = 1; // tipus xarxa "troncal"
        assertEquals(idTipus, client.getTipusXarxaById(idTipus).getIdTipusXarxa());
    }

    @Test
    public void getUnitats() {
        assertFalse(client.getUnitats().isEmpty());
    }

    @Test
    public void getUnitatByIdentificador() {
        String identificador = "UTGAC";
        assertEquals(identificador, client.getUnitatByIdentificador(identificador).getIdentificador());
    }

    @Test
    public void getUnitatsByNom() {
        String nom = "Utg de l'Àmbit de Camins";
        List<Unitat> unitats = client.getUnitatsByNom(nom);
        assertFalse(unitats.isEmpty());
        for (Unitat u : unitats) {
            assertEquals(nom, u.getNom());
        }
    }

    @Test
    public void getUnitatById() {
        long id = 79; // UTGAC
        assertEquals(id, client.getUnitatById(id).getIdUnitat());
    }

    @Test
    public void getUnitatsByNomAndIdentificadorAndCodi() {
        String nom = "Utg de l'Àmbit de Camins";
        String identificador = "UTGAC";
        String codi = "171";
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
        String idMarca = "2";
        String sn = "7MQ48Z1";
        Infraestructura infraestructura = client.getInfraestructuraByMarcaAndNumeroDeSerie(idMarca, sn);
        assertNotNull(infraestructura);
    }
}
