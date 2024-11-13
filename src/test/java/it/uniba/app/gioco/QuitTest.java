package it.uniba.app.gioco;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Classe di test per Quit.
 */
class QuitTest {
    // Salva il flusso di input standard originale per poterlo ripristinare dopo ogni test.
    private final InputStream originalIn = System.in;
    // Salva il flusso di output standard originale per lo stesso motivo.
    private final PrintStream originalOut = System.out;
    // Questo campo verrà usato per catturare l'output durante i test.
    private ByteArrayOutputStream outContent;

    /**
     * Prima di ogni test, questo metodo inizializza il flusso di output.
     */
    @BeforeEach
    void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent, true, StandardCharsets.UTF_8));
    }

    /**
     * Dopo ogni test, questo metodo ripristina System.out e System.in.
     */
    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    /**
     * Questo test verifica il comportamento del metodo confermaAbbandono() della classe Quit.
     */
    @Test
    void testConfermaAbbandonoConfermaPositiva() {
        String input = "si\n";
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(inContent);
        boolean result = Quit.confermaAbbandono(false, 1, '\u26C0');
        assertFalse(result, "La conferma di abbandono dovrebbe essere vera.");
    }

    /**
     * Questo test verifica il comportamento del metodo confermaAbbandono() della classe Quit.
     */
    @Test
    void testConfermaAbbandonoConfermaNegativa() {
        String input = "no\n";
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(inContent);
        boolean result = Quit.confermaAbbandono(false, 0, 'X');
        assertFalse(result, "La conferma di abbandono dovrebbe essere falsa.");
    }

    /**
     * Questo test verifica il comportamento del metodo getPartitaTerminata() della classe Quit.
     */
    @Test
    void testGetPartitaTerminataFalse() {
        assertFalse(Quit.getPartitaTerminata(false), "Il valore della partita terminata dovrebbe essere falso.");
    }

    /**
     * Questo test verifica il comportamento del metodo getPartitaTerminata() della classe Quit.
     */
    @Test
    void testGetPartitaTerminataTrue() {
        assertTrue(Quit.getPartitaTerminata(true), "Il valore della partita terminata dovrebbe essere vero.");
    }
}
