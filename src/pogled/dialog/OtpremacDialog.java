/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package pogled.dialog;

import controller.Controller;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Lokalitet;
import model.Otpremac;
import pogled.GlavnaForma;

/**
 *
 * @author Andrej
 */
public class OtpremacDialog extends javax.swing.JDialog {

    GlavnaForma parent = new GlavnaForma();
    Otpremac otpremac;

    /**
     * Creates new form OtpremacDialog
     */
    public OtpremacDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setTitle("Kreiraj otpremača");
        inicijalizuj();
        setLocationRelativeTo(parent);
        setResizable(false);
        this.parent = (GlavnaForma) parent;
    }

    public OtpremacDialog(java.awt.Frame parent, boolean modal, Otpremac otpremac) {
        super(parent, modal);
        initComponents();
        setTitle("Izmeni otpremača");
        inicijalizuj(otpremac);
        setLocationRelativeTo(parent);
        this.parent = (GlavnaForma) parent;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldJMBG = new javax.swing.JTextField();
        jTextFieldImePrezime = new javax.swing.JTextField();
        jLabelImePrezimeError = new javax.swing.JLabel();
        jLabelJmbgError = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButtonSacuvajIzmene = new javax.swing.JButton();
        jButtonIzmeni = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jButtonObrisi = new javax.swing.JButton();
        jButtonKreiraj = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("JMBG");

        jLabel3.setText("Ime i prezime");

        jLabelImePrezimeError.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabelImePrezimeError.setForeground(new java.awt.Color(255, 0, 51));
        jLabelImePrezimeError.setText("jLabel4");

        jLabelJmbgError.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabelJmbgError.setForeground(new java.awt.Color(255, 0, 51));
        jLabelJmbgError.setText("jLabel4");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(jTextFieldJMBG))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelJmbgError, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelImePrezimeError, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 47, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jTextFieldImePrezime)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldJMBG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(jLabelJmbgError)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldImePrezime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelImePrezimeError)
                .addContainerGap(85, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonSacuvajIzmene.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonSacuvajIzmene.setText("Sačuvaj izmene");
        jButtonSacuvajIzmene.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSacuvajIzmeneActionPerformed(evt);
            }
        });

        jButtonIzmeni.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonIzmeni.setText("Izmeni");
        jButtonIzmeni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIzmeniActionPerformed(evt);
            }
        });

        jButtonObrisi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonObrisi.setText("Obriši");
        jButtonObrisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonObrisiActionPerformed(evt);
            }
        });

        jButtonKreiraj.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonKreiraj.setText("Kreiraj");
        jButtonKreiraj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKreirajActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonObrisi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonKreiraj, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(jButtonIzmeni, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonSacuvajIzmene, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonIzmeni)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jButtonSacuvajIzmene)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonKreiraj)
                .addGap(3, 3, 3)
                .addComponent(jButtonObrisi)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonIzmeniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIzmeniActionPerformed
        jTextFieldImePrezime.setEnabled(true);
        jButtonIzmeni.setEnabled(false);
    }//GEN-LAST:event_jButtonIzmeniActionPerformed

    private void jButtonKreirajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKreirajActionPerformed
        if (jTextFieldJMBG.getText().isEmpty() 
                || !jTextFieldJMBG.getText().matches("^\\d{13}$")
                || jTextFieldImePrezime.getText().isEmpty() 
                ) {
            jLabelJmbgError.setText(jTextFieldJMBG.getText().isEmpty() ? "Unesite JMBG" : !jTextFieldJMBG.getText().matches("^\\d{13}$") ? "Pogrešan format JMBG-a":"");
            jLabelImePrezimeError.setText(jTextFieldImePrezime.getText().isEmpty() ? "Unesite ime i prezime" : "");
            return;
        }
        String jmbg = jTextFieldJMBG.getText();
        String imePrezime = jTextFieldImePrezime.getText();
        Otpremac otpremac = new Otpremac(jmbg, imePrezime);
        boolean uspesno = Controller.getInstance().kreirajOtpremac(otpremac);
        if (uspesno) {
            JOptionPane.showMessageDialog(this, "Otpremač uspešno kreiran", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
            parent.azurirajTabelu("otpremaci");
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Greška prilikom kreiranja otpremača", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonKreirajActionPerformed

    private void jButtonSacuvajIzmeneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSacuvajIzmeneActionPerformed
        if (jTextFieldJMBG.getText().isEmpty() 
                || !jTextFieldJMBG.getText().matches("^\\d{13}$")
                || jTextFieldImePrezime.getText().isEmpty() 
             ) {
            jLabelJmbgError.setText(jTextFieldJMBG.getText().isEmpty() ? "Unesite JMBG" : !jTextFieldJMBG.getText().matches("^\\d{13}$") ? "Pogrešan format JMBG-a":"");
            jLabelImePrezimeError.setText(jTextFieldImePrezime.getText().isEmpty() ? "Unesite ime i prezime" : "");
            return;
        }
        String jmbg = jTextFieldJMBG.getText();
        String imePrezime = jTextFieldImePrezime.getText();
        otpremac.setJmbg(jmbg);
        otpremac.setImePrezime(imePrezime);
        boolean uspesno = Controller.getInstance().izmeniOtpremaca(otpremac);
        if (uspesno) {
            JOptionPane.showMessageDialog(this, "Otpremač uspešno izmenjen", "Obaveštenje", JOptionPane.INFORMATION_MESSAGE);
            parent.azurirajTabelu("otpremaci");
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Greška prilikom izmene otpremača", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonSacuvajIzmeneActionPerformed

    private void jButtonObrisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonObrisiActionPerformed
        int odgovor = JOptionPane.showConfirmDialog(this, "Da li ste sigurni da želite da obrišete otpremača?", "Potvrda", JOptionPane.YES_NO_OPTION);
        if (odgovor == JOptionPane.YES_OPTION) {
            boolean uspesno = Controller.getInstance().obrisiOtpremaca(otpremac);
            if (!uspesno) {
                JOptionPane.showMessageDialog(this, "Greška prilikom brisanja otpremača", "Greška", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Uspešno obrisan otpremač", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                parent.azurirajTabelu("otpremaci");
                this.dispose();
            }
        }
        return;
    }//GEN-LAST:event_jButtonObrisiActionPerformed

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonIzmeni;
    private javax.swing.JButton jButtonKreiraj;
    private javax.swing.JButton jButtonObrisi;
    private javax.swing.JButton jButtonSacuvajIzmene;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelImePrezimeError;
    private javax.swing.JLabel jLabelJmbgError;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextFieldImePrezime;
    private javax.swing.JTextField jTextFieldJMBG;
    // End of variables declaration//GEN-END:variables

    private void inicijalizuj() {
        this.setValidationLabels();
        jButtonSacuvajIzmene.setVisible(false);
        jButtonObrisi.setVisible(false);
        jButtonIzmeni.setVisible(false);
    }

    private void inicijalizuj(Otpremac otpremac) {
        this.setValidationLabels();
        jTextFieldJMBG.setText(otpremac.getJmbg());
        jTextFieldImePrezime.setText(otpremac.getImePrezime());
        jTextFieldJMBG.setEnabled(false);
        jTextFieldImePrezime.setEnabled(false);
        jButtonKreiraj.setVisible(false);
        jButtonObrisi.setEnabled(false);
        this.otpremac = otpremac;
    }



    private void setValidationLabels() {
        jLabelImePrezimeError.setText("");
        jLabelJmbgError.setText("");
    }
}
