package br.com.kprunnin.classes;

import oshi.SystemInfo;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.util.Util;
import br.com.kprunnin.Gui.KprunninGui;
import java.awt.Color;
import java.io.File;

public class ThreadDsk extends Thread {

    public boolean rodando = true;
    Toolbox tb = new Toolbox();
    public SystemInfo si = new SystemInfo();
    public HardwareAbstractionLayer hal = si.getHardware();
    long[] filas = new long[4];
    File[] disco = File.listRoots();
    
    public void run() {

        HWDiskStore[] disk = hal.getDiskStores();
        int numeroDaMedicao = 0;
        float filaResultado;
        //KprunninGui.lblDisco.setText(String.format(" s %d", (int)((disco[0].getTotalSpace() - disco[0].getFreeSpace()) / disco[0].getTotalSpace()) * 100));
        Double espacoLivre = (double) disco[0].getFreeSpace() / 1073741824;
        Double espacoTotal = (double) disco[0].getTotalSpace() / 1073741824;
        KprunninGui.lblArmazenamento.setText("Armazenamento Total: " + (disk[0].getSize() / 1073741824) +  " GB");
        int espacoUsado = (int) Math.round(((espacoTotal - espacoLivre) / espacoTotal) * 100);
        //KprunninGui.usoDsk = (int)(disco[0].getTotalSpace() - disco[0].getFreeSpace() / disco[0].getTotalSpace()) * 100;
        //KprunninGui.usoDsk = (int)(disco[0].getTotalSpace() - disco[0].getFreeSpace() / disco[0].getTotalSpace()) * 100;
        //KprunninGui.barraDsk.setValue((int)(disco[0].getTotalSpace() - disco[0].getFreeSpace() / disco[0].getTotalSpace()) * 100);
        KprunninGui.barraDsk.setValue(espacoUsado);
        KprunninGui.barraDsk.setForeground(Color.getHSBColor(tb.HSBFloat(KprunninGui.barraDsk.getValue()), 1.0f, 1.0f));
        if (KprunninGui.timeTick < 5) {
            KprunninGui.dadosDsk[0]++;
            KprunninGui.timeTick++;
            Util.sleep(1000);
        }else {
        for (numeroDaMedicao = 0; numeroDaMedicao < filas.length; numeroDaMedicao++) {
            filas[numeroDaMedicao] = disk[0].getCurrentQueueLength();
            filaResultado = (float) (filas[0] + filas[1] + filas[2] + filas[3]) / (float) 4.0;
            int filaSetada = (int) (filaResultado * 20);
            KprunninGui.dadosDsk[0] = (float) filaSetada;
            Util.sleep(500);

        }
        }
        run();

    }

}
