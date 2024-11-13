package it.uniba.app.gioco;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.time.Duration;
import java.time.Instant;
import it.uniba.app.menu.Menu;
import it.uniba.app.altro.Utilities;
import it.uniba.app.tavoliere.Tavoliere;

/**
 * La classe Game può essere classificata come una classe control
 * poiché si occupa della gestione del flusso dell'applicazione,
 * dell'interazione tra le componenti, e della gestione dell'input dell'utente.
 */
public class Game {
    private char giocatoreCorrente = '\u26C0'; // Giocatore 1 inizia
    private int pedineRimasteAvversario;
    private Tavoliere tavoliere;
    private Scanner tipo;
    private Instant startTime; // Variabile per memorizzare il tempo di inizio
    private int[] conteggio; // Array per memorizzare il conteggio delle pedine dei giocatori
    private List<String> logMosse; // Lista per memorizzare lo storico delle mosse

    /**
    * Costruttore classe Game.
    */
    public Game(final Tavoliere tavolieredelgioco) {
        this.tavoliere = tavolieredelgioco;
        this.tipo = new Scanner(System.in, StandardCharsets.UTF_8.name());
        this.startTime = Instant.now(); // Inizializza il tempo di inizio
        this.logMosse = new ArrayList<>(); // Inizializza la lista delle mosse
    }

    /**
    * Questo metodo visualizza il tavoliere iniziale.
    */
    public void visualizzaTavoliereIniziale() {
        Utilities.barraDiCaricamentoVeloce(); // Chiama il metodo Caricamento() dalla classe BarraDiCaricamento.
        System.out.println("Tavoliere iniziale:");
        tavoliere.stampaTavoliere();
    }

