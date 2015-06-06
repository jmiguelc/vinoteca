/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import datos.GestorPersistenciaAbonado;
import excepciones.BDException;
import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author ruben
 */
public class Abonado {
    private int numeroAbonado;
    private String openIdRef;
    private String nif;
    
    protected Abonado(String jsonStr) {
        /*Conversion de String a Json*/
        StringReader strReader=new StringReader(jsonStr);
        JsonReader jReader=Json.createReader(strReader);
        JsonObject jsonObject=jReader.readObject();
        
        setNumeroAbonado(jsonObject.getInt("numAbonado"));
        setOpenIdRef(jsonObject.getString("openIdRef"));
        setNif(jsonObject.getString("nif"));    
    }

    public int getNumeroAbonado() {
        return numeroAbonado;
    }

    private void setNumeroAbonado(int numeroAbondo) {
        this.numeroAbonado = numeroAbondo;
    }

    public String getOpenIdRef() {
        return openIdRef;
    }

    private void setOpenIdRef(String openIdRef) {
        this.openIdRef = openIdRef;
    }

    public String getNif() {
        return nif;
    }

    private void setNif(String nif) {
        this.nif = nif;
    }
    
     protected static Abonado obtenerAbonado (int num) throws BDException{
        String jsonAb=GestorPersistenciaAbonado.obtenerAbonado(num);
        Abonado ab=null;
        
        /*Creación del abonado*/
        if(jsonAb!=null) ab=new Abonado(jsonAb);
              
        return ab;
    }
    
}
