package edu.upc.caminstech.equipstic.client.dao;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import edu.upc.caminstech.equipstic.client.EquipsTicClientConfiguration;

public class RestDaoTests {

    private static final String BASE_URI_STR = "https://example.com/api";

    private RestDao restDao;
    private EquipsTicClientConfiguration cfg;

    @Before
    public void setUp() throws Exception {
        cfg = createConfigurationFixture(BASE_URI_STR);
        restDao = new RestDao(cfg);
    }

    @Test
    public void testGetBaseUri() {
        URI expected = URI.create("http://equipstic.com/api");
        EquipsTicClientConfiguration cfg = createConfigurationFixture(expected.toString());
        RestDao dao = new RestDao(cfg);

        URI uri = dao.getBaseUri();

        assertEquals(expected, uri);
    }

    @Test
    public void testGetRestTemplate() {
        RestTemplate rt = restDao.getRestTemplate();

        assertNotNull(rt);
    }

    private EquipsTicClientConfiguration createConfigurationFixture(String baseUri) {
        try {
            return new EquipsTicClientConfiguration(baseUri, "username", "password");
        } catch (URISyntaxException e) {
            // això no passarà en aquest cas
            return null;
        }
    }

}
