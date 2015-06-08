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
public class ControlVistaComprobarImpagos {
    
    protected VistaCUConsultarImpagos v;

    public ControlVistaComprobarImpagos(VistaCUConsultarImpagos v) {
        this.v=v;
    }
    
    protected ArrayList<Factura> procesarDatosConsulta(){
        ArrayList<Factura> facturas=null;
        try{    
            Date fecha=v.getFecha();
            java.sql.Date fechaSQL=new java.sql.Date(fecha.getTime());
            
            if(!compararFechas(fecha)){
                v.lanzaError("El plazo introducido es menor a 30 dias");
            }
            
            /*comprobar impagos*/
            facturas=ContCUConsultarImpagos.obtenerInformacion(fechaSQL);
            
        }catch(ParseException|PrivilegedActionException ex){
            v.lanzaError("Error Formato de fecha Incorrecto. " +ex.getMessage());
        } catch (BDException ex) {
            v.lanzaError(ex.getMessage());
        }
        
        return facturas;
    }
    
    /*Comparamos si hay 30 dias entre una fecha y la actual*/
    private static boolean compararFechas(Date fecha){
        Date fechaComp=new Date();
        boolean val=false;
        
        long mili=fechaComp.getTime()-fecha.getTime();
        long days=mili/(1000*3600*24);
        
        if (days>30) val=true;
        
        return val;
    }
    
}