package core.controlador.hilos;

import core.controlador.helper.SessionHelper;
import core.logger.LogService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import obed77.Cerrando;



/**
 *
 * @author Saito
 */
public class HiloCerrando extends Thread {

    private boolean continuar = true;
    Timer timer;
    int i = 0;
    String user;
    Cerrando e = new Cerrando();

    public HiloCerrando(String user) {
        this.user= user;
    }
    
    
    
    public void detenElHilo() {
        this.continuar = false;
    }

    @Override
    public void run() {

        e.setVisible(true);
        while (this.continuar) {

            {
                SessionHelper cerrar = new SessionHelper();
                cerrar.cerrarSesion();
                
                    timer = new Timer(80, new ActionListener() {
                                  public void actionPerformed(ActionEvent o) {
                                      i += 1;
                                      if(i==5){
                                        e.cambiar();
                                      }
                                      cek();
                                  }
                              });
                    timer.start();
                    
                

            }
            detenElHilo();
        }

    }

    private void cek() {
        if (i == 11) {
            timer.stop();
            e.dispose();
            LogService.logger.info(user, "Sesión Cerrada");

        }
    }
}
