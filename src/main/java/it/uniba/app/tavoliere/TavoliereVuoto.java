package it.uniba.app.tavoliere;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import it.uniba.app.altro.Utilities;
import it.uniba.app.menu.Menu;

/**
 * Classe TavoliereVuoto che imposta la classe JFrame per visualizzare un tavoliere vuoto.
 * Usata per la visualizzazione del tavoliere vuoto.
 * Questa classe è stata creata per visualizzare un tavoliere vuoto 7x7 con etichette per le righe e le colonne.
 * Si tratta di una classe boundary dato che si occupa di visualizzare il tavoliere vuoto.
 * E quindi di gestire l'interazione tra utente e sistema tramite l'interfaccia grafica.
 */
public class TavoliereVuoto {

    /**
     * Questo metodo visualizza il tavoliere vuoto.
     */
    public final void visualizzaTavoliereVuoto() {
        Utilities.barraDiCaricamento(); // Chiama il metodo Caricamento() dalla classe BarraDiCaricamento.
        System.out.println("Visualizzazione del tavoliere vuoto...");
        TavoliereVuotoStampa.stampaTavoliereVuoto();
        System.out.print("Inserisci un qualsiasi tasto per andare avanti: ");
        try (Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8.name())) {
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
        }
        Utilities.pulisciSchermo(); // Pulisce lo schermo dopo il caricamento del gioco
        Menu.mostraMenuPartita(); // Mostra il menu partita
    }
}
