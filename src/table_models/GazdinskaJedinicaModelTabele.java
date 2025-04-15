
package table_models;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.GazdinskaJedinica;

/**
 *
 * @author Andrej
 */
public class GazdinskaJedinicaModelTabele extends AbstractTableModel{
    
    private String[] kolone = {"Šifra","Naziv","Lokalitet"};
    private List<GazdinskaJedinica> listaGJ = new ArrayList<>();

    public GazdinskaJedinicaModelTabele(List<GazdinskaJedinica> lista) {
        listaGJ = lista;
    }

    @Override
    public int getRowCount() {
        return listaGJ.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        GazdinskaJedinica gazdinskaJedinica = listaGJ.get(rowIndex);
        switch(columnIndex){
        case 0:
            return gazdinskaJedinica.getSifra();
        case 1:
            return gazdinskaJedinica.getNaziv();
        case 2:
            return gazdinskaJedinica.getLokalitet().getNaziv();
        default:
            return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<GazdinskaJedinica> getLista() {
         return listaGJ;
    }
    
}
