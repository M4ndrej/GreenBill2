package konfiguracija;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.LoggerConfig;

/**
 *
 * @author Andrej
 */
public class Konfiguracija {

    static final Logger logger = LoggerConfig.getLogger();

    private static Konfiguracija instance;
    private Properties config = new Properties();
    private static final String CONFIG_PATH = "resources\\config.properties";

    private Konfiguracija() {
        if (konfiguracijaPostoji()) {
            try {
                FileInputStream fis = new FileInputStream(CONFIG_PATH);
                config.load(fis);
            } catch (FileNotFoundException ex) {
//                Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
                logger.log(Level.SEVERE, "Greška->konfiguracija metoda", ex);
            } catch (IOException ex) {
//                Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
                logger.log(Level.SEVERE, "Greška->konfiguracija metoda", ex);
            }
        }
    }

    private boolean konfiguracijaPostoji() {
        File file = new File(CONFIG_PATH);
        return file.exists() && file.length() > 0;
    }

    public static Konfiguracija getInstance() {
        if (instance == null) {
            instance = new Konfiguracija();
        }
        return instance;
    }

    public String getPropertie(String key) {
        return config.getProperty(key, "");
    }

    public void setPropertie(String key, String value) {
        config.setProperty(key, value);
    }

    public void sacuvajIzmene() {
        try {
            config.store(new FileOutputStream(CONFIG_PATH), null);
        } catch (FileNotFoundException ex) {
//            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
            logger.log(Level.SEVERE, "Greška->sacuvajIzmene metoda", ex);
        } catch (IOException ex) {
//            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);   
            logger.log(Level.SEVERE, "Greška->sacuvajIzmene metoda", ex);
        }
    }

    public void kreirajPrazanFajl() {
        File file = new File(CONFIG_PATH);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("Uspesno kreiran");
                } else {
                    System.out.println("Nije kreiran");
                }
            } catch (IOException ex) {
//                Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
                logger.log(Level.SEVERE, "Greška->kreirajPrazanFajl metoda", ex);
            }
        }
    }
}
