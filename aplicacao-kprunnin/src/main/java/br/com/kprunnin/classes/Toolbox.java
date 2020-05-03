
package br.com.kprunnin.classes;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import br.com.kprunnin.Gui.KprunninGui;

public class Toolbox {


    public float HSBFloat(int cor) {
        float porcentagemInvertida = cor - 100;
        float porcentagemInvertidaPositiva = porcentagemInvertida * -1;
        float corHsbFloat = porcentagemInvertidaPositiva / 333;
        return corHsbFloat;
    }

    void PlayErro() {

        try {
            File arquivo = new File("./src/swingprojeto/erro.wav");
            if (arquivo.exists()) {
                AudioInputStream ai = AudioSystem.getAudioInputStream(arquivo);
                Clip clipErro = AudioSystem.getClip();
                clipErro.open(ai);
                clipErro.start();
            } else {
                System.out.println("Som de erro não encontrado");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void SvcAlertaSonoro() throws InterruptedException {
        if (KprunninGui.dadosCpu[0] > 90) {
            KprunninGui.contagemErrosCpu++;
        } else {
            KprunninGui.contagemErrosCpu = 0;
        }
        if (KprunninGui.dadosMem[0] > 90) {
            KprunninGui.contagemErrosMem++;
        } else {
            KprunninGui.contagemErrosMem = 0;
        }
        if (KprunninGui.dadosDsk[0] > 90) {
            KprunninGui.contagemErrosDsk++;
        } else {
            KprunninGui.contagemErrosDsk = 0;
        }

        if (KprunninGui.contagemErrosCpu > 10) {
            PlayErro();
            KprunninGui.contagemErrosCpu = 50;
        } else {
            KprunninGui.contagemErrosCpu = 0;
        }
        if (KprunninGui.contagemErrosMem > 20) {
            PlayErro();
            KprunninGui.contagemErrosMem = 10;
        } else {
            KprunninGui.contagemErrosMem = 0;
        }
        if (KprunninGui.contagemErrosDsk > 20) {
            PlayErro();
            KprunninGui.contagemErrosDsk = 10;
        } else {
            KprunninGui.contagemErrosDsk = 0;
        }

    }
}
