package core.controlador.hilos;

import core.controlador.helper.SessionHelper;
import core.controlador.session.Comunes;
import core.logger.LogService;
import core.modelo.helper.Configuraciones;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import javax.swing.Timer;
import obed77.Iniciando;
import obed77.views.dialogosComunes.JOptionDialog;



/**
 *
 * @author Saito
 */
public class HiloIniciando extends Thread {

    private boolean continuar = true;
    public static int iniciarexito = -1;
    Timer timer;
    int i = 0;
    Iniciando e = new Iniciando();
    String user;
    String pass;

    public HiloIniciando(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }
    
    
    
    public void detenElHilo() {
        this.continuar = false;
    }

    @Override
    public void run() {

        e.setVisible(true);
        while (this.continuar) {

            {
                SessionHelper iniciar = new SessionHelper();
                iniciarexito = iniciar.iniciarSesion(user,pass);
                
                
                if (iniciarexito==SessionHelper.StatusActivo) {
                    timer = new Timer(80, new ActionListener() {
                                  public void actionPerformed(ActionEvent o) {
                                      i += 1;

                                      cek();
                                  }
                              });
                    timer.start();
                    e.cambiar();
                } else {
                    Properties prop = new Properties();
                    try {
                        prop.load(new BufferedReader(new FileReader("resources/mensajes.properties")));
                    } catch (FileNotFoundException ex) {
                        LogService.logger.error(Comunes.USER, ex.getMessage());
                    } catch (IOException ex) {
                       LogService.logger.error(Comunes.USER, ex.getMessage());
                    }
                    
                    String mensaje = "";
                    switch(iniciarexito){
                        case SessionHelper.StatusInactivo:
                            mensaje = prop.getProperty("core.inicio.sesion.cuentaInactiva");
                            break;
                        case SessionHelper.StatusBloqueado:
                            mensaje = prop.getProperty("core.inicio.sesion.cuentaBloqueada");
                            break;
                        case SessionHelper.ClaveIncorrectaUsuarioBloqueado:
                            mensaje = prop.getProperty("core.inicio.sesion.claveIncorrectaBloqueado");
                            break;
                        case SessionHelper.ClaveIncorrecta: 
                            mensaje = prop.getProperty("core.inicio.sesion.claveIncorrecta");
                            mensaje = mensaje.replace("[INTENTOS]",""+Configuraciones.maxIntentos);
                            break;
                        case SessionHelper.CuentaSinRoles:
                            mensaje = prop.getProperty("core.inicio.sesion.cuentaSinRoles");
                            break;
                        case SessionHelper.UsuarioNoEncontrado:
                            mensaje = prop.getProperty("core.inicio.sesion.usuarioNoEncontrado");
                            break;
                        default :
                            mensaje = prop.getProperty("core.inicio.sesion.default");
                    }
                    JOptionDialog.showMessageDialog(e, mensaje,"Información",JOptionDialog.INFORMACION_ICON);
                    e.dispose();
                }

            }
            detenElHilo();
        }

    }

    private void cek() {
        if (i == 11) {
            timer.stop();
            e.dispose();

        }
    }
}
