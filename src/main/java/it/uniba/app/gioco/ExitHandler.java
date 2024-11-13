package it.uniba.app.gioco;

/**
 * Classe per uscire dal gioco.
 */
public class ExitHandler {
    private static ExitHandler instance = new ExitHandler();

    public static ExitHandler getInstance() {
        return instance;
    }

    public static void setInstance(final ExitHandler newInstance) {
        ExitHandler.instance = newInstance;
    }
    /**
     * Metodo per uscire dal gioco.
     */
    public void exitGame() {
        System.exit(0);
    }
}
