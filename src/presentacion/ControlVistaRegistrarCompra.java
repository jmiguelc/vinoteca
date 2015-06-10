/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import dominio.Compra;
import dominio.ContCURegistrarCompra;
import excepciones.AbNotFoundException;

/**
 *
 * @author nurcanc
 */
public class ControlVistaRegistrarCompra {
    
    protected VistaCURegistrarCompra vista;
    protected Compra comp;
    
    public ControlVistaRegistrarCompra(VistaCURegistrarCompra vista){
        this.vista=vista;
    }
    protected void comprobarIDCompra(){
       
    }
}
