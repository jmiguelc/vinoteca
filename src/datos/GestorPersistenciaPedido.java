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
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
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
                JsonObjectBuilder jObj=Json.createObjectBuilder()
                .add("numeroPedido",rs.getInt("NUMERO"))
                .add("fechaRealizacion",rs.getString("FECHAREALIZACION"));
                if(rs.getString("NOTAENTREGA")!=null)
                    jObj.add("notaEntrega",rs.getString("NOTAENTREGA"));
                
                jObj.add("importe",rs.getFloat("IMPORTE"));
                if(rs.getString("FECHARECEPCION")!=null)
                    jObj.add("fechaRecepcion",rs.getString("FECHARECEPCION"));
                
                if(rs.getString("FECHAENTREGA")!=null)
                    jObj.add("fechaEntrega",rs.getString("FECHAENTREGA"));
                jObj.add("estado",rs.getString("ESTADO"))
                .add("numeroAbonado",rs.getInt("NUMEROABONADO"))
                .add("numeroFactura",rs.getInt("NUMEROFACTURA"));
                
                JsonObject jsonObj=jObj.build();
                
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
    
    public static String getPedidosTramitados() throws BDException{
        ResultSet rs;
        String sql = "SELECT * FROM APP.PEDIDO WHERE ESTADO='T'";
        
        try{
            /*Lectura de la BD y creación de la cadena Json*/
            rs = ConexionBD.creaInstancia().ejecutaQuery(sql);
            JsonArrayBuilder jsonPedidos= Json.createArrayBuilder();
            while(rs.next()){
                JsonObjectBuilder jObj=Json.createObjectBuilder()
                .add("numeroPedido",rs.getInt("NUMERO"))
                .add("fechaRealizacion",rs.getString("FECHAREALIZACION"));
                if(rs.getString("NOTAENTREGA")!=null)
                    jObj.add("notaEntrega",rs.getString("NOTAENTREGA"));
                
                jObj.add("importe",rs.getFloat("IMPORTE"));
                if(rs.getString("FECHARECEPCION")!=null)
                    jObj.add("fechaRecepcion",rs.getString("FECHARECEPCION"));
                
                if(rs.getString("FECHAENTREGA")!=null)
                    jObj.add("fechaEntrega",rs.getString("FECHAENTREGA"));
                jObj.add("estado",rs.getString("ESTADO"))
                .add("numeroAbonado",rs.getInt("NUMEROABONADO"))
                .add("numeroFactura",rs.getInt("NUMEROFACTURA"));
                
                JsonObject jsonObj=jObj.build();
                
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
    
    public static int insertPedido(String jsonPedido) throws BDException {
        StringReader strReader=new StringReader(jsonPedido);
        JsonReader jReader=Json.createReader(strReader);
        JsonObject jsonObject=jReader.readObject();
        
        int numeroPedido=jsonObject.getInt("numeroPedido");
        Date fechaRealizacion=Date.valueOf(jsonObject.getString("fechaRealizacion"));
        double importe=jsonObject.getJsonNumber("importe").doubleValue();
        String estado=jsonObject.getString("estado");
        int numeroAbonado=jsonObject.getInt("numeroAbonado");
        int numeroFactura=jsonObject.getInt("numeroFactura");
        
        String sql = "INSERT INTO APP.PEDIDO(NUMERO,FECHAREALIZACION,IMPORTE,ESTADO,NUMEROABONADO,NUMEROFACTURA) VALUES("
                       +numeroPedido+",'"+fechaRealizacion+"',"+importe+",'"+estado+"',"+numeroAbonado+","+numeroFactura+")";
        int resutado=ConexionBD.creaInstancia().ejecutaUpdate(sql);
        
        
        return resutado;
    }
    
    public static int getNextPedido()throws BDException{
        ResultSet rs;
        String sql = "SELECT * FROM APP.PEDIDO";
        int nextPedido = 1;
        
        try{
            /*Lectura de la BD y creación de la cadena Json*/
            rs = ConexionBD.creaInstancia().ejecutaQuery(sql);
            while(rs.next())
                nextPedido++; 
        
        }catch(SQLException e){
            throw new BDException(e.getMessage());
        }
        
        return nextPedido;
    }
    
    public static int updateEstadoPedido(String estado,int numeroPedido) throws BDException {
            
        String sql = "UPDATE APP.PEDIDO SET ESTADO='"+estado+"' WHERE NUMERO="+numeroPedido;
        int resutado=ConexionBD.creaInstancia().ejecutaUpdate(sql);
        
        return resutado;
    }
}