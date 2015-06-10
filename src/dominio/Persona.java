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

    /**
     * Constructor no vacio de Persona
     * @param jsonPersona
     */
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

    /**
     * Se obtiene el nombre de Persona
     * @return un string con el nombre de persona
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Establece el nombre de Persona
     * @param nombre 
     */
    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *Se obtiene los apellidos de Persona
     * @return un string con los apellidos de una persona
     */
    public String getApellidos() {
        return apellidos;
    }
    /**
     * Establece los apellidos de Persona
     * @param apellidos 
     */
    private void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     *Se obtiene el nif de Persona
     * @return un string con el nif de una persona
     */
    public String getNif() {
        return nif;
    }
    /**
     * Establece el nif de Persona
     * @param nif 
     */
    private void setNif(String nif) {
        this.nif = nif;
    }

    /**
     *Se obtiene el correo electronico de Persona
     * @return un string con el correo electronico de una persona
     */
    public String getEmail() {
        return email;
    }
    /**
     * Establece el correo electronico de Persona
     * @param email
     */
    private void setEmail(String email) {
        this.email = email;
    }

    /**
     *Se obtiene el telefono de Persona
     * @return un string con el telefono de una persona
     */
    public String getTelefono() {
        return telefono;
    }
    /**
     * Establece el telefono de Persona
     * @param telefono 
     */
    private void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     *Se obtiene la direccion de Persona
     * @return un string con la direccion de una persona
     */
    public String getDireccion() {
        return direccion;
    }
    /**
     * Establece la direccion de Persona
     * @param direccion 
     */
    private void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     *Se obtiene la cuenta bancaria de Persona
     * @return un string con la cuenta bancaria de una persona
     */
    public String getCuentaBancaria() {
        return cuentaBancaria;
    }
    /**
     * Establece la cuenta bancaria de Persona
     * @param cuentaBancaria 
     */
    private void setCuentaBancaria(String cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }
        
    /**
     *Se obtiene la persona por el nif de Persona
     * @param nif
     * @return una Persona
     * @throws BDException
     */
    protected static Persona obtenerPersona (String nif) throws BDException{
        String jsonPersona=GestorPersistenciaPersona.getPersonaByNif(nif);
        Persona pers=null;
        
        /*Creación del empleado*/
        if(jsonPersona!=null) pers=new Persona(jsonPersona);
              
        return pers;
    } 
}
