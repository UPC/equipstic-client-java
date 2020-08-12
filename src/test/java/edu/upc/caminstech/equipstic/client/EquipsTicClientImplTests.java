package edu.upc.caminstech.equipstic.client;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.net.URISyntaxException;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.CustomTypeSafeMatcher;
import org.hamcrest.Matcher;
import org.junit.BeforeClass;
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
import edu.upc.caminstech.equipstic.TestUtils;
import edu.upc.caminstech.equipstic.TipusInfraestructura;
import edu.upc.caminstech.equipstic.TipusUs;
import edu.upc.caminstech.equipstic.TipusXarxa;
import edu.upc.caminstech.equipstic.Unitat;
import edu.upc.caminstech.equipstic.UsuariInfraestructura;
import edu.upc.caminstech.equipstic.client.exception.EquipsTicClientException;
import edu.upc.caminstech.equipstic.fixtures.InfraestructuraFixtures;

/**
 * Tests d'integració per a la classe {@link EquipsTicClientImpl}.
 * <p>
 * TODO: S'ha de refactoritzar aquesta classe per aïllar els tests de l'accés a
 * la xarxa mitjançant
 * <a href="https://en.wikipedia.org/wiki/Test_double">dobles</a>.
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

    private static final long ID_INFRAESTRUCTURA = 16137;
    private static final long ID_CATEGORIA_SERVIDOR = 1;
    private static final long ID_ESTAT_EN_GARANTIA_CENTRALITZAT = 1;
    private static final long ID_TIPUS_IMPRESSORA = 6;
    private static final long ID_TIPUS_XARXA_TRONCAL = 1;
    private static final long ID_TIPUS_US_DOCENCIA = 34;
    private static final long ID_CAMPUS_NORD = 1;
    private static final long ID_EDIFICI_B2 = 36;
    private static final long ID_MARCA_IBM = 45;
    private static final long ID_UNITAT_UTGAC = 79;
    private static final long ID_UNITAT_SERVEI_GESTIO_ACADEMICA = 3;
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
        TestUtils.assumeAllDefined(ENV_API_URL, ENV_USERNAME_VAR, ENV_PASSWORD_VAR);

        EquipsTicClientConfiguration config = new EquipsTicClientConfiguration(envApiUrl, envUsername, envPassword);
        EquipsTicClient baseClient = new EquipsTicClientImpl(config);

        client = baseClient;
    }

    @Test
    public void getAmbits() {
        List<Ambit> ambits = client.getAmbits();

        assertThat(ambits, not(empty()));
    }

    @Test
    public void getAmbitsByNom() {
        List<Ambit> ambits = client.getAmbitsByNom(NOM_AMBIT_LT_PAS);

        assertThat(ambits, not(empty()));
        assertThat(ambits, everyItem(hasProperty("nom", equalTo(NOM_AMBIT_LT_PAS))));
    }

    @Test
    public void getAmbitById() {
        Optional<Ambit> a = client.getAmbitById(ID_AMBIT_LT_PAS);

        assertThat(a.get().getIdAmbit(), is(ID_AMBIT_LT_PAS));
    }

    @Test
    public void getAmbitsByCodi() {
        List<Ambit> ambits = client.getAmbitsByCodi(CODI_AMBIT_SALA_TECNICA);

        assertThat(ambits, not(empty()));
        assertThat(ambits, everyItem(hasProperty("codi", equalToIgnoringCase(CODI_AMBIT_SALA_TECNICA))));
    }

    @Test
    public void getAmbitsByCodiNotFound() {
        List<Ambit> ambits = client.getAmbitsByCodi("dummy");

        assertThat(ambits, empty());
    }

    @Test
    public void getAmbitsByCategoria() {
        long idCategoria = ID_CATEGORIA_SERVIDOR;
        List<Ambit> ambits = client.getAmbitsByCategoria(idCategoria);

        assertThat(ambits, not(empty()));
        assertThat(ambits,
                everyItem(hasProperty("categoriaInfraestructura", hasProperty("idCategoria", equalTo(idCategoria)))));
    }

    @Test
    public void getAmbitsByCategoriaNotFound() {
        List<Ambit> ambits = client.getAmbitsByCategoria(0);

        assertThat(ambits, empty());
    }

    @Test
    public void getCampus() {
        List<Campus> campus = client.getCampus();

        assertThat(campus, not(empty()));
    }

    @Test
    public void getCampusByCodi() {
        Optional<Campus> campus = client.getCampusByCodi(CODI_CAMPUS_NORD);

        assertThat(campus.get().getCodi(), is(CODI_CAMPUS_NORD));
    }

    @Test
    public void getCampusById() {
        Optional<Campus> campus = client.getCampusById(ID_CAMPUS_NORD);

        assertThat(campus.get().getIdCampus(), is(ID_CAMPUS_NORD));
    }

    @Test
    public void getCategories() {
        List<Categoria> categories = client.getCategories();

        assertThat(categories, not(empty()));
    }

    @Test
    public void getCategoriaById() {
        Optional<Categoria> c = client.getCategoriaById(ID_CATEGORIA_SERVIDOR);

        assertThat(c.get().getIdCategoria(), is(ID_CATEGORIA_SERVIDOR));
    }

    @Test
    public void getEdificis() {
        List<Edifici> edificis = client.getEdificis();

        assertThat(edificis, not(empty()));
    }

    @Test
    public void getEdificiById() {
        Optional<Edifici> edifici = client.getEdificiById(ID_EDIFICI_B2);

        assertThat(edifici.get().getIdEdifici(), is(ID_EDIFICI_B2));
    }

    @Test
    public void getEdificiByCodiAndCodiCampus() {
        String codiEdifici = CODI_EDIFICI_VERTEX;
        String codiCampus = CODI_CAMPUS_NORD;
        Optional<Edifici> edifici = client.getEdificiByCodiAndCodiCampus(codiEdifici, codiCampus);

        assertThat(edifici.get(), hasProperty("codi", equalTo(codiEdifici)));
        assertThat(edifici.get(), hasProperty("campus", hasProperty("codi", equalTo(codiCampus))));
    }

    @Test
    public void getEstats() {
        List<Estat> estats = client.getEstats();

        assertThat(estats, not(empty()));
    }

    @Test
    public void getEstatByCodi() {
        String codi = CODI_ESTAT_BAIXA;
        Optional<Estat> estat = client.getEstatByCodi(codi);

        assertThat(estat.get(), hasProperty("codi", equalTo(codi)));
    }

    @Test
    public void getEstatsByNom() {
        List<Estat> estats = client.getEstatsByNom(NOM_ESTAT_BAIXA);

        assertThat(estats, not(empty()));
    }

    @Test
    public void getEstatById() {
        long idEstat = ID_ESTAT_EN_GARANTIA_CENTRALITZAT;
        Optional<Estat> estat = client.getEstatById(idEstat);

        assertThat(estat.get().getIdEstat(), is(idEstat));
    }

    @Test
    public void getMarques() {
        List<Marca> marques = client.getMarques();

        assertThat(marques, not(empty()));
    }

    @Test
    public void getMarquesByNom() {
        List<Marca> marques = client.getMarquesByNom(NOM_MARCA_IBM);

        assertThat(marques, not(empty()));
        assertThat(marques, everyItem(hasProperty("nom", equalToIgnoringCase(NOM_MARCA_IBM))));
    }

    @Test
    public void getMarcaById() {
        Optional<Marca> marca = client.getMarcaById(ID_MARCA_IBM);
        assertThat(marca.get().getIdMarca(), is(ID_MARCA_IBM));
    }

    @Test
    public void getTipusInfraestructura() {
        List<TipusInfraestructura> tipus = client.getTipusInfraestructura();

        assertThat(tipus, not(empty()));
    }

    @Test
    public void getTipusInfraestructuraByCategoria() {
        List<TipusInfraestructura> tipus = client.getTipusInfraestructuraByCategoria(ID_CATEGORIA_SERVIDOR);

        assertThat(tipus, not(empty()));
    }

    @Test
    public void getTipusInfraestructuraByCodi() {
        Optional<TipusInfraestructura> tipus = client.getTipusInfraestructuraBycodi(CODI_TIPUS_IMPRESSORA);

        assertThat(tipus.get().getCodi(), is(CODI_TIPUS_IMPRESSORA));
    }

    @Test
    public void getTipusInfraestructuraByNom() {
        List<TipusInfraestructura> tipus = client.getTipusInfraestructuraByNom(NOM_TIPUS_IMPRESSORA);

        assertThat(tipus, not(empty()));
    }

    @Test
    public void getTipusInfraestructuraById() {
        Optional<TipusInfraestructura> tipus = client.getTipusInfraestructuraById(ID_TIPUS_IMPRESSORA);

        assertThat(tipus.get().getIdTipus(), is(ID_TIPUS_IMPRESSORA));
    }

    @Test
    public void getTipusUs() {
        List<TipusUs> tipus = client.getTipusUs();

        assertThat(tipus, not(empty()));
    }

    @Test
    public void getTipusUsByUnitat() {
        Unitat unitat = new Unitat(ID_UNITAT_SERVEI_GESTIO_ACADEMICA);
        List<TipusUs> tipus = client.getTipusUsByUnitat(ID_UNITAT_SERVEI_GESTIO_ACADEMICA);

        assertThat(tipus, not(empty()));
        assertThat(tipus, everyItem(hasProperty("unitat", equalTo(unitat))));
    }

    @Test
    public void getTipusUsById() {
        Optional<TipusUs> tipus = client.getTipusUsById(ID_TIPUS_US_DOCENCIA);

        assertThat(tipus.get().getIdTipusUs(), equalTo(ID_TIPUS_US_DOCENCIA));
    }

    @Test
    public void getTipusXarxa() {
        List<TipusXarxa> tipus = client.getTipusXarxa();

        assertThat(tipus, not(empty()));
    }

    @Test
    public void getTipusXarxaById() {
        Optional<TipusXarxa> tipus = client.getTipusXarxaById(ID_TIPUS_XARXA_TRONCAL);

        assertThat(tipus.get().getIdTipusXarxa(), equalTo(ID_TIPUS_XARXA_TRONCAL));
    }

    @Test
    public void getUnitats() {
        List<Unitat> unitats = client.getUnitats();

        assertThat(unitats, not(empty()));
    }

    @Test
    public void getUnitatByIdentificador() {
        List<Unitat> unitats = client.getUnitatsByIdentificador(IDENTIFICADOR_UNITAT_UTGAC);

        assertThat(unitats, not(empty()));
        assertThat(unitats, everyItem(hasProperty("identificador", equalTo(IDENTIFICADOR_UNITAT_UTGAC))));
    }

    @Test
    public void getUnitatsByNom() {
        List<Unitat> unitats = client.getUnitatsByNom(NOM_UNITAT_UTGAC);

        assertThat(unitats, not(empty()));
        assertThat(unitats, everyItem(hasProperty("nom", equalToIgnoringCase(NOM_UNITAT_UTGAC))));
    }

    @Test
    public void getUnitatById() {
        Optional<Unitat> unitat = client.getUnitatById(ID_UNITAT_UTGAC);

        assertThat(unitat.get().getIdUnitat(), is(ID_UNITAT_UTGAC));
    }

    @Test
    public void getUnitatsByNomAndIdentificadorAndCodi() {
        String nom = NOM_UNITAT_UTGAC;
        String identificador = IDENTIFICADOR_UNITAT_UTGAC;
        String codi = CODI_UNITAT_UTGAC;
        List<Unitat> unitats = client.getUnitatsByNomAndIdentificadorAndCodi(nom, identificador, codi);

        assertThat(unitats, not(empty()));
        assertThat(unitats, everyItem(allOf( //
                hasProperty("nom", equalToIgnoringCase(nom)), //
                hasProperty("identificador", is(identificador)), //
                hasProperty("codiUnitat", is(codi)))));
    }

    @Test
    public void getInfraestructuraById() {
        Optional<Infraestructura> infraestructura = client.getInfraestructuraById(ID_INFRAESTRUCTURA, false);

        assertThat(infraestructura.get().getIdentificador(), equalTo(ID_INFRAESTRUCTURA));
    }

    @Test
    public void getInfraestructuraByIdNotFound() {
        try {
            client.getInfraestructuraById(0, false);
            fail("Hauria s'haver llançat una excepció");
        } catch (EquipsTicClientException e) {
            assertEquals(HttpStatus.NOT_FOUND, e.getStatus().orElse(null));
        }
    }

    @Test
    public void getInfraestructuraByMarcaAndNumeroDeSerie() {
        long idMarca = 2;
        String sn = "7MQ48Z1";
        Optional<Infraestructura> infraestructura = client.getInfraestructuraByMarcaAndNumeroDeSerie(idMarca, sn,
                false);

        assertThat(infraestructura.get().getMarca().getIdMarca(), is(idMarca));
        assertThat(infraestructura.get().getNumeroSerie(), is(sn));
    }

    @Test
    public void getInfraestructuresByUnitat() {
        List<Infraestructura> infraestructures = client.getInfraestructuresByUnitat(ID_UNITAT_UTGAC);

        assertThat(infraestructures, not(empty()));
        assertThat(infraestructures,
                everyItem(hasProperty("unitat", hasProperty("idUnitat", equalTo(ID_UNITAT_UTGAC)))));
    }

    @Test
    public void altaInfraestructura() {
        Infraestructura i = InfraestructuraFixtures.infraestructuraFixture();
        Infraestructura creada = null;
        try {
            creada = client.altaInfraestructura(i);

            assertEquals(i.getMarca(), creada.getMarca());
            assertEquals(i.getNumeroSerie(), creada.getNumeroSerie());
            assertEquals("dataCompra", i.getDataCompra(), creada.getDataCompra());
            assertEquals("dataFinalGarantia", i.getDataFinalGarantia(), creada.getDataFinalGarantia());
            assertThat(creada.getIdentificador(), is(not(0)));
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
            assertEquals(HttpStatus.NOT_FOUND, e.getStatus().orElse(null));
        }
    }

    @Test
    public void modificaInfraestructura() {
        Infraestructura fixture = InfraestructuraFixtures.infraestructuraFixture();
        Infraestructura i = null;
        try {
            i = client.altaInfraestructura(fixture);
            i.setDataTramitFactura(InfraestructuraFixtures.dateFixture(2017, Month.JANUARY, 2));

            Infraestructura nova = client.modificaInfraestructura(i);

            assertThat("L'identificador no hauria de canviar", nova.getIdentificador(), equalTo(i.getIdentificador()));
            assertThat(nova.getDataFinalGarantia(), equalTo(fixture.getDataFinalGarantia()));
            assertThat(nova.getDataTramitFactura(), equalTo(i.getDataTramitFactura()));
        } finally {
            if (i != null) {
                client.baixaInfraestructura(i.getIdentificador());
            }
        }
    }

    @Test
    public void getSistemesOperatius() {
        List<SistemaOperatiu> sistemesOperatius = client.getSistemesOperatius();

        assertThat(sistemesOperatius, not(empty()));
    }

    @Test
    public void getSistemesOperatiusByCategoria() {
        List<SistemaOperatiu> sistemesOperatius = client.getSistemesOperatiusByCategoria(ID_CATEGORIA_SERVIDOR);

        assertThat(sistemesOperatius, not(empty()));
        assertTrue(sistemesOperatius.parallelStream()
                .allMatch(so -> so.getCategoriaInfraestructura().getIdCategoria() == ID_CATEGORIA_SERVIDOR));
    }

    @Test
    public void getSistemesOperatiusByCodi() {
        List<SistemaOperatiu> sistemesOperatius = client.getSistemesOperatiusByCodi("LINUX");

        assertThat(sistemesOperatius, not(empty()));
        assertTrue(sistemesOperatius.parallelStream()
                .allMatch(so -> StringUtils.containsIgnoreCase(so.getCodi(), "LINUX")));
    }

    @Test
    public void getSistemesOperatiusByNom() {
        List<SistemaOperatiu> sistemesOperatius = client.getSistemesOperatiusByNom("Linux");

        assertThat(sistemesOperatius, not(empty()));
        assertTrue(sistemesOperatius.parallelStream()
                .allMatch(so -> StringUtils.containsIgnoreCase(so.getNom(), "Linux")));
    }

    @Test
    public void getSistemaOperatiuById() {
        Optional<SistemaOperatiu> so = client.getSistemaOperatiuById(ID_SISTEMA_OPERATIU_LINUX);

        assertThat(so.get().getIdSistemaOperatiu(), equalTo(ID_SISTEMA_OPERATIU_LINUX));
    }

    @Test
    public void getUsuariInfraestructuraById() {
        Optional<UsuariInfraestructura> u = client.getUsuariInfraestructura(1L);

        assertThat(u.get().getIdUsuariInfraestructura(), is(1L));
    }

    @Test
    public void getUsuarisInfraestructura() {
        List<UsuariInfraestructura> list = client.getUsuarisInfraestructura();

        assertThat(list, hasItem(hasProperty("nomUsuari", equalTo("angel.aguilera"))));
    }

    @Test
    public void getUsuarisInfraestructuraByNom() {
        String nom = "angel.aguilera";
        List<UsuariInfraestructura> list = client.getUsuarisInfraestructuraByNom(nom);

        assertThat(list, not(empty()));
        assertThat(list, everyItem(anyOf( //
                hasProperty("nom", containsSubstringIgnoringCase(nom)),
                hasProperty("nomUsuari", containsSubstringIgnoringCase(nom)))));
    }

    private Matcher<String> containsSubstringIgnoringCase(String searchValue) {
        return new CustomTypeSafeMatcher<String>("a string containing \"" + searchValue + "\" (ignoring case)") {

            @Override
            protected boolean matchesSafely(String item) {
                return StringUtils.containsIgnoreCase(item, searchValue);
            }
        };
    }

}
