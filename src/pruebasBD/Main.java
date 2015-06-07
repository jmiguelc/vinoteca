/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebasBD;

import datos.GestorPersistenciaFactura;
import excepciones.BDException;
import java.sql.Date;

/**
 *
 * @author monrae
 */
public class Main {
    public static void main(String[] args) {
           
        String str;
        try {
            Date fecha=Date.valueOf("2015-5-1");
            Date fecha2=Date.valueOf("2012-10-29");
            System.out.println(fecha2);
            //str = GestorPersistenciaEmpleado.getEmpleadoByLogin("rubhern", "1234");
            str=GestorPersistenciaFactura.recuperarFacturasVencidas(fecha);
            //str=GestorPersistenciaPedido.recuperarPedidosByFactura("");
            System.out.println(str);
        } catch ( BDException ex) {
           ex.printStackTrace();
        }

    }
}
