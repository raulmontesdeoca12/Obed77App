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
public class ReporteVentasMensualTo {
    int nMes;
    String mes;
    double monto;

    public ReporteVentasMensualTo() {
    }

    public ReporteVentasMensualTo(int nMes, String mes) {
        this.nMes = nMes;
        this.mes = mes;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getnMes() {
        return nMes;
    }

    public void setnMes(int nMes) {
        this.nMes = nMes;
    }
    
    
}
