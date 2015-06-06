/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.sql.Date;
import java.util.Currency;

/**
 *
 * @author monrae
 */
public class Pedido {
    private int numeroPedido;
    private EstadoPedido estado;
    private Date fechaRealizacion;
    private String NotaEntrega;
    private Currency importe;
    private Date fechaRecepcion;
    private Date fechaEntrega;

    /*Seguramente haya que hacer un constructor para Json 
    y otro normal para procesar pedido*/
    public Pedido() {
    
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    private void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    private void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public Date getFechaRealizacion() {
        return fechaRealizacion;
    }

    private void setFechaRealizacion(Date fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    public String getNotaEntrega() {
        return NotaEntrega;
    }

    private void setNotaEntrega(String NotaEntrega) {
        this.NotaEntrega = NotaEntrega;
    }

    public Currency getImporte() {
        return importe;
    }

    private void setImporte(Currency importe) {
        this.importe = importe;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    private void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    private void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }
    
    
    
}
