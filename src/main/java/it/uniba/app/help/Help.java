package it.uniba.app.help;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import it.uniba.app.altro.Utilities;
import it.uniba.app.menu.Menu;

/**
 * Questa classe permette di visualizzare i comandi e le regole di gioco.
 * Viene utilizzata per mostrare all'utente le informazioni necessarie per giocare.
 * La classe Help è una classe boundary, in quanto si occupa di interagire con l'utente.
 */
public final class Help {
    private Help() {
        // Costruttore
    }

    /**
     * Questo metodo visualizza il menu help.
     */
    public static void menuHelp() {
        try (Scanner input = new Scanner(System.in, StandardCharsets.UTF_8.name())) {
            while (true) {
                Utilities.pulisciSchermo();
                System.out.println("Benvenuto nel menu' help!");
                System.out.println("Vuoi visualizzare i comandi o le regole di gioco?");
                System.out.println(" (comandi/regole/uscire)");
                if (!input.hasNextLine()) {
                    break;
                }
                String choice = input.nextLine().trim().toLowerCase();
                switch (choice) {
                    case "comandi" -> {
                        HelpStampa.stampaComandi();
                        if (!input.hasNextLine()) {
                            break;
                        }
                        input.nextLine();
                    }
                    case "regole" -> {
                        HelpStampa.stampaRegole();
                        if (!input.hasNextLine()) {
                            break;
                        }
                        input.nextLine();
                    }
                    case "uscire" -> {
                        System.out.println("Uscita dal menu' help:");
                        Utilities.pulisciSchermo(); //pulisci schermo dopo essere uscito dal menù help
                        Menu.mostraMenuPartita();
                        return; // Esce dal metodo e continua con il programma principale
                    }
                    default -> System.out.println("Scelta non valida. Riprova.");
                }
            }
        }
    }
}
