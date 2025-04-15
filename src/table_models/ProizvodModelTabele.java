
package table_models;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Proizvod;


/**
 *
 * @author Andrej
 */
public class ProizvodModelTabele extends AbstractTableModel{
    
    private String[] kolone = {"Tip", "Vrsta", "Klasa", "Cena", "Jedinica mere", "Opis"};
    private List<Proizvod> listaProizvoda = new ArrayList<>();

    public ProizvodModelTabele(List<Proizvod> lista) {
        listaProizvoda = lista;
    }

    @Override
    public int getRowCount() {
        return listaProizvoda.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Proizvod proizvod = listaProizvoda.get(rowIndex);
        switch(columnIndex){
        case 0:
            return proizvod.getTip();
        case 1:
            return proizvod.getVrsta();
        case 2:
            return proizvod.getKlasa();
        case 3:
            return proizvod.getCena();
        case 4:
            return proizvod.getJedinicaMere();
        case 5:
            return proizvod.getOpis();
        default:
            return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Proizvod> getLista() {
        return listaProizvoda;
    }
    
}
