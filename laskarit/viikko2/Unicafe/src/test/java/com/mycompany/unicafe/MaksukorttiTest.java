package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void korttiToStringToimii() {
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals(1000, kortti.saldo());
    }
    
    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(300);
        assertEquals(1300, kortti.saldo());
    }
    
    @Test
    public void rahanOttaminenVahentaaSaldoaOikein() {
        kortti.otaRahaa(400);
        assertEquals(600, kortti.saldo());
    }
    
    @Test
    public void saldoEiMuutuJosRahaaEiTarpeeksi() {
        kortti.otaRahaa(1100);
        assertEquals(1000, kortti.saldo());
    }
    
    @Test
    public void palauttaaTrueJosRahatRiittavat() {
        assertEquals(true, kortti.otaRahaa(600));
    }
    
    @Test
    public void palauttaaFalseJosRahatEiRiita() {
        assertEquals(false, kortti.otaRahaa(1600));
    }
}
