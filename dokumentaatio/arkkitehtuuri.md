# Arkkitehtuurikuvaus

## Pakkausrakenne

![pakkausrakenne](https://github.com/matiastamsi/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/pakkausrakenne.jpg)

## Luokkakaavio

![luokkakaavio](https://github.com/matiastamsi/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/luokkakaavio.jpg)

## Sekvenssikaaviot

![sekvenssikaavio pelin kulusta](https://github.com/matiastamsi/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/sekvenssikaavio.png)

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
 
## Sovelluksen rakenteeseen jääneet heikkoudet

Luokkia olisi ollut hyvä rakentaa alusta alkaen JavaFx:stä riippumattomaksi (mikään muu luokka ei koske JavaFx:n komponentteihin paitsi FlyfishingUi), koska tästä on harmia. Esimerkiksi testaus on vaikeampaa ja jos JavaFx:n haluaa korvata jokin päivä, sen riittäisi korvata vain käyttöliittymäluokassa.
 
