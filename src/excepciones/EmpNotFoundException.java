/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package excepciones;

/**
 *
 * @author juacelo
 */
public class EmpNotFoundException extends Exception{
      public EmpNotFoundException() {
        super("Error Empleado no encontrado!");
    }
}
