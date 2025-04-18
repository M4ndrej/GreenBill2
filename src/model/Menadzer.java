
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import model_enum.Privilegija;

/**
 *
 * @author Korisnik
 */
public class Menadzer implements OpstiDomenskiObjekat {

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

    @Override
    public boolean napuni(ResultSet rs) {
        try {
            jmbg = rs.getString("m.jmbg");
            imePrezime = rs.getString("m.imePrezime");
            email = rs.getString("m.email");
            lozinka = rs.getString("m.lozinka");
            kontakt = rs.getString("m.kontakt");
            privilegija = Privilegija.valueOf(rs.getString("m.privilegija"));
        } catch (SQLException ex) {
            Logger.getLogger(Menadzer.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public String vratiKljuc() {
        return "'" + jmbg + "'";
    }

    @Override
    public String vratiImeKlaseUcitaj() {
        return "menadzer m";
    }

    @Override
    public String vratiImeKlaseUpisi() {
        return "menadzer";
    }

    @Override
    public String vratiVrednostAtributa() {
        return "('"+jmbg+"','"+imePrezime+"','"+email+"','"+lozinka+"','"+kontakt+"','"+privilegija+"')";
    }

    @Override
    public String postaviVrednostAtributa() {
        return "jmbg='"+jmbg+"',imePrezime='"+imePrezime+"',email='"+email+"',lozinka='"+lozinka+"',kontakt='"+kontakt+"',privilegija='"+privilegija+"'";
    }

    @Override
    public String vratiListuAtributa() {
        return "(jmbg,imePrezime,email,lozinka,kontakt,privilegija)";
    }

    @Override
    public String vratiUslovNadjiSlog() {
        return "jmbg='"+this.jmbg+"'";
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
        return "menadzer="+this.getJmbg();
    }

}
