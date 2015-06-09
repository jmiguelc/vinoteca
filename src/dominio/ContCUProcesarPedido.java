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
    public static boolean compruebaPagos(String nif)throws AbNotPaidException, PedidosNotFoundException{
        try{
            // Obtenemos todos los pedidos del abonado
            ArrayList<Pedido> pedidos = Pedido.getPedidosAbonado(nif);
            // Si no tiene pedidos entonces los pagos están bien
            if(pedidos == null)
                return true;
            // Obtenemos las facturas de los pedidos
            else{
                Factura f;
                
                for(Pedido p: pedidos){
                    
                }
            }
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
