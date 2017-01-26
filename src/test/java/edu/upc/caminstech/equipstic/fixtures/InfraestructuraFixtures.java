package edu.upc.caminstech.equipstic.fixtures;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

import edu.upc.caminstech.equipstic.Ambit;
import edu.upc.caminstech.equipstic.Edifici;
import edu.upc.caminstech.equipstic.Estat;
import edu.upc.caminstech.equipstic.Infraestructura;
import edu.upc.caminstech.equipstic.Marca;
import edu.upc.caminstech.equipstic.SistemaOperatiu;
import edu.upc.caminstech.equipstic.TipusInfraestructura;
import edu.upc.caminstech.equipstic.TipusXarxa;
import edu.upc.caminstech.equipstic.Unitat;

/**
 * Helper class to create test fixtures.
 */
public class InfraestructuraFixtures {

    public static final long ID_CATEGORIA_EQUIP_DE_TREBALL = 3L;
    public static final long ID_AMBIT_LLOC_DE_TREBALL = 6L;
    public static final long ID_MARCA_DELL = 2;
    public static final long ID_EDIFICI_B2 = 36;
    public static final long ID_UNITAT_UTGAC = 79;
    public static final long ID_TIPUS_XARXA_LOCAL = 3;
    public static final long ID_SISTEMA_OPERATIU_LINUX = 9;
    public static final long ID_TIPUS_INFRAESTRUCTURA_ESTACIO_DE_TREBALL = 42;
    public static final long ID_ESTAT_PENDENT_VALIDACIO = 6;
    public static final long ID_ESTAT_MANTENIMENT_NO_CENTRALITZAT = 9;

    public static Infraestructura infraestructuraFixture() {
        Infraestructura i = new Infraestructura();

        i.setAmbit(new Ambit(ID_AMBIT_LLOC_DE_TREBALL));
        i.setCapacitatTotalGb("capacitat total exemple");
        i.setConfiguracioHardware("configuracio exemple");
        i.setDataAltaManteniment(dateFixture(2016, Month.JANUARY, 1));
        i.setDataBaixaManteniment(dateFixture(2016, Month.JANUARY, 2));
        i.setDataCarrega(dateFixture(2016, Month.JANUARY, 3));
        i.setDataCompra(dateFixture(2016, Month.JANUARY, 4));
        i.setDataCreacio(dateFixture(2016, Month.JANUARY, 5));
        i.setDataDarreraModificacio(dateFixture(2016, Month.JANUARY, 6));
        i.setDataEntrega(dateFixture(2016, Month.JANUARY, 7));
        i.setDataFinalGarantia(dateFixture(2016, Month.JANUARY, 8));
        i.setDataTramitFactura(dateFixture(2016, Month.JANUARY, 9));
        i.setDiscs("discs exemple");
        i.setEdifici(new Edifici(ID_EDIFICI_B2));
        i.setEstat(new Estat(ID_ESTAT_MANTENIMENT_NO_CENTRALITZAT));
        i.setEstatValidacio(new Estat(ID_ESTAT_PENDENT_VALIDACIO));
        i.setIdentificador(0);
        i.setImportCompra(BigDecimal.valueOf(1250.75));
        i.setLocalitzacio("localitzacio exemple");
        i.setMarca(new Marca(ID_MARCA_DELL));
        i.setModel("model exemple");
        i.setModelCpu("model cpu exemple");
        i.setNomDns("nom-dns-exemple");
        i.setNomUsuariInfraestructura("nom usuari infraestructura exemple");
        i.setNumeroAd("numero ad exemple");
        i.setNumeroCpus(4);
        i.setNumeroInventariUpc("numero inventari upc exemple");
        i.setNumeroPorts(5);
        i.setNumeroSerie("numero serie exemple");
        i.setObservacions("observacions exemple");
        i.setProveidorCompra("proveidor compra exemple");
        i.setProveidorManteniment("proveidor manteniment exemple");
        i.setSistemaOperatiu(new SistemaOperatiu(ID_SISTEMA_OPERATIU_LINUX));
        i.setSla("sla exemple");
        i.setTipusInfraestructura(new TipusInfraestructura(ID_TIPUS_INFRAESTRUCTURA_ESTACIO_DE_TREBALL));
        i.setTipusUs(null);
        i.setTipusXarxa(new TipusXarxa(ID_TIPUS_XARXA_LOCAL));
        i.setUnitat(unitatFixture());
        i.setUnitatDestinataria(unitatFixture());
        i.setUnitatGestora(unitatFixture());
        i.setUsuari(null);
        i.setUsuariInfraestructura(null);

        return i;
    }

    public static Date dateFixture(int year, Month month, int dayOfMonth) {
        LocalDate localDate = LocalDate.of(year, month, dayOfMonth);
        return Date.from(localDate.atStartOfDay(ZoneId.of("Europe/Madrid")).toInstant());
    }

    private static Unitat unitatFixture() {
        return new Unitat(ID_UNITAT_UTGAC);
    }

}
