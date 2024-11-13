package it.uniba.app.gioco;

import it.uniba.app.tavoliere.Tavoliere;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


/**
 * Classe di test per la classe Game.
 */
class GameTest {
    private Game game;
    private Tavoliere tavoliere;

    /**
     * Inizializza il tavoliere e il gioco prima di ogni test.
     */
    @BeforeEach
    void setUp() {
        tavoliere = new Tavoliere();
        game = new Game(tavoliere);
    }

    /**
     * Testa il metodo startnewgame.
     */
    @Test
    void testStartNewGame() {
        game.startNewGame();
        assertNotNull(game.getTavoliere(), "Il tavoliere non dovrebbe essere null");
    }

    /**
     * Testa il metodo logMossa.
     */
    @Test
    void testLogMossaSize() {
        game.logMossa("a1", "a2", '\u26C0');
        List<String> logMosse = game.getLogMosse();
        assertEquals(1, logMosse.size(), "La dimensione del log delle mosse dovrebbe essere 1");
    }

    @Test
    void testLogMossaContent() {
        game.logMossa("a1", "a2", '\u26C0');
        List<String> logMosse = game.getLogMosse();
        assertEquals("Giocatore 1 (Bianco): a1-a2", logMosse.get(0),
         "La mossa loggata non corrisponde a quella attesa");
    }

    /**
     * Testa il metodo notazioneAlgebricaToCoord.
     */
    @Test
    void testNotazioneAlgebricaToCoordA1() {
        int[] coord = game.notazioneAlgebricaToCoord("a1");
        assertArrayEquals(new int[]{0, 0}, coord, "La conversione di a1 dovrebbe restituire {0, 0}");
    }

    @Test
    void testNotazioneAlgebricaToCoordG7() {
        final int rowIndex = 6;
        final int columnIndex = 6;
        int[] coord = game.notazioneAlgebricaToCoord("g7");
        assertArrayEquals(new int[]{rowIndex, columnIndex}, coord, "La conversione di g7 dovrebbe restituire {6, 6}");
    }

    @Test
    void testNotazioneAlgebricaToCoordInvalid() {
        int[] coord = game.notazioneAlgebricaToCoord("Z9"); // Testiamo un caso di input non valido
        assertNull(coord, "La conversione di Z9 dovrebbe restituire null");
    }

    /**
     * Testa il metodo getElapsedTime.
     */
    @Test
    void testGetElapsedTimeNotNull() {
        String elapsedTime = game.getElapsedTime();
        assertNotNull(elapsedTime, "Il tempo trascorso non dovrebbe essere null");
    }

    @Test
    void testGetElapsedTimeFormat() {
        String elapsedTime = game.getElapsedTime();
        assertTrue(elapsedTime.matches("\\d{2}:\\d{2}:\\d{2}"),
         "Il tempo trascorso dovrebbe essere nel formato HH:MM:SS");
    }

    /**
     * Testa il metodo determinareVincitore.
     */
    @Test
    void testDeterminareVincitorePlayer1Wins() {
        final int player1Pieces = 5;
        final int player2Pieces = 3;
        int[] conteggioPedine = {player1Pieces, player2Pieces};
        String vincitore = game.determinareVincitore(conteggioPedine);
        assertEquals("Giocatore 1 (Bianco) ha vinto con 5 pedine contro 3 pedine.",
         vincitore, "La determinazione del vincitore non è corretta");
    }

    @Test
    void testDeterminareVincitorePlayer2Wins() {
        final int player1NewPieces = 2;
        final int player2NewPieces = 6;
        int[] conteggioPedine = {player1NewPieces, player2NewPieces};
        String vincitore = game.determinareVincitore(conteggioPedine);
        assertEquals("Giocatore 2 (Nero) ha vinto con 6 pedine contro 2 pedine.",
         vincitore, "La determinazione del vincitore non è corretta");
    }

    /**
     * Testa il metodo determinareVincitorePerAbbandono.
     */
    @Test
    void testDeterminareVincitorePerAbbandono() {
        final int maxAbbandono = 10;
        assertDoesNotThrow(() -> Game.determinaVincitorePerAbbandono('\u26C0', maxAbbandono),
         "Il metodo dovrebbe eseguire senza lanciare eccezioni");
    }
}

