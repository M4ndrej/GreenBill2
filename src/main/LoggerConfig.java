package main;

import java.io.IOException;
import java.util.logging.*;

public class LoggerConfig {

    private static final Logger logger = Logger.getLogger("AppLogger");

    public static void setup() {
        try {
            // Kreiraj FileHandler (možeš staviti i putanju npr. "logs/app.log")
            Handler fileHandler = new FileHandler("app.log", true); // true = append

            // Format loga u fajlu (npr. jednostavan tekst)
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);

            // Dodaj handler Logger-u
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL); // beleži sve nivoe
            logger.setUseParentHandlers(false); // da ne ide i na konzolu

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Greška->sacuvajIzmene metoda", e);
//            System.err.println("Greška pri postavljanju loggera: " + e.getMessage());
        }
    }

    public static Logger getLogger() {
        return logger;
    }
}
