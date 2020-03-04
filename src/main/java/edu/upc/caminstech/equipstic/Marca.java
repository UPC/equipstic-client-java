package edu.upc.caminstech.equipstic;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class Marca {

    private final long idMarca;
    private final String nom;

    @JsonCreator
    public Marca( //
            @JsonProperty(value = "idMarca", required = true) long idMarca, //
            @JsonProperty(value = "nom", required = false) String nom) {

        this.idMarca = idMarca;
        this.nom = StringUtils.trim(nom);
    }

    public Marca(long idMarca) {
        this(idMarca, null);
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
        return Long.hashCode(idMarca);
    }

}
