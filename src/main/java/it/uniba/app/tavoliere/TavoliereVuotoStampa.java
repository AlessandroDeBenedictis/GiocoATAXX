package it.uniba.app.tavoliere;

/**
 * Classe di utilità per presentare informazioni del tavoliere vuoto.
 */
public final class TavoliereVuotoStampa {
    private TavoliereVuotoStampa() {
        // private constructor to prevent instantiation
    }
    /**
     * Stampa un tavoliere vuoto di dimensione 7x7.
     */
    public static void stampaTavoliereVuoto() {
        final int dimensione = 7;
        // Stampa le lettere sopra il tavoliere
        System.out.print("    ");
        for (int i = 0; i < dimensione; i++) {
            System.out.print(" " + (char) ('A' + i) + "  ");
        }
        System.out.println();
        // Stampa il bordo superiore
        System.out.print("  ");
        for (int i = 0; i < dimensione; i++) {
            System.out.print("+---");
        }
        System.out.println("+");

        // Stampa le righe del tavoliere
        for (int i = 0; i < dimensione; i++) {
            System.out.print((i + 1) + " | ");
            for (int j = 0; j < dimensione; j++) {
                System.out.print("- | ");
            }
            System.out.println((i + 1));
            // Stampa il bordo orizzontale tra le righe
            System.out.print("  ");
            for (int k = 0; k < dimensione; k++) {
                System.out.print("+---");
            }
            System.out.println("+");
        }
    }
}
