package it.uniba.app.gioco;

import it.uniba.app.tavoliere.Tavoliere;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class AdiacenteTest {

    private Tavoliere tavoliere;
    private Adiacente adiacente;

    @BeforeEach
    void setUp() {
        tavoliere = new Tavoliere();
        adiacente = new Adiacente(tavoliere);
    }

    @Test
    void testIsMossaValidaPosizioneValida() {
        // Setup
        String from = "a1";
        String to = "a2";
        char giocatore = '\u26C0'; // Giocatore 1

        // Verifica che la mossa è valida
        assertTrue(adiacente.isMossaValida(from, to, giocatore), "La mossa è valida");
    }

    @Test
    void testIsMossaValidaPosizioneNonValida() {
        // Setup
        String from = "a1";
        String to = "a3";
        char giocatore = '\u26C0'; // Giocatore 1

        // Verifica che la mossa non è valida
        assertFalse(adiacente.isMossaValida(from, to, giocatore), "La mossa non è valida");
    }

    @Test
    void testEseguiMossaPosizioneValida() {
        // Setup
        String from = "a1";
        String to = "a2";
        char giocatore = '\u26C0'; // Giocatore 1

        // Esegue la mossa
        boolean esito = adiacente.eseguiMossa(from, to, giocatore);

        // Verifica che la mossa è stata eseguita
        assertTrue(esito, "La mossa è stata eseguita");
    }

    @Test
    void testEseguiMossaPosizioneNonValida() {
        // Setup
        String from = "a1";
        String to = "a3";
        char giocatore = '\u26C0'; // Giocatore 1

        // Esegue la mossa
        boolean esito = adiacente.eseguiMossa(from, to, giocatore);

        // Verifica che la mossa non è stata eseguita
        assertFalse(esito, "La mossa non è stata eseguita");
    }

    @Test
    void testLogMossa() {
        // Setup
        String from = "a1";
        String to = "a2";
        char giocatore = '\u26C0'; // Giocatore 1

        // Esegue la mossa e registra nel log
        adiacente.eseguiMossa(from, to, giocatore);

        // Verifica che la mossa è stata registrata nel log
        assertEquals("Giocatore 1 (Bianco): a1-a2", adiacente.getLogMosse().get(0),
         "La mossa è stata registrata nel log");
    }

    @Test
    void testNotazioneAlgebricaToCoordPosizioneValida() {
        // Setup
        String notazione = "a1";
        // Verifica che la conversione è corretta
        int[] coords = adiacente.notazioneAlgebricaToCoord(notazione);
        assertArrayEquals(new int[]{0, 0}, coords, "La conversione è corretta");
    }

    @Test
    void testNotazioneAlgebricaToCoordPosizioneNonValida() {
        // Setup
        String notazione = "z9";

        // Verifica che la conversione restituisce null
        assertNull(adiacente.notazioneAlgebricaToCoord(notazione), "La conversione restituisce null");
    }
}
