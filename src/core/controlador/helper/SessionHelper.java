/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.controlador.helper;

import core.controlador.principal.Roles;
import core.controlador.session.Comunes;
import core.controlador.session.EmpleadoSession;
import core.controlador.session.UsuarioSession;
import core.logger.LogService;
import core.modelo.helper.Configuraciones;
import core.modelo.to.EmpleadoTo;
import core.modelo.to.UsuarioTo;
import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import obed77.Principal;



/**
 *
 * @author Saito
 */
public class SessionHelper {
    public static final int StatusInactivo = 0;
    public static final int StatusActivo = 1; 
    public static final int StatusBloqueado = 2;
    public static final int CuentaSinRoles = 3;
    public static final int ClaveIncorrecta = 4;
    public static final int ClaveIncorrectaUsuarioBloqueado = 5;
    public static final int UsuarioNoEncontrado = 6;
    
    
    public int iniciarSesion(String user, String pass) {
        
        int res = -1;
        ArrayList<Roles> listaRoles = new ArrayList<>();
        UsuarioSession userSession = new UsuarioSession();
        EmpleadoSession empSession = new  EmpleadoSession();
        UsuarioTo usuario = null;
        EmpleadoTo empleado = null;
        
        
        try {
            LogService.logger.info(Comunes.USER, "Buscando usuario");
            usuario = userSession.getUsuarioByUserPass(user);
            
            
            if (usuario!=null) {
                switch(usuario.getStatus()){
                    case StatusBloqueado:
                        LogService.logger.info(Comunes.USER, "Cuenta bloqueada");
                        return StatusBloqueado;
                    case StatusInactivo:
                        LogService.logger.info(Comunes.USER, "La cuenta se encuentra Inactiva");
                        return StatusInactivo;
                    case StatusActivo:
                         if (usuario.getPass().equals(pass)) {
                            LogService.logger.info(Comunes.USER, "Buscando roles del usuario");
                            listaRoles = userSession.getRolesByUser(user);
                            if(listaRoles.isEmpty() || listaRoles == null){
                                return CuentaSinRoles;
                            }
                            usuario.setRoles(listaRoles);
                            LogService.logger.info(Comunes.USER, "Buscando datos del empleado");
                            empleado = empSession.getEmpleadoByCedula(Comunes.USER,usuario.getEmpleado().getCedula());
                            //Le asignamos el empleado full al usuario
                            usuario.setEmpleado(empleado);
                            //Inicializo los intentos
                            usuario.setIntentos(0);
                            LogService.logger.info(Comunes.USER, "Configurando Intentos y Ultima conexion");
                            userSession.setIntentosUsuario(usuario);
                            userSession.setConexion(usuario.getUser());
                            LogService.logger.info(Comunes.USER, "Cambiando interfaz gráfica principal");
                            cambiarInterfazPrincipalInicio(usuario);
                            return StatusActivo;
                        
                        } else {
                            
                            int intentos = usuario.getIntentos();

                            if (intentos >= Configuraciones.maxIntentos) {
                                boolean r;
                                r = userSession.setStatusUsuario(Comunes.USER,usuario.getUser(), StatusBloqueado);
                                LogService.logger.info(Comunes.USER, "Contraseña incorrecta, Cuenta Bloqueada");
                                if (r) {
                                    return ClaveIncorrectaUsuarioBloqueado;

                                }

                            } else {
                                
                                usuario.setIntentos(intentos+1);
                                boolean r;
                                r = userSession.setIntentosUsuario(usuario);
                                LogService.logger.info(Comunes.USER, "Contraseña incorrecta aumentando un intento ["+usuario.getIntentos()+"]");
                                if (r) {
                                    return ClaveIncorrecta;
                                }

                            }

                        }
                        
                        
                }
                
            }else{
                LogService.logger.info(Comunes.USER, "Usuariop no encontrado");
                return UsuarioNoEncontrado;
            }


        } catch (ClassNotFoundException ex) {
            LogService.logger.error(Comunes.USER, ex.toString());
        } catch (SQLException ex) {
            LogService.logger.error(Comunes.USER, ex.toString());
        } catch (Exception ex) {
            LogService.logger.error(Comunes.USER, ex.toString());
        }
        return res;
    }
    
    
    private void cambiarInterfazPrincipalInicio (UsuarioTo user){
        //Cargamos lo inicial del panel principal
        Principal.labNombreEmpleado.setText(user.getEmpleado().getNombreCompleto());
        Principal.labCargo.setText(user.getEmpleado().getCargo());
        Principal.labUltimaConexion.setText(user.getUltimaConexion());
        Principal.panelHome.setVisible(true);
        Principal.panelBody.remove(Principal.panelInicial);
        Principal.panelBody.repaint();
        Principal.panelBody.add(Principal.panelPrincipalBotones, BorderLayout.NORTH, 1);
        Principal.panelBody.repaint();
        Principal.panelBody.add(Principal.panelIniciado, BorderLayout.SOUTH);
        Principal.panelBody.repaint();
        Principal.panelBody.validate();
        Principal.panelBody.updateUI();
        Principal.txtUser.setText("");
        Principal.txtPass.setText("");
        Principal.usuarioTo = user;
        
        
        //Validamos lso roles y habilitamos los respectivos menus y botones
        //Principal
        Principal.btnPrincipalClientes.setVisible(user.isMenuClientes());
        Principal.btnPrincipalEmpleados.setVisible(user.isMenuEmpleados());
        Principal.btnPrincipalProductos.setVisible(user.isMenuProductos());
        Principal.btnPrincipalProveedores.setVisible(user.isMenuProveedores());
        
        
        
        //Menus
        //Ventas
        Principal.pVentas.setVisible(user.isMenuVentas());
        
        
        
        
        //Productos
        Principal.pProductos.setVisible(user.isMenuProductos());
        
        
        
        //Clientes
        Principal.pClientes.setVisible(user.isMenuClientes());
        
        
        
        
        //Compras
        Principal.pCompras.setVisible(user.isMenuCompras());
        
        
        
        
        //Proveedores
        Principal.pProveedores.setVisible(user.isMenuProveedores());
        
                
        
        //Empleados
        Principal.pEmpleados.setVisible(user.isMenuEmpleados());
        
        
        
        //Configuracion
        Principal.pConfiguracion.setVisible(user.isMenuConfiguracion());
        
        
    }
    
    
   public void cerrarSesion(){
        Principal.multiPanel.removeAll();
        Principal.multiPanel.add(Principal.panelHome);
        ImageIcon icon = createImageIcon("/Imagenes/icono_home_mini.png");
        Principal.multiPanel.setIconAt(0, icon);
        Principal.labNombreEmpleado.setText("");
        Principal.labCargo.setText("");
        Principal.labUltimaConexion.setText("");
        Principal.panelHome.setVisible(true);
        Principal.panelBody.remove(Principal.panelIniciado);
        Principal.panelBody.repaint();
        Principal.panelBody.remove(1);
        Principal.panelBody.repaint();
        Principal.txtUser.setText("");
        Principal.txtPass.setText("");
        Principal.txtUser.grabFocus();
        Principal.panelBody.add(Principal.panelInicial, BorderLayout.SOUTH);
        Principal.panelBody.repaint();
        Principal.panelBody.validate();
        Principal.panelBody.updateUI();
        Principal.usuarioTo = new UsuarioTo();
        Principal.btnIniciarSesion.setEnabled(false);
        
        
        //Principal
        Principal.btnPrincipalClientes.setVisible(false);
        Principal.btnPrincipalEmpleados.setVisible(false);
        Principal.btnPrincipalProductos.setVisible(false);
        Principal.btnPrincipalProveedores.setVisible(false);
        
        
        
        //Menus
        //Ventas
        Principal.pVentas.setVisible(false);
        
        
        
        
        //Productos
        Principal.pProductos.setVisible(false);
        
        
        
        //Clientes
        Principal.pClientes.setVisible(false);
        
        
        
        
        //Compras
        Principal.pCompras.setVisible(false);
        
        
        
        
        //Proveedores
        Principal.pProveedores.setVisible(false);
        
                
        
        //Empleados
        Principal.pEmpleados.setVisible(false);
        
        
        
        //Configuracion
        Principal.pConfiguracion.setVisible(false);
        
        
   }
 protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Principal.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
