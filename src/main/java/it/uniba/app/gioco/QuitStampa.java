package it.uniba.app.gioco;

/**
 * Classe di utilità per presentare informazioni di abbandono della partita.
 * Classe boundary
 */
public final class QuitStampa {
    private static final String GIALLO = "\u001B[33m";
    private static final String RESET = "\u001B[0m";
    private QuitStampa() {
        // Costruttore privato per prevenire l'instanziazione
    }

    /**
     * Stampa la richiesta di conferma di abbandono della partita.
     */
    public static void stampaConfermaAbbandono() {
        System.out.println(GIALLO + "Sei sicuro di voler abbandonare la partita? (si/no)" + RESET);
    }

    /**
     * Stampa un messaggio per un comando non riconosciuto.
     */
    public static void stampaComandoNonRiconosciuto() {
        System.out.println("Comando non riconosciuto. Riprova.");
    }

    /**
     * Stampa il messaggio di abbandono annullato.
     */
    public static void stampaAbbandonoAnnullato() {
        System.out.println("Abbandono annullato. Torna al gioco...");
    }
}
