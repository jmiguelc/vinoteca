/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package datos;

import excepciones.BDException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author juacelo
 */
public class ConexionBD {
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
    
    private static final String user="root";
    private static final String pass = "root";
    private static final String driverName="org.apache.derby.jdbc.ClientDriver";
    private static Connection con;
    private static ConexionBD conexion; 
   
    /**
     * Constructor para la conexion con la base de datos
     * @throws BDException 
     */
    private ConexionBD() throws BDException{
        /*Cargamos el driver de la base de datos*/
        try{
            Class.forName(driverName);
        }catch(ClassNotFoundException e){
            throw new BDException("Error del Driver de la base de datos: " +e.getMessage());
        }
        
        try{
            /*creamos conexion*/
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/Vinoteca", user, pass);
                 
        }catch(SQLException e){
            throw new BDException(e.getMessage());
        }
    }
    
    /**
     * Si no hay conexion la crea
     * @return la conexion
     * @throws BDException
     */
    public static ConexionBD creaInstancia() throws BDException{
        if (conexion==null)
            conexion=new ConexionBD();
                   
        return conexion;
    }
    
    /**
     * Se busca la consulta realizada en la BD
     * @param sql
     * @return el resultado de la consulta a la BD
     * @throws BDException
     */
    public ResultSet ejecutaQuery(String sql) throws BDException{
        ResultSet resConsulta;
        /*creamos statement (el que ejecuta las querys)*/
        try{
        Statement s = con.createStatement();
        
        /*ejecutamos consultas*/
        resConsulta=s.executeQuery(sql);
        }catch(SQLException e){
            throw new BDException(e.getMessage());
        }
        return resConsulta;
    }
    
    /**
     * Actualiza las tuplas modificadas en la BD
     * @param sql
     * @return las tuplas modificadas
     * @throws BDException
     */
    public int ejecutaUpdate(String sql) throws BDException{
        /*creamos statement (el que ejecuta las querys)*/
        int lineasMod=0;
        try{
            Statement s = con.createStatement();
            lineasMod=s.executeUpdate(sql); //ejecutamos consulta
            
        }catch(SQLException e){
            throw new BDException(e.getMessage());
        }
        
        return lineasMod;
        
    }

}
