/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebasBD;

import datos.GestorPersistenciaFactura;
import datos.GestorPersistenciaPedido;
import excepciones.BDException;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Date;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonString;
import javax.json.JsonWriter;

/**
 *
 * @author monrae
 */
public class Main {
    public static void main(String[] args) {
           
        String str,str2;
        try {
            Date fecha=Date.valueOf("2015-5-1");
            Date fecha2=Date.valueOf("2012-10-29");
            System.out.println(fecha2);
            //str = GestorPersistenciaEmpleado.getEmpleadoByLogin("rubhern", "1234");
            //System.out.println(str);
            str=GestorPersistenciaFactura.recuperarFacturasVencidas(fecha);
            System.out.println(str);
            str=GestorPersistenciaPedido.recuperarPedidosByFactura(1);
            System.out.println(str);
            
            StringReader strReader=new StringReader(str);
            JsonReader jReader=Json.createReader(strReader);
            JsonArray jsonArray=jReader.readArray();
            
            for(int i=0;i<jsonArray.size();i++){
                str2 = jsonArray.getJsonObject(i).toString();
              
                System.out.println(str2);
            }
            
            
            
            
        } catch ( BDException ex) {
           ex.printStackTrace();
        }

    }
}
