package edu.upc.caminstech.equipstic;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

class MarcaTests {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private Marca marca;

    @BeforeEach
    public void setUp() {
        marca = new Marca(2, "DELL");
    }

    @Test
    void getIdMarca() {
        assertEquals(2, marca.getIdMarca());
    }

    @Test
    void getNom() {
        assertEquals("DELL", marca.getNom());
    }

    @Test
    void testSerialization() throws Exception {
        String json = objectMapper.writeValueAsString(marca);
        assertThat(json, containsString("idMarca"));
        assertThat(json, containsString("nom"));
    }

    @Test
    void testSerializationNomNull() throws Exception {
        Marca m = new Marca(1, null);
        String json = objectMapper.writeValueAsString(m);
        assertThat(json, not(containsString("nom")));
    }

    @Test
    void testDeserialization() throws Exception {
        String json = "{ \"idMarca\": \"2\", \"nom\": \"DELL\" }";
        Marca m = objectMapper.readValue(json, Marca.class);
        assertNotNull(m);
        assertEquals(2, m.getIdMarca());
        assertEquals("DELL", m.getNom());
    }

    @Test
    void testDeserializationNomNull() throws Exception {
        String json = "{ \"idMarca\": \"3\", \"nom\": null }";
        Marca m = objectMapper.readValue(json, Marca.class);
        assertNotNull(m);
        assertEquals(3, m.getIdMarca());
        assertNull(m.getNom());
    }
}
