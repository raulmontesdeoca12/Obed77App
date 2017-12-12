/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.modelo.to;

import core.controlador.principal.Roles;
import java.util.ArrayList;



/**
 * Clase que almacena los datos principales del usuario
 *
 * @author Saito
 */
public class UsuarioTo {

    String user;
    String pass;
    int status;
    int intentos;
    EmpleadoTo empleado;
    ArrayList<Roles> roles;
    String ultimaConexion;


    public EmpleadoTo getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoTo empleado) {
        this.empleado = empleado;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public ArrayList<Roles> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<Roles> roles) {
        this.roles = roles;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUltimaConexion() {
        return ultimaConexion;
    }

    public void setUltimaConexion(String ultimaConexion) {
        this.ultimaConexion = ultimaConexion;
    }

    public String getUser() {
        if (user != null) {
            return user;
        } else {
            return "SYS";
        }
    }

    public void setUser(String user) {
        this.user = user;
    }



    /**
     * Valida si el usuario tiene el rol consultado
     *
     * @param rol
     *
     * @return
     *
     */
    public boolean isUserInRol(String sRol) {

        ArrayList<String> sRoles = new ArrayList<String>();
        for (Roles rol : roles) {
            sRoles.add(rol.getRoleName());
        }
        return sRoles.contains(sRol);
    }


    public boolean isMenuVentas() {
        return false; //(isUserInRol(Roles.AdminApp));
    }

    public boolean isMenuProductos() {
        return true; //(isUserInRol(Roles.AdminApp));
    }

    public boolean isMenuClientes() {
        return false;//(isUserInRol(Roles.AdminApp));
    }

    public boolean isMenuCompras() {
        return true; //(isUserInRol(Roles.AdminApp));
    }

    public boolean isMenuProveedores() {
        return isUserInRol(Roles.ROL_ADMIN) || isUserInRol(Roles.ROL_PROVEEDORES);
    }

    public boolean isMenuEmpleados() {
        return false;//(isUserInRol(Roles.AdminApp));
    }

    public boolean isMenuConfiguracion() {
        return true;//(isUserInRol(Roles.AdminApp));
    }
}
