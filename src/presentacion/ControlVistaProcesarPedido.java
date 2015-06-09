/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import dominio.ContCUProcesarPedido;
import excepciones.AbNotFoundException;

/**
 *
 * @author nurcanc
 */
public class ControlVistaProcesarPedido {
    
    protected VistaCUProcesarPedido vista;

    public ControlVistaProcesarPedido(VistaCUProcesarPedido vista) {
        this.vista=vista;
    
    }
    
    protected void comprobarAbonado(){

        int numAbonado = vista.getNumAbonado();
        
        try{
            ContCUProcesarPedido.comprobarAbonado(numAbonado);
        }catch(AbNotFoundException ex){
            vista.lanzaError(ex.getMessage());
        }
    }
    //falta crear una funcion para el pedido en ContCUProcesarPedido
    protected void comprobarPedido(){
        int refencia = vista.getReferencia();
        int cantidad = vista.getCantidad();
    }
}
