/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.controlador.helper;

import core.controlador.session.ReporteSession;
import core.logger.LogService;
import core.modelo.to.ReporteVentasMensualTo;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;
import obed77.Principal;
import obed77.views.dialogosComunes.JOptionDialog;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;



/**
 *
 * @author Saito
 */
public class GenerarReportesExcelHelper {
    JFileChooser fc = new JFileChooser();
    
    public void generar(int anio){
        int resp=fc.showSaveDialog(null);
        String scrFilePath = "src/Files/ReporteVentasMes.xls";
        String scrDestPath ="";
        if(resp==JFileChooser.APPROVE_OPTION)
        {
            LogService.logger.info(Principal.getUsuarioPrincipal().getUser(), "Inicia Generacion de reporte");
            try {
                ReporteSession session = new ReporteSession();
                List<ReporteVentasMensualTo> list = session.getReportePorMes(anio);
                scrDestPath = String.valueOf(fc.getSelectedFile ())+".xls";
                 Map<String, List<ReporteVentasMensualTo>> beanParams = new HashMap<String, List<ReporteVentasMensualTo>>();
                 beanParams.put("reporte", list);
                 
                 XLSTransformer former = new XLSTransformer() ;
                 former.transformXLS(scrFilePath, beanParams, scrDestPath);
            } catch (ClassNotFoundException ex) {
                core.logger.LogService.logger.error(Principal.getUsuarioPrincipal().getUser(), "Error: " + ex.getMessage());
                obed77.views.dialogosComunes.JOptionDialog.showMessageDialog(core.controlador.principal.ErroresMap.MessageError(9999, ""), "Error", 2);
            } catch (java.sql.SQLException ex) {
                core.logger.LogService.logger.error(Principal.getUsuarioPrincipal().getUser(), "Error: " + ex.getMessage());
                obed77.views.dialogosComunes.JOptionDialog.showMessageDialog(core.controlador.principal.ErroresMap.MessageError(ex.getErrorCode(), ""), "Error", 2);
            } catch (ParsePropertyException ex) {
                Logger.getLogger(GenerarReportesExcelHelper.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(GenerarReportesExcelHelper.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidFormatException ex) {
                Logger.getLogger(GenerarReportesExcelHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JOptionDialog.showMessageDialog("Archivo generado","Reporte",2);
        }else if (resp==JFileChooser.CANCEL_OPTION){
            JOptionDialog.showMessageDialog("Ha cancelado la operación","Reporte",2);
        }
        
    }
}
