package it.uniba.app.gioco;

import java.util.List;
import it.uniba.app.tavoliere.Tavoliere;

/**
 * Questa classe gestisce la presentazione delle mosse disponibili nel gioco Ataxx.
 * Classe boundary
 */
public class MosseDisponibiliStampa {

    private Tavoliere tavoliere;
    /**
     * Costruttore della classe MosseDisponibiliStampa.
     */
    public MosseDisponibiliStampa(final Tavoliere tavoliereParam) {
        this.tavoliere = tavoliereParam;
    }
    /**
     * Visualizza sul tavoliere le mosse disponibili per il giocatore specificato.
     * @param mosse Le mosse disponibili.
     */
    public void visualizzaMosseDisponibili(final List<String> mosse) {
        tavoliere.pulisciMosse();
        for (String mossa : mosse) {
            String[] coords = mossa.split("-");
            @SuppressWarnings("unused")
            int[] from = notazioneAlgebricaToCoord(coords[0]);
            int[] to = notazioneAlgebricaToCoord(coords[1]);
            tavoliere.posizionaPedina(to[0], to[1], 'X', false);
        }
        tavoliere.stampaTavoliere();
    }

    /**
     * Converte la notazione algebrica in coordinate.
     * @param notazione La notazione algebrica.
     * @return Un array di coordinate.
     */
    private int[] notazioneAlgebricaToCoord(final String notazione) {
        if (notazione.length() != 2) {
            return null;
        }
        char col = notazione.charAt(0);
        char row = notazione.charAt(1);
        int x = Character.isDigit(row) ? (row - '1') : -1;
        int y = (col - 'a') >= 0 && (col - 'a') < tavoliere.getDimensione() ? (col - 'a') : -1;
        if (x < 0 || x >= tavoliere.getDimensione() || y < 0 || y >= tavoliere.getDimensione()) {
            return null;
        }
        return new int[]{x, y};
    }
}
