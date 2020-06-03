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
public class Estabelecimento {
    
    private Integer idEstab;
    private String codEstab;
    private Integer numMaquinas;
    private Integer fkUsuario;

    public Estabelecimento(){}
    public Estabelecimento(Integer idEstab, String codEstab, Integer numMaquinas, Integer fkUsuario) {
        this.idEstab = idEstab;
        this.codEstab = codEstab;
        this.numMaquinas = numMaquinas;
        this.fkUsuario = fkUsuario;
    }

    public Integer getIdEstab() {
        return idEstab;
    }

    public String getCodEstab() {
        return codEstab;
    }

    public Integer getNumMaquinas() {
        return numMaquinas;
    }

    public Integer getFkUsuario() {
        return fkUsuario;
    }

    @Override
    public String toString() {
        return String.format("Estabelecimento código: %s", this.codEstab);
    }
    
    
}
