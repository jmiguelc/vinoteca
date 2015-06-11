/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import datos.GestorPersistenciaBodega;
import datos.GestorPersistenciaPersona;
import excepciones.BDException;
import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author monrae
 */
public class Bodega {
    private String nombre;
    private String cif;
    private String direccion;

    public Bodega(String jsonBodega) {
        StringReader strReader=new StringReader(jsonBodega);
        JsonReader jReader=Json.createReader(strReader);
        JsonObject jsonObject=jReader.readObject();
        
        setNombre(jsonObject.getString("nombre"));
        setCif(jsonObject.getString("cif"));
        setDireccion(jsonObject.getString("direccion"));
    
    }

    
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCif() {
        return cif;
    }

    private void setCif(String cif) {
        this.cif = cif;
    }

    public String getDireccion() {
        return direccion;
    }

    private void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public static Bodega obtenerBodega(int idBodega) throws BDException{
        String jsonBodega=GestorPersistenciaBodega.getBodegaByidBodega(idBodega);
        Bodega bodega=new Bodega(jsonBodega);
        
        return bodega;
    }
}
