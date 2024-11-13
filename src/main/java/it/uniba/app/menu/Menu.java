package it.uniba.app.menu;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import it.uniba.app.altro.Utilities;
import it.uniba.app.gioco.Exit;
import it.uniba.app.gioco.Game;
import it.uniba.app.help.Help;
import it.uniba.app.tavoliere.TavoliereVuoto;
import it.uniba.app.tavoliere.Tavoliere;

/**
 * Classe utilizzata per mostrare il menu principale del gioco.
 */
public final class Menu {
    private static final TavoliereVuoto TAVOLIEREVUOTO = new TavoliereVuoto();
    private static final Tavoliere TAVOLIERE = new Tavoliere();
    private static final Game GAME = new Game(TAVOLIERE);
    private static boolean partitaterminata = false;

    private Menu() {
        // Costruttore privato per prevenire l'instanziazione
    }

    /**
     * Mostra il menu principale del gioco.
     */
    public static void mostraMenuPartita() {
        Utilities.stampaTitolo(); // Stampa il titolo del programma
        System.out.println("\n");
        Utilities.barraDiCaricamentoVeloce(); // Mostra la barra di caricamento
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8.name());
        boolean running = true;
        final int inizioINPUT = 8;

        while (running) {
            MenuStampa.stampaMenu();
            System.out.print("Inserisci un comando: ");
            if (!scanner.hasNextLine()) {
                break;
            }
            String comando = scanner.nextLine().trim().toLowerCase();
            Utilities.pulisciSchermo(); // Pulisce la console

            switch (comando) {
                case "/gioca":
                    if (partitaterminata) {
                        MenuStampa.stampaAvvisoPartitaTerminata();
                        if (!scanner.hasNextLine()) {
                            break;
                        }
                        String risposta = scanner.nextLine().trim().toLowerCase();
                        if (risposta.equals("si")) {
                            MenuStampa.stampaReimpostazioneTavoliere();
                            partitaterminata = false;
                            TAVOLIERE.inizializzaTavoliere();
                        } else {
                            System.out.println("Continuo la partita precedente...");
                        }
                    }
                    GAME.startNewGame();
                    break;
                case "/help":
                case "--help":
                case "-h":
                    Help.menuHelp();
                    break;
                case "/vuoto":
                    TAVOLIEREVUOTO.visualizzaTavoliereVuoto();
                    break;
                case "/tavoliere":
                    if (partitaterminata) {
                        TAVOLIERE.visualizzaTavoliereIniziale();
                        if (!scanner.hasNextLine()) {
                            break;
                        }
                    } else {
                        MenuStampa.stampaMessaggioIniziaPartita();
                    }
                    break;
                case "/esci":
                    running = false;
                    Exit exit = new Exit(); // Assumendo che SalvataggioGioco esista
                    exit.exitGame();
                    break;
                default:
                    if (comando.startsWith("/blocca ")) {
                        String casella = comando.substring(inizioINPUT);
                        if (TAVOLIERE.bloccaCasella(casella)) {
                            MenuStampa.stampaMessaggioBloccoSuccesso(casella);
                        } else {
                            MenuStampa.stampaMessaggioErroreBlocco();
                        }
                    } else {
                        MenuStampa.stampaComandoNonRiconosciuto();
                    }
                    break;
            }

            System.out.println(); // Aggiunge uno spazio tra le iterazioni
        }
        scanner.close(); // Chiude lo scanner alla fine
    }

    /**
     * Imposta lo stato della partita terminata.
     * @param isPartitaTerminata il valore booleano da impostare
     */
    public static void setPartitaTerminata(final boolean isPartitaTerminata) {
        partitaterminata = isPartitaTerminata;
    }

    /**
     * Restituisce lo stato della partita terminata.
     * @return il valore booleano della partita terminata
     */
    public static boolean getPartitaTerminata() {
        return partitaterminata;
    }
}
