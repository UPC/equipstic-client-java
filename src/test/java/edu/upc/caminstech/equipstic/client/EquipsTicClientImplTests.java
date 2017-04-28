package edu.upc.caminstech.equipstic.client;

import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

import java.net.URISyntaxException;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.HttpStatus;

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
import edu.upc.caminstech.equipstic.fixtures.InfraestructuraFixtures;

/**
 * Tests d'integració per a la classe {@link EquipsTicClientImpl}.
 * <p>
 * TODO: S'ha de refactoritzar aquesta classe per fer <em>mocking</em> del
 * servidor.
 */
public class EquipsTicClientImplTests {

    // noms de les variables d'entorn

    private static final String ENV_API_URL = "EQUIPSTIC_SOA_URL";
    private static final String ENV_USERNAME_VAR = "EQUIPSTIC_SOA_USERNAME";
    private static final String ENV_PASSWORD_VAR = "EQUIPSTIC_SOA_PASSWORD";

    private static final String envApiUrl = System.getenv(ENV_API_URL);
    private static final String envUsername = System.getenv(ENV_USERNAME_VAR);
    private static final String envPassword = System.getenv(ENV_PASSWORD_VAR);

    // alguns valors per fer servir als tests

    private static final int ID_INFRAESTRUCTURA = 16137;
    private static final long ID_CATEGORIA_SERVIDOR = 1;
    private static final long ID_ESTAT_EN_GARANTIA_CENTRALITZAT = 1;
    private static final long ID_TIPUS_IMPRESSORA = 6;
    private static final long ID_TIPUS_XARXA_TRONCAL = 1;
    private static final long ID_TIPUS_US_DOCENCIA = 34;
    private static final long ID_CAMPUS_NORD = 1;
    private static final long ID_EDIFICI_B2 = 36;
    private static final long ID_MARCA_IBM = 45;
    private static final long ID_UNITAT_UTGAC = 79;
    private static final long ID_SISTEMA_OPERATIU_LINUX = 2;
    private static final long ID_AMBIT_LT_PAS = 39;
    private static final String CODI_CAMPUS_NORD = "ND";
    private static final String NOM_TIPUS_IMPRESSORA = "Impressora";
    private static final String CODI_TIPUS_IMPRESSORA = "IMPRESSORA";
    private static final String NOM_AMBIT_LT_PAS = "Lloc de treball PAS";
    private static final String NOM_MARCA_IBM = "IBM";
    private static final String NOM_ESTAT_BAIXA = "Baixa";
    private static final String CODI_ESTAT_BAIXA = "BAIXA";
    private static final String CODI_EDIFICI_VERTEX = "VX";
    private static final String CODI_UNITAT_UTGAC = "171";
    private static final String CODI_AMBIT_SALA_TECNICA = "SALA_TEC";
    private static final String NOM_UNITAT_UTGAC = "Utg de l'Àmbit de Camins";
    private static final String IDENTIFICADOR_UNITAT_UTGAC = "UTGAC";

    private static EquipsTicClient client;

    @BeforeClass
    public static void setUp() throws URISyntaxException {
        /*
         * Els tests d'aquesta classe només s'executaran en cas que estiguin
         * definides les variables d'entorn necessàries per a la configuració
         * del client.
         */
        checkEnvironmentDefined();

        EquipsTicClientConfiguration config = new EquipsTicClientConfiguration(envApiUrl, envUsername, envPassword);
        EquipsTicClient baseClient = new EquipsTicClientImpl(config);

        client = baseClient;
    }

    private static void checkEnvironmentDefined() {
        List<String> varNames = Arrays.asList(ENV_API_URL, ENV_USERNAME_VAR, ENV_PASSWORD_VAR);
        List<String> values = varNames.stream().map(System::getenv).collect(Collectors.toList());
        String msg = String.format("No s'ha definit alguna de les variables d'entorn necessàries %s",
                varNames.toString());
        boolean allDefined = values.stream().noneMatch(StringUtils::isEmpty);

        // si això no es compleix, s'ignoraran els tests d'aquesta classe
        assumeTrue(msg, allDefined);
    }

    @Test
    public void getAmbits() {
        List<Ambit> ambits = client.getAmbits();
        assertFalse(ambits.isEmpty());
    }

    @Test
    public void getAmbitsByNom() {
        List<Ambit> ambits = client.getAmbitsByNom(NOM_AMBIT_LT_PAS);
        assertFalse(ambits.isEmpty());
        assertTrue(ambits.stream().allMatch(a -> a.getNom().equals(NOM_AMBIT_LT_PAS)));
    }

