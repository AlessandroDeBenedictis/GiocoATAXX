package it.uniba.app.menu;

/**
 * Classe di utilità per presentare informazioni del menu.
 * Le classi boundary sono responsabili dell'interazione tra il sistema e gli attori esterni (utenti o altri sistemi).
 * Gestiscono la presentazione delle informazioni e l'acquisizione dell'input.
 */
public final class MenuStampa {
    private static final String GIALLO = "\u001B[33m";
    private static final String RESET = "\u001B[0m";
    private MenuStampa() {
        // Costruttore privato per prevenire l'instanziazione
    }
    /**
     * Stampa il menu principale.
     */
    public static void stampaMenu() {
        System.out.println("\n\n");
        System.out.println(GIALLO + "  BENVENUTO IN ATAXX  " + RESET);
        System.out.println("\n\n");
        System.out.println(GIALLO + "/gioca" + RESET + " - Accedi al menu partita");
        System.out.println(GIALLO + "/help" + RESET + " - Accedi al menu help");
        System.out.println(GIALLO + "/blocca xn " + RESET + "- Blocca una casella specifica (es. b3)");
        System.out.println(GIALLO + "/vuoto" + RESET + "- Visualizza il tabellone");
        System.out.println(GIALLO + "/tavoliere" + RESET + "- Visualizza il tavoliere con le pedine posizionate");
        System.out.println(GIALLO + "/esci" + RESET + "- Termina il programma");
    }

    /**
     * Stampa il messaggio di avviso partita terminata.
     */
    public static void stampaAvvisoPartitaTerminata() {
        System.out.println(GIALLO + "ATTENZIONE!" + RESET);
        System.out.println("Partita precedente terminata. Vuoi iniziarne una nuova? (si/no)");
    }

    /**
     * Stampa il messaggio di reimpostazione del tavoliere.
     */
    public static void stampaReimpostazioneTavoliere() {
        System.out.println(GIALLO + "Reimpostazione del tavoliere..." + RESET);
    }

    /**
     * Stampa il messaggio di comando non riconosciuto.
     */
    public static void stampaComandoNonRiconosciuto() {
        System.out.println("Comando non riconosciuto. Prova di nuovo.");
    }

    /**
     * Stampa il messaggio per iniziare una partita prima di visualizzare il tavoliere.
     */
    public static void stampaMessaggioIniziaPartita() {
        System.out.println("Devi prima iniziare una partita per visualizzare il tavoliere");
    }

    /**
     * Stampa il messaggio di successo per il blocco della casella.
     *
     * @param casella La casella che è stata bloccata.
     */
    public static void stampaMessaggioBloccoSuccesso(final String casella) {
        System.out.println("Casella " + casella + " bloccata con successo.");
    }

    /**
     * Stampa il messaggio di errore per il blocco della casella.
     */
    public static void stampaMessaggioErroreBlocco() {
        System.out.println("Errore: casella non valida o già bloccata.");
    }
}
