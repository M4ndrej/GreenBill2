
package table_models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Racun;

/**
 *
 * @author Andrej
 */
public class RacunModelTabele extends AbstractTableModel {

    private String[] kolone = {"Broj", "Datum", "Komercijalista"};
    private List<Racun> listaRacuna = new ArrayList<>();

    public RacunModelTabele(List<Racun> lista) {
        listaRacuna = lista;
    }

    @Override
    public int getRowCount() {
        return listaRacuna.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        Racun racun = listaRacuna.get(rowIndex);
        switch (columnIndex) {
        case 0:
            return racun.getBroj();
        case 1:
            return format.format(racun.getDatum());  // Kolona "Datum otpremnice"
        case 2:
            return racun.getKomercijalista();
        default:
            return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Racun> getLista() {
        return listaRacuna;
    }

}
