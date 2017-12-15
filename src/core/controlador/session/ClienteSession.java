/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.controlador.session;

import core.controlador.bdd.ConexionBD;
import core.logger.LogService;
import core.modelo.to.ClienteTo;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import obed77.Principal;



/**
 *
 * @author Saito
 */
public class ClienteSession {
    
     public ArrayList<ClienteTo> getClientes() throws ClassNotFoundException, SQLException{
        ClienteTo to = null;
        ArrayList<ClienteTo> tos = new ArrayList<>();
        String sql = "CALL obed.getClientes()";
        Connection con = null;
        CallableStatement st = null;
        ResultSet rs = null;
        try{
            con = ConexionBD.getConnection();
            st = con.prepareCall(sql);
            long inicio = System.currentTimeMillis();
            LogService.logger.info(Principal.getUsuarioPrincipal().getUser(), "Ejecutando query");
            st.execute();
            long fin = System.currentTimeMillis();
            LogService.logger.info(Principal.getUsuarioPrincipal().getUser(), "Query Ejecutado ["+(fin-inicio)+"]ms");
            rs = st.getResultSet();
            while(rs.next()){
                
//                tipo_t_documento VARCHAR(25) NOT NULL,
//                documento VARCHAR(30) NOT NULL,
//                nombre VARCHAR(255) NOT NULL,
//                telefono VARCHAR(25) DEFAULT NULL,
//                correo VARCHAR(150) DEFAULT NULL,
//                direccion VARCHAR(255) DEFAULT NULL,
//                estatus INT(11) NOT NULL DEFAULT 1,

            to = new ClienteTo();
            to.setTipoDocumento(rs.getString("tipo_t_documento"));
            to.setDocumento(rs.getString("documento"));
            to.setNombre(rs.getString("nombre"));
            to.setTelefono(rs.getString("telefono"));
            to.setCorreo(rs.getString("correo"));
            to.setDireccion(rs.getString("direccion"));
            to.setEstatus(rs.getInt("estatus"));
            tos.add(to);
        }
            
        }catch (SQLException ex){
            LogService.logger.error(Principal.getUsuarioPrincipal().getUser(), "Error: "+ex.getMessage());
            throw ex;
        }catch (ClassNotFoundException ex){
            LogService.logger.error(Principal.getUsuarioPrincipal().getUser(), "Error: "+ex.getMessage());
            throw ex;
        }catch (Exception ex){
            LogService.logger.error(Principal.getUsuarioPrincipal().getUser(), "Error: "+ex.getMessage());
            throw ex;
        }finally{
            if(rs!=null){
                rs.close();}
            if(st!=null){
                st.close();}
            if(con!=null){
                con.close();}
        }

        
        return tos;
    }
     
     
     public ArrayList<ClienteTo> getClientesActivos() throws ClassNotFoundException, SQLException{
        ClienteTo to = null;
        ArrayList<ClienteTo> tos = new ArrayList<>();
        String sql = "CALL obed.getClientesActivos()";
        Connection con = null;
        CallableStatement st = null;
        ResultSet rs = null;
        try{
            con = ConexionBD.getConnection();
            st = con.prepareCall(sql);
            long inicio = System.currentTimeMillis();
            LogService.logger.info(Principal.getUsuarioPrincipal().getUser(), "Ejecutando query");
            st.execute();
            long fin = System.currentTimeMillis();
            LogService.logger.info(Principal.getUsuarioPrincipal().getUser(), "Query Ejecutado ["+(fin-inicio)+"]ms");
            rs = st.getResultSet();
            while(rs.next()){
                
//                tipo_t_documento VARCHAR(25) NOT NULL,
//                documento VARCHAR(30) NOT NULL,
//                nombre VARCHAR(255) NOT NULL,
//                telefono VARCHAR(25) DEFAULT NULL,
//                correo VARCHAR(150) DEFAULT NULL,
//                direccion VARCHAR(255) DEFAULT NULL,
//                estatus INT(11) NOT NULL DEFAULT 1,

            to = new ClienteTo();
            to.setTipoDocumento(rs.getString("tipo_t_documento"));
            to.setDocumento(rs.getString("documento"));
            to.setNombre(rs.getString("nombre"));
            to.setTelefono(rs.getString("telefono"));
            to.setCorreo(rs.getString("correo"));
            to.setDireccion(rs.getString("direccion"));
            to.setEstatus(rs.getInt("estatus"));
            tos.add(to);
        }
            
        }catch (SQLException ex){
            LogService.logger.error(Principal.getUsuarioPrincipal().getUser(), "Error: "+ex.getMessage());
            throw ex;
        }catch (ClassNotFoundException ex){
            LogService.logger.error(Principal.getUsuarioPrincipal().getUser(), "Error: "+ex.getMessage());
            throw ex;
        }catch (Exception ex){
            LogService.logger.error(Principal.getUsuarioPrincipal().getUser(), "Error: "+ex.getMessage());
            throw ex;
        }finally{
            if(rs!=null){
                rs.close();}
            if(st!=null){
                st.close();}
            if(con!=null){
                con.close();}
        }

        
        return tos;
    }
     
     
     
     
    public boolean insertarCliente (ClienteTo to) throws ClassNotFoundException, SQLException, Exception{
        boolean res = false;
//       insertarCliente(IN pTipoDocumento VARCHAR(255), IN pDocumento VARCHAR(255), IN pNombre VARCHAR(255), 
//                          IN pTelefono VARCHAR(255), IN pCorreo VARCHAR(255), 
//                          IN pDireccion VARCHAR(255), IN pUsuario VARCHAR(255))
        String sql = "CALL insertarCliente(?,?,?,?,?,?,?)" ;
        Connection con = null;
        CallableStatement st = null;
        try{
            con = ConexionBD.getConnection();
            con.setAutoCommit(false);
            st = con.prepareCall(sql);
            st.setString(1, to.getTipoDocumento());
            st.setString(2, to.getDocumento().toUpperCase());
            st.setString(3, to.getNombre());
            st.setString(4, to.getTelefono());
            st.setString(5, to.getCorreo());
            st.setString(6, to.getDireccion());
            st.setString(7, Principal.getUsuarioPrincipal().getUser());
            
            long inicio = System.currentTimeMillis();
            LogService.logger.info(Principal.getUsuarioPrincipal().getUser(), "Ejecutando query "+"["+to.getNombre()+","+Principal.getUsuarioPrincipal().getUser()+"]");
            st.execute();
            long fin = System.currentTimeMillis();
            LogService.logger.info(Principal.getUsuarioPrincipal().getUser(),"Query Ejecutado ["+(fin-inicio)+"]ms");
            con.commit();
            res = true;
        }catch (SQLException ex){
            con.rollback();
            LogService.logger.error(Principal.getUsuarioPrincipal().getUser(), "Error: "+ex.getMessage());
            throw ex;
        }catch (ClassNotFoundException ex){
            con.rollback();
            LogService.logger.error(Principal.getUsuarioPrincipal().getUser(), "Error: "+ex.getMessage());
            throw ex;
        }catch (Exception ex){
            con.rollback();
            LogService.logger.error(Principal.getUsuarioPrincipal().getUser(), "Error: "+ex.getMessage());
            throw ex;
        }finally{
            
            if(st!=null){
                st.close();}
            if(con!=null){
                con.close();}
        }
       return res;
    }
    
    
    public boolean modificarCliente (ClienteTo to) throws ClassNotFoundException, SQLException, Exception{
        boolean res = false;
//      modificarCliente(IN pTipoDocumento VARCHAR(255), IN pDocumento VARCHAR(255), IN pNombre VARCHAR(255), 
//                            IN pTelefono VARCHAR(255), IN pCorreo VARCHAR(255), 
//                            IN pDireccion VARCHAR(255), IN pUsuario VARCHAR(255))
        String sql = "CALL modificarCliente(?,?,?,?,?,?,?)" ;
        Connection con = null;
        CallableStatement st = null;
        try{
            con = ConexionBD.getConnection();
            con.setAutoCommit(false);
            st = con.prepareCall(sql);
            st.setString(1, to.getTipoDocumento());
            st.setString(2, to.getDocumento().toUpperCase());
            st.setString(3, to.getNombre());
            st.setString(4, to.getTelefono());
            st.setString(5, to.getCorreo());
            st.setString(6, to.getDireccion());
            st.setString(7, Principal.getUsuarioPrincipal().getUser());
            
            long inicio = System.currentTimeMillis();
            LogService.logger.info(Principal.getUsuarioPrincipal().getUser(), "Ejecutando query "+"["+to.getNombre()+","+Principal.getUsuarioPrincipal().getUser()+"]");
            st.execute();
            long fin = System.currentTimeMillis();
            LogService.logger.info(Principal.getUsuarioPrincipal().getUser(),"Query Ejecutado ["+(fin-inicio)+"]ms");
            con.commit();
            res = true;
        }catch (SQLException ex){
            con.rollback();
            LogService.logger.error(Principal.getUsuarioPrincipal().getUser(), "Error: "+ex.getMessage());
            throw ex;
        }catch (ClassNotFoundException ex){
            con.rollback();
            LogService.logger.error(Principal.getUsuarioPrincipal().getUser(), "Error: "+ex.getMessage());
            throw ex;
        }catch (Exception ex){
            con.rollback();
            LogService.logger.error(Principal.getUsuarioPrincipal().getUser(), "Error: "+ex.getMessage());
            throw ex;
        }finally{
            
            if(st!=null){
                st.close();}
            if(con!=null){
                con.close();}
        }
       return res;
    }
    
