/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obed77;

import javax.swing.UIManager;



/**
 *
 * @author Saito
 */
public class Main
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        for(UIManager.LookAndFeelInfo laf:UIManager.getInstalledLookAndFeels()){
            if("Nimbus".equals(laf.getName()))
               
                try {
                UIManager.setLookAndFeel(laf.getClassName());
            } catch (Exception ex) {
                    System.out.println("Error: "+ex);
            }
        }
        new Principal().setVisible(true);
        // TODO code application logic here
    }
        
    
}
