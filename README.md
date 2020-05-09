# FlyfishingGame

FlyfishingGame-sovellus on peli, jossa perhokalastetaan. Perhokalastukselle tyypillisesti kalastus tapahtuu koskessa.

Koski ei ole suinkaan hitaasti virtaava joenpätkä, vaan kivinen ja kuohuva. Pelaajan on osattava heittää tarkasti sekä lukea koskea.

Pelaaja saa saaleistaan pisteitä, joita voikin vertailla toisten pelaajien suorituksiin.

## Dokumentaatio

[Vaativuusmäärittely](https://github.com/matiastamsi/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/matiastamsi/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Käyttöohje](https://github.com/matiastamsi/ot-harjoitustyo/blob/master/dokumentaatio/käyttöohje.md)

[Testausdokumentti](https://github.com/matiastamsi/ot-harjoitustyo/blob/master/dokumentaatio/testausdokumentti.md)

[Työaikakirjanpito](https://github.com/matiastamsi/ot-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)

## Releaset

[Viikko 5](https://github.com/matiastamsi/ot-harjoitustyo/releases/tag/viikko5)

[Viikko 6](https://github.com/matiastamsi/ot-harjoitustyo/releases/tag/viikko6)

[Loppupalautus](https://github.com/matiastamsi/ot-harjoitustyo/releases/tag/loppupalautus)

## Komentorivitoiminnot

### Testaaminen

Testit suoritetaan komennolla:

    mvn test
    
Testikattavuusraportti luodaan komennolla:

    mvn jacoco:report
          
(Sitä voi tarkastella selaimessa avaamalla tiedosto _target/site/jacoco/index.html_.)

## Suoritettavan jarin generointi

Komennolla: 

    mvn package
    
hakemistoon target generoituu suoritettava jar-tiedosto nimeltä _FlyfishingGame-1.0-SNAPSHOT.jar_.

## Checkstyle

Checkstyle tarkistukset onnistuvat komennolla:

    mvn jxr:jxr checkstyle:checkstyle
  
ja tuloksia voi tarkastella avaamalla tiedoston _target/site/checkstyle.html_ selaimessa.

## JavaDoc

JavaDoc:in saa generoitua komennolla:

    mvn javadoc:javadoc

Tuloksia voi tarkastella selaimessa avaamalla tiedoston _target/site/apidocs/index.html_.


