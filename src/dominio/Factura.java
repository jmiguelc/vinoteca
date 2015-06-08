/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import datos.GestorPersistenciaFactura;
import excepciones.BDException;
import java.io.StringReader;
import java.sql.Date;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author monrae
 */
public class Factura {
    private int numeroFactura;
    private Date fechaEmision;
    private float importe;
    private EstadoFactura estado;
    private Date fechaPago;
    private String idExtractBancario;

    /*El constructor debe ser con Json por mi parte solo debo acceder
    a la base de datos para traerlos */
    public Factura(String jsonFactura) {
        StringReader strReader=new StringReader(jsonFactura);
        JsonReader jReader=Json.createReader(strReader);
        JsonObject jsonObject=jReader.readObject();
        
        setNumeroFactura(jsonObject.getInt("numeroFactura"));
        Date fechaEmision=Date.valueOf(jsonObject.getString("fechaEmision"));
        setFechaEmision(fechaEmision);
        float importe=Float.parseFloat(jsonObject.getString("importe"));
        setImporte(importe);
        if(!jsonObject.containsKey("fechaPago"))
            setFechaPago(null);
        if(!jsonObject.containsKey("idExtractBancario"))
            setIdExtractBancario(null);
        EstadoFactura estado=EstadoFactura.getEstado(jsonObject.getString("estado"));
        setEstado(estado);
       
    }

    public int getNumeroFactura() {
        return numeroFactura;
    }

    private void setNumeroFactura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    private void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public float getImporte() {
        return importe;
    }

    private void setImporte(float importe) {
        this.importe = importe;
    }

    public EstadoFactura getEstado() {
        return estado;
    }

    private void setEstado(EstadoFactura estado) {
        this.estado = estado;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    private void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getIdExtractBancario() {
        return idExtractBancario;
    }

    private void setIdExtractBancario(String idExtractBancario) {
        this.idExtractBancario = idExtractBancario;
    }
    
    protected static ArrayList<Factura> obtenerFacturasVencidas(Date fecha) throws BDException{
        ArrayList<Factura> facturas=new ArrayList<>();
        
        /*Recuperamos las facturas*/
        String jsonListaFacturas=GestorPersistenciaFactura.recuperarFacturasVencidas(fecha);
        String jsonFactura;
        Factura f;
        
        /*Deshacemos el jsonArray*/
        StringReader strReader=new StringReader(jsonListaFacturas);
        JsonReader jReader=Json.createReader(strReader);    
        JsonArray jsonArray=jReader.readArray();
        
        /*mandamos construir los objetos y generamos la lista de facturas*/
        for(int i=0;i<jsonArray.size();i++){
            jsonFactura = jsonArray.getJsonObject(i).toString();
            f=new Factura(jsonFactura);
            facturas.add(f);
        }
        
        /*ir a pedidos y recuperar los pedidos para cada factura*/
        
        return facturas;
    }
    
}
