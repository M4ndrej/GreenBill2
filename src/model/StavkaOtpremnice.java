
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
public class StavkaOtpremnice implements OpstiDomenskiObjekat{
    
    private int redniBroj;
    private Otpremnica otpremnica;
    private double ukupnaKolicina;
    private double iznos;
    private double rabat;
    private int pdv;
    private double iznosPdv;
    private double ukupnoSaPdv;
    private Proizvod proizvod;
    private OdeljenjeOdsek odeljenjeOdsek;

    public StavkaOtpremnice() {
    }

    public StavkaOtpremnice(int redniBroj, Otpremnica otpremnica, double ukupnaKolicina, double iznos, double rabat, int pdv, double iznosPdv, double ukupnoSaPdv, Proizvod proizvod, OdeljenjeOdsek odeljenjeOdsek) {
        this.redniBroj = redniBroj;
        this.otpremnica = otpremnica;
        this.ukupnaKolicina = ukupnaKolicina;
        this.iznos = iznos;
        this.rabat = rabat;
        this.pdv = pdv;
        this.iznosPdv = iznosPdv;
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

    public double getIznosPdv() {
        return iznosPdv;
    }

    public void setIznosPdv(double izbosPdv) {
        this.iznosPdv = izbosPdv;
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

    @Override
    public boolean napuni(ResultSet rs) {
        try {
            redniBroj = rs.getInt("s.redniBroj");
            ukupnaKolicina = rs.getDouble("s.ukupnaKolicina");
            iznos = rs.getDouble("s.iznos");
            rabat = rs.getDouble("s.rabat");
            pdv = rs.getInt("s.pdv");
            iznosPdv = rs.getDouble("s.iznosPdv");
            ukupnoSaPdv = rs.getDouble("s.ukupnoSaPdv");
        } catch (SQLException ex) {
            Logger.getLogger(StavkaOtpremnice.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public String vratiKljuc() {
        return redniBroj+","+otpremnica.getBroj();
    }

    @Override
    public String vratiImeKlaseUcitaj() {
        return "stavka_otpremnica s";
    }

    @Override
    public String vratiImeKlaseUpisi() {
        return "stavka_otpremnice";
    }

    @Override
    public String vratiVrednostAtributa() {
        return "("+redniBroj+",'"+otpremnica.getBroj()+"',"+ukupnaKolicina+","+iznos+","+rabat+","+pdv+","+iznosPdv+","+ukupnoSaPdv+","+proizvod.getId()+","+odeljenjeOdsek.getId()+","+odeljenjeOdsek.getGazdinskaJedinica().getSifra()+","+odeljenjeOdsek.getGazdinskaJedinica().getLokalitet().getId()+")";
    }

    @Override
    public String postaviVrednostAtributa() {
        return "redniBroj="+redniBroj+",otpremnica='"+otpremnica.getBroj()+"',ukupnaKolicina="+ukupnaKolicina+",iznos="+iznos+",rabat="+rabat+",pdv="+pdv+",iznosPdv="+iznosPdv+",ukupnoSaPdv="+ukupnoSaPdv+",proizvod="+proizvod.getId()+",odeljenjeOdsek="+odeljenjeOdsek.getId()+",gazdinskaJedinica="+odeljenjeOdsek.getGazdinskaJedinica().getSifra()+",lokalitet="+odeljenjeOdsek.getGazdinskaJedinica().getLokalitet().getId();
    }

    @Override
    public String vratiListuAtributa() {
        return "(redniBroj,otpremnica,ukupnaKolicina,iznos,rabat,pdv,iznosPdv,ukupnoSaPdv, proizvod,odeljenjeOdsek,gazdinskaJedinica,lokalitet)";
    }

    @Override
    public String vratiUslovNadjiSlog() {
        return "redniBroj=" + this.getRedniBroj() + " AND otpremnica='" + this.getOtpremnica().getBroj()+"'";
    }

    @Override
    public String vratiUslovNadjiSlogove() {
        return null;
    }

    @Override
    public boolean postojiRelacija() {
        return true;
    }

    @Override
    public String vratiUslovObrisiSlog() {
        return null;
    }
    
        
}
