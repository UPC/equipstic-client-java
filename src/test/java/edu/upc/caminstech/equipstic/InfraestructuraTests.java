package edu.upc.caminstech.equipstic;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.upc.caminstech.equipstic.Estat.TipusEstat;
import edu.upc.caminstech.equipstic.fixtures.InfraestructuraFixtures;

public class InfraestructuraTests {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private Infraestructura infra;
    private Date data = new Date();

    @Before
    public void setUp() throws Exception {
        objectMapper.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
        infra = new Infraestructura();
    }

    @Test
    public void testGetIdentificador() {
        assertEquals(0, infra.getIdentificador());
        infra.setIdentificador(3);
        assertEquals(3, infra.getIdentificador());
    }

    @Test
    public void testGetCapacitatTotalGb() {
        infra.setCapacitatTotalGb("300");
        assertEquals("300", infra.getCapacitatTotalGb());
    }

    @Test
    public void testGetDataAltaManteniment() {
        infra.setDataAltaManteniment(data);
        assertEquals(data, infra.getDataAltaManteniment());
    }

    @Test
    public void testGetDataBaixaManteniment() {
        infra.setDataBaixaManteniment(data);
        assertEquals(data, infra.getDataBaixaManteniment());
    }

    @Test
    public void testGetDataCompra() {
        infra.setDataCompra(data);
        assertEquals(data, infra.getDataCompra());
    }

    @Test
    public void testGetDataEntrega() {
        infra.setDataEntrega(data);
        assertEquals(data, infra.getDataEntrega());
    }

    @Test
    public void testGetDataFinalGarantia() {
        infra.setDataFinalGarantia(data);
        assertEquals(data, infra.getDataFinalGarantia());
    }

    @Test
    public void testGetDataTramitFactura() {
        infra.setDataTramitFactura(data);
        assertEquals(data, infra.getDataTramitFactura());
    }

    @Test
    public void testGetDiscs() {
        infra.setDiscs("2 discs");
        assertEquals("2 discs", infra.getDiscs());
    }

    @Test
    public void testGetEdifici() {
        Edifici edifici = new Edifici(1, null, null, null, null, null, null);
        infra.setEdifici(edifici);
        assertEquals(edifici, infra.getEdifici());
    }

    @Test
    public void testGetImportCompra() {
        BigDecimal importCompra = BigDecimal.valueOf(1254.56);
        infra.setImportCompra(importCompra);
        assertTrue(importCompra.compareTo(infra.getImportCompra()) == 0);
    }

    @Test
    public void testGetLocalitzacio() {
        infra.setLocalitzacio("loc");
        assertEquals("loc", infra.getLocalitzacio());
    }

    @Test
    public void testGetModel() {
        infra.setModel("model");
        assertEquals("model", infra.getModel());
    }

    @Test
    public void testGetModelCpu() {
        infra.setModelCpu("modelCPU");
        assertEquals("modelCPU", infra.getModelCpu());
    }

    @Test
    public void testGetNomDns() {
        infra.setNomDns("nomDNS");
        assertEquals("nomDNS", infra.getNomDns());
    }

    @Test
    public void testGetNumeroAd() {
        infra.setNumeroAd("numeroAD");
        assertEquals("numeroAD", infra.getNumeroAd());
    }

    @Test
    public void testGetNumeroCpus() {
        infra.setNumeroCpus(4);
        assertEquals(Integer.valueOf(4), infra.getNumeroCpus());
    }

    @Test
    public void testGetNumeroInventariUpc() {
        infra.setNumeroInventariUpc("1234");
        assertEquals("1234", infra.getNumeroInventariUpc());
    }

    @Test
    public void testGetNumeroPorts() {
        Integer ports = 48;
        infra.setNumeroPorts(ports);
        assertEquals(ports, infra.getNumeroPorts());
    }

    @Test
    public void testGetNumeroSerie() {
        infra.setNumeroSerie("9876");
        assertEquals("9876", infra.getNumeroSerie());
    }

    @Test
    public void testGetConfiguracioHardware() {
        infra.setConfiguracioHardware("config");
        assertEquals("config", infra.getConfiguracioHardware());
    }

    @Test
    public void testGetObservacions() {
        infra.setObservacions("obs");
        assertEquals("obs", infra.getObservacions());
    }

    @Test
    public void testGetProveidorCompra() {
        infra.setProveidorCompra("prov");
        assertEquals("prov", infra.getProveidorCompra());
    }

    @Test
    public void testGetSla() {
        infra.setSla("sla");
        assertEquals("sla", infra.getSla());
    }

    @Test
    public void testGetProveidorManteniment() {
        infra.setProveidorManteniment("provmant");
        assertEquals("provmant", infra.getProveidorManteniment());
    }

