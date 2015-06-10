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
 * @author ruben
 */
public class GestorPersistenciaReferencia {
    
    public static String getReferencia(int numRef) throws BDException{
        ResultSet rs;
        String sql = "SELECT * FROM APP.REFERENCIA WHERE CODIGO ="+numRef;
        try{
            /*Lectura de la BD y creación de la cadena Json*/
            rs = ConexionBD.creaInstancia().ejecutaQuery(sql);
            if(rs.next()){
                JsonObject jsonObj=Json.createObjectBuilder()
                .add("codigo",rs.getInt("CODIGO"))
                .add("porCajas", rs.getBoolean("ESPORCAJAS"))
                .add("contenido", rs.getShort("CONTENIDOENCL"))
                .add("importe", rs.getDouble("PRECIO"))
                .add("disponible", rs.getBoolean("DISPONIBLE"))
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