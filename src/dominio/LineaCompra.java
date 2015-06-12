/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import datos.GestorPersistenciaLineaCompra;
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
 * @author nurcanc
 */
public class LineaCompra {
    private int idLineaCompra;
    private int unidades;
    private boolean recibida;
    private Date fechaRecepcion;
   
    public LineaCompra(String jsonLineaCompra) {
        StringReader strReader=new StringReader(jsonLineaCompra);
        JsonReader jReader=Json.createReader(strReader);
        JsonObject jsonObject=jReader.readObject();
        
        setRecibida(jsonObject.getString("recibida"));
        setUnidades(jsonObject.getInt("unidades"));
        if(jsonObject.containsKey("fechaRecepcion")){
            String fechaRecepcion=jsonObject.getString("fechaRecepcion");
            setFechaRecepcion(Date.valueOf(fechaRecepcion));
        }
        setIdLineaCompra(jsonObject.getInt("idLineaCompra"));
    }

    public int getIdLineaCompra() {
        return idLineaCompra;
    }

    private void setIdLineaCompra(int idLineaCompra) {
        this.idLineaCompra = idLineaCompra;
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
    private void setRecibida(String recibida) {
        switch(recibida){
            case "T":
                this.recibida=true;
                break;
            case "F":
                this.recibida=false;
                break;
        }
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
    
    protected static ArrayList<LineaCompra> obtenerLineasCompra(int idCompra) throws BDException{
        ArrayList<LineaCompra> lineaCompras=new ArrayList<>();
        LineaCompra lineaCompra;
        
        /*recuperar pedidos */
         String jsonLineasCompra=GestorPersistenciaLineaCompra.getLineasCompra(idCompra);
         String jsonLineaCompra;
        
        /*Deshacemos el jsonArray*/
        StringReader strReader=new StringReader(jsonLineasCompra);
        JsonReader jReader=Json.createReader(strReader);    
        JsonArray jsonArray=jReader.readArray();
        
        /*mandamos construir los objetos y generamos la lista de Pedidos*/
        for(int i=0;i<jsonArray.size();i++){
            jsonLineaCompra = jsonArray.getJsonObject(i).toString();
            lineaCompra=new LineaCompra(jsonLineaCompra);
            lineaCompras.add(lineaCompra);
        }
        
        return lineaCompras;
    }  
    
    protected static LineaCompra obtenerLineaCompra (int id) throws BDException{
        String jsonLineaCompra=GestorPersistenciaLineaCompra.getLineaCompraByid(id);
        LineaCompra lineaCompra=null;
        
        /*Creación del empleado*/
        if(jsonLineaCompra!=null) lineaCompra=new LineaCompra(jsonLineaCompra);
              
        return lineaCompra;
    } 
    
    protected void lineaCompraRecibida() throws BDException{
        GestorPersistenciaLineaCompra.lineaCompraRecibida(getIdLineaCompra());
    }
    
    protected void fechaRecepcionLC(Date fecha)throws BDException{
        GestorPersistenciaLineaCompra.fechaRecepcionLC(getIdLineaCompra(), fecha);
    }
}
