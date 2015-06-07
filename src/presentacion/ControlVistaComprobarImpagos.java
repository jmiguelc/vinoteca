/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.security.PrivilegedActionException;
import java.text.ParseException;
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
    
    protected void procesarDatosConsulta(){
        try{
            Date fecha=v.getFecha();
            
            if(!compararFechas(fecha)){
                v.lanzaError("El plazo introducido es menor a 30 dias");
            }
            
            /*comprobar impagos*/
            
            
        }catch(ParseException|PrivilegedActionException ex){
            v.lanzaError("Error Formato de fecha Incorrecto");
        }
        
        
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