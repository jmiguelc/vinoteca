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
    
    protected String completada(){
        String valor;
        if(isCompletada())
            valor = "T";
        else
            valor = "F";
        return valor;
    }
    
    protected void guardarLineaPedido(LineaPedido lineaPedido, int numPedido)throws BDException{
        int id = GestorPersistenciaLineaPedido.getNextLineaPedido();
        String completada = completada();
        
        JsonObject jsonObj=Json.createObjectBuilder()
            .add("id",id)
            .add("unidades",lineaPedido.getUnidades())
            .add("completada",completada)
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
