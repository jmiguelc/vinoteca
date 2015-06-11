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
        setUnidades(unidades);
        setRecibida(recibida);
        setFechaRecepcion(fechaRecepcion);
    }
    
    public int getUnidades() {
        return unidades;
    }

    private void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public boolean isRecibida() {
        return recibida;
    }

    private void setRecibida(boolean recibida) {
        this.recibida = recibida;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    private void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }
    
}
