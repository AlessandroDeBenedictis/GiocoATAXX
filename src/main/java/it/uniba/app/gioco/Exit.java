package it.uniba.app.gioco;

/**
 * La classe Exit è una classe di controllo perché responsabile per la gestione del flusso di controllo.
 * Si occupa di gestire l'uscita del gioco e di salvare lo stato del gioco prima di uscire.
 */
public class Exit {

    /**
     * Esegue l'uscita dal gioco.
     * Se il gioco è salvato esce dal gioco, altrimenti stampa un messaggio di errore.
     */
    public void exitGame() {
        // Logica per salvare lo stato del gioco potrebbe essere qui
        // Se lo stato è salvato con successo, eseguire l'uscita
        ExitHandler.getInstance().exitGame();
    }
}
