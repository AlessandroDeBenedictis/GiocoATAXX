package it.uniba.app;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import it.uniba.app.altro.Utilities;
import it.uniba.app.menu.Menu;
/**
 * Main class of the application.
 */
public final class App {
    private App() {
        // Costruttore
    }
    /**
     * Entrypoint of the application.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        System.out.println("AVVIO DEL GIOCO ATAXX...");
        Utilities.barraDiCaricamento(); // Mostra la barra di caricamento
        Utilities.pulisciSchermo(); // Pulisce lo schermo dopo il caricamento del gioco
        Utilities.stampaTitolo(); // Stampa il titolo del programma
        Utilities.descrizione();
        System.out.print("Inserisci un qualsiasi tasto per andare avanti: ");
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8.name());
        scanner.nextLine(); // Use the instance to call nextLine()
        Utilities.pulisciSchermo(); // Pulisce lo schermo dopo il caricamento del gioco
        Menu.mostraMenuPartita(); // Mostra il menu principale del gioco
        scanner.close(); // Chiude lo scanner alla fine
    }
}
