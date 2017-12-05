package edu.upc.caminstech.equipstic;

import org.apache.commons.lang3.builder.CompareToBuilder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Representa una Unitat Estructural de la UPC.
 * <p>
 * <strong>NOTA:</strong> la API d'EquipsTIC defineix per a aquesta classe un
 * atribut {@link #identificador}, de tipus {@code String}, que conté les sigles
 * de la unitat, i que no s'ha de confondre amb l'atribut {@link #idUnitat}, de
 * tipus {@code long}.
 */
@JsonInclude(Include.NON_NULL)
public class Unitat implements Comparable<Unitat> {

    private final long idUnitat;
    private final String codiUnitat;
    private final String identificador;
    private final String nom;
    private final Estat estat;

    @JsonCreator
    public Unitat(//
            @JsonProperty("idUnitat") long idUnitat, //
            @JsonProperty(value = "codiUnitat", required = false) String codiUnitat, //
            @JsonProperty(value = "identificador", required = false) String identificador, //
            @JsonProperty(value = "nom", required = false) String nom,
            @JsonProperty(value = "estat", required = false) Estat estat) {

        this.idUnitat = idUnitat;
        this.codiUnitat = codiUnitat;
        this.identificador = identificador;
        this.nom = nom;
        this.estat = estat;
    }

    public Unitat(long idUnitat) {
        this(idUnitat, null, null, null, null);
    }

    public long getIdUnitat() {
        return idUnitat;
    }

    /**
     * Retorna el codi UPC de la Unitat Estructural.
     */
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

    public Estat getEstat() {
        return estat;
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

    /**
     * La comparació ordena segons (identificador, idUnitat).
     */
    @Override
    public int compareTo(Unitat o) {
        if (o == null) {
            return -1;
        }
        if (o == this) {
            return 0;
        }
        return new CompareToBuilder().append(this.identificador, o.identificador).append(this.idUnitat, o.idUnitat)
                .toComparison();
    }
}
