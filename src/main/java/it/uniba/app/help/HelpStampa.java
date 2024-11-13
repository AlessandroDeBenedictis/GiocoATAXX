package it.uniba.app.help;

/**
 * Classe di utilità per presentare informazioni di aiuto.
 * Le classi boundary sono responsabili dell'interazione tra il sistema e gli attori esterni (utenti o altri sistemi).
 * Gestiscono la presentazione delle informazioni e l'acquisizione dell'input.
 */
public final class HelpStampa {
    private HelpStampa() {
        // Costruttore vuoto
    }
    /**
     * Stampa i comandi disponibili.
     */
    public static void stampaComandi() {
        System.out.println("## Comandi utili: \n");
        System.out.println("/gioca \n");
        System.out.println("Questo comando avvia una nuova partita da zero,\n");
        System.out.println("resettando il tabellone e reimpostando lo stato del gioco. \n ");
        System.out.println("/help \n");
        System.out.println("Mostra i comandi utili per il Gioco \n");
        System.out.println("/tavoliere \n");
        System.out.println("Visualizza il tavoliere con le pedine posizionate. \n");
        System.out.println("/vuoto \n");
        System.out.println("Visualizza il tavoliere vuoto. \n");
        System.out.println("/mostrapunteggio: \n");
        System.out.println("Mostra il punteggio attuale o il numero di pedine di ciascun giocatore. \n");
        System.out.println("/tempo: \n");
        System.out.println("Visualizza il tempo trascorso durante la partita. \n");
        System.out.println("/classifica: \n");
        System.out.println("Visualizza la classifica dei giocatori. WORK IN PROGRESS... \n");
        System.out.println("/salva: \n");
        System.out.println("Salva lo stato attuale del gioco, consentendo ai giocatori");
        System.out.println(" di riprendere da dove hanno lasciato. WORK IN PROGRESS.. \n");
        System.out.println("/carica: \n");
        System.out.println("Carica uno stato di gioco precedentemente salvato. WORK IN PROGRESS...\n");
        System.out.println("/indietro: \n");
        System.out.println("Annulla più mosse o riporta il gioco a uno stato precedente. WORK IN PROGRESS.. \n");
        System.out.println("/abbandona: \n");
        System.out.println("Termina la partita attuale e consente di iniziare una nuova partita");
        System.out.println("o tornare al menu principale. \n");
        System.out.println("/esci: \n");
        System.out.println("Termina il programma. \n");
        System.out.println("\n Premi qualsiasi tasto per tornare indietro...");
    }

    /**
     * Stampa le regole del gioco.
     */
    public static void stampaRegole() {
        System.out.println("# Ataxx: Guida al Gioco \n");
        System.out.println("## Descrizione del Gioco\n");
        System.out.println("Ataxx è un gioco da tavolo strategico per due giocatori,");
        System.out.println("che viene giocato su una scacchiera");
        System.out.println("quadrata di dimensioni variabili (tipicamente 7x7 o 8x8). \n");
        System.out.println("L'obiettivo del gioco è conquistare il maggior numero di caselle,");
        System.out.println("convertendo le pedine dell'avversario in pedine del proprio colore. \n\n\n");

        System.out.println("## Regole del Gioco \n");
        System.out.println("1.**Scopo**: \n");
        System.out.println("Il giocatore con il maggior numero di pedine del proprio colore");
        System.out.println("alla fine del gioco vince.\n\n\n");

        System.out.println("2.**Posizionamento Iniziale**:\n");
        System.out.println("- Le pedine dei due giocatori sono posizionate ");
        System.out.println("agli angoli opposti della scacchiera.\n");
        System.out.println("- Ogni giocatore inizia con due pedine.\n\n\n");

        System.out.println("3. **Turni di Gioco**: \n");
        System.out.println("- I giocatori si alternano a fare le proprie mosse. \n");
        System.out.println("- Il giocatore di turno può muovere una delle proprie pedine");
        System.out.println(" a un'altra casella della scacchiera.\n\n\n");

        System.out.println("4.**Tipi di Mosse**: \n");
        System.out.println("- **Salto**: Una pedina può saltare a una casella vuota");
        System.out.println("che dista fino a due passi in qualsiasi direzione ");
        System.out.println("(orizzontale, verticale o diagonale). \n");
        System.out.println("- **Duplice Mossa**: Se la pedina viene mossa a una casella");
        System.out.println("adiacente (orizzontale, verticale o diagonale),");
        System.out.println("la mossa crea una copia della pedina nel nuovo spazio, ");
        System.out.println("lasciando la pedina originale al suo posto.\n\n\n");

        System.out.println("**5. Conversione delle Pedine**:");
        System.out.println("- Se una pedina viene spostata vicino \n");
        System.out.println("a una o più pedine dell'avversario, ");
        System.out.println(" queste vengono convertite in pedine del ");
        System.out.println("colore del giocatore che ha fatto la mossa. \n");
        System.out.println("- La conversione avviene per le pedine che sono adiacenti ");
        System.out.println("(orizzontali, verticali o diagonali) alla nuova posizione della pedina. \n");

        System.out.println("6. **Fine del Gioco**:\n");
        System.out.println("- Il gioco termina quando non ci sono più mosse disponibili ");
        System.out.println("o quando un giocatore controlla tutte le caselle. \n");
        System.out.println("- Il vincitore è il giocatore con il maggior numero di pedine.\n\n\n");

        System.out.println("## Suggerimenti per Giocare \n");
        System.out.println("- Cerca di occupare le zone centrali della scacchiera per ");
        System.out.println("massimizzare le opportunità di conversione.\n");
        System.out.println("- Pianifica le tue mosse per bloccare le opportunità dell'avversario. \n");
        System.out.println("- Tieni sempre d'occhio le caselle che possono");
        System.out.println("essere convertite con una mossa strategica. \n\n\n");

        System.out.println("Buon divertimento con Ataxx! \n");
        System.out.println("\n Premi qualsiasi tasto per tornare indietro...");
    }
}
