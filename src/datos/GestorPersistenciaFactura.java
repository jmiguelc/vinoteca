/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import excepciones.BDException;
import java.io.StringWriter;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonWriter;

/**
 *
 * @author monrae
 */
public class GestorPersistenciaFactura {
    public static String recuperarFacturasVencidas(Date fecha) throws BDException{
        ResultSet rs;
        String sql = "SELECT * FROM APP.FACTURA WHERE ESTADO='V' AND FECHAEMISION<'"+fecha.toString()+"'";
        
       try{
            /*Lectura de la BD y creación de la cadena Json*/
            rs = ConexionBD.creaInstancia().ejecutaQuery(sql);
            JsonArray jsonFacturas= Json.createArrayBuilder().build();
            while(rs.next()){
                JsonObject jsonObj=Json.createObjectBuilder()
                .add("numeroFactura",rs.getString("NUMEROFACTURA"))
                .add("fechaEmision",rs.getString("FECHAEMISION"))
                .add("importe",rs.getString("IMPORTE"))
                .add("fechaPago",rs.getString("FECHAPAGO"))
                .add("idExtractoBancario",rs.getString("NUMEROFACTURA"))
                .build();
                
                jsonFacturas.add(jsonObj);
            }
            
            /*Conversion de Json a String*/
            StringWriter jsonstr=new StringWriter();
            JsonWriter writer = Json.createWriter(jsonstr);
            writer.writeArray(jsonFacturas);

            return jsonstr.toString();
            
        }catch(SQLException e){
            throw new BDException(e.getMessage());
        }
       
    }
}
