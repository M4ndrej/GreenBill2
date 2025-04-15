
package table_models;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Lokalitet;

/**
 *
 * @author Andrej
 */
public class LokalitetModelTabele extends AbstractTableModel{
    
    private String[] kolone = {"ID","Naziv"};
    private List<Lokalitet> listaLokaliteta = new ArrayList<>();

    public LokalitetModelTabele(List<Lokalitet> lista) {
        listaLokaliteta = lista;
    }

    @Override
    public int getRowCount() {
        return listaLokaliteta.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Lokalitet lokalitet = listaLokaliteta.get(rowIndex);
        switch(columnIndex){
        case 0:
            return lokalitet.getId();
        case 1:
            return lokalitet.getNaziv();
        default:
            return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Lokalitet> getLista() {
         return listaLokaliteta;
    }
    
}
