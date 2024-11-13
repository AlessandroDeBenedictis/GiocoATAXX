package it.uniba.app.tavoliere;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Classe di test per Tavoliere.
 */
class TavoliereTest {

    private Tavoliere tavoliere;

    private static final int DIMENSIONE = 7;
    private static final int COORDINATA_0 = 0;
    private static final int COORDINATA_3 = 3;
    private static final char PEDINA_1 = '\u26C0';
    private static final char PEDINA_2 = '\u26C2';
    private static final char CASELLA_LIBERA = '-';

    /**
     * Inizializza il tavoliere prima di ogni test.
     */
    @BeforeEach
    void setUp() {
        tavoliere = new Tavoliere();
    }

    /**
     * Testa il metodo inizializzaTavoliere.
     */
    @Test
    void testInizializzaTavoliere() {
        tavoliere.inizializzaTavoliere();
        assertEquals(PEDINA_1, tavoliere.getPedina(COORDINATA_0, COORDINATA_0),
        "La pedina alla posizione iniziale dovrebbe essere PEDINA_1");
    }

    /**
     * Testa il metodo getDimensione.
     */
    @Test
    void testGetDimensione() {
        assertEquals(DIMENSIONE, tavoliere.getDimensione(),
        "La dimensione del tavoliere dovrebbe essere " + DIMENSIONE);
    }

    /**
     * Testa il metodo isCasellaLibera.
     */
    @Test
    void testIsCasellaLibera() {
        assertTrue(tavoliere.isCasellaLibera(COORDINATA_3, COORDINATA_3),
        "La casella alla posizione (3, 3) dovrebbe essere libera");
    }

    /**
     * Testa il metodo bloccaCasella.
     */
    @Test
    void testBloccaCasella() {
        assertTrue(tavoliere.bloccaCasella("d4"), "La casella d4 dovrebbe essere bloccata");
    }

    /**
     * Testa il metodo notazioneAlgebricaToCoord.
     */
    @Test
    void testNotazioneAlgebricaToCoord() {
        int[] coords = tavoliere.notazioneAlgebricaToCoord("d4");
        assertArrayEquals(new int[]{COORDINATA_3, COORDINATA_3},
        coords, "Le coordinate per d4 dovrebbero essere (3, 3)");
    }

    /**
     * Testa il metodo posizionaPedina.
     */
    @Test
    void testPosizionaPedina() {
        tavoliere.posizionaPedina(COORDINATA_3, COORDINATA_3, PEDINA_2, true);
        assertEquals(PEDINA_2, tavoliere.getPedina(COORDINATA_3, COORDINATA_3),
        "La pedina alla posizione (3, 3) dovrebbe essere PEDINA_2");
    }

    /**
     * Testa il metodo rimuoviPedina.
     */
    @Test
    void testRimuoviPedina() {
        tavoliere.posizionaPedina(COORDINATA_3, COORDINATA_3, PEDINA_2, true);
        tavoliere.rimuoviPedina(COORDINATA_3, COORDINATA_3);
        assertEquals(CASELLA_LIBERA, tavoliere.getPedina(COORDINATA_3, COORDINATA_3),
        "La casella alla posizione (3, 3) dovrebbe essere libera dopo la rimozione della pedina");
    }

    /**
     * Testa il metodo aggiornaStato.
     */
    @Test
    void testAggiornaStato() {
        tavoliere.inizializzaTavoliere();
        tavoliere.aggiornaStato();
        assertNotNull(tavoliere.toString(), "Il metodo non dovrebbe restituire una stringa non nulla");
        // Non ci sono asserzioni specifiche da fare qui, poiché si verifica solo che il metodo non lanci eccezioni.
    }

    /**
     * Testa il metodo getPedina.
     */
    @Test
    void testGetPedina() {
        assertEquals(CASELLA_LIBERA, tavoliere.getPedina(COORDINATA_3, COORDINATA_3),
        "La casella alla posizione (3, 3) dovrebbe essere libera di default");
    }
}
