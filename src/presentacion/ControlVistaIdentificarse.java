/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import dominio.ContCUIdentificarse;
import dominio.TipoEmpleado;
import excepciones.EmpNotFoundException;
import javax.swing.JFrame;

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
        TipoEmpleado tipoEmpleado;
        JFrame v;
        
        try{
            tipoEmpleado=ContCUIdentificarse.identificarse(login, password);
            switch(tipoEmpleado){
                case responsablePedidos:
                    v=new VistaOpcionesEncPedidos();
                    vista.dispose();
                    v.setVisible(true);
                    break;
                case responsableContabilidad:
                    v=new VistaOpcionesEncContabilidad();
                    vista.dispose();
                    v.setVisible(true);
                    break;
                case responsableAlmacen:
                    v=new VistaOpcionesEncAlmacen();
                    vista.dispose();
                    v.setVisible(true);
                    break;
            }
        }catch(EmpNotFoundException ex){
            vista.lanzaError(ex.getMessage());
        }
    }
}
