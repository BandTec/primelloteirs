/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kprunnin.classes;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.util.FormatUtil;

/**
 *
 * @author olive
 */
public class InfoHardware {

    private final SystemInfo si;
    private final CentralProcessor infoProcessador;
    private final FileSystem arquivosSistema;
    private final HardwareAbstractionLayer hal;

    public InfoHardware() {
        this.si = new SystemInfo();
        this.infoProcessador = si.getHardware().getProcessor();
        this.arquivosSistema = si.getOperatingSystem().getFileSystem();
        this.hal = si.getHardware();
    }

    public String getProcessador() {
        String processador = String.valueOf(this.infoProcessador);
        return processador;
    }

    public String getMemoria() {
        GlobalMemory memory = hal.getMemory();
        String teste = String.valueOf(memory);
        String[] teste2 = teste.split("/");
        String memoriaTotal = teste2[1];

        return memoriaTotal;
    }

    public String getEspacoHd() {
        OSFileStore[] fileStores = arquivosSistema.getFileStores();
        long total = fileStores[0].getTotalSpace();
        String espacoHd = FormatUtil.formatBytes(total);
        return espacoHd;
    }

    @Override
    public String toString() {
        return "Classe utilizada para capturar informações básicas do Hardware da máquina";
    }
    
}