    /**
     * Questo metodo permette di iniziare un nuovo gioco.
     */
    public final void startNewGame() {
        String giallo = "\u001B[33m";
        String arancione = "\u001B[31m";
        String reset = "\u001B[0m";
        final int pausa = 6000;
        visualizzaTavoliereIniziale();
        System.out.println("\nPedine iniziali posizionate.");
        System.out.println("Il gioco puo'  iniziare!");
        System.out.println("I giocatori possono muovere le pedine in orizzontale, verticale, diagonale.");
        System.out.println("Per vedere quali mosse sono disponibili, digita "
        + giallo + "/qualimosse" + reset + " e premi invio.");
        System.out.println("Per sapere quanto tempo è trascorso " + giallo + "/tempo" + reset + ".");
        System.out.println("Per abbandonare la partita, digita " + giallo + "/abbandona" + reset + " e premi invio.");
        System.out.println("Per visualizzare lo storico delle mosse, digita  "
        + giallo + "/mosse" + reset + " e premi invio.");
        System.out.println("Per visualizzare l'elenco dei comandi, digita " + giallo + "/help" + reset);
        System.out.println(giallo + "Per iniziare la partita, premi invio." + reset);
        if (!tipo.hasNextLine()) {
            return;
        }
        tipo.nextLine();  // Attendere che l'utente prema invio per continuare
        boolean partitaInCorso = true;
        Adiacente adiacente = new Adiacente(tavoliere);
        Spostamento spostamento = new Spostamento(tavoliere);
        MosseDisponibili mosseDisponibili = new MosseDisponibili(tavoliere);
        while (partitaInCorso) {
            Utilities.pulisciSchermo();
            aggiornaEVisualizzaTavoliere();
            //aggiorna costantemente il punteggio
            conteggio = mosseDisponibili.contaPedineSeCaselleRiempiteAbbandono();
            if (mosseDisponibili.tutteLeCaselleRiempite()) {
                conteggio = mosseDisponibili.contaPedineSeCaselleRiempite();
                if (conteggio != null) {
                    System.out.println("Tutte le caselle sono riempite!");
                    System.out.println("Giocatore 1 ha " + conteggio[0] + " pedine.");
                    System.out.println("Giocatore 2 ha " + conteggio[1] + " pedine.");
                    String vincitore = determinareVincitore(conteggio);
                    System.out.println("Il vincitore è: " + vincitore);
                    Utilities.pausa(pausa); // Mostra il vincitore per 6 secondi
                }
                partitaInCorso = false;
                break;
            }
            System.out.println("Giocatore " + (giocatoreCorrente == '\u26C0' ? "1 (Bianco)" : "2 (Nero)")
            + ", in che posizione mettere la pedina?");
            if (!tipo.hasNextLine()) {
                break;
            }
            String comando = tipo.nextLine().trim().toLowerCase();
            if (comando.equals("/qualimosse")) {
                handleQualiMosse(mosseDisponibili);
                System.out.println("Mosse disponibili:");
                System.out.println("Le celle di color " + giallo + " giallo " + reset
                + " indicano le mosse di generazione (spostamento) delle pedine.");
                System.out.println("Una mossa di spostamento permette alla pedina di muoversi di una casella");
                System.out.println("Le celle di color " + arancione + " arancione" + reset
                + " indicano le mosse di salto delle pedine.");
                System.out.print("Una mossa di salto permette alla pedina di saltare");
                System.out.println("due caselle generandone una nuova.");
                System.out.println(giallo + "Caricamento in corso..." + reset);
                final int delay = 4000;
                Utilities.pausa(delay); // Mostra le mosse disponibili per 4 secondi
            } else if (comando.equals("/abbandona")) {
               if (Quit.confermaAbbandono(partitaInCorso, pedineRimasteAvversario, giocatoreCorrente)) {
                    pedineRimasteAvversario = conteggio[giocatoreCorrente == '\u26C0' ? 1 : 0];
                    determinaVincitorePerAbbandono(giocatoreCorrente, pedineRimasteAvversario);
                    partitaInCorso = false;
               }
            } else if (comando.equals("/tempo")) {
                System.out.println(giallo + "Tempo trascorso: " + getElapsedTime() + reset);
                System.out.println(giallo + "Rientro al gioco in corso..." + reset);
                final int delay = 4000;
                Utilities.pausa(delay); // Mostra le mosse disponibili per 4 secondi
            } else if (comando.equals("/help")) {
                System.out.println("\n I giocatori possono muovere le pedine in orizzontale, verticale, diagonale.");
                System.out.print("Per vedere quali mosse sono disponibili, digita "
                + giallo + "/qualimosse" + reset + " e premi invio.");
                System.out.println("Per sapere quanto tempo è trascorso " + giallo + "/tempo" + reset + ".");
                System.out.println("Per visualizzare lo storico delle mosse, digita  " + giallo
                + "/mosse" + reset + " e premi invio.");
                System.out.println("Per abbandonare la partita, digita " + giallo + "/abbandona"
                + reset + " e premi invio.");
                System.out.println(giallo + "Ritornando alla partita..." + reset);
                final int delay = 4000;
                 Utilities.pausa(delay); // Mostra le mosse disponibili per 4 secondi
            } else if (comando.equals("/mosse")) {
                visualizzaStoricoMosse();
                final int delay = 4000;
                Utilities.pausa(delay); // Mostra le mosse disponibili per 4 secondi
            } else {
                String[] mossa = comando.split("-");
                if (mossa.length == 2) {
                    String from = mossa[0];
                    String to = mossa[1];
                    if (adiacente.eseguiMossa(from, to, giocatoreCorrente)
                        || spostamento.eseguiMossa(from, to, giocatoreCorrente)) {
                        Utilities.pulisciSchermo();
                        System.out.println("Mossa registrata: "
                        + (giocatoreCorrente == '\u26C0' ? "Giocatore 1 (Bianco)"
                        : "Giocatore 2 (Nero)")
                        + " ha mosso da " + from + " a " + to);
                        logMossa(from, to, giocatoreCorrente); // Registra la mossa nel log delle mosse
                        aggiornaEVisualizzaTavoliere();
                        System.out.println(giallo + "Posizionando la pedina..." + reset);
                        giocatoreCorrente = (giocatoreCorrente == '\u26C0') ? '\u26C2' : '\u26C0'; // Cambia giocatore
                        final int delay = 2500;
                        Utilities.pausa(delay); // Mostra le mosse disponibili per 4 secondi
                } else {
                    System.out.println("Mossa non valida. La casella di destinazione");
                    System.out.print("è occupata o la mossa non è nel range.");
                    final int delay = 2000;
                    Utilities.pausa(delay); // Mostra le mosse disponibili per 4 secondi
                    }
                } else {
                    System.out.println("Comando non valido. Utilizzare la notazione algebrica");
                    System.out.println("es. 'a1-a2' o 'a1-a3'.");
                    System.out.println(giallo + "Ritorno al gioco in corso..." + reset);
                    final int delay = 2000;
                    Utilities.pausa(delay); // Mostra le mosse disponibili per 4 secondi
                }
            }
        }
        //il giocatore tot ha tot pedine, il giocatore x ha vinto.
        Menu.setPartitaTerminata(true);
        Quit.getPartitaTerminata(partitaInCorso);
        final int delay = 3000;
        Utilities.pausa(delay);
        System.out.println(giallo + "La partita è terminata. Grazie per aver giocato!" + reset);
        System.out.println(giallo + "Ritorno al menu..." + reset);
        Utilities.pausa(delay); // Mostra le mosse disponibili per 4 secondi
        Utilities.pulisciSchermo(); // Pulisce lo schermo dopo il caricamento del gioco
        Menu.mostraMenuPartita();
    }

