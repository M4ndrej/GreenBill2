
package model;
import java.sql.*;
/**
 *
 * @author Korisnik
 */
public interface OpstiDomenskiObjekat {
    
    public boolean napuni(ResultSet rs);
    public String vratiKljuc();
    public String vratiImeKlaseUcitaj();
    public String vratiImeKlaseUpisi();
    public String vratiVrednostAtributa();
    public String postaviVrednostAtributa();
    public String vratiListuAtributa();
    public String vratiUslovNadjiSlog();
    public String vratiUslovNadjiSlogove();
    public boolean postojiRelacija();
    public String vratiUslovObrisiSlog();
    
}
