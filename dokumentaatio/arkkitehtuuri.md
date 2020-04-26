# Arkkitehtuurikuvaus

## Pakkausrakenne

![pakkausrakenne](https://github.com/matiastamsi/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/pakkausrakenne.jpg)

## Luokkakaavio

![luokkakaavio](https://github.com/matiastamsi/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/luokkakaavio.jpg)

## Sekvenssikaaviot

### Pelin aloitus ja paikan vaihto

![sekvenssikaavio pelin aloittamisesta](https://github.com/matiastamsi/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/sekvenssikaavio_pelin_aloitus.png)

## Tiedontallennus

Sovellus käyttää tiedontallennuksessa tietokantaa, josta pakkauksen flyfishinggame.dao luokka FlyfishingDao vastaa.
Luokka luo uuden tietokannan (jos sitä ei ole vielä luotu kansioon FlyfishinGame), jonne se luo yhden taulun _Scores_

![tietokanta](https://github.com/matiastamsi/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/db.png)

### CREATE TABLE -komennot

    CREATE TABLE Scores(
      id INTEGER PRIMARY KEY
      nickname TEXT
      score INTEGER
      )
