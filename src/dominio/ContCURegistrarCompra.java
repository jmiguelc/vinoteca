/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import excepciones.BDException;
import excepciones.LineaCompraNotFoundException;
import excepciones.ReferenciaNotFoundException;

/**
 *
 * @author nurcanc
 */
public class ContCURegistrarCompra {
    
    /**
     * Se comprueba que el identificador de compra es correcto o no
     * @param idCompra
     * @return el identificador de compra 
     * @throws excepciones.LineaCompraNotFoundException 
     */
    public static Compra comprobarIDCompra(int idCompra) throws LineaCompraNotFoundException{
        Compra compra;
        try{    
            // Obtenemos el identificador de compra, si no existe, devuelve null y gestionamos el error
            compra= Compra.obtenerCompra(idCompra);
            if(compra==null){
                throw new LineaCompraNotFoundException("El identificador de compra no es correcto");
            }
            System.out.println("El identificador de compra existe y es válido");
            
        }catch(BDException ex){
            throw new LineaCompraNotFoundException("Identificador de compra no Encontrado: "+ex.getMessage());   
        }
        // Devolvemos el identificador de compra
        return compra;
    }
    
    public static Referencia obtenerInfRef(int idLineaCompra) throws LineaCompraNotFoundException, ReferenciaNotFoundException{
        Referencia ref;
        try{    
            
            ref= Referencia.obtenerReferenciaByLineaPedido(idLineaCompra);
            if(ref==null){
                throw new ReferenciaNotFoundException("No se han encontrado referencias de la compra");
            }
            
        }catch(BDException ex){
            throw new ReferenciaNotFoundException("Identificador de compra no Encontrado: "+ex.getMessage());   
        }
        
        return ref;
    }
}
