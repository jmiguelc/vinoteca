/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import excepciones.AbNotFoundException;
import excepciones.BDException;

/**
 *
 * @author ruben
 */
public class ContCUProcesarPedido {
    
    public static Abonado comprobarAbonado(int numAbonado) throws AbNotFoundException{
        Abonado ab;
        try{    
            ab = Abonado.obtenerAbonado(numAbonado);
            if(ab==null){
                throw new AbNotFoundException("El número de abonado no es correcto");
            }
            System.out.println("Existe el abonado");
        }catch(BDException ex){
            throw new AbNotFoundException("Abonado no Encontrado: "+ex.getMessage());   
        }
        return ab;
    }
   /* public static String comprobarPedido(int ref, int cant){
        Pedido p;
        
        try{
         p = Pedido.    
            
        }catch(){
        }
    }*/
}
