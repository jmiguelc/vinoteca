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
    
    /**
     * Constructor no vacio de Abonado
     * @param jsonAbonado
     * @param jsonPersona
     */
    protected Abonado(String jsonAbonado,String jsonPersona) {
        /*Conversion de String a Json*/
        super(jsonPersona);
        
        StringReader strReader=new StringReader(jsonAbonado);
        JsonReader jReader=Json.createReader(strReader);
        JsonObject jsonObject=jReader.readObject();
 
        setNumeroAbonado(jsonObject.getInt("numAbonado"));
        setOpenIdRef(jsonObject.getString("openIdRef"));
        
    }

    /**
     * Se obtiene el numero de abonado
     * @return el numero de abonado
     */
    public int getNumeroAbonado() {
        return numeroAbonado;
    }
    /**
     * Establece eñ numero de abonado
     * @param numeroAbondo 
     */
    private void setNumeroAbonado(int numeroAbondo) {
        this.numeroAbonado = numeroAbondo;
    }

    /**
     * Se obtiene el identificador de la referencia al Abonado
     * @return un identificador de la referencia
     */
    public String getOpenIdRef() {
        return openIdRef;
    }
    /**
     * Establece un identificador de la referncia al Abonado
     * @param openIdRef 
     */
    private void setOpenIdRef(String openIdRef) {
        this.openIdRef = openIdRef;
    }
  
    /**
     * Se obtiene un Abonado
     * @param numeroAbonado
     * @return un Abonado
     * @throws BDException
     */
    protected static Abonado obtenerAbonado (int numeroAbonado) throws BDException{
        String jsonAb=GestorPersistenciaAbonado.getAbonadoByNumeroAbonado(numeroAbonado);
        Abonado ab=null;
        
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
