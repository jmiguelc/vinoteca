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
import javax.json.JsonObject;
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
        String sql = "SELECT * FROM APP.COMPRA WHERE IDCOMPRA="+idCompra+" AND RECIBIDACOMPLETA='T'";
        
        try{
            /*Lectura de la BD y creación de la cadena Json*/
            rs = ConexionBD.creaInstancia().ejecutaQuery(sql);
            if(rs.next()){
                JsonObject jsonObj=Json.createObjectBuilder()
                .add("idCompra",rs.getInt("IDCOMPRA"))
                .add("fechaInicio",rs.getString("FECHAINICIOCOMPRA"))
                .add("fechaCompraCompletada",rs.getString("FECHACOMPRACOMPLETADA"))
                .add("recibidaCompleta",rs.getString("RECIBIDACOMPLETA"))
                .add("importe",rs.getDouble("IMPORTE"))
                .add("pagada",rs.getString("PAGADA"))
                .add("fechaPago",rs.getString("FECHAPAGO"))
                .build();
                
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
    
}
