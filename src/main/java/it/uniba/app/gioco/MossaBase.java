package it.uniba.app.gioco;

import it.uniba.app.tavoliere.Tavoliere;
import it.uniba.app.altro.Utilities;

/**
 * La classe MossaBase gestisce la logica delle mosse di base,
 * cioè la cattura delle pedine avversarie adiacenti a quelle di partenza.
 * Si tratta di una classe control che gestisce e coordina le operazioni legate alle mosse di base nel gioco.
 */
public class MossaBase {
    private Tavoliere tavoliere;

    protected MossaBase(final Tavoliere tavoliereParam) {
        this.tavoliere = tavoliereParam;
    }

    /**
     * Converte la notazione algebrica in coordinate.
     */
    protected String coordinateToNotazioneAlgebrica(final int x, final int y) {
        char col = (char) ('a' + y);
        int row = x + 1;  // Convert from 0-based index to 1-based for human readability
        return col + "" + row;
    }

    /**
     * Cattura le pedine adiacenti alla posizione di arrivo.
     * Se una pedina avversaria è adiacente alla posizione di arrivo, la cattura.
     * La pedina catturata viene sostituita con una pedina del giocatore corrente.
     * @param x la coordinata x della posizione di arrivo
     * @param y la coordinata y della posizione di arrivo
     * @param giocatore il giocatore corrente ('\u26C0' per il giocatore 1, '\u26C2' per il giocatore 2)
     */
    protected void catturaPedineAdiacenti(final int x, final int y, final char giocatore) {
        char avversario = (giocatore == '\u26C0') ? '\u26C2' : '\u26C0';
        // Controlla le 8 direzioni adiacenti
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                // Ignora la posizione di partenza
                if (dx == 0 && dy == 0) {
                    continue;
                }
                int nx = x + dx;
                int ny = y + dy;
                // Controlla i confini del tavoliere prima di accedere agli indici
                if (nx >= 0 && nx < tavoliere.getDimensione() && ny >= 0 && ny < tavoliere.getDimensione()) {
                    // Controlla se la casella contiene una pedina dell'avversario
                    if (tavoliere.getPedina(nx, ny) == avversario) {
                        tavoliere.posizionaPedina(nx, ny, giocatore, true); // Esegue la conversione
                        PedinaNotifier.notifyPedinaConvertita(nx, ny, giocatore, avversario);
                        final int pausapausa = 2000;
                        Utilities.pausa(pausapausa); // Mostra la mossa per 2 secondi
                    }
                }
            }
        }
    }
}
