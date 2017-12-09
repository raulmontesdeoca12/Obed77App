/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.controlador.session;

import core.controlador.bdd.ConexionBD;
import core.logger.LogService;
import core.modelo.to.ConfiguracionesTo;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;





/**
 *
 * @author Saito
 */
public class Comunes {
    public static final String USER = "SYS";
    private final String DESNOMBREAPP = "NOMBRE_APP";
    private final String DESMAXINTENTOSIS = "MAX_INTENTOS_INICIO_SESION";
    private final String TDVARCHAR = "VARCHAR";
    private final String TDINT = "INT";
    private final String TDDATE = "DATE";
    private final String TDFLOAT = "FLOAT";
    
    
    public ConfiguracionesTo getConfiguraciones () throws ClassNotFoundException, SQLException, Exception{
        ConfiguracionesTo configuraciones = null;
        String sql = "CALL getConfiguraciones()";
        Connection con = null;
        CallableStatement st = null;
        ResultSet rs = null;
        try{
        con = ConexionBD.getConnection();
        st = con.prepareCall(sql);
        
        LogService.logger.info(Comunes.USER, "Ejecutando Query ["+USER+"]");
        st.execute();
        LogService.logger.info(Comunes.USER,"Query Ejecutado");
        rs = st.getResultSet();
        configuraciones = new ConfiguracionesTo();
        
        while (rs.next()) {
            
//            Datos de la tabla
//                descripcion VARCHAR(30) NOT NULL,
//                tipo_dato VARCHAR(30) DEFAULT NULL,
//                valor VARCHAR(255) DEFAULT NULL,
            
            String descripcion = rs.getString("descripcion");
                       
            switch(descripcion){
                case DESNOMBREAPP :
                    
                    configuraciones.setNombreApp(rs.getString("valor"));
                    LogService.logger.debug(USER, "Nombre APP: "+configuraciones.getNombreApp());
                    break;
                case DESMAXINTENTOSIS :
                    configuraciones.setMaxIntentos(Integer.parseInt(rs.getString("valor")));
                    LogService.logger.debug(USER, "Maximos Intentos de Inicio de Sesion: "+configuraciones.getMaxIntentos());
                    break;
                    
            }
           

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
        
        
        return configuraciones;
    }
    
    
   
}
