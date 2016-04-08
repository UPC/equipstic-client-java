package edu.upc.caminstech.equipstic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Estat {

    public enum TipusEstat {
        VALIDACIO, INFRAESTRUCTURA;
    }

    private final long idEstat;
    private final String nom;
    private final TipusEstat tipusEstat;
    private final String codi;
    private final boolean requereixValidacio;

    @JsonCreator
    public Estat( //
            @JsonProperty("idEstat") long idEstat, //
            @JsonProperty("nom") String nom, //
            @JsonProperty("tipusEstat") TipusEstat tipusEstat, //
            @JsonProperty("codi") String codi, //
            @JsonProperty("requereixValidacio") boolean requereixValidacio) {

        this.idEstat = idEstat;
        this.nom = nom;
        this.tipusEstat = tipusEstat;
        this.codi = codi;
        this.requereixValidacio = requereixValidacio;
    }

    public long getIdEstat() {
        return idEstat;
    }

    public String getNom() {
        return nom;
    }

    public TipusEstat getTipusEstat() {
        return tipusEstat;
    }

    public String getCodi() {
        return codi;
    }

    public boolean isRequereixValidacio() {
        return requereixValidacio;
    }

    @Override
    public String toString() {
        return String.format("[Estat idEstat: %s, nom: %s, tipusEstat: %s, codi: %s, requereixValidacio: %s]", idEstat,
                nom, tipusEstat, codi, requereixValidacio);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Estat)) {
            return false;
        }
        return this.idEstat == ((Estat) obj).idEstat;
    }

    @Override
    public int hashCode() {
        return Long.valueOf(idEstat).hashCode();
    }
}
