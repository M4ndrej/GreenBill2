/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Korisnik
 */
public class Racun implements OpstiDomenskiObjekat {

    private String broj;
    private String pozivNaBroj;
    private String napomena;
    private String nacinPlacanja;
    private String mesto;
    private Date datum;
    private double osnovica;
    private double pdv;
    private Otpremnica otpremnica;
    private String komercijalista;

    public Racun() {
    }

    public Racun(String broj, String pozivNaBroj, String napomena, String nacinPlacanja, String mesto, Date datum, double osnovica, double pdv, Otpremnica otpremnica, String komercijalista) {
        this.broj = broj;
        this.pozivNaBroj = pozivNaBroj;
        this.napomena = napomena;
        this.nacinPlacanja = nacinPlacanja;
        this.mesto = mesto;
        this.datum = datum;
        this.osnovica = osnovica;
        this.pdv = pdv;
        this.otpremnica = otpremnica;
        this.komercijalista = komercijalista;
    }

    public String getBroj() {
        return broj;
    }

    public void setBroj(String broj) {
        this.broj = broj;
    }

    public String getPozivNaBroj() {
        return pozivNaBroj;
    }

    public void setPozivNaBroj(String pozivNaBroj) {
        this.pozivNaBroj = pozivNaBroj;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public String getNacinPlacanja() {
        return nacinPlacanja;
    }

    public void setNacinPlacanja(String nacinPlacanja) {
        this.nacinPlacanja = nacinPlacanja;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public double getOsnovica() {
        return osnovica;
    }

    public void setOsnovica(double osnovica) {
        this.osnovica = osnovica;
    }

    public double getPdv() {
        return pdv;
    }

    public void setPdv(double pdv) {
        this.pdv = pdv;
    }

    public Otpremnica getOtpremnica() {
        return otpremnica;
    }

    public void setOtpremnica(Otpremnica otpremnica) {
        this.otpremnica = otpremnica;
    }

    public String getKomercijalista() {
        return komercijalista;
    }

    public void setKomercijalista(String komercijalista) {
        this.komercijalista = komercijalista;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.broj);
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
        final Racun other = (Racun) obj;
        return Objects.equals(this.broj, other.broj);
    }

    @Override
    public boolean napuni(ResultSet rs) {
        try {
            broj = rs.getString("r.broj");
            pozivNaBroj = rs.getString("r.pozivNaBroj");
            napomena = rs.getString("r.napomena");
            nacinPlacanja = rs.getString("r.nacinPlacanja");
            mesto = rs.getString("r.mesto");
            java.sql.Date sqlDatum = rs.getDate("r.datum");
            datum = new Date(sqlDatum.getTime());
            osnovica = rs.getDouble("r.osnovica");
            pdv = rs.getDouble("r.pdv");
            komercijalista = rs.getString("r.komercijalista");
        } catch (SQLException ex) {
            Logger.getLogger(Racun.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public String vratiKljuc() {
        return broj;
    }

    @Override
    public String vratiImeKlaseUcitaj() {
        return "racun r";
    }

    @Override
    public String vratiImeKlaseUpisi() {
        return "racun";
    }

    @Override
    public String vratiVrednostAtributa() {
        java.sql.Date sqlDatum = new java.sql.Date(datum.getTime());
        return "('" + broj + "','" + pozivNaBroj + "','" + napomena + "','" + nacinPlacanja + "','" + mesto + "','" + sqlDatum + "'," + osnovica + "," + pdv + ",'" + otpremnica.getBroj() + "','" + komercijalista + "')";
    }

    @Override
    public String postaviVrednostAtributa() {
        java.sql.Date sqlDatum = new java.sql.Date(datum.getTime());
        return "broj='" + broj + "',pozivNaBroj='" + pozivNaBroj + "',napomena='" + napomena + "',nacinPlacanja='" + nacinPlacanja + "',mesto='" + mesto + "',datum='" + sqlDatum + "',osnovica=" + osnovica + ",pdv=" + pdv + ",otpremnica='" + otpremnica.getBroj() + "',komercijalista='" + komercijalista + "'";
    }

    @Override
    public String vratiListuAtributa() {
        return "(broj,pozivNaBroj,napomena,nacinPlacanja,mesto,datum,osnovica,pdv,otpremnica,komercijalista)";
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
