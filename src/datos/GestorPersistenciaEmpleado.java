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
public class GestorPersistenciaEmpleado {
    public static String getEmpleadoByLogin(String login, String password) throws BDException{
        ResultSet rs;
        String sql = "SELECT * FROM APP.EMPLEADO WHERE LOGIN ='"+login+"' AND PASSWORD='"+password+"'";
        
        
        try{
            /*Lectura de la BD y creación de la cadena Json*/
            rs = ConexionBD.creaInstancia().ejecutaQuery(sql);
            if(rs.next()){
                JsonObject jsonObj=Json.createObjectBuilder()
                .add("login",rs.getString("LOGIN"))
                .add("fechaInicio",rs.getString("FECHAINICIO"))
                .add("tipoEmpleado",rs.getString("TIPOEMPLEADO"))
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
