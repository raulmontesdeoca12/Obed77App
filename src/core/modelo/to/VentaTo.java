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
public class VentaTo {
    private final String APROBADO = "APROBADA";
    private final String ANULADO = "ANULADA";
    Date fecha;
    ClienteTo cliente;
    String usuario;
    String documento;
    String referencia;
    int estatus;
    double totalVenta;
    
    ArrayList<DetalleVentaTo> detalles;

     public ArrayList<DetalleVentaTo> getDetalles() {
        return detalles;
    }

    public void setDetalles(ArrayList<DetalleVentaTo> detalles) {
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

    public ClienteTo getCliente() {
        return cliente;
    }

    public void setCliente(ClienteTo cliente) {
        this.cliente = cliente;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

  
    public double getTotalCompraByDetalles() {
        double total = 0;
        if(detalles!=null){
            for(DetalleVentaTo to : detalles){
                total = total +to.getSubTotal();
            }    
        }
        return total;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
}
