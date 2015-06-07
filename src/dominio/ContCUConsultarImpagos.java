/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author monrae
 */
public class ContCUConsultarImpagos {
    public static ArrayList<Factura> obtenerInformacion(Date fecha){
        ArrayList<Factura> facturasVencidas=null;
        facturasVencidas=Factura.obtenerFacturasVencidas(fecha);
        
                
        return facturasVencidas;
    } 
}
