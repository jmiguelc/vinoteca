/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import dominio.ContCUIdentificarse;
import excepciones.BDException;
import excepciones.EmpNotFoundException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author monrae
 */
public class ControlVistaIdentificarse {

    protected VistaCUIdentificarse vista;
    
    public ControlVistaIdentificarse(VistaCUIdentificarse vista) {
        this.vista=vista;
    }
    
    
    void procesaIdentificacion(){
        /*Traemos el contenido de los elementos*/
        String login=vista.getUsuario();
        String password=Arrays.toString(vista.getPassword());
        
        try{
        ContCUIdentificarse.identificarse(login, password);
        }catch(BDException | EmpNotFoundException ex){
            vista.lanzaError(ex.getMessage());
        }
    }
}
