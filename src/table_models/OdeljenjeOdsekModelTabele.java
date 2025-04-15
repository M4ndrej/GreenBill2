
package table_models;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.OdeljenjeOdsek;

/**
 *
 * @author Andrej
 */
public class OdeljenjeOdsekModelTabele extends AbstractTableModel {

    private String[] kolone = {"Naziv", "Gazdinska jedinica", "Lokalitet"};
    private List<OdeljenjeOdsek> listaOO = new ArrayList<>();

    public OdeljenjeOdsekModelTabele(List<OdeljenjeOdsek> lista) {
        listaOO = lista;
    }

    @Override
    public int getRowCount() {
        return listaOO.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        OdeljenjeOdsek odsekOdeljenje = listaOO.get(rowIndex);
        switch (columnIndex) {
        case 0:
            return odsekOdeljenje.getNaziv();
        case 1:
            return odsekOdeljenje.getGazdinskaJedinica().getNaziv();
        case 2:
            return odsekOdeljenje.getGazdinskaJedinica().getLokalitet().getNaziv();
        default:
            return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<OdeljenjeOdsek> getLista() {
        return listaOO;
    }

}
