/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.ResultSet;

/**
 *
 * @author ruben
 */
public class GestorPersistenciaEmpleado {
    public static String getEmpleado(String login, String password){
        ResultSet rs;
        String sql = "SELECT LOGIN FROM EMPLEADO WHERE LOGIN ='"+login+"'";
        
        rs = conexionBD.creaInstancia().ejecutaQuery(sql);
        if(rs.next()){
            
        }
    }
}
