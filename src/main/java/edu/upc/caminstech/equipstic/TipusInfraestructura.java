package edu.upc.caminstech.equipstic;

import java.util.Arrays;
import java.util.Optional;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Tipus d'infraestructura.
 * <p>
 * Cada {@code TipusInfraestructura} té associada una {@link Categoria}. Cada
 * combinació de tipus/categoria té uns atributs obligatoris o uns altres a la
 * API d'EquipsTIC (per exemple model i nombre de CPU, capacitat dels discs,
 * etc.).
 * <p>
 * Consulteu la documentació de <a href=
 * "https://equipstic.upc.edu/equipstic-api-v2/swagger-ui.html#!/infraestructura/addUsingPOST">POST
 * /infraestructura</a> per veure quins camps són obligatoris per
 * tipus/categoria.
 */
@JsonInclude(Include.NON_NULL)
public class TipusInfraestructura implements Comparable<TipusInfraestructura> {

    /*
     * Algunes constants que es fan servir a funcions auxiliars (no estan tots
     * els tipus i categories existents)
     */

    private static final String EQUIP_COMPUTACIO = "EQUIP_COMPUTACIO";
    private static final String EQUIP_TREBALL = "EQUIP_TREBALL";
    private static final String DISPOSITIU_EMMAGATZEMAMENT = "DISPOSITIU_EMMAGATZEMAMENT";

    private static final String TIPUS_ESTACIO_DE_TREBALL = "ESTACIO_TREBALL";
    private static final String TIPUS_NETBOOK = "'NETBOOK";
    private static final String TIPUS_PORTATIL = "PORTATIL";

    private final long idTipus;
    private final String nom;
    private final String codi;
    private final Boolean requereixCampsExtra;
    private final Categoria categoriaInfraestructura;

    @JsonCreator
    public TipusInfraestructura( //
            @JsonProperty(value = "idTipus") long idTipus, //
            @JsonProperty(value = "nom", required = false) String nom, //
            @JsonProperty(value = "codi", required = false) String codi, //
            @JsonProperty(value = "requereixCampsExtra", required = false) Boolean requereixCampsExtra, //
            @JsonProperty(value = "categoriaInfraestructura", required = false) Categoria categoriaInfraestructura) {
        this.idTipus = idTipus;
        this.nom = nom;
        this.codi = codi;
        this.requereixCampsExtra = requereixCampsExtra;
        this.categoriaInfraestructura = categoriaInfraestructura;
    }

    public TipusInfraestructura(long idTipus) {
        this(idTipus, null, null, null, null);
    }

    public Categoria getCategoriaInfraestructura() {
        return categoriaInfraestructura;
    }

    public long getIdTipus() {
        return idTipus;
    }

    public String getNom() {
        return nom;
    }

    public String getCodi() {
        return codi;
    }

    public Boolean isRequereixCampsExtra() {
        return requereixCampsExtra;
    }

    public boolean calModelCpu() {
        String codiTipus = this.getCodi();
        return Arrays.asList(DISPOSITIU_EMMAGATZEMAMENT, EQUIP_COMPUTACIO).contains(getCodiCategoria())
                || (EQUIP_TREBALL.equals(getCodiCategoria())
                        && Arrays.asList(TIPUS_ESTACIO_DE_TREBALL, TIPUS_NETBOOK, TIPUS_PORTATIL).contains(codiTipus));
    }

    public boolean calNumeroCpus() {
        return calModelCpu();
    }

    public boolean calDiscs() {
        return calModelCpu();
    }

    public boolean calCapacitatTotalGb() {
        return Arrays.asList(DISPOSITIU_EMMAGATZEMAMENT, EQUIP_COMPUTACIO).contains(getCodiCategoria());
    }

    public boolean calUsuari() {
        return EQUIP_TREBALL.equals(getCodiCategoria());
    }

    public boolean calSistemaOperatiu() {
        String codiTipus = this.getCodi();
        return EQUIP_COMPUTACIO.equals(getCodiCategoria()) || (EQUIP_TREBALL.equals(getCodiCategoria())
                && Arrays.asList(TIPUS_ESTACIO_DE_TREBALL, TIPUS_NETBOOK, TIPUS_PORTATIL).contains(codiTipus));
    }

    @Override
    public String toString() {
        return String.format(
                "[TipusInfraestructura idTipus: %s, nom: %s, codi: %s, requereixCampsExtra: %s, categoriaInfraestructura: %s]",
                idTipus, nom, codi, requereixCampsExtra, categoriaInfraestructura);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TipusInfraestructura)) {
            return false;
        }
        return this.idTipus == ((TipusInfraestructura) obj).idTipus;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(idTipus);
    }

    @Override
    public int compareTo(TipusInfraestructura t) {
        if (t == null) {
            return -1;
        }
        if (t == this) {
            return 0;
        }
        return new CompareToBuilder() //
                .append(this.categoriaInfraestructura, t.categoriaInfraestructura) //
                .append(this.nom, t.nom) //
                .append(this.idTipus, t.idTipus) //
                .toComparison();
    }

    @Nullable
    private String getCodiCategoria() {
        return Optional.ofNullable(this.getCategoriaInfraestructura()) //
                .map(Categoria::getCodi) //
                .orElse(null);
    }
}
