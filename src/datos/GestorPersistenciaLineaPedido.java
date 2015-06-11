/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import excepciones.BDException;
import java.io.StringReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author ruben
 */
public class GestorPersistenciaLineaPedido {
     public static int insertLineaPedido(String jsonLineaPedido) throws BDException {
        StringReader strReader=new StringReader(jsonLineaPedido);
        JsonReader jReader=Json.createReader(strReader);
        JsonObject jsonObject=jReader.readObject();
        
        int id = jsonObject.getInt("id");
        int unidades=jsonObject.getInt("unidades");
        boolean completada=jsonObject.getBoolean("completada");
        int numPedido = jsonObject.getInt("numPedido");
        int idLineaCompra = jsonObject.getInt("idLineaCompra");
        int codigo = jsonObject.getInt("codigo");
        
        String sql = "INSERT INTO APP.LINEAPEDIDO VALUES("+id+","
                       +unidades+",'"+completada+"',"+numPedido+","+idLineaCompra+","+codigo+")";
        int resutado=ConexionBD.creaInstancia().ejecutaUpdate(sql);
        
        
        return resutado;
    }
     
     public static int getNextLineaPedido()throws BDException{
        ResultSet rs;
        String sql = "SELECT * FROM APP.LINEAPEDIDO";
        int nextPedido = 1;
        
        try{
            /*Lectura de la BD y creación de la cadena Json*/
            rs = ConexionBD.creaInstancia().ejecutaQuery(sql);
            while(rs.next())
                nextPedido++; 
        
        }catch(SQLException e){
            throw new BDException(e.getMessage());
        }
        
        return nextPedido;
    }
}
