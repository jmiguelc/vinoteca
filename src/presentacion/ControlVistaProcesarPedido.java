/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import dominio.Abonado;
import dominio.ContCUProcesarPedido;
import excepciones.AbNotFoundException;
import excepciones.AbNotPaidException;
import excepciones.PedidosNotFoundException;

/**
 *
 * @author nurcanc
 */
public class ControlVistaProcesarPedido {
    
    protected VistaCUProcesarPedido vista;
    protected Abonado ab;

    public ControlVistaProcesarPedido(VistaCUProcesarPedido vista) {
        this.vista=vista;
    
    }
    // Comprobamos que el numero de abonado existe y mostramos la informacion
    protected void comprobarAbonado(){
        
        try{
            int numAbonado = Integer.parseInt(vista.getNumAbonado());
            ab = ContCUProcesarPedido.comprobarAbonado(numAbonado);
            vista.setEmailLabel(ab.getEmail());
            vista.setNombreLabel(ab.getNombre());
            vista.setTelefonoLabel(ab.getTelefono());
            vista.setapellidoLabel(ab.getApellidos());
            vista.setConfirmarEnabled();
        }catch(AbNotFoundException | NumberFormatException ex){
            vista.lanzaError(ex.getMessage());
        }
    }
    
  // Comprobamos que el abonado no tenga pagos pendientes
    protected void comprobarPagosPendientes(){
          
        try{
            int numAbonado =Integer.parseInt(vista.getNumAbonado());
            ContCUProcesarPedido.compruebaPagos(numAbonado);               
        }catch(AbNotPaidException ex){
            vista.lanzaError(ex.getMessage());
        }catch(PedidosNotFoundException ex){
            vista.lanzaError(ex.getMessage());
        }
    }
    
    protected void comprobarPedido(){
        int refencia = vista.getReferencia();
        int cantidad = vista.getCantidad();
    }
}

