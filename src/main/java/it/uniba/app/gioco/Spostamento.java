package it.uniba.app.gioco;

import it.uniba.app.tavoliere.Tavoliere;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe Spostamento gestisce la logica delle mosse adiacenti,
 *  cioè la duplicazione delle pedine nelle caselle adiacenti a quelle di partenza.
 * Verifica la validità delle mosse, aggiorna lo stato del tavoliere e notifica eventuali errori.
 *  Similmente alla classe Adiacente,
 * Spostamento è una classe di controllo poiché gestisce e coordina le operazioni legate alle mosse adiacenti nel gioco.
 */
public class Spostamento extends MossaBase {
    private Tavoliere tavoliere;
    private List<String> logMosse;

    /**
     * Costruttore della classe Spostamento.
     *
     * @param tavolierespostato il tavoliere corrente
     */
    public Spostamento(final Tavoliere tavolierespostato) {
        super(tavolierespostato); // super(tavolierespostato) chiama il costruttore della classe MossaBase
        this.tavoliere = tavolierespostato;
        this.logMosse = new ArrayList<>();
    }

    /**
     * Verifica se la mossa è valida.
     *
     * @param from la posizione di partenza in notazione algebrica
     * @param to la posizione di arrivo in notazione algebrica
     * @param giocatore il giocatore corrente ('\u26C0' per il giocatore 1, '\u26C2' per il giocatore 2)
     * @return true se la mossa è valida, false altrimenti
     */
    public boolean isMossaValida(final String from, final String to, final char giocatore) {
        int[] fromCoords = notazioneAlgebricaToCoord(from);
        int[] toCoords = notazioneAlgebricaToCoord(to);

        if (fromCoords == null || toCoords == null) {
            return false;
        }

        int fromX = fromCoords[0];
        int fromY = fromCoords[1];
        int toX = toCoords[0];
        int toY = toCoords[1];

        // Verifica che la pedina di partenza appartenga al giocatore corrente
        if (tavoliere.getPedina(fromX, fromY) != giocatore) {
            return false;
        }

        // Verifica che la casella di arrivo sia libera
        if (!tavoliere.isCasellaLibera(toX, toY)) {
            return false;
        }

        // Verifica che la mossa sia Spostamento
        int dx = Math.abs(fromX - toX);
        int dy = Math.abs(fromY - toY);

        // Permetti sia i movimenti a "L" sia quelli a due passi diagonalmente
        return (dx == 2 && dy == 1) || (dx == 1 && dy == 2)
        || (dx == 2 && dy == 0) || (dx == 0 && dy == 2) || (dx == 2 && dy == 2);
    }

    /**
     * Esegue la mossa se è valida.
     *
     * @param from la posizione di partenza in notazione algebrica
     * @param to la posizione di arrivo in notazione algebrica
     * @param giocatore il giocatore corrente ('\u26C0' per il giocatore 1, '\u26C2' per il giocatore 2)
     * @return true se la mossa è stata eseguita, false altrimenti
     */
    public boolean eseguiMossa(final String from, final String to, final char giocatore) {
        if (!isMossaValida(from, to, giocatore)) {
            return false;
        }
        int[] fromCoords = notazioneAlgebricaToCoord(from);
        int fromX = fromCoords[0];
        int fromY = fromCoords[1];
        tavoliere.rimuoviPedina(fromX, fromY); // Rimuovi la pedina di partenza
        int[] toCoords = notazioneAlgebricaToCoord(to);
        int toX = toCoords[0];
        int toY = toCoords[1];

        tavoliere.posizionaPedina(toX, toY, giocatore, true); // posiziona la nuova pedina
        catturaPedineAdiacenti(toX, toY, giocatore); // aggiunta la logica di cattura
        logMossa(from, to, giocatore);
        tavoliere.stampaTavoliere();
        return true;
    }

    /**
     * Registra la mossa nel log delle mosse.
     *
     * @param from la posizione di partenza in notazione algebrica
     * @param to la posizione di arrivo in notazione algebrica
     * @param giocatore il giocatore corrente ('\u26C0' per il giocatore 1, '\u26C2' per il giocatore 2)
     */
    private void logMossa(final String from, final String to, final char giocatore) {
        String mossa = "Giocatore " + (giocatore == '\u26C0' ? "1 (Bianco)" : "2 (Nero)") + ": " + from + "-" + to;
        logMosse.add(mossa);
        SpostamentoStampa.stampaMossaRegistrata(giocatore, from, to);
    }

    /**
     * Converte una notazione algebrica in coordinate del tavoliere.
     *
     * @param notazione la notazione algebrica (es. "a1")
     * @return un array di due interi rappresentanti le coordinate [riga, colonna] o null se non valida.
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
