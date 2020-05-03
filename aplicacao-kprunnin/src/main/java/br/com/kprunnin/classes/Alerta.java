/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kprunnin.classes;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author olive
 */
public class Alerta {

    private Integer contagemErrosCpu = 0;
    private Integer contagemErrosDisco = 0;
    private Integer contagemErrosMemoria = 0;
    private Integer errosRegistrados = 0;

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

    public float pegaPorcentagem(Number valorTotal, Number valorAEncontrar) {
        float total = (float) valorTotal;
        float qualQuero = (float) valorAEncontrar;

        return (qualQuero * 100) / total;
    }
    

    public void lancarAlerta(float valorCpu, float valorDisco, float valorMemoria) {
        if (valorCpu > 90) {
            this.contagemErrosCpu++;
        } else {
            this.contagemErrosCpu = 0;
        }

        if (valorMemoria > 90) {
            this.contagemErrosMemoria++;
        } else {
            this.contagemErrosMemoria = 0;
        }

        if (valorDisco > 90) {
            this.contagemErrosDisco++;
        } else {
            this.contagemErrosDisco = 0;
        }

        if (this.contagemErrosCpu > 10) {
            //PlayErro();
            this.errosRegistrados++;
            //Registrar erro
        }

        if (this.contagemErrosMemoria > 20) {
            //PlayErro();
            this.errosRegistrados++;
            //Registrar erro
        }
        if (this.contagemErrosDisco > 20) {
            //PlayErro();
            this.errosRegistrados++;
            //Registrar erro
        }

    }

    public Integer getContagemErrosCpu() {
        return contagemErrosCpu;
    }

    public Integer getContagemErrosDisco() {
        return contagemErrosDisco;
    }

    public Integer getContagemErrosMemoria() {
        return contagemErrosMemoria;
    }

    public Integer getErrosRegistrados() {
        return errosRegistrados;
    }

}
