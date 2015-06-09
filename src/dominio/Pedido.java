/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import datos.GestorPersistenciaPedido;
import excepciones.BDException;
import java.io.StringReader;
import java.sql.Date;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author monrae
 */
public class Pedido {
    private int numeroPedido;
    private EstadoPedido estado;
    private Date fechaRealizacion;
    private String NotaEntrega;
    private double importe;
    private Date fechaRecepcion;
    private Date fechaEntrega;
    private Abonado Abonado;
    private String numFactura;

    /*Seguramente haya que hacer un constructor para Json 
    y otro normal para procesar pedido*/
    public Pedido(String jsonPedido) throws BDException {
        StringReader strReader=new StringReader(jsonPedido);
        JsonReader jReader=Json.createReader(strReader);
        JsonObject jsonObject=jReader.readObject();
        
        setNumeroPedido(jsonObject.getInt("numeroPedido"));
        
        Date fechaRealizacion=Date.valueOf(jsonObject.getString("fechaRealizacion"));
        setFechaRealizacion(fechaRealizacion);
        
        setNotaEntrega(jsonObject.getString("notaEntrega"));
        
        double importe=(jsonObject.getJsonNumber("importe").doubleValue());
        setImporte(importe);
        
        Date fechaRecepcion=null;
        if(jsonObject.containsKey("fechaRecepcion")){
            fechaRecepcion=Date.valueOf(jsonObject.getString("fechaRecepcion"));
        }
        setFechaRecepcion(fechaRecepcion);
        
        Date fechaEntrega=null;
        if(jsonObject.containsKey("fechaEntrega")){
            fechaEntrega=Date.valueOf(jsonObject.getString("fechaEntrega"));
        }
        setFechaEntrega(fechaEntrega);
        
        EstadoPedido estado= EstadoPedido.getEstado(jsonObject.getString("estado"));
        setEstado(estado);
        
        String numeroAbonado=jsonObject.getString("numeroAbonado");
        setAbonado();
        
        String numFactura=jsonObject.getString("numeroFactura");
        setNumFactura(numFactura);
    
    }

    public String getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(String numFactura) {
        this.numFactura = numFactura;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    private void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    private void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public Date getFechaRealizacion() {
        return fechaRealizacion;
    }

    private void setFechaRealizacion(Date fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    public String getNotaEntrega() {
        return NotaEntrega;
    }

    private void setNotaEntrega(String NotaEntrega) {
        this.NotaEntrega = NotaEntrega;
    }

    public double getImporte() {
        return importe;
    }

    private void setImporte(double importe) {
        this.importe = importe;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    private void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    private void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Abonado getAbonado() {
        return Abonado;
    }

    public void setAbonado(Abonado Abonado) {
        this.Abonado = Abonado;
    }

    
    protected static ArrayList<Pedido> obtenerPedidos(int numeroFactura) throws BDException{
        ArrayList<Pedido> pedidos=new ArrayList<>();
        Pedido p;
        
        /*recuperar pedidos */
         String jsonListaPedidos=GestorPersistenciaPedido.getPedidosByFactura(numeroFactura);
         String jsonPedido;
        
        /*Deshacemos el jsonArray*/
        StringReader strReader=new StringReader(jsonListaPedidos);
        JsonReader jReader=Json.createReader(strReader);    
        JsonArray jsonArray=jReader.readArray();
        
        /*mandamos construir los objetos y generamos la lista de Pedidos*/
        for(int i=0;i<jsonArray.size();i++){
            jsonPedido = jsonArray.getJsonObject(i).toString();
            p=new Pedido(jsonPedido);
            pedidos.add(p);
        }
        
        return pedidos;
    }
    
    // Método para hallar los pedidos de un abonado
    protected static ArrayList getPedidosAbonado (String nif)throws BDException{
        ArrayList<Pedido> pedidos = new ArrayList<>();
        Pedido p;
        
         /*recuperar pedidos de un abonado */
         String jsonListaPedidos=GestorPersistenciaPedido.recuperarPedidosAbonado(nif);
         String jsonPedido;
        
        /*Deshacemos el jsonArray*/
        StringReader strReader=new StringReader(jsonListaPedidos);
        JsonReader jReader=Json.createReader(strReader);    
        JsonArray jsonArray=jReader.readArray();
        
        /*mandamos construir los objetos y generamos la lista de Pedidos*/
        for(int i=0;i<jsonArray.size();i++){
            jsonPedido = jsonArray.getJsonObject(i).toString();
            p=new Pedido(jsonPedido);
            pedidos.add(p);
        }
        
        return pedidos;
    }   
}
