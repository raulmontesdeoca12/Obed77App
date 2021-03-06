/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obed77.views.cliente;

import core.controlador.principal.Utilidades;
import core.controlador.session.ClienteSession;
import core.modelo.to.ClienteTo;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;



/**
 *
 * @author Saito
 */
public class BuscarCliente extends javax.swing.JDialog {
    private DefaultTableModel tableModelBuscarCli = new DefaultTableModel();
    int x, y;
    public ClienteTo cliente;
    public BuscarCliente(java.awt.Dialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cargar();

    }

    public ClienteTo getCliente() {
        return cliente;
    }

    public void setCliente(ClienteTo cliente) {
        this.cliente = cliente;
    }

    private void cargar() {
        tableModelBuscarCli = (DefaultTableModel) tablaBuscarCliente.getModel();
        tableModelBuscarCli.setRowCount(0);
        Object[] fila = new Object[tableModelBuscarCli.getColumnCount()];
        ClienteSession prvSession = new ClienteSession();
        try {
            ArrayList<ClienteTo> tos = prvSession.getClientesActivos();
             for (ClienteTo to : tos) {
                fila[0] = to.getTipoDocumento();
                fila[1] = to.getDocumento();
                fila[2] = to;
                tableModelBuscarCli.addRow(fila);
      }
      filtro("");
        } catch (ClassNotFoundException ex) {
            core.logger.LogService.logger.error(obed77.Principal.getUsuarioPrincipal().getUser(), "Error: "+ex.getMessage());
            obed77.views.dialogosComunes.JOptionDialog.showMessageDialog(core.controlador.principal.ErroresMap.MessageError(9999, ""), "Error", 2);
        } catch (java.sql.SQLException ex) {
            core.logger.LogService.logger.error(obed77.Principal.getUsuarioPrincipal().getUser(), "Error: "+ex.getMessage());
            obed77.views.dialogosComunes.JOptionDialog.showMessageDialog(core.controlador.principal.ErroresMap.MessageError(9999, ""), "Error", 2);
        }finally {
        tablaBuscarCliente.setModel(tableModelBuscarCli);
        }
    }
    
