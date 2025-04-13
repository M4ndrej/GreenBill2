
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
public class Otpremac implements OpstiDomenskiObjekat{
    
    private String jmbg;
    private String imePrezime;

    public Otpremac() {
    }

    public Otpremac(String jmbg, String imePrezime) {
        this.jmbg = jmbg;
        this.imePrezime = imePrezime;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.jmbg);
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
        final Otpremac other = (Otpremac) obj;
        return Objects.equals(this.jmbg, other.jmbg);
    }

    @Override
    public String toString() {
        return imePrezime;
    }

    @Override
    public boolean napuni(ResultSet rs) {
        try {
            jmbg = rs.getString("otp.jmbg");
            imePrezime = rs.getString("otp.imePrezime");
        } catch (SQLException ex) {
            Logger.getLogger(Otpremac.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public String vratiKljuc() {
        return jmbg;
    }

    @Override
    public String vratiImeKlaseUcitaj() {
        return "otpremac otp";
    }

    @Override
    public String vratiImeKlaseUpisi() {
        return "otpremac";
    }

    @Override
    public String vratiVrednostAtributa() {
        return "('"+jmbg+"','"+imePrezime+"')";
    }

    @Override
    public String postaviVrednostAtributa() {
        return "jmbg='"+jmbg+"',imePrezime='"+imePrezime+"'";
    }

    @Override
    public String vratiListuAtributa() {
        return "(jmbg,imePrezime)";
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
        return "otpremac="+this.getJmbg();
    }
}
