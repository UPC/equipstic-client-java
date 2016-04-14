package edu.upc.caminstech.equipstic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Ambit {

    private final long idAmbit;
    private final String nom;

    @JsonCreator
    public Ambit(@JsonProperty("idAmbit") long idAmbit, @JsonProperty("nom") String nom) {
        this.idAmbit = idAmbit;
        this.nom = nom;
    }

    public long getIdAmbit() {
        return idAmbit;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return String.format("[Ambit idAmbit: %s, nom: %s]", idAmbit, nom);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Ambit)) {
            return false;
        }
        return this.idAmbit == ((Ambit) obj).idAmbit;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(idAmbit);
    }
}