    @Test
    public void getAmbitById() {
        Ambit a = client.getAmbitById(ID_AMBIT_LT_PAS);
        assertNotNull(a);
        assertEquals(ID_AMBIT_LT_PAS, a.getIdAmbit());
    }

    @Test
    public void getAmbitsByCodi() {
        List<Ambit> ambits = client.getAmbitsByCodi(CODI_AMBIT_SALA_TECNICA);
        assertNotNull(ambits);
        assertFalse(ambits.isEmpty());
        assertTrue(ambits.stream().allMatch(a -> StringUtils.equalsIgnoreCase(CODI_AMBIT_SALA_TECNICA, a.getCodi())));
    }

    @Test
    public void getAmbitsByCodiNotFound() {
        List<Ambit> ambits = client.getAmbitsByCodi("dummy");
        assertNotNull(ambits);
        assertTrue(ambits.isEmpty());
    }

    @Test
    public void getAmbitsByCategoria() {
        long idCategoria = ID_CATEGORIA_SERVIDOR;
        List<Ambit> ambits = client.getAmbitsByCategoria(idCategoria);
        assertNotNull(ambits);
        assertTrue(ambits.stream().allMatch(a -> idCategoria == a.getCategoriaInfraestructura().getIdCategoria()));
    }

    @Test
    public void getAmbitsByCategoriaNotFound() {
        List<Ambit> ambits = client.getAmbitsByCategoria(0);
        assertNotNull(ambits);
        assertTrue(ambits.isEmpty());
    }

