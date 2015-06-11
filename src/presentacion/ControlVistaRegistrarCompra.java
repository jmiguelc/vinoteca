/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import dominio.Bodega;
import dominio.Compra;
import dominio.ContCURegistrarCompra;
import excepciones.CompraNotFoundException;

/**
 *
 * @author nurcanc
 */
public class ControlVistaRegistrarCompra {

    protected VistaCURegistrarCompra vista;
    protected Compra comp;
    
    /**
     *Constructor no vacio de ControlVistaRegistrarCompra
     * @param vista
     */
    public ControlVistaRegistrarCompra(VistaCURegistrarCompra vista){
        this.vista=vista;
    }

    /**
     *
     */
    protected void comprobarIDCompra(){
       try{
           int idCompra = Integer.parseInt(vista.getIdCompra());
           Compra compra=ContCURegistrarCompra.comprobarIDCompra(idCompra);
           vista.setIdCompraOutLabel(idCompra);
           Bodega bodega=compra.getBodega();
           vista.setNombreBodegaLabel(bodega.getNombre());
           
           
       }catch(NumberFormatException ex){
            vista.lanzaError("Formato de identificador de Compra Incorrecto"); 
       }catch(CompraNotFoundException ex){
           vista.lanzaError(ex.getMessage());
       }
    }
}
