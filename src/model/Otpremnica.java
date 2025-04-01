/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Korisnik
 */
public class Otpremnica {
    
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
    
    
    
}
