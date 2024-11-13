package it.uniba.app.gioco;

import it.uniba.app.tavoliere.Tavoliere;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


/**
 * Classe di test per la classe MosseDisponibili.
 */
class MosseDisponibiliTest {
    private static final char GIOCATORE_1 = '\u26C0';
    private static final char GIOCATORE_2 = '\u26C2';
    private static final char PEDINA_VUOTA = '-';
    private static final int DIMENSIONE_1 = 1;
    private static final int DIMENSIONE_2 = 2;
    private static final int DIMENSIONE_3 = 3;
    private static final int DIMENSIONE_4 = 4;
    private static final int GIOCATORE_1_COUNT = 46;
    private static final int GIOCATORE_1_COUNTINTERO = 26;
    private static final int GIOCATORE_2_COUNT = 23;
    private static final int NUMEROSALTI = 8;
    private static final int NUMEROMOVIMENTI = 6;

    private MosseDisponibili mosseDisponibili;
    private Tavoliere tavoliere;

    /**
     * Inizializza il tavoliere e le mosse disponibili prima di ogni test.
     */
    @BeforeEach
    void setUp() {
        tavoliere = new Tavoliere();
        mosseDisponibili = new MosseDisponibili(tavoliere);
    }

    /**
     * Testa il metodo ripristinaTavoliere.
     */
    @Test
    void testRipristinaTavoliereX() {
        // Imposta alcune pedine di mossa
        tavoliere.posizionaPedina(DIMENSIONE_4, DIMENSIONE_4, 'X', false);

        // Ripristina il tavoliere
        mosseDisponibili.ripristinaTavoliere();

        // Verifica che le pedine di mossa siano state rimosse
        assertEquals(PEDINA_VUOTA, tavoliere.getPedina(DIMENSIONE_4, DIMENSIONE_4),
        "La pedina alla posizione (4, 4) dovrebbe essere rimossa");

    }
     /**
     * Testa il metodo ripristinaTavoliere.
     */
    @Test
    void testRipristinaTavoliereY() {
        // Imposta alcune pedine di mossa
        tavoliere.posizionaPedina(DIMENSIONE_2, DIMENSIONE_2, 'Y', false);

        // Ripristina il tavoliere
        mosseDisponibili.ripristinaTavoliere();

        // Verifica che le pedine di mossa siano state rimosse

        assertEquals(PEDINA_VUOTA, tavoliere.getPedina(DIMENSIONE_2, DIMENSIONE_2),
        "La pedina alla posizione (2, 2) dovrebbe essere rimossa");
    }

    /**
     * Testa il metodo calcolaEMostraMosseDisponibili.
     */
    @Test
    void testCalcolaEMostraMosseDisponibili() {
        tavoliere.posizionaPedina(0, 0, GIOCATORE_1, false);

        List<String> mosse = mosseDisponibili.calcolaEMostraMosseDisponibili(GIOCATORE_1, 0, 0);
        assertTrue(mosse.contains("A1-A2"), "La mossa 'A1-A2' dovrebbe essere presente nella lista delle mosse");
    }
     /**
     * Testa il metodo calcolaEMostraMosseDisponibili.
     */
    @Test
    void testCalcolaEMostraMosseDisponibiliNonVuoto() {
        tavoliere.posizionaPedina(0, 0, GIOCATORE_1, false);

        List<String> mosse = mosseDisponibili.calcolaEMostraMosseDisponibili(GIOCATORE_1, 0, 0);
        assertFalse(mosse.isEmpty(), "La lista delle mosse non dovrebbe essere vuota");
    }

