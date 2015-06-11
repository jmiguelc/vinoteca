/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import datos.GestorPersistenciaFactura;
import excepciones.BDException;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;

/**
 *
 * @author monrae
 */
public class Factura {
    private int numeroFactura;
    private Date fechaEmision;
    private double importe;
    private EstadoFactura estado;
    private Date fechaPago;
    private String idExtractBancario;
    private ArrayList<Pedido> pedidos;


    /**
     * El constructor debe ser con Json por mi parte solo debo acceder
     * a la base de datos para traerlos
     * @param jsonFactura
     * @throws BDException
     */
    
    public Factura(String jsonFactura) throws BDException {
        ArrayList<Pedido> pedidos;
        StringReader strReader=new StringReader(jsonFactura);
        JsonReader jReader=Json.createReader(strReader);
        JsonObject jsonObject=jReader.readObject();
        
        setNumeroFactura(jsonObject.getInt("numeroFactura"));
        Date fechaEmision=Date.valueOf(jsonObject.getString("fechaEmision"));
        setFechaEmision(fechaEmision);
        double importe=jsonObject.getJsonNumber("importe").doubleValue();
        setImporte(importe);
        if(!jsonObject.containsKey("fechaPago"))
            setFechaPago(null);
        if(!jsonObject.containsKey("idExtractBancario"))
            setIdExtractBancario(null);
        EstadoFactura estado=EstadoFactura.getEstado(jsonObject.getString("estado"));
        setEstado(estado);
        
        pedidos=Pedido.obtenerPedidos(getNumeroFactura());
        setPedidos(pedidos); 
       
    }
    /**
     * Constructor no vacio de Factura
     * @param numeroFactura
     * @param fechaEmision
     * @param estado 
     */
    public Factura(int numeroFactura,Date fechaEmision,EstadoFactura estado){
        setNumeroFactura(numeroFactura);
        setFechaEmision(fechaEmision);
        setEstado(estado);
        setPedidos(new ArrayList<Pedido>());
    }

    /**
     * Obtiene el identificador de factura asociado a la factura
     * @return en numero de factura almacenado en dicha factura
     */
    public int getNumeroFactura() {
        return numeroFactura;
    }
    /**
     * Establece el numero de factura de una Factura
     * @param numeroFactura 
     */
    private void setNumeroFactura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    /**
     * Se obtiene la fecha de emision de una Factura
     * @return la fecha de emision de una factura
     */
    public Date getFechaEmision() {
        return fechaEmision;
    }
    /**
    * Establece una fecha de emision de una Factura
    * @param fechaEmision 
    */
    private void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    /**
     * Se obtiene el importe de una Factura
     * @return el importe de la factura
     */
    public double getImporte() {
        return importe;
    }
    /**
     * Establece el importe de una Factura
     * @param importe 
     */
    private void setImporte(double importe) {
        this.importe = importe;
    }

    /**
     * Se obtiene el estado de una Factura
     * @return el estado de la factura
     */
    public EstadoFactura getEstado() {
        return estado;
    }
    /**
     * Establece el estado de una Factura
     * @param estado 
     */
    private void setEstado(EstadoFactura estado) {
        this.estado = estado;
    }

    /**
     * Se obtiene la fecha de pago de una Factura
     * @return la fecha de pago de la factura
     */
    public Date getFechaPago() {
        return fechaPago;
    }
    /**
     * Establece la fecha de pago de una Factura
     * @param fechaPago 
     */
    private void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    /**
     * Se obtiene el identificador del extracto bancario de una Factura
     * @return un identificador del extracto bancario de la factura
     */
    public String getIdExtractBancario() {
        return idExtractBancario;
    }
    /**
     * Establece un identificador del extracto bancario de una Factura
     * @param idExtractBancario 
     */
    private void setIdExtractBancario(String idExtractBancario) {
        this.idExtractBancario = idExtractBancario;
    }

