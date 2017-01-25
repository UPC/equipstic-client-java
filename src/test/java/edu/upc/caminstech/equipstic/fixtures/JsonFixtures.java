package edu.upc.caminstech.equipstic.fixtures;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Classe que agrupa mètodes per generar JSON als tests.
 * 
 */
public class JsonFixtures {

    private JsonFixtures() {
    }

    /**
     * Retorna un atribut amb valor de tipus {@code String} en format JSON.
     * 
     * @param name
     *            el nom de l'atribut.
     * @param val
     *            el valor de l'atribut.
     * @return retorna la cadena {@code "<name>": "<val>"}
     */
    public static String attr(String name, String val) {
        return q(name) + ':' + q(val);
    }

    /**
     * Retorna un atribut amb valor de tipus {@code long} en format JSON.
     * 
     * @param name
     *            el nom de l'atribut.
     * @param val
     *            el valor de l'atribut.
     * @return retorna la cadena {@code "<name>": "<val>"}
     */
    public static String attr(String name, long val) {
        return q(name) + ':' + val;
    }

    /**
     * Retorna un atribut amb valor de tipus {@code Object} en format JSON.
     * 
     * @param name
     *            el nom de l'atribut.
     * @param val
     *            el valor de l'atribut.
     * @param objectMapper
     *            l'objectMapper que s'utilitzarà per serialitzar el valor.
     * @return la cadena
     * 
     *         <pre>
     * "&lt;name&gt;": {val}
     *         </pre>
     * 
     * @throws JsonProcessingException
     *             si hi hagués algun problema en serialitzar el valor.
     */
    public static String attr(String name, Object val, ObjectMapper objectMapper) throws JsonProcessingException {
        return q(name) + ':' + objectMapper.writeValueAsString(val);
    }

    private static String q(String s) {
        return '"' + s + '"';
    }

}
