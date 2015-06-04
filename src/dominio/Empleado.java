/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import datos.GestorPersistenciaEmpleado;
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
public class Empleado {
    private String login;
    private Date fechaInicio;
    private TipoEmpleado tipoEmpleado;

    protected Empleado(String jsonStr) {
        /*Conversion de String a Json*/
        StringReader strReader=new StringReader(jsonStr);
        JsonReader jReader=Json.createReader(strReader);
        JsonObject jsonObject=jReader.readObject();
        
        setLogin(jsonObject.getString("LOGIN"));
        Date fechaInicio=Date.valueOf(jsonObject.getString("FECHAINICIO"));
        setFechaInicio(fechaInicio);
        String tipoEmp=jsonObject.getString("TIPOEMPLEADO");
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
        if(jsonEmp!=null) emp=new Empleado(jsonEmp);
              
        return emp;
    }
}
