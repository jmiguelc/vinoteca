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
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;

/**
 *
 * @author nurcanc
 */
public class GestorPersistenciaLineaCompra {
    /**
     * Se obtiene una linea de compra por las unidades
     * @param idCompra número de Compra a la que pertenece la LineaCompra 
     * @return una cadena de caracteres de una linea de compra
     * @throws BDException 
     */
    
    public static String getLineasCompra(int idCompra) throws BDException{
        ResultSet rs;
        String sql = "SELECT * FROM APP.LINEACOMPRA WHERE IDCOMPRA="+idCompra;
        
        try{
            /*Lectura de la BD y creación de la cadena Json*/
            rs = ConexionBD.creaInstancia().ejecutaQuery(sql);
            JsonArrayBuilder jsonLineaCompra= Json.createArrayBuilder();
            while(rs.next()){
                JsonObjectBuilder jObj=Json.createObjectBuilder()
                .add("idLineaCompra",rs.getInt("ID"))
                .add("unidades",rs.getInt("UNIDADES"))
                .add("recibida",rs.getString("RECIBIDA"));
                
                if(rs.getString("FECHARECEPCION")!=null)
                    jObj.add("fechaRecepcion",rs.getString("FECHARECEPCION"));
                
                JsonObject jsonObj=jObj.build();  
                jsonLineaCompra.add(jsonObj);
            }
            
            /*Conversion de Json a String*/
            StringWriter jsonstr=new StringWriter();
            JsonWriter writer = Json.createWriter(jsonstr);
            writer.writeArray(jsonLineaCompra.build());

            return jsonstr.toString();
            
        }catch(SQLException e){
            throw new BDException(e.getMessage());
        }
       
    }
    
     public static String getLineaCompraByid(int id) throws BDException{
        ResultSet rs;
        String sql = "SELECT * FROM APP.LINEACOMPRA WHERE ID="+id;
        
        try{
            /*Lectura de la BD y creación de la cadena Json*/
            rs = ConexionBD.creaInstancia().ejecutaQuery(sql);
            if(rs.next()){
                JsonObjectBuilder jsonObjBuilder=Json.createObjectBuilder()
                .add("idLineaCompra",rs.getInt("ID"));
                
                if(rs.getString("FECHARECEPCION")!=null)
                    jsonObjBuilder.add("fechaRecepcion",rs.getString("FECHARECEPCION"));
                
                jsonObjBuilder.add("recibida",rs.getString("RECIBIDA"))
                .add("unidades",rs.getInt("UNIDADES"));
                JsonObject jsonObj=jsonObjBuilder.build();
                
                /*Conversion de Json a String*/
                StringWriter jsonstr=new StringWriter();
                JsonWriter writer = Json.createWriter(jsonstr);
                writer.writeObject(jsonObj);

                return jsonstr.toString();
            }
        }catch(SQLException e){
            throw new BDException(e.getMessage());
        }
       
        return null;
     }
     
     public static void lineaCompraRecibida(int id) throws BDException{
        String sql = "UPDATE APP.LINEACOMPRA SET RECIBIDA='T' WHERE ID="+id;
        
        ConexionBD.creaInstancia().ejecutaUpdate(sql);
     }
     
      public static void fechaRecepcionLC(int id, Date fecha) throws BDException{
        String sql = "UPDATE APP.LINEACOMPRA SET FECHARECEPCION='"+fecha+"' WHERE ID="+id;
        
        ConexionBD.creaInstancia().ejecutaUpdate(sql);
     }
}