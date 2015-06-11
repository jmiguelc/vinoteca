/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import excepciones.BDException;
import java.io.StringReader;
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
        
        int unidades=jsonObject.getInt("unidades");
        boolean completada=jsonObject.getBoolean("completada");
        
        String sql = "INSERT INTO APP.LINEAPEDIDO(UNIDADES,COMPLETADA) VALUES("
                       +unidades+",'"+completada+"')";
        int resutado=ConexionBD.creaInstancia().ejecutaUpdate(sql);
        
        
        return resutado;
    }
}
