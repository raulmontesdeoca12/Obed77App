/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.modelo.to;

/**
 *
 * @author Saito
 */
public class CategoriaTo {

    private final String ACTIVO = "ACTIVO";
    private final String INACTIVO = "INACTIVO";
    int id;
    String nombre;
    String descripcion;
    int estatus;


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstatusString() {
        if (estatus == 1) {
            return ACTIVO;
        } else {
            return INACTIVO;
        }
    }

    public void setEstatusString(String estatus) {
        if (estatus.equals(ACTIVO)) {
            this.estatus = 1;
        } else {
            this.estatus = 0;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
