/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.controlador.session;

import core.controlador.bdd.ConexionBD;
import core.logger.LogService;
import core.modelo.to.CategoriaTo;
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
public class CategoriaSession {
    
     public ArrayList<CategoriaTo> getCategorias() throws ClassNotFoundException, SQLException{
        CategoriaTo to = null;
        ArrayList<CategoriaTo> tos = new ArrayList<>();
        String sql = "CALL obed.getCategorias()";
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
                
//                id INT(11) NOT NULL AUTO_INCREMENT
//                descripcion VARCHAR(255) NOT NULL
//                estatus INT(11) NOT NULL DEFAULT 1

            to = new CategoriaTo();
            to.setId(rs.getInt("id"));
            to.setNombre(rs.getString("nombre"));
            to.setDescripcion(rs.getString("descripcion"));
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
     
     
     
     
    public boolean insertarCategoria (CategoriaTo to) throws ClassNotFoundException, SQLException, Exception{
        boolean res = false;
//        insertarCategoria(IN pDescripcion VARCHAR(255), IN pUsuario VARCHAR(255))
        String sql = "CALL insertarCategoria(?,?,?)" ;
        Connection con = null;
        CallableStatement st = null;
        try{
            con = ConexionBD.getConnection();
            con.setAutoCommit(false);
            st = con.prepareCall(sql);
            st.setString(1, to.getNombre());
            st.setString(2, to.getDescripcion().toUpperCase());
            st.setString(3, Principal.getUsuarioPrincipal().getUser());
            
            long inicio = System.currentTimeMillis();
            LogService.logger.info(Principal.getUsuarioPrincipal().getUser(), "Ejecutando query "+"["+to.getNombre()+","+to.getDescripcion()+","+Principal.getUsuarioPrincipal().getUser()+"]");
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
    
    
    public boolean modificarCategoria (CategoriaTo to) throws ClassNotFoundException, SQLException, Exception{
        boolean res = false;
//      modificarCategoria(IN pId INT, IN pDescripcion VARCHAR(255), IN pUsuario VARCHAR(255))
        String sql = "CALL modificarCategoria(?,?,?,?)" ;
        Connection con = null;
        CallableStatement st = null;
        try{
            con = ConexionBD.getConnection();
            con.setAutoCommit(false);
            st = con.prepareCall(sql);
            st.setInt(1, to.getId());
            st.setString(2, to.getNombre());
            st.setString(3, to.getDescripcion());
            st.setString(4, Principal.getUsuarioPrincipal().getUser());
            
            long inicio = System.currentTimeMillis();
            LogService.logger.info(Principal.getUsuarioPrincipal().getUser(), "Ejecutando query "+"["+to.getId()+","+to.getNombre()+","+to.getDescripcion()+","+Principal.getUsuarioPrincipal().getUser()+"]");
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
    
    public boolean modificarEstatusCategoria (CategoriaTo to) throws ClassNotFoundException, SQLException, Exception{
        boolean res = false;
//    cambiarEstatusCategoria(IN pId INT, IN pEstatus INT, IN pUser VARCHAR(255))
        String sql = "CALL cambiarEstatusCategoria(?,?,?)" ;
        Connection con = null;
        CallableStatement st = null;
        try{
            con = ConexionBD.getConnection();
            con.setAutoCommit(false);
            st = con.prepareCall(sql);
            st.setInt(1, to.getId());
            st.setInt(2, to.getEstatus());
            st.setString(3, Principal.getUsuarioPrincipal().getUser());
            
            long inicio = System.currentTimeMillis();
            LogService.logger.info(Principal.getUsuarioPrincipal().getUser(), "Ejecutando query "+"["+to.getId()+","+to.getEstatus()+","+Principal.getUsuarioPrincipal().getUser()+"]");
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
