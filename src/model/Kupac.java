/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Objects;
import model_enum.PravniStatus;

/**
 *
 * @author Korisnik
 */
public class Kupac {
    
    private String naziv;
    private PravniStatus pravniStatus;
    private String adresa;
    private String mesto;
    private String pib;
    private String maticniBroj;

    public Kupac() {
    }

    public Kupac(String naziv, PravniStatus pravniStatus, String adresa, String mesto, String pib, String maticniBroj) {
        this.naziv = naziv;
        this.pravniStatus = pravniStatus;
        this.adresa = adresa;
        this.mesto = mesto;
        this.pib = pib;
        this.maticniBroj = maticniBroj;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public PravniStatus getPravniStatus() {
        return pravniStatus;
    }

    public void setPravniStatus(PravniStatus pravniStatus) {
        this.pravniStatus = pravniStatus;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public String getPib() {
        return pib;
    }

    public void setPib(String pib) {
        this.pib = pib;
    }

    public String getMaticniBroj() {
        return maticniBroj;
    }

    public void setMaticniBroj(String maticniBroj) {
        this.maticniBroj = maticniBroj;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.pib);
        hash = 83 * hash + Objects.hashCode(this.maticniBroj);
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
        final Kupac other = (Kupac) obj;
        if (!Objects.equals(this.pib, other.pib)) {
            return false;
        }
        return Objects.equals(this.maticniBroj, other.maticniBroj);
    }

    @Override
    public String toString() {
        return naziv;
    }
    
    
    
}
