/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebasBD;

import datos.conexionBD;
import excepciones.BDException;
import java.sql.ResultSet;

/**
 *
 * @author monrae
 */
public class Main {
    public static void main(String[] args) {
        ResultSet r;
        String sql="SELECT * FROM APP.CATEGORIA";
        try{
            r=conexionBD.creaInstancia().ejecutaQuery(sql);
        }catch(ClassNotFoundException|BDException e){
            e.printStackTrace();
        }
    }
}
