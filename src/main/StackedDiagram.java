package main;

import java.awt.BorderLayout;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.List;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.ui.TextAnchor;

public class StackedDiagram {

     public static void createStackedBarChart(JPanel panel, List<String> mesta, List<Integer> vreme, boolean isGodina, List<Double> kolicine, String cenaKolicina ,String prosekUkupno) {
        
        panel.setLayout(new BorderLayout());
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 0; i < mesta.size(); i++) {
            dataset.addValue(kolicine.get(i), mesta.get(i), vreme.get(i));
        }

        JFreeChart chart = ChartFactory.createBarChart(
                (prosekUkupno.equals("PROSEK")?"Prosečna ":(prosekUkupno.equals("UKUPNO")?"Ukupna ":"Udeo "))+(cenaKolicina.equals("CENA")?"cena po ":"količina po ")+(isGodina ? "godinama":"mesecima"),
                (isGodina ? "Godine":"Meseci"),                 
                "Ukupna količina",            
                dataset                     
        );

        BarRenderer renderer = (BarRenderer) chart.getCategoryPlot().getRenderer();
        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator()); 
        renderer.setDefaultItemLabelsVisible(false);

        renderer.setDefaultPositiveItemLabelPosition(
                new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER)
        );  

        panel.removeAll();
        panel.setLayout(new java.awt.BorderLayout());
        panel.add(new ChartPanel(chart), java.awt.BorderLayout.CENTER);
        panel.revalidate();
        panel.repaint();
    }
}
