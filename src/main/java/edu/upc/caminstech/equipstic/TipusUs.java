package edu.upc.caminstech.equipstic;

import org.apache.commons.lang3.builder.CompareToBuilder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class TipusUs implements Comparable<TipusUs> {

    private final long idTipusUs;
    private final String nom;
    private final Unitat unitat;

    @JsonCreator
    public TipusUs(@JsonProperty("idTipusUs") long idTipusUs, //
            @JsonProperty(value = "nom", required = false) String nom, //
            @JsonProperty(value = "unitat", required = false) Unitat unitat) {

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
        return Long.hashCode(idTipusUs);
    }

    @Override
    public int compareTo(TipusUs o) {
        if (o == null) {
            return -1;
        }
        if (o == this) {
            return 0;
        }
        return new CompareToBuilder().append(this.unitat, o.unitat).append(this.nom, o.nom)
                .append(this.idTipusUs, o.idTipusUs).toComparison();
    }

}
