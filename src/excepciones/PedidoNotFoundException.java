/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excepciones;

/**
 *
 * @author ruben
 */
public class PedidoNotFoundException extends Exception{
    public PedidoNotFoundException(String msg) {
        super(msg);
    }
}
