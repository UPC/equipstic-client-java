package edu.upc.caminstech.equipstic;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class UsuariInfraestructuraTests {

    private static final long idUsuariInfraestructura = 10L;
    private static final String nom = "Nom";
    private static final String nomUsuari = "nom.usuari";
    private static final String cognom1 = "Cognom1";
    private static final String cognom2 = "Cognom2";
    private static final Date dataCreacio = new Date();

    private UsuariInfraestructura usuariInfra;

    @Before
    public void setUp() {
        usuariInfra = new UsuariInfraestructura(idUsuariInfraestructura, nom, nomUsuari, cognom1, cognom2, dataCreacio);
    }

    @Test
    public void testHashCode() {
        assertEquals(Long.hashCode(10L), usuariInfra.hashCode());
    }

    @Test
    public void testUsuariInfraestructura() {
        assertNotNull(usuariInfra);
    }

    @Test
    public void testGetIdUsuariInfraestructura() {
        assertEquals(idUsuariInfraestructura, usuariInfra.getIdUsuariInfraestructura());
    }

    @Test
    public void testGetNom() {
        assertEquals(nom, usuariInfra.getNom());
    }

    @Test
    public void testGetNomUsuari() {
        assertEquals(nomUsuari, usuariInfra.getNomUsuari());
    }

    @Test
    public void testGetCognom1() {
        assertEquals(cognom1, usuariInfra.getCognom1());
    }

    @Test
    public void testGetCognom2() {
        assertEquals(cognom2, usuariInfra.getCognom2());
    }

    @Test
    public void testGetDataCreacio() {
        assertEquals(dataCreacio, usuariInfra.getDataCreacio());
    }

    @Test
    public void testEqualsObject() {
        UsuariInfraestructura u = new UsuariInfraestructura(idUsuariInfraestructura, null, null, null, null, null);
        assertEquals(u, usuariInfra);
    }

}
