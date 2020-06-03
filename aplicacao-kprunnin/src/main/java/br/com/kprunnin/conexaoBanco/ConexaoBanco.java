/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kprunnin.conexaoBanco;

import br.com.kprunnin.DAO.EstabelecimentoDAO;
import br.com.kprunnin.DAO.MaquinaDAO;
import br.com.kprunnin.DAO.UsuarioDAO;
import br.com.kprunnin.modelo.Estabelecimento;
import br.com.kprunnin.modelo.Maquina;
import br.com.kprunnin.modelo.Usuario;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author olive
 */
public class ConexaoBanco {

    private String login = "vazia";
    private String senha = "vazia";
    private String codigoEstab = "vazia";
    private String codigoMaquina = "vazia";
    private Integer idMaquina;
    
    public boolean testaConexaoComBanco() throws SQLException, IOException {

        boolean conectado = false;
        String conexao = "";
        
        try (Connection connection = new ConnectionFactory().getConnection()) {

            try {
               
                BufferedReader br = new BufferedReader(new FileReader("autenticacao.txt"));
                conexao = br.readLine();
                br.close();
                
                String [] stringConexao = conexao.split(";");
                
                this.login = stringConexao[0];
                this.senha = stringConexao[1];
                this.codigoEstab = stringConexao[2];
                this.codigoMaquina = stringConexao[3];
                
                conectado = true;

            } catch (IOException e) {
                throw new SQLException("Ainda não configurado");
            } finally {
                return conectado;
            }
        }
    }

    public boolean configuraConexao(String login, String senha, String codigoEstab, String codigoMaquina) {

        boolean configurado = false;

        System.out.println("Iniciando configuracao");

        BufferedWriter bw;

        try {

            bw = new BufferedWriter(new FileWriter("autenticacao.txt"));
            bw.write(login + ";" + senha + ";" + codigoEstab + ";" + codigoMaquina);

            System.out.println("Registro efetuado");

            this.login = login;
            this.senha = senha;
            this.codigoEstab = codigoEstab;
            this.codigoMaquina = codigoMaquina;

            configurado = true;
            bw.close();

        } catch (IOException e) {
        }

        return configurado;
    }

    public boolean conectarComBanco(Connection connection) throws SQLException {

        boolean conectado = false;

        Usuario usuarioLogin = new Usuario(this.login, this.senha);

        UsuarioDAO usuarioDao = new UsuarioDAO(connection);
        Usuario usuario = usuarioDao.select(usuarioLogin);

        EstabelecimentoDAO estabDao = new EstabelecimentoDAO(connection);
        Estabelecimento estabelecimento = estabDao.select(usuario, this.codigoEstab);

        MaquinaDAO maquinaDao = new MaquinaDAO(connection);
        Maquina maquina = maquinaDao.select(estabelecimento, this.codigoMaquina);

        System.out.println(String.format("Usuario: %s, Estabelecimento: %s, Maquina: %d ",
                usuario.getNome(), estabelecimento.getCodEstab(), maquina.getIdMaquina()));
        
        this.idMaquina = maquina.getIdMaquina();

        conectado = true;

        return conectado;
    }

    public Integer getIdMaquina() {
        return idMaquina;
    }
    
}
