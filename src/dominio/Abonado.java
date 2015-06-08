/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import datos.GestorPersistenciaAbonado;
import datos.GestorPersistenciaPersona;
import excepciones.BDException;
import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author ruben
 */
public class Abonado extends Persona{
    private int numeroAbonado;
    private String openIdRef;
    
    protected Abonado(String jsonAbonado,String jsonPersona) {
        /*Conversion de String a Json*/
        super(jsonPersona);
        
        StringReader strReader=new StringReader(jsonAbonado);
        JsonReader jReader=Json.createReader(strReader);
        JsonObject jsonObject=jReader.readObject();
 
        setNumeroAbonado(jsonObject.getInt("numAbonado"));
        setOpenIdRef(jsonObject.getString("openIdRef"));
        
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
  
    protected static Abonado obtenerAbonado (int num) throws BDException{
        String jsonAb=GestorPersistenciaAbonado.obtenerAbonado(num);
        Abonado ab=null;
        Persona p;
        
        /*Creación del abonado*/
        if(jsonAb!=null){
            StringReader strReader=new StringReader(jsonAb);
            JsonReader jReader=Json.createReader(strReader);
            JsonObject jsonObject=jReader.readObject();
            String jsonPersona=GestorPersistenciaPersona.getPersonaByNif(jsonObject.getString("nif"));
            
            ab=new Abonado(jsonAb,jsonPersona);
        
        }
        
              
        return ab;
    }
    
}
