/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;


import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import pogled.PrijavaForma;

/**
 *
 * @author Andrej
 */
public class Main {

    public static void main(String[] args) {
     
        LoggerConfig.setup(); // Pokreni logger konfiguraciju
        Logger logger = LoggerConfig.getLogger();

        logger.info("Aplikacija pokrenuta");

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AppStyles.setLookAndFeel();

                SwingUtilities.invokeLater(() -> {
                    new PrijavaForma().setVisible(true);
                });
            }

        });
    }

}
