# equipstic-client-java
Un client java per a la API d'Equips TIC de la UPC.

## Requeriments

- Java >= 1.7 (per Spring Boot)
- Maven >= 3

## Quickstart

Compilar el client i instal·lar-lo al vostre repositori local de Maven:

    $ mvn install

Al vostre projecte, indiqueu la dependència del client en el vostre fitxer `pom.xml`:

    <dependency>
        <groupId>edu.upc.caminstech</groupId>
        <artifactId>equipstic-java</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </dependency>

## Tests

Podeu executar tests unitaris del client amb:

    $ mvn test

Si voleu executar els tests d'integració (deshabilitat per defecte),
reviseu el fitxer `runClientTests.sh`, definiu les variables d'entorn segons pertoqui, i executeu-lo.
