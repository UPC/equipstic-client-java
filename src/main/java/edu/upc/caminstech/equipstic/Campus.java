package edu.upc.caminstech.equipstic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Campus {

    private final long idCampus;
    private final String nom;
    private final String codi;

    @JsonCreator
    public Campus(@JsonProperty("idCampus") long idCampus, @JsonProperty("nom") String nom,
            @JsonProperty("codi") String codi) {
        this.idCampus = idCampus;
        this.nom = nom;
        this.codi = codi;
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
        return String.format("[idCampus: %s, nom: %s, codi: %s]", idCampus, nom, codi);
    }
}
