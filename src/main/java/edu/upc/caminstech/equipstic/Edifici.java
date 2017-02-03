package edu.upc.caminstech.equipstic;

import org.apache.commons.lang3.builder.CompareToBuilder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class Edifici implements Comparable<Edifici> {

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
            @JsonProperty(value = "nom", required = false) String nom, //
            @JsonProperty(value = "codi", required = false) String codi, //
            @JsonProperty(value = "adreca", required = false) String adreca, //
            @JsonProperty(value = "ciutat", required = false) String ciutat, //
            @JsonProperty(value = "codi_postal", required = false) String codiPostal, //
            @JsonProperty(value = "campus", required = false) Campus campus) {

        this.idEdifici = idEdifici;
        this.nom = nom;
        this.codi = codi;
        this.adreca = adreca;
        this.ciutat = ciutat;
        this.codiPostal = codiPostal;
        this.campus = campus;
    }

    public Edifici(long idEdifici) {
        this(idEdifici, null, null, null, null, null, null);
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

    @Override
    public String toString() {
        return String.format(
                "[Edifici idEdifici: %s, nom: %s, codi: %s, adreca: %s, ciutat: %s, codiPostal: %s, campus: %s]",
                idEdifici, nom, codi, adreca, ciutat, codiPostal, campus);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Edifici)) {
            return false;
        }
        return this.idEdifici == ((Edifici) obj).idEdifici;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(idEdifici);
    }

    @Override
    public int compareTo(Edifici o) {
        if (o == null) {
            return -1;
        }
        if (o == this) {
            return 0;
        }
        return new CompareToBuilder().append(this.campus, o.campus).append(this.codi, o.codi)
                .append(this.idEdifici, o.idEdifici).toComparison();
    }

}
