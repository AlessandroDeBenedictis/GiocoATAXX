package it.uniba.app.gioco;

import it.uniba.app.tavoliere.Tavoliere;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MossaBaseTest {

    private Tavoliere tavoliere;
    private MossaBase mossaBase;

    private static final int COORDINATA_0 = 0;
    private static final int COORDINATA_1 = 1;
    private static final int COORDINATA_2 = 2;
    private static final int COORDINATA_3 = 3;
    private static final int COORDINATA_4 = 4;
    private static final int COORDINATA_6 = 6;
    private static final char GIOCATORE = '\u26C0';
    private static final char AVVERSARIO = '\u26C2';

    @BeforeEach
    void setUp() {
        tavoliere = new Tavoliere();
        mossaBase = new MossaBase(tavoliere);
    }

    @Test
    void testCoordinateToNotazioneAlgebricaA1() {
        assertEquals("a1", mossaBase.coordinateToNotazioneAlgebrica(COORDINATA_0, COORDINATA_0),
        "La conversione di (0, 0) dovrebbe restituire 'a1'");
    }
    @Test
    void testCoordinateToNotazioneAlgebricaB2() {
        assertEquals("b2", mossaBase.coordinateToNotazioneAlgebrica(COORDINATA_1, COORDINATA_1),
        "La conversione di (1, 1) dovrebbe restituire 'b2'");
    }
    @Test
    void testCoordinateToNotazioneAlgebricaG7() {
        assertEquals("g7", mossaBase.coordinateToNotazioneAlgebrica(COORDINATA_6, COORDINATA_6),
        "La conversione di (6, 6) dovrebbe restituire 'g7'");
    }

    @Test
    void testCatturaPedineAdiacenti() {
        // Setup
        tavoliere.posizionaPedina(COORDINATA_2, COORDINATA_2, AVVERSARIO, false);
        tavoliere.posizionaPedina(COORDINATA_2, COORDINATA_3, AVVERSARIO, false);
        tavoliere.posizionaPedina(COORDINATA_3, COORDINATA_2, AVVERSARIO, false);
        tavoliere.posizionaPedina(COORDINATA_3, COORDINATA_4, AVVERSARIO, false);
        tavoliere.posizionaPedina(COORDINATA_4, COORDINATA_2, AVVERSARIO, false);
        tavoliere.posizionaPedina(COORDINATA_4, COORDINATA_3, AVVERSARIO, false);
        tavoliere.posizionaPedina(COORDINATA_4, COORDINATA_4, AVVERSARIO, false);

        // Execute
        mossaBase.catturaPedineAdiacenti(COORDINATA_3, COORDINATA_3, GIOCATORE);

        // Verify
        assertEquals(GIOCATORE, tavoliere.getPedina(COORDINATA_2, COORDINATA_2),
        "La pedina dovrebbe essere del giocatore a (2, 2)");
    }
    @Test
    void testCatturaPedineAdiacenti44() {
        // Setup
        tavoliere.posizionaPedina(COORDINATA_2, COORDINATA_2, AVVERSARIO, false);
        tavoliere.posizionaPedina(COORDINATA_2, COORDINATA_3, AVVERSARIO, false);
        tavoliere.posizionaPedina(COORDINATA_3, COORDINATA_2, AVVERSARIO, false);
        tavoliere.posizionaPedina(COORDINATA_3, COORDINATA_4, AVVERSARIO, false);
        tavoliere.posizionaPedina(COORDINATA_4, COORDINATA_2, AVVERSARIO, false);
        tavoliere.posizionaPedina(COORDINATA_4, COORDINATA_3, AVVERSARIO, false);
        tavoliere.posizionaPedina(COORDINATA_4, COORDINATA_4, AVVERSARIO, false);

        // Execute
        mossaBase.catturaPedineAdiacenti(COORDINATA_3, COORDINATA_3, GIOCATORE);

        // Verify
        assertEquals(GIOCATORE, tavoliere.getPedina(COORDINATA_4, COORDINATA_4),
        "La pedina dovrebbe essere del giocatore a (4, 4)");
    }
    @Test
    void testCatturaPedineAdiacenti23() {
        // Setup
        tavoliere.posizionaPedina(COORDINATA_2, COORDINATA_2, AVVERSARIO, false);
        tavoliere.posizionaPedina(COORDINATA_2, COORDINATA_3, AVVERSARIO, false);
        tavoliere.posizionaPedina(COORDINATA_3, COORDINATA_2, AVVERSARIO, false);
        tavoliere.posizionaPedina(COORDINATA_3, COORDINATA_4, AVVERSARIO, false);
        tavoliere.posizionaPedina(COORDINATA_4, COORDINATA_2, AVVERSARIO, false);
        tavoliere.posizionaPedina(COORDINATA_4, COORDINATA_3, AVVERSARIO, false);
        tavoliere.posizionaPedina(COORDINATA_4, COORDINATA_4, AVVERSARIO, false);
        // Execute
        mossaBase.catturaPedineAdiacenti(COORDINATA_3, COORDINATA_3, GIOCATORE);
        // Verify
        assertEquals(GIOCATORE, tavoliere.getPedina(COORDINATA_2, COORDINATA_3),
        "La pedina dovrebbe essere del giocatore a (2, 3)");
    }
}
