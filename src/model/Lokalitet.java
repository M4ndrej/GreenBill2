
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Korisnik
 */
public class Lokalitet implements OpstiDomenskiObjekat{
    private int id;
    private String naziv;

    public Lokalitet() {
    }

    public Lokalitet(int id, String naziv) {
        this.id = id;
        this.naziv = naziv;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id;
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
        final Lokalitet other = (Lokalitet) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public boolean napuni(ResultSet rs) {
        try {
            id= rs.getInt("l.id");
            naziv = rs.getString("l.naziv");
        } catch (SQLException ex) {
            Logger.getLogger(Lokalitet.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public String vratiKljuc() {
        return id+"";
    }

    @Override
    public String vratiImeKlaseUcitaj() {
        return "lokalitet l";
    }
    
    @Override
    public String vratiImeKlaseUpisi() {
        return "lokalitet";
    }

    @Override
    public String vratiVrednostAtributa() {
        return "('"+naziv+"')";
    }

    @Override
    public String postaviVrednostAtributa() {
        return "naziv='"+naziv+"'";
    }

    @Override
    public String vratiListuAtributa() {
        return "(naziv)";
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
        return "lokalitet="+this.getId();
    }
    
}