    @Test
    public void getCampus() {
        List<Campus> campus = client.getCampus();
        assertFalse(campus.isEmpty());
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
    public void getCategories() {
        List<Categoria> categories = client.getCategories();
        assertFalse(categories.isEmpty());
    }

    @Test
    public void getCategoriaById() {
        Categoria c = client.getCategoriaById(ID_CATEGORIA_SERVIDOR);
        assertNotNull(c);
        assertEquals(ID_CATEGORIA_SERVIDOR, c.getIdCategoria());
    }

    @Test
    public void getEdificis() {
        List<Edifici> edificis = client.getEdificis();
        assertFalse(edificis.isEmpty());
    }

    @Test
    public void getEdificiById() {
        Edifici edifici = client.getEdificiById(ID_EDIFICI_B2);

        assertNotNull(edifici);
        assertEquals(ID_EDIFICI_B2, edifici.getIdEdifici());
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
        assertFalse(estats.isEmpty());
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
        assertFalse(estats.isEmpty());
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
        List<Marca> marques = client.getMarques();
        assertFalse(marques.isEmpty());
    }

    @Test
    public void getMarquesByNom() {
        List<Marca> marques = client.getMarquesByNom(NOM_MARCA_IBM);
        assertFalse(marques.isEmpty());
        assertTrue(marques.stream().allMatch(m -> containsIgnoreCase(m.getNom(), NOM_MARCA_IBM)));
    }

    @Test
    public void getMarcaById() {
        Marca marca = client.getMarcaById(ID_MARCA_IBM);
        assertEquals(ID_MARCA_IBM, marca.getIdMarca());
    }

    @Test
    public void getTipusInfraestructura() {
        List<TipusInfraestructura> tipus = client.getTipusInfraestructura();
        assertFalse(tipus.isEmpty());
    }

    @Test
    public void getTipusInfraestructuraByCategoria() {
        List<TipusInfraestructura> tipus = client.getTipusInfraestructuraByCategoria(ID_CATEGORIA_SERVIDOR);
        assertFalse(tipus.isEmpty());
    }

    @Test
    public void getTipusInfraestructuraByCodi() {
        TipusInfraestructura tipus = client.getTipusInfraestructuraBycodi(CODI_TIPUS_IMPRESSORA);
        assertEquals(CODI_TIPUS_IMPRESSORA, tipus.getCodi());
    }

    @Test
    public void getTipusInfraestructuraByNom() {
        List<TipusInfraestructura> tipus = client.getTipusInfraestructuraByNom(NOM_TIPUS_IMPRESSORA);
        assertFalse(tipus.isEmpty());
    }

    @Test
    public void getTipusInfraestructuraById() {
        TipusInfraestructura tipus = client.getTipusInfraestructuraById(ID_TIPUS_IMPRESSORA);
        assertEquals(ID_TIPUS_IMPRESSORA, tipus.getIdTipus());
    }

    @Test
    public void getTipusUs() {
        List<TipusUs> tipus = client.getTipusUs();
        assertFalse(tipus.isEmpty());
    }

    @Test
    public void getTipusUsByUnitat() {
        Unitat unitat = new Unitat(ID_UNITAT_UTGAC);
        List<TipusUs> tipus = client.getTipusUsByUnitat(ID_UNITAT_UTGAC);
        assertTrue(tipus.stream().allMatch(t -> t.equals(unitat)));
    }

    @Test
    public void getTipusUsById() {
        TipusUs tipus = client.getTipusUsById(ID_TIPUS_US_DOCENCIA);
        assertEquals(ID_TIPUS_US_DOCENCIA, tipus.getIdTipusUs());
    }

    @Test
    public void getTipusXarxa() {
        List<TipusXarxa> tipus = client.getTipusXarxa();
        assertFalse(tipus.isEmpty());
    }

    @Test
    public void getTipusXarxaById() {
        TipusXarxa tipus = client.getTipusXarxaById(ID_TIPUS_XARXA_TRONCAL);
        assertEquals(ID_TIPUS_XARXA_TRONCAL, tipus.getIdTipusXarxa());
    }

    @Test
    public void getUnitats() {
        List<Unitat> unitats = client.getUnitats();
        assertFalse(unitats.isEmpty());
    }

    @Test
    public void getUnitatByIdentificador() {
        List<Unitat> unitats = client.getUnitatsByIdentificador(IDENTIFICADOR_UNITAT_UTGAC);
        assertFalse(unitats.isEmpty());
        assertTrue(unitats.stream().allMatch(u -> IDENTIFICADOR_UNITAT_UTGAC.equals(u.getIdentificador())));
    }

    @Test
    public void getUnitatsByNom() {
        List<Unitat> unitats = client.getUnitatsByNom(NOM_UNITAT_UTGAC);
        assertFalse(unitats.isEmpty());
        for (Unitat u : unitats) {
            assertThat(u.getNom(), equalToIgnoringCase(NOM_UNITAT_UTGAC));
        }
    }

    @Test
    public void getUnitatById() {
        Unitat unitat = client.getUnitatById(ID_UNITAT_UTGAC);
        assertEquals(ID_UNITAT_UTGAC, unitat.getIdUnitat());
    }

    @Test
    public void getUnitatsByNomAndIdentificadorAndCodi() {
        String nom = NOM_UNITAT_UTGAC;
        String identificador = IDENTIFICADOR_UNITAT_UTGAC;
        String codi = CODI_UNITAT_UTGAC;
        List<Unitat> unitats = client.getUnitatsByNomAndIdentificadorAndCodi(nom, identificador, codi);
        assertFalse(unitats.isEmpty());
        for (Unitat u : unitats) {
            assertThat(u.getNom(), equalToIgnoringCase(nom));
            assertEquals(identificador, u.getIdentificador());
            assertEquals(codi, u.getCodiUnitat());
        }
    }

    @Test
    public void getInfraestructuraById() {
        Infraestructura infraestructura = client.getInfraestructuraById(ID_INFRAESTRUCTURA, false);
        assertNotNull(infraestructura);
        assertEquals(ID_INFRAESTRUCTURA, infraestructura.getIdentificador());
    }

    @Test
    public void getInfraestructuraByIdNotFound() {
        Infraestructura i = client.getInfraestructuraById(123, false);
        assertNull(i);
    }

    @Test
    public void getInfraestructuraByMarcaAndNumeroDeSerie() {
        long idMarca = 2;
        String sn = "7MQ48Z1";
        Infraestructura infraestructura = client.getInfraestructuraByMarcaAndNumeroDeSerie(idMarca, sn, false);
        assertNotNull(infraestructura);
    }

    @Test
    @Ignore // si no es fa servir caché aquest test és molt lent
    public void getInfraestructuresByUnitat() {
        List<Infraestructura> infraestructures = client.getInfraestructuresByUnitat(ID_UNITAT_UTGAC);
        assertNotNull(infraestructures);
        assertFalse(infraestructures.isEmpty());
        assertTrue(infraestructures.stream().allMatch(i -> i.getUnitat().getIdUnitat() == ID_UNITAT_UTGAC));
    }

    @Test
    public void altaInfraestructura() {
        Infraestructura i = InfraestructuraFixtures.infraestructuraFixture();
        Infraestructura creada = null;
        try {
            creada = client.altaInfraestructura(i);
            assertNotNull(creada);
            assertEquals(i.getMarca(), creada.getMarca());
            assertEquals(i.getNumeroSerie(), creada.getNumeroSerie());
            assertEquals("dataCompra", i.getDataCompra(), creada.getDataCompra());
            assertEquals("dataFinalGarantia", i.getDataFinalGarantia(), creada.getDataFinalGarantia());
            assertNotEquals(0, creada.getIdentificador());
        } finally {
            if (creada != null) {
                client.baixaInfraestructura(creada.getIdentificador());
            }
        }
    }

    @Test
    public void baixaInfraestructuraInexistent() {
        try {
            client.baixaInfraestructura(0);
            fail("S'hauria d'haver llançat una excepció");
        } catch (EquipsTicClientException e) {
            assertEquals(HttpStatus.NOT_FOUND, e.getStatus());
        }
    }

    @Test
    public void modificaInfraestructura() {
        Infraestructura fixture = InfraestructuraFixtures.infraestructuraFixture();
        Infraestructura i = null;
        try {
            i = client.altaInfraestructura(fixture);

            assertEquals(fixture.getDataFinalGarantia(), i.getDataFinalGarantia());

            i.setDataTramitFactura(InfraestructuraFixtures.dateFixture(2017, Month.JANUARY, 2));

            Infraestructura nova = client.modificaInfraestructura(i);

            assertNotNull(nova);
            assertEquals("L'identificador no hauria de canviar", i.getIdentificador(), nova.getIdentificador());
            assertEquals(fixture.getDataFinalGarantia(), nova.getDataFinalGarantia());
            assertEquals(i.getDataTramitFactura(), nova.getDataTramitFactura());
        } finally {
            if (i != null) {
                client.baixaInfraestructura(i.getIdentificador());
            }
        }
    }

    @Test
    public void getSistemesOperatius() {
        List<SistemaOperatiu> sistemesOperatius = client.getSistemesOperatius();
        assertFalse(sistemesOperatius.isEmpty());
    }

    @Test
    public void getSistemesOperatiusByCategoria() {
        List<SistemaOperatiu> sistemesOperatius = client.getSistemesOperatiusByCategoria(ID_CATEGORIA_SERVIDOR);
        assertFalse(sistemesOperatius.isEmpty());
        assertTrue(sistemesOperatius.parallelStream()
                .allMatch(so -> so.getCategoriaInfraestructura().getIdCategoria() == ID_CATEGORIA_SERVIDOR));
    }

    @Test
    public void getSistemesOperatiusByCodi() {
        List<SistemaOperatiu> sistemesOperatius = client.getSistemesOperatiusByCodi("LINUX");
        assertFalse(sistemesOperatius.isEmpty());
        assertTrue(sistemesOperatius.parallelStream()
                .allMatch(so -> StringUtils.containsIgnoreCase(so.getCodi(), "LINUX")));
    }

    @Test
    public void getSistemesOperatiusByNom() {
        List<SistemaOperatiu> sistemesOperatius = client.getSistemesOperatiusByNom("Linux");
        assertFalse(sistemesOperatius.isEmpty());
        assertTrue(sistemesOperatius.parallelStream()
                .allMatch(so -> StringUtils.containsIgnoreCase(so.getNom(), "Linux")));
    }

    @Test
    public void getSistemaOperatiuById() {
        SistemaOperatiu so = client.getSistemaOperatiuById(ID_SISTEMA_OPERATIU_LINUX);
        assertNotNull(so);
        assertEquals(ID_SISTEMA_OPERATIU_LINUX, so.getIdSistemaOperatiu());
    }

    @Test
    public void getUsuariInfraestructuraById() {
        UsuariInfraestructura u = client.getUsuariInfraestructura(1L);
        assertNotNull(u);
        assertEquals(1L, u.getIdUsuariInfraestructura());
    }

    @Test
    public void getUsuarisInfraestructura() {
        List<UsuariInfraestructura> list = client.getUsuarisInfraestructura();
        assertNotNull(list);
        assertTrue(list.stream().filter(u -> "angel.aguilera".equals(u.getNomUsuari())).findFirst().isPresent());
    }

    @Test
    public void getUsuarisInfraestructuraByNom() {
        String nom = "angel.aguilera";
        List<UsuariInfraestructura> list = client.getUsuarisInfraestructuraByNom(nom);
        assertNotNull(list);
        assertFalse(list.isEmpty());
        System.out.println(list);
        assertTrue(list.stream().allMatch(u -> coincideixNom(u, nom)));
    }

    /**
     * Mètode auxiliar per verificar la coincidència d'un
     * {@link UsuariInfraestructura} amb un nom.
     */
    private boolean coincideixNom(UsuariInfraestructura u, String nom) {
        return containsIgnoreCase(u.getNom(), nom) || containsIgnoreCase(u.getNomUsuari(), nom);
    }
}
