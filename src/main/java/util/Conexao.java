/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jhonatan
 */
public class Conexao {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/gefi";
    public Connection conn;

    public void abrirConexao() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        this.conn = DriverManager.getConnection(DB_URL, "root", "root");
    }

}
