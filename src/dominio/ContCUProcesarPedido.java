/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import excepciones.AbNotFoundException;
import excepciones.AbNotPaidException;
import excepciones.BDException;
import excepciones.PedidosNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author ruben
 */
public class ContCUProcesarPedido {
    
    /**
     * Comprobar que el número de abonado es correcto
     * @param numAbonado
     * @return el abonado
     * @throws AbNotFoundException
     */
        public static Abonado comprobarAbonado(int numAbonado) throws AbNotFoundException{
        Abonado ab;
        try{    
            // Obtenemos el abonado, si no existe, devuelve null y gestionamos el error
            ab = Abonado.obtenerAbonado(numAbonado);
            if(ab==null){
                throw new AbNotFoundException("El número de abonado no es correcto");
            }
            System.out.println("Existe el abonado");
        }catch(BDException ex){
            throw new AbNotFoundException("Abonado no Encontrado: "+ex.getMessage());   
        }
        // Devolvemos el abonado
        return ab;
    }
    
    /**
     * Comprueba si hay alguna factura vencida
     * @param numAbonado
     * @throws AbNotPaidException
     * @throws PedidosNotFoundException
     */
        public static void compruebaPagos(int numAbonado)throws AbNotPaidException, PedidosNotFoundException{
        try{
            String numFactura;
            // Obtenemos todos los pedidos del abonado
            ArrayList<Pedido> pedidos = Pedido.getPedidosAbonado(numAbonado);
            // Comprobamos si las facturas estan vencidas con su numero de factura incluida en los pedidos
                for(Pedido p: pedidos){
                    numFactura = Pedido.getNumFactura(p.getNumeroPedido());
                    if(Factura.comprobarFacturaVencida(numFactura))
                        throw new AbNotPaidException("Tiene facturas vencidas, no puede realizar otro pedido");
                }
            System.out.println("No tiene facturas pendientes");
        }catch(BDException ex){
            throw new PedidosNotFoundException("Error al buscar los pedidos: "+ ex.getMessage());
        }
    }
    // Método para comprobar si la referencia del pedido existe    
    public static String comprobarPedido(int ref, int cant){
        Referencia referencia;
        
        try{
         referencia = Referencia.getReferencia(ref);
            
        }catch(){
        }
    }
}
