/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_export;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import konfiguracija.Konfiguracija;
import model.StavkaOtpremnice;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Andrej
 */
public class DataExport {

    public void dodajPodatkeUExcel(StavkaOtpremnice so) {
        String excelFilePath = Konfiguracija.getInstance().getPropertie("excel.file.path");

        try (FileInputStream fis = new FileInputStream(excelFilePath); Workbook workbook = new XSSFWorkbook(fis)) {

            System.out.println("poceo");
            Sheet sheet = workbook.getSheetAt(0); // Prva radna sveska
            int lastRow = sheet.getLastRowNum();
            if (lastRow == -1) {
                lastRow = 0; // Ako nema redova, kreiraj prvi red
            } else {
                lastRow += 1; // Inače, dodaj novi red na kraju
            }
            Row newRow = sheet.createRow(lastRow);

            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            String datum = format.format(so.getOtpremnica().getDatum());

            newRow.createCell(0).setCellValue(so.getOtpremnica().getBroj());
            newRow.createCell(1).setCellValue(datum);
            newRow.createCell(2).setCellValue(so.getOtpremnica().getKupac().getNaziv());
            newRow.createCell(3).setCellValue(so.getOtpremnica().getMenadzer().getImePrezime());
            newRow.createCell(4).setCellValue(so.getOtpremnica().getOtpremac().getImePrezime());

            newRow.createCell(5).setCellValue(so.getProizvod().getTip() + "");
            newRow.createCell(6).setCellValue(so.getProizvod().getVrsta() + "");
            newRow.createCell(7).setCellValue(so.getProizvod().getKlasa() + "");
            newRow.createCell(8).setCellValue(so.getProizvod().getJedinicaMere() + "");
            newRow.createCell(9).setCellValue(so.getProizvod().getPdv() + "");
            newRow.createCell(10).setCellValue(so.getProizvod().getCena() + "");
            double cenaSaPdv = so.getProizvod().getCena() + so.getProizvod().getCena() * so.getProizvod().getPdv() / 100;
            newRow.createCell(11).setCellValue(String.format("%.2f",cenaSaPdv));
            newRow.createCell(12).setCellValue(so.getUkupnaKolicina() + "");
            newRow.createCell(13).setCellValue(String.format("%.2f", so.getUkupnoSaPdv()));
            
            try (FileOutputStream fos = new FileOutputStream(excelFilePath)) {
                workbook.write(fos);
            }

            System.out.println("Podaci uspešno dodati u Excel!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
