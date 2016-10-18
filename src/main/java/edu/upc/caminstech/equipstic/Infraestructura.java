package edu.upc.caminstech.equipstic;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * TODO: Utilitzar el patró Builder crear instàncies, tenint en compte quins
 * atributs són opcionals i quins no, segons el tipus d'infraestructura.
 */
public class Infraestructura {

    // atributs obligatoris

    private long identificador;
    private String nomDns; // nom
    private String numeroSerie;
    private TipusInfraestructura tipusInfraestructura; // inclou la categoria
    private Marca marca;
    private String model;
    private Date dataCompra;
    private BigDecimal importCompra;
    private Estat estat;
    private Unitat unitat;
    private Edifici edifici; // inclou el campus
    private Date dataFinalGarantia;

    private Date dataCarrega;
    private Estat estatValidacio;
    private Date dataCreacio;
    private Date dataDarreraModificacio;
    private Usuari usuari;
    private String causaCanviWorkflow;
    private Date dataCanviWorkflow;
    private Estat estatAnteriorWorkflow;

    // atributs opcionals (generics)

    private String sla;
    private String proveidorCompra;
    private String proveidorManteniment;
    private Date dataTramitFactura;
    private Date dataEntrega;
    private Date dataAltaManteniment;
    private Date dataBaixaManteniment;
    private String numeroAd;
    private String localitzacio;
    private String numeroInventariUpc;
    private Ambit ambit;
    private TipusUs tipusUs;
    private Unitat unitatGestora;
    private String observacions;
    private TipusXarxa tipusXarxa;
    private int numeroPorts;
    private String configuracioHardware;

    // atributs obligatoris (només per a alguns TipusInfraestructura)

    private String modelCpu;
    private Integer numeroCpus;
    private String discs;
    private String capacitatTotalGb;

    public long getIdentificador() {
        return identificador;
    }

    public void setIdentificador(long identificador) {
        this.identificador = identificador;
    }

    public String getCapacitatTotalGb() {
        return capacitatTotalGb;
    }

    public void setCapacitatTotalGb(String capacitatTotalGb) {
        this.capacitatTotalGb = capacitatTotalGb;
    }

    public Date getDataAltaManteniment() {
        return dataAltaManteniment;
    }

    public void setDataAltaManteniment(Date dataAltaManteniment) {
        this.dataAltaManteniment = dataAltaManteniment;
    }

    public Date getDataBaixaManteniment() {
        return dataBaixaManteniment;
    }

