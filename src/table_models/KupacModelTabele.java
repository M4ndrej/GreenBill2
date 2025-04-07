/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package table_models;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Kupac;

/**
 *
 * @author Andrej
 */
public class KupacModelTabele extends AbstractTableModel{
    
    private String[] kolone = {"Naziv","Pravni status","Adresa","Mesto","Pib","Maticni broj"};
    private List<Kupac> listaKupaca = new ArrayList<>();

    public KupacModelTabele(List<Kupac> lista) {
        listaKupaca = lista;
    }

    @Override
    public int getRowCount() {
        return listaKupaca.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Kupac kupac = listaKupaca.get(rowIndex);
        switch (columnIndex) {
        case 0:
            return kupac.getNaziv();  // Kolona "Naziv"
        case 1:
            return kupac.getPravniStatus();  // Kolona "Pravni status"
        case 2:
            return kupac.getAdresa();  // Kolona "Adresa"
        case 3:
            return kupac.getMesto();  // Kolona "Mesto"
        case 4:
            return kupac.getPib();  // Kolona "Pib"
        case 5:
            return kupac.getMaticniBroj();  // Kolona "Matični broj"
        default:
            return null;  // Ako je index van opsega
    }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Kupac> getList() {
        return listaKupaca;
    }
    
}
