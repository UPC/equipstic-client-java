package edu.upc.caminstech.equipstic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Edifici {

    private final long idEdifici;
    private final String nom;
    private final String codi;
    private final String adreca;
    private final String ciutat;
    private final String codiPostal;
    private final Campus campus;

    @JsonCreator
    public Edifici( //
            @JsonProperty("idEdifici") long idEdifici, //
            @JsonProperty("nom") String nom, //
            @JsonProperty("codi") String codi, //
            @JsonProperty("adreca") String adreca, //
            @JsonProperty("ciutat") String ciutat, //
            @JsonProperty("codi_postal") String codiPostal, //
            @JsonProperty("campus") Campus campus) {

        this.idEdifici = idEdifici;
        this.nom = nom;
        this.codi = codi;
        this.adreca = adreca;
        this.ciutat = ciutat;
        this.codiPostal = codiPostal;
        this.campus = campus;
    }

    public long getIdEdifici() {
        return idEdifici;
    }

    public String getNom() {
        return nom;
    }

    public String getCodi() {
        return codi;
    }

    public String getAdreca() {
        return adreca;
    }

    public String getCiutat() {
        return ciutat;
    }

    @JsonProperty("codi_postal")
    public String getCodiPostal() {
        return codiPostal;
    }

    public Campus getCampus() {
        return campus;
    }

}
