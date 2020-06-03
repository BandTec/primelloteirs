/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kprunnin.conexaoBanco;

import com.microsoft.sqlserver.jdbc.SQLServerXADataSource;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 *
 * @author olive
 */
public class ConnectionFactory {
    
    private DataSource dataSource;

    public ConnectionFactory() {
        
        SQLServerXADataSource serverDataSource = new SQLServerXADataSource();
        String connectionUrl = "jdbc:sqlserver://svrkprunnin.database.windows.net:1433;"
                + "database=bdkprunnin;"
                + "user=localprimeloitters@svrkprunnin;"
                //+ "user=userowlight;"
                + "password=#Gfgrupo6b;"
                + "encrypt=true;"
                + "trustServerCertificate=false;"
                + "loginTimeout=30;";
        
        //jdbc:sqlserver://svrkprunnin.database.windows.net:1433;database=bdkprunnin;
        //user=localprimeloitters@svrkprunnin;
        //password={your_password_here};
        //hostNameInCertificate=*.database.windows.net;
        
        serverDataSource.setURL(connectionUrl);
        this.dataSource = serverDataSource;
    }
    
    public Connection getConnection() throws SQLException{
        return this.dataSource.getConnection();
    }
    
}
