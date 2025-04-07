package data_export;

import java.io.File;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import model.Otpremnica;
import model.Racun;
import model.StavkaOtpremnice;

public class InvoiceExport {

    /**
     * Metoda koja menja placeholder-e u Word dokumentu.
     *
     * @param documentPath putanja do originalnog Word dokumenta
     * @param updatedDocumentPath putanja do novog Word dokumenta sa izmenama
     * @param placeholderValues mapa koja sadrži vrednosti za zamenu
     * placeholder-a
     * @throws IOException ako dođe do greške u radu sa fajlovima
     */
    public static void replacePlaceholdersInDocument(String documentPath, String updatedDocumentPath, List<StavkaOtpremnice> stavke, Otpremnica otp, Racun rac) throws IOException {

        File updatedDocumentFile = new File(updatedDocumentPath);

        // Ako fajl ne postoji, kreiraj novi Word dokument
        if (!updatedDocumentFile.exists()) {
            // Kreiraj novi dokument
            try (XWPFDocument newDocument = new XWPFDocument()) {
                // Dodaj paragraf u novi dokument (ako je potrebno)
                XWPFParagraph paragraph = newDocument.createParagraph();
                XWPFRun run = paragraph.createRun();
                run.setText("Dokument je prazan. Ovaj tekst je samo primer.");

                // Sačuvaj novi dokument
                try (FileOutputStream fos = new FileOutputStream(updatedDocumentPath)) {
                    newDocument.write(fos);
                }
            }
        }

        try (FileInputStream fis = new FileInputStream(documentPath); XWPFDocument document = new XWPFDocument(fis)) {

            for (XWPFParagraph paragraph : document.getParagraphs()) {
                for (XWPFRun run : paragraph.getRuns()) {
                    String text = run.getText(0);
                    if (text != null) {
                        if (text.contains("{rbr}")) {
                            text = text.replace("{rbr}", rac.getBroj());
                        }
                        if (text.contains("{pnb}")) {
                            text = text.replace("{pnb}", rac.getPozivNaBroj());
                        }
                        if (text.contains("{kun}")) {
                            text = text.replace("{kun}", otp.getKupac().getNaziv());
                        }
                        if (text.contains("{kum}")) {
                            text = text.replace("{kum}", otp.getKupac().getMesto());
                        }
                        if (text.contains("{ka}")) {
                            text = text.replace("{ka}", otp.getKupac().getAdresa());
                        }
                        if (text.contains("{kpib}")) {
                            text = text.replace("{kpib}", otp.getKupac().getPib());
                        }
                        if (text.contains("{kmb}")) {
                            text = text.replace("{kmb}", otp.getKupac().getMaticniBroj());
                        }
                        if (text.contains("{kom}")) {
                            text = text.replace("{kom}", rac.getKomercijalista());
                        }
                        if (text.contains("{nap}")) {
                            text = text.replace("{nap}", rac.getNapomena());
                        }
                        if (text.contains("{np}")) {
                            text = text.replace("{np}", rac.getNacinPlacanja());
                        }
                        if (text.contains("{dat}")) {
                            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                            text = text.replace("{dat}", format.format(rac.getDatum()));
                        }
                        if (text.contains("{osn}")) {
                            text = text.replace("{osn}", rac.getOsnovica() + "");
                        }
                        if (text.contains("{pdv}")) {
                            text = text.replace("{pdv}", rac.getPdv() + "");
                        }
                        if (text.contains("{uku}")) {
                            text = text.replace("{uku}", (rac.getPdv() + rac.getOsnovica()) + "");
                        }
                        if (text.contains("{propdv}")) {
                            text = text.replace("{propdv}", stavke.get(0).getPdv() + "");
                        }

                        run.setText(text, 0);
                    }
                }
            }

            try (FileOutputStream fos = new FileOutputStream(updatedDocumentPath)) {
                document.write(fos);
            }
        }
    }

}