    /**
     * Se obtiene una lista de pedidos
     * @return una lista de pedidos
     */
    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }
    /**
     * Establece una lista de pedidos
     * @param pedidos 
     */
    private void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    /**
     * Añade un pedido
     * @param pedido 
     */
    protected void addPedido(Pedido pedido){
        this.pedidos.add(pedido);
    }
    
    /**
     * Se obtiene una lista de facturas vencidas de Factura
     * @param fecha
     * @return lista de facturas vencidas
     * @throws BDException
     */
    protected static ArrayList<Factura> obtenerFacturasVencidas(Date fecha) throws BDException{
        ArrayList<Factura> facturas=new ArrayList<>();
        
        /*Recuperamos las facturas*/
        String jsonListaFacturas=GestorPersistenciaFactura.getFacturasVencidasByFecha(fecha);
        String jsonFactura;
        Factura f;
        
        /*Deshacemos el jsonArray*/
        StringReader strReader=new StringReader(jsonListaFacturas);
        JsonReader jReader=Json.createReader(strReader);    
        JsonArray jsonArray=jReader.readArray();
        
        /*mandamos construir los objetos y generamos la lista de facturas*/
        for(int i=0;i<jsonArray.size();i++){
            jsonFactura = jsonArray.getJsonObject(i).toString();
            if (jsonFactura!=null){
                f=new Factura(jsonFactura);
                facturas.add(f);
            }
            
        }
        
        return facturas;
    }
    /**
     * Se obtiene una lista de las facturas mensuales dependiendo la fecha
     * @param fecha
     * @return una lista de facturas mensuales
     * @throws BDException 
     */
    protected static ArrayList<Factura> obtenerFacturasMensuales(Date fecha) throws BDException{
        ArrayList<Factura> facturas=new ArrayList<>();
        
        /*Recuperamos las facturas*/
        String jsonListaFacturas=GestorPersistenciaFactura.getFacturasMensuales(fecha);
        String jsonFactura;
        Factura f;
        
        /*Deshacemos el jsonArray*/
        StringReader strReader=new StringReader(jsonListaFacturas);
        JsonReader jReader=Json.createReader(strReader);    
        JsonArray jsonArray=jReader.readArray();
        
        /*mandamos construir los objetos y generamos la lista de facturas*/
        for(int i=0;i<jsonArray.size();i++){
            jsonFactura = jsonArray.getJsonObject(i).toString();
            if (jsonFactura!=null){
                f=new Factura(jsonFactura);
                facturas.add(f);
            }            
        }
        
        /*ir a pedidos y recuperar los pedidos para cada factura*/       
        return facturas;
    }
    /**
     * Se guarda la factura indicada
     * @param factura
     * @param importe
     * @throws BDException 
     */
    protected static void guardarFactura(Factura factura, double importe) throws BDException{
        JsonObject jsonObj=Json.createObjectBuilder()
            .add("numeroFactura",factura.getNumeroFactura())
            .add("fechaEmision",factura.getFechaEmision().toString())
            .add("importe",importe)           
            .add("estado",factura.getEstado().toString())
            .build();
        
        StringWriter jsonstr=new StringWriter();
        JsonWriter writer = Json.createWriter(jsonstr);
        writer.writeObject(jsonObj);
        GestorPersistenciaFactura.insertFactura(jsonstr.toString());
    }
    /* Sumamos el importe del pedido al total de la facura */
    protected void actualizaImporteFactura(){
        double total = 0;
        ArrayList<Pedido> pedidos = getPedidos();
        
        for(Pedido p: pedidos){
            total += p.getImporte();
        }
        
        setImporte(total);
    } 
    
    protected void actualizaFactura()throws BDException{
        GestorPersistenciaFactura.actualizaFactura(getImporte(), getNumeroFactura());
    }
    
    protected static Factura crearFactura(int numeroFactura,Date fechaEmision,EstadoFactura estado){
        Factura f = new Factura(numeroFactura,fechaEmision,estado);
        
        return f;
    }
}
    
