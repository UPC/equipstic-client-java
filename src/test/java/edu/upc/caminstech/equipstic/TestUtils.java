package edu.upc.caminstech.equipstic;

import static org.junit.Assume.assumeTrue;

import org.apache.commons.lang3.StringUtils;
import org.junit.AssumptionViolatedException;

/**
 * Algunes funcions genèriques per als tests.
 */
public class TestUtils {

    private TestUtils() {
        throw new UnsupportedOperationException("No s'han de crear instàncies d'aquesta classe.");
    }

    /**
     * Verifica que les variables d'entorn donades estiguin definides.
     * <p>
     * Llença una {@link AssumptionViolatedException} en cas que alguna de les
     * variables no estigui declarada o tingui un valor buit.
     * 
     * @param varNames
     *            els valors de les variables d'entorn a comprovar.
     */
    public static void assumeAllDefined(String... varNames) {
        for (String varName : varNames) {
            assumeDefined(varName);
        }
    }

    private static void assumeDefined(String varName) {
        String message = "Cal definir la variable d'entorn " + varName;
        String value = System.getenv(varName);
        assumeTrue(message, StringUtils.isNotEmpty(value));
    }
}
