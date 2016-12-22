package edu.upc.caminstech.equipstic.client;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

import edu.upc.caminstech.equipstic.Ambit;
import edu.upc.caminstech.equipstic.Campus;
import edu.upc.caminstech.equipstic.Categoria;
import edu.upc.caminstech.equipstic.Edifici;
import edu.upc.caminstech.equipstic.Estat;
import edu.upc.caminstech.equipstic.Estat.TipusEstat;
import edu.upc.caminstech.equipstic.Infraestructura;
import edu.upc.caminstech.equipstic.Marca;
import edu.upc.caminstech.equipstic.SistemaOperatiu;
import edu.upc.caminstech.equipstic.TipusInfraestructura;
import edu.upc.caminstech.equipstic.TipusXarxa;
import edu.upc.caminstech.equipstic.Unitat;

/**
 * Helper class to create test fixtures.
 */
public class EquipsTicFixtures {

    public static Infraestructura infraestructuraFixture() {
        Infraestructura i = new Infraestructura();

        i.setAmbit(ambitFixture());
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
        i.setEdifici(edificiFixture());
        i.setEstat(estatFixture());
        i.setEstatValidacio(estatValidacioFixture());
        i.setIdentificador(987654321);
        i.setImportCompra(BigDecimal.valueOf(1250.75));
        i.setLocalitzacio("localitzacio exemple");
        i.setMarca(marcaFixture());
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
        i.setSistemaOperatiu(sistemaOperatiuFixture());
        i.setSla("sla exemple");
        i.setTipusInfraestructura(tipusInfraestructuraFixture());
        i.setTipusUs(null);
        i.setTipusXarxa(tipusXarxaFixture());
        i.setUnitat(unitatFixture());
        i.setUnitatDestinataria(unitatFixture());
        i.setUnitatGestora(unitatFixture());
        i.setUsuari(null);
        i.setUsuariInfraestructura(null);

        return i;
    }

    public static Categoria categoriaFixture() {
        return new Categoria(3, "Equip de treball", "EQUIP_TREBALL");
    }

    public static Ambit ambitFixture() {
        return new Ambit(6, "Lloc de treball PAS", "LT_PAS", categoriaFixture());
    }

    public static Marca marcaFixture() {
        return new Marca(2, "DELL");
    }

    public static Date dateFixture(int year, Month month, int dayOfMonth) {
        LocalDate localDate = LocalDate.of(year, month, dayOfMonth);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Campus campusFixture() {
        return new Campus(4, "CAMPUS NORD", "ND");
    }

    public static Edifici edificiFixture() {
        return new Edifici(36, "EDIFICI B2", "B2", "C. JORDI GIRONA, 1-3", "BARCELONA", "08034", campusFixture());
    }

    public static Estat estatFixture() {
        return new Estat(9, "En manteniment NO centralitzat", TipusEstat.INFRAESTRUCTURA, "MANTENIMENT_NO_CENTRALITZAT",
                false);
    }

    public static Estat estatValidacioFixture() {
        return new Estat(6, "Pendent de validacio", TipusEstat.VALIDACIO, "PENDENT_VALIDACIO", false);
    }

    public static SistemaOperatiu sistemaOperatiuFixture() {
        return new SistemaOperatiu(9, "Linux", "LINUX", categoriaFixture());
    }

    public static TipusInfraestructura tipusInfraestructuraFixture() {
        return new TipusInfraestructura(42, "Estacio de treball", "ESTACIO_TREBALL", true, categoriaFixture());
    }

    public static TipusXarxa tipusXarxaFixture() {
        return new TipusXarxa(3, "Local", "LOCAL");
    }

    public static Unitat unitatFixture() {
        return new Unitat(79, "171", "UTGAC", "Utg de l'Ambit de Camins");
    }

}
