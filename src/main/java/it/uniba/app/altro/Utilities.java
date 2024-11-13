package it.uniba.app.altro;
/**
 * la classe Utilities  appartiene alla categoria Boundary.
 * È responsabile della visualizzazione di un feedback visivo,
 * in questo caso un indicatore di progresso.
 */
public final class Utilities {
    private Utilities() {
        // costruttore privato per evitare l'istanziazione
    }


    /**
     * Metodo per il caricamento.
     */
    public static void barraDiCaricamento() {
        final int length = 50; // Lunghezza della barra di caricamento
      final int tempo = 100; // Tempo di attesa in millisecondi
      System.out.println("Caricamento in corso...");

      try {
        for (int i = 0; i <= length; i++) {
          // Stampa il progresso (in percentuale)
          System.out.print("\r[" + "=".repeat(i) + " ".repeat(length - i) + "] " + (i * 2) + "%");
          Thread.sleep(tempo); // Simula un ritardo nel caricamento
        }
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }

      System.out.println("\nCaricamento completato!");
    }
    /**
     * Metodo per il caricamento.
     */
    public static void barraDiCaricamentoVeloce() {
      final int length = 50; // Lunghezza della barra di caricamento
    final int tempo = 50; // Tempo di attesa in millisecondi
    try {
      for (int i = 0; i <= length; i++) {
        // Stampa il progresso (in percentuale)
        System.out.print("\r[" + "=".repeat(i) + " ".repeat(length - i) + "] " + (i * 2) + "%");
        Thread.sleep(tempo); // Simula un ritardo nel caricamento
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    System.out.println("\nCaricamento completato!");
  }
    /**
* pulisciSchermo è un metodo che permette di pulire la console.
 */
public static void pulisciSchermo() {
    // Sequenza di escape ANSI per pulire lo schermo
    System.out.print("\033[H\033[2J");
    System.out.flush();
}

/**
 * Stampa il titolo del programma.
 */
public static void stampaTitolo() {
    System.out.println("  ____                    ");
    System.out.println(" / ___|  _ __  _   _  ____    ____      ___");
    System.out.println("| |  _  | '__|| | | ||  _ \\  |  _ \\    / _ \\");
    System.out.println("| |_| | | |   | |_| || |_) | | |_) |  | (_) |");
    System.out.println(" \\____| |_|    \\___/ | ___/  |____/    \\___/");
    System.out.println("                     | |     | |");
    System.out.println("                     |_|     |_|");
    System.out.println("  ____                          ");
    System.out.println(" |  _ )   _ __    ___       ___      _  __     ____      ");
    System.out.println(" |  _ \\  |  __| /  _  \\   /  _  \\   | |/ /   / ___|  ");
    System.out.println(" | |_) | | |   |  (_)  | |  (_)  |  |   <    \\___ \\ ");
    System.out.println(" |____/  |_|    \\ ___ /   \\ ___ /   |_|\\_\\     __) |     ");
    System.out.println("                                              |___/    ");
}
/**
 * Descrizione del gioco.
 */
public static void descrizione() {
  String rosso = "\u001B[31m";
  String giallo = "\u001B[33m";
  String reset = "\u001B[0m";

  // Array di stringhe da stampare
  String[] testo = {
    " ATAXX 1.0.0 Team Brooks ",
    " ",
    "Prodotto da: ",
    " -1 - Alessandro De Benedictis",
    " -2 - Claudio De Benedictis",
    " -3 - Paolo Giampietro",
    " -4 - Francesco Gassi",
    " -5 - Andrea Di Caro"
  };

  // Metodo per alternare i colori dei caratteri
  for (String riga : testo) {
    StringBuilder rigaColorata = new StringBuilder();
    boolean usaRosso = true;
    for (char c : riga.toCharArray()) {
      if (usaRosso) {
        rigaColorata.append(rosso).append(c);
      } else {
        rigaColorata.append(giallo).append(c);
      }
      usaRosso = !usaRosso; // alterna colore
    }
    rigaColorata.append(reset); // resetta colore alla fine della riga
    System.out.println(rigaColorata.toString());
  }
}
/**
     * Pausa esecuzione per un periodo di tempo definito.
     *
     * @param delay Tempo di attesa in millisecondi.
     */
    public static void pausa(final int delay) {
      try {
          Thread.sleep(delay);
      } catch (InterruptedException ex) {
          Thread.currentThread().interrupt(); // Gestione corretta dell'interruzione
      }
  }

}


