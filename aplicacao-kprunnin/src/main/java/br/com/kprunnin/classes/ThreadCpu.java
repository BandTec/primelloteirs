/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kprunnin.classes;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.util.Util;
import br.com.kprunnin.Gui.KprunninGui;

public class ThreadCpu extends Thread {

    public boolean rodando = true;
    Toolbox tb = new Toolbox();
    CentralProcessor cpu = new SystemInfo().getHardware().getProcessor();
    
    public void run() {            
            while (rodando) {
            long[] medicaoOld = cpu.getSystemCpuLoadTicks();
            Util.sleep(1000);
            long[] medicaoNew = cpu.getSystemCpuLoadTicks();
            long user = medicaoNew[CentralProcessor.TickType.USER.getIndex()] - medicaoOld[CentralProcessor.TickType.USER.getIndex()];
            long sys = medicaoNew[CentralProcessor.TickType.SYSTEM.getIndex()] - medicaoOld[CentralProcessor.TickType.SYSTEM.getIndex()];
            long idle = medicaoNew[CentralProcessor.TickType.IDLE.getIndex()] - medicaoOld[CentralProcessor.TickType.IDLE.getIndex()];
            int cpuEmUso = (int) (100d * (user + sys)) / (int) (user + sys + idle);
            KprunninGui.dadosCpu[0] = (float) cpuEmUso;
            try {
                tb.SvcAlertaSonoro();
            } catch (InterruptedException ex) {
                System.out.println("erro na thread de cpu : " + ex);            
            }
            run();
            System.exit(1);
        }
    }
}