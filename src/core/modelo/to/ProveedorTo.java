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
public class ProveedorTo {
  private final String ACTIVO = "ACTIVO";
  private final String INACTIVO = "INACTIVO";
  String tipoDocumento;
  String documento;
  String nombre ;
  String contacto;
  String telefono;
  String correo;
  String direccion;
  int estatus;

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
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
