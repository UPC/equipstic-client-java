package edu.upc.caminstech.equipstic;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Usuari {

    private final long idUsuari;
    private final String adreca;
    private final String carrec;
    private final String cognom1;
    private final String cognom2;
    private final Date dataCreacio;
    private final Date dataDarreraModificacio;
    private final String email;
    private final String mobil;
    private final String nom;
    private final String nomUsuari;
    private final String observacions;
    private final String telefon;
    private final Usuari usuariDarreraModificacio;

    /**
     * Crea un nou usuari.
     * <p>
     * Aquest constructor no és gaire còmode pel gran nombre de paràmetres, però
     * cal tenir en compte que la API no permet crear ni modificar usuaris, per
     * tant normalment no hauríeu de cridar directament aquest constructor des
     * de les vostres aplicacions.
     */
    @JsonCreator
    public Usuari(@JsonProperty("idUsuari") long idUsuari, //
            @JsonProperty(value = "nomUsuari", required = false) String nomUsuari, //
            @JsonProperty(value = "nom", required = false) String nom, //
            @JsonProperty(value = "cognom1", required = false) String cognom1, //
            @JsonProperty(value = "cognom2", required = false) String cognom2, //
            @JsonProperty(value = "email", required = false) String email, //
            @JsonProperty(value = "telefon", required = false) String telefon, //
            @JsonProperty(value = "mobil", required = false) String mobil, //
            @JsonProperty(value = "adreca", required = false) String adreca, //
            @JsonProperty(value = "carrec", required = false) String carrec, //
            @JsonProperty(value = "dataCreacio", required = false) Date dataCreacio, //
            @JsonProperty(value = "dataDarreraModificacio", required = false) Date dataDarreraModificacio, //
            @JsonProperty(value = "observacions", required = false) String observacions, //
            @JsonProperty(value = "usuariDarreraModificacio", required = false) Usuari usuariDarreraModificacio) {

        this.idUsuari = idUsuari;
        this.nomUsuari = nomUsuari;
        this.nom = nom;
        this.cognom1 = cognom1;
        this.cognom2 = cognom2;
        this.email = email;
        this.telefon = telefon;
        this.mobil = mobil;
        this.adreca = adreca;
        this.carrec = carrec;
        this.dataCreacio = dataCreacio;
        this.dataDarreraModificacio = dataDarreraModificacio;
        this.observacions = observacions;
        this.usuariDarreraModificacio = usuariDarreraModificacio;
    }

    public long getIdUsuari() {
        return idUsuari;
    }

    public String getAdreca() {
        return adreca;
    }

    public String getCarrec() {
        return carrec;
    }

    public String getCognom1() {
        return cognom1;
    }

    public String getCognom2() {
        return cognom2;
    }

    public Date getDataCreacio() {
        return dataCreacio;
    }

    public Date getDataDarreraModificacio() {
        return dataDarreraModificacio;
    }

    public String getEmail() {
        return email;
    }

    public String getMobil() {
        return mobil;
    }

    public String getNom() {
        return nom;
    }

    public String getNomUsuari() {
        return nomUsuari;
    }

    public String getObservacions() {
        return observacions;
    }

    public String getTelefon() {
        return telefon;
    }

    public Usuari getUsuariDarreraModificacio() {
        return usuariDarreraModificacio;
    }

    @Override
    public String toString() {
        return String.format(
                "[Usuari idUsuari: %s, nomUsuari: %s, nom: %s, cognom1: %s, "
                        + "cognom2: %s, email: %s, telefon: %s, mobil: %s, "
                        + "adreca: %s, carrec: %s, dataCreacio: %s, dataDarreraModificacio: %s, "
                        + "observacions: %s, usuariDarreraModificacio: %s]",
                idUsuari, nomUsuari, nom, cognom1, cognom2, email, telefon, mobil, adreca, carrec, dataCreacio,
                dataDarreraModificacio, observacions, usuariDarreraModificacio);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Usuari)) {
            return false;
        }
        return this.idUsuari == ((Usuari) obj).idUsuari;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(idUsuari);
    }
}
