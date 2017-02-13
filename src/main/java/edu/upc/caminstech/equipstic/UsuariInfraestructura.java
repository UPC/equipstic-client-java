package edu.upc.caminstech.equipstic;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Usuari habitual d'un equip.
 */
@JsonInclude(Include.NON_NULL)
public class UsuariInfraestructura {

    private final long idUsuariInfraestructura;
    private final String nom;
    private final String nomUsuari;
    private final String cognom1;
    private final String cognom2;
    private final Date dataCreacio;

    @JsonCreator
    public UsuariInfraestructura(@JsonProperty("idUsuariInfraestructura") long idUsuariInfraestructura, //
            @JsonProperty(value = "nom", required = false) String nom, //
            @JsonProperty(value = "nomUsuari", required = false) String nomUsuari, //
            @JsonProperty(value = "cognom1", required = false) String cognom1, //
            @JsonProperty(value = "cognom2", required = false) String cognom2, //
            @JsonProperty(value = "dataCreacio", required = false) Date dataCreacio) {
        this.idUsuariInfraestructura = idUsuariInfraestructura;
        this.nom = nom;
        this.nomUsuari = nomUsuari;
        this.cognom1 = cognom1;
        this.cognom2 = cognom2;
        this.dataCreacio = dataCreacio;
    }

    public long getIdUsuariInfraestructura() {
        return idUsuariInfraestructura;
    }

    public String getNom() {
        return nom;
    }

    public String getNomUsuari() {
        return nomUsuari;
    }

    public String getCognom1() {
        return cognom1;
    }

    public String getCognom2() {
        return cognom2;
    }

    public Date getDataCreacio() {
        return dataCreacio;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof UsuariInfraestructura)) {
            return false;
        }
        UsuariInfraestructura u = (UsuariInfraestructura) obj;
        return this.idUsuariInfraestructura == u.idUsuariInfraestructura;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(idUsuariInfraestructura);
    }

    @Override
    public String toString() {
        return String.format(
                "[UsuariInfraestructura idUsuariInfraestructura: %s, nom: %s, nomUsuari: %s, cognom1: %s, cognom2: %s, dataCreacio:%s]",
                idUsuariInfraestructura, nom, nomUsuari, cognom1, cognom2, dataCreacio);
    }
}