    /**
    * Gestisce la visualizzazione delle mosse disponibili per il giocatore corrente.
    * Metodo modificato in modo tale da poter controllare se ci sono pedine mobili e mosse disponibili.
    * Se non ci sono pedine mobili, il turno passa al giocatore successivo.
    */
    public void handleQualiMosse(final MosseDisponibili mosseDisponibili) {
        mosseDisponibili.ripristinaTavoliere();
        List<String> pedineMobili = mosseDisponibili.getPedineMobili(giocatoreCorrente);
        if (pedineMobili.isEmpty()) {
            System.out.println("Non ci sono pedine che possono essere mosse.");
            System.out.println("Nessuna mossa disponibile. Il turno passa al giocatore "
            + (giocatoreCorrente == '\u26C0' ? "2 (Nero)" : "1 (Bianco)") + ".");
            giocatoreCorrente = (giocatoreCorrente == '\u26C0') ? '\u26C2' : '\u26C0'; // Cambia giocatore
            return;
        }
        System.out.println("Pedine che possono essere mosse:");
        for (String pedina : pedineMobili) {
            System.out.println(pedina);
        }
        System.out.println("Specificare la posizione della pedina per visualizzare le mosse (es. 'a1'): ");
        if (!tipo.hasNextLine()) {
            return;
        }
        String posizione = tipo.nextLine().trim().toUpperCase();
        int[] coords = notazioneAlgebricaToCoord(posizione);
        if (coords != null && pedineMobili.contains(posizione)) {
            List<String> mosse = mosseDisponibili.calcolaEMostraMosseDisponibili(
                giocatoreCorrente, coords[0], coords[1]
            );
            if (!mosse.isEmpty()) {
                System.out.println("Puoi posizionare la tua pedina nelle seguenti posizioni:");
                for (String mossa : mosse) {
                    System.out.println(mossa);
                }
            } else {
                System.out.println("Non ci sono mosse disponibili per la pedina selezionata.");
                System.out.println("Nessuna mossa disponibile. Il turno passa al giocatore "
                + (giocatoreCorrente == '\u26C0' ? "2 (Nero)" : "1 (Bianco)") + ".");
                giocatoreCorrente = (giocatoreCorrente == '\u26C0') ? '\u26C2' : '\u26C0'; // Cambia giocatore
            }
        } else {
            System.out.println("Posizione non valida o la pedina non può essere mossa.");
        }
    }

