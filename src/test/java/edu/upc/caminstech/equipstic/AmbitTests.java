package edu.upc.caminstech.equipstic;

import static edu.upc.caminstech.equipstic.fixtures.JsonFixtures.attr;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AmbitTests {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private Ambit ambit;

    @Before
    public void setUp() throws Exception {
        ambit = ambitFixture();
    }

    private Ambit ambitFixture() {
        Categoria c = new Categoria(10, "Nom categoria", "CODI_CATEGORIA_10");
        return new Ambit(1, "Ambit 1", "CODI_AMBIT_1", c);
    }

    @Test
    public void testAmbit() {
        assertNotNull(ambit);
    }

    @Test
    public void testGetIdAmbit() {
        assertEquals(1, ambit.getIdAmbit());
    }

    @Test
    public void testGetNom() {
        assertEquals("Ambit 1", ambit.getNom());
    }

    @Test
    public void testToString() {
        String s = ambit.toString();
        assertTrue(StringUtils.substringMatch(s, 0, "[Ambit "));
    }

    @Test
    public void testEquals() {
        Ambit copy = new Ambit(1, "Ambit 1", "CODI_AMBIT_1", ambit.getCategoriaInfraestructura());
        assertEquals(copy, ambit);
    }

    @Test
    public void testNotEquals() {
        Ambit a = new Ambit(2, "Ambit 2", "CODI_AMBIT_2", ambit.getCategoriaInfraestructura());
        assertNotEquals(ambit, a);
    }

    @Test
    public void testHashCode() {
        Ambit copy = new Ambit(1, "Ambit 1", "CODI_AMBIT_1", ambit.getCategoriaInfraestructura());
        assertEquals(ambit.hashCode(), copy.hashCode());
    }

    @Test
    public void testSerialize() throws JsonProcessingException {
        String[] attrs = new String[] { attr("idAmbit", 1L), attr("nom", "Ambit 1"), attr("codi", "CODI_AMBIT_1"),
                attr("categoriaInfraestructura", ambit.getCategoriaInfraestructura(), objectMapper) };
        String expected = String.format("{ %s }", String.join(", ", attrs));
        String actual = objectMapper.writeValueAsString(ambit);
        JSONAssert.assertEquals(expected, actual, true);
    }

    @Test
    public void testSerializeNullAttributes() throws JsonProcessingException {
        Ambit a = new Ambit(23L);
        String expected = "{" + attr("idAmbit", 23) + "}";
        String actual = objectMapper.writeValueAsString(a);
        JSONAssert.assertEquals(expected, actual, true);
    }

}
