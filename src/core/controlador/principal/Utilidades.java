/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.controlador.principal;

import java.awt.Color;
import java.awt.Dimension;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



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
    
    public static double redondearDosDecimales(double valor){
        return (double)Math.round(valor * 100d) / 100d;
    }
    
    public static Date getPrimerDiaMes(int anio,int mes) throws ParseException{

    Calendar calendario=Calendar.getInstance();
    calendario.set(anio, mes-1, 1);
    String fecha = calendario.getActualMinimum(Calendar.DAY_OF_MONTH)+"/"+mes+"/"+anio;
    return  stringToDate(fecha, "dd/MM/yyyy");

    }
    
    public static Date getUltimoDiaMes(int anio,int mes) throws ParseException{

    Calendar calendario=Calendar.getInstance();
    calendario.set(anio, mes-1, 1);
    String fecha = calendario.getActualMaximum(Calendar.DAY_OF_MONTH)+"/"+mes+"/"+anio;
    return  stringToDate(fecha, "dd/MM/yyyy");

    }
}
