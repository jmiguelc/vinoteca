/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import datos.GestorPersistenciaReferencia;
import excepciones.BDException;
import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author ruben
 */
public class Referencia {
    private int codigo;
    private boolean esPorCajas;
    private int contenidoENCL;
    private double importe;
    private boolean disponible;
    
    /**
     *Constructor no vacio de Referencia
     * @param jsonReferencia
     * @throws BDException
     */
    public Referencia(String jsonReferencia)throws BDException{
        StringReader strReader=new StringReader(jsonReferencia);
        JsonReader jReader=Json.createReader(strReader);
        JsonObject jsonObject=jReader.readObject();
        
        setCodigo(jsonObject.getInt("codigo"));
        setEsPorCajas(jsonObject.getBoolean("porCajas"));
        setContenidoENCL(jsonObject.getInt("contenido"));
        setImporte(jsonObject.getJsonNumber("importe").doubleValue());
        setDisponible(jsonObject.getBoolean("disponible"));
    }

    /**
     * Se obtiene el codigo de Referencia
     * @return un codigo de una referencia
     */
    public int getCodigo() {
        return codigo;
    }
    /**
     * Establece un codigo de Referencia
     * @param codigo 
     */
    private void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Se obtiene si es por cajas o no la referencia
     * @return un booleano de si la referencia es por cajas
     */
    public boolean isEsPorCajas() {
        return esPorCajas;
    }
    /**
     * Establece si la referencia es por cajas
     * @param esPorCajas 
     */
    private void setEsPorCajas(boolean esPorCajas) {
        this.esPorCajas = esPorCajas;
    }

    /**
     * Se obtiene el contenido en cl de Referencia
     * @return el contenido en cl de una referencia
     */
    public int getContenidoENCL() {
        return contenidoENCL;
    }
    /**
     * Establece el contenido en cl de Referencia
     * @param contenidoENCL 
     */
    private void setContenidoENCL(int contenidoENCL) {
        this.contenidoENCL = contenidoENCL;
    }

    /**
     * Se obtiene el importe de Referencia
     * @return el importe de una referencia
     */
    public double getImporte() {
        return importe;
    }
    /**
     * Establece un importe de Referencia
     * @param importe 
     */
    private void setImporte(double importe) {
        this.importe = importe;
    }

    /**
     * Se obtiene si esta disponible de Referencia
     * @return un booleano de si esta disponible 
     */
    public boolean isDisponible() {
        return disponible;
    }
    /**
     * Establece si esta disponible de Referencia
     * @param disponible 
     */
    private void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    /**
     * Se obtiene la referencia a traves del numero de referencia
     * @param numRef
     * @return el numero de referencia de una referencia
     * @throws BDException
     */
    protected static Referencia getReferencia(int numRef) throws BDException{
        String jsonReferencia = GestorPersistenciaReferencia.getReferencia(numRef);
        Referencia ref = null;
        
        if(jsonReferencia != null){
            ref = new Referencia(jsonReferencia);
        }
        return ref;
    }
}
