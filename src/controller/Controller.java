package controller;

import data_export.DataExport;
import database.DBBroker;
import database.Konekcija;
import java.util.ArrayList;
import java.util.List;
import model.Kupac;
import model.Lokalitet;
import model.Menadzer;
import model.OpstiDomenskiObjekat;
import model.Otpremac;
import model.Otpremnica;
import model.Proizvod;
import model.StavkaOtpremnice;
import main.Statistika;
import model.GazdinskaJedinica;
import model.OdeljenjeOdsek;
import model.Racun;

/**
 *
 * @author Andrej
 */
public class Controller {

    private static Controller instance;
    private List<Menadzer> menadzeri = new ArrayList<>();
    private Menadzer ulogovani;
    private final DBBroker dbb = new DBBroker();

    private Controller() {

    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public Menadzer getUlovovani() {
        return ulogovani;
    }

    public void setUlogovani(Menadzer ulogovani) {
        this.ulogovani = ulogovani;
    }

    //kupac
    public boolean kreirajKupac(Kupac kupac) {
        return dbb.create(kupac);
    }

    public boolean izmeniKupca(Kupac kupac) {
        return dbb.update(kupac);
    }

    public boolean vratiListuKupac(List<Kupac> lista) {
        List<OpstiDomenskiObjekat> listaOdo = dbb.read(new Kupac());
        if (listaOdo == null) {
            return false;
        }
        for (OpstiDomenskiObjekat o : listaOdo) {
            lista.add((Kupac) o);
        }
        return true;
    }

    public boolean vratiListuSviKupac(Kupac kupac, List<Kupac> lista) {
        List<OpstiDomenskiObjekat> listaOdo = dbb.readWithCondition(kupac);
        if (listaOdo == null) {
            return false;
        }
        for (OpstiDomenskiObjekat o : listaOdo) {
            lista.add((Kupac) o);
        }
        return true;
    }

    public boolean obrisiKupca(Kupac kupac) {
        boolean uspesno = dbb.existRelation(kupac, new Otpremnica());
        if (uspesno) {
            return false;
        }
        return dbb.delete(kupac);
    }

    //lokalitet
    public boolean kreirajLokalitet(Lokalitet lokalitet) {
        return dbb.create(lokalitet);
    }

    public boolean izmeniLokalitet(Lokalitet lokalitet) {
        return dbb.update(lokalitet);
    }

    public boolean obrisiLokalitet(Lokalitet lokalitet) {
        boolean uspesno = dbb.existRelation(lokalitet, new Otpremac());
        if (uspesno) {
            return false;
        }
        return dbb.delete(lokalitet);
    }

    public boolean vratiListuLokalitet(List<Lokalitet> lista) {
        List<OpstiDomenskiObjekat> listaOdo = dbb.read(new Lokalitet());
        if (listaOdo == null) {
            return false;
        }
        for (OpstiDomenskiObjekat o : listaOdo) {
            lista.add((Lokalitet) o);
        }
        return true;
    }

    public boolean vratiListuSviLokalitet(Lokalitet lokalitet, List<Lokalitet> lista) {
        List<OpstiDomenskiObjekat> listaOdo = dbb.readWithCondition(lokalitet);
        if (listaOdo == null) {
            return false;
        }
        for (OpstiDomenskiObjekat o : listaOdo) {
            lista.add((Lokalitet) o);
        }
        return true;
    }

    //zaposleni
    public boolean prijaviMenadzer(String jmbg, String lozinka) {
        List<OpstiDomenskiObjekat> listaOdo = dbb.read(new Menadzer());
        if (listaOdo == null) {
            menadzeri = null;
            return false;
        }
        for (OpstiDomenskiObjekat o : listaOdo) {
            menadzeri.add((Menadzer) o);
        }

        for (Menadzer m : menadzeri) {
            if (m.getJmbg().equals(jmbg) && m.getLozinka().equals(lozinka)) {
                ulogovani = m;
                return true;
            }
        }
        return false;
    }

    public boolean kreirajMenadzer(Menadzer menadzer) {
        return dbb.create(menadzer);
    }

    public boolean izmeniMenadzera(Menadzer menadzer) {
        return dbb.update(menadzer);
    }

    public boolean vratiListuSviMenadzer(Menadzer menadzer, List<Menadzer> lista) {
        List<OpstiDomenskiObjekat> listaOdo = dbb.readWithCondition(menadzer);
        if (listaOdo == null) {
            return false;
        }
        for (OpstiDomenskiObjekat o : listaOdo) {
            lista.add((Menadzer) o);
        }
        return true;
    }

    public boolean vratiListuZaposlenih(List<Menadzer> lista) {
        List<OpstiDomenskiObjekat> listaOdo = dbb.read(new Menadzer());
        if (listaOdo == null) {
            return false;
        }
        for (OpstiDomenskiObjekat o : listaOdo) {
            lista.add((Menadzer) o);
        }
        return true;
    }

    //otpremac
    public boolean izmeniOtpremaca(Otpremac otpremac) {
        return dbb.update(otpremac);
    }

    public boolean obrisiOtpremaca(Otpremac otpremac) {
        boolean uspesno = dbb.existRelation(otpremac, new Otpremnica());
        if (uspesno) {
            return false;
        }
        return dbb.delete(otpremac);
    }

    public boolean vratiListuOtpremaca(List<Otpremac> lista) {
        List<OpstiDomenskiObjekat> listaOdo = dbb.read(new Otpremac());
        if (listaOdo == null) {
            return false;
        }
        for (OpstiDomenskiObjekat o : listaOdo) {
            lista.add((Otpremac) o);
        }
        return true;
    }

    public boolean kreirajOtpremac(Otpremac otpremac) {
        return dbb.create(otpremac);
    }

    //proizvod
    public boolean kreirajProizvod(Proizvod proizvod) {
        return dbb.create(proizvod);
    }

    public boolean izmeniProizvod(Proizvod proizvod) {
        return dbb.update(proizvod);
    }

    public boolean orbisiProizvod(Proizvod proizvod) {
        boolean uspesno = dbb.existRelation(proizvod, new StavkaOtpremnice());
        if (uspesno) {
            return false;
        }
        return dbb.delete(proizvod);
    }

    public boolean vratiListuProizvoda(List<Proizvod> lista) {
        List<OpstiDomenskiObjekat> listaOdo = dbb.read(new Proizvod());
        if (listaOdo == null) {
            return false;
        }
        for (OpstiDomenskiObjekat o : listaOdo) {
            lista.add((Proizvod) o);
        }
        return true;
    }

    public boolean vratiListuSviProizvod(Proizvod proizvod, List<Proizvod> lista) {
        List<OpstiDomenskiObjekat> listaOdo = dbb.readWithCondition(proizvod);
        if (listaOdo == null) {
            return false;
        }
        for (OpstiDomenskiObjekat o : listaOdo) {
            lista.add((Proizvod) o);
        }
        return true;
    }

    //stavka otpremnice
    public boolean kreirajStavkuOtpremnice(StavkaOtpremnice stavkaOtpremnice) {
        return dbb.create(stavkaOtpremnice);
    }

    public boolean obrisiStavkuOtpremnice(StavkaOtpremnice so) {
        return dbb.delete(so);
    }

    public boolean vratiListuStavkiOtpremnica(List<StavkaOtpremnice> lista, Otpremnica otpremnica) {
        return dbb.readStavkeOtpremniceForOtpremnica(lista, otpremnica);
    }

    public boolean vratiListuOtpremnica(List<Otpremnica> lista) {
        return dbb.readOtpremnicaWithMenadzerOtpremacKupac(lista);
    }

    //otpremnica
    public boolean kreirajOtpremnicu(Otpremnica otpremnica, List<StavkaOtpremnice> listaStavki) {
        DataExport de = new DataExport();

        if (dbb.create(otpremnica)) {
            for (StavkaOtpremnice stavkaOtpremnice : listaStavki) {
                stavkaOtpremnice.setOtpremnica(otpremnica);
                boolean uspesno = Controller.getInstance().kreirajStavkuOtpremnice(stavkaOtpremnice);
                if (!uspesno) {
                    return false;
                }
                if (otpremnica.isVerifikovana()) {
                    de.dodajPodatkeUExcel(stavkaOtpremnice);
                }
            }
        }
        return true;
    }

    public boolean azurirajOtpremnicu(Otpremnica otpremnica) {
        DataExport de = new DataExport();

        if (otpremnica.isVerifikovana()) {
            upisiExcel(de, otpremnica);
        }
        return dbb.update(otpremnica);
    }

    public boolean vratiListuSviOtpremnica(Otpremnica otpremnica, List<Otpremnica> lista) {
        return dbb.readWithConditionOtpremnicaKupacOtpremac(otpremnica, lista);
    }

    //dodatno
    public boolean analiziraj(StavkaOtpremnice so, String prosekUkupno, String cenaKolicina, String radio, List<Object[]> lista) {
        Statistika statistika = new Statistika();
        return statistika.analize(so, prosekUkupno, cenaKolicina, radio, lista);
    }

    public boolean vratiListuDoznakaOtprema(List<Object[]> podaci) {
        return dbb.readWithConditionDoznakaOtprema(podaci);
    }

    private void upisiExcel(DataExport de, Otpremnica otpremnica) {
        List<StavkaOtpremnice> stavke = new ArrayList<>();
        vratiListuStavkiOtpremnica(stavke, otpremnica);
        for (StavkaOtpremnice s : stavke) {
            de.dodajPodatkeUExcel(s);
        }
    }

    //Gazdinska jedinica
    public boolean kreirajGazdinskaJedinica(GazdinskaJedinica gj) {
        return dbb.create(gj);
    }

    public boolean izmeniGazdinskaJedinica(GazdinskaJedinica gj) {
        return dbb.update(gj);
    }

    public boolean obrisiGazdinskuJedinicu(GazdinskaJedinica gj) {
        return dbb.delete(gj);
    }

    public boolean vratiListuGazdinskaJedinica(List<GazdinskaJedinica> lista) {
        List<OpstiDomenskiObjekat> listaOdo = dbb.readGazdinskaJedinicaWithLokalitet();
        if (listaOdo == null) {
            return false;
        }
        for (OpstiDomenskiObjekat o : listaOdo) {
            lista.add((GazdinskaJedinica) o);
        }
        return true;
    }

    //Odeljenje odsek
    public boolean izmeniOdeljenjeOdsek(OdeljenjeOdsek oo) {
        return dbb.update(oo);
    }

    public boolean kreirajOdeljenjeOdsek(OdeljenjeOdsek oo) {
        return dbb.create(oo);
    }

    public boolean obrisiOdeljenjeOdsek(OdeljenjeOdsek oo) {
        return dbb.delete(oo);
    }

    public boolean vratiListuOdeljenjeOdsek(List<OdeljenjeOdsek> lista) {
        List<OpstiDomenskiObjekat> listaOdo = dbb.readOdeljenjeOdsekWithGazdinskaJedinicaLokalitet();
        if (listaOdo == null) {
            return false;
        }
        for (OpstiDomenskiObjekat o : listaOdo) {
            lista.add((OdeljenjeOdsek) o);
        }
        return true;
    }

    //Racun
    public boolean kreirajRacun(Racun racun) {
        return dbb.create(racun);
    }

    public boolean vratiListuRacun(List<Racun> lista) {
        List<OpstiDomenskiObjekat> listaOdo = dbb.readRacunWithOtpremnica();
        if (listaOdo == null) {
            return false;
        }
        for (OpstiDomenskiObjekat o : listaOdo) {
            lista.add((Racun) o);
        }
        return true;
    }

    public Otpremnica vratiOtpremnicaForRacun(Racun racun) {
        return dbb.readOtpremnicaForRacun(racun);
    }

    public boolean vratiListuSviRacun(Racun racun, List<Racun> racuni) {
        dbb.readWithConditionRacunOtpremnica(racun, racuni);
        return racuni != null;
    }

    public void vratiListuOdeljenjeOdsekForGazdinskaJedinica(GazdinskaJedinica gj, List<OdeljenjeOdsek> listaOO) {
        dbb.readOdeljenjeOdsekForGazdinskaJedinica(gj, listaOO);
    }

    public boolean proveriKonekciju() {
    if (!Konekcija.getInstance().isConnected()) {
        Konekcija.getInstance().reconnect(); 
    }
    return Konekcija.getInstance().isConnected();
}

}
