/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

/**
 *
 * @author ruben
 */
public enum TipoEmpleado {
    responsablePedidos,
    responsableContabilidad,
    responsableAlmacen;
    /**
     * Se obtiene el tipo de empleado de TipoEmpleado
     * @param tipo
     * @return un tipo de empleado
     */
    public static TipoEmpleado getTipo(String tipo) {
        switch(tipo){
          case "A": return responsableAlmacen;
          case "C": return responsableContabilidad;
          case "P": return responsablePedidos;
          default: return null;
        }
    }
    
}
