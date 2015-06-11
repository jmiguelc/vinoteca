/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import dominio.ContCUConsultarImpagos;
import dominio.Factura;
import excepciones.BDException;
import java.security.PrivilegedActionException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author monrae
 */
public class ControlVistaConsultarImpagos {
    
    protected VistaCUConsultarImpagos vista;

    /**
     * Constructor no vacio de ControlVistaConsultarImpagos
     * @param v
     */
    public ControlVistaConsultarImpagos(VistaCUConsultarImpagos v) {
        this.vista=v;
    }
    
    /**
     * Se procesa los datos de una consulta 
     * para comprobar posibles impagos de facturas
     */
    protected void procesarDatosConsulta(){
        ArrayList<Factura> facturas;
        try{    
            Date fecha=vista.getFecha();
            java.sql.Date fechaSQL=new java.sql.Date(fecha.getTime());
            
            if(!compararFechas(fecha)){
                vista.lanzaAdvertencia("El plazo introducido es menor a 30 dias");
            }
            
            /*comprobar impagos*/
            facturas=ContCUConsultarImpagos.obtenerInformacion(fechaSQL);
            
            vista.showInforme(facturas);
            
        }catch(ParseException|PrivilegedActionException ex){
            vista.lanzaError("Error Formato de fecha Incorrecto. " +ex.getMessage());
        } catch (BDException ex) {
            vista.lanzaError(ex.getMessage());
        }
        
        
    }
    /**
     * Comparamos si hay 30 dias entre una fecha y la actual
     * @param fecha
     * @return un booleano si se cumple la condicion de la comparacion
     */
    private static boolean compararFechas(Date fecha){
        Date fechaComp=new Date();
        boolean val=false;
        
        long mili=fechaComp.getTime()-fecha.getTime();
        long days=mili/(1000*3600*24);
        
        if (days>30) val=true;
        
        return val;
    }
    
}