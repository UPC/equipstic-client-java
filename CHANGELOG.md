
5.2.0 / 2020-10-01
==================

  * refactor: extract interface

5.1.3 / 2020-09-29
==================

  * Update to Java 11

5.1.2 / 2020-09-29
==================

  * Bump spring boot -> 2.3.4
  * Remove log message

5.1.1 / 2020-08-17
==================

  * Fix setter name Infraestructura.setUnitatUTG(u)

5.1.0 / 2020-08-14
==================

  * Update to Spring Boot -> 2.3.3
  * New method Unitat.formattedName()

5.0.0 / 2020-08-13
==================

  * Upgrade to Spring Boot 2.3.2
  * Added suport for EquipsTIC API 1.8.1, with the following new attributes:
    - Estat: acronim
    - Infraestructura: mac, ip, condicionsGarantia, financament, unitatUTG, nomDns
    - Unitat: utg

4.1.0 / 2020-05-26
==================

  * Bump version in maven POM -> 4.1.0
  * Refactor: Add methods to check for required attributes

v4.0.0 / 2020-03-04
===================

  * Actualitza a Spring Boot 2.1.13.RELEASE
  * refactor: reusa Setter
  * fix: trim Marca
  * sonarLint
  * sonarLint
  * sonarLInt
  * genera missatge de log en l'excepció
  * fix: maven surefire plugin exception

v2.3.0 / 2017-05-18
===================

* Les operacions del client que podien retornar null ara retornen un  Optional.

v2.2.1 / 2017-05-05
===================

* Aquesta versió és la "v2.2.1" ja que, erròniament, es va anotar
  la versió "2.0.0" amb el tag "v2.2.0" en comptes del "v2.0.0".
* S'han afegit més tests.
* S'ha refactoritzat la gestió d'excepcions per tractar de manera uniforme
  alguns errors que no calia distingir entre sí.
* Refactoritzacions per millorar la legibilitat del codi en els tests.

v2.0.0 / 2017-04-28
===================

* Importants refactoritzacions al client (no compatible amb la versió anterior!).
* Es fa servir la APIv2 d'EquipsTIC.
* Millorada la detecció d'errors del servidor.
* Actualitzat a Spring Boot 1.5.2.

v1.2.0 / 2017-02-15
===================

 * Implementades operacions de la API relatives a `UsuariInfraestructura`.
 * S'ha definit un ordre natural per defecte per als tipus `Ambit`, `Categoria`,
 `TipusUs`, `Estat`, `Edifici`, `Campus` i `Unitat`. El client retorna aquests objectes ordenats segons aquest ordre.
 * Conforme a la versió 1.2f de la API d'EquipsTIC.

v1.1.0 / 2017-01-27
==================

 * Primera versió estable i completa.
 * Conforme a la versió 1.2d de la API d'EquipsTIC.

