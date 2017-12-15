/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.controlador.principal;

/**
 *
 * @author Saito
 */
public class Roles {
        public static String ROL_ADMIN = "ROL_ADMIN";
        public static String ROL_PROVEEDORES = "ROL_PROVEEDORES";
        public static String ROL_PRODUCTOS = "ROL_PRODUCTOS";
        public static String ROL_VENTAS = "ROL_VENTAS";
        public static String ROL_CLIENTES = "ROL_CLIENTES";
        public static String ROL_COMPRAS = "ROL_COMPRAS";
        public static String ROL_CONFIG = "ROL_CONFIG";
        
        int id;
        String roleName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
        
        
        
}
