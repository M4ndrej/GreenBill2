package email;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import model.Menadzer;
import model.OdeljenjeOdsek;

/**
 *
 * @author Andrej
 */
public class Email {

    private Session novaSesija = null;
    private MimeMessage poruka = null;


    private Email() {
    }

    private void postaviPropertie() {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        novaSesija = Session.getDefaultInstance(properties, null);
    }

    private MimeMessage draft1(double staraDoznaka, double novaDoznaka, Menadzer menadzer, OdeljenjeOdsek oo) throws AddressException, MessagingException {
        String primalac = "andrejmax347@gmail.com";

        String subject = "Promena doznake";

        String teloPoruke = "Poštovani, obaveštavamo Vas da je na odseku i odeljenju " + oo.getNaziv() + " u gazdinskoj jedinici " + oo.getGazdinskaJedinica().getNaziv() + ",\n"
                + ", izvršena izmena doznačene količine.\n"
                + "Stara vrednost: " + staraDoznaka + "\n"
                + "Nova vrednost: " + novaDoznaka + "\n"
                + "Izmenu izvršio: " + menadzer.getImePrezime() + "\n"
                + "Email izvršioca: " + menadzer.getEmail() + ".";

        poruka = new MimeMessage(novaSesija);

        poruka.addRecipient(Message.RecipientType.TO, new InternetAddress(primalac));

        poruka.setSubject(subject);

        MimeMultipart okvir = new MimeMultipart();

        MimeBodyPart telo = new MimeBodyPart();
        telo.setContent(teloPoruke, "text/plain; charset=UTF-8");

        okvir.addBodyPart(telo);

        poruka.setContent(okvir);

        return poruka;

    }

    private void posalji() throws NoSuchProviderException, MessagingException {
        String from = "sluzbaupravljanjeprojektima@gmail.com";
        String sifra = "seru qtiw cgux ppch";
        String host = "smtp.gmail.com";
        Transport transport = novaSesija.getTransport("smtp");
        transport.connect(host, from, sifra);
        transport.sendMessage(poruka, poruka.getAllRecipients());
        transport.close();
        System.out.println("Uspesno poslato!");
    }

    public static void promeniDoznaku(double staraDoznaka, double novaDoznaka, Menadzer menadzer, OdeljenjeOdsek oo) throws MessagingException {
        Email por = new Email();
        por.postaviPropertie();
        por.draft1(staraDoznaka, novaDoznaka, menadzer, oo);
        por.posalji();
    }

}
