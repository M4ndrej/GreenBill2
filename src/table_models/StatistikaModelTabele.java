
package table_models;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Andrej
 */
public class StatistikaModelTabele extends AbstractTableModel{
    
    private String[] kolone = {"Odeljenje i odsek","Gazdinska jedinica","Doznaka","Otprema"};
    private  List<Object[]> otpreme;
    
    public StatistikaModelTabele(List<Object[]> otpreme){
        this.otpreme = new ArrayList<>(otpreme);
    }
    
    @Override
    public int getRowCount() {
        return otpreme.size();
    }

    @Override
    public int getColumnCount() {
            return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    Object[] entry = otpreme.get(rowIndex); // Dobija podatke za red
    
    // Proverava da li je kolona 2 (treća kolona) i da li je vrednost tipa Double
    if (columnIndex == 3 && entry[columnIndex] instanceof Double) {
        double value = (Double) entry[columnIndex];
        // Zaokružuje broj na dve decimale
        return String.format("%.2f", value);
    }

    // Ako nije treća kolona ili nije Double, samo vraća vrednost
    return entry[columnIndex];
}


    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    
    
}
