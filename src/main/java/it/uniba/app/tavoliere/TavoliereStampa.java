package it.uniba.app.tavoliere;

/**
 * Classe di utilità per presentare informazioni del tavoliere.
 */
public final class TavoliereStampa {
    private TavoliereStampa() {
        // private constructor to prevent instantiation
    }
    private static final String GIALLO = "\u001B[33m";
    private static final String RESET = "\u001B[0m";
    private static final String ARANCIONE = "\u001B[31m";

    /**
     * Stampa il tavoliere con le pedine iniziali posizionate.
     */
    public static void stampaTavoliere(final char[][] tavoliere) {
        final int dimensione = tavoliere.length;
        System.out.print("    ");
        for (int i = 0; i < dimensione; i++) {
            System.out.print(" " + (char) ('A' + i) + "  ");
        }
        System.out.println();
        System.out.print("  ");
        for (int i = 0; i < dimensione; i++) {
            System.out.print("+---");
        }
        System.out.println("+");
        for (int i = 0; i < dimensione; i++) {
            System.out.print((i + 1) + " | ");
            for (int j = 0; j < dimensione; j++) {
                char cella = tavoliere[i][j];
                if (cella == 'X') {
                    System.out.print(GIALLO + cella + RESET + " | ");
                } else if (cella == 'Y') {
                    System.out.print(ARANCIONE + cella + RESET + " | ");
                } else {
                    System.out.print(cella + " | ");
                }
            }
            System.out.println((i + 1));
            System.out.print("  ");
            for (int k = 0; k < dimensione; k++) {
                System.out.print("+---");
            }
            System.out.println("+");
        }
    }

    /**
     * Aggiorna lo stato visivo del tavoliere.
     */
    public static void aggiornaStato(final char[][] tavoliere) {
        final int dimensione = tavoliere.length;
        System.out.println("Aggiornamento del tavoliere:");
        System.out.print("    ");
        for (int i = 0; i < dimensione; i++) {
            System.out.print(" " + (char) ('A' + i) + "  ");
        }
        System.out.println();
        System.out.print("  ");
        for (int i = 0; i < dimensione; i++) {
            System.out.print("+---");
        }
        System.out.println("+");
        for (int i = 0; i < dimensione; i++) {
            System.out.print((i + 1) + " | ");
            for (int j = 0; j < dimensione; j++) {
                System.out.print(tavoliere[i][j] + " | ");
            }
            System.out.println((i + 1));
            System.out.print("  ");
            for (int k = 0; k < dimensione; k++) {
                System.out.print("+---");
            }
            System.out.println("+");
        }
    }
}
