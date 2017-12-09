/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.controlador.bdd;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Saito
 */
public class TestConection {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
          
            Connection con = ConexionBD.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TestConection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TestConection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
