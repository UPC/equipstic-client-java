package edu.upc.caminstech.equipstic;

import org.apache.commons.lang3.builder.CompareToBuilder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class Categoria implements Comparable<Categoria> {

    private final long idCategoria;
    private final String nom;
    private final String codi;

    @JsonCreator
    public Categoria(@JsonProperty("idCategoria") long idCategoria, //
            @JsonProperty(value = "nom", required = false) String nom,
            @JsonProperty(value = "codi", required = false) String codi) {
        this.idCategoria = idCategoria;
        this.nom = nom;
        this.codi = codi;
    }

    public Categoria(long idCategoria) {
        this(idCategoria, null, null);
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

    @Override
    public int compareTo(Categoria o) {
        if (o == null) {
            return -1;
        }
        if (o == this) {
            return 0;
        }
        return new CompareToBuilder().append(this.nom, o.nom).append(this.idCategoria, o.idCategoria).toComparison();
    }
}
