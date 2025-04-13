package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model_enum.JedinicaMere;
import model_enum.Klasa;
import model_enum.Tip;
import model_enum.Vrsta;

/**
 *
 * @author Korisnik
 */
public class Proizvod implements OpstiDomenskiObjekat {

    private int id;
    private Tip tip;
    private Vrsta vrsta;
    private Klasa klasa;
    private double cena;
    private String opis;
    private JedinicaMere jedinicaMere;
    private int pdv;
    private java.util.Date datumIzmene;

    public Proizvod() {
    }

    public Proizvod(int id, Tip tip, Vrsta vrsta, Klasa klasa, double cena, String opis, JedinicaMere jedinicaMere, int pdv, Date datumIzmene) {
        this.id = id;
        this.tip = tip;
        this.vrsta = vrsta;
        this.klasa = klasa;
        this.cena = cena;
        this.opis = opis;
        this.jedinicaMere = jedinicaMere;
        this.pdv = pdv;
        this.datumIzmene = datumIzmene;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }

    public Vrsta getVrsta() {
        return vrsta;
    }

    public void setVrsta(Vrsta vrsta) {
        this.vrsta = vrsta;
    }

    public Klasa getKlasa() {
        return klasa;
    }

    public void setKlasa(Klasa klasa) {
        this.klasa = klasa;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public JedinicaMere getJedinicaMere() {
        return jedinicaMere;
    }

    public void setJedinicaMere(JedinicaMere jedinicaMere) {
        this.jedinicaMere = jedinicaMere;
    }

    public int getPdv() {
        return pdv;
    }

    public void setPdv(int pdv) {
        this.pdv = pdv;
    }

    public Date getDatumIzmene() {
        return datumIzmene;
    }

    public void setDatumIzmene(Date datumIzmene) {
        this.datumIzmene = datumIzmene;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + this.id;
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
        final Proizvod other = (Proizvod) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return tip.toString() + " " + vrsta.toString() + " " + klasa.toString();
    }

    @Override
    public boolean napuni(ResultSet rs) {
        try {
            id = rs.getInt("p.id");
            tip = Tip.valueOf(rs.getString("p.tip"));
            vrsta = Vrsta.valueOf(rs.getString("p.vrsta"));
            klasa = Klasa.valueOf(rs.getString("p.klasa"));
            cena = rs.getDouble("p.cena");
            opis = rs.getString("p.opis");
            jedinicaMere = JedinicaMere.valueOf(rs.getString("p.mernaJedinica"));
            pdv = rs.getInt("p.pdv");
            java.sql.Date datumSql = rs.getDate("p.datumIzmene");
            datumIzmene = new Date(datumSql.getTime());
        } catch (SQLException ex) {
            Logger.getLogger(Proizvod.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public String vratiKljuc() {
        return id + "";
    }

    @Override
    public String vratiImeKlaseUcitaj() {
        return "proizvod p";
    }

    @Override
    public String vratiImeKlaseUpisi() {
        return "proizvod";
    }

    @Override
    public String vratiVrednostAtributa() {
        java.sql.Date datumSql = new java.sql.Date(datumIzmene.getTime());
        return "('" + tip + "','" + vrsta + "','" + klasa + "'," + cena + ",'" + opis + "','" + jedinicaMere + "'," + pdv + ",'" + datumSql + "')";
    }

    @Override
    public String postaviVrednostAtributa() {
        java.sql.Date datumSql = new java.sql.Date(datumIzmene.getTime());
        return "tip='" + tip + "',vrsta='" + vrsta + "',klasa='" + klasa + "',cena=" + cena + ",opis='" + opis + "',mernaJedinica='" + jedinicaMere + "',pdv=" + pdv + ",datumIzmene='" + datumSql + "'";
    }

    @Override
    public String vratiListuAtributa() {
        return "(tip,vrsta,klasa,cena,opis,mernaJedinica,pdv,datumIzmene)";
    }

    @Override
    public String vratiUslovNadjiSlog() {
        return "id=" + this.getId();
    }

    @Override
    public String vratiUslovNadjiSlogove() {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        boolean prev = false;
        if (this.tip != null) {
            sb.append("tip LIKE LOWER ('" + this.tip.toString().toLowerCase() + "') ");
            prev = true;
        }
        if (this.vrsta != null) {
            if (prev == true) {
                sb.append(" AND ");
            }
            sb.append("vrsta LIKE LOWER ('" + this.vrsta.toString().toLowerCase() + "') ");
            prev = true;
        }
        if (this.klasa != null) {
            if (prev == true) {
                sb.append(" AND ");
            }
            sb.append("klasa LIKE LOWER ('" + this.klasa.toString().toLowerCase() + "') ");
            prev = true;
        }
        if (cenaOd > -1) {
            if (prev == true) {
                sb.append(" AND ");
            }
            sb.append("cena > " + this.cenaOd);
            prev = true;
        }
        if (cenaDo > -1) {
            if (prev == true) {
                sb.append(" AND ");
            }
            sb.append("cena < " + this.cenaDo);
            prev = true;
        }

        return sb.toString();
    }

    @Override
    public boolean postojiRelacija() {
        return true;
    }

    private double cenaOd = -1;
    private double cenaDo = -1;

    public void filterCena(double cenaOd, double cenaDo) {
        this.cenaOd = cenaOd;
        this.cenaDo = cenaDo;
    }

    @Override
    public String vratiUslovObrisiSlog() {
        return "proizvod=" + this.getId();
    }

}
