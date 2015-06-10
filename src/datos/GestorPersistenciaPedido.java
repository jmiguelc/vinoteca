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
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonWriter;

/**
 *
 * @author monrae
 */
public class GestorPersistenciaPedido {
    
    /**
     * Se obtiene el pedido por el numero de factura
     * @param numeroFactura
     * @return el pedido buscado por el numero de factura
     * @throws BDException
     */
    public static String getPedidosByFactura(int numeroFactura) throws BDException{
        ResultSet rs;
        String sql = "SELECT * FROM APP.PEDIDO WHERE NUMEROFACTURA="+numeroFactura;
        
        try{
            /*Lectura de la BD y creación de la cadena Json*/
            rs = ConexionBD.creaInstancia().ejecutaQuery(sql);
            JsonArrayBuilder jsonPedidos= Json.createArrayBuilder();
            while(rs.next()){
                JsonObject jsonObj=Json.createObjectBuilder()
                .add("numeroPedido",rs.getInt("NUMERO"))
                .add("fechaRealizacion",rs.getString("FECHAREALIZACION"))
                .add("notaEntrega",rs.getString("NOTAENTREGA"))
                .add("importe",rs.getFloat("IMPORTE"))
                .add("fechaRecepcion",rs.getString("FECHARECEPCION"))
                .add("fechaEntrega",rs.getString("FECHAENTREGA"))
                .add("estado",rs.getString("ESTADO"))
                .add("numeroAbonado",rs.getInt("NUMEROABONADO"))
                .add("numeroFactura",rs.getInt("NUMEROFACTURA"))
                .build();
                
                jsonPedidos.add(jsonObj);
            }
            
            /*Conversion de Json a String*/
            StringWriter jsonstr=new StringWriter();
            JsonWriter writer = Json.createWriter(jsonstr);
            writer.writeArray(jsonPedidos.build());

            return jsonstr.toString();
            
        }catch(SQLException e){
            throw new BDException(e.getMessage());
        }
       
    }
}