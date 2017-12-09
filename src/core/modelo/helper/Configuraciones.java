/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.modelo.helper;

import core.controlador.session.Comunes;
import core.logger.LogService;
import core.modelo.to.ConfiguracionesTo;
import java.sql.SQLException;



/**
 *
 * @author Saito
 */
public class Configuraciones {
    public static String nombreApp;
    public static int maxIntentos;

    static{
        try {
            LogService.logger.info(Comunes.USER, "Cargando configuraciones");
            Comunes session = new Comunes();
            ConfiguracionesTo config = session.getConfiguraciones();
            nombreApp = config.getNombreApp();
            maxIntentos = config.getMaxIntentos();
        } catch (SQLException ex) {
            LogService.logger.error(Comunes.USER, "Error: "+ex.getMessage());
        } catch (Exception ex) {
            LogService.logger.error(Comunes.USER, "Error: "+ex.getMessage());
        }
    }
    
    
    
    
}
