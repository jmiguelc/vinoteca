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
        
     public static Referencia comprobarReferencia(int numRef)throws BDException, RefNotAvaliableException{
         Referencia ref = Referencia.getReferencia(numRef);
         boolean val = false;
         
         if(ref != null && ref.isDisponible())
             val = true;
             
         if (val == false)
             throw new RefNotAvaliableException("Producto no disponible");
         return ref;
     }
     
     public static Pedido nuevoPedido(Date fecha,Abonado ab){
         EstadoPedido estado = null;
         Pedido p = new Pedido(estado.pendiente,fecha,ab);
         
         return p;
     }
     
     public static void nuevaLineaPedido( int cantidad, Referencia ref, Pedido p){
         boolean completada = false;
         LineaPedido lineaPedido = new LineaPedido(cantidad,completada, ref);
         
         p.addLineaPedido(lineaPedido);
     }
     
     public static void finalizarPedido(Pedido p) throws BDException{
        double importe = p.getTotal();
        /* Obtiene la factura de este mes, sino es null, se crea. */
        ArrayList<Factura> facturas;
        ArrayList<Pedido> pedidos;
        Abonado abonado;
        Abonado abonadoPedActual=p.getAbonado();
        Pedido pedido;
        Factura factura=null;
        
        Calendar cal=Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),
            cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        System.out.println(cal.getTime().toString());
        Date mes=new Date(cal.getTimeInMillis());
         
        facturas=Factura.obtenerFacturasMensuales(mes);
        
        if(!facturas.isEmpty()){
            int i=0;
            boolean existeFactura=false;
            do{
                pedidos=facturas.get(i).getPedidos();
                abonado=pedidos.get(0).getAbonado();
                if (abonadoPedActual.getNumeroAbonado()==abonado.getNumeroAbonado()) {
                    existeFactura=true;
                    factura=facturas.get(i);
                }
                i++;
            }while(i<facturas.size() && existeFactura==false);   
        }
        
        if(factura==null){
            /*crear factura*/
        }
       
         /* Añadir el pedido actual a la factura */
        factura.addPedido(p);
         
        /*Persistencia de la factura, la del pedido y la de linea de pedido */
        //Factura
        //Pedido.guardarPedido(p, importe);
        //LineaPedido
     }
}