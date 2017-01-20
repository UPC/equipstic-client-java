package edu.upc.caminstech.equipstic;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MarcaTests {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private Marca marca;

    @Before
    public void setUp() {
        marca = new Marca(2, "DELL");
    }

    @Test
    public void getIdMarca() {
        assertEquals(2, marca.getIdMarca());
    }

    @Test
    public void getNom() {
        assertEquals("DELL", marca.getNom());
    }

    @Test
    public void testSerialization() throws Exception {
        String json = objectMapper.writeValueAsString(marca);
        assertThat(json, containsString("idMarca"));
        assertThat(json, containsString("nom"));
    }

    @Test
    public void testSerializationNomNull() throws Exception {
        Marca m = new Marca(1, null);
        String json = objectMapper.writeValueAsString(m);
        assertThat(json, not(containsString("nom")));
    }
}
