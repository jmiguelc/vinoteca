/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import datos.GestorPersistenciaCompra;
import datos.GestorPersistenciaPersona;
import excepciones.BDException;
import java.io.StringReader;
import java.sql.Date;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author nurcanc
 */
public class Compra {
    
    private int idCompra;
    private Date fechaInicio;
    private Date fechaCompraCompletada;
    private boolean recibidaCompletada;
    private Date fechaPago;
    private boolean pagada;
    private ArrayList<LineaCompra> lineaCompra;

    public Compra(String jsonCompra) {
        /*Conversion de String a Json*/
        StringReader strReader=new StringReader(jsonCompra);
        JsonReader jReader=Json.createReader(strReader);
        JsonObject jsonObject=jReader.readObject();
        
        setIdCompra(jsonObject.getInt("idCompra"));
        Date fechaInicio=Date.valueOf(jsonObject.getString("fechaInicio"));
        setFechaInicio(fechaInicio);
        Date fechaCompraCompletada=Date.valueOf(jsonObject.getString("fechaCompraCompletada"));
        setFechaCompraCompletada(fechaCompraCompletada);
        Date fechaPago=Date.valueOf(jsonObject.getString("fechaPago"));
        setFechaPago(fechaPago);
        setRecibidaCompletada(jsonObject.getBoolean("recibidaCompletada"));
        setPagada(jsonObject.getBoolean("pagada"));
    }
    
    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaCompraCompletada() {
        return fechaCompraCompletada;
    }

    public void setFechaCompraCompletada(Date fechaCompraCompletada) {
        this.fechaCompraCompletada = fechaCompraCompletada;
    }

    public boolean isRecibidaCompletada() {
        return recibidaCompletada;
    }

    public void setRecibidaCompletada(boolean recibidaCompletada) {
        this.recibidaCompletada = recibidaCompletada;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public boolean isPagada() {
        return pagada;
    }

    public void setPagada(boolean pagada) {
        this.pagada = pagada;
    }
    public ArrayList<LineaCompra> getLineaCompra() {
        return lineaCompra;
    }
    private void setLineaCompra(ArrayList<LineaCompra> lineaCompra) {
        this.lineaCompra = lineaCompra;
    }
    protected static Compra obtenerCompra (int idCompra) throws BDException{
        String jsonCompra=GestorPersistenciaCompra.getCompraByidCompra(idCompra);
        Compra comp=null;
        
        /*Creación del empleado*/
        if(jsonCompra!=null) comp=new Compra(jsonCompra);
              
        return comp;
    } 
    
}
