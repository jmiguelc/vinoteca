/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import excepciones.BDException;
import java.io.StringWriter;
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
public class GestorPersistenciaPedido {
    public static String recuperarPedidosByFactura(String numeroFactura) throws BDException{
        ResultSet rs;
        String sql = "SELECT * FROM APP.PEDIDO WHERE NUMEROFACTURA='"+numeroFactura+"'";
        
        try{
            /*Lectura de la BD y creación de la cadena Json*/
            rs = ConexionBD.creaInstancia().ejecutaQuery(sql);
            JsonArray jsonPedidos= Json.createArrayBuilder().build();
            while(rs.next()){
                JsonObject jsonObj=Json.createObjectBuilder()
                .add("numeroPedido",rs.getString("NUMEROPEDIDO"))
                .add("fechaRealizacion",rs.getString("FECHAREALIZACION"))
                .add("notaEntrega",rs.getString("NOTAENTREGA"))
                .add("importe",rs.getString("IMPORTE"))
                .add("fechaRecepcion",rs.getString("FECHARECEPCION"))
                .add("fechaEntrega",rs.getString("FECHAENTREGA"))
                .add("estado",rs.getString("ESTADO"))
                .add("nifAbonado",rs.getString("NIFABONADO"))
                .add("numeroFactura",rs.getString("NUMEROFACTURA"))
                .build();
                
                jsonPedidos.add(jsonObj);
            }
            
            /*Conversion de Json a String*/
            StringWriter jsonstr=new StringWriter();
            JsonWriter writer = Json.createWriter(jsonstr);
            writer.writeArray(jsonPedidos);

            return jsonstr.toString();
            
        }catch(SQLException e){
            throw new BDException(e.getMessage());
        }
       
    }
}
