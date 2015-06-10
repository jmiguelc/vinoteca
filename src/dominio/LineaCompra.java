/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.sql.Date;

/**
 *
 * @author nurcanc
 */
public class LineaCompra {
    private int unidades;
    private boolean recibida;
    private Date fechaRecepcion;

    public LineaCompra(int unidades, boolean recibida, Date fechaRecepcion) {
        this.unidades = unidades;
        this.recibida = recibida;
        this.fechaRecepcion = fechaRecepcion;
    }
    
    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public boolean isRecibida() {
        return recibida;
    }

    public void setRecibida(boolean recibida) {
        this.recibida = recibida;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }
    
}
