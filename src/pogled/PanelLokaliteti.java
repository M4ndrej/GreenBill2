
package pogled;

import controller.Controller;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.Lokalitet;
import table_models.LokalitetModelTabele;
import pogled.dialog.LokalitetDialog;

/**
 *
 * @author Andrej
 */
public class PanelLokaliteti extends javax.swing.JPanel {

    JFrame parent;
    /**
     * Creates new form PanelLokaliteti
     */
    public PanelLokaliteti(JFrame parent) {
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
        jTableLokaliteti = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1000, 700));

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

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 10));
        jPanel5.setPreferredSize(new java.awt.Dimension(600, 700));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(550, 650));

        jTableLokaliteti.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableLokaliteti.setPreferredSize(new java.awt.Dimension(550, 650));
        jTableLokaliteti.setShowGrid(true);
        jScrollPane1.setViewportView(jTableLokaliteti);

        jPanel5.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1012, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 712, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonDetaljiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDetaljiActionPerformed
        int red = jTableLokaliteti.getSelectedRow();
        if (red == -1) {
            JOptionPane.showMessageDialog(this, "Odaberite lokalitet", "Greska", JOptionPane.ERROR_MESSAGE);
            return;
        }
        LokalitetModelTabele lmt = (LokalitetModelTabele) jTableLokaliteti.getModel();
        Lokalitet lokalitet = lmt.getLista().get(red);
        LokalitetDialog ld = new LokalitetDialog(parent, true, lokalitet);
        ld.setVisible(true);
    }//GEN-LAST:event_jButtonDetaljiActionPerformed

    private void jButtonKreirajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKreirajActionPerformed
        LokalitetDialog ld = new LokalitetDialog(parent, true);
        ld.setVisible(true);
    }//GEN-LAST:event_jButtonKreirajActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDetalji;
    private javax.swing.JButton jButtonKreiraj;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableLokaliteti;
    // End of variables declaration//GEN-END:variables

    void azurirajTabelu() {
        inicijalizacija();
    }

    private void inicijalizacija() {
        List<Lokalitet> lista = new ArrayList<>();
        boolean uspesno = Controller.getInstance().vratiListuLokalitet(lista);
        if (uspesno) {
            LokalitetModelTabele lmt = new LokalitetModelTabele(lista);
            jTableLokaliteti.setModel(lmt);
        } else {
            JOptionPane.showMessageDialog(this, "Sistem ne može da učita listu lokaliteta", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }
}
