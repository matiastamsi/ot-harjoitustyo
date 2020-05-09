# Testausdokumentti

## Toteutetut testit

Alla näkyvässä kuvassa näkyy sovelluksen luokille tehdyt testiluokat. Niissä on testattu yksittäisiä luokkaan liittyviä toimintoja kuin myös yhteistoimintaa muiden sovelluksen luokkien kanssa. Näiden testien rivi- ja haarautumakattavuus ovat 100%.

![testiluokat](https://github.com/matiastamsi/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/testiluokat.png)

## Testit joita ei toteutettu

Testeistä on jätetty pois Main-luokka (käynnistää vain sovelluksen), käyttöliittymäpakkaukseen kuuluvat luokat sekä luokat Rapids ja Line. Testauksen ulosjätön syy on (Main-luokkaa lukuunottamatta) JavaFx:n aiheuttama ongelmallisuus testeissä.

## Testaukseen jääneet puutteet

Alunperin oli tarkoitus jättää testien ulkopuolelle vain käyttöliittymään kuuluvat luokat. Kuitenkin jossain määrin käyttöliittymän ja muun sovelluksen toiminnallisuuden eriyttäminen meni metsään JavaFx:n komponenttien osalta, joten luokat Rapids sekä Line jäävät myös ulos testauksesta. Jos aikaa olisi vielä ollut, olisin muokannut näistä luokista JavaFx:ään tukeutumattomat. Valitettavasti huomasin tämän liian myöhään.

## Checkstyle

Checkstyle virheitä jäi muutama. Nämä johtuivat Rapids-luokan (koski) metodien rivimäärien ylittymisestä. Kuitenkin nämä metodit ovat toiminnallisuuden puitteissa kannattaneet toteuttaa näin. Checkstyletestauksen ulkopuolelle on jätetty käyttöliittymän luokat.

![checkstyle](https://github.com/matiastamsi/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/checkstyle.png)
