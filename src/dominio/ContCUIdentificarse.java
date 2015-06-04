/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import excepciones.BDException;
import excepciones.EmpNotFoundException;

/**
 *
 * @author ruben
 */
public class ContCUIdentificarse {
    
    public static TipoEmpleado identificarse(String login, String password) throws BDException, EmpNotFoundException{
        Empleado emp;
        emp = Empleado.obtenerEmpleado(login, password);
        if (emp==null){
            throw new EmpNotFoundException();
        }
        return emp.getTipoEmpleado();
    }
}
