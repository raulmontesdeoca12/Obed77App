/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.controlador.session;

import core.controlador.bdd.ConexionBD;
import core.logger.LogService;
import core.modelo.to.ProductoTo;
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
public class ProductoSession {
    
     public ArrayList<ProductoTo> getProductos() throws ClassNotFoundException, SQLException{
        ProductoTo to = null;
        ArrayList<ProductoTo> tos = new ArrayList<>();
        String sql = "CALL obed.getProductos()";
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
                
//            cod INT(11) NOT NULL AUTO_INCREMENT,
//            nombre VARCHAR(255) NOT NULL,
//            descripcion VARCHAR(255) NOT NULL,
//            categoria VARCHAR(255) NOT NULL,
//            stock BIGINT(20) NOT NULL,
//            estatus INT(11) NOT NULL,

            to = new ProductoTo();
            to.setCod(rs.getLong("cod"));
            to.setNombre(rs.getString("nombre"));
            to.setDescripcion(rs.getString("descripcion"));
            to.setCategoria(rs.getInt("categoria"));
            to.setCategoriaString(rs.getString("nombre_categoria"));
            to.setStock(rs.getInt("stock"));
            to.setPrecio(rs.getDouble("precio_venta_si"));
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
     
     
     
      public ArrayList<ProductoTo> getProductosActivos() throws ClassNotFoundException, SQLException{
        ProductoTo to = null;
        ArrayList<ProductoTo> tos = new ArrayList<>();
        String sql = "CALL obed.getProductosActivos()";
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
                
//            cod INT(11) NOT NULL AUTO_INCREMENT,
//            nombre VARCHAR(255) NOT NULL,
//            descripcion VARCHAR(255) NOT NULL,
//            categoria VARCHAR(255) NOT NULL,
//            stock BIGINT(20) NOT NULL,
//            estatus INT(11) NOT NULL,

            to = new ProductoTo();
            to.setCod(rs.getLong("cod"));
            to.setNombre(rs.getString("nombre"));
            to.setDescripcion(rs.getString("descripcion"));
            to.setCategoria(rs.getInt("categoria"));
            to.setCategoriaString(rs.getString("nombre_categoria"));
            to.setStock(rs.getInt("stock"));
            to.setPrecio(rs.getDouble("precio_venta_si"));
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
    public boolean insertarProducto (ProductoTo to) throws ClassNotFoundException, SQLException, Exception{
        boolean res = false;
//        obed.insertarProducto(IN pCod INT, IN pNombre VARCHAR(255), IN pDescripcion VARCHAR(255), IN pCategoria VARCHAR(255), IN pUsuario VARCHAR(255))
        String sql = "CALL obed.insertarProducto(?,?,?,?,?)" ;
        Connection con = null;
        CallableStatement st = null;
        try{
            con = ConexionBD.getConnection();
            con.setAutoCommit(false);
            st = con.prepareCall(sql);
            st.setLong(1, to.getCod());
            st.setString(2, to.getNombre());
            st.setString(3, to.getDescripcion());
            st.setInt(4, to.getCategoria());
            st.setString(5, Principal.getUsuarioPrincipal().getUser());
            
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
    
    
    public boolean modificarProducto (ProductoTo to) throws ClassNotFoundException, SQLException, Exception{
        boolean res = false;
//        obed.modificarProducto(IN pCod INT, IN pNombre VARCHAR(255), IN pDescripcion VARCHAR(255), IN pCategoria VARCHAR(255), IN pUsuario VARCHAR(255))
        String sql = "CALL obed.modificarProducto(?,?,?,?,?)" ;
        Connection con = null;
        CallableStatement st = null;
        try{
            con = ConexionBD.getConnection();
            con.setAutoCommit(false);
            st = con.prepareCall(sql);
            st.setLong(1, to.getCod());
            st.setString(2, to.getNombre());
            st.setString(3, to.getDescripcion());
            st.setInt(4, to.getCategoria());
            st.setString(5, Principal.getUsuarioPrincipal().getUser());
            
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
    
    public boolean modificarEstatusProducto (ProductoTo to) throws ClassNotFoundException, SQLException, Exception{
        boolean res = false;
//         obed.cambiarEstatusProducto(IN pCod INT, IN pEstatus INT, IN pUsuario VARCHAR(255))
        String sql = "CALL obed.cambiarEstatusProducto(?,?,?)" ;
        Connection con = null;
        CallableStatement st = null;
        try{
            con = ConexionBD.getConnection();
            con.setAutoCommit(false);
            st = con.prepareCall(sql);
            st.setLong(1, to.getCod());
            st.setInt(2, to.getEstatus());
            st.setString(3, Principal.getUsuarioPrincipal().getUser());
            
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
