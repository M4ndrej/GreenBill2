
package table_models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Otpremnica;


/**
 *
 * @author Andrej
 */
public class OtpremnicaModelTabele extends AbstractTableModel{
    
    private String[] kolone = {"Broj","Datum otpremnice","Kupac","Proknjižena"};
    private List<Otpremnica> listaOtprmenica = new ArrayList<>();

    public OtpremnicaModelTabele(List<Otpremnica> lista) {
        listaOtprmenica = lista;
    }

    @Override
    public int getRowCount() {
        return listaOtprmenica.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Otpremnica otpremnica = listaOtprmenica.get(rowIndex);
        SimpleDateFormat format  = new SimpleDateFormat("dd-MM-yyyy");
        switch (columnIndex) {
        case 0:
            return otpremnica.getBroj();  // Kolona "Broj"
        case 1:
            return format.format(otpremnica.getDatum());  // Kolona "Datum otpremnice"
        case 2:
            return otpremnica.getKupac().getNaziv();  // Kolona "Kupac" - Pretpostavljamo da postoji getKupac() metoda koja vraća objekat Kupac
        case 3:
            return otpremnica.isVerifikovana() ? "Proknjižena": "Neproknjižena";
        default:
            return null;  // Ako je index van opsega
    }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == 5){
            return Boolean.class;
        }
        return String.class;
    }
    
    public List<Otpremnica> getList(){
        return listaOtprmenica;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Otpremnica> getLista() {
        return listaOtprmenica;
    }

 
    
}