    @Test
    public void testGetDataCarrega() {
        infra.setDataCarrega(data);
        assertEquals(data, infra.getDataCarrega());
    }

    @Test
    public void testGetEstatValidacio() {
        Estat estat = new Estat(1, "ESTAT", TipusEstat.VALIDACIO, "E", true);
        infra.setEstatValidacio(estat);
        assertEquals(estat, infra.getEstatValidacio());
    }

    @Test
    public void testGetEstat() {
        Estat estat = new Estat(1, "ESTAT", TipusEstat.VALIDACIO, "E", true);
        infra.setEstat(estat);
        assertEquals(estat, infra.getEstat());
    }

    @Test
    public void testGetMarca() {
        Marca marca = new Marca(1, "ACME");
        infra.setMarca(marca);
        assertEquals(marca, infra.getMarca());
    }

    @Test
    public void testGetAmbit() {
        Categoria cat = new Categoria(10, "Nom categoria", "CODI_CATEGORIA");
        Ambit ambit = new Ambit(1, "AMBIT", "CODI_AMBIT", cat);
        infra.setAmbit(ambit);
        assertEquals(ambit, infra.getAmbit());
    }

    @Test
    public void testGetTipusInfraestructura() {
        TipusInfraestructura tipus = new TipusInfraestructura(1, "TIPUS", "T", false, null);
        infra.setTipusInfraestructura(tipus);
        assertEquals(tipus, infra.getTipusInfraestructura());
    }

    @Test
    public void testGetTipusUs() {
        TipusUs tipus = new TipusUs(1, "TIPUS_US", null);
        infra.setTipusUs(tipus);
        assertEquals(tipus, infra.getTipusUs());
    }

    @Test
    public void testGetTipusXarxa() {
        TipusXarxa tipus = new TipusXarxa(1, "TIPUS_XARXA", "TX");
        infra.setTipusXarxa(tipus);
        assertEquals(tipus, infra.getTipusXarxa());
    }

    @Test
    public void testGetUnitatGestora() {
        Unitat unitat = new Unitat(1, "U", "UNITAT", "Unitat");
        infra.setUnitatGestora(unitat);
        assertEquals(unitat, infra.getUnitatGestora());
    }

    @Test
    public void testGetUnitat() {
        Unitat unitat = new Unitat(1, "U", "UNITAT", "Unitat");
        infra.setUnitat(unitat);
        assertEquals(unitat, infra.getUnitat());
    }

    @Test
    public void testGetDataCreacio() {
        infra.setDataCreacio(data);
        assertEquals(data, infra.getDataCreacio());
    }

    @Test
    public void testGetDataDarreraModificacio() {
        infra.setDataDarreraModificacio(data);
        assertEquals(data, infra.getDataDarreraModificacio());
    }

    @Test
    public void testGetUsuari() {
        Usuari usuari = new Usuari(1, null, null, null, null, null, null, null, null, null, null, null, null, null);
        infra.setUsuari(usuari);
        assertEquals(usuari, infra.getUsuari());
    }

    @Test
    public void testGetCausaCanviWorkflow() {
        infra.setCausaCanviWorkflow("causa");
        assertEquals("causa", infra.getCausaCanviWorkflow());
    }

    @Test
    public void testGetDataCanviWorkflow() {
        infra.setDataCanviWorkflow(data);
        assertEquals(data, infra.getDataCanviWorkflow());
    }

    @Test
    public void testGetEstatAnteriorWorkflow() {
        Estat estat = new Estat(1, "ESTAT", TipusEstat.VALIDACIO, "E", true);
        infra.setEstatAnteriorWorkflow(estat);
        assertEquals(estat, infra.getEstatAnteriorWorkflow());
    }

    @Test
    public void testEquals() {
        Infraestructura copia = new Infraestructura();
        copia.setIdentificador(infra.getIdentificador());
        assertEquals(copia.hashCode(), infra.hashCode());
    }

    @Test
    public void testHashCode() {
        Infraestructura copia = new Infraestructura();
        copia.setIdentificador(infra.getIdentificador());
        assertEquals(copia.hashCode(), infra.hashCode());
    }

    @Test
    public void testGetSistemaOperatiu() {
        SistemaOperatiu so = new SistemaOperatiu(2, null, null, null);
        infra.setSistemaOperatiu(so);
        assertEquals(so, infra.getSistemaOperatiu());
    }

    @Test
    public void testSerializeDate() throws JsonProcessingException {
        Infraestructura i = InfraestructuraFixtures.infraestructuraFixture();
        String expected = "{ \"dataCompra\": \"2016-01-04\" }";
        String actual = objectMapper.writeValueAsString(i);
        JSONAssert.assertEquals(expected, actual, false);
    }
}
