/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import datos.GestorPersistenciaLineaPedido;
import excepciones.BDException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
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
    
    public LineaPedido(String jsonLineaPedido) {
        StringReader strReader=new StringReader(jsonLineaPedido);
        JsonReader jReader=Json.createReader(strReader);
        JsonObject jsonObject=jReader.readObject();
        
        setUnidades(jsonObject.getInt("unidades"));
        setCompletada(jsonObject.getString("completada"));
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
    private void setCompletada(String completada) {
        switch(completada){
            case "T":
                this.completada=true;
                break;
            case "F":
                this.completada=false;
                break;
        }
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
    
    protected String completada(){
        String valor;
        if(isCompletada())
            valor = "T";
        else
            valor = "F";
        return valor;
    }
    /**
     * Se guarda la linea de pedido dependiendo de ella misma y del numero de pedido
     * @param lineaPedido
     * @param numPedido
     * @throws BDException
     */
    protected void guardarLineaPedido(LineaPedido lineaPedido, int numPedido)throws BDException{
        int id = GestorPersistenciaLineaPedido.getNextLineaPedido();
        String completada = completada();
        
        JsonObject jsonObj=Json.createObjectBuilder()
            .add("id",id)
            .add("unidades",lineaPedido.getUnidades())
            .add("completada",completada)
            .add("numPedido",numPedido)    
            .add("codigo",getRef().getCodigo())    
            .build();
        
        StringWriter jsonstr=new StringWriter();
        JsonWriter writer = Json.createWriter(jsonstr);
        writer.writeObject(jsonObj);
        GestorPersistenciaLineaPedido.insertLineaPedido(jsonstr.toString());
    }
    
    protected static LineaPedido crearLineaPedido(int unidades, boolean completada, Referencia ref){
        LineaPedido lp = new LineaPedido(unidades, completada, ref);
        
        return lp;
    }
    
    protected static void lineasPedidoCompletas(int idLineaCompra) throws BDException{
        GestorPersistenciaLineaPedido.lineasPedidoCompletas(idLineaCompra);
    }
    
    protected static ArrayList<LineaPedido> obtenerlineasPedidosByPedido(int numeroPedido) throws BDException{
        ArrayList<LineaPedido> lineasPedido=new ArrayList<>();
        LineaPedido lineaPedido;
        
        /*recuperar pedidos */
         String jsonListaLineasPedido=GestorPersistenciaLineaPedido.getLineasPedidoByPedido(numeroPedido);
         String jsonLineaPedido;
        
        /*Deshacemos el jsonArray*/
        StringReader strReader=new StringReader(jsonListaLineasPedido);
        JsonReader jReader=Json.createReader(strReader);    
        JsonArray jsonArray=jReader.readArray();
        
        /*mandamos construir los objetos y generamos la lista de Pedidos*/
        for(int i=0;i<jsonArray.size();i++){
            jsonLineaPedido = jsonArray.getJsonObject(i).toString();
            lineaPedido=new LineaPedido(jsonLineaPedido);
            lineasPedido.add(lineaPedido);
        }
        
        return lineasPedido;
    }  
}
