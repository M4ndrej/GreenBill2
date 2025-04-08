package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.BorderFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
//import org.jfree.chart.ui.HorizontalAlignment;
import org.jfree.chart.ui.TextAnchor;

public class AppStyles {

    public static void setLookAndFeel() {
        try {
            // Korišćenje FlatLaf za moderni izgled
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());

            // Postavljanje stila za komponente
            setButtonStyle();
            setTextFieldStyle();
            setComboBoxStyle();
            setTableStyle();
            setModernFont();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    // Postavljanje modernog fonta
   public static void setModernFont() {
        UIManager.put("defaultFont", new Font("Roboto", Font.PLAIN, 12));
        UIManager.put("Button.font", new Font("Roboto", Font.BOLD, 12));
        UIManager.put("Label.font", new Font("Roboto", Font.PLAIN, 12));
        UIManager.put("TextField.font", new Font("Roboto", Font.PLAIN, 12));
        UIManager.put("TextArea.font", new Font("Roboto", Font.PLAIN, 12));
    }

    private static void setButtonStyle() {

        UIManager.put("Button.font", new Font("Arial", Font.PLAIN, 14));
        UIManager.put("Button.arc", 15);  
        UIManager.put("Button.shadow", new Color(50, 50, 50, 50)); 
        UIManager.put("Button.select", new Color(135, 206, 250));
        UIManager.put("Button.rollover", true);
    }

    private static void setTextFieldStyle() {
        UIManager.put("TextField.background", new Color(255, 255, 255, 200)); 
        UIManager.put("TextField.foreground", Color.BLACK);  
        UIManager.put("TextField.font", new Font("Arial", Font.PLAIN, 14));
        UIManager.put("TextField.border", BorderFactory.createLineBorder(new Color(200, 200, 200)));  

    }

    private static void setComboBoxStyle() {
        UIManager.put("ComboBox.background", new Color(240, 255, 240, 200)); 
        UIManager.put("ComboBox.foreground", Color.BLACK);  
        UIManager.put("ComboBox.font", new Font("Arial", Font.PLAIN, 14));
        UIManager.put("ComboBox.border", BorderFactory.createLineBorder(new Color(200, 200, 200)));
       
    }

    private static void setTableStyle() {
    UIManager.put("Table.selectionBackground", new Color(144, 238, 144));
    UIManager.put("Table.selectionForeground", Color.BLACK);
    UIManager.put("Table.font", new Font("Arial", Font.PLAIN, 16));
    UIManager.put("Table.border", BorderFactory.createLineBorder(Color.GRAY, 1));
    UIManager.put("Table.cellBorder", BorderFactory.createLineBorder(Color.GRAY, 1));
    UIManager.put("Table.showGrid", true);
    UIManager.put("Table.intercellSpacing", new java.awt.Dimension(0, 1));
}
     public static void setChartStyles(JFreeChart chart) {

        Font font = new Font("Segoe UI", Font.PLAIN, 12);

        TextTitle title = chart.getTitle();
        title.setFont(new Font("Segoe UI", Font.BOLD, 16));  
        title.setPaint(new GradientPaint(0, 0, new Color(0x1E88E5), 0, 100, new Color(0x1976D2))); 
//        title.setHorizontalAlignment(HorizontalAlignment.CENTER);

        CategoryPlot plot = chart.getCategoryPlot();
        plot.getDomainAxis().setLabelFont(font);
        plot.getRangeAxis().setLabelFont(font);

        plot.getDomainAxis().setTickLabelFont(font);
        plot.getRangeAxis().setTickLabelFont(font);

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setItemMargin(0.10); 
        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setDefaultItemLabelsVisible(false);
        renderer.setDefaultPositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));
        renderer.setSeriesPaint(0, new Color(0x29B6F6));  

        

        plot.setBackgroundPaint(new GradientPaint(0, 0, new Color(240, 240, 240), 0, 1, new Color(255, 255, 255)));
        chart.setBackgroundPaint(new Color(250, 250, 250)); 

        plot.getDomainAxis().setAxisLinePaint(new Color(200, 200, 200));
        plot.getRangeAxis().setAxisLinePaint(new Color(200, 200, 200));
    }

}
