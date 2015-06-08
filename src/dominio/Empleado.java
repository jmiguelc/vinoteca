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

    public TipoEmpleado getTipoEmpleado() {
        return tipoEmpleado;
    }

    private void setTipoEmpleado(TipoEmpleado tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }


    public String getLogin() {
        return login;
    }

    private void setLogin(String login) {
        this.login = login;
    }


    public Date getFechaInicio() {
        return fechaInicio;
    }

    private void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    
    protected static Empleado obtenerEmpleado (String login, String password) throws BDException{
        String jsonEmp=GestorPersistenciaEmpleado.getEmpleadoByLogin(login, password);
        Empleado emp=null;
        
        /*Creación del empleado*/
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
