/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import dominio.ContCUIdentificarse;
import excepciones.EmpNotFoundException;

/**
 *
 * @author monrae
 */
public class ControlVistaIdentificarse {

    protected VistaCUIdentificarse vista;
    
    protected ControlVistaIdentificarse(VistaCUIdentificarse vista) {
        this.vista=vista;
    }
    
    
    protected void procesaIdentificacion(){
        /*Traemos el contenido de los elementos*/
        String login=vista.getUsuario();
        String password=String.valueOf(vista.getPassword());
        
        try{
        ContCUIdentificarse.identificarse(login, password);
        }catch(EmpNotFoundException ex){
            vista.lanzaError(ex.getMessage());
        }
    }
}
