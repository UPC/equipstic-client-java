package edu.upc.caminstech.equipstic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Date;
import java.util.TimeZone;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.upc.caminstech.equipstic.Estat.TipusEstat;
import edu.upc.caminstech.equipstic.fixtures.InfraestructuraFixtures;

class InfraestructuraTests {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private Infraestructura infra;
    private Date data = new Date();

    @BeforeEach
    void setUp() throws Exception {
        objectMapper.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
        infra = new Infraestructura();
    }

    @Test
    void testGetIdentificador() {
        assertEquals(0, infra.getIdentificador());
        infra.setIdentificador(3);
        assertEquals(3, infra.getIdentificador());
    }

    @Test
    void testGetCapacitatTotalGb() {
        infra.setCapacitatTotalGb("300");
        assertEquals("300", infra.getCapacitatTotalGb());
    }

    @Test
    void testGetDataAltaManteniment() {
        infra.setDataAltaManteniment(data);
        assertEquals(data, infra.getDataAltaManteniment());
    }

    @Test
    void testGetDataBaixaManteniment() {
        infra.setDataBaixaManteniment(data);
        assertEquals(data, infra.getDataBaixaManteniment());
    }

    @Test
    void testGetDataCompra() {
        infra.setDataCompra(data);
        assertEquals(data, infra.getDataCompra());
    }

    @Test
    void testGetDataEntrega() {
        infra.setDataEntrega(data);
        assertEquals(data, infra.getDataEntrega());
    }

    @Test
    void testGetDataFinalGarantia() {
        infra.setDataFinalGarantia(data);
        assertEquals(data, infra.getDataFinalGarantia());
    }

    @Test
    void testGetDataTramitFactura() {
        infra.setDataTramitFactura(data);
        assertEquals(data, infra.getDataTramitFactura());
    }

    @Test
    void testGetDiscs() {
        infra.setDiscs("2 discs");
        assertEquals("2 discs", infra.getDiscs());
    }

    @Test
    void testGetEdifici() {
        Edifici edifici = new Edifici(1, null, null, null, null, null, null);
        infra.setEdifici(edifici);
        assertEquals(edifici, infra.getEdifici());
    }

    @Test
    void testGetImportCompra() {
        BigDecimal importCompra = BigDecimal.valueOf(1254.56);
        infra.setImportCompra(importCompra);
        assertEquals(0, importCompra.compareTo(infra.getImportCompra()));
    }

    @Test
    void testGetLocalitzacio() {
        infra.setLocalitzacio("loc");
        assertEquals("loc", infra.getLocalitzacio());
    }

    @Test
    void testGetModel() {
        infra.setModel("model");
        assertEquals("model", infra.getModel());
    }

    @Test
    void testGetModelCpu() {
        infra.setModelCpu("modelCPU");
        assertEquals("modelCPU", infra.getModelCpu());
    }

    @Test
    void testGetNom() {
        infra.setNom("nom");
        assertEquals("nom", infra.getNom());
    }

    @Test
    void testGetNomDns() {
        infra.setNomDns("nom.example.com");
        assertEquals("nom.example.com", infra.getNomDns());
    }

    @Test
    void testGetNumeroAd() {
        infra.setNumeroAd("numeroAD");
        assertEquals("numeroAD", infra.getNumeroAd());
    }

    @Test
    void testGetNumeroCpus() {
        infra.setNumeroCpus(4);
        assertEquals(4, infra.getNumeroCpus());
    }

    @Test
    void testGetNumeroInventariUpc() {
        infra.setNumeroInventariUpc("1234");
        assertEquals("1234", infra.getNumeroInventariUpc());
    }

    @Test
    void testGetNumeroPorts() {
        Integer ports = 48;
        infra.setNumeroPorts(ports);
        assertEquals(ports, infra.getNumeroPorts());
    }

    @Test
    void testGetNumeroSerie() {
        infra.setNumeroSerie("9876");
        assertEquals("9876", infra.getNumeroSerie());
    }

    @Test
    void testGetConfiguracioHardware() {
        infra.setConfiguracioHardware("config");
        assertEquals("config", infra.getConfiguracioHardware());
    }

    @Test
    void testGetObservacions() {
        infra.setObservacions("obs");
        assertEquals("obs", infra.getObservacions());
    }

    @Test
    void testGetProveidorCompra() {
        infra.setProveidorCompra("prov");
        assertEquals("prov", infra.getProveidorCompra());
    }

    @Test
    void testGetSla() {
        infra.setSla("sla");
        assertEquals("sla", infra.getSla());
    }

    @Test
    void testGetProveidorManteniment() {
        infra.setProveidorManteniment("provmant");
        assertEquals("provmant", infra.getProveidorManteniment());
    }

    @Test
    void testGetDataCarrega() {
        infra.setDataCarrega(data);
        assertEquals(data, infra.getDataCarrega());
    }

    @Test
    void testGetEstatValidacio() {
        Estat estat = estatFixture();
        infra.setEstatValidacio(estat);
        assertEquals(estat, infra.getEstatValidacio());
    }

