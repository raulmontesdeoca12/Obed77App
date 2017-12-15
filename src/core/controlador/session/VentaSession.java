/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.controlador.session;

import core.controlador.bdd.ConexionBD;
import core.logger.LogService;
import core.modelo.to.ClienteTo;
import core.modelo.to.DetalleVentaTo;
import core.modelo.to.ProductoTo;
import core.modelo.to.VentaTo;
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
public class VentaSession {
    
     public ArrayList<VentaTo> getVentas() throws ClassNotFoundException, SQLException{
        VentaTo to = null;
        ArrayList<VentaTo> tos = new ArrayList<>();
        String sql = "CALL obed.getVentas()";
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
                
//            ven.documento_venta, 
//            ven.referencia, 
//            ven.fecha, 
//            cli.tipo_t_documento, 
//            cli.documento, 
//            cli.nombre,
//            ven.user_venta, 
//            ven.estatus   

            to = new VentaTo();
            to.setDocumento(rs.getString("documento_venta"));
            to.setReferencia(rs.getString("referencia"));
            to.setFecha(rs.getDate("fecha"));
            ClienteTo pTo = new ClienteTo();
            pTo.setTipoDocumento(rs.getString("tipo_t_documento"));
            pTo.setDocumento(rs.getString("documento"));
            pTo.setNombre(rs.getString("nombre"));
            to.setCliente(pTo);
            to.setUsuario(rs.getString("user_venta"));
            to.setEstatus(rs.getInt("estatus"));
            LogService.logger.info(Principal.getUsuarioPrincipal().getUser(), "Obteniendo total de la venta");
            to.setTotalVenta(getTotalVenta(to.getDocumento(),to.getReferencia()));
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
     
     
      public ArrayList<DetalleVentaTo> getDetallesVentas(String documento, String referencia) throws ClassNotFoundException, SQLException{
        DetalleVentaTo to = null;
        ArrayList<DetalleVentaTo> tos = new ArrayList<>();
        //obed.getDetallesVenta(IN pDoc VARCHAR(255), IN pReferencia VARCHAR(255))
        String sql = "CALL obed.getDetallesVenta(?,?)";
        Connection con = null;
        CallableStatement st = null;
        ResultSet rs = null;
        try{
            con = ConexionBD.getConnection();
            st = con.prepareCall(sql);
            st.setString(1, documento);
            st.setString(2, referencia);
            long inicio = System.currentTimeMillis();
            LogService.logger.info(Principal.getUsuarioPrincipal().getUser(), "Ejecutando query("+documento+","+referencia+")");
            st.execute();
            long fin = System.currentTimeMillis();
            LogService.logger.info(Principal.getUsuarioPrincipal().getUser(), "Query Ejecutado ["+(fin-inicio)+"]ms");
            rs = st.getResultSet();
            while(rs.next()){
                
//            det.id, 
//            prod.cod,
//            prod.nombre, 
//            det.cantidad, 
//            det.precio, 
//            det.descuento, 
//            det.impuesto, 
//            det.sub_total
            ProductoTo prod = new ProductoTo();
            to = new DetalleVentaTo();
            to.setId(rs.getLong("id"));
            prod.setCod(rs.getLong("cod"));
            prod.setNombre(rs.getString("nombre"));
            to.setProducto(prod);
            to.setCantidad(rs.getInt("cantidad"));
            to.setPrecio(rs.getDouble("precio"));
            to.setDescuento(rs.getFloat("descuento"));
            to.setImpuesto(rs.getFloat("impuesto"));
            to.setSubTotal(rs.getDouble("sub_total"));
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
      
       public double getTotalVenta(String documento, String referencia) throws ClassNotFoundException, SQLException{
        // obed.getTotalVenta(IN pDoc VARCHAR(255), IN pReferencia VARCHAR(255), OUT pTotal DECIMAL)
        double valor = 0;
        String sql = "CALL obed.getTotalVenta(?,?,?)";
        Connection con = null;
        CallableStatement st = null;
        ResultSet rs = null;
        try{
            con = ConexionBD.getConnection();
            st = con.prepareCall(sql);
            st.setString(1, documento);
            st.setString(2, referencia);
            st.registerOutParameter(3, Types.DECIMAL);
            long inicio = System.currentTimeMillis();
            LogService.logger.info(Principal.getUsuarioPrincipal().getUser(), "Ejecutando query("+documento+","+referencia+")");
            st.execute();
            long fin = System.currentTimeMillis();
            LogService.logger.info(Principal.getUsuarioPrincipal().getUser(), "Query Ejecutado ["+(fin-inicio)+"]ms");
            
            valor= st.getDouble(3);
            
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
     
     
     
     
    public boolean insertarVenta (VentaTo to) throws ClassNotFoundException, SQLException, Exception{
        boolean res = false;
//        obed.insertarVenta
//        IN pDocVenta VARCHAR(255), 
//        IN pReferencia VARCHAR(255), 
//        IN pFecha DATE, 
//        IN pTipoDocCli VARCHAR(255), 
//        IN pDocCli VARCHAR(255), 
//        IN pUsuarioVenta VARCHAR(255), 
//        IN pUsuarioInsert VARCHAR(255)
        String sql = "CALL obed.insertarVenta(?,?,?,?,?,?,?)" ;
        Connection con = null;
        CallableStatement st = null;
        
//        obed.insertarDetalleVenta
//        IN pDocVenta VARCHAR(255), 
//        IN pReferencia VARCHAR(255), 
//        IN pCodProd BIGINT, 
//        IN pCantidad INT, 
//        IN pPrecio DECIMAL, 
//        IN pDescuento DECIMAL, 
//        IN pImpuesto DECIMAL, 
//        IN pSubTotal DECIMAL
        String sqlDet = "CALL obed.insertarDetalleVenta(?,?,?,?,?,?,?,?)";
        CallableStatement stDet = null;
        try{
            con = ConexionBD.getConnection();
            con.setAutoCommit(false);
            st = con.prepareCall(sql);
            st.setString(1,to.getDocumento());
            st.setString(2, to.getReferencia());
            st.setDate(3, new java.sql.Date(to.getFecha().getTime()) );
            st.setString(4, to.getCliente().getTipoDocumento());
            st.setString(5, to.getCliente().getDocumento());
            st.setString(6, to.getUsuario());
            st.setString(7, Principal.getUsuarioPrincipal().getUser());
            
            
            long inicio = System.currentTimeMillis();
            LogService.logger.info(Principal.getUsuarioPrincipal().getUser(), "Ejecutando query "+"["+to.toString()+","+Principal.getUsuarioPrincipal().getUser()+"]");
            st.executeUpdate();
            long fin = System.currentTimeMillis();
            LogService.logger.info(Principal.getUsuarioPrincipal().getUser(),"Query Ejecutado ["+(fin-inicio)+"]ms");
            
            
            LogService.logger.info(Principal.getUsuarioPrincipal().getUser(), "Iniciando insert de detalles...");

            for(DetalleVentaTo toDet : to.getDetalles()){         
                stDet = con.prepareCall(sqlDet);
                stDet.setString(1, to.getDocumento());
                stDet.setString(2, to.getReferencia());
                stDet.setLong(3, toDet.getProducto().getCod());
                stDet.setInt(4, toDet.getCantidad());
                stDet.setDouble(5, toDet.getPrecio());
                stDet.setDouble(6, toDet.getDescuento());
                stDet.setFloat(7, toDet.getImpuesto());
                stDet.setDouble(8, toDet.getSubTotal());
                
                
                long inicioDet = System.currentTimeMillis();
                LogService.logger.info(Principal.getUsuarioPrincipal().getUser(), "Ejecutando query de detalle de pedido ["+to.getDocumento()+","+to.getReferencia()+","+toDet.getProducto().getCod()+"]");
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

 public void anularVenta(String documento, String referencia) throws ClassNotFoundException, SQLException{
        //obed.anularVenta(IN pDoc VARCHAR(255), IN pReferencia VARCHAR(255), IN pUsuario VARCHAR(255))
        String sql = "CALL obed.anularVenta(?,?,?)";
        Connection con = null;
        CallableStatement st = null;
        ResultSet rs = null;
        try{
            con = ConexionBD.getConnection();
            st = con.prepareCall(sql);
            st.setString(1, documento);
            st.setString(2, referencia);
            st.setString(3, Principal.getUsuarioPrincipal().getUser());
            long inicio = System.currentTimeMillis();
            LogService.logger.info(Principal.getUsuarioPrincipal().getUser(), "Ejecutando query["+documento+","+referencia+"]");
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
