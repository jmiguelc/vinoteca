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
public class DenominacionOrigen {
    private String nombre;

    /**
     * Constructor no vacio de DenominacionOrigen
     * @param nombre
     */
    public DenominacionOrigen(String nombre) {
        setNombre(nombre);
    }

    /**
     * Se obtiene el nombre de DenominacionOrigen
     * @return el nombre
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Establece el nombre de DenominacionOrigen
     * @param nombre 
     */
    private void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
