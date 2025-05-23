package database;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.OpstiDomenskiObjekat;
import java.sql.ResultSet;
import model.GazdinskaJedinica;
import model.Kupac;
import model.Lokalitet;
import model.Menadzer;
import model.OdeljenjeOdsek;
import model.Otpremac;
import model.Otpremnica;
import model.Proizvod;
import model.Racun;
import model.StavkaOtpremnice;
import java.sql.PreparedStatement;
import main.LoggerConfig;

/**
 *
 * @author Andrej
 */
public class DBBroker {

    private final Konekcija konekcija = Konekcija.getInstance();
    private Statement statement;
    static final Logger logger = LoggerConfig.getLogger();

    public List<OpstiDomenskiObjekat> read(OpstiDomenskiObjekat odo) {
        try {
            List<OpstiDomenskiObjekat> lista = new ArrayList<>();
            statement = konekcija.getConnection().createStatement();
            String upit = "SELECT * FROM " + odo.vratiImeKlaseUcitaj();
            System.out.println(upit);
            ResultSet rs = statement.executeQuery(upit);
            while (rs.next()) {
                OpstiDomenskiObjekat noviObjekat;
                try {
                    noviObjekat = odo.getClass().getDeclaredConstructor().newInstance();
                    if (noviObjekat.napuni(rs)) {
                        lista.add(noviObjekat);
                    }
                } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException ex) {
//                    Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
                    logger.log(Level.SEVERE, "Greška->kreiranje instance klase koja implementira interface, read metoda", ex);
                }
            }
            return lista;
        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            logger.log(Level.SEVERE, "Greška->read metoda", ex);
            return null;
        }
    }

    public boolean readOtpremacWithLokalitet(List<Otpremac> lista) {
        try {
            String upit = "SELECT * FROM otpremac o JOIN lokalitet l ON o.lokalitet = l.id";
            statement = konekcija.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(upit);
            while (rs.next()) {
                Lokalitet lokalitet = new Lokalitet();
                lokalitet.napuni(rs);
                Otpremac otpremac = new Otpremac();
                otpremac.napuni(rs);
                lista.add(otpremac);
            }
        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            logger.log(Level.SEVERE, "Greška->readOtpremacWithLokalitet metoda", ex);
            return false;
        }
        return true;

    }

    public boolean readOtpremnicaWithMenadzerOtpremacKupac(List<Otpremnica> lista) {
        try {
            String upit = "SELECT * FROM otpremnica otp_doc JOIN menadzer m ON otp_doc.menadzer = m.jmbg "
                    + "JOIN otpremac otp ON otp_doc.otpremac = otp.jmbg "
                    + "JOIN kupac k ON (otp_doc.kupacPib = k.pib AND otp_doc.kupacMaticniBroj = k.MaticniBroj) "
                    + "JOIN gazdinska_jedinica gj ON (otp_doc.gazdinskaJedinica = gj.sifra AND otp_doc.lokalitet = gj.lokalitet) "
                    + "JOIN lokalitet l ON l.id = gj.lokalitet";
            System.out.println(upit);
            statement = konekcija.getConnection().createStatement();

            ResultSet rs = statement.executeQuery(upit);

            while (rs.next()) {
                Menadzer menadzer = new Menadzer();
                menadzer.napuni(rs);
                Otpremac otpremac = new Otpremac();
                otpremac.napuni(rs);
                Kupac kupac = new Kupac();
                kupac.napuni(rs);
                GazdinskaJedinica gj = new GazdinskaJedinica();
                gj.napuni(rs);
                Lokalitet l = new Lokalitet();
                l.napuni(rs);
                gj.setLokalitet(l);
                Otpremnica otpremnica = new Otpremnica();
                otpremnica.napuni(rs);
                otpremnica.setMenadzer(menadzer);
                otpremnica.setOtpremac(otpremac);
                otpremnica.setKupac(kupac);
                otpremnica.setGazdinskaJedinica(gj);
                lista.add(otpremnica);
            }
        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            logger.log(Level.SEVERE, "Greška->readOtpremnicaWithMenadzerOtpremacKupac metoda", ex);
            return false;
        }
        return true;
    }

    public boolean readStavkeOtpremniceForOtpremnica(List<StavkaOtpremnice> lista, Otpremnica otpremnica) {
        try {
            String upit = "SELECT * FROM  otpremnica o JOIN stavka_otpremnice s ON s.otpremnica = o.broj "
                    + "JOIN proizvod p ON s.proizvod = p.id "
                    + "JOIN odeljenje_odsek oo ON s.odeljenjeOdsek=oo.id "
                    + "JOIN gazdinska_jedinica gj ON oo.gazdinskaJedinica=gj.sifra "
                    + "WHERE o.broj = " + otpremnica.getBroj() + " ORDER BY s.redniBroj";
            statement = konekcija.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(upit);
            while (rs.next()) {
                Proizvod proizvod = new Proizvod();
                proizvod.napuni(rs);
                OdeljenjeOdsek oo = new OdeljenjeOdsek();
                oo.napuni(rs);
                GazdinskaJedinica gj = new GazdinskaJedinica();
                gj.napuni(rs);
                StavkaOtpremnice stavka = new StavkaOtpremnice();
                stavka.napuni(rs);
                stavka.setProizvod(proizvod);
                stavka.setOtpremnica(otpremnica);
                oo.setGazdinskaJedinica(gj);
                stavka.setOdeljenjeOdsek(oo);
                lista.add(stavka);
            }
        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            logger.log(Level.SEVERE, "Greška->readStavkeOtpremniceForOtpremnica metoda", ex);
            return false;
        }
        return true;
    }

    public boolean create(OpstiDomenskiObjekat odo) {
        int affectedRows;
        try {
            String upit = "INSERT INTO " + odo.vratiImeKlaseUpisi() + " " + odo.vratiListuAtributa() + " VALUES " + odo.vratiVrednostAtributa();
            System.out.println(upit);
            statement = konekcija.getConnection().createStatement();
            affectedRows = statement.executeUpdate(upit);
            konekcija.getConnection().commit();
        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            logger.log(Level.SEVERE, "Greška->create metoda", ex);
            return false;
        }
        return (affectedRows > 0);
    }

    public boolean update(OpstiDomenskiObjekat odo) {
        int affectedRows;
        try {

            String upit = "UPDATE " + odo.vratiImeKlaseUpisi() + " SET " + odo.postaviVrednostAtributa() + " WHERE " + odo.vratiUslovNadjiSlog();
            statement = konekcija.getConnection().createStatement();
            affectedRows = statement.executeUpdate(upit);
            konekcija.getConnection().commit();
        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            logger.log(Level.SEVERE, "Greška->update metoda", ex);
            return false;
        }
        return (affectedRows > 0);
    }

    public boolean delete(OpstiDomenskiObjekat odo) {
        int affectedRows;
        try {
            String upit = "DELETE FROM " + odo.vratiImeKlaseUpisi() + " WHERE " + odo.vratiUslovNadjiSlog();
            System.out.println(upit);
            statement = konekcija.getConnection().createStatement();
            affectedRows = statement.executeUpdate(upit);
            konekcija.getConnection().commit();
        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            logger.log(Level.SEVERE, "Greška->delete metoda", ex);
            return false;
        }
        return (affectedRows > 0);
    }

    public List<OpstiDomenskiObjekat> readWithCondition(OpstiDomenskiObjekat odo) {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        try {
            statement = konekcija.getConnection().createStatement();
            String upit = "SELECT * FROM " + odo.vratiImeKlaseUcitaj() + " WHERE " + odo.vratiUslovNadjiSlogove();
            System.out.println(upit);
            ResultSet rs = statement.executeQuery(upit);
            while (rs.next()) {
                OpstiDomenskiObjekat noviObjekat;
                try {
                    noviObjekat = odo.getClass().getDeclaredConstructor().newInstance();
                    if (noviObjekat.napuni(rs)) {
                        lista.add(noviObjekat);
                    }
                } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException ex) {
//                    Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
                    logger.log(Level.SEVERE, "Greška->kreiranje instance klase koja implementira interface, readWithCondition metoda", ex);
                }
            }
        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            logger.log(Level.SEVERE, "Greška->readWithCondition metoda", ex);
            return null;
        }
        return lista;
    }

    public boolean readWithConditionOtpremnicaKupacOtpremac(Otpremnica otp, List<Otpremnica> lista) {
        try {
            statement = konekcija.getConnection().createStatement();
            String upit = "SELECT * FROM otpremnica otp_doc JOIN menadzer m ON otp_doc.menadzer = m.jmbg "
                    + "JOIN otpremac otp ON otp_doc.otpremac = otp.jmbg "
                    + "JOIN kupac k ON (otp_doc.kupacPib = k.pib AND otp_doc.kupacMaticniBroj = k.MaticniBroj) "
                    + "JOIN gazdinska_jedinica gj ON (otp_doc.gazdinskaJedinica = gj.sifra AND otp_doc.lokalitet = gj.lokalitet) "
                    + "JOIN lokalitet l ON l.id = gj.lokalitet"
                    + " WHERE " + otp.vratiUslovNadjiSlogove();
            ResultSet rs = statement.executeQuery(upit);
            System.out.println(upit);
            while (rs.next()) {
                Menadzer menadzer = new Menadzer();
                menadzer.napuni(rs);
                Otpremac otpremac = new Otpremac();
                otpremac.napuni(rs);
                Kupac kupac = new Kupac();
                kupac.napuni(rs);
                GazdinskaJedinica gj = new GazdinskaJedinica();
                gj.napuni(rs);
                Lokalitet l = new Lokalitet();
                l.napuni(rs);
                gj.setLokalitet(l);
                Otpremnica otpremnica = new Otpremnica();
                otpremnica.napuni(rs);
                otpremnica.setMenadzer(menadzer);
                otpremnica.setOtpremac(otpremac);
                otpremnica.setKupac(kupac);
                otpremnica.setGazdinskaJedinica(gj);
                lista.add(otpremnica);
            }
        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            logger.log(Level.SEVERE, "Greška->readWithConditionOtpremnicaKupacOtpremac metoda", ex);
            return false;
        }
        return true;
    }

    public boolean readWithConditionDoznakaOtprema(List<Object[]> podaci) {
        try {
            statement = konekcija.getConnection().createStatement();
            String upit = "SELECT oo.naziv, gj.sifra,  oo.doznaka , SUM(ukupnaKolicina) AS otprema FROM stavka_otpremnice so"
                    + " JOIN odeljenje_odsek oo ON so.odeljenjeOdsek=oo.id AND so.gazdinskaJedinica=oo.gazdinskaJedinica AND so.lokalitet=oo.lokalitet"
                    + " JOIN gazdinska_jedinica gj ON gj.sifra=oo.gazdinskaJedinica"
                    + " GROUP BY oo.id ";

            System.out.println(upit);
            ResultSet rs = statement.executeQuery(upit);
            while (rs.next()) {

                String odsek = rs.getString("oo.naziv");
                int gj = rs.getInt("gj.sifra");
                double doznaka = rs.getDouble("oo.doznaka");
                double otprema = rs.getDouble("otprema");
                podaci.add(new Object[]{odsek, gj, doznaka, otprema});
            }
        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            logger.log(Level.SEVERE, "Greška->readWithConditionDoznakaOtprema metoda", ex);
            return false;
        }
        return true;
    }

    public List<OpstiDomenskiObjekat> readGazdinskaJedinicaWithLokalitet() {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        try {
            String upit = "SELECT * FROM gazdinska_jedinica gj JOIN lokalitet l ON gj.lokalitet = l.id";
            statement = konekcija.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(upit);
            while (rs.next()) {
                Lokalitet lokalitet = new Lokalitet();
                lokalitet.napuni(rs);
                GazdinskaJedinica gj = new GazdinskaJedinica();
                gj.setLokalitet(lokalitet);
                gj.napuni(rs);
                lista.add(gj);
            }

        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            logger.log(Level.SEVERE, "Greška->readGazdinskaJedinicaWithLokalitet metoda", ex);
            return null;
        }
        return lista;
    }

    public List<OpstiDomenskiObjekat> readOdeljenjeOdsekWithGazdinskaJedinicaLokalitet() {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        try {
            String upit = "SELECT * FROM odeljenje_odsek oo JOIN gazdinska_jedinica gj  ON oo.gazdinskaJedinica = gj.sifra JOIN lokalitet l ON gj.lokalitet = l.id";
            statement = konekcija.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(upit);
            while (rs.next()) {
                Lokalitet lokalitet = new Lokalitet();
                lokalitet.napuni(rs);
                GazdinskaJedinica gj = new GazdinskaJedinica();
                gj.setLokalitet(lokalitet);
                gj.napuni(rs);
                OdeljenjeOdsek oo = new OdeljenjeOdsek();
                oo.setGazdinskaJedinica(gj);
                oo.napuni(rs);
                lista.add(oo);
            }

        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            logger.log(Level.SEVERE, "Greška->readOdeljenjeOdsekWithGazdinskaJedinica metoda", ex);
            return null;
        }
        return lista;
    }

    public List<OpstiDomenskiObjekat> readRacunWithOtpremnica() {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();

        try {
            String upit = "SELECT * FROM racun r JOIN otpremnica otp_doc ON r.otpremnica = otp_doc.broj";
            statement = konekcija.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(upit);
            while (rs.next()) {
                Otpremnica otpremnica = new Otpremnica();
                otpremnica.napuni(rs);
                Racun racun = new Racun();
                racun.napuni(rs);
                racun.setOtpremnica(otpremnica);
                lista.add(racun);
            }
        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            logger.log(Level.SEVERE, "Greška->readRacunWithOtpremnica metoda", ex);
            return null;
        }
        return lista;
    }

    public Otpremnica readOtpremnicaForRacun(Racun racun) {
        Otpremnica otpremnica = new Otpremnica();
        try {
            // Pravimo upit sa parametrizovanim upitom
            String upit = "SELECT * FROM otpremnica otp_doc JOIN kupac k ON otp_doc.kupacPib = k.pib AND otp_doc.kupacMaticniBroj = k.maticniBroj  WHERE otp_doc.broj = ?";

            // Kreiramo PreparedStatement, koji omogućava postavljanje parametara
            PreparedStatement preparedStatement = konekcija.getConnection().prepareStatement(upit);

            // Postavljamo vrednost parametra (pretpostavljamo da je broj otpremnice celobrojni tip)
            preparedStatement.setString(1, racun.getOtpremnica().getBroj());  // Postavljamo prvi parametar u upitu

            // Izvršavamo upit
            ResultSet rs = preparedStatement.executeQuery();

            // Iteriramo kroz rezultate i punimo objekat otpremnice
            while (rs.next()) {
                Kupac k = new Kupac();
                k.napuni(rs);
                otpremnica.setKupac(k);
                otpremnica.napuni(rs);
            }
        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            logger.log(Level.SEVERE, "Greška->readOtpremnicaForRacun metoda", ex);
            return null;
        }
        return otpremnica;
    }

    public List<Racun> readWithConditionRacunOtpremnica(Racun racun, List<Racun> lista) {
        try {
            statement = konekcija.getConnection().createStatement();
            String upit = "SELECT * FROM racun r JOIN otpremnica otp_doc ON r.otpremnica = otp_doc.broj WHERE " + racun.vratiUslovNadjiSlogove();
            ResultSet rs = statement.executeQuery(upit);
            while (rs.next()) {
                Racun r = new Racun();
                r.napuni(rs);
                Otpremnica o = new Otpremnica();
                o.napuni(rs);
                r.setOtpremnica(o);
                lista.add(r);
                System.out.println(r.getBroj());
            }
        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            logger.log(Level.SEVERE, "Greška->readWithConditionRacunOtpremnica metoda", ex);
            return null;
        }
        return lista;
    }

    public void readOdeljenjeOdsekForGazdinskaJedinica(GazdinskaJedinica gj, List<OdeljenjeOdsek> listaOO) {
        try {
            statement = konekcija.getConnection().createStatement();
            String upit = "SELECT * FROM odeljenje_odsek oo WHERE "
                    + "oo.gazdinskaJedinica=" + gj.getSifra();
            System.out.println(upit);
            ResultSet rs = statement.executeQuery(upit);
            while (rs.next()) {
                OdeljenjeOdsek o = new OdeljenjeOdsek();
                o.napuni(rs);
                o.setGazdinskaJedinica(gj);
                listaOO.add(o);
            }
        } catch (SQLException ex) {
//            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            logger.log(Level.SEVERE, "Greška->readOdeljenjeOdsekForGazdinskaJedinica metoda", ex);
        }
    }

    public boolean existsInDb(OpstiDomenskiObjekat odo) {

        try (Statement statement = Konekcija.getInstance().getConnection().createStatement()) {
            String upit = "SELECT * FROM " + odo.vratiImeKlaseUcitaj() + " WHERE " + odo.vratiUslovNadjiSlog();
            try (ResultSet rs = statement.executeQuery(upit)) {
                return rs.next();
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Greška->existsInDb metoda", ex);
            return false;
        }
    }

    public boolean existRelation(OpstiDomenskiObjekat subjekat, OpstiDomenskiObjekat objekat) {
        try (Statement statement = Konekcija.getInstance().getConnection().createStatement()) {
            String upit = "SELECT * FROM " + objekat.vratiImeKlaseUcitaj() + " WHERE " + subjekat.vratiUslovObrisiSlog();
            try (ResultSet rs = statement.executeQuery(upit)) {
                return rs.next();
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Greška->existsRelation metoda", ex);
            return false;
        }
    }

}
