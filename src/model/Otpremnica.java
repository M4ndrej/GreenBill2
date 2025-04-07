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
public class Otpremnica implements OpstiDomenskiObjekat {

    private String broj;
    private Date datum;
    private double ukupnaCena;
    private boolean verifikovana;
    private Menadzer menadzer;
    private Otpremac otpremac;
    private GazdinskaJedinica gazdinskaJedinica;
    private Kupac kupac;

    public Otpremnica() {
    }

    public Otpremnica(String broj, Date datum, double ukupnaCena, boolean verifikovana, Menadzer menadzer, Otpremac otpremac, GazdinskaJedinica gazdinskaJedinica, Kupac kupac) {
        this.broj = broj;
        this.datum = datum;
        this.ukupnaCena = ukupnaCena;
        this.verifikovana = verifikovana;
        this.menadzer = menadzer;
        this.otpremac = otpremac;
        this.gazdinskaJedinica = gazdinskaJedinica;
        this.kupac = kupac;
    }

    public String getBroj() {
        return broj;
    }

    public void setBroj(String broj) {
        this.broj = broj;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public double getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(double ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    public boolean isVerifikovana() {
        return verifikovana;
    }

    public void setVerifikovana(boolean verifikovana) {
        this.verifikovana = verifikovana;
    }

    public Menadzer getMenadzer() {
        return menadzer;
    }

    public void setMenadzer(Menadzer menadzer) {
        this.menadzer = menadzer;
    }

    public Otpremac getOtpremac() {
        return otpremac;
    }

    public void setOtpremac(Otpremac otpremac) {
        this.otpremac = otpremac;
    }

    public GazdinskaJedinica getGazdinskaJedinica() {
        return gazdinskaJedinica;
    }

    public void setGazdinskaJedinica(GazdinskaJedinica gazdinskaJedinica) {
        this.gazdinskaJedinica = gazdinskaJedinica;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.broj);
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
        final Otpremnica other = (Otpremnica) obj;
        return Objects.equals(this.broj, other.broj);
    }

    @Override
    public boolean napuni(ResultSet rs) {
        try {
            broj = rs.getString("otp_doc.broj");
            java.sql.Date sqlDate = rs.getDate("otp_doc.datum");
            datum = new Date(sqlDate.getTime());
            ukupnaCena = rs.getDouble("otp_doc.ukupnaCena");
            verifikovana = rs.getBoolean("otp_doc.verifikovana");
        } catch (SQLException ex) {
            Logger.getLogger(Otpremnica.class.getName()).log(Level.SEVERE, null, ex);
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
        return "otpremnica otp_doc";
    }

    @Override
    public String vratiImeKlaseUpisi() {
        return "otpremnica";
    }

    @Override
    public String vratiVrednostAtributa() {
        java.sql.Date sqlDatum = new java.sql.Date(datum.getTime());
        return "('" + broj + "','" + sqlDatum + "'," + ukupnaCena + "," + verifikovana + ",'" + menadzer.getJmbg() + "','" + otpremac.getJmbg() + "'," + gazdinskaJedinica.getSifra() + "," + gazdinskaJedinica.getLokalitet().getId() + ",'" + kupac.getPib() + "','" + kupac.getMaticniBroj() + "')";
    }

    @Override
    public String postaviVrednostAtributa() {
        java.sql.Date sqlDatum = new java.sql.Date(datum.getTime());
        return "broj='" + broj + "',datum='" + sqlDatum + "',ukupnaCena=" + ukupnaCena + ",verifikovana=" + verifikovana + ",menadzer='" + menadzer.getJmbg() + "',otpremac='" + otpremac.getJmbg() + "',gazdinskaJedinica=" + gazdinskaJedinica.getSifra() + ",kupacPib='" + kupac.getPib() + "',kupacMaticniBroj='" + kupac.getMaticniBroj() + "'";
    }

    @Override
    public String vratiListuAtributa() {
        return "(broj,datum,ukupnaCena,verifikovana,menadzer,otpremac,gazdinskaJedinica,lokalitet,kupacPib,kupacMaticniBroj)";
    }

    @Override
    public String vratiUslovNadjiSlog() {
        return "broj="+this.getBroj();
    }

    @Override
    public String vratiUslovNadjiSlogove() {
        StringBuilder sb = new StringBuilder();
//        sb.append("");
        boolean prev = false;
        if(this.broj.matches("^\\d+$")){
            sb.append("broj = "+this.broj);
            prev = true;
        }
        if(!this.kupac.getNaziv().isEmpty()){
            if(prev){
                sb.append(" AND ");
            }
            sb.append(" naziv LIKE LOWER ('"+this.kupac.getNaziv().toLowerCase()+"%') ");
            prev = true;
        }
        if(this.otpremac != null){
            if(prev){
                sb.append(" AND ");
            }
            sb.append(" otpremac LIKE ('"+this.otpremac.getJmbg()+"')");
        }
        return sb.toString();
    }

    @Override
    public boolean postojiRelacija() {
        return true;
    }

}
