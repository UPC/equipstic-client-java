package edu.upc.caminstech.equipstic;

import static edu.upc.caminstech.equipstic.fixtures.JsonFixtures.attr;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class AmbitTests {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private Ambit ambit;

    @BeforeEach
    public void setUp() throws Exception {
        ambit = ambitFixture();
    }

    private Ambit ambitFixture() {
        Categoria c = new Categoria(10, "Nom categoria", "CODI_CATEGORIA_10");
        return new Ambit(1, "Ambit 1", "CODI_AMBIT_1", c);
    }

    @Test
    void testAmbit() {
        assertNotNull(ambit);
    }

    @Test
    void testGetIdAmbit() {
        assertEquals(1, ambit.getIdAmbit());
    }

    @Test
    void testGetNom() {
        assertEquals("Ambit 1", ambit.getNom());
    }

    @Test
    void testToString() {
        String s = ambit.toString();
        assertTrue(StringUtils.substringMatch(s, 0, "[Ambit "));
    }

    @Test
    void testEquals() {
        Ambit copy = new Ambit(1, "Ambit 1", "CODI_AMBIT_1", ambit.getCategoriaInfraestructura());
        assertEquals(copy, ambit);
    }

    @Test
    void testNotEquals() {
        Ambit a = new Ambit(2, "Ambit 2", "CODI_AMBIT_2", ambit.getCategoriaInfraestructura());
        assertNotEquals(ambit, a);
    }

    @Test
    void testHashCode() {
        Ambit copy = new Ambit(1, "Ambit 1", "CODI_AMBIT_1", ambit.getCategoriaInfraestructura());
        assertEquals(ambit.hashCode(), copy.hashCode());
    }

    @Test
    void testSerialize() throws JsonProcessingException, JSONException {
        String[] attrs = new String[] { attr("idAmbit", 1L), attr("nom", "Ambit 1"), attr("codi", "CODI_AMBIT_1"),
                attr("categoriaInfraestructura", ambit.getCategoriaInfraestructura(), objectMapper) };
        String expected = String.format("{ %s }", String.join(", ", attrs));
        String actual = objectMapper.writeValueAsString(ambit);
        JSONAssert.assertEquals(expected, actual, true);
    }

    @Test
    void testSerializeNullAttributes() throws JsonProcessingException, JSONException {
        Ambit a = new Ambit(23L);
        String expected = "{" + attr("idAmbit", 23) + "}";
        String actual = objectMapper.writeValueAsString(a);
        JSONAssert.assertEquals(expected, actual, true);
    }

}
