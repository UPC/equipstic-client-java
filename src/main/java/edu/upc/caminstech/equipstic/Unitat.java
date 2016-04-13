package edu.upc.caminstech.equipstic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Unitat {

    private final long idUnitat;
    private final String codiUnitat;
    private final String identificador;
    private final String nom;

    @JsonCreator
    public Unitat(//
            @JsonProperty("idUnitat") long idUnitat, //
            @JsonProperty("codiUnitat") String codiUnitat, //
            @JsonProperty("identificador") String identificador, //
            @JsonProperty("nom") String nom) {

        this.idUnitat = idUnitat;
        this.codiUnitat = codiUnitat;
        this.identificador = identificador;
        this.nom = nom;
    }

    public long getIdUnitat() {
        return idUnitat;
    }

    public String getCodiUnitat() {
        return codiUnitat;
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return String.format("[Unitat idUnitat: %s, codiUnitat: %s, identificador: %s, nom: %s]", idUnitat, codiUnitat,
                identificador, nom);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Unitat)) {
            return false;
        }
        return this.idUnitat == ((Unitat) obj).idUnitat;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(idUnitat);
    }
}
