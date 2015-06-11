/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import excepciones.BDException;
import excepciones.CompraNotFoundException;

/**
 *
 * @author nurcanc
 */
public class ContCURegistrarCompra {
    /**
     * Se comprueba que el identificador de compra es correcto o no
     * @param idCompra
     * @return el identificador de compra 
     * @throws excepciones.CompraNotFoundException 
     */
    public static Compra comprobarIDCompra(int idCompra) throws CompraNotFoundException{
        Compra comp;
        try{    
            // Obtenemos el identificador de compra, si no existe, devuelve null y gestionamos el error
            comp= Compra.obtenerCompra(idCompra);
            if(comp==null){
                throw new CompraNotFoundException("El identificador de compra no es correcto");
            }
            System.out.println("El identificador de compra existe y es válido");
            
        }catch(BDException ex){
            throw new CompraNotFoundException("Identificador de compra no Encontrado: "+ex.getMessage());   
        }
        // Devolvemos el identificador de compra
        return comp;
    }
}