    public void setDataBaixaManteniment(Date dataBaixaManteniment) {
        this.dataBaixaManteniment = dataBaixaManteniment;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Date getDataFinalGarantia() {
        return dataFinalGarantia;
    }

    public void setDataFinalGarantia(Date dataFinalGarantia) {
        this.dataFinalGarantia = dataFinalGarantia;
    }

    public Date getDataTramitFactura() {
        return dataTramitFactura;
    }

    public void setDataTramitFactura(Date dataTramitFactura) {
        this.dataTramitFactura = dataTramitFactura;
    }

    public String getDiscs() {
        return discs;
    }

    public void setDiscs(String discs) {
        this.discs = discs;
    }

    public Edifici getEdifici() {
        return edifici;
    }

    public void setEdifici(Edifici edifici) {
        this.edifici = edifici;
    }

    public BigDecimal getImportCompra() {
        return importCompra;
    }

    public void setImportCompra(BigDecimal importCompra) {
        this.importCompra = importCompra;
    }

    public String getLocalitzacio() {
        return localitzacio;
    }

    public void setLocalitzacio(String localitzacio) {
        this.localitzacio = localitzacio;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModelCpu() {
        return modelCpu;
    }

    public void setModelCpu(String modelCpu) {
        this.modelCpu = modelCpu;
    }

    public String getNomDns() {
        return nomDns;
    }

    public void setNomDns(String nomDns) {
        this.nomDns = nomDns;
    }

    public String getNumeroAd() {
        return numeroAd;
    }

    public void setNumeroAd(String numeroAd) {
        this.numeroAd = numeroAd;
    }

    public Integer getNumeroCpus() {
        return numeroCpus;
    }

    public void setNumeroCpus(Integer numeroCpus) {
        this.numeroCpus = numeroCpus;
    }

    public String getNumeroInventariUpc() {
        return numeroInventariUpc;
    }

    public void setNumeroInventariUpc(String numeroInventariUpc) {
        this.numeroInventariUpc = numeroInventariUpc;
    }

    public int getNumeroPorts() {
        return numeroPorts;
    }

    public void setNumeroPorts(int numeroPorts) {
        this.numeroPorts = numeroPorts;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getConfiguracioHardware() {
        return configuracioHardware;
    }

    public void setConfiguracioHardware(String configuracioHardware) {
        this.configuracioHardware = configuracioHardware;
    }

    public String getObservacions() {
        return observacions;
    }

    public void setObservacions(String observacions) {
        this.observacions = observacions;
    }

    public String getProveidorCompra() {
        return proveidorCompra;
    }

    public void setProveidorCompra(String proveidorCompra) {
        this.proveidorCompra = proveidorCompra;
    }

    public String getSla() {
        return sla;
    }

    public void setSla(String sla) {
        this.sla = sla;
    }

    public String getProveidorManteniment() {
        return proveidorManteniment;
    }

    public void setProveidorManteniment(String proveidorManteniment) {
        this.proveidorManteniment = proveidorManteniment;
    }

    public Date getDataCarrega() {
        return dataCarrega;
    }

    public void setDataCarrega(Date dataCarrega) {
        this.dataCarrega = dataCarrega;
    }

    public Estat getEstatValidacio() {
        return estatValidacio;
    }

    public void setEstatValidacio(Estat estatValidacio) {
        this.estatValidacio = estatValidacio;
    }

    public Estat getEstat() {
        return estat;
    }

    public void setEstat(Estat estat) {
        this.estat = estat;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Ambit getAmbit() {
        return ambit;
    }

    public void setAmbit(Ambit ambit) {
        this.ambit = ambit;
    }

    public TipusInfraestructura getTipusInfraestructura() {
        return tipusInfraestructura;
    }

    public void setTipusInfraestructura(TipusInfraestructura tipusInfraestructura) {
        this.tipusInfraestructura = tipusInfraestructura;
    }

    public TipusUs getTipusUs() {
        return tipusUs;
    }

    public void setTipusUs(TipusUs tipusUs) {
        this.tipusUs = tipusUs;
    }

    public TipusXarxa getTipusXarxa() {
        return tipusXarxa;
    }

    public void setTipusXarxa(TipusXarxa tipusXarxa) {
        this.tipusXarxa = tipusXarxa;
    }

    public Unitat getUnitatGestora() {
        return unitatGestora;
    }

    public void setUnitatGestora(Unitat unitatGestora) {
        this.unitatGestora = unitatGestora;
    }

    public Unitat getUnitat() {
        return unitat;
    }

    public void setUnitat(Unitat unitat) {
        this.unitat = unitat;
    }

    public Date getDataCreacio() {
        return dataCreacio;
    }

    public void setDataCreacio(Date dataCreacio) {
        this.dataCreacio = dataCreacio;
    }

    public Date getDataDarreraModificacio() {
        return dataDarreraModificacio;
    }

    public void setDataDarreraModificacio(Date dataDarreraModificacio) {
        this.dataDarreraModificacio = dataDarreraModificacio;
    }

    public Usuari getUsuari() {
        return usuari;
    }

    public void setUsuari(Usuari usuari) {
        this.usuari = usuari;
    }

    public String getCausaCanviWorkflow() {
        return causaCanviWorkflow;
    }

    public void setCausaCanviWorkflow(String causaCanviWorkflow) {
        this.causaCanviWorkflow = causaCanviWorkflow;
    }

    public Date getDataCanviWorkflow() {
        return dataCanviWorkflow;
    }

    public void setDataCanviWorkflow(Date dataCanviWorkflow) {
        this.dataCanviWorkflow = dataCanviWorkflow;
    }

    public Estat getEstatAnteriorWorkflow() {
        return estatAnteriorWorkflow;
    }

    public void setEstatAnteriorWorkflow(Estat estatAnteriorWorkflow) {
        this.estatAnteriorWorkflow = estatAnteriorWorkflow;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Infraestructura)) {
            return false;
        }
        return this.identificador == ((Infraestructura) obj).identificador;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(identificador);
    }
}
