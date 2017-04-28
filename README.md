# equipstic-client-java

Un client Java per a la API d'Equips TIC de la UPC.

## Requeriments

- Java >= 1.8
- Maven >= 3

## Quickstart

Compilar el client i instal·lar-lo al vostre repositori local de Maven:

    $ mvn install

Al vostre projecte, indiqueu la dependència del client en el vostre fitxer `pom.xml`:

    <dependency>
        <groupId>edu.upc.caminstech</groupId>
        <artifactId>equipstic-java</artifactId>
        <version>2.0.0</version>   <!-- canvieu-ho per la versió que toqui -->
    </dependency>

## Documentació

Generar la documentació Javadoc de la llibreria:

    $ mvn javadoc:javadoc

La documentació es generarà dins el directori ``target/site/apidocs/``
(obriu el fitxer ``index.html`` amb el vostre navegador).

## Tests

Podeu executar tests unitaris del client amb:

    $ mvn test

Si voleu executar els tests d'integració (deshabilitat per defecte),
reviseu el fitxer `runIntegrationTests.sh`, definiu les variables d'entorn segons pertoqui, i executeu-lo.
