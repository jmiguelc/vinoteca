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

/**
 *
 * @author nurcanc
 */
public class ControlVistaProcesarPedido {
    
    protected VistaCUProcesarPedido vista;

    public ControlVistaProcesarPedido(VistaCUProcesarPedido vista) {
        this.vista=vista;
    
    }
    // Comprobamos que el numero de abonado existe y mostramos la informacion
    protected void comprobarAbonado(){

        int numAbonado = vista.getNumAbonado();
        Abonado ab = null;
        
        try{
            ab = ContCUProcesarPedido.comprobarAbonado(numAbonado);
            vista.setEmailLabel(ab.getEmail());
            vista.setNombreLabel(ab.getNombre());
            vista.setTelefonoLabel(ab.getTelefono());
            vista.setapellidoLabel(ab.getApellidos());
        }catch(AbNotFoundException ex){
            vista.lanzaError(ex.getMessage());
        }
    }
    
    // Comprobamos que el abonado no tenga pagos pendientes
    protected void comprobarPagosPendientes(){
        int numAbonado = vista.getNumAbonado();
        
        try{
            ContCUProcesarPedido.compruebaPagos(numAbonado);
        }catch(AbNotPaidException ex){
            vista.lanzaError(ex.getMessage);
        }
    }
    
    protected void comprobarPedido(){
        int refencia = vista.getReferencia();
        int cantidad = vista.getCantidad();
    }
}
