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
    public static String getEmpleado(String login, String password) throws ClassNotFoundException, BDException{
        ResultSet rs;
        String sql = "SELECT * FROM APP.EMPLEADO WHERE LOGIN ='"+login+"' AND PASSWORD='"+password+"'";
        
        try{
        rs = conexionBD.creaInstancia().ejecutaQuery(sql);
        if(rs.next()){
            JsonObject jsonObj=Json.createObjectBuilder()
            .add("login",rs.getString("LOGIN"))
            .add("nif",rs.getString("NIF"))
            .add("password",rs.getString("PASSWORD"))
            .add("fechaInicio",rs.getString("FECHAINICIO"))
            .add("tipoEmpleado",rs.getString("TIPOEMPLEADO"))
            .build();
            
            StringWriter jsonstr=new StringWriter();
            JsonWriter w=Json.createWriter(jsonstr);
            w.writeObject(jsonObj);
            w.close();
            
            return jsonstr.toString();
        }
        }catch(ClassNotFoundException e){
            throw new ClassNotFoundException(e.getMessage());
        }catch(SQLException e){
            throw new BDException(e.getMessage());
        }
        
        return null;
    }
}
