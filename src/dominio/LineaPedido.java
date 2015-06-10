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
public class LineaPedido {
    private int unidades;
    private boolean completada;
    private Referencia ref;

    public LineaPedido(int unidades, boolean completada, Referencia ref) {
        this.unidades = unidades;
        this.completada = completada;
        this.ref = ref;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    public Referencia getRef() {
        return ref;
    }

    public void setRef(Referencia ref) {
        this.ref = ref;
    }
    
    protected double getTotal(){
        double importe;
        double total;
        Referencia ref = getRef();
        
        importe = ref.getImporte();
        total = importe * getUnidades();
        
        return total;
    }
}
