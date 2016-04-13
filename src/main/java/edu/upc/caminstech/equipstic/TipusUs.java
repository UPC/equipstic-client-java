package edu.upc.caminstech.equipstic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TipusUs {

    private final long idTipusUs;
    private final String nom;
    private final Unitat unitat;

    @JsonCreator
    public TipusUs(@JsonProperty("idTipusUs") long idTipusUs, @JsonProperty("nom") String nom,
            @JsonProperty("unitat") Unitat unitat) {

        this.idTipusUs = idTipusUs;
        this.nom = nom;
        this.unitat = unitat;
    }

    public long getIdTipusUs() {
        return idTipusUs;
    }

    public String getNom() {
        return nom;
    }

    public Unitat getUnitat() {
        return unitat;
    }

    @Override
    public String toString() {
        return String.format("[TipusUs idtipusUs: %s, nom: %s, unitat: %s]", idTipusUs, nom, unitat);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TipusUs)) {
            return false;
        }
        return this.idTipusUs == ((TipusUs) obj).idTipusUs;
    }

    @Override
    public int hashCode() {
        return Long.valueOf(idTipusUs).hashCode();
    }

}
