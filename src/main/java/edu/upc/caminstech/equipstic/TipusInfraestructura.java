package edu.upc.caminstech.equipstic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TipusInfraestructura {

    private final long idTipus;
    private final String nom;
    private final String codi;
    private final boolean requereixCampsExtra;
    private final CategoriaInfraestructura categoriaInfraestructura;

    @JsonCreator
    public TipusInfraestructura( //
            @JsonProperty("idTipus") long idTipus, //
            @JsonProperty("nom") String nom, //
            @JsonProperty("codi") String codi, //
            @JsonProperty("requereixCampsExtra") boolean requereixCampsExtra, //
            @JsonProperty("categoriaInfraestructura") CategoriaInfraestructura categoriaInfraestructura) {
        this.idTipus = idTipus;
        this.nom = nom;
        this.codi = codi;
        this.requereixCampsExtra = requereixCampsExtra;
        this.categoriaInfraestructura = categoriaInfraestructura;
    }

    public CategoriaInfraestructura getCategoriaInfraestructura() {
        return categoriaInfraestructura;
    }

    public long getIdTipus() {
        return idTipus;
    }

    public String getNom() {
        return nom;
    }

    public String getCodi() {
        return codi;
    }

    public boolean isRequereixCampsExtra() {
        return requereixCampsExtra;
    }

    @Override
    public String toString() {
        return String.format(
                "[TipusInfraestructura idTipus: %s, nom: %s, codi: %s, requereixCampsExtra: %s, categoriaInfraestructura: %s]",
                idTipus, nom, codi, requereixCampsExtra, categoriaInfraestructura);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TipusInfraestructura)) {
            return false;
        }
        return this.idTipus == ((TipusInfraestructura) obj).idTipus;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(idTipus);
    }
}
