package edu.upc.caminstech.equipstic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Representa una Unitat Estructural de la UPC.
 * <p>
 * <strong>NOTA:</strong> la versió 1 de l'API REST d'EquipsTIC defineix per a
 * aquesta classe un atribut {@link #identificador}, de tipus {@code String},
 * que conté les sigles de la unitat, i que no s'ha de confondre amb l'atribut
 * {@link #idUnitat}, de tipus {@code long}.
 */
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

    /**
     * Retorna les sigles de la unitat.
     */
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
