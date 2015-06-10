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
    
    /**
     * Se comprueba si se realiza Identificarse correctamente.
     * @param login
     * @param password
     * @return un tipo de empleado
     * @throws EmpNotFoundException
     */
    public static TipoEmpleado identificarse(String login, String password) throws EmpNotFoundException{
        Empleado emp;
        TipoEmpleado tipo=null;
        try{    
            emp = Empleado.obtenerEmpleado(login, password);
            if(emp==null){
                throw new EmpNotFoundException("Login o password Incorrectos.");
            }
            System.out.println("El login se ha realizado correctamente");
            tipo=emp.getTipoEmpleado();
        }catch(BDException ex){
            throw new EmpNotFoundException("Empleado no Encontrado: "+ex.getMessage());   
        }
        return tipo;
    }
    
}
