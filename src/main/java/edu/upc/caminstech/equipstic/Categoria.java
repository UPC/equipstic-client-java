package edu.upc.caminstech.equipstic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Categoria {

    private final long idCategoria;
    private final String nom;
    private final String codi;

    @JsonCreator
    public Categoria(@JsonProperty("idCategoria") long idCategoria, @JsonProperty("nom") String nom,
            @JsonProperty("codi") String codi) {
        this.idCategoria = idCategoria;
        this.nom = nom;
        this.codi = codi;
    }

    public long getIdCategoria() {
        return idCategoria;
    }

    public String getNom() {
        return nom;
    }

    public String getCodi() {
        return codi;
    }

    @Override
    public String toString() {
        return String.format("[Categoria idCategoria: %s, nom: %s, codi: %s]", idCategoria, nom, codi);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Categoria)) {
            return false;
        }
        return this.idCategoria == ((Categoria) obj).idCategoria;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(idCategoria);
    }
}
