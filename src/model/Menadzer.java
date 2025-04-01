/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Objects;
import model_enum.Privilegija;

/**
 *
 * @author Korisnik
 */
public class Menadzer {
    
    private String jmbg;
    private String imePrezime;
    private String email;
    private String lozinka;
    private String kontakt;
    private Privilegija privilegija;

    public Menadzer() {
    }

    public Menadzer(String jmbg, String imePrezime, String email, String lozinka, String kontakt, Privilegija privilegija) {
        this.jmbg = jmbg;
        this.imePrezime = imePrezime;
        this.email = email;
        this.lozinka = lozinka;
        this.kontakt = kontakt;
        this.privilegija = privilegija;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getKontakt() {
        return kontakt;
    }

    public void setKontakt(String kontakt) {
        this.kontakt = kontakt;
    }

    public Privilegija getPrivilegija() {
        return privilegija;
    }

    public void setPrivilegija(Privilegija privilegija) {
        this.privilegija = privilegija;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.jmbg);
        hash = 43 * hash + Objects.hashCode(this.email);
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
        final Menadzer other = (Menadzer) obj;
        if (!Objects.equals(this.jmbg, other.jmbg)) {
            return false;
        }
        return Objects.equals(this.email, other.email);
    }

    @Override
    public String toString() {
        return imePrezime;
    }
    
    
    
}
