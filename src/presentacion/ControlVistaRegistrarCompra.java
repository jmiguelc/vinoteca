/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import dominio.Bodega;
import dominio.Compra;
import dominio.ContCURegistrarCompra;
import dominio.LineaCompra;
import dominio.Referencia;
import excepciones.LineaCompraNotFoundException;
import excepciones.ReferenciaNotFoundException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

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
           ArrayList<Referencia> listaReferencias=new ArrayList<>();
           ArrayList<LineaCompra> lineaCompras;
           int idCompra = Integer.parseInt(vista.getIdCompra());
           Compra compra=ContCURegistrarCompra.comprobarIDCompra(idCompra);
           lineaCompras=compra.getLineaCompras();
           for(LineaCompra lineaCompra:lineaCompras){
            Referencia ref=ContCURegistrarCompra.obtenerInfRef(lineaCompra.getIdLineaCompra());
            if (ref!=null){
                listaReferencias.add(ref);
            }
           }
           vista.setIdCompraOutLabel(idCompra);
           Bodega bodega=compra.getBodega();
           vista.setNombreBodegaLabel(bodega.getNombre());
           vista.showInforme(compra,listaReferencias);
           
       }catch(NumberFormatException ex){
            vista.lanzaError("Formato de identificador de Compra Incorrecto"); 
       }catch(LineaCompraNotFoundException | ReferenciaNotFoundException ex){
           vista.lanzaError(ex.getMessage());
       }
    }
    
    protected void lineaCompraSeleccionada(int codigo, boolean esCajas, int contenido, 
            double importe, boolean disponible,int unidades){
        try{
            ContCURegistrarCompra.lineaCompraRecibida(codigo);
             
            Date today =Date.valueOf(LocalDate.now());
            
            ContCURegistrarCompra.recepcionLineaCompra(codigo,today);
            
            
        }catch(LineaCompraNotFoundException ex){
           vista.lanzaError(ex.getMessage());
       }
    }
}
