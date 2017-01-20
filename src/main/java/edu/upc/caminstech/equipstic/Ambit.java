package edu.upc.caminstech.equipstic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class Ambit {

    private final long idAmbit;
    private final String nom;
    private final String codi;
    private final Categoria categoriaInfraestructura;

    @JsonCreator
    public Ambit(@JsonProperty("idAmbit") long idAmbit, //
            @JsonProperty(value = "nom", required = false) String nom, //
            @JsonProperty(value = "codi", required = false) String codi, //
            @JsonProperty(value = "categoriaInfraestructura", required = false) Categoria categoriaInfraestructura) {
        this.idAmbit = idAmbit;
        this.nom = nom;
        this.codi = codi;
        this.categoriaInfraestructura = categoriaInfraestructura;
    }

    public Ambit(long idAmbit) {
        this(idAmbit, null, null, null);
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

    public Categoria getCategoriaInfraestructura() {
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
