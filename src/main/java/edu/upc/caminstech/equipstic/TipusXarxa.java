package edu.upc.caminstech.equipstic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class TipusXarxa {

    private final long idTipusXarxa;
    private final String nom;
    private final String codi;

    @JsonCreator
    public TipusXarxa( //
            @JsonProperty("idTipusXarxa") long idTipusXarxa, //
            @JsonProperty(value = "nom", required = false) String nom, //
            @JsonProperty(value = "codi", required = false) String codi) {

        this.idTipusXarxa = idTipusXarxa;
        this.nom = nom;
        this.codi = codi;
    }

    public TipusXarxa(long idTipusXarxa) {
        this(idTipusXarxa, null, null);
    }

    public long getIdTipusXarxa() {
        return idTipusXarxa;
    }

    public String getNom() {
        return nom;
    }

    public String getCodi() {
        return codi;
    }

    @Override
    public String toString() {
        return String.format("[TipusXarxa idTipusXarxa: %s, nom: %s, codi: %s]", idTipusXarxa, nom, codi);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TipusXarxa)) {
            return false;
        }
        return this.idTipusXarxa == ((TipusXarxa) obj).idTipusXarxa;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(idTipusXarxa);
    }
}
