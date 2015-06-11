/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import dominio.Referencia;
import javax.swing.JOptionPane;

/**
 *
 * @author nurcanc
 */
public class VistaCURegistrarCompra extends javax.swing.JPanel {
    protected ControlVistaRegistrarCompra c;
    /**
     * Creates new form VistaCURegistrarCompra
     */
    public VistaCURegistrarCompra() {
        initComponents();
        c=new ControlVistaRegistrarCompra(this);
    }
    /**
     * Comprobamos que los elementos de la Compra son correctos
     * @return un booleano si son correctos o no
     */
    protected boolean compruebaElementosCompra(){
        boolean val=true;
        
        /*Mirar si tienen los campos por defecto*/
        /*Mirar si los campos estan vacios*/
        if(getIdCompra()==0) val = false;
        
        return val;
    }
    /**
     * Se obtiene el identificador de la compra
     * @return un identificador de la compra
     */
    protected int getIdCompra(){
        return Integer.parseInt(idCompraTextField.getText());
    }
    /**
     * Establece el identificador de la compra en la etiqueta
     * @param idCompra 
     */
    protected void setidComLabel(int idCompra){
        this.idCompraOutLabel.setText("idCompra");
    }
    /**
     * Establece el nombre de la bodega en la etiqueta
     * @param nombreBodega 
     */
    protected void setNombreBodegaLabel(String nombreBodega){
        this.nombreBodegaLabel.setText(nombreBodega);    
    }
    /**
     * Establece las unidades en la etiqueta
     * @param unidades 
     */
    protected void setUnidadesLabel(int unidades){
        this.unidadesLabel.setText("unidades");
    }
    /**
     * Lanza por pantalla un mensaje si existe un error
     * @param msg 
     */
    public void lanzaError(String msg){
        JOptionPane.showMessageDialog(this, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    /**
     * 
     */
    public void setComprobarEnabled(){
        this.comprobarButton.setEnabled(true);
    } 
    public void setSeleccionarEnabled(){
        this.seleccionarButton.setEnabled(true);
    }
    
    public void setFinalizarEnabled(boolean enabled){
        this.finalizarButton.setEnabled(enabled);
    }
    protected void showInforme(Referencia ref){

    }
    protected void mostrarTablas(boolean show){

    }
    /**
     * Evento de pulsado
     * @param evt 
     */
    private void comprobarButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                
         /*Comprobamos los campos*/
        if(compruebaElementosCompra()){
            /*Procesamos la comprobacion de idcompra*/
           c.comprobarIDCompra();
           
        }else{
            lanzaError("Identificador de compra vacio(s)\no con el valor por defecto");
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        entradaPanel = new javax.swing.JPanel();
        compraPanelLabel = new javax.swing.JLabel();
        idCompraLabel = new javax.swing.JLabel();
        comprobarButton = new javax.swing.JButton();
        idCompraTextField = new javax.swing.JTextField();
        volverButton = new javax.swing.JButton();
        salidaPanel = new javax.swing.JPanel();
        idCompraOutLabel = new javax.swing.JLabel();
        nombreBodegaLabel = new javax.swing.JLabel();
        unidadesLabel = new javax.swing.JLabel();
        seleccionarButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        referenciaTable = new javax.swing.JTable();
        finalizarButton = new javax.swing.JButton();

        compraPanelLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        compraPanelLabel.setText("Compra");

        idCompraLabel.setText("IDCompra");

        comprobarButton.setText("Comprobar");

        idCompraTextField.setText(" idCompra");

        volverButton.setText("volver");

        javax.swing.GroupLayout entradaPanelLayout = new javax.swing.GroupLayout(entradaPanel);
        entradaPanel.setLayout(entradaPanelLayout);
        entradaPanelLayout.setHorizontalGroup(
            entradaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(entradaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(entradaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(entradaPanelLayout.createSequentialGroup()
                        .addComponent(volverButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(compraPanelLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(entradaPanelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(entradaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comprobarButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(entradaPanelLayout.createSequentialGroup()
                        .addComponent(idCompraLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(idCompraTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );
        entradaPanelLayout.setVerticalGroup(
            entradaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(entradaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(compraPanelLabel)
                .addGap(20, 20, 20)
                .addGroup(entradaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idCompraLabel)
                    .addComponent(idCompraTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(comprobarButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(volverButton)
                .addContainerGap())
        );

        idCompraOutLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        idCompraOutLabel.setText("iDCompra");

        nombreBodegaLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nombreBodegaLabel.setText("nombreBodega");

        unidadesLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        unidadesLabel.setText("unidades");

        seleccionarButton.setText("seleccionar");

        referenciaTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "codigo", "cajas", "contenidoENCL", "precio", "disponible"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Boolean.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(referenciaTable);

        finalizarButton.setText("finalizar");

        javax.swing.GroupLayout salidaPanelLayout = new javax.swing.GroupLayout(salidaPanel);
        salidaPanel.setLayout(salidaPanelLayout);
        salidaPanelLayout.setHorizontalGroup(
            salidaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(salidaPanelLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(salidaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                    .addGroup(salidaPanelLayout.createSequentialGroup()
                        .addComponent(seleccionarButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(finalizarButton))
                    .addComponent(idCompraOutLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nombreBodegaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(unidadesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        salidaPanelLayout.setVerticalGroup(
            salidaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(salidaPanelLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(idCompraOutLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nombreBodegaLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(unidadesLabel)
                .addGroup(salidaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(salidaPanelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 74, Short.MAX_VALUE))
                    .addGroup(salidaPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(salidaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(seleccionarButton)
                            .addComponent(finalizarButton))
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(entradaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(salidaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(entradaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(salidaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(VistaCUProcesarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaCUProcesarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaCUProcesarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaCUProcesarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaCURegistrarCompra().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel compraPanelLabel;
    private javax.swing.JButton comprobarButton;
    private javax.swing.JPanel entradaPanel;
    private javax.swing.JButton finalizarButton;
    private javax.swing.JLabel idCompraLabel;
    private javax.swing.JLabel idCompraOutLabel;
    private javax.swing.JTextField idCompraTextField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel nombreBodegaLabel;
    private javax.swing.JTable referenciaTable;
    private javax.swing.JPanel salidaPanel;
    private javax.swing.JButton seleccionarButton;
    private javax.swing.JLabel unidadesLabel;
    private javax.swing.JButton volverButton;
    // End of variables declaration//GEN-END:variables
}
