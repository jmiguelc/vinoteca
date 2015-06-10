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
import javax.swing.JFrame;

/**
 *
 * @author nurcanc
 */
public class ControlVistaProcesarPedido {
    
    protected VistaCUProcesarPedido vista;
    protected VistaCrearLineaPedido vistaLP;
    protected Abonado ab;

    public ControlVistaProcesarPedido(VistaCUProcesarPedido vista) {
        this.vista=vista;    
    }
    
     public ControlVistaProcesarPedido(VistaCrearLineaPedido vistaLP) {
        this.vistaLP=vistaLP;
    }
    
    // Comprobamos que el numero de abonado existe y mostramos la informacion
    protected void comprobarAbonado(){

        int numAbonado = vista.getNumAbonado();
        
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
        JFrame v;
        
        try{
            ContCUProcesarPedido.compruebaPagos(numAbonado); 
            vista.dispose();
            v = new VistaCrearLineaPedido();
            v.setVisible(true);
        }catch(AbNotPaidException ex){
            vista.lanzaError(ex.getMessage());
        }catch(PedidosNotFoundException ex){
            vista.lanzaError(ex.getMessage());
        }
    }
    
    protected void comprobarPedido(){
        int refencia = vistaLP.getReferencia();
        int cantidad = vistaLP.getCantidad();
        
        
    }
}

