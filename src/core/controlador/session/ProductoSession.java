/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.controlador.session;

import core.controlador.bdd.ConexionBD;
import core.logger.LogService;
import core.modelo.to.EmpleadoTo;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;



/**
 *
 * @author Saito
 */
public class ProductoSession {
    
     public EmpleadoTo getEmpleadoByCedula (String user,String cedEmpleado) throws ClassNotFoundException, SQLException{
        EmpleadoTo empleado = new EmpleadoTo();
        String sql = "CALL obed.getEmpleadoByCedula(?)";
        Connection con = null;
        CallableStatement st = null;
        ResultSet rs = null;
        try{
            con = ConexionBD.getConnection();
            st = con.prepareCall(sql);
            st.setString(1, cedEmpleado);
            LogService.logger.info(user, "Ejecutando query"+"["+cedEmpleado+"]");
            st.execute();
            LogService.logger.info(user, "Query Ejecutado");
            rs = st.getResultSet();
            if(rs.next()){

//                id INT(11) NOT NULL AUTO_INCREMENT,
//                cedula VARCHAR(11) NOT NULL,
//                primer_nombre VARCHAR(20) NOT NULL,
//                segundo_nombre VARCHAR(20) DEFAULT NULL,
//                primer_apellido VARCHAR(20) NOT NULL,
//                segundo_apellido VARCHAR(20) DEFAULT NULL,
//                fecha_nac DATE NOT NULL,
//                sexo VARCHAR(20) DEFAULT NULL,
//                direccion VARCHAR(255) NOT NULL,
//                correo VARCHAR(50) NOT NULL
//                telefono VARCHAR(14) NOT NULL,
//                cargo_t_cargo VARCHAR(25) NOT NULL

            empleado.setCedula(rs.getString("cedula"));
            empleado.setPrimerNombre(rs.getString("primer_nombre"));
            empleado.setSegundoNombre(rs.getString("segundo_nombre"));
            empleado.setPrimerApellido(rs.getString("primer_apellido"));
            empleado.setSegundoApellido(rs.getString("segundo_apellido"));
            empleado.setFechaNacimiento(rs.getDate("fecha_nac"));
            empleado.setSexo(rs.getString("sexo"));
            empleado.setDireccion(rs.getString("direccion"));
            empleado.setCorreo(rs.getString("correo"));
            empleado.setTelefono(rs.getString("telefono"));
            empleado.setCargo(rs.getString("cargo_t_cargo"));

        }
            
        }catch (SQLException ex){
            throw ex;
        }catch (ClassNotFoundException ex){
            throw ex;
        }catch (Exception ex){
            throw ex;
        }finally{
            if(rs!=null){
                rs.close();}
            if(st!=null){
                st.close();}
            if(con!=null){
                con.close();}
        }

        
        return empleado;
    }
}
