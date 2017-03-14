package edu.upc.caminstech.equipstic;

import org.apache.commons.lang3.builder.CompareToBuilder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class SistemaOperatiu implements Comparable<SistemaOperatiu> {

    private final long idSistemaOperatiu;
    private final String nom;
    private final String codi;
    private final Categoria categoriaInfraestructura;

    @JsonCreator
    public SistemaOperatiu(//
            @JsonProperty("idSistemaOperatiu") long idSistemaOperatiu, //
            @JsonProperty(value = "nom", required = false) String nom, //
            @JsonProperty(value = "codi", required = false) String codi, //
            @JsonProperty(value = "categoriaInfraestructura", required = false) Categoria categoriaInfraestructura) {
        this.idSistemaOperatiu = idSistemaOperatiu;
        this.nom = nom;
        this.codi = codi;
        this.categoriaInfraestructura = categoriaInfraestructura;
    }

    public SistemaOperatiu(long idSistemaOperatiu) {
        this(idSistemaOperatiu, null, null, null);
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
                idSistemaOperatiu, nom, codi,
                categoriaInfraestructura != null ? categoriaInfraestructura.toString() : null);
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

    @Override
    public int compareTo(SistemaOperatiu other) {
        if (other == null) {
            return -1;
        }
        if (other == this) {
            return 0;
        }
        return new CompareToBuilder().append(this.categoriaInfraestructura, other.categoriaInfraestructura)
                .append(this.nom, other.nom).append(this.idSistemaOperatiu, other.idSistemaOperatiu).toComparison();
    }
}
