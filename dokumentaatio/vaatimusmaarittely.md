# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovellus on perhokalastuspeli. Perhokalastukselle tyypillisesti kalastus tapahtuu koskessa.

## Käyttäjät

Sovelluksella on vain yksi käyttäjärooli eli pelaaja. 

## Perusversion tarjoama toiminnallisuus

### Ennen kirjautumista

- käyttäjä voi luoda uuden käyttäjätunnuksen
	- käyttäjätunnuksen on oltava uniikki ja vähintään 5 merkkiä
- käyttäjä voi kirjautua järjestelmään
	- kirjautuminen onnistuu olemassa olevalla käyttäjätunnuksella
	- jos käyttäjätunnusta ei löydy, ilmoittaa järjestelmä tästä

### Kirjautumisen jälkeen

- käyttäjälle avautuu pelinäkymä (virtaava koski) jossa:
	- kiviä
	- kuplia
	- lehtiä
- järjestelmä hakee käyttäjätunnuksen maksimipisteet
- käyttäjä voi vaihtaa kalastuspaikkaa
- heitellä perhoa minne haluaa
- napata kalan kun kala syö
- pelinäkymässä näkyy pelikerran pisteet, jotka kasvavat saaliin painon mukaan
- käyttäjä voi kirjautua ulos, jolloin pisteet tallentuvat, jos ylittävät ennätyksen

## Jatkokehitysideoita

Perusversiota jatkokehitetään ajansalliessa mm.
- käyttäjälle näkyy pelin esimerkiksi TOP 3 maksimipistettä ja niiden tekijät
- joki ja toiminnallisuudet näyttävät mahdollisimman aidolta
- kalat sijoittuvat varsinkin paikkoihin, jotka vastaavat luonnollista tapaa (kivien muodostamiin virtauksiin)
- tehokkuus ja toimintavarmuus
