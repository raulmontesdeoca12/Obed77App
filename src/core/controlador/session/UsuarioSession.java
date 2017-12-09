/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.controlador.session;

import core.controlador.bdd.ConexionBD;
import core.controlador.principal.Roles;
import core.controlador.principal.Utilidades;
import core.logger.LogService;
import core.modelo.to.EmpleadoTo;
import core.modelo.to.UsuarioTo;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;



/**
 *
 * @author Saito
 */
public class UsuarioSession {
    /**
     * Método para obetener el usuario al iniciar sessión
     * @param user
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws Exception 
     */
    public UsuarioTo getUsuarioByUserPass (String user) throws ClassNotFoundException, SQLException, Exception{
        UsuarioTo usuario = null;
        EmpleadoTo empleado = new EmpleadoTo();
        String sql = "CALL getUsuarioByUser(?)";
        Connection con = null;
        CallableStatement st = null;
        ResultSet rs = null;
        try{
        con = ConexionBD.getConnection();
        st = con.prepareCall(sql);
        st.setString(1, user);
        
        LogService.logger.info(Comunes.USER, "Ejecutando Query ["+user+"]");
        st.execute();
        LogService.logger.info(Comunes.USER,"Query Ejecutado");
        rs = st.getResultSet();
        if (rs.next()) {
            
//            Datos de la tabla
//            user VARCHAR(25) NOT NULL,
//            password VARCHAR(255) NOT NULL,
//            status VARCHAR(255) NOT NULL,
//            intentos VARCHAR(255) NOT NULL,
//            id_t_empleado INT(11) NOT NULL
//            ultima_conexion DATE DEFAULT NULL

            usuario = new UsuarioTo();
            usuario.setUser(user);
            usuario.setPass(rs.getString("password"));
            usuario.setStatus(rs.getInt("status"));
            usuario.setIntentos(rs.getInt("intentos"));
            empleado.setCedula(rs.getString("cedula_t_empleado"));
            if(rs.getString("ultima_conexion") == null){
                usuario.setUltimaConexion( Utilidades.dateToString(new Date(), Utilidades.ddMMyyyyhhmma) );
            }else{
                usuario.setUltimaConexion(rs.getString("ultima_conexion"));
            }
            
            usuario.setEmpleado(empleado);

        }
        
        
        }catch (SQLException ex){
            LogService.logger.error(Comunes.USER, "Error: "+ex.getMessage());
            throw ex;
        }catch (ClassNotFoundException ex){ 
            LogService.logger.error(Comunes.USER, "Error: "+ex.getMessage());
            throw ex;
        }catch (Exception ex){
            LogService.logger.error(Comunes.USER, "Error: "+ex.getMessage());
            throw ex;
        }finally{
            if(rs!=null){
                rs.close();}
            if(st!=null){
                st.close();}
            if(con!=null){
                con.close();}
        }
        
        
        return usuario;
    }
    /**
     * Método para obetener los roles por usuario
     * @param user
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws Exception 
     */
    public ArrayList<Roles> getRolesByUser(String user) throws ClassNotFoundException, SQLException, Exception{
        ArrayList<Roles> listaRoles = new ArrayList<>();
        String sql = "CALL getRolesByUser(?)";
        Connection con = null;
        CallableStatement st = null;
        ResultSet rs =null;
        Roles rol = null;
        try{
        con = ConexionBD.getConnection();
        st = con.prepareCall(sql);
        st.setString(1, user);
        
        LogService.logger.info(Comunes.USER, "Ejecutando Query ["+user+"]" );
        st.execute();
        LogService.logger.info(Comunes.USER,"Query Ejecutado");
        rs = st.getResultSet();
        while(rs.next()){
            rol = new Roles();
            rol.setId(rs.getInt("id"));
            rol.setRoleName(rs.getString("descripcion"));
            listaRoles.add(rol);
        }

            
        }catch (SQLException ex){
            LogService.logger.error(Comunes.USER, "Error: "+ex.getMessage());
            throw ex;
        }catch (ClassNotFoundException ex){ 
            LogService.logger.error(Comunes.USER, "Error: "+ex.getMessage());
            throw ex;
        }catch (Exception ex){
            LogService.logger.error(Comunes.USER, "Error: "+ex.getMessage());
            throw ex;
        }finally{
            if(rs!=null){
                rs.close();}
            if(st!=null){
                st.close();}
            if(con!=null){
                con.close();}
        }
        return listaRoles;
    }
    /**
     * Método para cambiar el número de intentos de inicio de sesión de un usuario
     * @param user
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws Exception 
     */
    public boolean setIntentosUsuario (UsuarioTo user) throws ClassNotFoundException, SQLException, Exception{
        boolean res = false;
//        intentosUsuario(IN pUser VARCHAR(255), IN pIntentos INT)
        String sql = "CALL intentosUsuario(?,?)" ;
        Connection con = null;
        CallableStatement st = null;
        try{
            con = ConexionBD.getConnection();
            con.setAutoCommit(false);
            st = con.prepareCall(sql);
            st.setString(1, user.getUser());
            st.setInt(2, user.getIntentos());
            
            
            LogService.logger.info(Comunes.USER, "Ejecutando intentosUsuario "+"["+user.getIntentos()+","+user.getUser()+"]");
            st.execute();
            LogService.logger.info(Comunes.USER,"Query Ejecutado");
            con.commit();
            res = true;
        }catch (SQLException ex){
            con.rollback();
            LogService.logger.error(Comunes.USER, "Error: "+ex.getMessage());
            throw ex;
        }catch (ClassNotFoundException ex){
            con.rollback();
            LogService.logger.error(Comunes.USER, "Error: "+ex.getMessage());
            throw ex;
        }catch (Exception ex){
            con.rollback();
            LogService.logger.error(Comunes.USER, "Error: "+ex.getMessage());
            throw ex;
        }finally{
            
            if(st!=null){
                st.close();}
            if(con!=null){
                con.close();}
        }
       return res;
    }
    /**
     * Método para cambiar el estatus del usuario
     * @param userAudit usuario para la auditoría
     * @param user usuario al que se le cambiará el estatus
     * @param status código del estatus InicioSessionHelper.StatusXXX
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws Exception 
     */
    public boolean setStatusUsuario (String userAudit, String user, int status) throws ClassNotFoundException, SQLException, Exception{
        boolean res = false;
        String sql = "CALL statusUsuario(?,?)" ;
        Connection con = null;
        CallableStatement st = null;
        try{
            con = ConexionBD.getConnection();
            con.setAutoCommit(false);
            st = con.prepareCall(sql);
            st.setString(1, user);
            st.setInt(2, status);
            
            
            LogService.logger.info(userAudit, "Ejecutando Query "+"["+status+","+user+"]");
            st.execute();
            LogService.logger.info(userAudit,"Query Ejecutado");
            con.commit();
            res = true;
        }catch (SQLException ex){
            LogService.logger.error(Comunes.USER, "Error: "+ex.getMessage());
            con.rollback();
            throw ex;
        }catch (ClassNotFoundException ex){
            LogService.logger.error(Comunes.USER, "Error: "+ex.getMessage());
            con.rollback();
            throw ex;
        }catch (Exception ex){
            LogService.logger.error(Comunes.USER, "Error: "+ex.getMessage());
            con.rollback();
            throw ex;
        }finally{
            
            if(st!=null){
                st.close();}
            if(con!=null){
                con.close();}
        }
       return res;
    }
    
    public boolean setConexion (String user) throws ClassNotFoundException, SQLException, Exception{
        boolean res = false;
        String sql = "CALL conexionUsuario(?)" ;
        Connection con = null;
        CallableStatement st = null;
        try{
            con = ConexionBD.getConnection();
            con.setAutoCommit(false);
            st = con.prepareCall(sql);
            st.setString(1, user);
            
            LogService.logger.info(Comunes.USER, "Ejecutando Query "+"["+user+"]");
            st.execute();
            LogService.logger.info(Comunes.USER,"Query Ejecutado");
            con.commit();
            res = true;
        }catch (SQLException ex){
            LogService.logger.error(Comunes.USER, "Error: "+ex.getMessage());
            con.rollback();
            throw ex;
        }catch (ClassNotFoundException ex){
            LogService.logger.error(Comunes.USER, "Error: "+ex.getMessage());
            con.rollback();
            throw ex;
        }catch (Exception ex){
            LogService.logger.error(Comunes.USER, "Error: "+ex.getMessage());
            con.rollback();
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