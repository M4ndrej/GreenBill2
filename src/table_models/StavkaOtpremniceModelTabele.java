/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package table_models;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.StavkaOtpremnice;

/**
 *
 * @author Andrej
 */
public class StavkaOtpremniceModelTabele extends AbstractTableModel {

    private String[] kolone;
    private final String[] kolone1 = {"Redni broj", "Proizvod", "Odeljenje i odsek", "Ukupna količina", "Ukupna cena"};
    private final String[] kolone2 = {"Redni broj", "Proizvod", "Odeljenje i odsek", "Ukupna količina", "Iznos bez PDV-a", "Rabat", "PDV", "Iznos PDV-a", "Iznos sa PDV-om"};

    private List<StavkaOtpremnice> listaStavkiOtpremnica = new ArrayList<>();

    public StavkaOtpremniceModelTabele(List<StavkaOtpremnice> lista, boolean isRacun) {
        listaStavkiOtpremnica = lista;
        if (isRacun) {
            kolone = kolone2;
        } else {
            kolone = kolone1;
        }
    }

    @Override
    public int getRowCount() {
        return listaStavkiOtpremnica.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaOtpremnice stavkaOtpremnice = listaStavkiOtpremnica.get(rowIndex);

        switch (columnIndex) {
        case 0:
            return stavkaOtpremnice.getRedniBroj();  // Kolona "Redni broj"
        case 1:
            return stavkaOtpremnice.getProizvod();  // Kolona "Proizvod"
        case 2:
            return stavkaOtpremnice.getOdeljenjeOdsek();  // Kolona "Odeljenje i odsek"
        case 3:
            return String.format("%.2f", stavkaOtpremnice.getUkupnaKolicina());  // Kolona "Ukupna količina"
        case 4:
            return String.format("%.2f", stavkaOtpremnice.getIznos());  // Kolona "Ukupna cena" (ili "Iznos bez PDV-a")
        case 5:
            return String.format("%.2f", stavkaOtpremnice.getRabat());  // Kolona "Rabat"
        case 6:
            return stavkaOtpremnice.getPdv();
        case 7:
            return String.format("%.2f", stavkaOtpremnice.getIznosPdv());  // Kolona "Iznos PDV-a"
        case 8:
            return String.format("%.2f", stavkaOtpremnice.getUkupnoSaPdv());  // Kolona "Iznos sa PDV-om"
        default:
            return null;  // Ako je index van opsega
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void dodajElement(StavkaOtpremnice so) {
        listaStavkiOtpremnica.add(so);
        fireTableDataChanged();
    }

    public List<StavkaOtpremnice> getLista() {
        return listaStavkiOtpremnica;
    }

    public void ukloniStavku(int red) {
        listaStavkiOtpremnica.remove(red);
        fireTableDataChanged();
    }

}
