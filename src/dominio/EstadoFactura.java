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
    
    public static EstadoFactura getEstado(String estado) {
        switch(estado){
          case "E": return emitida;
          case "P": return pagada;
          case "V": return vencida;
          default: return null;
        }
    }
}