    private void filtro(String consulta) {
    TableRowSorter<DefaultTableModel> tr = new TableRowSorter(tableModelBuscarCli);
    tablaBuscarCliente.setRowSorter(tr);
    tr.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)" + consulta, new int[0]));
  }
    
    private void validarAceptar(){
        if(tablaBuscarCliente.getSelectedRow() == -1){
            btnCrear.setEnabled(false);
        }else{
            btnCrear.setEnabled(true);
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

        buttonAeroLeft1 = new org.edisoncor.gui.button.ButtonAeroLeft();
        Panel = new javax.swing.JPanel();
        panelTop = new javax.swing.JPanel();
        header = new javax.swing.JLabel();
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        btnCrear = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaBuscarCliente = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        btn_opc_restaurar = new javax.swing.JButton();

        buttonAeroLeft1.setText("buttonAeroLeft1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setUndecorated(true);

        Panel.setBackground(new java.awt.Color(220, 220, 220));
        Panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        panelTop.setBackground(new java.awt.Color(220, 220, 220));
        panelTop.setPreferredSize(new java.awt.Dimension(701, 25));

        header.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        header.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        header.setText("Buscar Cliente");
        header.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        header.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                headerMouseDragged(evt);
            }
        });
        header.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                headerMousePressed(evt);
            }
        });

        javax.swing.GroupLayout panelTopLayout = new javax.swing.GroupLayout(panelTop);
        panelTop.setLayout(panelTopLayout);
        panelTopLayout.setHorizontalGroup(
            panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
        );
        panelTopLayout.setVerticalGroup(
            panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTopLayout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoApp.jpg"))); // NOI18N

        btnCrear.setBackground(new java.awt.Color(255, 255, 255));
        btnCrear.setText("Aceptar");
        btnCrear.setBorderPainted(false);
        btnCrear.setContentAreaFilled(false);
        btnCrear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCrear.setEnabled(false);
        btnCrear.setMaximumSize(new java.awt.Dimension(110, 25));
        btnCrear.setMinimumSize(new java.awt.Dimension(110, 25));
        btnCrear.setOpaque(true);
        btnCrear.setPreferredSize(new java.awt.Dimension(110, 25));
        btnCrear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCrearMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCrearMouseExited(evt);
            }
        });
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.setBorderPainted(false);
        btnCancelar.setContentAreaFilled(false);
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelar.setMaximumSize(new java.awt.Dimension(110, 25));
        btnCancelar.setMinimumSize(new java.awt.Dimension(110, 25));
        btnCancelar.setOpaque(true);
        btnCancelar.setPreferredSize(new java.awt.Dimension(110, 25));
        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCancelarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCancelarMouseExited(evt);
            }
        });
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Buscar:");

        jScrollPane1.setAutoscrolls(true);

        tablaBuscarCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Doc", "Número", "Nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class
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
        tablaBuscarCliente.setOpaque(false);
        tablaBuscarCliente.getTableHeader().setReorderingAllowed(false);
        tablaBuscarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaBuscarClienteMouseClicked(evt);
            }
        });
        tablaBuscarCliente.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tablaBuscarClientePropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(tablaBuscarCliente);
        if (tablaBuscarCliente.getColumnModel().getColumnCount() > 0) {
            tablaBuscarCliente.getColumnModel().getColumn(0).setMinWidth(60);
            tablaBuscarCliente.getColumnModel().getColumn(0).setMaxWidth(60);
            tablaBuscarCliente.getColumnModel().getColumn(1).setMinWidth(80);
            tablaBuscarCliente.getColumnModel().getColumn(2).setMinWidth(150);
        }

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        btn_opc_restaurar.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btn_opc_restaurar.setForeground(new java.awt.Color(255, 255, 255));
        btn_opc_restaurar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icono_reestablecer_mini.png"))); // NOI18N
        btn_opc_restaurar.setToolTipText("Restaurar Tabla");
        btn_opc_restaurar.setBorderPainted(false);
        btn_opc_restaurar.setContentAreaFilled(false);
        btn_opc_restaurar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_opc_restaurar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelImage1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelImage1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_opc_restaurar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelImage1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        panelImage1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCancelar, btnCrear});

        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelImage1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)))
                    .addComponent(btn_opc_restaurar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        panelImage1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCancelar, btnCrear});

        javax.swing.GroupLayout PanelLayout = new javax.swing.GroupLayout(Panel);
        Panel.setLayout(PanelLayout);
        PanelLayout.setHorizontalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTop, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
            .addGroup(PanelLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        PanelLayout.setVerticalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLayout.createSequentialGroup()
                .addComponent(panelTop, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - x, point.y - y);         // TODO add your handling code here:
    }//GEN-LAST:event_headerMouseDragged

    private void headerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMousePressed
        x = evt.getX();
        y = evt.getY();         // TODO add your handling code here:
    }//GEN-LAST:event_headerMousePressed

    private void tablaBuscarClientePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tablaBuscarClientePropertyChange
        validarAceptar();// TODO add your handling code here:
    }//GEN-LAST:event_tablaBuscarClientePropertyChange

    private void tablaBuscarClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaBuscarClienteMouseClicked
        validarAceptar();      // TODO add your handling code here:
    }//GEN-LAST:event_tablaBuscarClienteMouseClicked

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        cliente = null;
        this.dispose();       // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnCancelarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseExited
        btnCancelar.setBackground(Utilidades.getColorNormalMenu()); // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarMouseExited

    private void btnCancelarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseEntered
        btnCancelar.setBackground(Utilidades.getColorEntered());// TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarMouseEntered

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        cliente = (ClienteTo) tablaBuscarCliente.getValueAt(tablaBuscarCliente.getSelectedRow(), 2);
        dispose();// TODO add your handling code here:
    }//GEN-LAST:event_btnCrearActionPerformed

    private void btnCrearMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCrearMouseExited
        btnCrear.setBackground(Utilidades.getColorNormalMenu()); // TODO add your handling code here:
    }//GEN-LAST:event_btnCrearMouseExited

    private void btnCrearMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCrearMouseEntered
        btnCrear.setBackground(Utilidades.getColorEntered());        // TODO add your handling code here:
    }//GEN-LAST:event_btnCrearMouseEntered

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
    filtro(txtBuscar.getText());        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarKeyReleased

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
            java.util.logging.Logger.getLogger(BuscarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BuscarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BuscarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BuscarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                BuscarCliente dialog = new BuscarCliente(new javax.swing.JDialog(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btn_opc_restaurar;
    private org.edisoncor.gui.button.ButtonAeroLeft buttonAeroLeft1;
    private javax.swing.JLabel header;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JScrollPane jScrollPane1;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private javax.swing.JPanel panelTop;
    private javax.swing.JTable tablaBuscarCliente;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables

}
