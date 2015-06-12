/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import datos.GestorPersistenciaCompra;
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
    private Bodega bodega;
    /**
     * Constructor no vacio de Compra
     * @param jsonCompra 
     * @throws excepciones.BDException 
     */
    public Compra(String jsonCompra) throws BDException {
        /*Conversion de String a Json*/
        StringReader strReader=new StringReader(jsonCompra);
        JsonReader jReader=Json.createReader(strReader);
        JsonObject jsonObject=jReader.readObject();
        Date fechaInicio;
        
        setIdCompra(jsonObject.getInt("idCompra"));
        if(jsonObject.containsKey("fechaInicio")){
            fechaInicio=Date.valueOf(jsonObject.getString("fechaInicio"));
            setFechaInicio(fechaInicio);
        }
        
        if(jsonObject.containsKey("fechaCompraCompletada")){
            Date fechaCompraCompletada=Date.valueOf(jsonObject.getString("fechaCompraCompletada"));
            setFechaCompraCompletada(fechaCompraCompletada);
        }
        
        if(jsonObject.containsKey("fechaPago")){
            Date fechaPago=Date.valueOf(jsonObject.getString("fechaPago"));
            setFechaPago(fechaPago);
        }
        
        setRecibidaCompletada(jsonObject.getString("recibidaCompleta"));
        setPagada(jsonObject.getString("pagada"));
        
        int idBodega=jsonObject.getInt("idBodega");
        Bodega bodega=Bodega.obtenerBodega(idBodega);
        setBodega(bodega);
        
        ArrayList<LineaCompra> lineaCompras=LineaCompra.obtenerLineasCompra(idCompra);
        setLineaCompras(lineaCompras);
    }
    
    /**
     * Se obtiene el identificador de la compra
     * @return un identificador de compra
     */
    public int getIdCompra() {
        return idCompra;
    }
    /**
     * Establece un identificador de compra
     * @param idCompra 
     */
    private void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    /**
     *Se obtiene la fecha de inicio de Compra
     * @return una fecha de inicio de una compra
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }
    /**
     * Establece una fecha de inicio de la compra
     * @param fechaInicio 
     */
    private void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     *Se obtiene la fecha de compra finalizada
     * @return una fecha de compra finalizada
     */
    public Date getFechaCompraCompletada() {
        return fechaCompraCompletada;
    }
    /**
     * Establece la fecha de compra finalizada
     * @param fechaCompraCompletada 
     */
    private void setFechaCompraCompletada(Date fechaCompraCompletada) {
        this.fechaCompraCompletada = fechaCompraCompletada;
    }

    /**
     * Se obtiene el estado de la compra recibida si esta completada o no
     * @return un booleano si la compra recibida es completada o no
     */
    public boolean isRecibidaCompletada() {
        return recibidaCompletada;
    }
    /**
     * Establece el estado de la compra recibida si esta completada o no
     * @param recibidaCompletada 
     */
    private void setRecibidaCompletada(String recibidaCompletada) {
        switch (recibidaCompletada) {
            case "T":
                this.recibidaCompletada = true;
                break;
            case "F":
                this.recibidaCompletada = false;
                break;
        }
    }

    /**
     * Se obtiene la fecha de pago de compra
     * @return una fecha de pago de una compra
     */
    public Date getFechaPago() {
        return fechaPago;
    }
    /**
     * Establece la fecha de pago de la compra
     * @param fechaPago 
     */
    private void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    /**
     * Se obtiene el estado de la compra si esta pagada o no
     * @return un booleano si la compra es pagada o no
     */
    public boolean isPagada() {
        return pagada;
    }
    /**
     * Establece el estado de la compra si esta pagada o no
     * @param pagada 
     */
    private void setPagada(String pagada) {
        switch(pagada){
            case "T": 
                this.pagada = true;
                break;
            case "F": 
                this.pagada = false;
                break;
        }
    }

    /**
     *Se obtiene una lista de linea de compra
     * @return una lista de las lineas de compra
     */
    public ArrayList<LineaCompra> getLineaCompras() {
        return lineaCompra;
    }
    /**
     * Establece  la lista de las lineas de compra
     * @param lineaCompra 
     */
    private void setLineaCompras(ArrayList<LineaCompra> lineaCompra) {
        this.lineaCompra = lineaCompra;
    }

    public Bodega getBodega() {
        return bodega;
    }

    private void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }
    

    /**
     * Se obtiene la compra mediante el identificador de compra
     * @param idCompra
     * @return la compra
     * @throws BDException
     */
    protected static Compra obtenerCompra (int idCompra) throws BDException{
        String jsonCompra=GestorPersistenciaCompra.getCompraByidCompra(idCompra);
        Compra comp=null;
        
        /*Creación del empleado*/
        if(jsonCompra!=null) comp=new Compra(jsonCompra);
              
        return comp;
    } 
    
    protected int comprobarLineasCompra() throws BDException{
        ArrayList<LineaCompra> lineasCompra = getLineaCompras();
        int resultado = 1;
        
        for(LineaCompra lineaCompra: lineasCompra){
            resultado = lineaCompra.comprobarLineaCompraRecibida();
            if(resultado == 1)
                return resultado;
        }
        return resultado;
    }
    
    protected void fechaRecepcionCompra(Date fecha)throws BDException{
        GestorPersistenciaCompra.fechaRecepcionCompra(getIdCompra(), fecha);
    }
    
}
