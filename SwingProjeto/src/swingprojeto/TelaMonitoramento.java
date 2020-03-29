package swingprojeto;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class TelaMonitoramento extends javax.swing.JFrame {

    Random aleatorio = new Random();

    public TelaMonitoramento() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaMonitoramento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(TelaMonitoramento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TelaMonitoramento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(TelaMonitoramento.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();

        barCpu.setMaximum(101);
        barCpu.setMinimum(0);
        barCpu.setValue(aleatorio.nextInt(101));

        barMemoria.setMaximum(101);
        barMemoria.setMinimum(0);
        barMemoria.setValue(aleatorio.nextInt(101));

        barDisco.setMaximum(101);
        barDisco.setMinimum(0);
        barDisco.setValue(aleatorio.nextInt(101));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblCpu = new javax.swing.JLabel();
        lblMemoria = new javax.swing.JLabel();
        lblDisco = new javax.swing.JLabel();
        barCpu = new javax.swing.JProgressBar();
        barMemoria = new javax.swing.JProgressBar();
        barDisco = new javax.swing.JProgressBar();
        btnMonitorar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Monitoramento de Sistema");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(java.awt.Color.blue);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCpu.setForeground(new java.awt.Color(255, 255, 255));
        lblCpu.setText("CPU usada:");
        getContentPane().add(lblCpu, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 75, 119, 28));

        lblMemoria.setForeground(new java.awt.Color(255, 255, 255));
        lblMemoria.setText("Memória usada:");
        getContentPane().add(lblMemoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 121, 119, 28));

        lblDisco.setForeground(new java.awt.Color(255, 255, 255));
        lblDisco.setText("Disco usado:");
        getContentPane().add(lblDisco, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 167, 119, 28));

        barCpu.setForeground(new java.awt.Color(51, 102, 255));
        barCpu.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 102, 255), 2, true));
        barCpu.setStringPainted(true);
        getContentPane().add(barCpu, new org.netbeans.lib.awtextra.AbsoluteConstraints(167, 82, 200, -1));

        barMemoria.setForeground(new java.awt.Color(51, 102, 255));
        barMemoria.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 102, 255), 2, true));
        barMemoria.setStringPainted(true);
        getContentPane().add(barMemoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(167, 128, 200, -1));

        barDisco.setForeground(new java.awt.Color(51, 102, 255));
        barDisco.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 102, 255), 2, true));
        barDisco.setStringPainted(true);
        getContentPane().add(barDisco, new org.netbeans.lib.awtextra.AbsoluteConstraints(167, 174, 200, -1));

        btnMonitorar.setText("Monitorar");
        btnMonitorar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMonitorarActionPerformed(evt);
            }
        });
        getContentPane().add(btnMonitorar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 162, 41));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Desempenho da máquina");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swingprojeto/call.jpg"))); // NOI18N
        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-200, -850, 580, 1150));

        jMenu1.setText("Janelas");

        jMenuItem1.setText("Monitoramento");
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMonitorarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMonitorarActionPerformed
        barCpu.setValue(aleatorio.nextInt(101));
        barMemoria.setValue(aleatorio.nextInt(101));
        barDisco.setValue(aleatorio.nextInt(101));
    }//GEN-LAST:event_btnMonitorarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaMonitoramento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaMonitoramento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaMonitoramento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaMonitoramento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaMonitoramento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barCpu;
    private javax.swing.JProgressBar barDisco;
    private javax.swing.JProgressBar barMemoria;
    private javax.swing.JButton btnMonitorar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JLabel lblCpu;
    private javax.swing.JLabel lblDisco;
    private javax.swing.JLabel lblMemoria;
    // End of variables declaration//GEN-END:variables
}
