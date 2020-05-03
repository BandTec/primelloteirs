package br.com.kprunnin.classes;

import oshi.SystemInfo;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.util.Util;
import br.com.kprunnin.Gui.KprunninGui;

public class ThreadDsk extends Thread {

    public boolean rodando = true;
    public SystemInfo si = new SystemInfo();
    public HardwareAbstractionLayer hal = si.getHardware();
    long[] filas = new long[4];

    public void run() {

        HWDiskStore[] disk = hal.getDiskStores();
        int numeroDaMedicao = 0;
        float filaResultado;
        for (numeroDaMedicao = 0; numeroDaMedicao < filas.length; numeroDaMedicao++) {
            filas[numeroDaMedicao] = disk[0].getCurrentQueueLength();
            filaResultado = (float) (filas[0] + filas[1] + filas[2] + filas[3]) / (float) 4.0;
            int filaSetada = (int) (filaResultado * 20);
            KprunninGui.dadosDsk[0] = (float) filaSetada;
            Util.sleep(500);

        }
        run();

    }

}
