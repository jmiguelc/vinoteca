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
    /**
     * Constructor no vacio de linea de compra
     * @param unidades
     * @param recibida
     * @param fechaRecepcion 
     */
    public LineaCompra(int unidades, boolean recibida, Date fechaRecepcion) {
        setUnidades(unidades);
        setRecibida(recibida);
        setFechaRecepcion(fechaRecepcion);
    }
    
    /**
     * Se obtiene las unidades de la linea de compra
     * @return las unidades de una linea de compra
     */
    public int getUnidades() {
        return unidades;
    }
    /**
     * Establece las unidades de la linea de compra
     * @param unidades 
     */
    private void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    /**
     * Se obtiene el estado de si esta recibida o no la linea de compra
     * @return un booleano con el estado de la linea de compra
     */
    public boolean isRecibida() {
        return recibida;
    }
    /**
     * Establece el estado de si la linea de compra esta recibida o no
     * @param recibida 
     */
    private void setRecibida(boolean recibida) {
        this.recibida = recibida;
    }

    /**
     * Se obtiene la fecha de recepcion de la linea de compra
     * @return una fecha de recepcion de una linea de compra
     */
    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }
    /**
     * Establece la fecha de recepcion de la linea de compra
     * @param fechaRecepcion 
     */
    private void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }
    
}
