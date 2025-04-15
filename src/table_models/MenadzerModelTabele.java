
package table_models;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Menadzer;

/**
 *
 * @author Andrej
 */
public class MenadzerModelTabele extends AbstractTableModel {

    private String[] kolone = {"Ime i prezime", "Kontakt", "Email", "Privilegija"};
    private List<Menadzer> listaMenadzera = new ArrayList<>();

    public MenadzerModelTabele(List<Menadzer> lista) {
        listaMenadzera = lista;
    }

    @Override
    public int getRowCount() {
        return listaMenadzera.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Menadzer menadzer = listaMenadzera.get(rowIndex);
        switch (columnIndex) {
        case 0:
            return menadzer.getImePrezime();
        case 1:
            return menadzer.getKontakt();
        case 2:
            return menadzer.getEmail();
        case 3:
            return menadzer.getPrivilegija();
        default:
            return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

}
