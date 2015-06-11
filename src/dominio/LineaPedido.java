/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import datos.GestorPersistenciaLineaPedido;
import excepciones.BDException;
import java.io.StringWriter;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;

/**
 *
 * @author ruben
 */
public class LineaPedido {
    private int unidades;
    private boolean completada;
    private Referencia ref;

    /**
     *Constructor no vacio de linea de pedido
     * @param unidades
     * @param completada
     * @param ref
     */
    public LineaPedido(int unidades, boolean completada, Referencia ref) {
        this.unidades = unidades;
        this.completada = completada;
        this.ref = ref;
    }

    /**
     * Se obtiene las unidades de linea de pedido
     * @return las unidades de una linea de pedido
     */
    public int getUnidades() {
        return unidades;
    }

    /**
     *Establece las unidades de la linea de pedido
     * @param unidades
     */
    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    /**
     * Se obtiene el estado de si la linea de pedido esta completada o no
     * @return un booleano de si esta completada la linea de pedido o no
     */
    public boolean isCompletada() {
        return completada;
    }

    /**
     * Establece el estado de si la linea de pedido esta completada o no
     * @param completada
     */
    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    /**
     * Se obtiene la referencia de la linea de pedido
     * @return la referencia de una linea de pedido
     */
    public Referencia getRef() {
        return ref;
    }

    /**
     * Establece la referencia de la linea de pedido
     * @param ref
     */
    public void setRef(Referencia ref) {
        this.ref = ref;
    }
    
    /**
     * Se obtiene el importe total de la linea de pedido
     * @return el importe total de la linea de pedido
     */
    protected double getTotal(){
        double importe;
        double total;
        Referencia ref = getRef();
        
        importe = ref.getImporte();
        total = importe * getUnidades();
        
        return total;
    }
    
    /**
     * Se guarda la linea de pedido dependiendo de ella misma y del numero de pedido
     * @param lineaPedido
     * @param numPedido
     * @throws BDException
     */
    protected void guardarLineaPedido(LineaPedido lineaPedido, int numPedido)throws BDException{
        int id = GestorPersistenciaLineaPedido.getNextLineaPedido();
        
        JsonObject jsonObj=Json.createObjectBuilder()
            .add("id",id)
            .add("unidades",lineaPedido.getUnidades())
            .add("completada",lineaPedido.isCompletada())
            .add("numPedido",numPedido)
            .add("idLineaCompra",id)    
            .add("codigo",getRef().getCodigo())    
            .build();
        
        StringWriter jsonstr=new StringWriter();
        JsonWriter writer = Json.createWriter(jsonstr);
        writer.writeObject(jsonObj);
        GestorPersistenciaLineaPedido.insertLineaPedido(jsonstr.toString());
    }
}
