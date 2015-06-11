/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import datos.GestorPersistenciaPedido;
import excepciones.BDException;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;

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
    private Abonado abonado;
    private ArrayList<LineaPedido> lineasPedido; 

    /*Seguramente haya que hacer un constructor para Json 
    y otro normal para procesar pedido*/

    /**
     * Constructor no vacio para procesar un Pedido
     * @param estado
     * @param fechaRealizacion
     * @param abonado
     */
    
    public Pedido( EstadoPedido estado, Date fechaRealizacion, Abonado abonado) {
        setEstado(estado);
        setFechaRealizacion (fechaRealizacion);
        setAbonado(abonado);
        setLineasPedido(new ArrayList<LineaPedido>());
    }

    public Pedido(String jsonPedido) throws BDException {
        StringReader strReader=new StringReader(jsonPedido);
        JsonReader jReader=Json.createReader(strReader);
        JsonObject jsonObject=jReader.readObject();
        
        setNumeroPedido(jsonObject.getInt("numeroPedido"));
        
        Date fechaRealizacion=Date.valueOf(jsonObject.getString("fechaRealizacion"));
        setFechaRealizacion(fechaRealizacion);
        
        if(jsonObject.containsKey("notaEntrega"))
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
        
        int numeroAbonado=jsonObject.getInt("numeroAbonado");
        Abonado ab=Abonado.obtenerAbonado(numeroAbonado);
        setAbonado(ab);
    }

    /**
     * Se obtiene el numero de pedido de Pedido
     * @return el numero de un pedido
     */
    public int getNumeroPedido() {
        return numeroPedido;
    }
    /**
     * Establece el numero de un pedido de Pedido
     * @param numeroPedido 
     */
    private void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    /**
     * Se obtiene el estado de Pedido
     * @return un estado de un pedido
     */
    public EstadoPedido getEstado() {
        return estado;
    }
    /**
     * Establece el estado de Pedido
     * @param estado 
     */
    private void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    /**
     * Se obtiene la fecha de realizacion de Pedido
     * @return una fecha de realizacion de un pedido
     */
    public Date getFechaRealizacion() {
        return fechaRealizacion;
    }
    /**
     * Establece la fecha de realizacion de Pedido
     * @param fechaRealizacion 
     */
    private void setFechaRealizacion(Date fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    /**
     * Se obtiene una nota de entrega de Pedido
     * @return un string con una nota de entrega de un pedido
     */
    public String getNotaEntrega() {
        return NotaEntrega;
    }
    /**
     * Establece una nota de entrega de Pedido
     * @param NotaEntrega 
     */
    private void setNotaEntrega(String NotaEntrega) {
        this.NotaEntrega = NotaEntrega;
    }

    /**
     * Se obtiene el importe de Pedido
     * @return el importe de un pedido
     */
    public double getImporte() {
        return importe;
    }
    /**
     * Establece el importe de Pedido
     * @param importe 
     */
    private void setImporte(double importe) {
        this.importe = importe;
    }

    /**
     * Se obtiene la fecha de recepcion de Pedido
     * @return la fecha de recepcion de un pedido
     */
    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }
    /**
     * Establece la fecha de recepcion de Pedido
     * @param fechaRecepcion 
     */
    private void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    /**
     * Se obtiene la fecha de entrega de Pedido
     * @return una fecha de entrega de un pedido
     */
    public Date getFechaEntrega() {
        return fechaEntrega;
    }
    /**
     * Establece la fecha de entrega de Pedido
     * @param fechaEntrega 
     */
    private void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    /**
     *Se obtiene el abonado de Pedido
     * @return el abonado de un pedido
     */
    public Abonado getAbonado() {
        return abonado;
    }
    /**
     * Establece el abonado de Pedido
     * @param abonado 
     */
    private void setAbonado(Abonado abonado) {
        this.abonado = abonado;
    }

    public ArrayList<LineaPedido> getLineasPedido() {
        return lineasPedido;
    }

    private void setLineasPedido(ArrayList<LineaPedido> lineasPedido) {
        this.lineasPedido = lineasPedido;
    }
    
    
    
    /**
     * Se obtiene una lista de pedidos por el numero de factura de Pedido
     * @param numeroFactura
     * @return la lista de pedidos por numero de factura
     * @throws BDException
     */
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
    
    protected void addLineaPedido(LineaPedido lineaPedido){
        this.lineasPedido.add(lineaPedido);
    }
    
    protected static void guardarPedido(Pedido p, double importe, int numFactura) throws BDException{
        int numPedido = GestorPersistenciaPedido.getNextPedido();
        p.setNumeroPedido(numPedido);
        
        JsonObject jsonObj=Json.createObjectBuilder()
            .add("numeroPedido",numPedido)
            .add("fechaRealizacion",p.getFechaRealizacion().toString())
            .add("importe",importe)           
            .add("estado",p.getEstado().toString())
            .add("numeroAbonado",p.getAbonado().getNumeroAbonado())
            .add("numeroFactura",numFactura)
            .build();
        
        StringWriter jsonstr=new StringWriter();
        JsonWriter writer = Json.createWriter(jsonstr);
        writer.writeObject(jsonObj);
        GestorPersistenciaPedido.insertPedido(jsonstr.toString());
    }
    
    protected void setTotal(){
        double importeTotal = 0.0;
        
        ArrayList<LineaPedido> lineasPedidos = getLineasPedido();
        for(LineaPedido lp: lineasPedidos){
            importeTotal += lp.getTotal();
        }
        setImporte(importeTotal);
    }
}
