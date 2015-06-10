/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import datos.GestorPersistenciaEmpleado;
import datos.GestorPersistenciaPersona;
import excepciones.BDException;
import java.io.StringReader;
import java.sql.Date;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author ruben
 */
public class Empleado extends Persona{
    private String login;
    private Date fechaInicio;
    private TipoEmpleado tipoEmpleado;

    /**
     * Constructir no vacio de Empleado
     * @param jsonEmp
     * @param jsonPersona
     */
    protected Empleado(String jsonEmp,String jsonPersona) {
        /*Conversion de String a Json*/
        super(jsonPersona);
        StringReader strReader=new StringReader(jsonEmp);
        JsonReader jReader=Json.createReader(strReader);
        JsonObject jsonObject=jReader.readObject();
        
        setLogin(jsonObject.getString("login"));
        Date fechaInicio=Date.valueOf(jsonObject.getString("fechaInicio"));
        setFechaInicio(fechaInicio);
        String tipoEmp=jsonObject.getString("tipoEmpleado");
        TipoEmpleado tipoEmpleado=TipoEmpleado.getTipo(tipoEmp);
        setTipoEmpleado(tipoEmpleado); 
        
    }

    /**
     * Se obtiene el tipo de empleado
     * @return el tipo de empleado
     */
    public TipoEmpleado getTipoEmpleado() {
        return tipoEmpleado;
    }
    /**
     * Establece el tipo de empleado
     * @param tipoEmpleado 
     */
    private void setTipoEmpleado(TipoEmpleado tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    /**
     * Se obtiene el login del Empleado
     * @return el login de Empleado
     */
    public String getLogin() {
        return login;
    }
    /**
     * Establece el login del Empleado
     * @param login 
     */
    private void setLogin(String login) {
        this.login = login;
    }

    /**
     * Se obtiene la fecha de inicio de Empleado
     * @return la fecha de inicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }
    /**
     * Establece la fecha de inicio de Empleado
     * @param fechaInicio 
     */
    private void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    
    /**
     * Se obtiene el empleado mediante login y password
     * @param login
     * @param password
     * @return el empleado 
     * @throws BDException
     */
    protected static Empleado obtenerEmpleado (String login, String password) throws BDException{
        String jsonEmp=GestorPersistenciaEmpleado.getEmpleadoByLogin(login, password);
        Empleado emp=null;
        
        /*Creación del empleado y recuperacion de la persona*/
        if(jsonEmp!=null){
            StringReader strReader=new StringReader(jsonEmp);
            JsonReader jReader=Json.createReader(strReader);
            JsonObject jsonObject=jReader.readObject();
            
            String jsonPersona=GestorPersistenciaPersona.getPersonaByNif(jsonObject.getString("nif"));
            emp=new Empleado(jsonEmp,jsonPersona);
        }
              
        return emp;
    }
}
