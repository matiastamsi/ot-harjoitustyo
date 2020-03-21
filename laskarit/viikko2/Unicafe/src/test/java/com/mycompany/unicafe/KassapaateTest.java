
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class KassapaateTest {
    Kassapaate kassapaate;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
        kortti = new Maksukortti(1000);
    }
    
    @Test
    public void luodunKassapaatteenRahamaaraOikea() {
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void luodullaKassapaatteellaLounaitaEiVielaMyyty() {
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty()+
                kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kassanRahamaaraKasvaaEdullisenLounaanOsaltaKateisella() {
        kassapaate.syoEdullisesti(250);
        assertEquals(100240, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void kassanRahamaaraKasvaaMaukkaanLounaanOsaltaKateisella() {
        kassapaate.syoMaukkaasti(500);
        assertEquals(100400, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void maksuEdulliseenRiittavaJotenLounaidenMaaraKasvaa() {
        kassapaate.syoEdullisesti(250);
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maksuMaukkaaseenRiittavaJotenLounaidenMaaraKasvaa() {
        kassapaate.syoMaukkaasti(400);
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void maksuEdulliseenEiRiittavaSaldoEiMuutu() {
        kassapaate.syoEdullisesti(100);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void maksuMaukkaaseenEiRiittavaSaldoEIMuutu() {
        kassapaate.syoMaukkaasti(300);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void maksuEdulliseenEiRiittavaMyyntiEiKasva() {
        kassapaate.syoEdullisesti(100);
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maksuMaukkaaseenEiRiittavaMyyntiEiKasva() {
        kassapaate.syoMaukkaasti(300);
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    
    @Test
    public void kortillaVoiOstaaEdullisesti() {
        assertEquals(true, kassapaate.syoEdullisesti(kortti));
    }
    
    @Test
    public void kortillaVoiOstaaMaukkaasti() {
        assertEquals(true, kassapaate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void kortillaEiVoiOstaaEdullisesti() {
        kassapaate.syoEdullisesti(kortti);
        kassapaate.syoEdullisesti(kortti);
        kassapaate.syoEdullisesti(kortti);
        kassapaate.syoEdullisesti(kortti);
        assertEquals(false, kassapaate.syoEdullisesti(kortti));
    }
    
    @Test
    public void kortillaEiVoiOstaaMaukkaasti() {
        kassapaate.syoMaukkaasti(kortti);
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(false, kassapaate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void kortillaVoiOstaaEdullisestiKortinSaldoMuuttuu() {
        kassapaate.syoEdullisesti(kortti);
        assertEquals(760, kortti.saldo());
    }
    
    @Test
    public void kortillaVoiOstaaMaukkaastiKortinSaldoMuuttuu() {
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(600, kortti.saldo());
    }
    
    @Test
    public void kortillaTarpeeksiRahaaJotenMyydytLounaatKasvaa() {
        kassapaate.syoEdullisesti(kortti);
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(2, kassapaate.edullisiaLounaitaMyyty()+
                kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kortillaEiTarpeeksiRahaaJotenMyydytLounaatEiKasva() {
        kassapaate.syoEdullisesti(kortti);
        kassapaate.syoMaukkaasti(kortti);
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(2, kassapaate.edullisiaLounaitaMyyty()+
                kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kortillaVoiOstaaEdullisestiEikaKassanSaldoMuuttuu() {
        kassapaate.syoEdullisesti(kortti);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    @Test
    public void kortillaVoiOstaaMaukkastiEikaKassanSaldoMuuttuu() {
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void lataaRahaaKortilleToimii() {
        kassapaate.lataaRahaaKortille(kortti, 100);
        assertEquals(100100, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void kortilleEiVoiLadataNegatiivistaSummaa() {
        kassapaate.lataaRahaaKortille(kortti, -100);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
}
