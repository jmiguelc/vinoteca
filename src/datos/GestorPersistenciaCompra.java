/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import excepciones.BDException;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;

/**
 *
 * @author nurcanc
 */
public class GestorPersistenciaCompra {
    /**
     * Se obtiene la compra mediante el identificador de compra
     * @param idCompra
     * @return una cadena de caracteres de la compra
     * @throws BDException 
     */
    public static String getCompraByidCompra(int idCompra) throws BDException{
        ResultSet rs;
        String sql = "SELECT * FROM APP.COMPRA WHERE IDCOMPRA="+idCompra;
        
        try{
            /*Lectura de la BD y creación de la cadena Json*/
            rs = ConexionBD.creaInstancia().ejecutaQuery(sql);
            JsonArrayBuilder jsonPedidos= Json.createArrayBuilder();
            while(rs.next()){
                JsonObject jsonObj=Json.createObjectBuilder()
                .add("idCompra",rs.getInt("IDCOMPRA"))
                .add("fechaInicio",rs.getString("FECHAINICIO"))
                .add("fechaCompraCompletada",rs.getString("FECHACOMPRACOMPLETADA"))
                .add("recibidaCompletada",rs.getBoolean("RECIBIDACOMPLETADA"))
                .add("fechaPago",rs.getString("FECHAPAGO"))
                .add("pagada",rs.getBoolean("PAGADA"))
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
    /**
     * Se introduce un pedido
     * @param jsonPedido
     * @return una nueva tupla de un pedido
     * @throws BDException 
     */
    public static int insertPedido(String jsonPedido) throws BDException {
        StringReader strReader=new StringReader(jsonPedido);
        JsonReader jReader=Json.createReader(strReader);
        JsonObject jsonObject=jReader.readObject();
        
        int numeroPedido=jsonObject.getInt("numeroPedido");
        Date fechaRealizacion=Date.valueOf(jsonObject.getString("fechaRealizacion"));
        double importe=jsonObject.getJsonNumber("importe").doubleValue();
        String estado=jsonObject.getString("estado");
        int numeroAbonado=jsonObject.getInt("numeroAbonado");
        //int numeroFactura=jsonObject.getInt("numeroFactura");
        
        String sql = "INSERT APP.PEDIDO(NUMERO,FECHAREALIZACION,IMPORTE,ESTADO,NUMEROABONADO) VALUES('"
                       +numeroPedido+","+fechaRealizacion+","+importe+","+estado+","+numeroAbonado+"')";
        int resutado=ConexionBD.creaInstancia().ejecutaUpdate(sql);
        
        
        return resutado;
    }
}
