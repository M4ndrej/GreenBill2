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
public class OdeljenjeOdsek {
    
    private int id;
    private String naziv;
    private double doznaka;
    private Date datumDoznake;
    private GazdinskaJedinica gazdinskaJedinica;

    public OdeljenjeOdsek() {
    }

    public OdeljenjeOdsek(int id, String naziv, double doznaka, Date datumDoznake, GazdinskaJedinica gazdinskaJedinica) {
        this.id = id;
        this.naziv = naziv;
        this.doznaka = doznaka;
        this.datumDoznake = datumDoznake;
        this.gazdinskaJedinica = gazdinskaJedinica;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getDoznaka() {
        return doznaka;
    }

    public void setDoznaka(double doznaka) {
        this.doznaka = doznaka;
    }

    public Date getDatumDoznake() {
        return datumDoznake;
    }

    public void setDatumDoznake(Date datumDoznake) {
        this.datumDoznake = datumDoznake;
    }

    public GazdinskaJedinica getGazdinskaJedinica() {
        return gazdinskaJedinica;
    }

    public void setGazdinskaJedinica(GazdinskaJedinica gazdinskaJedinica) {
        this.gazdinskaJedinica = gazdinskaJedinica;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + this.id;
        hash = 11 * hash + Objects.hashCode(this.gazdinskaJedinica);
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
        final OdeljenjeOdsek other = (OdeljenjeOdsek) obj;
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.gazdinskaJedinica, other.gazdinskaJedinica);
    }

    @Override
    public String toString() {
        return gazdinskaJedinica.getNaziv() +" "+naziv;
    }
    
    
    
    
}
