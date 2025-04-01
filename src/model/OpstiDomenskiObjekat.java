/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model;
import java.sql.*;
/**
 *
 * @author Korisnik
 */
public interface OpstiDomenskiObjekat {
    
    public boolean napuni(ResultSet rs);
    public String vratiKljuc();
    public String vratiImeKlase();
    public String vratiVrednostAtributa();
    public String postaviVrednostAtributa();
    public String vratiListuAtributa();
    public String vratiUslovNadjiSlog();
    public String vratiUslovNadjiSlogove();
    public boolean postojiRelacija();
    
}
