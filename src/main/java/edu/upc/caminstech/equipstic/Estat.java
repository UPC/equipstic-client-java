package edu.upc.caminstech.equipstic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class Estat {

    public enum TipusEstat {
        VALIDACIO, INFRAESTRUCTURA;
    }

    private final long idEstat;
    private final String nom;
    private final TipusEstat tipusEstat;
    private final String codi;
    private final Boolean requereixValidacio;

    @JsonCreator
    public Estat( //
            @JsonProperty("idEstat") long idEstat, //
            @JsonProperty(value = "nom", required = false) String nom, //
            @JsonProperty(value = "tipusEstat", required = false) TipusEstat tipusEstat, //
            @JsonProperty(value = "codi", required = false) String codi, //
            @JsonProperty(value = "requereixValidacio", required = false) Boolean requereixValidacio) {

        this.idEstat = idEstat;
        this.nom = nom;
        this.tipusEstat = tipusEstat;
        this.codi = codi;
        this.requereixValidacio = requereixValidacio;
    }

    public Estat(long idEstat) {
        this(idEstat, null, null, null, null);
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

    public Boolean isRequereixValidacio() {
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
        return Long.hashCode(idEstat);
    }
}
