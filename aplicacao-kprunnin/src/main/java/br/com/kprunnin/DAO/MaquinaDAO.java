/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kprunnin.DAO;

import br.com.kprunnin.modelo.Estabelecimento;
import br.com.kprunnin.modelo.Maquina;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author olive
 */
public class MaquinaDAO {
    
    private Connection connection;

    public MaquinaDAO(Connection connection) {
        this.connection = connection;
    }

    public Maquina select(Estabelecimento estabelecimento, String codigoMaquina) throws SQLException {

        String insertSql = "select m.IdMaquina, m.tipoMaquina,m.codigoMaquina, m.numeroSerie," +
                            " m.memoria, m.valorCpu, m.disco, m.fkEstabelecimento \n" +
                            "from kprMaquina as m inner join kprEstabelecimento as e " +
                            "on m.fkEstabelecimento = ? and e.idEstab = ? and m.codigoMaquina = ?;";

        try (PreparedStatement ps = connection.prepareStatement(insertSql)) {

            ps.setInt(1, estabelecimento.getIdEstab());
            ps.setInt(2, estabelecimento.getIdEstab());
            ps.setString(3, codigoMaquina);
            
            ps.execute();
            
            try(ResultSet rs = ps.getResultSet()){
                
                Maquina maquina = null;
                
                while(rs.next()){
                    
                    maquina = new 
                        Maquina(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4),
                                rs.getInt(5), rs.getInt(6), rs.getInt(7),rs.getInt(8));
                    
                }
                
                return maquina;
            }
        }
    }
}
