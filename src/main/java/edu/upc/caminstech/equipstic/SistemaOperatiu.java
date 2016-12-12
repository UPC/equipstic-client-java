package edu.upc.caminstech.equipstic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SistemaOperatiu {

    private final long idSistemaOperatiu;
    private final String nom;
    private final String codi;
    private final Categoria categoriaInfraestructura;

    @JsonCreator
    public SistemaOperatiu(//
            @JsonProperty("idSistemaOperatiu") long idSistemaOperatiu, //
            @JsonProperty("nom") String nom, //
            @JsonProperty("codi") String codi, //
            @JsonProperty("categoriaInfraestructura") Categoria categoriaInfraestructura) {
        this.idSistemaOperatiu = idSistemaOperatiu;
        this.nom = nom;
        this.codi = codi;
        this.categoriaInfraestructura = categoriaInfraestructura;
    }

    public long getIdSistemaOperatiu() {
        return idSistemaOperatiu;
    }

    public String getNom() {
        return nom;
    }

    public String getCodi() {
        return codi;
    }

    public Categoria getCategoriaInfraestructura() {
        return categoriaInfraestructura;
    }

    @Override
    public String toString() {
        return String.format("[SistemaOperatiu idSistemaOperatiu: %s, nom: %s, codi: %s, categoriaInfraestructura: %s]",
                idSistemaOperatiu, nom, codi, categoriaInfraestructura.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SistemaOperatiu)) {
            return false;
        }
        return this.idSistemaOperatiu == ((SistemaOperatiu) obj).idSistemaOperatiu;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(idSistemaOperatiu);
    }
}
