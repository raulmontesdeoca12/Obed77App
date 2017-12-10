/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obed77.views.dialogosComunes;

import java.awt.Dialog;
import java.awt.Frame;



/**
 *
 * @author Saito
 */
public class JOptionDialog {
    //******** OPCIONES DE BOTONES
    public static final int SI_NO_OPTION = 0;
    public static final int SI_NO_CANCELAR_OPTION = 1;
    public static final int ACEPTAR_CANCELAR_OPTION = 2;
    public static final int ACEPTAR_OPTION = 3;
    public static final int CANCELAR_OPTION = 4;
    
    //******* OPCIONES DE ICONOS
    public static final int ADVERTENCIA_ICON = 1;
    public static final int INFORMACION_ICON = 2;
    public static final int PREGUNTA_ICON = 3;
    public static final int ERROR_ICON = 4;
    /**
     * Crear dialogo de mensaje
     * Usar las variables estaticas de la clase para llenar los INT
     * @param parent
     * @param modal
     * @param title
     * @param message
     * @param botones
     * @param icon 
     */
    public static void showMessageDialog(Frame parent, String message,String title, int icon){
        boolean modal = true;
       
        switch(icon){
            case 1: 
                dialogWarnF diagW = new dialogWarnF(parent, modal);
                diagW.setTitulo(title);
                diagW.setTexto(message);
                diagW.setBotones(ACEPTAR_OPTION);
                diagW.repaint();
                diagW.setVisible(true);
                break;
            case 2:
                dialogInfoF diagI = new dialogInfoF(parent, modal);
                diagI.setTitulo(title);
                diagI.setTexto(message);
                diagI.setBotones(ACEPTAR_OPTION);
                diagI.repaint();
                diagI.setVisible(true);
                break;
            case 3:
                dialogQuestF diagQ = new dialogQuestF(parent, modal);
                diagQ.setTitulo(title);
                diagQ.setTexto(message);
                diagQ.setBotones(ACEPTAR_OPTION);
                diagQ.repaint();
                diagQ.setVisible(true);
                break;
            case 4:
                dialogErrorF diagF = new dialogErrorF(parent, modal);
                diagF.setTitulo(title);
                diagF.setTexto(message);
                diagF.setBotones(ACEPTAR_OPTION);
                diagF.repaint();
                diagF.setVisible(true);
        }
        
    }
    
    public static void showMessageDialog(Dialog parent, String message,String title, int icon){
        boolean modal = true;
       
        switch(icon){
            case 1: 
                dialogWarnD diagW = new dialogWarnD(parent, modal);
                diagW.setTitulo(title);
                diagW.setTexto(message);
                diagW.setBotones(ACEPTAR_OPTION);
                diagW.repaint();
                diagW.setVisible(true);
                break;
            case 2:
                dialogInfoD diagI = new dialogInfoD(parent, modal);
                diagI.setTitulo(title);
                diagI.setTexto(message);
                diagI.setBotones(ACEPTAR_OPTION);
                diagI.repaint();
                diagI.setVisible(true);
                break;
            case 3:
                dialogQuestD diagQ = new dialogQuestD(parent, modal);
                diagQ.setTitulo(title);
                diagQ.setTexto(message);
                diagQ.setBotones(ACEPTAR_OPTION);
                diagQ.repaint();
                diagQ.setVisible(true);
                break;
            case 4:
                dialogErrorD diagF = new dialogErrorD(parent, modal);
                diagF.setTitulo(title);
                diagF.setTexto(message);
                diagF.setBotones(ACEPTAR_OPTION);
                diagF.repaint();
                diagF.setVisible(true);
        }
        
    }
    
    
    public static void showMessageDialog(String message,String title, int icon){
        boolean modal = true;
       
        switch(icon){
            case 1: 
                dialogWarnD diagW = new dialogWarnD(null, modal);
                diagW.setTitulo(title);
                diagW.setTexto(message);
                diagW.setBotones(ACEPTAR_OPTION);
                diagW.repaint();
                diagW.setVisible(true);
                break;
            case 2:
                dialogInfoD diagI = new dialogInfoD(null, modal);
                diagI.setTitulo(title);
                diagI.setTexto(message);
                diagI.setBotones(ACEPTAR_OPTION);
                diagI.repaint();
                diagI.setVisible(true);
                break;
            case 3:
                dialogQuestD diagQ = new dialogQuestD(null, modal);
                diagQ.setTitulo(title);
                diagQ.setTexto(message);
                diagQ.setBotones(ACEPTAR_OPTION);
                diagQ.repaint();
                diagQ.setVisible(true);
                break;
            case 4:
                dialogErrorD diagF = new dialogErrorD(null, modal);
                diagF.setTitulo(title);
                diagF.setTexto(message);
                diagF.setBotones(ACEPTAR_OPTION);
                diagF.repaint();
                diagF.setVisible(true);
        }
        
    }
    
    
    public static int showConfirmDialog(Frame parent, String message,String title,int botones){
        boolean modal = true;
        dialogQuestF diagQ = new dialogQuestF(parent, modal);
        diagQ.setTitulo(title);
        diagQ.setTexto(message);
        diagQ.setBotones(botones);
        diagQ.repaint();
        diagQ.setVisible(true);
        return diagQ.resultado;
        
    }
    
    public static int showConfirmDialog(Dialog parent, String message,String title,int botones){
        boolean modal = true;
        dialogQuestD diagQ = new dialogQuestD(parent, modal);
        diagQ.setTitulo(title);
        diagQ.setTexto(message);
        diagQ.setBotones(botones);
        diagQ.repaint();
        diagQ.setVisible(true);
        return diagQ.resultado;
        
    }
    
     public static int showConfirmDialog(String message,String title,int botones){
        boolean modal = true;
        dialogQuestD diagQ = new dialogQuestD(null, modal);
        diagQ.setTitulo(title);
        diagQ.setTexto(message);
        diagQ.setBotones(botones);
        diagQ.repaint();
        diagQ.setVisible(true);
        return diagQ.resultado;
        
    }
    
    
}
