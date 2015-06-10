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
 * @author nurcanc
 */
public class ContCURegistrarCompra {
    
    public static Compra comprobarIDCompra(int idCompra) throws AbNotFoundException{
        Compra comp;
        try{    
            // Obtenemos el identificador de compra, si no existe, devuelve null y gestionamos el error
            comp= Compra.obtenerCompra(idCompra);
            if(comp==null){
                throw new AbNotFoundException("El identificador de compra no es correcto");
            }
            System.out.println("El identificador de compra existe y es válido");
            
        }catch(BDException ex){
            throw new AbNotFoundException("identificador no Encontrado: "+ex.getMessage());   
        }
        // Devolvemos el identificador de compra
        return comp;
    }
}
