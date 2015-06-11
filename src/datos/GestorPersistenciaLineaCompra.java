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
public class GestorPersistenciaLineaCompra {
    /**
     * Se obtiene una linea de compra por las unidades
     * @param unidades
     * @return una cadena de caracteres de una linea de compra
     * @throws BDException 
     */
    
    public static String getLineaCompra(int unidades) throws BDException{
        ResultSet rs;
        String sql = "SELECT * FROM APP.REFERENCIA WHERE UNIDADES ="+unidades;
        try{
            /*Lectura de la BD y creación de la cadena Json*/
            rs = ConexionBD.creaInstancia().ejecutaQuery(sql);
            if(rs.next()){
                JsonObject jsonObj=Json.createObjectBuilder()
                .add("unidades",rs.getInt("UNIDADES"))
                .add("recibida", rs.getBoolean("RECIBIDA"))
                .add("fechaRecepcion",rs.getString("FECHARECEPCION"))
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
