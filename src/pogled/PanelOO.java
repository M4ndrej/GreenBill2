
package pogled;

import controller.Controller;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.OdeljenjeOdsek;
import pogled.dialog.OdeljenjeOdsekDialog;
import table_models.OdeljenjeOdsekModelTabele;

/**
 *
 * @author Andrej
 */
public class PanelOO extends javax.swing.JPanel {

    JFrame parent;

    /**
     * Creates new form PanelOO
     */
    public PanelOO(JFrame parent) {
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

        jPanel6 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButtonDetalji = new javax.swing.JButton();
        jButtonKreiraj = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableOO = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1000, 700));
        setLayout(new java.awt.BorderLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(400, 700));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 100));

        jButtonDetalji.setText("DETALJI");
        jButtonDetalji.setPreferredSize(new java.awt.Dimension(350, 40));
        jButtonDetalji.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDetaljiActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonDetalji);

        jButtonKreiraj.setText("KREIRAJ");
        jButtonKreiraj.setPreferredSize(new java.awt.Dimension(350, 40));
        jButtonKreiraj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKreirajActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonKreiraj);

        jPanel6.add(jPanel1, java.awt.BorderLayout.SOUTH);

        add(jPanel6, java.awt.BorderLayout.EAST);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 10));
        jPanel5.setPreferredSize(new java.awt.Dimension(600, 700));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(550, 650));

        jTableOO.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableOO.setPreferredSize(new java.awt.Dimension(550, 650));
        jTableOO.setShowGrid(true);
        jScrollPane1.setViewportView(jTableOO);

        jPanel5.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        add(jPanel5, java.awt.BorderLayout.WEST);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonKreirajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKreirajActionPerformed
        OdeljenjeOdsekDialog ood = new OdeljenjeOdsekDialog(parent, true);
        ood.setVisible(true);
    }//GEN-LAST:event_jButtonKreirajActionPerformed

    private void jButtonDetaljiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDetaljiActionPerformed
        int red = jTableOO.getSelectedRow();
        if (red == -1) {
            JOptionPane.showMessageDialog(this, "Izaberite odeljenje i odsek", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }
        OdeljenjeOdsekModelTabele oomt = (OdeljenjeOdsekModelTabele) jTableOO.getModel();
        OdeljenjeOdsek oo = oomt.getLista().get(red);
        OdeljenjeOdsekDialog ood = new OdeljenjeOdsekDialog(parent, true, oo);
        ood.setVisible(true);
    }//GEN-LAST:event_jButtonDetaljiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDetalji;
    private javax.swing.JButton jButtonKreiraj;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableOO;
    // End of variables declaration//GEN-END:variables

    private void inicijalizacija() {
        List<OdeljenjeOdsek> lista = new ArrayList<>();
        boolean uspesno = Controller.getInstance().vratiListuOdeljenjeOdsek(lista);
        if (uspesno) {
            OdeljenjeOdsekModelTabele oomt = new OdeljenjeOdsekModelTabele(lista);
            jTableOO.setModel(oomt);
        } else {
            JOptionPane.showMessageDialog(this, "Sistem ne može da učita listu odeljenja i odseka", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    void azurirajTabelu() {
        inicijalizacija();
    }
}
