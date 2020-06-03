/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kprunnin.modelo;

/**
 *
 * @author olive
 */
public class Maquina {
    
    private Integer idMaquina;
    private String tipoMaquina;
    private String codigoMaquina;
    private String numeroSerie;
    private long memoria;
    private long cpu;
    private long disco;
    private Integer fkEstabelecimento;
    
    public Maquina(){}
    public Maquina(Integer idMaquina, String tipoMaquina, String codigoMaquina, String numeroSerie, long memoria, long cpu, long disco, Integer fkEstabelecimento) {
        this.idMaquina = idMaquina;
        this.tipoMaquina = tipoMaquina;
        this.codigoMaquina = codigoMaquina;
        this.numeroSerie = numeroSerie;
        this.memoria = memoria;
        this.cpu = cpu;
        this.disco = disco;
        this.fkEstabelecimento = fkEstabelecimento;
    }

    public Integer getIdMaquina() {
        return idMaquina;
    }

    public String getTipoMaquina() {
        return tipoMaquina;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public long getMemoria() {
        return memoria;
    }

    public long getCpu() {
        return cpu;
    }

    public long getDisco() {
        return disco;
    }

    public Integer getFkEstabelecimento() {
        return fkEstabelecimento;
    }

    @Override
    public String toString() {
        return String.format("Codigo da maquina: %s, fkEstabelecimento: %d", this.codigoMaquina, this.fkEstabelecimento);
    }
    
}
