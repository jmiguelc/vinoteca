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
    
    // Método para comprobar que el número de abonado es correcto
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
    
    // Método que comprueba si hay alguna factura vencida
    public static void compruebaPagos(String nif)throws AbNotPaidException, PedidosNotFoundException{
        try{
            // Obtenemos todos los pedidos del abonado
            ArrayList<Pedido> pedidos = Pedido.getPedidosAbonado(nif);
            // Comprobamos si las facturas estan vencidas con su numero de factura incluida en los pedidos
                for(Pedido p: pedidos){
                    if(Factura.comprobarFacturaVencida(p.getNumFactura()))
                        throw new AbNotPaidException("Tiene facturas vencidas, no puede realizar otro pedido");
                }
            System.out.println("No tiene facturas pendientes");
        }catch(BDException ex){
            throw new PedidosNotFoundException("Error al buscar los pedidos: "+ ex.getMessage());
        }
    }
   /* public static String comprobarPedido(int ref, int cant){
        Pedido p;
        
        try{
         p = Pedido.    
            
        }catch(){
        }
    }*/
}
