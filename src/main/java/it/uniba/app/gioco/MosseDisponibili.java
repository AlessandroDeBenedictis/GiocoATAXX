package it.uniba.app.gioco;

import java.util.ArrayList;
import java.util.List;

import it.uniba.app.tavoliere.Tavoliere;

/**
 * Questa classe rappresenta un Controller nel contesto del gioco Ataxx.
 * Gestisce il calcolo e la visualizzazione delle mosse disponibili per una pedina selezionata.
 */
public class MosseDisponibili {
    private Tavoliere tavoliere;
    private final int dimensione;

    /**
     * Crea un nuovo oggetto MosseDisponibili con il tavoliere specificato.
     *
     * @param inputTavoliere Il tavoliere su cui calcolare le mosse disponibili.
     */
    public MosseDisponibili(final Tavoliere inputTavoliere) {
        this.tavoliere = inputTavoliere;
        this.dimensione = inputTavoliere.getDimensione();
    }

    /**
     * Ripristina il tavoliere rimuovendo temporaneamente le mosse precedentemente calcolate.
     */
    public void ripristinaTavoliere() {
        for (int i = 0; i < dimensione; i++) {
            for (int j = 0; j < dimensione; j++) {
                if (tavoliere.getPedina(i, j) == 'X' || tavoliere.getPedina(i, j) == 'Y') {
                    tavoliere.rimuoviPedina(j, i);  // Assumendo che '-' sia una casella vuota
                }
            }
        }
    }

