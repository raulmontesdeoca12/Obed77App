/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.modelo.to;

import java.util.ArrayList;
import java.util.Date;



/**
 *
 * @author Saito
 */
public class CompraTo {
    private final String APROBADO = "APROBADO";
    private final String ANULADO = "ANULADO";
    long cod;
    Date fecha;
    ProveedorTo proveedor;
    String usuario;
    String documento;
    String referencia;
    int estatus;
    ArrayList<DetalleCompraTo> detalles;

    public long getCod() {
        return cod;
    }

    public void setCod(long cod) {
        this.cod = cod;
    }

    public ArrayList<DetalleCompraTo> getDetalles() {
        return detalles;
    }

    public void setDetalles(ArrayList<DetalleCompraTo> detalles) {
        this.detalles = detalles;
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

  
     public String getEstatusString() {
        if (estatus == 1) {
            return APROBADO;
        } else {
            return ANULADO;
        }
    }

    public void setEstatusString(String estatus) {
        if (estatus.equals(APROBADO)) {
            this.estatus = 1;
        } else {
            this.estatus = 0;
        }
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ProveedorTo getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedorTo proveedor) {
        this.proveedor = proveedor;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public String getProveedorCompleto(){
        return this.proveedor.getTipoDocumento()+"<>"+this.proveedor.getDocumento()+"<>"+this.proveedor.getNombre();
    }
    
    public void setProveedorCompleto(String proveedor){
        String[] prov = proveedor.split("<>");
        this.proveedor.setTipoDocumento(prov[0]);
        this.proveedor.setDocumento(prov[1]);
        this.proveedor.setNombre(prov[2]);
    }
    
    
}
