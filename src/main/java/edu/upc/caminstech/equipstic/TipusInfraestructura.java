package edu.upc.caminstech.equipstic;

import org.apache.commons.lang3.builder.CompareToBuilder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class TipusInfraestructura implements Comparable<TipusInfraestructura> {

    private final long idTipus;
    private final String nom;
    private final String codi;
    private final Boolean requereixCampsExtra;
    private final Categoria categoriaInfraestructura;

    @JsonCreator
    public TipusInfraestructura( //
            @JsonProperty(value = "idTipus") long idTipus, //
            @JsonProperty(value = "nom", required = false) String nom, //
            @JsonProperty(value = "codi", required = false) String codi, //
            @JsonProperty(value = "requereixCampsExtra", required = false) Boolean requereixCampsExtra, //
            @JsonProperty(value = "categoriaInfraestructura", required = false) Categoria categoriaInfraestructura) {
        this.idTipus = idTipus;
        this.nom = nom;
        this.codi = codi;
        this.requereixCampsExtra = requereixCampsExtra;
        this.categoriaInfraestructura = categoriaInfraestructura;
    }

    public TipusInfraestructura(long idTipus) {
        this(idTipus, null, null, null, null);
    }

    public Categoria getCategoriaInfraestructura() {
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

    public Boolean isRequereixCampsExtra() {
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

    @Override
    public int compareTo(TipusInfraestructura t) {
        if (t == null) {
            return -1;
        }
        if (t == this) {
            return 0;
        }
        return new CompareToBuilder().append(this.categoriaInfraestructura, t.categoriaInfraestructura)
                .append(this.nom, t.nom).append(this.idTipus, t.idTipus).toComparison();
    }
}
