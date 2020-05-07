# Käyttöohje

## Konfigurointi

Ohjelma olettaa, että juurikansion config.properties-tiedostossa on määritettynä koot. Jos näin ei ole, ohjelma käyttää oletus kokoja. 

	WIDTH = 960
	HEIGHT = 525
	bubbleSize = 7
	leafSize = 0
	rockSize = 20
	speedRange = 1
	fishSize = 15
	thinLineSize = 0.5
	thickLineSize = 1.5

## Ohjelman käynnistäminen

Ohjelman saa käyntiin komennolla

    java -jar flyfishinggame.jar


## Pelin aloitus

Pelin alussa toivotetaan tervetulleeksi peliin sekä käydään hiukan läpi ohjeita. Lisäksi annetaan nimimerkki, jolla pelataan. 

![pelin aloitus](https://github.com/matiastamsi/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/pelin_aloitus.png)

## Pelinäkymä

Pelinäkymässä oikeassa yläkulmassa näkyy pelikerran pisteet (pisteitä saa saaduista kaloista) sekä nimimerkki. Lisäksi siellä on listattuna TOP SCORES eli parhaat pisteet ja niiden suorittaneet pelaajat. Maksimissaan näytetään viisi parasta tulosta.

Pelinäkymän yläosassa on ohjeita pelaajalle liittyen heittämiseen sekä kalan koukuttamiseen.

![pelinäkymä](https://github.com/matiastamsi/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/pelinakyma.png)

Vasemmassa yläkulmassa puolestaan on nappi josta voi vaihtaa paikkaa.

## Paikan vaihtaminen

Painamalla _Change spot_-nappia pääsee uudelle koskenpätkälle (kivet eri paikoissa, kalat eri paikoissa, virtaus erilainen yms.)

![paikanvaihto](https://github.com/matiastamsi/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/change_spot.png)

## Heittäminen

Heittää voi klikkaamalla hiiren vasemmalla näppäimellä sieltä minne haluaa heittää.

![heittäminen](https://github.com/matiastamsi/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/heitto.png)

## Kala syö

Kalat pomppivat välillä koskessa syöden hyönteisiä. Sitä ei pidä sekoittaa siihen, kun kala syö juuri pelaajan perhoa.

Kun perho on vedessä ja kala syö perhon kohdalla (noin siiman päässä), pelaajan tulee klikata C-näppäintä koukuttaakseen kalan.

![kala syö](https://github.com/matiastamsi/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kala_syo.png)

## Pisteiden kasvu

Jos pelaaja saa kalan koukkuun saa hän pisteitä kalan pituuden mukaan.

![pisteet kalasta](https://github.com/matiastamsi/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/pisteet_kalasta.png)

## Tulostaulu

Tulostauluun päivittyy tuloksia sitä mukaan kun kalastetaan.

![tulokset](https://github.com/matiastamsi/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/tulokset.png)