    public boolean modificarEstatusCliente (ClienteTo to) throws ClassNotFoundException, SQLException, Exception{
        boolean res = false;
//    cambiarEstatusCliente(IN pTipoDocumento VARCHAR(255), IN pDocumento VARCHAR(255), IN pEstatus INT, IN pUsuario VARCHAR(255))
        String sql = "CALL cambiarEstatusCliente(?,?,?,?)" ;
        Connection con = null;
        CallableStatement st = null;
        try{
            con = ConexionBD.getConnection();
            con.setAutoCommit(false);
            st = con.prepareCall(sql);
            st.setString(1, to.getTipoDocumento());
            st.setString(2, to.getDocumento());
            st.setInt(3, to.getEstatus());
            st.setString(4, Principal.getUsuarioPrincipal().getUser());
            
            long inicio = System.currentTimeMillis();
            LogService.logger.info(Principal.getUsuarioPrincipal().getUser(), "Ejecutando query "+"["+to.getEstatus()+","+Principal.getUsuarioPrincipal().getUser()+"]");
            st.execute();
            long fin = System.currentTimeMillis();
            LogService.logger.info(Principal.getUsuarioPrincipal().getUser(),"Query Ejecutado ["+(fin-inicio)+"]ms");
            con.commit();
            res = true;
        }catch (SQLException ex){
            con.rollback();
            LogService.logger.error(Principal.getUsuarioPrincipal().getUser(), "Error: "+ex.getMessage());
            throw ex;
        }catch (ClassNotFoundException ex){
            con.rollback();
            LogService.logger.error(Principal.getUsuarioPrincipal().getUser(), "Error: "+ex.getMessage());
            throw ex;
        }catch (Exception ex){
            con.rollback();
            LogService.logger.error(Principal.getUsuarioPrincipal().getUser(), "Error: "+ex.getMessage());
            throw ex;
        }finally{
            
            if(st!=null){
                st.close();}
            if(con!=null){
                con.close();}
        }
       return res;
    }
}
