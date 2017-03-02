package edu.upc.caminstech.equipstic;

import org.apache.commons.lang3.builder.CompareToBuilder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Aquesta classe representa l'àmbit d'una infraestructura.
 * <p>
 * <blockquote><strong>ATENCIÓ:</strong> dins l'aplicació web d'EquipsTIC,
 * l'àmbit es troba sota l'etiqueta <em>Propòsit</em>. </blockquote>
 */
@JsonInclude(Include.NON_NULL)
public class Ambit implements Comparable<Ambit> {

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

    /**
     * La ordenació per defecte és per {@code categoriaInfraestructura},
     * {@code nom} i {@code idAmbit}.
     */
    @Override
    public int compareTo(Ambit other) {
        if (other == null) {
            return -1;
        }
        if (other == this) {
            return 0;
        }
        return new CompareToBuilder().append(this.categoriaInfraestructura, other.categoriaInfraestructura)
                .append(this.nom, other.nom).append(this.idAmbit, other.idAmbit).toComparison();
    }
}