    /**
     * Testa il metodo calcolaMosseDisponibili.
     */
    @Test
    void testCalcolaMosseDisponibiliNonVuoto() {
        // Imposta la pedina del giocatore alla posizione 0,0
        tavoliere.posizionaPedina(0, 0, GIOCATORE_1, false);

        // Calcola le mosse disponibili per la pedina alla posizione 0,0
        List<String> mosse = mosseDisponibili.calcolaMosseDisponibili(GIOCATORE_1, 0, 0);

        // Verifica che la lista delle mosse non sia vuota
        assertFalse(mosse.isEmpty(), "La lista delle mosse non dovrebbe essere vuota");
    }
      /**
     * Testa il metodo calcolaMosseDisponibili.
     */
    @Test
    void testCalcolaMosseDisponibiliNumSalti() {
        // Imposta la pedina del giocatore alla posizione 0,0
        tavoliere.posizionaPedina(0, 0, GIOCATORE_1, false);

        // Calcola le mosse disponibili per la pedina alla posizione 0,0
        List<String> mosse = mosseDisponibili.calcolaMosseDisponibili(GIOCATORE_1, 0, 0);


        // Verifica che il numero di mosse calcolate sia corretto
        assertEquals(NUMEROSALTI, mosse.size(), "Il numero di mosse dovrebbe essere " + NUMEROSALTI);
    }
        /**
     * Testa il metodo calcolaMosseDisponibili.
     */
    @Test
    void testCalcolaMosseDisponibiliPedine() {
        // Imposta la pedina del giocatore alla posizione 0,0
        tavoliere.posizionaPedina(0, 0, GIOCATORE_1, false);

        // Calcola le mosse disponibili per la pedina alla posizione 0,0
        List<String> mosse = mosseDisponibili.calcolaMosseDisponibili(GIOCATORE_1, 0, 0);


        // Verifica che le mossa specifiche è presenti nella lista
        assertTrue(mosse.contains("A2"), "La mossa 'A2' dovrebbe essere presente nella lista delle mosse");
    }

    /**
     * Testa il metodo getPedineMobili.
     */
    @Test
    void testGetPedineMobili() {
        // Posiziona pedine del giocatore 1 in varie posizioni
        tavoliere.posizionaPedina(0, 0, GIOCATORE_1, false);
        tavoliere.posizionaPedina(DIMENSIONE_1, DIMENSIONE_1, GIOCATORE_1, false);
        tavoliere.posizionaPedina(DIMENSIONE_2, DIMENSIONE_2, GIOCATORE_1, false);
        tavoliere.posizionaPedina(DIMENSIONE_3, DIMENSIONE_3, GIOCATORE_1, false);
        tavoliere.posizionaPedina(DIMENSIONE_4, DIMENSIONE_4, GIOCATORE_1, false);

        // Imposta una pedina del giocatore 2 per bloccare una delle pedine del giocatore 1
        tavoliere.posizionaPedina(DIMENSIONE_1, 0, GIOCATORE_2, false);
        tavoliere.posizionaPedina(0, DIMENSIONE_1, GIOCATORE_2, false);

        // Ottieni le pedine mobili per il giocatore 1
        List<String> pedineMobili = mosseDisponibili.getPedineMobili(GIOCATORE_1);

        // Verifica che una delle pedine mobili sia corretta
        assertFalse(pedineMobili.contains("B1"), "La pedina alla posizione (1, 1) dovrebbe essere bloccata");
    }
      /**
     * Testa il metodo getPedineMobili.
     */
    @Test
    void testGetPedineMobiliNumPedineVuote() {
        // Posiziona pedine del giocatore 1 in varie posizioni
        tavoliere.posizionaPedina(0, 0, GIOCATORE_1, false);
        tavoliere.posizionaPedina(DIMENSIONE_1, DIMENSIONE_1, GIOCATORE_1, false);
        tavoliere.posizionaPedina(DIMENSIONE_2, DIMENSIONE_2, GIOCATORE_1, false);
        tavoliere.posizionaPedina(DIMENSIONE_3, DIMENSIONE_3, GIOCATORE_1, false);
        tavoliere.posizionaPedina(DIMENSIONE_4, DIMENSIONE_4, GIOCATORE_1, false);

        // Imposta una pedina del giocatore 2 per bloccare una delle pedine del giocatore 1
        tavoliere.posizionaPedina(DIMENSIONE_1, 0, GIOCATORE_2, false);
        tavoliere.posizionaPedina(0, DIMENSIONE_1, GIOCATORE_2, false);

        // Ottieni le pedine mobili per il giocatore 1
        List<String> pedineMobili = mosseDisponibili.getPedineMobili(GIOCATORE_1);
        // Verifica che il numero di pedine mobili sia corretto
        assertEquals(NUMEROMOVIMENTI, pedineMobili.size(),
        "Il numero di pedine mobili dovrebbe essere " + NUMEROMOVIMENTI);
    }

