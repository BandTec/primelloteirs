/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kprunnin.Gui;

import br.com.kprunnin.classes.Alerta;
import br.com.kprunnin.classes.GraficoLinha;
import br.com.kprunnin.classes.GraficoPizza;
import br.com.kprunnin.classes.InfoHardware;
import br.com.kprunnin.classes.InfoMaquina;
import br.com.kprunnin.classes.Monitoramento;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.Timer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.DynamicTimeSeriesCollection;
import org.jfree.data.time.Second;

/**
 *
 * @author olive
 */
public class KprunninGui extends javax.swing.JFrame {

    private final Monitoramento monitoramento;

    private final InfoHardware infoHardware;
    private final InfoMaquina infoMaquina;

    private final DynamicTimeSeriesCollection datasetCpu;
    private final DynamicTimeSeriesCollection datasetDisco;

    private final GraficoLinha graficoLinha;
    private final GraficoPizza graficoPizza;

    private final Alerta alerta;

    public KprunninGui() {
        initComponents();

        this.monitoramento = new Monitoramento();

        this.infoHardware = new InfoHardware();
        this.infoMaquina = new InfoMaquina();

        this.graficoLinha = new GraficoLinha(monitoramento.getCPU());
        this.graficoPizza = new GraficoPizza();

        this.datasetCpu = new DynamicTimeSeriesCollection(1, 60, new Second());
        this.datasetDisco = new DynamicTimeSeriesCollection(1, 60, new Second());

        this.alerta = new Alerta();

        getInfoEstaticas();

        lblAlerta.setText("Numeros de alertas Registrados: ");

        setValoresIniciais();

        atualizar();
    }

    private void atualizar() {

        Timer timer = new Timer(50, e -> {

            this.datasetCpu.advanceTime();
            this.datasetCpu.appendData(monitoramento.getCPU());

            this.datasetDisco.advanceTime();
            this.datasetDisco.appendData(monitoramento.getDisco());

            graficoPizza.limparDataset();

            graficoPizza.adicionaValor(String.format("Memoria em uso = %.2f",
                    graficoPizza.getPorcentagem(monitoramento.getMemoriaTotal(), monitoramento.getMemoriaEmUso())) + "%",
                    monitoramento.getMemoriaEmUso());

            graficoPizza.adicionaValor(String.format("Memoria Livre = %.2f",
                    graficoPizza.getPorcentagem(monitoramento.getMemoriaTotal(), monitoramento.getMemoriaLivre())) + "%",
                    monitoramento.getMemoriaLivre());

            alerta.lancarAlerta(monitoramento.getCPU()[0], monitoramento.getDisco()[0],
                    alerta.pegaPorcentagem(monitoramento.getMemoriaTotal(), monitoramento.getMemoriaEmUso()));

            lblAlerta.setText("Numeros de Alertas Registrados: " + alerta.getErrosRegistrados());
            
            pnlGeral.updateUI();

        });
        timer.start();
    }

    private void getInfoEstaticas() {

        lblMarca.setText("Montadora: " + infoMaquina.getMarca());
        lblModelo.setText("Modelo: " + infoMaquina.getModelo());
        lblNumeroSerie.setText("Numero de Série: " + infoMaquina.getNumeroDeSerie());
        lblSistemaOperacional.setText("Sistema Operacional: " + infoMaquina.getSistemaOperacional());
        lblArmazenamento.setText("Armazenamento Total: " + infoHardware.getEspacoHd());
        lblMemoria.setText("Memoria total: " + infoHardware.getMemoria());
        lblProcessador.setText("Informações do Processador: " + infoHardware.getProcessador());

    }

    private void setValoresIniciais() {

        Date date = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());

        this.datasetCpu.setTimeBase(new Second(date));
        this.datasetCpu.addSeries(monitoramento.getCPU(), 0, "CPU");

        this.datasetDisco.setTimeBase(new Second(date));
        this.datasetDisco.addSeries(monitoramento.getCPU(), 0, "Disco");

        graficoPizza.adicionaValor(String.format("Memoria em uso = %.2f",
                graficoPizza.getPorcentagem(monitoramento.getMemoriaTotal(), monitoramento.getMemoriaEmUso())) + "%",
                monitoramento.getMemoriaEmUso());

