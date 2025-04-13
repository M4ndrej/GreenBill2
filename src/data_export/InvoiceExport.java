
package data_export;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
 
import java.io.*;
import java.util.*;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import model.Otpremnica;
import model.Racun;
import model.StavkaOtpremnice;
 
import org.apache.xmlbeans.XmlCursor;
 
public class InvoiceExport {
 
    public static void generisiRacun(
            List<StavkaOtpremnice> stavke,
            Otpremnica otpremnica,
            Racun racun,
            String templatePath,
            String outputPath
    ) throws Exception {
 
        FileInputStream fis = new FileInputStream(templatePath);
        XWPFDocument doc = new XWPFDocument(fis);
 
        // Zameni tekstualne placeholder-e
        for (XWPFParagraph p : doc.getParagraphs()) {
            zameniPlaceholdereUParagrafu(p,stavke, otpremnica, racun);
        }
 
        // Ubaci tabelu na mesto {stab}
        insertTable(doc, stavke);
 
        // Zameni tekst u tabelama
        for (XWPFTable tbl : doc.getTables()) {
            for (XWPFTableRow row : tbl.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    for (XWPFParagraph p : cell.getParagraphs()) {
                        zameniPlaceholdereUParagrafu(p,stavke, otpremnica, racun);
                    }
                }
            }
        }
 
        FileOutputStream out = new FileOutputStream(outputPath);
        doc.write(out);
        out.close();
        doc.close();
        fis.close();
    }
 
    private static void zameniPlaceholdereUParagrafu(XWPFParagraph p, List<StavkaOtpremnice> stavke, Otpremnica o, Racun r) {
        for (XWPFRun run : p.getRuns()) {
            String text = run.getText(0);
            if (text == null) continue;
 
            text = text.replace("kupacNaziv", o.getKupac().getNaziv());
            text = text.replace("{ka}", o.getKupac().getAdresa());
            text = text.replace("kum", o.getKupac().getMesto());
            text = text.replace("{kpib}", o.getKupac().getPib());
            text = text.replace("{kmb}", o.getKupac().getMaticniBroj());
            text = text.replace("{rbr}", r.getBroj());
            text = text.replace("{pnb}", r.getPozivNaBroj());
            text = text.replace("{kom}", r.getKomercijalista());
            text = text.replace("{nap}", r.getNapomena());
            text = text.replace("{np}", r.getNacinPlacanja());
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            text = text.replace("dat", format.format(r.getDatum()));
            text = text.replace("{propdv}", stavke.get(0).getPdv()+"");
            text = text.replace("{osn}", String.format("%.2f", r.getOsnovica()));
            text = text.replace("pdv", String.format("%.2f", r.getPdv()));
            text = text.replace("{uku}", String.format("%.2f",(r.getOsnovica()+r.getPdv())));
 
            run.setText(text, 0);
        }
    }
 
    private static void insertTable(XWPFDocument document, List<StavkaOtpremnice> stavke) {
        for (XWPFParagraph paragraph : document.getParagraphs()) {
            String text = paragraph.getText();
            if (text != null && text.contains("{stab}")) {
                XmlCursor cursor = paragraph.getCTP().newCursor();
 
                for (XWPFRun run : paragraph.getRuns()) {
                    run.setText("", 0);
                }
 
                XWPFTable table = document.insertNewTbl(cursor);
                configureTable(table, stavke);
                break;
            }
        }
    }
 
    private static void configureTable(XWPFTable table, List<StavkaOtpremnice> stavke) {
        CTTblWidth tblWidth = CTTblWidth.Factory.newInstance();
        tblWidth.setType(STTblWidth.DXA);
        tblWidth.setW(BigInteger.valueOf(10000));
        table.getCTTbl().getTblPr().setTblW(tblWidth);
 
        CTTblGrid grid = table.getCTTbl().addNewTblGrid();
        int[] columnWidths = {400, 4000, 1500, 600, 600, 600, 800, 600, 600, 800, 800};
        for (int width : columnWidths) {
            CTTblGridCol gridCol = grid.addNewGridCol();
            gridCol.setW(BigInteger.valueOf(width));
        }
 
        XWPFTableRow headerRow = table.getRow(0);
        String[] headers = {"RB", "ID", "Naziv", "Jed. mere", "Količina", "Cena", "Iznos", "Rabat", "PDV", "Iznos PDV", "Ukupno"};
 
        while (headerRow.getTableCells().size() < headers.length) {
            headerRow.addNewTableCell();
        }
 
        for (int i = 0; i < headers.length; i++) {
            setHeaderCell(headerRow.getCell(i), headers[i]);
        }
 
        for (StavkaOtpremnice stavka : stavke) {
            XWPFTableRow row = table.createRow();
            addRowData(row, stavka);
        }
    }
 
    private static void setHeaderCell(XWPFTableCell cell, String text) {
        if (cell == null) return;
 
        for (int i = cell.getParagraphs().size() - 1; i >= 0; i--) {
            cell.removeParagraph(i);
        }
 
        XWPFParagraph paragraph = cell.addParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(text);
        run.setFontSize(8);
        run.setFontFamily("Arial");
        run.setBold(true);
 
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
    }
 
    private static void addRowData(XWPFTableRow row, StavkaOtpremnice s) {
        setCellValue(row, 0, String.valueOf(s.getRedniBroj()));
        setCellValue(row, 1, String.valueOf(s.getProizvod().getId()));
        setCellValue(row, 2, s.getProizvod().toString());
        setCellValue(row, 3, s.getProizvod().getJedinicaMere().toString());
        setCellValue(row, 4, String.format("%.2f",s.getUkupnaKolicina()));
        setCellValue(row, 5, String.format("%.2f",s.getProizvod().getCena()));
        setCellValue(row, 6, String.format("%.2f",s.getIznos()));
        setCellValue(row, 7, String.valueOf(s.getRabat()));
        setCellValue(row, 8, String.valueOf(s.getPdv()));
        setCellValue(row, 9, String.format("%.2f",s.getIznosPdv()));
        setCellValue(row, 10, String.format("%.2f",s.getUkupnoSaPdv()));
    }
 
    private static void setCellValue(XWPFTableRow row, int cellIndex, String value) {
        XWPFTableCell cell = row.getCell(cellIndex);
        if (cell == null) cell = row.addNewTableCell();
 
        for (int i = cell.getParagraphs().size() - 1; i >= 0; i--) {
            cell.removeParagraph(i);
        }
 
        XWPFParagraph paragraph = cell.addParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(value);
        run.setFontSize(5);
        run.setFontFamily("Arial");
    }
}