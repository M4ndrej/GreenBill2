/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Objects;

/**
 *
 * @author Korisnik
 */
public class StavkaOtpremnice {
    
    private int redniBroj;
    private Otpremnica otpremnica;
    private double ukupnaKolicina;
    private double iznos;
    private double rabat;
    private int pdv;
    private double izbosPdv;
    private double ukupnoSaPdv;
    private Proizvod proizvod;
    private OdeljenjeOdsek odeljenjeOdsek;

    public StavkaOtpremnice() {
    }

    public StavkaOtpremnice(int redniBroj, Otpremnica otpremnica, double ukupnaKolicina, double iznos, double rabat, int pdv, double izbosPdv, double ukupnoSaPdv, Proizvod proizvod, OdeljenjeOdsek odeljenjeOdsek) {
        this.redniBroj = redniBroj;
        this.otpremnica = otpremnica;
        this.ukupnaKolicina = ukupnaKolicina;
        this.iznos = iznos;
        this.rabat = rabat;
        this.pdv = pdv;
        this.izbosPdv = izbosPdv;
        this.ukupnoSaPdv = ukupnoSaPdv;
        this.proizvod = proizvod;
        this.odeljenjeOdsek = odeljenjeOdsek;
    }

    public int getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(int redniBroj) {
        this.redniBroj = redniBroj;
    }

    public Otpremnica getOtpremnica() {
        return otpremnica;
    }

    public void setOtpremnica(Otpremnica otpremnica) {
        this.otpremnica = otpremnica;
    }

    public double getUkupnaKolicina() {
        return ukupnaKolicina;
    }

    public void setUkupnaKolicina(double ukupnaKolicina) {
        this.ukupnaKolicina = ukupnaKolicina;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public double getRabat() {
        return rabat;
    }

    public void setRabat(double rabat) {
        this.rabat = rabat;
    }

    public int getPdv() {
        return pdv;
    }

    public void setPdv(int pdv) {
        this.pdv = pdv;
    }

    public double getIzbosPdv() {
        return izbosPdv;
    }

    public void setIzbosPdv(double izbosPdv) {
        this.izbosPdv = izbosPdv;
    }

    public double getUkupnoSaPdv() {
        return ukupnoSaPdv;
    }

    public void setUkupnoSaPdv(double ukupnoSaPdv) {
        this.ukupnoSaPdv = ukupnoSaPdv;
    }

    public Proizvod getProizvod() {
        return proizvod;
    }

    public void setProizvod(Proizvod proizvod) {
        this.proizvod = proizvod;
    }

    public OdeljenjeOdsek getOdeljenjeOdsek() {
        return odeljenjeOdsek;
    }

    public void setOdeljenjeOdsek(OdeljenjeOdsek odeljenjeOdsek) {
        this.odeljenjeOdsek = odeljenjeOdsek;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.redniBroj;
        hash = 37 * hash + Objects.hashCode(this.otpremnica);
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
        final StavkaOtpremnice other = (StavkaOtpremnice) obj;
        if (this.redniBroj != other.redniBroj) {
            return false;
        }
        return Objects.equals(this.otpremnica, other.otpremnica);
    }
    
        
}