    /**
     * Testa il metodo visualizzaTavoliereAiuto.
     */
    @Test
    void testVisualizzaTavoliereAiuto() {
        tavoliere.posizionaPedina(DIMENSIONE_3, DIMENSIONE_3, GIOCATORE_1, false);
        mosseDisponibili.visualizzaTavoliereAiuto(GIOCATORE_1);

        // Assicurati che il metodo non lanci eccezioni
        assertDoesNotThrow(() -> mosseDisponibili.visualizzaTavoliereAiuto(GIOCATORE_1),
        "Il metodo visualizzaTavoliereAiuto dovrebbe eseguire senza lanciare eccezioni");
    }

    /**
     * Testa il metodo visualizzaMosseDisponibili.
     */
    @Test
    void testVisualizzaMosseDisponibili() {
        tavoliere.posizionaPedina(DIMENSIONE_3, DIMENSIONE_3, GIOCATORE_1, false);
        mosseDisponibili.visualizzaMosseDisponibili(GIOCATORE_1, DIMENSIONE_3, DIMENSIONE_3);
    // Assicurati che il metodo non lanci eccezioni
    assertDoesNotThrow(() -> mosseDisponibili.visualizzaMosseDisponibili(GIOCATORE_1, DIMENSIONE_3, DIMENSIONE_3),
    "Il metodo visualizzaMosseDisponibili dovrebbe eseguire senza lanciare eccezioni");
}

/**
 * Testa il metodo tutteLeCaselleRiempite.
 */
@Test
void testTutteLeCaselleRiempite() {
    assertFalse(mosseDisponibili.tutteLeCaselleRiempite(),
    "Il metodo tutteLeCaselleRiempite dovrebbe restituire false se il tavoliere non è pieno");
}
/**
 * Testa il metodo tutteLeCaselleRiempite.
 */
@Test
void testTutteLeCaselleRiempitefalso() {
    assertFalse(mosseDisponibili.tutteLeCaselleRiempite(),
    "Il metodo tutteLeCaselleRiempite dovrebbe restituire false se il tavoliere non è pieno");
}

/**
 * Testa il metodo contaPedineSeCaselleRiempite.
 */
@Test
void testContaPedineSeCaselleRiempiteNotNull() {
    for (int i = 0; i < tavoliere.getDimensione(); i++) {
        for (int j = 0; j < tavoliere.getDimensione(); j++) {
            tavoliere.posizionaPedina(i, j, i % 2 == 0 ? GIOCATORE_1 : GIOCATORE_2, false);
        }
    }

    int[] conteggio = mosseDisponibili.contaPedineSeCaselleRiempite();
    assertNotNull(conteggio, "Il conteggio delle pedine non dovrebbe essere null");
}
/**
 * Testa il metodo contaPedineSeCaselleRiempite.
 */
@Test
void testContaPedineSeCaselleRiempiteGiocatore1() {
    for (int i = 0; i < tavoliere.getDimensione(); i++) {
        for (int j = 0; j < tavoliere.getDimensione(); j++) {
            tavoliere.posizionaPedina(i, j, i % 2 == 0 ? GIOCATORE_1 : GIOCATORE_2, false);
        }
    }

    int[] conteggio = mosseDisponibili.contaPedineSeCaselleRiempite();
    assertEquals(GIOCATORE_1_COUNTINTERO, conteggio[0],
    "Il conteggio delle pedine del giocatore 1 dovrebbe essere " + GIOCATORE_1_COUNTINTERO);
}
/**
 * Testa il metodo contaPedineSeCaselleRiempite.
 */
@Test
void testContaPedineSeCaselleRiempiteGiocatore2() {
    for (int i = 0; i < tavoliere.getDimensione(); i++) {
        for (int j = 0; j < tavoliere.getDimensione(); j++) {
            tavoliere.posizionaPedina(i, j, i % 2 == 0 ? GIOCATORE_1 : GIOCATORE_2, false);
        }
    }

    int[] conteggio = mosseDisponibili.contaPedineSeCaselleRiempite();
    assertEquals(GIOCATORE_2_COUNT, conteggio[1],
    "Il conteggio delle pedine del giocatore 2 dovrebbe essere " + GIOCATORE_2_COUNT);
}

/**
 * Testa il metodo contaPedineSeCaselleRiempiteAbbandono.
 */
@Test
void testContaPedineSeCaselleRiempiteAbbandonoGiocatore2() {
    // Questa classe viene utilizzata quando il tabellone viene riempito completamente.
    for (int i = 0; i < tavoliere.getDimensione(); i++) {
        for (int j = 0; j < tavoliere.getDimensione(); j++) {
            tavoliere.posizionaPedina(i, j, GIOCATORE_1, false);
        }
    }
    // Rimuovi una pedina del giocatore 1
    tavoliere.rimuoviPedina(DIMENSIONE_2, DIMENSIONE_1);

    // Verifica che le caselle non siano tutte riempite
    int[] conteggio = mosseDisponibili.contaPedineSeCaselleRiempiteAbbandono();
    assertEquals(2, conteggio[1], "Il conteggio delle pedine del giocatore 2 dovrebbe essere 2");
}
/**
 * Testa il metodo contaPedineSeCaselleRiempiteAbbandono.
 */
@Test
void testContaPedineSeCaselleRiempiteAbbandonoNotNull() {
    // Questa classe viene utilizzata quando il tabellone viene riempito completamente.
    for (int i = 0; i < tavoliere.getDimensione(); i++) {
        for (int j = 0; j < tavoliere.getDimensione(); j++) {
            tavoliere.posizionaPedina(i, j, GIOCATORE_1, false);
        }
    }
    // Rimuovi una pedina del giocatore 1
    tavoliere.rimuoviPedina(DIMENSIONE_2, DIMENSIONE_1);

    // Verifica che le caselle non siano tutte riempite
    int[] conteggio = mosseDisponibili.contaPedineSeCaselleRiempiteAbbandono();
    assertNotNull(conteggio, "Il conteggio delle pedine non dovrebbe essere null");
}
/**
 * Testa il metodo contaPedineSeCaselleRiempiteAbbandono.
 */
@Test
void testContaPedineSeCaselleRiempiteAbbandonoGiocatore1() {
    // Questa classe viene utilizzata quando il tabellone viene riempito completamente.
    for (int i = 0; i < tavoliere.getDimensione(); i++) {
        for (int j = 0; j < tavoliere.getDimensione(); j++) {
            tavoliere.posizionaPedina(i, j, GIOCATORE_1, false);
        }
    }
    // Rimuovi una pedina del giocatore 1
    tavoliere.rimuoviPedina(DIMENSIONE_2, DIMENSIONE_1);

    // Verifica che le caselle non siano tutte riempite
    int[] conteggio = mosseDisponibili.contaPedineSeCaselleRiempiteAbbandono();
    assertEquals(GIOCATORE_1_COUNT, conteggio[0],
    "Il conteggio delle pedine del giocatore 1 dovrebbe essere " + GIOCATORE_1_COUNT);
}
}
