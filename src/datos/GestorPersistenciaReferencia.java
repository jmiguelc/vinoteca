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
    
    public static String getReferenciaByLineaCompra(int idLineaCompra) throws BDException{
        ResultSet rs;
        String sql = "SELECT R.CODIGO, R.ESPORCAJAS, R.CONTENIDOENCL, R.PRECIO, R.DISPONIBLE "
                + "FROM APP.REFERENCIA R, APP.LINEAPEDIDO LP "
                + "WHERE LP.CODIGO = R.CODIGO AND LP.IDLINEACOMPRA="+idLineaCompra;
        try{
            /*Lectura de la BD y creación de la cadena Json*/
            rs = ConexionBD.creaInstancia().ejecutaQuery(sql);
            if(rs.next()){
                JsonObject jsonObj=Json.createObjectBuilder()
                .add("codigo",rs.getInt("R.CODIGO"))
                .add("porCajas", rs.getBoolean("R.ESPORCAJAS"))
                .add("contenido", rs.getShort("R.CONTENIDOENCL"))
                .add("importe", rs.getDouble("R.PRECIO"))
                .add("disponible", rs.getBoolean("R.DISPONIBLE"))
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