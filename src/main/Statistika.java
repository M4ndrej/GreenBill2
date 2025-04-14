package main;

//import database.DBBroker;
//import database.Konekcija;
import database.Konekcija;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.StavkaOtpremnice;

/**
 *
 * @author Andrej
 */
public class Statistika {

    //private Konekcija konekcija = Konekcija.getInstance();
    private Statement statement;

    public boolean analize(StavkaOtpremnice so, String prosekUkupnoUdeo, String cenaKolicina, String radio, List<Object[]> lista) {

        try {
            statement = Konekcija.getInstance().getConnection().createStatement();
            StringBuilder select = new StringBuilder();
            StringBuilder where = new StringBuilder();
            StringBuilder groupBy = new StringBuilder();
            StringBuilder from = new StringBuilder();

            select.append("SELECT ");
            where.append(" WHERE ");
            groupBy.append(" GROUP BY ");
            from.append(" FROM stavka_otpremnice so JOIN otpremnica o ON o.broj = so.otpremnica JOIN otpremac otp ON o.otpremac = otp.jmbg JOIN lokalitet l ON l.id = so.lokalitet JOIN gazdinska_jedinica gj ON gj.sifra=so.gazdinskaJedinica JOIN odeljenje_odsek oo ON oo.id=so.odeljenjeOdsek JOIN proizvod p ON so.proizvod=p.id ");

            boolean prev = false;
            //datumi

            if (so.getOtpremnica().getDatum() != null) {
                select.append(" MONTH(o.datum) ");
                groupBy.append(" MONTH(o.datum)");
                if (prev) {
                    where.append(" AND ");
                }
                where.append(" YEAR(o.datum) = " + (so.getOtpremnica().getDatum().getYear() + 1900));
                prev = true;

            } else {
                select.append(" YEAR(o.datum) ");
                groupBy.append(" YEAR(o.datum)");
            }

            if (so.getOdeljenjeOdsek() != null && so.getOdeljenjeOdsek().getNaziv() == null) {  // Ako je OdeljenjeOdsek nije null, počinjemo od njega
                if (so.getOdeljenjeOdsek().getGazdinskaJedinica() != null && so.getOdeljenjeOdsek().getGazdinskaJedinica().getNaziv() == null) {  // Ako je GazdinskaJedinica nije null
                    if (so.getOdeljenjeOdsek().getGazdinskaJedinica().getLokalitet() != null && so.getOdeljenjeOdsek().getGazdinskaJedinica().getLokalitet().getNaziv() != null) {
                        if (prev) {
                            where.append(" AND ");
                        }
                        prev = true;
                        // Dodajemo uslov za Lokalitet
                        where.append(" so.lokalitet = " + so.getOdeljenjeOdsek().getGazdinskaJedinica().getLokalitet().getId());
                    }
                } else if (so.getOdeljenjeOdsek().getGazdinskaJedinica() != null && so.getOdeljenjeOdsek().getGazdinskaJedinica().getNaziv() != null) {  // Ako je samo GazdinskaJedinica definisana, a Lokalitet nije
                    if (prev) {
                        where.append(" AND ");
                    }
                    prev = true;
                    // Dodajemo uslov za GazdinskuJedinicu
                    where.append(" so.gazdinskaJedinica = " + so.getOdeljenjeOdsek().getGazdinskaJedinica().getSifra());
                }
            } else if (so.getOdeljenjeOdsek() != null && so.getOdeljenjeOdsek().getNaziv() != null) {  // Ako je samo OdeljenjeOdsek definisano
                if (prev) {
                    where.append(" AND ");
                }
                prev = true;
                // Dodajemo uslov za OdeljenjeOdsek
                where.append(" so.odeljenjeOdsek = " + so.getOdeljenjeOdsek().getId());
            }

            //agregacija
            if ("PROSEK".equals(prosekUkupnoUdeo)) {
                if ("CENA".equals(cenaKolicina)) {
                    select.append(", SUM(iznos) / SUM(ukupnaKolicina)");
                } else {
                    select.append(", SUM(ukupnaKolicina)/(SELECT COUNT(*) FROM otpremnica o2 JOIN stavka_otpremnice so2 ON so2.otpremnica = o2.broj WHERE YEAR(o2.datum) = YEAR(o.datum)");
                    if (so.getOdeljenjeOdsek() != null && so.getOdeljenjeOdsek().getNaziv() == null) {  // Ako je OdeljenjeOdsek nije null, počinjemo od njega
                        if (so.getOdeljenjeOdsek().getGazdinskaJedinica() != null && so.getOdeljenjeOdsek().getGazdinskaJedinica().getNaziv() == null) {  // Ako je GazdinskaJedinica nije null
                            if (so.getOdeljenjeOdsek().getGazdinskaJedinica().getLokalitet() != null && so.getOdeljenjeOdsek().getGazdinskaJedinica().getLokalitet().getNaziv() != null) {
                                select.append(" AND o2.lokalitet = o.lokalitet ");
                            }
                        } else if (so.getOdeljenjeOdsek().getGazdinskaJedinica() != null && so.getOdeljenjeOdsek().getGazdinskaJedinica().getNaziv() != null) {  // Ako je samo GazdinskaJedinica definisana, a Lokalitet nije
                            select.append(" AND o2.gazdinskaJedinica = o.gazdinskaJedinica");
                        }
                    } else if (so.getOdeljenjeOdsek() != null && so.getOdeljenjeOdsek().getNaziv() != null) {  // Ako je samo OdeljenjeOdsek definisano
                        select.append(" AND so.odeljenjeOdsek = so2.odeljenjeOdsek ");
                    }

                    if (so.getOtpremnica().getDatum() != null) {
                        select.append(" AND MONTH(o.datum) = MONTH(o2.datum) ");
                    }
                    select.append(")");
                }
            } else if ("UKUPNO".equals(prosekUkupnoUdeo)) {
                if ("CENA".equals(cenaKolicina)) {
                    select.append(", SUM(iznos) ");
                } else {
                    select.append(", SUM(ukupnaKolicina) ");
                }
            } else {
                StringBuilder podSelect = new StringBuilder("");
                StringBuilder podWhere = new StringBuilder("");

                if ("CENA".equals(cenaKolicina)) {
                    select.append(",(SUM(so.iznos) ");
                    podSelect.append("/ (SELECT SUM(so2.iznos) FROM stavka_otpremnice so2 JOIN otpremnica o2 ON so2.otpremnica = o2.broj JOIN proizvod p2 ON so2.proizvod=p2.id");
                    podWhere.append(" WHERE YEAR(o2.datum)=YEAR(o.datum)");
                    if (so.getProizvod() != null && so.getProizvod().getKlasa() != null
                            && so.getProizvod().getVrsta() != null && so.getProizvod().getTip() != null) {
                        podWhere.append(" AND tip='" + so.getProizvod().getTip() + "' AND vrsta='" + so.getProizvod().getVrsta() + "'");
                    } else if (so.getProizvod() != null && so.getProizvod().getTip() != null
                            && so.getProizvod().getVrsta() != null) {
                        podWhere.append(" AND vrsta='" + so.getProizvod().getVrsta() + "'");
                    } else if (so.getProizvod() != null && so.getProizvod().getKlasa() != null
                            && so.getProizvod().getTip() != null) {
                        podWhere.append(" AND tip='" + so.getProizvod().getTip() + "'");
                    } else if (so.getProizvod() != null && so.getProizvod().getKlasa() != null
                            && so.getProizvod().getVrsta() != null) {
                        podWhere.append(" AND vrsta='" + so.getProizvod().getVrsta() + "'");
                    }
                    if (so.getOdeljenjeOdsek() != null && so.getOdeljenjeOdsek().getNaziv() == null) {
                        System.out.println("usao");
                        if (so.getOdeljenjeOdsek().getGazdinskaJedinica() != null && so.getOdeljenjeOdsek().getGazdinskaJedinica().getNaziv() == null) {
                            System.out.println("usao 2");
                            if (so.getOdeljenjeOdsek().getGazdinskaJedinica().getLokalitet() != null && so.getOdeljenjeOdsek().getGazdinskaJedinica().getLokalitet().getNaziv() != null) {
                                System.out.println("usao3");
                                if (radio == "LOKALITET") {
                                    podWhere.append(" AND so2.lokalitet=" + so.getOdeljenjeOdsek().getGazdinskaJedinica().getLokalitet().getId());
                                } else if (radio == "GJ" || radio == "OO") {
                                    return false;
                                }
                            }
                        } else if (so.getOdeljenjeOdsek().getGazdinskaJedinica() != null && so.getOdeljenjeOdsek().getGazdinskaJedinica().getNaziv() != null) {
                            System.out.println("gj");

                            if (radio == "LOKALITET") {
                                podWhere.append(" AND so2.lokalitet=" + so.getOdeljenjeOdsek().getGazdinskaJedinica().getLokalitet().getId());
                            } else if (radio == "GJ") {
                                podWhere.append(" AND so2.gazdinskaJedinica=" + so.getOdeljenjeOdsek().getGazdinskaJedinica().getSifra());
                            } else if (radio == "OO") {
                                return false;
                            }
                        }

                    } else if (so.getOdeljenjeOdsek() != null && so.getOdeljenjeOdsek().getNaziv() != null) {
                        System.out.println("oo");

                        if (radio == "LOKALITET") {
                            podWhere.append(" AND so2.lokalitet=" + so.getOdeljenjeOdsek().getGazdinskaJedinica().getLokalitet().getId());
                        } else if (radio == "GJ") {
                            podWhere.append(" AND so2.gazdinskaJedinica=" + so.getOdeljenjeOdsek().getGazdinskaJedinica().getSifra());
                        } else if (radio == "OO") {
                            podWhere.append(" AND so2.odeljenjeOdsek=" + so.getOdeljenjeOdsek().getId());
                        }
                    }
                    podWhere.append(")*100)");
                    select.append(podSelect.append(podWhere));
                } else {
                    select.append(",(SUM(so.ukupnaKolicina) ");
                    podSelect.append("/ (SELECT SUM(so2.ukupnaKolicina) FROM stavka_otpremnice so2 JOIN otpremnica o2 ON so2.otpremnica = o2.broj JOIN proizvod p2 ON so2.proizvod=p2.id");
                    podWhere.append(" WHERE YEAR(o2.datum)=YEAR(o.datum)");
                    if (so.getProizvod() != null && so.getProizvod().getKlasa() != null
                            && so.getProizvod().getVrsta() != null && so.getProizvod().getTip() != null) {
                        podWhere.append(" AND tip='" + so.getProizvod().getTip() + "' AND vrsta='" + so.getProizvod().getVrsta() + "'");
                    } else if (so.getProizvod() != null && so.getProizvod().getTip() != null
                            && so.getProizvod().getVrsta() != null) {
                        podWhere.append(" AND vrsta='" + so.getProizvod().getVrsta() + "'");
                    } else if (so.getProizvod() != null && so.getProizvod().getKlasa() != null
                            && so.getProizvod().getTip() != null) {
                        podWhere.append(" AND tip='" + so.getProizvod().getTip() + "'");
                    } else if (so.getProizvod() != null && so.getProizvod().getKlasa() != null
                            && so.getProizvod().getVrsta() != null) {
                        podWhere.append(" AND vrsta='" + so.getProizvod().getVrsta() + "'");
                    }
                    if (so.getOdeljenjeOdsek() != null && so.getOdeljenjeOdsek().getNaziv() == null) {
                        if (so.getOdeljenjeOdsek().getGazdinskaJedinica() != null && so.getOdeljenjeOdsek().getGazdinskaJedinica().getNaziv() == null) {
                            if (so.getOdeljenjeOdsek().getGazdinskaJedinica().getLokalitet() != null && so.getOdeljenjeOdsek().getGazdinskaJedinica().getLokalitet().getNaziv() != null) {
                                if (radio == "LOKALITET") {
                                    podWhere.append(" AND so2.lokalitet=" + so.getOdeljenjeOdsek().getGazdinskaJedinica().getLokalitet().getId());
                                } else if (radio == "GJ" || radio == "OO") {
                                    return false;
                                }
                            } 
                        }else if (so.getOdeljenjeOdsek().getGazdinskaJedinica() != null && so.getOdeljenjeOdsek().getGazdinskaJedinica().getNaziv() != null) {
                                if (radio == "LOKALITET") {
                                    podWhere.append(" AND so2.lokalitet=" + so.getOdeljenjeOdsek().getGazdinskaJedinica().getLokalitet().getId());
                                } else if (radio == "GJ") {
                                    podWhere.append(" AND so2.gazdinskaJedinica=" + so.getOdeljenjeOdsek().getGazdinskaJedinica().getSifra());
                                } else if (radio == "OO") {
                                    return false;
                                }
                            } 

                    }else if (so.getOdeljenjeOdsek() != null && so.getOdeljenjeOdsek().getNaziv() != null) {
                            if (radio == "LOKALITET") {
                                podWhere.append(" AND so2.lokalitet=" + so.getOdeljenjeOdsek().getGazdinskaJedinica().getLokalitet().getId());
                            } else if (radio == "GJ") {
                                podWhere.append(" AND so2.gazdinskaJedinica=" + so.getOdeljenjeOdsek().getGazdinskaJedinica().getSifra());
                            } else if (radio == "OO") {
                                podWhere.append(" AND so2.odeljenjeOdsek=" + so.getOdeljenjeOdsek().getId());
                            }
                        }

                    podWhere.append(")*100)");
                    select.append(podSelect.append(podWhere));
                }
            }
            //
            if (so.getProizvod().getTip() != null) {
                if (prev) {
                    where.append(" AND ");
                }
                prev = true;
                where.append(" tip LIKE '" + so.getProizvod().getTip().toString() + "'");
            }
            if (so.getProizvod().getVrsta() != null) {
                if (prev) {
                    where.append(" AND ");
                }
                prev = true;
                where.append(" vrsta LIKE '" + so.getProizvod().getVrsta().toString() + "'");
            }
            if (so.getProizvod().getKlasa() != null) {
                if (prev) {
                    where.append(" AND ");
                }
                prev = true;
                where.append(" klasa LIKE '" + so.getProizvod().getKlasa().toString() + "'");
            }
            if (so.getProizvod().getTip() == null
                    && so.getProizvod().getVrsta() == null
                    && so.getProizvod().getKlasa() == null
                    && so.getOdeljenjeOdsek() == null
                    && so.getOtpremnica().getDatum() == null) {
                where = new StringBuilder();
            }
            String upit = select.toString() + from.toString() + (where.length() > 8 ? where.toString() : "") + groupBy.toString();
            System.out.println("Analiza:  " + upit);
            ResultSet rs = statement.executeQuery(upit);
            if (!rs.isBeforeFirst()) {
                return false;
            }
            while (rs.next()) {

                if (rs.getMetaData().getColumnCount() == 3) {
                    int vreme = rs.getInt(1);
                    String mesto = rs.getString(2);
                    double vrednost = rs.getDouble(3);
                    lista.add(new Object[]{vreme, mesto, vrednost});

                } else {
                    int vreme = rs.getInt(1);
                    double vrednost = rs.getDouble(2);
                    lista.add(new Object[]{vreme, vrednost});

                }
            }
        } catch (SQLException ex) {
            //Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

}
