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

    public Empleado() {
  
    }

    public TipoEmpleado getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(TipoEmpleado tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    
    public Empleado obtenerEmpleado (String login, String password) throws ClassNotFoundException, BDException{
        String jsonStr=GestorPersistenciaEmpleado.getEmpleadoByLogin(login, password);
        Empleado emp=null;
        
        if(jsonStr!=null){
            /*Conversion de String a Json*/
            StringReader strReader=new StringReader(jsonStr);
            JsonReader jReader=Json.createReader(strReader);
            JsonObject jsonObject=jReader.readObject();
        
            /*Creación del empleado*/
            emp=new Empleado();
            emp.setLogin(jsonObject.getString("LOGIN"));
            Date fechaInicio=Date.valueOf(jsonObject.getString("FECHAINICIO"));
            emp.setFechaInicio(fechaInicio);
            String tipoEmp=jsonObject.getString("TIPOEMPLEADO");
            TipoEmpleado tipoEmpleado=TipoEmpleado.getTipo(tipoEmp);
            emp.setTipoEmpleado(tipoEmpleado);  
        }
        
        return emp;
    }
}
