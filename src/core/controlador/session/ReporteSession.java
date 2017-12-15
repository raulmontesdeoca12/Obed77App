/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.controlador.session;

import core.controlador.bdd.ConexionBD;
import core.controlador.principal.Utilidades;
import core.logger.LogService;
import core.modelo.to.ReporteVentasMensualTo;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import obed77.Principal;



/**
 *
 * @author Saito
 */
public class ReporteSession {
    
    
    public List<ReporteVentasMensualTo> getReportePorMes(int anio) throws ClassNotFoundException, SQLException, Exception{
        
        List<ReporteVentasMensualTo> tos = new ArrayList<>();
        tos.add(new ReporteVentasMensualTo(1, "ENERO"));
        tos.add(new ReporteVentasMensualTo(2, "FEBRERO"));
        tos.add(new ReporteVentasMensualTo(3, "MARZO"));
        tos.add(new ReporteVentasMensualTo(4, "ABRIL"));
        tos.add(new ReporteVentasMensualTo(5, "MAYO"));
        tos.add(new ReporteVentasMensualTo(6, "JUNIO"));
        tos.add(new ReporteVentasMensualTo(7, "JULIO"));
        tos.add(new ReporteVentasMensualTo(8, "AGOSTO"));
        tos.add(new ReporteVentasMensualTo(9, "SEPTIEMBRE"));
        tos.add(new ReporteVentasMensualTo(10, "OCTUBRE"));
        tos.add(new ReporteVentasMensualTo(11, "NOVIEMBRE"));
        tos.add(new ReporteVentasMensualTo(12, "DICIEMBRE"));
        
//        obed.getVentasPorMes(IN pFechaInicio DATE, IN pFechaFin DATE, OUT pMonto DECIMAL)
        String sql = "CALL obed.getVentasPorMes(?,?,?)";
        Connection con = null;
        CallableStatement st = null;
        try{
            con = ConexionBD.getConnection();
            for(ReporteVentasMensualTo to : tos){
        
                    st = con.prepareCall(sql);
                    Date fIni = Utilidades.getPrimerDiaMes(anio, to.getnMes());
                    Date fFin = Utilidades.getUltimoDiaMes(anio, to.getnMes());
                    
                    st.setDate(1, new java.sql.Date(fIni.getTime()));
                    st.setDate(2, new java.sql.Date(fFin.getTime()));
                    st.registerOutParameter(3, Types.DECIMAL);
                    long inicio = System.currentTimeMillis();
                    System.out.println(Utilidades.dateToString(fIni, "dd/MM/YYYY")+ " "+ Utilidades.dateToString(fFin, "dd/MM/YYYY"));
                    LogService.logger.info(Principal.getUsuarioPrincipal().getUser(), "Ejecutando query ("+anio+","+to.getMes()+")");
                    st.execute();
                    long fin = System.currentTimeMillis();
                    LogService.logger.info(Principal.getUsuarioPrincipal().getUser(), "Query Ejecutado ["+(fin-inicio)+"]ms");
                    to.setMonto(st.getDouble(3));
                    LogService.logger.info(Principal.getUsuarioPrincipal().getUser(), "Obtenido monto ["+to.getMonto()+"]");
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
            if(st!=null){
                st.close();}
            if(con!=null){
                con.close();}
        }

        return tos;
    }
    
}
