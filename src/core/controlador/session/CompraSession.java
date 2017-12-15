/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.controlador.session;

import core.controlador.bdd.ConexionBD;
import core.logger.LogService;
import core.modelo.to.CompraTo;
import core.modelo.to.DetalleCompraTo;
import core.modelo.to.ProductoTo;
import core.modelo.to.ProveedorTo;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import obed77.Principal;



/**
 *
 * @author Saito
 */
public class CompraSession {
    
     public ArrayList<CompraTo> getCompras() throws ClassNotFoundException, SQLException{
        CompraTo to = null;
        ArrayList<CompraTo> tos = new ArrayList<>();
        String sql = "CALL obed.getCompras()";
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
                
//            com.cod, 
//            com.fecha,
//            com.user_compra,
//            com.documento_compra,
//            com.referencia, 
//            com.estatus,
//            prov.tipo_t_documento, 
//            prov.documento, 
//            prov.nombre 

            to = new CompraTo();
            to.setCod(rs.getLong("cod"));
            to.setFecha(rs.getDate("fecha"));
            ProveedorTo pTo = new ProveedorTo();
            pTo.setTipoDocumento(rs.getString("tipo_t_documento"));
            pTo.setDocumento(rs.getString("documento"));
            pTo.setNombre(rs.getString("nombre"));
            to.setProveedor(pTo);
            to.setUsuario(rs.getString("user_compra"));
            to.setDocumento(rs.getString("documento_compra"));
            to.setReferencia(rs.getString("referencia"));
            to.setEstatus(rs.getInt("estatus"));
            LogService.logger.info(Principal.getUsuarioPrincipal().getUser(), "Obteniendo total de la compra");
            to.setTotalCompra(getTotalCompra(to.getCod()));
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
     
     
      public ArrayList<DetalleCompraTo> getDetallesCompras(long codCompra) throws ClassNotFoundException, SQLException{
        DetalleCompraTo to = null;
        ArrayList<DetalleCompraTo> tos = new ArrayList<>();
        //obed.getDetallesCompra(IN pCompra BIGINT)
        String sql = "CALL obed.getDetallesCompra(?)";
        Connection con = null;
        CallableStatement st = null;
        ResultSet rs = null;
        try{
            con = ConexionBD.getConnection();
            st = con.prepareCall(sql);
            st.setLong(1, codCompra);
            long inicio = System.currentTimeMillis();
            LogService.logger.info(Principal.getUsuarioPrincipal().getUser(), "Ejecutando query("+codCompra+")");
            st.execute();
            long fin = System.currentTimeMillis();
            LogService.logger.info(Principal.getUsuarioPrincipal().getUser(), "Query Ejecutado ["+(fin-inicio)+"]ms");
            rs = st.getResultSet();
            while(rs.next()){
                
//            det.id, 
//            prod.nombre, 
//            prod.cod,
//            det.cantidad, 
//            det.costo, 
//            det.impuesto, 
//            det.sub_total,
//            det.precio
            ProductoTo prod = new ProductoTo();
            to = new DetalleCompraTo();
            to.setId(rs.getLong("id"));
            prod.setCod(rs.getLong("cod"));
            prod.setNombre(rs.getString("nombre"));
            to.setProducto(prod);
            to.setCantidad(rs.getInt("cantidad"));
            to.setCosto(rs.getDouble("costo"));
            to.setImpuesto(rs.getFloat("impuesto"));
            to.setSubTotal(rs.getDouble("sub_total"));
            to.setPrecio(rs.getDouble("precio"));
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
      
       public double getTotalCompra(long codCompra) throws ClassNotFoundException, SQLException{
        // obed.getTotalCompra(IN pCodCompra BIGINT, OUT pTotal DECIMAL)
        double valor = 0;
        String sql = "CALL obed.getTotalCompra(?,?)";
        Connection con = null;
        CallableStatement st = null;
        ResultSet rs = null;
        try{
            con = ConexionBD.getConnection();
            st = con.prepareCall(sql);
            st.setLong(1, codCompra);
            st.registerOutParameter(2, Types.DECIMAL);
            long inicio = System.currentTimeMillis();
            LogService.logger.info(Principal.getUsuarioPrincipal().getUser(), "Ejecutando query("+codCompra+")");
            st.execute();
            long fin = System.currentTimeMillis();
            LogService.logger.info(Principal.getUsuarioPrincipal().getUser(), "Query Ejecutado ["+(fin-inicio)+"]ms");
            
            valor= st.getDouble(2);
            
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

        
        return valor;
    }
     
     
     
     
    public boolean insertarCompra (CompraTo to) throws ClassNotFoundException, SQLException, Exception{
        boolean res = false;
//        obed.insertarCompra
//        IN pFecha DATE, IN pTipoDocProv VARCHAR(255), IN pDocProv VARCHAR(255), 
//        IN pUsuarioCompra VARCHAR(255), IN pDocCompra VARCHAR(255), IN pReferencia VARCHAR(255), 
//        IN pUsuarioInsert VARCHAR(255))
        String sql = "CALL obed.insertarCompra(?,?,?,?,?,?,?,?)" ;
        Connection con = null;
        CallableStatement st = null;
        String sqlDet = "CALL obed.insertarDetalleCompra(?,?,?,?,?,?,?)";
        CallableStatement stDet = null;
        try{
            con = ConexionBD.getConnection();
            con.setAutoCommit(false);
            st = con.prepareCall(sql);
            st.setDate(1, new java.sql.Date(to.getFecha().getTime()) );
            st.setString(2, to.getProveedor().getTipoDocumento());
            st.setString(3, to.getProveedor().getDocumento());
            st.setString(4, to.getUsuario());
            st.setString(5, to.getDocumento());
            st.setString(6, to.getReferencia());
            st.setString(7, Principal.getUsuarioPrincipal().getUser());
            st.registerOutParameter(8,Types.BIGINT);
            
            
            long inicio = System.currentTimeMillis();
            LogService.logger.info(Principal.getUsuarioPrincipal().getUser(), "Ejecutando query "+"["+to.getProveedorCompleto()+","+Principal.getUsuarioPrincipal().getUser()+"]");
            st.executeUpdate();
            long fin = System.currentTimeMillis();
            LogService.logger.info(Principal.getUsuarioPrincipal().getUser(),"Query Ejecutado ["+(fin-inicio)+"]ms");
            to.setCod(st.getLong(8));
            
            
            LogService.logger.info(Principal.getUsuarioPrincipal().getUser(), "Iniciando insert de detalles...");
//            
//            obed.insertarDetalleCompra
//            IN pCodCompra BIGINT, 
//            IN pCodProd BIGINT, 
//            IN pCantidad INT, 
//            IN pCosto DECIMAL, 
//            IN pImpuesto DECIMAL, 
//            IN pSubTotal DECIMAL, 
//            IN pVenta DECIMAL

            for(DetalleCompraTo toDet : to.getDetalles()){
                
                stDet = con.prepareCall(sqlDet);
                stDet.setLong(1, to.getCod());
                stDet.setLong(2, toDet.getProducto().getCod());
                stDet.setInt(3, toDet.getCantidad());
                stDet.setDouble(4, toDet.getCosto());
                stDet.setFloat(5, toDet.getImpuesto());
                stDet.setDouble(6, toDet.getSubTotal());
                stDet.setDouble(7, toDet.getPrecio());
                
                long inicioDet = System.currentTimeMillis();
                LogService.logger.info(Principal.getUsuarioPrincipal().getUser(), "Ejecutando query de detalle de pedido ["+to.getCod()+","+toDet.getProducto().getCod()+"]");
                stDet.execute();
                long finDet = System.currentTimeMillis();
                LogService.logger.info(Principal.getUsuarioPrincipal().getUser(),"Query Ejecutado ["+(finDet-inicioDet)+"]ms");
            }
            
            con.commit();
            res = true;
        }catch (SQLException ex){
            con.rollback();
            LogService.logger.error(Principal.getUsuarioPrincipal().getUser(), "Error "+ex.getErrorCode()+": "+ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }catch (ClassNotFoundException ex){
            con.rollback();
            LogService.logger.error(Principal.getUsuarioPrincipal().getUser(), "Error: "+ex.getMessage());
             ex.printStackTrace();
            throw ex;
        }catch (Exception ex){
            con.rollback();
            LogService.logger.error(Principal.getUsuarioPrincipal().getUser(), "Error: "+ex.getMessage());
             ex.printStackTrace();
            throw ex;
        }finally{
            if(stDet!=null){
               stDet.close();}
            if(st!=null){
                st.close();}
            if(con!=null){
               con.close();}
            
        }
       return res;
    }

 public void anularCompra(long codCompra) throws ClassNotFoundException, SQLException{
        
        String sql = "CALL obed.anularCompra(?,?)";
        Connection con = null;
        CallableStatement st = null;
        ResultSet rs = null;
        try{
            con = ConexionBD.getConnection();
            st = con.prepareCall(sql);
            st.setLong(1, codCompra);
            st.setString(2, Principal.getUsuarioPrincipal().getUser());
            long inicio = System.currentTimeMillis();
            LogService.logger.info(Principal.getUsuarioPrincipal().getUser(), "Ejecutando query["+codCompra+"]");
            st.execute();
            long fin = System.currentTimeMillis();
            LogService.logger.info(Principal.getUsuarioPrincipal().getUser(), "Query Ejecutado ["+(fin-inicio)+"]ms");
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

    }
}
