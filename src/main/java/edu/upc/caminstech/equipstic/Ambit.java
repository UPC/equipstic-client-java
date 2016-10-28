package edu.upc.caminstech.equipstic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Ambit {

    private final long idAmbit;
    private final String nom;
    private final String codi;
    private final CategoriaInfraestructura categoriaInfraestructura;

    @JsonCreator
    public Ambit(@JsonProperty("idAmbit") long idAmbit, @JsonProperty("nom") String nom,
            @JsonProperty("codi") String codi,
            @JsonProperty("categoriaInfraestructura") CategoriaInfraestructura categoriaInfraestructura) {
        this.idAmbit = idAmbit;
        this.nom = nom;
        this.codi = codi;
        this.categoriaInfraestructura = categoriaInfraestructura;
    }

    public long getIdAmbit() {
        return idAmbit;
    }

    public String getNom() {
        return nom;
    }

    public String getCodi() {
        return codi;
    }

    public CategoriaInfraestructura getCategoriaInfraestructura() {
        return categoriaInfraestructura;
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
