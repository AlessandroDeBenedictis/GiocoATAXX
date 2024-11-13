package it.uniba.app.gioco;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniba.app.tavoliere.Tavoliere;

/**
 * Classe di test per Spostamento.
 */
class SpostamentoTest {

    private static final char GIOCATORE = '\u26C0'; // Giocatore 1
    private static final int COORDINATA_0 = 0;
    private static final int COORDINATA_2 = 2;
    private static final int COORDINATA_5 = 5;

    private Tavoliere tavoliere;
    private Spostamento spostamento;

    /**
     * Inizializza il tavoliere e lo spostamento prima di ogni test.
     */
    @BeforeEach
    void setUp() {
        tavoliere = new Tavoliere();
        spostamento = new Spostamento(tavoliere);
    }

    /**
     * Testa il metodo isMossaValida.
     */
    @Test
    void testIsMossaValidaOrizzontale() {
        tavoliere.posizionaPedina(COORDINATA_2, COORDINATA_2, GIOCATORE, false);

        assertTrue(spostamento.isMossaValida("c3", "a3", GIOCATORE), "Il movimento orizzontale dovrebbe essere valido");
    }
      /**
     * Testa il metodo isMossaValida.
     */
    @Test
    void testIsMossaValidaVerticale() {
        tavoliere.posizionaPedina(COORDINATA_2, COORDINATA_2, GIOCATORE, false);
        assertTrue(spostamento.isMossaValida("c3", "c1", GIOCATORE), "Il movimento verticale dovrebbe essere valido");
    }
        /**
     * Testa il metodo isMossaValida.
     */
    @Test
    void testIsMossaValidaDiagonale() {
        tavoliere.posizionaPedina(COORDINATA_2, COORDINATA_2, GIOCATORE, false);
        assertTrue(spostamento.isMossaValida("c3", "e5", GIOCATORE), "Il movimento diagonale dovrebbe essere valido");
    }

    /**
     * Testa il metodo isMossaNonValida.
     */
    @Test
    void testIsMossaNonValida() {
        tavoliere.posizionaPedina(COORDINATA_2, COORDINATA_2, GIOCATORE, false);
        assertFalse(spostamento.isMossaValida("c3", "c6", GIOCATORE),
        "Il movimento troppo lungo dovrebbe essere invalido");
    }
    /**
     * Testa il metodo isMossaNonValida.
     */
    @Test
    void testIsMossaNonValidatroppolungo() {
        tavoliere.posizionaPedina(COORDINATA_2, COORDINATA_2, GIOCATORE, false);
        assertFalse(spostamento.isMossaValida("c3", "f3", GIOCATORE),
        "Il movimento troppo lungo dovrebbe essere invalido");
    }
    /**
     * Testa il metodo isMossaNonValida.
     */
    @Test
    void testIsMossaNonValidafuoritavoliere() {
        tavoliere.posizionaPedina(COORDINATA_2, COORDINATA_2, GIOCATORE, false);
        assertFalse(spostamento.isMossaValida("j3", "d4", GIOCATORE),
        "Il movimento invalido dovrebbe essere invalido");
    }

     /**
     * Testa il metodo eseguiMossa.
     */
    @Test
    void testEseguiMossa() {
        tavoliere.posizionaPedina(COORDINATA_2, COORDINATA_2, GIOCATORE, false);
        assertTrue(spostamento.eseguiMossa("c3", "a3", GIOCATORE), "Il movimento orizzontale dovrebbe essere valido");
    }
    /**
     * Testa il metodo eseguiMossa.
     */
    @Test
    void testEseguiMossagiocatore() {
        tavoliere.posizionaPedina(COORDINATA_2, COORDINATA_2, GIOCATORE, false);
        assertEquals(GIOCATORE, tavoliere.getPedina(COORDINATA_0, COORDINATA_0),
         "La pedina dovrebbe essere spostata nella nuova posizione");
    }
    /**
     * Testa il metodo eseguiMossa.
     */
    @Test
    void testEseguiMossavuota() {
        tavoliere.posizionaPedina(COORDINATA_2, COORDINATA_2, GIOCATORE, false);
        spostamento.eseguiMossa("c3", "a3", GIOCATORE);
        assertEquals('-', tavoliere.getPedina(COORDINATA_2, COORDINATA_2),
         "La posizione di partenza dovrebbe essere vuota");
    }
    /**
     * Testa il metodo eseguiMossaNonValida.
     */
    @Test
    void testEseguiMossaNonValida() {
        tavoliere.posizionaPedina(COORDINATA_2, COORDINATA_2, GIOCATORE, false);

        assertFalse(spostamento.eseguiMossa("c3", "c6", GIOCATORE),
        "La mossa invalida non dovrebbe essere eseguita");
    }
     /**
     * Testa il metodo eseguiMossaNonValida.
     */
    @Test
    void testEseguiMossaNonValidaposizioneoriginale() {
        tavoliere.posizionaPedina(COORDINATA_2, COORDINATA_2, GIOCATORE, false);
        assertEquals(GIOCATORE, tavoliere.getPedina(COORDINATA_2, COORDINATA_2),
        "La pedina dovrebbe rimanere nella posizione originale");
    }
     /**
     * Testa il metodo eseguiMossaNonValida.
     */
    @Test
    void testEseguiMossaNonValidavuoto() {
        tavoliere.posizionaPedina(COORDINATA_2, COORDINATA_2, GIOCATORE, false);
        assertEquals('-', tavoliere.getPedina(COORDINATA_5, COORDINATA_2),
        "La destinazione invalida dovrebbe rimanere vuota");
    }
}
