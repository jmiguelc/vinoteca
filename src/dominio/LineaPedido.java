/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

/**
 *
 * @author ruben
 */
public class LineaPedido {
    private int unidades;
    private boolean completada;
    private Referencia ref;

    public LineaPedido(int unidades, boolean completada, Referencia ref) {
        this.unidades = unidades;
        this.completada = completada;
        this.ref = ref;
    }
    
    
}
