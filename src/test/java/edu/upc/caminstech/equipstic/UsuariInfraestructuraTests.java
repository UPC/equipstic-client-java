package edu.upc.caminstech.equipstic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UsuariInfraestructuraTests {

    private static final long idUsuariInfraestructura = 10L;
    private static final String nom = "Nom";
    private static final String nomUsuari = "nom.usuari";
    private static final String cognom1 = "Cognom1";
    private static final String cognom2 = "Cognom2";
    private static final Date dataCreacio = new Date();

    private UsuariInfraestructura usuariInfra;

    @BeforeEach
    public void setUp() {
        usuariInfra = new UsuariInfraestructura(idUsuariInfraestructura, nom, nomUsuari, cognom1, cognom2, dataCreacio);
    }

    @Test
    void testHashCode() {
        assertEquals(Long.hashCode(10L), usuariInfra.hashCode());
    }

    @Test
    void testUsuariInfraestructura() {
        assertNotNull(usuariInfra);
    }

    @Test
    void testGetIdUsuariInfraestructura() {
        assertEquals(idUsuariInfraestructura, usuariInfra.getIdUsuariInfraestructura());
    }

    @Test
    void testGetNom() {
        assertEquals(nom, usuariInfra.getNom());
    }

    @Test
    void testGetNomUsuari() {
        assertEquals(nomUsuari, usuariInfra.getNomUsuari());
    }

    @Test
    void testGetCognom1() {
        assertEquals(cognom1, usuariInfra.getCognom1());
    }

    @Test
    void testGetCognom2() {
        assertEquals(cognom2, usuariInfra.getCognom2());
    }

    @Test
    void testGetDataCreacio() {
        assertEquals(dataCreacio, usuariInfra.getDataCreacio());
    }

    @Test
    void testEqualsObject() {
        UsuariInfraestructura u = new UsuariInfraestructura(idUsuariInfraestructura, null, null, null, null, null);
        assertEquals(u, usuariInfra);
    }

}
