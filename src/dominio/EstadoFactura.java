/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

/**
 *
 * @author juacelo
 */
public enum EstadoFactura {

    emitida,
    pagada,
    vencida;
    
    /**
      * Trasnforma una cadena correspondiente en el valor de EstadoFactura
     * que le corresponda.
     * @param estado cadena de caracteres que indica el estado
     * @return devuelve una valor del tipo estado
     */
    public static EstadoFactura getEstado(String estado) {
        switch(estado){
          case "E": return emitida;
          case "P": return pagada;
          case "V": return vencida;
          default: return null;
        }
    }
    
    public String toString(){
        switch(this){
            case emitida: return "E";
            case pagada: return "P";
            case vencida: return "V";
            default: return null;
        }
    }
}
