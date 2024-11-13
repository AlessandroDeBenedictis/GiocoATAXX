package it.uniba.app.tavoliere;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import it.uniba.app.altro.Utilities;
import it.uniba.app.gioco.Game;
import it.uniba.app.menu.Menu;

/**
 * Classe che rappresenta un tavoliere di gioco.
 * Il metodo tavoliereiniziale permette di posizionare le pedine in posizione iniziale.
 * Estende la classe TavoliereVuoto.
 * Si occupa di posizionare le pedine iniziali sul tavoliere.
 * Si tratta di una classe entity dato che rappresenta e gestisce un oggetto del dominio
 * del sistema.
 */
public class Tavoliere {
    private char[][] tavoliere;
    private static final int DIMENSIONE = 7;  // Dimensione di 7x7
    private static final int MAX_CASELLE_BLOCCATE = 9;
    private int caselleBloccateCount = 0;  // Contatore per le caselle bloccate
    private static final Set<String> CASELLE_NON_ACCESSIBILI = new HashSet<>();
    static {
        String[] caselleNonAccessibili = {
            "a1", "a2", "a3", "b1", "b2", "b3", "c1", "c2", "c3",
            "e1", "e2", "e3", "f1", "f2", "f3", "g1", "g2", "g3",
            "a5", "a6", "a7", "b5", "b6", "b7", "c5", "c6", "c7",
            "e5", "e6", "e7", "f5", "f6", "f7", "g5", "g6", "g7"
        };
        for (String casella : caselleNonAccessibili) {
            CASELLE_NON_ACCESSIBILI.add(casella);
        }
    }

    /**
     * Costruttore della classe Tavoliere.
     */
    public Tavoliere() {
        tavoliere = new char[DIMENSIONE][DIMENSIONE];
        inizializzaTavoliere();
    }

    /**
     * Inizializza il tavoliere con le pedine iniziali.
     */
    public void inizializzaTavoliere() {
        for (int i = 0; i < DIMENSIONE; i++) {
            for (int j = 0; j < DIMENSIONE; j++) {
                tavoliere[i][j] = '-';
            }
        }
        tavoliere[0][0] = '\u26C0'; // Pedina del giocatore 1 in A1
        tavoliere[DIMENSIONE - 1][DIMENSIONE - 1] = '\u26C0'; // Pedina del giocatore 1 in G7
        tavoliere[DIMENSIONE - 1][0] = '\u26C2'; // Pedina del giocatore 2 in A7
        tavoliere[0][DIMENSIONE - 1] = '\u26C2'; // Pedina del giocatore 2 in G1
    }

    /**
     * Ottiene la dimensione del tavoliere.
     * @return la dimensione del tavoliere
     */
    public int getDimensione() {
        return DIMENSIONE;
    }

    /**
     * Controlla se la casella in posizione (x, y) è libera.
     * @param x coordinata x della casella
     * @param y coordinata y della casella
     * @return true se la casella è libera, false altrimenti
     */
    public boolean isCasellaLibera(final int x, final int y) {
        return tavoliere[x][y] == '-';
    }

    /**
     * Pulisce le mosse precedenti.
     */
    public void pulisciMosse() {
        for (int i = 0; i < DIMENSIONE; i++) {
            for (int j = 0; j < DIMENSIONE; j++) {
                if (tavoliere[i][j] == 'X' || tavoliere[i][j] == 'Y') {
                    tavoliere[i][j] = '-';
                }
            }
        }
    }

    /**
     * Stampa il tavoliere con le pedine iniziali posizionate.
     */
    public void stampaTavoliere() {
        TavoliereStampa.stampaTavoliere(tavoliere);
        pulisciMosse();
    }

    /**
     * Ritorna la pedina nella casella specificata.
     * @param x la coordinata x della casella
     * @param y la coordinata y della casella
     * @return il carattere che rappresenta la pedina in quella casella
     */
    public char getPedina(final int x, final int y) {
        return tavoliere[x][y];
    }

    /**
     * Visualizza il tavoliere iniziale.
     */
    public void visualizzaTavoliereIniziale() {
        Utilities.barraDiCaricamento();
        stampaTavoliere();
        try (Scanner input = new Scanner(System.in, StandardCharsets.UTF_8.name())) {
            System.out.println("Altrimenti premi un qualsiasi tasto per tornare indietro al menu.");
            if (!input.hasNextLine()) {
                return;
            }
            String comando = input.nextLine().trim().toLowerCase();
            switch (comando) {
                case "/gioca":
                    Game game = new Game(this);
                    game.startNewGame();
                    break;
                default:
                    Utilities.pulisciSchermo();
                    Menu.mostraMenuPartita();
                    break;
            }
        }
        System.out.print("Inserisci un qualsiasi tasto per tornare indietro: ");
        Utilities.pulisciSchermo();
    }

    /**
     * Blocca una casella specifica del tavoliere.
     * @param casella La casella da bloccare.
     * @return true se la casella è stata bloccata con successo, false altrimenti.
     */
    public boolean bloccaCasella(final String casella) {
        if (caselleBloccateCount >= MAX_CASELLE_BLOCCATE) {
            System.out.println("Hai raggiunto il numero massimo di caselle bloccate (9).");
            return false;
        }

        if (CASELLE_NON_ACCESSIBILI.contains(casella.toLowerCase())) {
            System.out.println("Non puoi bloccare questa casella: " + casella);
            return false;
        }

        int[] coords = notazioneAlgebricaToCoord(casella);
        char fullBlock = '\u2588';
        if (coords != null && isCasellaLibera(coords[0], coords[1])) {
            posizionaPedina(coords[0], coords[1], fullBlock, true);
            caselleBloccateCount++;
            return true;
        }
        return false;
    }

    /**
     * Converte la notazione algebrica in coordinate x e y.
     * @param notazione La notazione algebrica da convertire.
     * @return Le coordinate x e y della casella, o null se la notazione è errata.
     */
    public int[] notazioneAlgebricaToCoord(final String notazione) {
        if (notazione.length() != 2) {
            return null;
        }
        char col = notazione.charAt(0);
        char row = notazione.charAt(1);

        int x = Character.isDigit(row) ? (row - '1') : -1;
        int y = (col - 'a') >= 0 && (col - 'a') < getDimensione() ? (col - 'a') : -1;

        if (x < 0 || x >= getDimensione() || y < 0 || y >= getDimensione()) {
            return null;
        }

        return new int[]{x, y};
    }

    /**
     * Posiziona una pedina in una casella del tavoliere, convertendola se necessario.
     * @param x La coordinata x della casella.
     * @param y La coordinata y della casella.
     * @param pedina Il simbolo della pedina da posizionare.
     * @param isConversion Se true, la posizione sarà convertita ignorando se è libera.
     *                     Se false, la posizione sarà convertita solo se è libera.
     */
    public void posizionaPedina(final int x, final int y, final char pedina, final boolean isConversion) {
        if (isConversion || isCasellaLibera(x, y)) {
            tavoliere[x][y] = pedina;
        }
    }

    /**
     * Rimuove una pedina dalla casella specificata.
     * @param x La coordinata x della casella.
     * @param y La coordinata y della casella.
     */
    public void rimuoviPedina(final int x, final int y) {
        tavoliere[x][y] = '-';
    }

    /**
     * Aggiorna lo stato visivo del tavoliere.
     */
    public void aggiornaStato() {
        TavoliereStampa.aggiornaStato(tavoliere);
    }
}
