package main;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class HistogramExample {

    public static void kreirajGrafikon(JPanel panel, ArrayList<Integer> vreme, boolean isGodina, ArrayList<Double> kolicine, String cenaKolicina ,String prosekUkupno) {
        panel.setLayout(new BorderLayout());

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 0; i < vreme.size(); i++) {
            dataset.addValue(kolicine.get(i), "Otprema", vreme.get(i));
        }

        JFreeChart chart = ChartFactory.createBarChart(
                (prosekUkupno.equals("PROSEK")?"Prosečna ":(prosekUkupno.equals("UKUPNO")?"Ukupna ":"Udeo "))+(cenaKolicina.equals("CENA")?"cena po ":"količina po ")+(isGodina ? "godinama":"mesecima"),
                (isGodina ? "Godina" : "Mesec"),
                "Vrednost", 
                dataset, 
                PlotOrientation.VERTICAL,
                true, true, false
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(panel.getWidth(), panel.getHeight()));

        panel.removeAll();
        panel.add(chartPanel, BorderLayout.CENTER); 
        panel.revalidate();
        panel.repaint();
    }

}
