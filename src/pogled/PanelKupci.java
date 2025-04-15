
package pogled;

import controller.Controller;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.Kupac;
import pogled.dialog.KupciDialog;
import table_models.KupacModelTabele;

/**
 *
 * @author Andrej
 */
public class PanelKupci extends javax.swing.JPanel {

    JFrame parent;
    /**
     * Creates new form PanelKupci
     */
    public PanelKupci(JFrame parent) {
        initComponents();
        this.parent = parent;
        inicijalizacija();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableKupci = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jTextFieldNaziv = new javax.swing.JTextField();
        jButtonFilter = new javax.swing.JButton();
        jButtonOcistiFilter = new javax.swing.JButton();
        jButtonDetalji = new javax.swing.JButton();
        jButtonKreiraj = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1000, 700));
        setLayout(new java.awt.BorderLayout());

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 10));
        jPanel9.setPreferredSize(new java.awt.Dimension(1200, 600));
        jPanel9.setLayout(new java.awt.BorderLayout());

        jScrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jTableKupci.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableKupci.setShowGrid(true);
        jScrollPane3.setViewportView(jTableKupci);

        jPanel9.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        add(jPanel9, java.awt.BorderLayout.SOUTH);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1200, 100));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 30));
        jPanel3.setPreferredSize(new java.awt.Dimension(600, 100));

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Naziv:");
        jLabel24.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel3.add(jLabel24);

        jTextFieldNaziv.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel3.add(jTextFieldNaziv);

        jButtonFilter.setText("Filtriraj");
        jButtonFilter.setPreferredSize(new java.awt.Dimension(120, 30));
        jButtonFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFilterActionPerformed(evt);
            }
        });
        jPanel3.add(jButtonFilter);

        jButtonOcistiFilter.setText("Očisti filter");
        jButtonOcistiFilter.setPreferredSize(new java.awt.Dimension(120, 30));
        jButtonOcistiFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOcistiFilterActionPerformed(evt);
            }
        });
        jPanel3.add(jButtonOcistiFilter);

        jButtonDetalji.setText("Detalji");
        jButtonDetalji.setPreferredSize(new java.awt.Dimension(200, 30));
        jButtonDetalji.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDetaljiActionPerformed(evt);
            }
        });
        jPanel3.add(jButtonDetalji);

        jButtonKreiraj.setText("Kreiraj");
        jButtonKreiraj.setPreferredSize(new java.awt.Dimension(200, 30));
        jButtonKreiraj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKreirajActionPerformed(evt);
            }
        });
        jPanel3.add(jButtonKreiraj);

        jPanel2.add(jPanel3, java.awt.BorderLayout.NORTH);

        add(jPanel2, java.awt.BorderLayout.NORTH);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFilterActionPerformed
        if (jTextFieldNaziv.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Unesite kriterijum pretrage", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String naziv = jTextFieldNaziv.getText();
        Kupac kupac = new Kupac();
        kupac.setNaziv(naziv);
        List<Kupac> lista = new ArrayList<>();
        boolean uspesno = Controller.getInstance().vratiListuSviKupac(kupac, lista);
        if (uspesno) {
            KupacModelTabele kmt = new KupacModelTabele(lista);
            jTableKupci.setModel(kmt);
        }
    }//GEN-LAST:event_jButtonFilterActionPerformed

    private void jButtonOcistiFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOcistiFilterActionPerformed
        jTextFieldNaziv.setText("");
        inicijalizacija();
    }//GEN-LAST:event_jButtonOcistiFilterActionPerformed

    private void jButtonKreirajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKreirajActionPerformed
        KupciDialog kd = new KupciDialog(parent, true);
        kd.setVisible(true);
    }//GEN-LAST:event_jButtonKreirajActionPerformed

    private void jButtonDetaljiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDetaljiActionPerformed
        int red = jTableKupci.getSelectedRow();
        if (red == -1) {
            JOptionPane.showMessageDialog(this, "Izaberite kupca", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }
        KupacModelTabele kmt = (KupacModelTabele) jTableKupci.getModel();
        Kupac kupac = kmt.getList().get(red);
        KupciDialog kd = new KupciDialog(parent, true, kupac);
        kd.setVisible(true);
    }//GEN-LAST:event_jButtonDetaljiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDetalji;
    private javax.swing.JButton jButtonFilter;
    private javax.swing.JButton jButtonKreiraj;
    private javax.swing.JButton jButtonOcistiFilter;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableKupci;
    private javax.swing.JTextField jTextFieldNaziv;
    // End of variables declaration//GEN-END:variables

    private void inicijalizacija() {
        List<Kupac> lista = new ArrayList<>();
        boolean uspesno = Controller.getInstance().vratiListuKupac(lista);
        if (uspesno) {
            KupacModelTabele kmt = new KupacModelTabele(lista);
            jTableKupci.setModel(kmt);
        } else {
            JOptionPane.showMessageDialog(this, "Sistem ne može da učita listu kupaca", "Greška", JOptionPane.ERROR_MESSAGE);
        }

    }

    void azurirajTabelu() {
        inicijalizacija();
    }
}
