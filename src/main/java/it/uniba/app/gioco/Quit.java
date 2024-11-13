package it.uniba.app.gioco;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import it.uniba.app.altro.Utilities;
import it.uniba.app.menu.Menu;

/**
 * Gestisce l'abbandono della partita da parte del giocatore.
 * classe control
 */
public final class Quit {
    private static Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8.name());

    private Quit() {
        // Costruttore privato per prevenire l'instanziazione
    }

    /**
     * Metodo per confermare l'abbandono della partita.
     */
    public static boolean confermaAbbandono(final boolean partitaterminata,
     final int pedineRimasteAvversario, final char giocatoreCorrente) {
        boolean abbandono = true;
        boolean esito = false;
        while (abbandono) {
            QuitStampa.stampaConfermaAbbandono();
            if (!scanner.hasNextLine()) {
                break;
            }
            String risposta = scanner.nextLine().trim().toLowerCase();
            switch (risposta) {
                case "si" -> {
                    // Conferma positiva
                    esito = true;
                    Menu.setPartitaTerminata(true);
                    getPartitaTerminata(partitaterminata);
                    abbandono = false;
                    break;
                }
                case "no" -> {
                    // Conferma negativa
                    QuitStampa.stampaAbbandonoAnnullato();
                    final int delay = 1500;
                    Utilities.pausa(delay);
                    abbandono = false;
                    esito = false;
                    break;
                }
                default -> QuitStampa.stampaComandoNonRiconosciuto();
            }
        }
        return esito;
    }

    /**
     * Ritorna lo stato della partita terminata.
     *
     * @param partitaterminata lo stato della partita terminata
     * @return vero se la partita è terminata, altrimenti falso
     */
    public static boolean getPartitaTerminata(final boolean partitaterminata) {
        return partitaterminata;
    }
}
