package edu.upc.caminstech.equipstic;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UsuariInfraestructura {

    private final long idUsuariInfraestructura;
    private final String nom;
    private final String nomUsuari;
    private final String cognom1;
    private final String cognom2;
    private final Date dataCreacio;

    @JsonCreator
    public UsuariInfraestructura(@JsonProperty("idUsuariInfraestructura") long idUsuariInfraestructura, //
            @JsonProperty("nom") String nom, //
            @JsonProperty("nomUsuari") String nomUsuari, //
            @JsonProperty("cognom1") String cognom1, //
            @JsonProperty("cognom2") String cognom2, //
            @JsonProperty("dataCreacio") Date dataCreacio) {
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
