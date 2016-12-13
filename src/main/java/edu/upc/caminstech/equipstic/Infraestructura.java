package edu.upc.caminstech.equipstic;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * TODO: Utilitzar el patró Builder per crear instàncies, tenint en compte quins
 * atributs són opcionals i quins no, segons el tipus d'infraestructura.
 */
public class Infraestructura {

    // atributs obligatoris (comuns)

    private long identificador;
    private String nomDns; // etiquetat com a "Nom" al formulari
    private String numeroSerie;
    private TipusInfraestructura tipusInfraestructura; // inclou la Categoria
    private Marca marca;
    private String model;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataCompra;
    private BigDecimal importCompra;
    private Estat estat;
    private Unitat unitat;
    private Ambit ambit; // etiquetat com a "Propòsit" al formulari
    private Edifici edifici; // inclou el Campus
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataFinalGarantia;

    // atributs obligatoris (per a alguns TipusInfraestructura)

    private String modelCpu;
    private Integer numeroCpus;
    private String discs;
    private String capacitatTotalGb;
    private SistemaOperatiu sistemaOperatiu;

    // atributs opcionals (comuns)

    private String sla;
    private String proveidorCompra;
    private String observacions;
    private Unitat unitatGestora; // etiquetat com a "Unitat TIC" al formulari
    private Unitat unitatDestinataria;
    private TipusUs tipusUs;
    private String numeroInventariUpc;
    private String numeroAd;
    private String localitzacio;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataAltaManteniment;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataBaixaManteniment;
    private String proveidorManteniment;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataEntrega;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataTramitFactura;

    // atributs opcionals (per a alguns TipusInfraestructura)

    private TipusXarxa tipusXarxa;
    private Integer numeroPorts;
    private String configuracioHardware;
    private Usuari usuariInfraestructura;
    private String nomUsuariInfraestructura;

    // atributs d'ús intern

    private Estat estatValidacio;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataCreacio;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataDarreraModificacio;
    private Usuari usuari;
    private String causaCanviWorkflow;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataCanviWorkflow;
    private Estat estatAnteriorWorkflow;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataCarrega;

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

    public Integer getNumeroPorts() {
        return numeroPorts;
    }

    public void setNumeroPorts(Integer numeroPorts) {
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

    public Unitat getUnitatDestinataria() {
        return unitatDestinataria;
    }

    public void setUnitatDestinataria(Unitat unitatDestinataria) {
        this.unitatDestinataria = unitatDestinataria;
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

    public Usuari getUsuariInfraestructura() {
        return usuariInfraestructura;
    }

    public void setUsuariInfraestructura(Usuari usuariInfraestructura) {
        this.usuariInfraestructura = usuariInfraestructura;
    }

    public SistemaOperatiu getSistemaOperatiu() {
        return sistemaOperatiu;
    }

    public void setSistemaOperatiu(SistemaOperatiu sistemaOperatiu) {
        this.sistemaOperatiu = sistemaOperatiu;
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

    public String getNomUsuariInfraestructura() {
        return nomUsuariInfraestructura;
    }

    public void setNomUsuariInfraestructura(String nomUsuariInfraestructura) {
        this.nomUsuariInfraestructura = nomUsuariInfraestructura;
    }

}
