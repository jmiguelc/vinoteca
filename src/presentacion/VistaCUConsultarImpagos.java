/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import dominio.Abonado;
import dominio.Factura;
import dominio.Pedido;
import java.security.PrivilegedActionException;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author monrae
 */
public class VistaCUConsultarImpagos extends javax.swing.JFrame {

    protected ControlVistaConsultarImpagos c;
    /**
     * Creates new form VistaCUConsultarImpagos
     */
    public VistaCUConsultarImpagos() {
        initComponents();
        mostrarTablas(false);
        c=new ControlVistaConsultarImpagos(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleLabel = new javax.swing.JLabel();
        fechaFiled = new javax.swing.JFormattedTextField();
        consultaButton = new javax.swing.JButton();
        scrollInforme1 = new javax.swing.JScrollPane();
        informeTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        scrollInforme2 = new javax.swing.JScrollPane();
        informeTable2 = new javax.swing.JTable();
        volverButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Consultar Impagos");

        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Consultar Impagos");

        fechaFiled.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("d/MM/yyyy"))));
        fechaFiled.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fechaFiled.setText("dd/mm/aaaa");
        fechaFiled.setToolTipText("");

        consultaButton.setText("Consultar");
        consultaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultaButtonActionPerformed(evt);
            }
        });

        informeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Número de Factura", "Fecha de Emisión", "Importe (€)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        informeTable.setEnabled(false);
        scrollInforme1.setViewportView(informeTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 330, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 330, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        informeTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Número de Factura", "Número de Pedido", "Fecha de Realizacion", "Importe (€)", "Fecha de Recepción", "Estado", "Abonado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Double.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollInforme2.setViewportView(informeTable2);
        if (informeTable2.getColumnModel().getColumnCount() > 0) {
            informeTable2.getColumnModel().getColumn(0).setResizable(false);
            informeTable2.getColumnModel().getColumn(1).setResizable(false);
            informeTable2.getColumnModel().getColumn(2).setResizable(false);
            informeTable2.getColumnModel().getColumn(3).setResizable(false);
            informeTable2.getColumnModel().getColumn(4).setResizable(false);
            informeTable2.getColumnModel().getColumn(5).setResizable(false);
            informeTable2.getColumnModel().getColumn(6).setResizable(false);
        }

        volverButton.setText("Volver");
        volverButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                            .addComponent(fechaFiled)
                            .addComponent(consultaButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrollInforme1)
                            .addComponent(scrollInforme2))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(volverButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(titleLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaFiled, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(consultaButton))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollInforme1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(scrollInforme2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(volverButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void consultaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultaButtonActionPerformed
        // TODO add your handling code here:
        c.procesarDatosConsulta();
    }//GEN-LAST:event_consultaButtonActionPerformed

    private void volverButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverButtonActionPerformed
        // TODO add your handling code here:      
        VistaOpcionesEncContabilidad v=new VistaOpcionesEncContabilidad();
        v.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_volverButtonActionPerformed
    
    public void lanzaError(String msg){
        JOptionPane.showMessageDialog(this, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    
    public void lanzaAdvertencia(String msg){
        JOptionPane.showMessageDialog(this, msg, "WARNING", JOptionPane.WARNING_MESSAGE);
    }
    
    protected Date getFecha() throws ParseException,PrivilegedActionException{
        SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy");
        Date fecha=null;
        //Date fechaSQL=null;
        
        fecha=df.parse(fechaFiled.getText());
        //fechaSQL=new Date(fecha.getTime());
        
        return fecha;
    }
    
    protected void showInforme(ArrayList<Factura> facturas){
        Factura factura;
        Pedido pedido;
        Abonado abonado;
        ArrayList<Pedido> pedidos;
        DefaultTableModel modelo;
        int numPedidos = 0;
        
        modelo=(DefaultTableModel)informeTable.getModel();
        modelo.setRowCount(facturas.size());
        informeTable.setModel(modelo);
        
        modelo=(DefaultTableModel)informeTable2.getModel();
        for (Factura factura1 : facturas) {
            numPedidos=numPedidos+factura1.getPedidos().size();
        }
        modelo.setRowCount(numPedidos);
        informeTable2.setModel(modelo);
        
        mostrarTablas(true);
        
        for(int i=0;i<facturas.size();i++){
            factura=facturas.get(i);
            pedidos=factura.getPedidos();
            informeTable.setValueAt(factura.getNumeroFactura(),i ,0);
            informeTable.setValueAt(factura.getFechaEmision(),i ,1);
            informeTable.setValueAt(factura.getImporte(),i ,2);
            for(int j=0;j<pedidos.size();j++){
                pedido=pedidos.get(j);
                informeTable2.setValueAt(factura.getNumeroFactura(), j, 0);
                informeTable2.setValueAt(pedido.getNumeroPedido(), j, 1);
                informeTable2.setValueAt(pedido.getFechaRealizacion(), j, 2);
                informeTable2.setValueAt(pedido.getImporte(), j, 3);
                informeTable2.setValueAt(pedido.getFechaRecepcion(), j, 4);
                informeTable2.setValueAt(pedido.getEstado(), j, 5);
                abonado=pedido.getAbonado();
                informeTable2.setValueAt(abonado.getNombre()+" "+abonado.getApellidos(), j, 6);
            }
        }
    }
    
    /**
     * parametro que cambia la visibilidad de la tabla segun el parametro show
     * @param show si el parametro es true entonces se visualiza la tabla<br>
     * por el contrario si es false se oculta
     */
    protected void mostrarTablas(boolean show){
        scrollInforme1.setVisible(show);
        scrollInforme2.setVisible(show);
        getContentPane().validate();
        getContentPane().repaint();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaCUConsultarImpagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaCUConsultarImpagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaCUConsultarImpagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaCUConsultarImpagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaCUConsultarImpagos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton consultaButton;
    private javax.swing.JFormattedTextField fechaFiled;
    private javax.swing.JTable informeTable;
    private javax.swing.JTable informeTable2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane scrollInforme1;
    private javax.swing.JScrollPane scrollInforme2;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JButton volverButton;
    // End of variables declaration//GEN-END:variables
}
