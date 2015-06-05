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
    public static String comprobarAbonado(int numAb) throws AbNotFoundException{
        Abonado ab;
        String nif=null;
        try{    
            ab = Abonado.obtenerAbonado(numAb);
            if(ab==null){
                throw new AbNotFoundException("El número de abonado no es correcto");
            }
            System.out.println("Existe el abonado");
            nif=ab.getNif();
        }catch(BDException ex){
            throw new AbNotFoundException("Abonado no Encontrado: "+ex.getMessage());   
        }
        return nif;
    }
}