        graficoPizza.adicionaValor(String.format("Memoria Livre = %.2f",
                graficoPizza.getPorcentagem(monitoramento.getMemoriaTotal(), monitoramento.getMemoriaLivre())) + "%",
                monitoramento.getMemoriaLivre());

    }

    private ChartPanel criaGraficoCpu() {

        JFreeChart systemCpu = ChartFactory.createTimeSeriesChart("Uso da CPU", "Tempo", "% de uso da CPU", this.datasetCpu, true,
                true, false);
        ChartPanel chartPanel = new ChartPanel(systemCpu);

        return chartPanel;

    }

    private ChartPanel criaGraficoMemoria() {

        ChartPanel chartPanel = graficoPizza.criaGraficoPizza(graficoPizza.getDataset(), "Uso da Memória");
        return chartPanel;

    }

    private ChartPanel criaGraficoDisco() {

        JFreeChart graficoDisco = ChartFactory.createTimeSeriesChart("Uso do Disco", "Tempo", "% de uso da Disco", this.datasetDisco, true,
                true, false);
        ChartPanel chartPanel = new ChartPanel(graficoDisco);

        return chartPanel;
    }

    private void getGrafico(ChartPanel chartPanel) {

        chartPanel.setVisible(true);
        chartPanel.setSize(pnlGeral.getWidth(), pnlGeral.getHeight());

        pnlGeral.removeAll();
        pnlGeral.add(chartPanel);
        pnlGeral.revalidate();
        pnlGeral.repaint();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        pnlGeral = new javax.swing.JPanel();
        btnCPU = new javax.swing.JButton();
        btnMemoria = new javax.swing.JButton();
        btnDisco = new javax.swing.JButton();
        lblMarca = new javax.swing.JLabel();
        lblModelo = new javax.swing.JLabel();
        lblNumeroSerie = new javax.swing.JLabel();
        lblSistemaOperacional = new javax.swing.JLabel();
        lblArmazenamento = new javax.swing.JLabel();
        lblMemoria = new javax.swing.JLabel();
        lblProcessador = new javax.swing.JLabel();
        lblAlerta = new javax.swing.JLabel();

        jLabel4.setText("jLabel4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(0, 0));

        pnlGeral.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout pnlGeralLayout = new javax.swing.GroupLayout(pnlGeral);
        pnlGeral.setLayout(pnlGeralLayout);
        pnlGeralLayout.setHorizontalGroup(
            pnlGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 488, Short.MAX_VALUE)
        );
        pnlGeralLayout.setVerticalGroup(
            pnlGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 304, Short.MAX_VALUE)
        );

        btnCPU.setText("Mostrar uso CPU");
        btnCPU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCPUActionPerformed(evt);
            }
        });

        btnMemoria.setText("Mostrar uso Memória");
        btnMemoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMemoriaActionPerformed(evt);
            }
        });

        btnDisco.setText("Mostrar uso de Disco");
        btnDisco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiscoActionPerformed(evt);
            }
        });

        lblMarca.setText("jLabel1");

        lblModelo.setText("jLabel2");

        lblNumeroSerie.setText("jLabel5");

        lblSistemaOperacional.setText("jLabel7");

        lblArmazenamento.setText("jLabel8");

        lblMemoria.setText("jLabel9");

        lblProcessador.setText("jLabel10");

        lblAlerta.setText("jLabel11");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblProcessador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(325, 325, 325))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMemoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblSistemaOperacional)
                                    .addComponent(lblMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblArmazenamento, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNumeroSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(btnCPU, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btnMemoria, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDisco, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblAlerta, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(pnlGeral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMarca)
                    .addComponent(lblModelo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSistemaOperacional)
                    .addComponent(lblNumeroSerie))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblArmazenamento)
                    .addComponent(lblMemoria))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblProcessador)
                .addGap(9, 9, 9)
                .addComponent(lblAlerta, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCPU)
                    .addComponent(btnMemoria)
                    .addComponent(btnDisco))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlGeral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCPUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCPUActionPerformed
        getGrafico(criaGraficoCpu());
    }//GEN-LAST:event_btnCPUActionPerformed

    private void btnMemoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMemoriaActionPerformed
        getGrafico(criaGraficoMemoria());
    }//GEN-LAST:event_btnMemoriaActionPerformed

    private void btnDiscoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiscoActionPerformed
        getGrafico(criaGraficoDisco());
    }//GEN-LAST:event_btnDiscoActionPerformed

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
            java.util.logging.Logger.getLogger(KprunninGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KprunninGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KprunninGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KprunninGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KprunninGui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCPU;
    private javax.swing.JButton btnDisco;
    private javax.swing.JButton btnMemoria;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblAlerta;
    private javax.swing.JLabel lblArmazenamento;
    private javax.swing.JLabel lblMarca;
    private javax.swing.JLabel lblMemoria;
    private javax.swing.JLabel lblModelo;
    private javax.swing.JLabel lblNumeroSerie;
    private javax.swing.JLabel lblProcessador;
    private javax.swing.JLabel lblSistemaOperacional;
    private javax.swing.JPanel pnlGeral;
    // End of variables declaration//GEN-END:variables
}
