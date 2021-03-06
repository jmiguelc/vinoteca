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
 * @author monrae
 */
public class GestorPersistenciaFactura {
    
    /**
     * Se obtiene las facturas vencidas por la fecha
     * @param fecha
     * @return las facturas vencidas por fecha
     * @throws BDException
     */
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
    /**
     * 
     * @param fecha
     * @return
     * @throws BDException 
     */
    public static String getFacturasMensuales(Date fecha) throws BDException{
        ResultSet rs;
        String sql = "SELECT * FROM APP.FACTURA WHERE FECHAEMISION>='"+fecha.toString()+"'";
        
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
     
     public static int getNextFactura()throws BDException{
        ResultSet rs;
        String sql = "SELECT * FROM APP.FACTURA";
        int nextFactura = 1;
        
        try{
            /*Lectura de la BD y obtencion del dato requerido*/
            rs = ConexionBD.creaInstancia().ejecutaQuery(sql);
            while(rs.next())
                nextFactura++; 
        
        }catch(SQLException e){
            throw new BDException(e.getMessage());
        }
        
        return nextFactura;
    }
     
   /*  public static double getImporte(int numFactura)throws BDException{
         ResultSet rs;
         String sql = "SELECT IMPORTE FROM APP.FACTURA WHERE NUMEROFACTURA="+numFactura;
         double importe = 0.0;
         
         try{
             /*Lectura de la BD y obtencion del dato requerido
             rs = ConexionBD.creaInstancia().ejecutaQuery(sql);
             while(rs.next()){
                 importe = rs.getDouble("IMPORTE");
             }
         }catch(SQLException e){
             throw new BDException(e.getMessage());
         }
         return importe;
     }
     */
     public static int insertFactura(String jsonFactura) throws BDException {
        StringReader strReader=new StringReader(jsonFactura);
        JsonReader jReader=Json.createReader(strReader);
        JsonObject jsonObject=jReader.readObject();
        
        int numeroFactura=jsonObject.getInt("numeroFactura");
        Date fechaEmision=Date.valueOf(jsonObject.getString("fechaEmision"));
        double importe=jsonObject.getJsonNumber("importe").doubleValue();
        String estado=jsonObject.getString("estado");
        
        String sql = "INSERT INTO APP.FACTURA(NUMEROFACTURA,FECHAEMISION,IMPORTE,ESTADO) VALUES("
                       +numeroFactura+",'"+fechaEmision+"',"+importe+",'"+estado+"')";
        int resutado=ConexionBD.creaInstancia().ejecutaUpdate(sql);
        
        
        return resutado;
    }
     
     public static void actualizaFactura(double importe, int numFactura) throws BDException{
        String sql = "UPDATE APP.FACTURA SET IMPORTE="+importe+" WHERE NUMEROFACTURA="+numFactura;
        
        ConexionBD.creaInstancia().ejecutaUpdate(sql);
     }
}
