package edu.upc.caminstech.equipstic;

import org.apache.commons.lang3.builder.CompareToBuilder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class Campus implements Comparable<Campus> {

    private final long idCampus;
    private final String nom;
    private final String codi;

    @JsonCreator
    public Campus(@JsonProperty("idCampus") long idCampus, //
            @JsonProperty(value = "nom", required = false) String nom, //
            @JsonProperty(value = "codi", required = false) String codi) {
        this.idCampus = idCampus;
        this.nom = nom;
        this.codi = codi;
    }

    public Campus(long idCampus) {
        this(idCampus, null, null);
    }

    public long getIdCampus() {
        return idCampus;
    }

    public String getNom() {
        return nom;
    }

    public String getCodi() {
        return codi;
    }

    @Override
    public String toString() {
        return String.format("[Campus idCampus: %s, nom: %s, codi: %s]", idCampus, nom, codi);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Campus)) {
            return false;
        }
        return this.idCampus == ((Campus) obj).idCampus;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(idCampus);
    }

    /**
     * La comparació ordena per codi, idCampus
     */
    @Override
    public int compareTo(Campus o) {
        if (o == null) {
            return -1;
        }
        if (o == this) {
            return 0;
        }
        return new CompareToBuilder().append(this.codi, o.codi).append(this.idCampus, o.idCampus).toComparison();
    }
}
