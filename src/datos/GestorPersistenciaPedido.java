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
    public static String recuperarPedidosAbonado(int numAbonado) throws BDException{
        ResultSet rs;
        String sql = "SELECT * FROM APP.PEDIDO WHERE NUMEROABONADO="+numAbonado;
        
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
                .add("numeroFactura",rs.getString("NUMEROFACTURA"))
                .build();
                
                jsonPedidos.add(jsonObj);
            }
            
            if(jsonPedidos == null)
                return null;
            
            
            /*Conversion de Json a String*/
            StringWriter jsonstr=new StringWriter();
            JsonWriter writer = Json.createWriter(jsonstr);
            writer.writeArray(jsonPedidos.build());

            return jsonstr.toString();
            
        }catch(SQLException e){
            throw new BDException(e.getMessage());
        }       
    }
    
    public static String getNumFactura (int num)throws BDException{
        ResultSet rs;
        String sql = "SELECT NUMEROFACTURA FROM APP.PEDIDO WHERE NUMERO="+num;
        String numFactura = null;
        
        try{
            /*Lectura de la BD y devolucion del número de factura */
            rs = ConexionBD.creaInstancia().ejecutaQuery(sql);
            
            while(rs.next())
                numFactura = rs.getString("NUMEROFACTURA");
        }catch(SQLException e){
             throw new BDException(e.getMessage());
        }
        return numFactura;
    }
}