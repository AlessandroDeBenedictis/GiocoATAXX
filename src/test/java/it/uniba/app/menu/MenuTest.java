package it.uniba.app.menu;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Classe di test per Menu.
 */
 class MenuTest {
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;
    private ByteArrayOutputStream outContent;
    /**
     * viene runnato prima di ogni test, inizializza il flusso di output.
     */
    @BeforeEach
     void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent, true, StandardCharsets.UTF_8));
    }
    /**
     * viene runnato dopo ogni test, ripristina il flusso di input e output.
     */
    @AfterEach
     void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }
    /**
     * Testa il metodo setPartitaTerminata.
     */
    @Test
     void testSetPartitaTerminatatrue() {
        Menu.setPartitaTerminata(true);
        assertTrue(Menu.getPartitaTerminata(), "La partita dovrebbe essere terminata.");
     }

    /**
     * Testa il metodo setPartitaTerminata.
     */
    @Test
     void testSetPartitaTerminatafalse() {
        Menu.setPartitaTerminata(false);
        assertFalse(Menu.getPartitaTerminata(), "La partita non dovrebbe essere terminata.");
    }
    /**
     * Testa il metodo mostraMenuPartita.
     */
    @Test
     void testMostraMenuPartitaGioca() {
        String input = "/gioca";
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(inContent);

        Menu.mostraMenuPartita();

        String output = outContent.toString(StandardCharsets.UTF_8);
        assertTrue(output.contains("Inserisci un comando"), "Il comando non è stato richiesto.");
    }
    /**
     * Testa il metodo mostraMenuPartita.
     */
    @Test
     void testMostraMenuPartitaHelp() {
        String input = "/help";
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(inContent);

        Menu.mostraMenuPartita();

        String output = outContent.toString(StandardCharsets.UTF_8);
        assertFalse(output.contains("menuHelp"), "Il menu help non è stato mostrato.");
    }
    /**
     * Testa il metodo mostraMenuPartita.
     */
    @Test
     void testMostraMenuPartitaVuoto() {
        String input = "/vuoto";
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(inContent);

        Menu.mostraMenuPartita();

        String output = outContent.toString(StandardCharsets.UTF_8);
        assertFalse(output.contains("visualizzaTavoliereVuoto"), "Il tavoliere vuoto non è stato mostrato.");
    }
    /**
     * Testa il metodo mostraMenuPartita.
     */
    @Test
     void testMostraMenuPartitaTavoliere() {
        String input = "/tavoliere";
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(inContent);

        Menu.mostraMenuPartita();

        String output = outContent.toString(StandardCharsets.UTF_8);
        assertFalse(output.contains("visualizzaTavoliereIniziale")
        || output.contains("Inizia una partita"), "Il tavoliere non è stato gestito correttamente.");
    }
}
