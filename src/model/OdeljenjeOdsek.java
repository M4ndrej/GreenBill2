
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
public class OdeljenjeOdsek implements OpstiDomenskiObjekat {

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
        return this.getGazdinskaJedinica().getNaziv() + " " + naziv;
    }

    @Override
    public boolean napuni(ResultSet rs) {
        try {
            id = rs.getInt("oo.id");
            naziv = rs.getString("oo.naziv");
            doznaka = rs.getDouble("oo.doznaka");
            java.sql.Date sqlDatumDoznake = rs.getDate("oo.datumDoznake");
            datumDoznake = new Date(sqlDatumDoznake.getTime());
        } catch (SQLException ex) {
            Logger.getLogger(OdeljenjeOdsek.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;

    }

    @Override
    public String vratiKljuc() {
        return id + " " + gazdinskaJedinica.getSifra() ;
    }

    @Override
    public String vratiImeKlaseUcitaj() {
        return "odeljenje_odsek oo";
    }

    @Override
    public String vratiImeKlaseUpisi() {
        return "odeljenje_odsek";
    }

    @Override
    public String vratiVrednostAtributa() {
        java.sql.Date sqlDatum = new java.sql.Date(datumDoznake.getTime());
        return "('" + naziv + "'," + doznaka + ",'" + sqlDatum + "'," + gazdinskaJedinica.getSifra() + "," + gazdinskaJedinica.getLokalitet().getId() + ")";
    }

    @Override
    public String postaviVrednostAtributa() {
        java.sql.Date sqlDatum = new java.sql.Date(datumDoznake.getTime());
        return "naziv='" + naziv + "',doznaka=" + doznaka + ",datumDoznake='" + sqlDatum + "',gazdinskaJedinica=" + gazdinskaJedinica.getSifra() + ",lokalitet=" + gazdinskaJedinica.getLokalitet().getId();
    }

    @Override
    public String vratiListuAtributa() {
        return "(naziv,doznaka,datumDoznake,gazdinskaJedinica,lokalitet)";
    }

    @Override
    public String vratiUslovNadjiSlog() {
        return "id="+this.getId();
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
        return "odeljenjeOdsek="+this.getId();
    }

}
