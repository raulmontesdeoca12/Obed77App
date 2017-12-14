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
public class ProductoTo {
    private final String ACTIVO = "ACTIVO";
    private final String INACTIVO = "INACTIVO";
    long cod;
    String nombre;
    String descripcion;
    int categoria;
    String categoriaString;
    long stock;
    double precio;
    int estatus; 

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public String getCategoriaString() {
        return categoriaString;
    }

    public void setCategoriaString(String categoriaString) {
        this.categoriaString = categoriaString;
    }

    public long getCod() {
        return cod;
    }

    public void setCod(long cod) {
        this.cod = cod;
    }

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
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
    
    public String toString(){
        return this.nombre;
    }
}
