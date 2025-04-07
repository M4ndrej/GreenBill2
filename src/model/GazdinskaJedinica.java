/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Korisnik
 */
public class GazdinskaJedinica implements OpstiDomenskiObjekat{
    
    private int sifra;
    private Lokalitet lokalitet;
    private String naziv;

    public GazdinskaJedinica() {
    }

    public GazdinskaJedinica(int sifra, Lokalitet lokalitet, String naziv) {
        this.sifra = sifra;
        this.lokalitet = lokalitet;
        this.naziv = naziv;
    }

    public int getSifra() {
        return sifra;
    }

    public void setSifra(int sifra) {
        this.sifra = sifra;
    }

    public Lokalitet getLokalitet() {
        return lokalitet;
    }

    public void setLokalitet(Lokalitet lokalitet) {
        this.lokalitet = lokalitet;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.sifra;
        hash = 23 * hash + Objects.hashCode(this.lokalitet);
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
        final GazdinskaJedinica other = (GazdinskaJedinica) obj;
        if (this.sifra != other.sifra) {
            return false;
        }
        return Objects.equals(this.lokalitet, other.lokalitet);
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public boolean napuni(ResultSet rs) {
        
        try {
            sifra = rs.getInt("gj.sifra");
            naziv = rs.getString("gj.naziv");
        } catch (SQLException ex) {
            Logger.getLogger(GazdinskaJedinica.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public String vratiKljuc() {
        return "'"+sifra+"'";
    }

    @Override
    public String vratiImeKlaseUcitaj() {
        return "gazdinska_jedinica gj";
    }
    
    @Override
    public String vratiImeKlaseUpisi() {
        return "gazdinska_jedinica";
    }

    @Override
    public String vratiVrednostAtributa() {
        return "("+sifra+","+lokalitet.getId()+",'"+naziv+"')" ;
    }

    @Override
    public String postaviVrednostAtributa() {
        return "sifra="+sifra+",lokalitet="+lokalitet.getId()+",naziv='"+naziv+"'";
    }

    @Override
    public String vratiListuAtributa() {
        return "(sifra, lokalitet, naziv)";
    }

    @Override
    public String vratiUslovNadjiSlog() {
        return "";
    }

    @Override
    public String vratiUslovNadjiSlogove() {
        return "";
    }

    @Override
    public boolean postojiRelacija() {
        return true;
    }
    
    
    
}
