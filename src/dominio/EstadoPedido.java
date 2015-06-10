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
public enum EstadoPedido {
    pendiente,
    tramitado,
    completado,
    servido,
    facturado,
    abonado;  
    
    /**
     * Trasnforma una cadena correspondiente en el valor de EstadoPedido
     * que le corresponda.
     * @param estado cadena de caracteres que indica el estado
     * @return devuelve una valor del tipo estado
     */
    public static EstadoPedido getEstado(String estado) {
        switch(estado){
          case "P": return pendiente;
          case "T": return tramitado;
          case "C": return completado;
          case "S": return servido;
          case "F": return facturado;
          case "A": return abonado;
          default: return null;
        }
    }

    public String toString(){
        switch(this){
            case pendiente: return "P";
            case tramitado: return "T";
            case completado: return "C";
            case servido: return "S";
            case facturado: return "F";
            case abonado: return "A";
            default: return null;
        }
    }
}
