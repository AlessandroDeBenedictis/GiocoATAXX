package it.uniba.app.gioco;

/**
 * Classe responsabile della notifica delle conversioni delle pedine.
 * classe boundary
 */
public final class PedinaNotifier {
    private PedinaNotifier() { }

    /**
     * Notifica la conversione di una pedina.
     * @param x la coordinata x della pedina convertita
     * @param y la coordinata y della pedina convertita
     * @param giocatore il giocatore che ha effettuato la conversione
     * @param avversario il giocatore avversario
     */
    public static void notifyPedinaConvertita(final int x, final int y, final char giocatore, final char avversario) {
        String giallo = "\u001B[33m";
        String reset = "\u001B[0m";
        String posizioneConvertita = coordinateToNotazioneAlgebrica(x, y);
        System.out.println("Il giocatore " + giallo
                + (giocatore == '\u26C0' ? "1 (Bianco)" : "2 (Nero)")
                + reset + " ha convertito la pedina in posizione " + giallo + posizioneConvertita + reset
                + " del giocatore " + giallo + (avversario == '\u26C0' ? "1 (Bianco)" : "2 (Nero)")
                + reset + ".");
    }

    /**
     * Converte la notazione algebrica in coordinate.
     * @param x la coordinata x
     * @param y la coordinata y
     * @return la notazione algebrica
     */
    private static String coordinateToNotazioneAlgebrica(final int x, final int y) {
        char col = (char) ('a' + y);
        int row = x + 1;  // Convert from 0-based index to 1-based for human readability
        return col + "" + row;
    }
}
