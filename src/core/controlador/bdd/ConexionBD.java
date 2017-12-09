/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.controlador.bdd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author user
 */
public class ConexionBD {

    public static String driver;
    public static String db;
    public static String url;
    public static String user;
    public static String pass;
    private static Properties pp = new Properties();

    static {
        try {
            pp.load(new BufferedReader(new FileReader("resources/ajustes.properties")));
            String bdd, user, pass, host, port;
            bdd = pp.getProperty("bdd");
            user = pp.getProperty("user");
            pass = pp.getProperty("pass");
            host = pp.getProperty("host");
            port = pp.getProperty("port");
            ConexionBD.driver = "org.gjt.mm.mysql.Driver";
            ConexionBD.db = bdd;
            ConexionBD.url = "jdbc:mysql://" + host + ":" + port + "/" + bdd;
            ConexionBD.user = user;
            ConexionBD.pass = pass;
        } catch (IOException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        Class.forName(driver);
        Connection con = DriverManager.getConnection(url, user, pass);
        return con;
    }

}
