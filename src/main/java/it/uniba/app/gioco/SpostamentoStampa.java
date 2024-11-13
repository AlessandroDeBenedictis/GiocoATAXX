package it.uniba.app.gioco;

/**
 * Classe di utilità per presentare informazioni sulle mosse di spostamento.
 * Le classi boundary sono responsabili dell'interazione tra il sistema e gli attori esterni (utenti o altri sistemi).
 * Gestiscono la presentazione delle informazioni e l'acquisizione dell'input.
 */
public final class SpostamentoStampa {
    private static final String GIALLO = "\u001B[33m";
    private static final String RESET = "\u001B[0m";
    private SpostamentoStampa() {
        // Costruttore privato per prevenire l'instanziazione
    }

    /**
     * Stampa la registrazione della mossa.
     *
     * @param giocatore Il giocatore che ha effettuato la mossa.
     * @param from La posizione di partenza in notazione algebrica.
     * @param to La posizione di arrivo in notazione algebrica.
     */
    public static void stampaMossaRegistrata(final char giocatore, final String from, final String to) {
        String mossa = "Giocatore " + (giocatore == '\u26C0' ? "1 (Bianco)" : "2 (Nero)") + ": " + from + "-" + to;
        System.out.println(GIALLO + "Mossa registrata: " + mossa + RESET);
    }
}
