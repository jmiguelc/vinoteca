/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import datos.GestorPersistenciaFactura;
import excepciones.AbNotFoundException;
import excepciones.AbNotPaidException;
import excepciones.BDException;
import excepciones.RefNotAvaliableException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author ruben
 */
public class ContCUProcesarPedido {
    
    /**
     * Comprobar que el número de abonado es correcto
     * @param numAbonado
     * @return el abonado
     * @throws AbNotFoundException
     */
    public static Abonado comprobarAbonado(int numAbonado) throws AbNotFoundException{
        Abonado ab;
        try{    
            // Obtenemos el abonado, si no existe, devuelve null y gestionamos el error
            ab = Abonado.obtenerAbonado(numAbonado);
            if(ab==null){
                throw new AbNotFoundException("El número de abonado no es correcto");
            }
            System.out.println("El abonado existe y es válido");
            
        }catch(BDException ex){
            throw new AbNotFoundException("Abonado no Encontrado: "+ex.getMessage());   
        }
        // Devolvemos el abonado
        return ab;
    }
    
    /**
     * Comprueba si hay alguna factura vencida
     * @param numAbonado
     * @return 
     * @throws excepciones.BDException 
     * @throws AbNotPaidException
     */
    public static boolean compruebaPagos(int numAbonado) throws BDException, AbNotPaidException{
        
        Date today = Date.valueOf(LocalDate.now());
        ArrayList<Factura> facturas=Factura.obtenerFacturasVencidas(today);
        ArrayList<Pedido> pedidos;
        Pedido pedido;
        Abonado abonado;
        boolean val=true;
           
        for(Factura factura :facturas){
            pedidos=factura.getPedidos();
            pedido=pedidos.get(0);
            abonado=pedido.getAbonado();
            if (numAbonado==abonado.getNumeroAbonado()){
                val=false;
            }
        }
        if (val==false){
            throw new AbNotPaidException("Tiene facturas vencidas, no puede realizar otro pedido");
        }else{
            System.out.println("No tiene facturas pendientes");
        }
        return val;
    }
    /**
     * Se comprueba la referencia a partir del numero de referencia
     * @param numRef
     * @return el numero de referencia
     * @throws BDException
     * @throws RefNotAvaliableException 
     */    
    public static Referencia comprobarReferencia(int numRef)throws BDException, RefNotAvaliableException{
         Referencia ref = Referencia.obtenerReferencia(numRef);
         boolean val = false;
         
         if(ref != null && ref.isDisponible())
             val = true;
             
         if (val == false)
             throw new RefNotAvaliableException("Producto no disponible");
         return ref;
     }
    /**
     * Crea un pedido nuevo
     * @param fecha
     * @param ab
     * @return el pedido creado
     */ 
    public static Pedido nuevoPedido(Date fecha,Abonado ab){
         EstadoPedido estado = null;
         Pedido p = Pedido.crearPedido(estado.pendiente,fecha,ab);
         
         return p;
     }
    /**
     * Crea una nueva linea de pedido
     * @param cantidad
     * @param ref
     * @param p 
     */ 
    public static void nuevaLineaPedido( int cantidad, Referencia ref, Pedido p){
         boolean completada = false;
         LineaPedido lineaPedido = LineaPedido.crearLineaPedido(cantidad,completada, ref);
         
         p.addLineaPedido(lineaPedido);
     }
     /**
     * Finaliza el pedido indicado
     * @param p
     * @throws BDException 
     */ 
     public static void finalizarPedido(Pedido p) throws BDException{
        p.setTotal();
        /* Obtiene la factura de este mes, sino es null, se crea. */
        ArrayList<Factura> facturas;
        ArrayList<Pedido> pedidos;
        Abonado abonado;
        Abonado abonadoPedActual=p.getAbonado();
        Factura factura=null;
        
        /*obtenemos la fecha*/
        Calendar cal=Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),
            cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        System.out.println(cal.getTime().toString());
        Date mes=new Date(cal.getTimeInMillis());
        
        /*Obtenemos Facturas*/
        facturas=Factura.obtenerFacturasMensuales(mes);
        
        /*comprobamos si alguna factura es la de nuestro Abonado*/
        if(!facturas.isEmpty()){
            int i=0;
            boolean existeFactura=false;
            do{
                pedidos=facturas.get(i).getPedidos();
                abonado=pedidos.get(0).getAbonado();
                if (abonadoPedActual.getNumeroAbonado()==abonado.getNumeroAbonado()) {
                    existeFactura=true;
                    factura=facturas.get(i);
                    factura.addPedido(p);
                    factura.actualizaImporteFactura();
                    factura.actualizaFactura();
                }
                i++;
            }while(i<facturas.size() && existeFactura==false);            
        }
            
        
        /*Si no hay factura la creamos*/
        if(factura==null){
            //obtener nextFactura
            Date today = Date.valueOf(LocalDate.now());
            EstadoFactura estado=EstadoFactura.emitida;
            int numFactura=GestorPersistenciaFactura.getNextFactura();
            factura= Factura.crearFactura(numFactura,today,estado);
            factura.addPedido(p);
            Factura.guardarFactura(factura, p.getImporte());
        }
          
        /*Persistencia de la factura, la del pedido y la de linea de pedido */
        
        Pedido.guardarPedido(p, p.getImporte(),factura.getNumeroFactura());
        ArrayList<LineaPedido> lineasPedidos = p.getLineasPedido();
        for(LineaPedido lp: lineasPedidos){
            lp.guardarLineaPedido(lp,p.getNumeroPedido());
        }
     }
}
