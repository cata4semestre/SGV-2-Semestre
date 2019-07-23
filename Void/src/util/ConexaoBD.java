
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    private static final String URL_MYSQL = "jdbc:mysql://localhost:3307/projetovendas";
    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private static final String USER = "ViktorfXD";
    private static final String PASS = "ges2912";
    
    public static Connection conectarBanco() {
        System.out.println("iniciando conexão ...");
        try {
            Class.forName(DRIVER_CLASS);
            return DriverManager.getConnection(URL_MYSQL, USER, PASS);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Não foi possível conectar..." + ex.getMessage());
            return null;
        }        
    }
}
