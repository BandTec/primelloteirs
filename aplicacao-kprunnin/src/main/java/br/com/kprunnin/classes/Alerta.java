/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kprunnin.classes;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

/**
 *
 * @author olive
 */
public class Alerta {

    String origem = this.getClass().getSimpleName();
    Logger log = new Logger();
    Toolbox tb = new Toolbox();
    private Integer contagemErrosCpu = 0;
    private Integer contagemErrosDisco = 0;
    private Integer contagemErrosMemoria = 0;

    private Integer nivelAlertaCpu = 90 ;
    private Integer nivelAlertaDisco = 90;
    private Integer nivelAlertaMemoria = 90;

    private Integer tempoAlertaCpu = 20;
    private Integer tempoAlertaDisco = 10;
    private Integer tempoAlertaMemoria = 10;

    private Integer errosRegistrados = 0;

    void PlayErro() throws IOException {

        try {
            File arquivo = new File("./lib/erro.wav");
            if (arquivo.exists()) {
                AudioInputStream ai = AudioSystem.getAudioInputStream(arquivo);
                Clip clipErro = AudioSystem.getClip();
                clipErro.open(ai);
                clipErro.start();
            } else {
                log.gravarLinha(tb.data(), "ERRO", origem, "Som de erro não encontrado");
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.gravarLinha(tb.data(), "ERRO", origem, "Erro ao reproduzir som de erro");
        }
    }

    public float pegaPorcentagem(Number valorTotal, Number valorAEncontrar) {
        float total = (float) valorTotal;
        float qualQuero = (float) valorAEncontrar;

        return (qualQuero * 100) / total;
    }

    public void lancarAlerta(float valorCpu, float valorDisco, float valorMemoria) throws IOException {
        if (valorCpu > nivelAlertaCpu) {
            this.contagemErrosCpu++;
        } else {
            this.contagemErrosCpu = 0;
        }

        if (valorMemoria > nivelAlertaMemoria) {
            this.contagemErrosMemoria++;
        } else {
            this.contagemErrosMemoria = 0;
        }

        if (valorDisco > nivelAlertaDisco) {
            this.contagemErrosDisco++;
        } else {
            this.contagemErrosDisco = 0;
        }

        if (this.contagemErrosCpu > tempoAlertaCpu/3) {
            PlayErro();
            this.contagemErrosCpu = 0;
            log.gravarLinha(tb.data(), "ALRT", origem, String.format("Uso de cpu maior que %d%% por mais de %d segundos",
                    nivelAlertaCpu, tempoAlertaCpu));
            this.errosRegistrados++;
            //Registrar erro
        }

        if (this.contagemErrosMemoria > tempoAlertaMemoria/3) {
            PlayErro();
            this.contagemErrosMemoria = 0;
            log.gravarLinha(tb.data(), "ALRT", origem, String.format("Uso de memória maior que %d%% por mais de %d segundos",
                    nivelAlertaMemoria, tempoAlertaMemoria));

            this.errosRegistrados++;
            //Registrar erro
        }
        if (this.contagemErrosDisco > tempoAlertaDisco/3) {
            PlayErro();
            this.contagemErrosMemoria = 0;
            log.gravarLinha(tb.data(), "ALRT", origem, String.format("Uso de disco maior que %d%% por mais de %d segundos",
                    nivelAlertaDisco, tempoAlertaDisco));
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
