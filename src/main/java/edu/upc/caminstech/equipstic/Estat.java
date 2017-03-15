package edu.upc.caminstech.equipstic;

import org.apache.commons.lang3.builder.CompareToBuilder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class Estat implements Comparable<Estat> {

    public enum TipusEstat {
        VALIDACIO, INFRAESTRUCTURA, UNITAT;
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

    @Override
    public int compareTo(Estat other) {
        if (other == null) {
            return -1;
        }
        if (other == this) {
            return 0;
        }
        return new CompareToBuilder().append(this.nom, other.nom).append(this.idEstat, other.idEstat).toComparison();
    }
}
