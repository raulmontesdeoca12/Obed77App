/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.controlador.principal;

import core.modelo.helper.Configuraciones;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;



/**
 *
 * @author Saito
 */
public class Utilidades {
    public static final String ddMMyyyy = "dd/MM/yyyy";
    public static final String ddMMyyyyhhmma = "dd/MM/yyyy hh:mm a";
    
    public static String dateToString (Date fecha, String formato){
        SimpleDateFormat formatter = new SimpleDateFormat(formato);
        return formatter.format(fecha);
    }
    
    public static Date stringToDate (String fecha, String formato) throws ParseException{
        SimpleDateFormat formatter = new SimpleDateFormat(formato);
        return formatter.parse(fecha);
    }
    
    public static Color getColorEntered(){
        return new Color(174,214,241);
    }
    
    public static Color getColorNormalMenu(){
        return new Color(255,255,255);
    }
    public static Color getColorSelectedMenu(){
        return new Color(229,232,232);
    }
    
    public static Dimension getMinimumDimension(){
        return new Dimension(100,25);
    }
    
    public static void mostrarDialogoInfoCodError(Component parent, int errorCode, String text){
        JOptionPane.showMessageDialog(parent,ErroresMap.MessageError(errorCode, text),Configuraciones.nombreApp, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void mostrarDialogoWarn(Component parent, String mensaje){
        JOptionPane.showMessageDialog(parent,mensaje,"Error", JOptionPane.WARNING_MESSAGE);
    }
}
