
package com.mycompany.proyecto_u1.db;

import java.sql.Connection;
import java.sql.DriverManager;


public class Conexion {
    
    
    private String host = "localhost";
    private int port = 3307; 
    private String dbName = "proyecto_encuestas_u1"; 
    private String dbUser = "root";
    private String dbPassword = null; 

    public Conexion() {
    }

    
    public Conexion(String host, int port, String dbName, String dbUser, String dbPassword) {
        this.host = host;
        this.port = port;
        this.dbName = dbName;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }
    
       public Connection open() throws Exception {
        Connection connection = DriverManager.getConnection(
            "jdbc:mysql://"+host+":"+port+"/" + dbName,
            dbUser, dbPassword
        );
        return connection;
    }
    
    public void close(Connection connection) throws Exception {
        connection.close();
    }
}
