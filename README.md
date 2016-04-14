# equipstic-client-java
Un client java per a la API d'Equips TIC de la UPC.

## Requeriments

- Java >= 1.7 (per Spring Boot)
- Maven >= 3

## Quickstart

Situeu-vos al directori on es troba el fitxer pom.xml, i executeu

    mvn package

Al subdirectori `target/` s'hauria d'haver generat el `.jar` que haureu d'incloure al classpath de la vostra aplicació.

## Tests

Si voleu executar els tests d'integració (deshabilitat per defecte),
reviseu el fitxer `runClientTests.sh`, definiu les variables d'entorn segons pertoqui, i executeu-lo.