    /**
     * Calcola e restituisce un elenco di mosse disponibili per la pedina selezionata.
     *
     * @param giocatore Il giocatore che sta effettuando la mossa.
     * @param x La coordinata x della pedina sul tavoliere.
     * @param y La coordinata y della pedina sul tavoliere.
     * @return Un elenco di mosse disponibili nel formato di notazione algebrica.
     */
    public List<String> calcolaEMostraMosseDisponibili(final char giocatore, final int x, final int y) {
        List<String> mosse = new ArrayList<>();
        if (tavoliere.getPedina(x, y) != giocatore) {
            return mosse;
        }

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newX = x + i;
                int newY = y + j;
                if (newX >= 0 && newX < dimensione && newY >= 0 && newY < dimensione) {
                    if (tavoliere.isCasellaLibera(newX, newY)) {
                        tavoliere.posizionaPedina(newX, newY, 'X', false); // Mossa generativa
                        mosse.add(coordinateToNotazioneAlgebrica(x, y)
                            + "-" + coordinateToNotazioneAlgebrica(newX, newY));
                    }
                }
            }
        }

        final int offset = -2;
        for (int i = offset; i <= 2; i++) {
            for (int j = offset; j <= 2; j++) {
                int newX = x + i;
                int newY = y + j;
                if ((Math.abs(i) == 2 || Math.abs(j) == 2) && newX >= 0
                    && newX < dimensione && newY >= 0 && newY < dimensione) {
                    if (tavoliere.isCasellaLibera(newX, newY)) {
                        tavoliere.posizionaPedina(newX, newY, 'Y', false); // Mossa di salto
                        mosse.add(coordinateToNotazioneAlgebrica(x, y)
                            + "-" + coordinateToNotazioneAlgebrica(newX, newY));
                    }
                }
            }
        }

        return mosse;
    }

    /**
     * Calcola le mosse disponibili per la pedina selezionata.
     *
     * @param giocatore Il giocatore che sta effettuando la mossa.
     * @param x La coordinata x della pedina sul tavoliere.
     * @param y La coordinata y della pedina sul tavoliere.
     * @return Un elenco di mosse disponibili nel formato di notazione algebrica.
     */
    public List<String> calcolaMosseDisponibili(final char giocatore, final int x, final int y) {
        List<String> mosse = new ArrayList<>();
        if (tavoliere.getPedina(x, y) != giocatore) {
            return mosse;
        }

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newX = x + i;
                int newY = y + j;
                if (newX >= 0 && newX < dimensione && newY >= 0 && newY < dimensione) {
                    if (tavoliere.isCasellaLibera(newX, newY)) {
                        mosse.add(coordinateToNotazioneAlgebrica(newX, newY));
                    }
                }
            }
        }

        final int offset = -2;
        for (int i = offset; i <= 2; i++) {
            for (int j = offset; j <= 2; j++) {
                int newX = x + i;
                int newY = y + j;
                if ((Math.abs(i) == 2 || Math.abs(j) == 2) && newX >= 0
                    && newX < dimensione && newY >= 0 && newY < dimensione) {
                    if (tavoliere.isCasellaLibera(newX, newY)) {
                        mosse.add(coordinateToNotazioneAlgebrica(newX, newY));
                    }
                }
            }
        }

        return mosse;
    }

    /**
     * Ottiene tutte le pedine del giocatore corrente che possono essere mosse.
     *
     * @param giocatore Il giocatore corrente.
     * @return Un elenco di coordinate delle pedine mobili.
     */
    public List<String> getPedineMobili(final char giocatore) {
        List<String> pedineMobili = new ArrayList<>();
        for (int i = 0; i < dimensione; i++) {
            for (int j = 0; j < dimensione; j++) {
                if (tavoliere.getPedina(i, j) == giocatore) {
                    List<String> mosse = calcolaMosseDisponibili(giocatore, i, j);
                    if (!mosse.isEmpty()) {
                        pedineMobili.add(coordinateToNotazioneAlgebrica(i, j));
                    }
                }
            }
        }
        return pedineMobili;
    }

    /**
     * Visualizza sul tavoliere le mosse disponibili per il giocatore specificato.
     *
     * @param giocatore Il giocatore corrente.
     */
    public void visualizzaTavoliereAiuto(final char giocatore) {
        ripristinaTavoliere();

        // Calcoliamo e mostriamo le mosse disponibili
        for (int i = 0; i < dimensione; i++) {
            for (int j = 0; j < dimensione; j++) {
                if (tavoliere.getPedina(i, j) == giocatore) {
                    // Generazione mosse
                    for (int x = -1; x <= 1; x++) {
                        for (int y = -1; y <= 1; y++) {
                            int nuovaX = i + x;
                            int nuovaY = j + y;
                            if (nuovaX >= 0 && nuovaX < dimensione && nuovaY >= 0
                                && nuovaY < dimensione && tavoliere.isCasellaLibera(nuovaX, nuovaY)) {
                                tavoliere.posizionaPedina(nuovaX, nuovaY, 'X', false);
                            }
                        }
                    }
                    // Mosse di salto
                    final int offset = -2;
                    for (int x = offset; x <= 2; x++) {
                        for (int y = offset; y <= 2; y++) {
                            int nuovaX = i + x;
                            int nuovaY = j + y;
                            if (Math.abs(x) == 2 || Math.abs(y) == 2) {
                                if (nuovaX >= 0 && nuovaX < dimensione && nuovaY >= 0
                                    && nuovaY < dimensione && tavoliere.isCasellaLibera(nuovaX, nuovaY)) {
                                    tavoliere.posizionaPedina(nuovaX, nuovaY, 'Y', false);
                                }
                            }
                        }
                    }
                }
            }
        }
        tavoliere.stampaTavoliere(); // Stampa il tavoliere con le mosse disponibili
    }

    /**
     * Trasforma le coordinate della casella in notazione algebrica.
     *
     * @param x La coordinata x della casella.
     * @param y La coordinata y della casella.
     * @return La notazione algebrica della casella.
     */
    private String coordinateToNotazioneAlgebrica(final int x, final int y) {
        return "" + (char) ('A' + y) + (x + 1);
    }

    /**
     * Visualizza le mosse disponibili per la pedina selezionata.
     *
     * @param giocatore Il giocatore che sta effettuando la mossa.
     * @param x La coordinata x della pedina sul tavoliere.
     * @param y La coordinata y della pedina sul tavoliere.
     */
    public void visualizzaMosseDisponibili(final char giocatore, final int x, final int y) {
        ripristinaTavoliere();
        calcolaEMostraMosseDisponibili(giocatore, x, y);
        System.out.println("Mosse disponibili per la pedina in posizione " + (char) ('A' + y) + (x + 1) + ":");
        tavoliere.aggiornaStato();
    }

    /**
     * Controlla se tutte le caselle del tavoliere sono riempite.
     *
     * @return true se tutte le caselle sono riempite, false altrimenti.
     */
    public boolean tutteLeCaselleRiempite() {
        for (int i = 0; i < dimensione; i++) {
            for (int j = 0; j < dimensione; j++) {
                if (tavoliere.isCasellaLibera(i, j)) {
                    return false; // Se troviamo una casella libera, ritorniamo false
                }
            }
        }
        return true; // Se nessuna casella è libera, ritorniamo true
    }

    /**
     * Conta le pedine dei giocatori se tutte le caselle sono riempite.
     *
     * @return un array con il numero di pedine di ciascun giocatore
     */
    public int[] contaPedineSeCaselleRiempite() {
        if (!tutteLeCaselleRiempite()) {
            return null; // Le caselle non sono tutte riempite
        }

        int pedineGiocatore1 = 0;
        int pedineGiocatore2 = 0;
        char giocatore1 = '\u26C0'; // Simbolo pedina giocatore 1
        char giocatore2 = '\u26C2'; // Simbolo pedina giocatore 2

        for (int i = 0; i < dimensione; i++) {
            for (int j = 0; j < dimensione; j++) {
                char pedina = tavoliere.getPedina(i, j);
                if (pedina == giocatore1) {
                    pedineGiocatore1++;
                } else if (pedina == giocatore2) {
                    pedineGiocatore2++;
                }
            }
        }

        return new int[]{pedineGiocatore1, pedineGiocatore2};
    }

    /**
     * Conta le pedine dei giocatori se tutte le caselle sono riempite.
     *
     * @return un array con il numero di pedine di ciascun giocatore
     */
    public int[] contaPedineSeCaselleRiempiteAbbandono() {
        if (tutteLeCaselleRiempite()) {
            return null; // Le caselle non sono tutte riempite
        }

        int pedineGiocatore1 = 0;
        int pedineGiocatore2 = 0;
        char giocatore1 = '\u26C0'; // Simbolo pedina giocatore 1
        char giocatore2 = '\u26C2'; // Simbolo pedina giocatore 2

        for (int i = 0; i < dimensione; i++) {
            for (int j = 0; j < dimensione; j++) {
                char pedina = tavoliere.getPedina(i, j);
                if (pedina == giocatore1) {
                    pedineGiocatore1++;
                } else if (pedina == giocatore2) {
                    pedineGiocatore2++;
                }
            }
        }

        return new int[]{pedineGiocatore1, pedineGiocatore2};
    }
}
