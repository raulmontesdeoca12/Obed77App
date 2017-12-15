/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obed77.views.compras;

import core.controlador.session.CompraSession;
import core.logger.LogService;
import core.modelo.to.CompraTo;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Window;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import obed77.Principal;
import obed77.views.dialogosComunes.JOptionDialog;


/**
 *
 * @author Saito
 */
public class PanelCompras extends javax.swing.JPanel {
    static DefaultTableModel tableModelProv;
    /**
     * Creates new form Panel_Proveedores
     */
    public PanelCompras() {
        initComponents();
        jScrollPane1.setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);
        cargar();
        inhabilitarBotones();
    }

    public static void cargar()
  {
    try {
      tableModelProv = (javax.swing.table.DefaultTableModel)tablaCompras.getModel();
      
      tableModelProv.setRowCount(0);
      Object[] fila = new Object[tableModelProv.getColumnCount()];
      CompraSession compSession = new CompraSession();
      java.util.ArrayList<CompraTo> lista = compSession.getCompras();
      for (CompraTo to : lista) {
        fila[0] = to.getCod();
        fila[1] = to.getFecha();
        fila[2] = to.getProveedor();
        fila[3] = to.getUsuario();
        fila[4] = to.getDocumento();
        fila[5] = to.getReferencia();
        fila[6] = to.getTotalCompra();
        fila[7] = to.getEstatusString();
        tableModelProv.addRow(fila);
      }
      filtro("");
    } catch (ClassNotFoundException ex) {
      core.logger.LogService.logger.error(obed77.Principal.getUsuarioPrincipal().getUser(), "Error");
      
      obed77.views.dialogosComunes.JOptionDialog.showMessageDialog(core.controlador.principal.ErroresMap.MessageError(9999, ""), "Error", 2);
    } catch (java.sql.SQLException ex) {
      core.logger.LogService.logger.error(obed77.Principal.getUsuarioPrincipal().getUser(), "Error");
      obed77.views.dialogosComunes.JOptionDialog.showMessageDialog(core.controlador.principal.ErroresMap.MessageError(9999, ""), "Error", 2);
    } finally {
      tablaCompras.setModel(tableModelProv);
    }
  }
  







  private void inhabilitarBotones()
  {
    btnDetalles.setEnabled(false);
    btn_opc_anular.setEnabled(false);
  }
  
  private void validarSeleccionFila() {
    int fsel = tablaCompras.getSelectedRow();
    if (fsel != -1) {
      String estatus = tablaCompras.getValueAt(fsel, 7).toString();
      CompraTo cat = new CompraTo();
      cat.setEstatusString(estatus);
      btnDetalles.setEnabled(true);
      btn_opc_anular.setEnabled(cat.getEstatus() == 1);
    }
    else {
      inhabilitarBotones();
    }
  }
  


  private void anularCompra()
  {
    int fsel = tablaCompras.getSelectedRow();
    long cod = Long.parseLong(tablaCompras.getValueAt(fsel, 0).toString());
    try {
     
      CompraSession session = new CompraSession();
      session.anularCompra(cod);
      JOptionDialog.showMessageDialog("Compra Anulada Correctamente", "Compras", 2);
      cargar();
    } catch (java.sql.SQLException ex) {
      core.logger.LogService.logger.error(obed77.Principal.getUsuarioPrincipal().getUser(), "Error");
      obed77.views.dialogosComunes.JOptionDialog.showMessageDialog(core.controlador.principal.ErroresMap.MessageError(ex.getErrorCode(), cod+""), "Error", 2);
    } catch (Exception ex) {
      core.logger.LogService.logger.error(obed77.Principal.getUsuarioPrincipal().getUser(), "Error");
      obed77.views.dialogosComunes.JOptionDialog.showMessageDialog(core.controlador.principal.ErroresMap.MessageError(9999,""), "Error", 2);
    }
  }
  
  private void verDetalles(){
  }
  
  private static void filtro(String consulta) {
    javax.swing.table.TableRowSorter<javax.swing.table.DefaultTableModel> tr = new javax.swing.table.TableRowSorter(tableModelProv);
    tablaCompras.setRowSorter(tr);
    tr.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)" + consulta, new int[0]));
  }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jAASLoginService1 = new org.jdesktop.swingx.auth.JAASLoginService();
        jPanel1 = new javax.swing.JPanel();
        Panel_Botones = new javax.swing.JPanel();
        btn_opc_nuevo = new javax.swing.JButton();
        btn_opc_anular = new javax.swing.JButton();
        btnDetalles = new javax.swing.JButton();
        pan_opc_6 = new javax.swing.JPanel();
        btn_opc_cerrar = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        btn_opc_restaurar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCompras = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 204, 204));
        setOpaque(false);

        jPanel1.setOpaque(false);

        Panel_Botones.setBackground(new java.awt.Color(229, 232, 232));
        Panel_Botones.setOpaque(false);

        btn_opc_nuevo.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btn_opc_nuevo.setForeground(new java.awt.Color(255, 255, 255));
        btn_opc_nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icono_nuevo.png"))); // NOI18N
        btn_opc_nuevo.setText("Nuevo");
        btn_opc_nuevo.setToolTipText("Crear nuevo producto");
        btn_opc_nuevo.setBorderPainted(false);
        btn_opc_nuevo.setContentAreaFilled(false);
        btn_opc_nuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_opc_nuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_opc_nuevo.setIconTextGap(1);
        btn_opc_nuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_opc_nuevo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_opc_nuevoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_opc_nuevoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_opc_nuevoMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_opc_nuevoMouseReleased(evt);
            }
        });
        btn_opc_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_opc_nuevoActionPerformed(evt);
            }
        });

        btn_opc_anular.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btn_opc_anular.setForeground(new java.awt.Color(255, 255, 255));
        btn_opc_anular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icono_anular_40.png"))); // NOI18N
        btn_opc_anular.setText("Anular");
        btn_opc_anular.setToolTipText("Inhabilitar el producto");
        btn_opc_anular.setBorderPainted(false);
        btn_opc_anular.setContentAreaFilled(false);
        btn_opc_anular.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_opc_anular.setEnabled(false);
        btn_opc_anular.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_opc_anular.setIconTextGap(1);
        btn_opc_anular.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_opc_anular.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_opc_anularMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_opc_anularMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_opc_anularMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_opc_anularMouseReleased(evt);
            }
        });
        btn_opc_anular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_opc_anularActionPerformed(evt);
            }
        });

        btnDetalles.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnDetalles.setForeground(new java.awt.Color(255, 255, 255));
        btnDetalles.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icono_buscar_40.png"))); // NOI18N
        btnDetalles.setText("Detalles");
        btnDetalles.setToolTipText("Inhabilitar el producto");
        btnDetalles.setBorderPainted(false);
        btnDetalles.setContentAreaFilled(false);
        btnDetalles.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDetalles.setEnabled(false);
        btnDetalles.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDetalles.setIconTextGap(1);
        btnDetalles.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDetalles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDetallesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDetallesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDetallesMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnDetallesMouseReleased(evt);
            }
        });
        btnDetalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetallesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Panel_BotonesLayout = new javax.swing.GroupLayout(Panel_Botones);
        Panel_Botones.setLayout(Panel_BotonesLayout);
        Panel_BotonesLayout.setHorizontalGroup(
            Panel_BotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_BotonesLayout.createSequentialGroup()
                .addComponent(btn_opc_nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btn_opc_anular)
                .addGap(0, 0, 0)
                .addComponent(btnDetalles)
                .addGap(100, 100, 100))
        );

        Panel_BotonesLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnDetalles, btn_opc_anular, btn_opc_nuevo});

        Panel_BotonesLayout.setVerticalGroup(
            Panel_BotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_BotonesLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(Panel_BotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Panel_BotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btn_opc_nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_opc_anular, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        Panel_BotonesLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_opc_anular, btn_opc_nuevo});

        pan_opc_6.setBackground(new java.awt.Color(229, 232, 232));
        pan_opc_6.setOpaque(false);

        btn_opc_cerrar.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btn_opc_cerrar.setForeground(new java.awt.Color(255, 255, 255));
        btn_opc_cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icono_cerrar.png"))); // NOI18N
        btn_opc_cerrar.setText("Cerrar");
        btn_opc_cerrar.setBorderPainted(false);
        btn_opc_cerrar.setContentAreaFilled(false);
        btn_opc_cerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_opc_cerrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_opc_cerrar.setIconTextGap(1);
        btn_opc_cerrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_opc_cerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_opc_cerrarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_opc_cerrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_opc_cerrarMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_opc_cerrarMouseReleased(evt);
            }
        });
        btn_opc_cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_opc_cerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pan_opc_6Layout = new javax.swing.GroupLayout(pan_opc_6);
        pan_opc_6.setLayout(pan_opc_6Layout);
        pan_opc_6Layout.setHorizontalGroup(
            pan_opc_6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_opc_6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_opc_cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pan_opc_6Layout.setVerticalGroup(
            pan_opc_6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_opc_6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_opc_cerrar)
                .addGap(0, 0, 0))
        );

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
        btn_opc_restaurar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_opc_restaurarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_opc_restaurarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_opc_restaurarMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_opc_restaurarMouseReleased(evt);
            }
        });
        btn_opc_restaurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_opc_restaurarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Buscar:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Panel_Botones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_opc_restaurar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pan_opc_6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pan_opc_6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(Panel_Botones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addComponent(btn_opc_restaurar))
                            .addGap(7, 7, 7))))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setOpaque(false);

        jScrollPane1.setAutoscrolls(true);

        tablaCompras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Fecha", "Proveedor", "Usuario", "Documento", "Referencia", "Total Compra", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaCompras.setOpaque(false);
        tablaCompras.getTableHeader().setReorderingAllowed(false);
        tablaCompras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaComprasMouseClicked(evt);
            }
        });
        tablaCompras.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tablaComprasPropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(tablaCompras);
        if (tablaCompras.getColumnModel().getColumnCount() > 0) {
            tablaCompras.getColumnModel().getColumn(0).setPreferredWidth(50);
            tablaCompras.getColumnModel().getColumn(0).setMaxWidth(80);
            tablaCompras.getColumnModel().getColumn(1).setPreferredWidth(80);
            tablaCompras.getColumnModel().getColumn(1).setMaxWidth(90);
            tablaCompras.getColumnModel().getColumn(2).setPreferredWidth(200);
            tablaCompras.getColumnModel().getColumn(3).setPreferredWidth(80);
            tablaCompras.getColumnModel().getColumn(3).setMaxWidth(90);
            tablaCompras.getColumnModel().getColumn(4).setPreferredWidth(80);
            tablaCompras.getColumnModel().getColumn(4).setMaxWidth(90);
            tablaCompras.getColumnModel().getColumn(5).setPreferredWidth(100);
            tablaCompras.getColumnModel().getColumn(5).setMaxWidth(120);
            tablaCompras.getColumnModel().getColumn(6).setPreferredWidth(80);
            tablaCompras.getColumnModel().getColumn(6).setMaxWidth(150);
            tablaCompras.getColumnModel().getColumn(7).setMinWidth(60);
            tablaCompras.getColumnModel().getColumn(7).setMaxWidth(75);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_opc_nuevoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_opc_nuevoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_opc_nuevoMouseClicked

    private void btn_opc_nuevoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_opc_nuevoMouseEntered
        btn_opc_nuevo.setBackground(new Color(174, 214, 241));
        btn_opc_nuevo.setOpaque(true);            // TODO add your handling code here:
    }//GEN-LAST:event_btn_opc_nuevoMouseEntered

    private void btn_opc_nuevoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_opc_nuevoMouseExited
        btn_opc_nuevo.setOpaque(false);  // TODO add your handling code here:
    }//GEN-LAST:event_btn_opc_nuevoMouseExited

    private void btn_opc_nuevoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_opc_nuevoMouseReleased
        btn_opc_nuevo.setBackground(new Color(174, 214, 241));// TODO add your handling code here:
    }//GEN-LAST:event_btn_opc_nuevoMouseReleased

    private void btn_opc_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_opc_nuevoActionPerformed
        btn_opc_nuevo.setOpaque(false);
        Window parentWindow = SwingUtilities.windowForComponent(this);
        NuevaCompra nuevo = new NuevaCompra((Frame) parentWindow, true);
        nuevo.setVisible(true);
        inhabilitarBotones();
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_opc_nuevoActionPerformed

    private void btn_opc_anularMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_opc_anularMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_opc_anularMouseClicked

    private void btn_opc_anularMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_opc_anularMouseEntered
        if (btn_opc_anular.isEnabled()) {
            btn_opc_anular.setBackground(new Color(174, 214, 241));
            btn_opc_anular.setOpaque(true);
        }// TODO add your handling code here:
    }//GEN-LAST:event_btn_opc_anularMouseEntered

    private void btn_opc_anularMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_opc_anularMouseExited
        if (btn_opc_anular.isEnabled()) {
            btn_opc_anular.setOpaque(false);
        }// TODO add your handling code here:
    }//GEN-LAST:event_btn_opc_anularMouseExited

    private void btn_opc_anularMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_opc_anularMouseReleased
        if (btn_opc_anular.isEnabled()) {
            btn_opc_anular.setBackground(new Color(174, 214, 241));
        } // TODO add your handling code here:
    }//GEN-LAST:event_btn_opc_anularMouseReleased

    private void btn_opc_anularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_opc_anularActionPerformed
        btn_opc_anular.setOpaque(false);
        int opc = JOptionDialog.showConfirmDialog("¿Desea anular esta compra?", "Inhabilitar Proveedor", JOptionDialog.SI_NO_OPTION);
        if (opc == 0) {
            anularCompra();
        }
        inhabilitarBotones();
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_opc_anularActionPerformed

    private void btn_opc_cerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_opc_cerrarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_opc_cerrarMouseClicked

    private void btn_opc_cerrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_opc_cerrarMouseEntered
        btn_opc_cerrar.setBackground(new Color(174, 214, 241));
        btn_opc_cerrar.setOpaque(true);    // TODO add your handling code here:
    }//GEN-LAST:event_btn_opc_cerrarMouseEntered

    private void btn_opc_cerrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_opc_cerrarMouseExited
        btn_opc_cerrar.setOpaque(false);  // TODO add your handling code here:
    }//GEN-LAST:event_btn_opc_cerrarMouseExited

    private void btn_opc_cerrarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_opc_cerrarMouseReleased
        btn_opc_cerrar.setBackground(new Color(174, 214, 241)); // TODO add your handling code here:
    }//GEN-LAST:event_btn_opc_cerrarMouseReleased

    private void btn_opc_cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_opc_cerrarActionPerformed
        int res = JOptionDialog.showConfirmDialog("¿Seguro Que Desea Cerrar Esta Pestaña?", "Cerrar Pestaña", JOptionDialog.SI_NO_OPTION);
        if (res == 0) {
            LogService.logger.info(Principal.getUsuarioPrincipal().getUser(), "Cerrando Panel Proveedores");
            Principal.multiPanel.remove(this);
        }            // TODO add your handling code here:
    }//GEN-LAST:event_btn_opc_cerrarActionPerformed

    private void btn_opc_restaurarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_opc_restaurarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_opc_restaurarMouseClicked

    private void btn_opc_restaurarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_opc_restaurarMouseEntered
        btn_opc_restaurar.setBackground(new Color(174, 214, 241));
        btn_opc_restaurar.setOpaque(true);     // TODO add your handling code here:
    }//GEN-LAST:event_btn_opc_restaurarMouseEntered

    private void btn_opc_restaurarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_opc_restaurarMouseExited
        btn_opc_restaurar.setOpaque(false);  // TODO add your handling code here:
    }//GEN-LAST:event_btn_opc_restaurarMouseExited

    private void btn_opc_restaurarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_opc_restaurarMouseReleased
        btn_opc_restaurar.setBackground(new Color(174, 214, 241)); // TODO add your handling code here:
    }//GEN-LAST:event_btn_opc_restaurarMouseReleased

    private void btn_opc_restaurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_opc_restaurarActionPerformed
        txtBuscar.setText("");
        cargar();
        filtro(txtBuscar.getText());          // TODO add your handling code here:
    }//GEN-LAST:event_btn_opc_restaurarActionPerformed

    private void tablaComprasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaComprasMouseClicked
        validarSeleccionFila();        // TODO add your handling code here:
    }//GEN-LAST:event_tablaComprasMouseClicked

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
    filtro(txtBuscar.getText());// TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void tablaComprasPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tablaComprasPropertyChange
    if(tablaCompras.getSelectedRow() == -1){
    inhabilitarBotones();
    }// TODO add your handling code here:
    }//GEN-LAST:event_tablaComprasPropertyChange

    private void btnDetallesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDetallesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDetallesMouseClicked

    private void btnDetallesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDetallesMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDetallesMouseEntered

    private void btnDetallesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDetallesMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDetallesMouseExited

    private void btnDetallesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDetallesMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDetallesMouseReleased

    private void btnDetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetallesActionPerformed
        btnDetalles.setOpaque(false);
        Window parentWindow = SwingUtilities.windowForComponent(this);
        DetallesCompra nuevo = new DetallesCompra((Frame) parentWindow, true);
        nuevo.setVisible(true);
        inhabilitarBotones();
    }//GEN-LAST:event_btnDetallesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel_Botones;
    private javax.swing.JButton btnDetalles;
    private javax.swing.JButton btn_opc_anular;
    private javax.swing.JButton btn_opc_cerrar;
    private javax.swing.JButton btn_opc_nuevo;
    private javax.swing.JButton btn_opc_restaurar;
    private org.jdesktop.swingx.auth.JAASLoginService jAASLoginService1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pan_opc_6;
    public static javax.swing.JTable tablaCompras;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
