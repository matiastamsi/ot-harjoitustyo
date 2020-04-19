# FlyfishingGame

FlyfishingGame-sovellus on peli, jossa perhokalastetaan. Perhokalastukselle tyypillisesti kalastus tapahtuu koskessa.

Koski ei ole suinkaan hitaasti virtaava joenpätkä, vaan kivinen ja kuohuva. Pelaajan on osattava heittää tarkasti sekä lukea koskea.

Pelaaja saa saaleistaan pisteitä, joita voikin vertailla toisten pelaajien suorituksiin.

## Dokumentaatio

[Vaativuusmäärittely](https://github.com/matiastamsi/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/matiastamsi/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Työaikakirjanpito](https://github.com/matiastamsi/ot-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)

## Releaset

[Viikko 5](https://github.com/matiastamsi/ot-harjoitustyo/releases/tag/viikko5)

## Komentorivitoiminnot

### Testaaminen

- Testit suoritetaan komennolla _mvn test_.
- Testikattavuusraportti luodaan komennolla _mvn jacoco:report_.
  (Sitä voi tarkastella selaimessa avaamalla tiedosto _target/site/jacoco/index.html_).

## Suoritettavan jarin generointi

- Komennolla _mvn package_ hakemistoon target generoituu suoritettava jar-tiedosto nimeltä _FlyfishingGame-1.0-SNAPSHOT.jar_.

## Checkstyle
- Checkstyle tarkistukset onnistuvat komennolla _mvn jxr:jxr checkstyle:checkstyle_ ja tuloksia voi tarkastella avaamalla tiedoston _target/site/checkstyle.html_ selaimessa.