    @Test
    void testGetEstat() {
        Estat estat = estatFixture();
        infra.setEstat(estat);
        assertEquals(estat, infra.getEstat());
    }

    @Test
    void testGetMarca() {
        Marca marca = new Marca(1, "ACME");
        infra.setMarca(marca);
        assertEquals(marca, infra.getMarca());
    }

    @Test
    void testGetAmbit() {
        Categoria cat = new Categoria(10, "Nom categoria", "CODI_CATEGORIA");
        Ambit ambit = new Ambit(1, "AMBIT", "CODI_AMBIT", cat);
        infra.setAmbit(ambit);
        assertEquals(ambit, infra.getAmbit());
    }

    @Test
    void testGetTipusInfraestructura() {
        TipusInfraestructura tipus = new TipusInfraestructura(1, "TIPUS", "T", false, null);
        infra.setTipusInfraestructura(tipus);
        assertEquals(tipus, infra.getTipusInfraestructura());
    }

    @Test
    void testGetTipusUs() {
        TipusUs tipus = new TipusUs(1, "TIPUS_US", null);
        infra.setTipusUs(tipus);
        assertEquals(tipus, infra.getTipusUs());
    }

    @Test
    void testGetTipusXarxa() {
        TipusXarxa tipus = new TipusXarxa(1, "TIPUS_XARXA", "TX");
        infra.setTipusXarxa(tipus);
        assertEquals(tipus, infra.getTipusXarxa());
    }

    @Test
    void testGetUnitatGestora() {
        Unitat unitat = unitatFixture();
        infra.setUnitatGestora(unitat);
        assertEquals(unitat, infra.getUnitatGestora());
    }

    @Test
    void testGetUnitat() {
        Unitat unitat = unitatFixture();
        infra.setUnitat(unitat);
        assertEquals(unitat, infra.getUnitat());
    }

    @Test
    void testGetDataCreacio() {
        infra.setDataCreacio(data);
        assertEquals(data, infra.getDataCreacio());
    }

    @Test
    void testGetDataDarreraModificacio() {
        infra.setDataDarreraModificacio(data);
        assertEquals(data, infra.getDataDarreraModificacio());
    }

    @Test
    void testGetUsuari() {
        Usuari usuari = new Usuari(1, null, null, null, null, null, null, null, null, null, null, null, null, null);
        infra.setUsuari(usuari);
        assertEquals(usuari, infra.getUsuari());
    }

    @Test
    void testGetCausaCanviWorkflow() {
        infra.setCausaCanviWorkflow("causa");
        assertEquals("causa", infra.getCausaCanviWorkflow());
    }

    @Test
    void testGetDataCanviWorkflow() {
        infra.setDataCanviWorkflow(data);
        assertEquals(data, infra.getDataCanviWorkflow());
    }

    @Test
    void testGetEstatAnteriorWorkflow() {
        Estat estat = estatFixture();
        infra.setEstatAnteriorWorkflow(estat);
        assertEquals(estat, infra.getEstatAnteriorWorkflow());
    }

    @Test
    void testEquals() {
        Infraestructura copia = new Infraestructura();
        copia.setIdentificador(infra.getIdentificador());
        assertEquals(copia.hashCode(), infra.hashCode());
    }

    @Test
    void testHashCode() {
        Infraestructura copia = new Infraestructura();
        copia.setIdentificador(infra.getIdentificador());
        assertEquals(copia.hashCode(), infra.hashCode());
    }

    @Test
    void testGetSistemaOperatiu() {
        SistemaOperatiu so = new SistemaOperatiu(2, null, null, null);
        infra.setSistemaOperatiu(so);
        assertEquals(so, infra.getSistemaOperatiu());
    }

    @Test
    void testSerializeDate() throws JsonProcessingException, JSONException {
        Infraestructura i = InfraestructuraFixtures.infraestructuraFixture();
        String expected = "{ \"dataCompra\": \"2016-01-04\" }";
        String actual = objectMapper.writeValueAsString(i);
        JSONAssert.assertEquals(expected, actual, false);
    }

    @Test
    void testSerializeUnitatUTG() throws JsonProcessingException, JSONException {
        Infraestructura i = InfraestructuraFixtures.infraestructuraFixture();
        String expected = "{ \"unitatUTG\": { \"idUnitat\": " + InfraestructuraFixtures.ID_UNITAT_UTGAC + " } }";
        String actual = objectMapper.writeValueAsString(i);
        JSONAssert.assertEquals(expected, actual, false);
    }

    @Test
    void testSerializeNomAndNomDns() throws JsonProcessingException, JSONException {
        Infraestructura i = InfraestructuraFixtures.infraestructuraFixture();
        String expected = "{ \"nom\": \"nom exemple\", \"nomDns\": \"nom.example.com\" }";
        String actual = objectMapper.writeValueAsString(i);
        JSONAssert.assertEquals(expected, actual, false);
    }

    private Estat estatFixture() {
        return new Estat(1, "ESTAT", TipusEstat.VALIDACIO, "E", "EST", true);
    }

    private Unitat unitatFixture() {
        return new Unitat(1, "U", "UNITAT", "Unitat", new Estat(11L), false);
    }

}