    private void aggiornaEVisualizzaTavoliere() {
        System.out.println("Aggiornamento del tavoliere:");
        tavoliere.stampaTavoliere();
    }

    /**
     * Converte una notazione algebrica in coordinate del tavoliere.
     * @param notazione la notazione algebrica (es. "a1")
     * @return un array di due interi rappresentanti le coordinate [riga, colonna] o null se non valida.
     */
    public int[] notazioneAlgebricaToCoord(final String notazione) {
        if (notazione == null || notazione.length() != 2) {
            return null;
        }
        char col = Character.toUpperCase(notazione.charAt(0)); // Convertiamo il carattere a maiuscolo
        char row = notazione.charAt(1);
        if (!Character.isDigit(row) || col < 'A' || col > 'H') {
            return null;
        }
        int x = row - '1';
        int y = col - 'A';
        if (x < 0 || x >= tavoliere.getDimensione() || y < 0 || y >= tavoliere.getDimensione()) {
            return null;
        }
        return new int[]{x, y};
    }

    /**
     * Calcola il tempo trascorso dall'inizio della partita.
     *
     * @return il tempo trascorso in formato ore:minuti:secondi.
     */
    public String getElapsedTime() {
        Duration duration = Duration.between(startTime, Instant.now());
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }


/**
 * Determina il vincitore in base al numero di pedine.
 *
 * @param conteggioPedine array con il numero di pedine di ciascun giocatore
 * @return una stringa che indica il vincitore
 */
public String determinareVincitore(final int[] conteggioPedine) {
    if (conteggioPedine[0] > conteggioPedine[1]) {
        return "Giocatore 1 (Bianco) ha vinto con " + conteggioPedine[0]
        + " pedine contro " + conteggioPedine[1] + " pedine.";
    } else {
        return "Giocatore 2 (Nero) ha vinto con " + conteggioPedine[1]
        + " pedine contro " + conteggioPedine[0] + " pedine.";
    }
}
/**
 * Determina il vincitore della partita per abbandono.
 */
public static void determinaVincitorePerAbbandono(final char giocatoreCorrente, final int pedineRimasteAvversario) {
    String giallo = "\u001B[33m";
    String reset = "\u001B[0m";
    String vincitore = giocatoreCorrente == '\u26C0' ? "Giocatore 2 (Nero)" : "Giocatore 1 (Bianco)";
    System.out.println("Il giocatore " + giallo + vincitore + reset + " ha vinto per abbandono.");
    System.out.println("Vincitore: " + giallo + vincitore + reset + " con "
    + giallo + pedineRimasteAvversario + reset + " pedine!");
    Menu.setPartitaTerminata(true);
}

/**
     * Registra la mossa nel log delle mosse.
     *
     * @param from la posizione di partenza in notazione algebrica
     * @param to la posizione di arrivo in notazione algebrica
     * @param giocatore il giocatore corrente ('\u26C0' per il giocatore 1, '\u26C2' per il giocatore 2)
     */
    public void logMossa(final String from, final String to, final char giocatore) {
        String mossa = "Giocatore " + (giocatore == '\u26C0' ? "1 (Bianco)" : "2 (Nero)") + ": " + from + "-" + to;
        logMosse.add(mossa);
    }

    /**
     * Visualizza lo storico delle mosse.
     */
    public void visualizzaStoricoMosse() {
        System.out.println("Storico delle mosse:");
        for (String mossa : logMosse) {
            System.out.println(mossa);
        }
    }

    // Metodi getter per i test
    public final Tavoliere getTavoliere() {
        return tavoliere;
    }

    public final List<String> getLogMosse() {
        return Collections.unmodifiableList(logMosse);
    }

}
