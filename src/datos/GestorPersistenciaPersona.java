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
public class GestorPersistenciaPersona {
    public static String getPersonaByNif(String nif) throws BDException{
        ResultSet rs;
        String sql = "SELECT * FROM APP.PERSONA WHERE NIF ='"+nif+"'";
        try{
            /*Lectura de la BD y creación de la cadena Json*/
            rs = ConexionBD.creaInstancia().ejecutaQuery(sql);
            if(rs.next()){
                JsonObject jsonObj=Json.createObjectBuilder()
                .add("nif",rs.getString("NIF"))
                .add("nombre", rs.getString("NOMBRE"))
                .add("apellidos", rs.getString("APELLIDOS"))
                .add("direccion", rs.getString("DIRECCION"))
                .add("email", rs.getString("EMAIL"))
                .add("telefono", rs.getString("TELEFONO"))
                .add("cuentaBancaria", rs.getString("CUENTABANCARIA"))
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
