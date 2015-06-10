/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

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
    
    public Referencia(String jsonReferencia)throws BDException{
        StringReader strReader=new StringReader(jsonReferencia);
        JsonReader jReader=Json.createReader(strReader);
        JsonObject jsonObject=jReader.readObject();
        
        
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public boolean isEsPorCajas() {
        return esPorCajas;
    }

    public void setEsPorCajas(boolean esPorCajas) {
        this.esPorCajas = esPorCajas;
    }

    public int getContenidoENCL() {
        return contenidoENCL;
    }

    public void setContenidoENCL(int contenidoENCL) {
        this.contenidoENCL = contenidoENCL;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    protected static Referencia getReferencia(int numRef){
        return GestorPersistenciaReferencia(numRef);
    }
}
