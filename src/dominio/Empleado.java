/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.sql.Date;

/**
 *
 * @author ruben
 */
public class Empleado {
    private String login;
    private Date fechaInicio;
    private TipoEmpleado tipoEmpleado;

    public Empleado(String login, Date fechaInicio, TipoEmpleado tipoEmpleado) {
        this.login = login;
        this.fechaInicio = fechaInicio;
        this.tipoEmpleado = tipoEmpleado;
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
    
    /*public Empleado obtenerEmpleado (String login, String password){
        
    }*/
}
