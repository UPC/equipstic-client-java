package edu.upc.caminstech.equipstic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Marca {

    private final long idMarca;
    private final String nom;

    @JsonCreator
    public Marca( //
            @JsonProperty("idMarca") long idMarca, //
            @JsonProperty("nom") String nom) {

        this.idMarca = idMarca;
        this.nom = nom;
    }

    public long getIdMarca() {
        return idMarca;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return String.format("[Marca idMarca: %s, nom: %s]", idMarca, nom);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Marca)) {
            return false;
        }
        return this.idMarca == ((Marca) obj).idMarca;
    }

    @Override
    public int hashCode() {
        return Long.valueOf(idMarca).hashCode();
    }

}
