
package table_models;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Otpremac;


/**
 *
 * @author Andrej
 */
public class OtpremacModelTabele extends AbstractTableModel{
    
    private String[] kolone = {"Ime i prezime"};
    private List<Otpremac> listaOtpremaca = new ArrayList<>();

    public OtpremacModelTabele(List<Otpremac> lista) {
        listaOtpremaca = lista;
    }

    @Override
    public int getRowCount() {
        return listaOtpremaca.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Otpremac otpremac = listaOtpremaca.get(rowIndex);
        switch(columnIndex){
        case 0:
            return otpremac.getImePrezime();
        default:
            return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Otpremac> getLista() {
        return listaOtpremaca;
    }
    
}
