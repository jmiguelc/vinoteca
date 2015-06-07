/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import datos.GestorPersistenciaFactura;
import excepciones.BDException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Currency;

/**
 *
 * @author monrae
 */
public class Factura {
    private int numeroFactura;
    private Date fechaEmision;
    private Currency importe;
    private EstadoFactura estado;
    private Date fechaPago;
    private String idExtractBancario;

    /*El constructor debe ser con Json por mi parte solo debo acceder
    a la base de datos para traerlos */
    public Factura() {
    }

    public int getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Currency getImporte() {
        return importe;
    }

    public void setImporte(Currency importe) {
        this.importe = importe;
    }

    public EstadoFactura getEstado() {
        return estado;
    }

    public void setEstado(EstadoFactura estado) {
        this.estado = estado;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getIdExtractBancario() {
        return idExtractBancario;
    }

    public void setIdExtractBancario(String idExtractBancario) {
        this.idExtractBancario = idExtractBancario;
    }
    
    protected static ArrayList<Factura> obtenerFacturasVencidas(Date fecha) throws BDException{
        ArrayList<Factura> facturas=null;
        String jsonArray=GestorPersistenciaFactura.recuperarFacturasVencidas(fecha);
        
        
        return facturas;
    }
    
}
