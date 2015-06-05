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
public class GestorPersistenciaAbonado {
    public static String  obtenerAbonado(int num) throws BDException{
        ResultSet rs;
        String sql = "SELECT * FROM APP.ABONADO WHERE NUMEROABONADO ="+num;
        
        try{
            /*Lectura de la BD y creación de la cadena Json*/
            rs = conexionBD.creaInstancia().ejecutaQuery(sql);
            if(rs.next()){
                JsonObject jsonObj=Json.createObjectBuilder()
                .add("num",rs.getInt("NUMEROABONADO"))
                .add("openIdRef",rs.getString("OPENIDREF"))
                .add("nif",rs.getString("NIF"))
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
