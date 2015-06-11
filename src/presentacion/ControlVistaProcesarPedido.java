/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import dominio.Abonado;
import dominio.ContCUProcesarPedido;
import dominio.Pedido;
import dominio.Referencia;
import excepciones.AbNotFoundException;
import excepciones.AbNotPaidException;
import excepciones.BDException;
import excepciones.RefNotAvaliableException;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author nurcanc
 */
public class ControlVistaProcesarPedido {
    
    protected VistaCUProcesarPedido vista;
    protected Abonado ab;
    protected Pedido p;
    protected Referencia ref;
    /**
     * Constructor no vacio de ControlVistaProcesarPedido
     * @param vista 
     */
    public ControlVistaProcesarPedido(VistaCUProcesarPedido vista) {
        this.vista=vista;    
    }
    
    /**
     * Comprobamos que el numero de abonado existe y mostramos la informacion
     */
    protected void comprobarAbonado(){
        
        try{
            int numAbonado = Integer.parseInt(vista.getNumAbonado());
            ab = ContCUProcesarPedido.comprobarAbonado(numAbonado);
            vista.setEmailLabel(ab.getEmail());
            vista.setNombreLabel(ab.getNombre());
            vista.setTelefonoLabel(ab.getTelefono());
            vista.setapellidoLabel(ab.getApellidos());
            vista.setConfirmarEnabled(true);
        }catch(AbNotFoundException ex){
            vista.lanzaError(ex.getMessage());
        }catch(NumberFormatException ex){
            vista.lanzaError("Formato de Número de Abonado Incorrecto");
        }
    }
     
    /**
     * Comprobamos que el abonado no tenga pagos pendientes
     */
    protected void comprobarPagosPendientes() {
        Date today =Date.valueOf(LocalDate.now());
        
        try{
            int numAbonado =Integer.parseInt(vista.getNumAbonado());
            ContCUProcesarPedido.compruebaPagos(numAbonado);
            p = ContCUProcesarPedido.nuevoPedido(today, ab);
            vista.setComprobarEnabled(false);
            vista.setConfirmarEnabled(false);
            vista.setAddEnabled();
        }catch(AbNotPaidException | BDException ex){
            vista.lanzaError(ex.getMessage());
        }
    }
   /**
    * Comprobamos que la referencia y la cantidad existen
    */ 
   protected void comprobarPedido(){

        try{
            int referencia = Integer.parseInt(vista.getReferencia());
            int cantidad = Integer.parseInt(vista.getCantidad());
            ref = ContCUProcesarPedido.comprobarReferencia(referencia);
            ContCUProcesarPedido.nuevaLineaPedido( cantidad, ref, p);
            vista.setTerminarEnabled(true);
                
        }catch(RefNotAvaliableException | BDException ex){
            vista.lanzaError(ex.getMessage());
        }catch(NumberFormatException ex){
            vista.lanzaError("Formato de Referencia o Cantidad Erroneos");
        }
    }
   /**
    * Comprobamos que el pedido ha finalizado
    */
   protected void finalizarPedido(){
       try{
            ContCUProcesarPedido.finalizarPedido(p);
       }catch(BDException ex){
           vista.lanzaError(ex.getMessage());
       }
   }
}