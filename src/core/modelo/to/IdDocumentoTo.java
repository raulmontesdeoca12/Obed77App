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
public class IdDocumentoTo {
    private final String ACTIVO = "ACTIVO";
    private final String INACTIVO = "INACTIVO";
    String idDocumento;
    int estatus;

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public String getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
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
}
