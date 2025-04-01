/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import model_enum.JedinicaMere;
import model_enum.Klasa;
import model_enum.Tip;
import model_enum.Vrsta;

/**
 *
 * @author Korisnik
 */
public class Proizvod {
    
    private int id;
    private Tip tip;
    private Vrsta vrsta;
    private Klasa klasa;
    private double cena;
    private String opis;
    private JedinicaMere jedinicaMere;

    public Proizvod() {
    }

    public Proizvod(int id, Tip tip, Vrsta vrsta, Klasa klasa, double cena, String opis, JedinicaMere jedinicaMere) {
        this.id = id;
        this.tip = tip;
        this.vrsta = vrsta;
        this.klasa = klasa;
        this.cena = cena;
        this.opis = opis;
        this.jedinicaMere = jedinicaMere;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }

    public Vrsta getVrsta() {
        return vrsta;
    }

    public void setVrsta(Vrsta vrsta) {
        this.vrsta = vrsta;
    }

    public Klasa getKlasa() {
        return klasa;
    }

    public void setKlasa(Klasa klasa) {
        this.klasa = klasa;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public JedinicaMere getJedinicaMere() {
        return jedinicaMere;
    }

    public void setJedinicaMere(JedinicaMere jedinicaMere) {
        this.jedinicaMere = jedinicaMere;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Proizvod other = (Proizvod) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return tip.toString() + " "+ vrsta.toString()+" "+klasa.toString();
    }
    
    
    
}
