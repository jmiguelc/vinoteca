/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import datos.GestorPersistenciaPersona;
import excepciones.BDException;
import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author nurcanc
 */
public class Persona {
    private String nombre;
    private String apellidos;
    private String nif;
    private String email;
    private String telefono;
    private String direccion;
    private String cuentaBancaria;

    public Persona(String jsonPersona) {
         /*Conversion de String a Json*/
        StringReader strReader=new StringReader(jsonPersona);
        JsonReader jReader=Json.createReader(strReader);
        JsonObject jsonObject=jReader.readObject();
        
        setNombre(jsonObject.getString("nombre"));
        setApellidos(jsonObject.getString("apellidos"));
        setNif(jsonObject.getString("nif"));
        setEmail(jsonObject.getString("email"));
        setTelefono(jsonObject.getString("telefono"));
        setDireccion(jsonObject.getString("direccion"));
        setCuentaBancaria(jsonObject.getString("cuentaBancaria"));

    }

    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    private void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNif() {
        return nif;
    }

    private void setNif(String nif) {
        this.nif = nif;
    }

    public String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    private void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    private void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCuentaBancaria() {
        return cuentaBancaria;
    }

    private void setCuentaBancaria(String cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }
        
    protected static Persona obtenerPersona (String nif) throws BDException{
        String jsonPersona=GestorPersistenciaPersona.getPersonaByNif(nif);
        Persona pers=null;
        
        /*Creación del empleado*/
        if(jsonPersona!=null) pers=new Persona(jsonPersona);
              
        return pers;
    } 
}
