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
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonWriter;

/**
 *
 * @author monrae
 */
public class GestorPersistenciaFactura {
    public static String getFacturasVencidasByFecha(Date fecha) throws BDException{
        ResultSet rs;
        String sql = "SELECT * FROM APP.FACTURA WHERE ESTADO='V' AND FECHAEMISION<'"+fecha.toString()+"'";
        
       try{
            /*Lectura de la BD y creación de la cadena Json*/
            rs = ConexionBD.creaInstancia().ejecutaQuery(sql);
            JsonArrayBuilder jsonFacturas= Json.createArrayBuilder();
            while(rs.next()){
                JsonObject jsonObj=Json.createObjectBuilder()
                .add("numeroFactura",rs.getInt("NUMEROFACTURA"))
                .add("fechaEmision",rs.getString("FECHAEMISION"))
                .add("importe",rs.getFloat("IMPORTE"))
                .add("estado", rs.getString("ESTADO"))
                .build();
                
                jsonFacturas.add(jsonObj);
            }
            
            /*Conversion de Json a String*/
            StringWriter jsonstr=new StringWriter();
            JsonWriter writer = Json.createWriter(jsonstr);
            writer.writeArray(jsonFacturas.build());

            return jsonstr.toString();
            
        }catch(SQLException e){
            throw new BDException(e.getMessage());
        }
       
    }
    
    public static boolean comprobarFacturaVencida(String numFactura)throws BDException{
        ResultSet rs;
        String sql = "SELECT ESTADO FROM APP.FACTURA WHERE NUMEROFACTURA='"+ numFactura +"'";
        String estado;
        
        // Leemos de la BD y comprobamos si la factura está vencida o no
        try{
            rs = ConexionBD.creaInstancia().ejecutaQuery(sql);
            while(rs.next()){
                estado = rs.getString("NUMEROFACTURA");
                if(estado.equals('V'))
                    return true;
                else
                    return false;
            }
        }catch(SQLException e){
            throw new BDException(e.getMessage());
        }
        return false;
    }
}
