/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obed77.views.proveedor;

import core.logger.LogService;
import core.modelo.to.ProveedorTo;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Window;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import obed77.Principal;
import obed77.views.dialogosComunes.JOptionDialog;



/**
 *
 * @author Saito
 */
public class PanelProveedor extends javax.swing.JPanel {
    static DefaultTableModel tableModelProv;
    /**
     * Creates new form Panel_Proveedores
     */
    public PanelProveedor() {
        initComponents();
        jScrollPane1.setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);
        cargar();
        inhabilitarBotones();
        tablaProveedores.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }

    public static void cargar()
  {
    try {
      tableModelProv = (javax.swing.table.DefaultTableModel)tablaProveedores.getModel();
      
      tableModelProv.setRowCount(0);
      Object[] fila = new Object[tableModelProv.getColumnCount()];
      core.controlador.session.ProveedorSession provSession = new core.controlador.session.ProveedorSession();
      java.util.ArrayList<ProveedorTo> lista = provSession.getProveedores();
      for (ProveedorTo to : lista) {
        fila[0] = to.getTipoDocumento();
        fila[1] = to.getDocumento();
        fila[2] = to.getNombre();
        fila[3] = to.getContacto();
        fila[4] = to.getTelefono();
        fila[5] = to.getCorreo();
        fila[6] = to.getDireccion();
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
      tablaProveedores.setModel(tableModelProv);
    }
  }
  







  private void inhabilitarBotones()
  {
    btn_opc_modificar.setEnabled(false);
    btn_opc_habilitar.setEnabled(false);
    btn_opc_inhabilitar.setEnabled(false);
  }
  
  private void validarSeleccionFila() {
    int fsel = tablaProveedores.getSelectedRow();
    if (fsel != -1) {
      String estatus = tablaProveedores.getValueAt(fsel, 7).toString();
      ProveedorTo cat = new ProveedorTo();
      cat.setEstatusString(estatus);
      btn_opc_modificar.setEnabled(true);
      btn_opc_inhabilitar.setEnabled(cat.getEstatus() == 1);
      btn_opc_habilitar.setEnabled(cat.getEstatus() != 1);
    }
    else {
      inhabilitarBotones();
    }
  }
  


  private void inhabilitarProveedor()
  {
    int fsel = tablaProveedores.getSelectedRow();
    String tipDoc = tablaProveedores.getValueAt(fsel, 0).toString();
    String doc = tablaProveedores.getValueAt(fsel, 1).toString();
    String nombre = tablaProveedores.getValueAt(fsel, 2).toString();
    try {
      int estatus = 0;
      ProveedorTo to = new ProveedorTo();
      to.setTipoDocumento(tipDoc);
      to.setDocumento(doc);
      to.setEstatus(estatus);
      core.controlador.session.ProveedorSession session = new core.controlador.session.ProveedorSession();
      session.modificarEstatusProveedor(to);
      obed77.views.dialogosComunes.JOptionDialog.showMessageDialog("Proveedor Inhabilitado Correctamente", "Proveedores", 2);
      cargar();
    } catch (java.sql.SQLException ex) {
      core.logger.LogService.logger.error(obed77.Principal.getUsuarioPrincipal().getUser(), "Error");
      obed77.views.dialogosComunes.JOptionDialog.showMessageDialog(core.controlador.principal.ErroresMap.MessageError(ex.getErrorCode(), nombre), "Error", 2);
    } catch (Exception ex) {
      core.logger.LogService.logger.error(obed77.Principal.getUsuarioPrincipal().getUser(), "Error");
      obed77.views.dialogosComunes.JOptionDialog.showMessageDialog(core.controlador.principal.ErroresMap.MessageError(9999, ""), "Error", 2);
    }
  }
  
  private void habilitarProveedor() {
    int fsel = tablaProveedores.getSelectedRow();
    String tipDoc = tablaProveedores.getValueAt(fsel, 0).toString();
    String doc = tablaProveedores.getValueAt(fsel, 1).toString();
    String nombre = tablaProveedores.getValueAt(fsel, 2).toString();
    try {
      int estatus = 1;
      ProveedorTo to = new ProveedorTo();
      to.setTipoDocumento(tipDoc);
      to.setDocumento(doc);
      to.setEstatus(estatus);
      core.controlador.session.ProveedorSession session = new core.controlador.session.ProveedorSession();
      session.modificarEstatusProveedor(to);
      obed77.views.dialogosComunes.JOptionDialog.showMessageDialog("Proveedor Habilitado Correctamente", "Proveedores", 2);
      cargar();
    } catch (java.sql.SQLException ex) {
      core.logger.LogService.logger.error(obed77.Principal.getUsuarioPrincipal().getUser(), "Error");
      obed77.views.dialogosComunes.JOptionDialog.showMessageDialog(core.controlador.principal.ErroresMap.MessageError(ex.getErrorCode(), nombre), "Error", 2);
    } catch (Exception ex) {
      core.logger.LogService.logger.error(obed77.Principal.getUsuarioPrincipal().getUser(), "Error");
      obed77.views.dialogosComunes.JOptionDialog.showMessageDialog(core.controlador.principal.ErroresMap.MessageError(9999, ""), "Error", 2);
    }
  }
  
  private static void filtro(String consulta) {
    javax.swing.table.TableRowSorter<javax.swing.table.DefaultTableModel> tr = new javax.swing.table.TableRowSorter(tableModelProv);
    tablaProveedores.setRowSorter(tr);
    tr.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)" + consulta, new int[0]));
  }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jAASLoginService1 = new org.jdesktop.swingx.auth.JAASLoginService();
        jPanel1 = new javax.swing.JPanel();
        Panel_Botones_Proveedores = new javax.swing.JPanel();
        btn_opc_nuevo = new javax.swing.JButton();
        btn_opc_modificar = new javax.swing.JButton();
        btn_opc_habilitar = new javax.swing.JButton();
        btn_opc_inhabilitar = new javax.swing.JButton();
        pan_opc_6 = new javax.swing.JPanel();
        btn_opc_cerrar = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        btn_opc_restaurar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProveedores = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 204, 204));
        setOpaque(false);

        jPanel1.setOpaque(false);

        Panel_Botones_Proveedores.setBackground(new java.awt.Color(229, 232, 232));
        Panel_Botones_Proveedores.setOpaque(false);

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

        btn_opc_modificar.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btn_opc_modificar.setForeground(new java.awt.Color(255, 255, 255));
        btn_opc_modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icono_modificar.png"))); // NOI18N
        btn_opc_modificar.setText("Modificar");
        btn_opc_modificar.setToolTipText("Modificar el producto seleccionado");
        btn_opc_modificar.setBorderPainted(false);
        btn_opc_modificar.setContentAreaFilled(false);
        btn_opc_modificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_opc_modificar.setEnabled(false);
        btn_opc_modificar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_opc_modificar.setIconTextGap(1);
        btn_opc_modificar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_opc_modificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_opc_modificarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_opc_modificarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_opc_modificarMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_opc_modificarMouseReleased(evt);
            }
        });
        btn_opc_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_opc_modificarActionPerformed(evt);
            }
        });

        btn_opc_habilitar.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btn_opc_habilitar.setForeground(new java.awt.Color(255, 255, 255));
        btn_opc_habilitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icono_habilitar.png"))); // NOI18N
        btn_opc_habilitar.setText("Habilitar");
        btn_opc_habilitar.setToolTipText("Habilitar el Producto");
        btn_opc_habilitar.setBorderPainted(false);
        btn_opc_habilitar.setContentAreaFilled(false);
        btn_opc_habilitar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_opc_habilitar.setEnabled(false);
        btn_opc_habilitar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_opc_habilitar.setIconTextGap(1);
        btn_opc_habilitar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_opc_habilitar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_opc_habilitarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_opc_habilitarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_opc_habilitarMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_opc_habilitarMouseReleased(evt);
            }
        });
        btn_opc_habilitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_opc_habilitarActionPerformed(evt);
            }
        });

        btn_opc_inhabilitar.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btn_opc_inhabilitar.setForeground(new java.awt.Color(255, 255, 255));
        btn_opc_inhabilitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icono_deshabilitar.png"))); // NOI18N
        btn_opc_inhabilitar.setText("Inhabilitar");
        btn_opc_inhabilitar.setToolTipText("Inhabilitar el producto");
        btn_opc_inhabilitar.setBorderPainted(false);
        btn_opc_inhabilitar.setContentAreaFilled(false);
        btn_opc_inhabilitar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_opc_inhabilitar.setEnabled(false);
        btn_opc_inhabilitar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_opc_inhabilitar.setIconTextGap(1);
        btn_opc_inhabilitar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_opc_inhabilitar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_opc_inhabilitarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_opc_inhabilitarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_opc_inhabilitarMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_opc_inhabilitarMouseReleased(evt);
            }
        });
        btn_opc_inhabilitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_opc_inhabilitarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Panel_Botones_ProveedoresLayout = new javax.swing.GroupLayout(Panel_Botones_Proveedores);
        Panel_Botones_Proveedores.setLayout(Panel_Botones_ProveedoresLayout);
        Panel_Botones_ProveedoresLayout.setHorizontalGroup(
            Panel_Botones_ProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_Botones_ProveedoresLayout.createSequentialGroup()
                .addComponent(btn_opc_nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btn_opc_modificar)
                .addGap(0, 0, 0)
                .addComponent(btn_opc_habilitar)
                .addGap(0, 0, 0)
                .addComponent(btn_opc_inhabilitar)
                .addGap(25, 25, 25))
        );

        Panel_Botones_ProveedoresLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn_opc_habilitar, btn_opc_inhabilitar, btn_opc_modificar, btn_opc_nuevo});

        Panel_Botones_ProveedoresLayout.setVerticalGroup(
            Panel_Botones_ProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_Botones_ProveedoresLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(Panel_Botones_ProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_opc_modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_opc_nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_opc_habilitar, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_opc_inhabilitar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        Panel_Botones_ProveedoresLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_opc_habilitar, btn_opc_inhabilitar, btn_opc_modificar, btn_opc_nuevo});

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
                .addComponent(Panel_Botones_Proveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_opc_restaurar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(pan_opc_6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pan_opc_6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(Panel_Botones_Proveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        tablaProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "T.Doc", "Documento", "Nombre", "Contacto", "Teléfono", "Correo", "Dirección", "Estadp"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        tablaProveedores.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tablaProveedores.setOpaque(false);
        tablaProveedores.getTableHeader().setReorderingAllowed(false);
        tablaProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProveedoresMouseClicked(evt);
            }
        });
        tablaProveedores.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tablaProveedoresPropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(tablaProveedores);
        if (tablaProveedores.getColumnModel().getColumnCount() > 0) {
            tablaProveedores.getColumnModel().getColumn(0).setMinWidth(50);
            tablaProveedores.getColumnModel().getColumn(0).setPreferredWidth(50);
            tablaProveedores.getColumnModel().getColumn(0).setMaxWidth(50);
            tablaProveedores.getColumnModel().getColumn(1).setMinWidth(100);
            tablaProveedores.getColumnModel().getColumn(1).setMaxWidth(100);
            tablaProveedores.getColumnModel().getColumn(2).setMinWidth(200);
            tablaProveedores.getColumnModel().getColumn(3).setMinWidth(250);
            tablaProveedores.getColumnModel().getColumn(4).setMinWidth(100);
            tablaProveedores.getColumnModel().getColumn(4).setMaxWidth(100);
            tablaProveedores.getColumnModel().getColumn(5).setMinWidth(200);
            tablaProveedores.getColumnModel().getColumn(5).setMaxWidth(250);
            tablaProveedores.getColumnModel().getColumn(6).setMinWidth(250);
            tablaProveedores.getColumnModel().getColumn(7).setMinWidth(60);
            tablaProveedores.getColumnModel().getColumn(7).setMaxWidth(75);
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
        NuevoProveedor nuevo = new NuevoProveedor((Frame) parentWindow, true);
        nuevo.setVisible(true);
        inhabilitarBotones();
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_opc_nuevoActionPerformed

    private void btn_opc_modificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_opc_modificarMouseClicked
  

// TODO add your handling code here:
    }//GEN-LAST:event_btn_opc_modificarMouseClicked

    private void btn_opc_modificarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_opc_modificarMouseEntered
        if (btn_opc_modificar.isEnabled()) {
            btn_opc_modificar.setBackground(new Color(174, 214, 241));
            btn_opc_modificar.setOpaque(true);
        }// TODO add your handling code here:
    }//GEN-LAST:event_btn_opc_modificarMouseEntered

    private void btn_opc_modificarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_opc_modificarMouseExited
        if (btn_opc_modificar.isEnabled()) {
            btn_opc_modificar.setOpaque(false);
        }// TODO add your handling code here:
    }//GEN-LAST:event_btn_opc_modificarMouseExited

    private void btn_opc_modificarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_opc_modificarMouseReleased
        if (btn_opc_modificar.isEnabled()) {
            btn_opc_modificar.setBackground(new Color(174, 214, 241));
        }// TODO add your handling code here:
    }//GEN-LAST:event_btn_opc_modificarMouseReleased

    private void btn_opc_habilitarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_opc_habilitarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_opc_habilitarMouseClicked

    private void btn_opc_habilitarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_opc_habilitarMouseEntered
        if (btn_opc_habilitar.isEnabled()) {
            btn_opc_habilitar.setBackground(new Color(174, 214, 241));
            btn_opc_habilitar.setOpaque(true);
        }// TODO add your handling code here:
    }//GEN-LAST:event_btn_opc_habilitarMouseEntered

    private void btn_opc_habilitarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_opc_habilitarMouseExited
        if (btn_opc_habilitar.isEnabled()) {
            btn_opc_habilitar.setOpaque(false);
        }// TODO add your handling code here:
    }//GEN-LAST:event_btn_opc_habilitarMouseExited

    private void btn_opc_habilitarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_opc_habilitarMouseReleased
        if (btn_opc_habilitar.isEnabled()) {
            btn_opc_habilitar.setBackground(new Color(174, 214, 241));
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_opc_habilitarMouseReleased

    private void btn_opc_habilitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_opc_habilitarActionPerformed
        btn_opc_habilitar.setOpaque(false);
        int opc = JOptionDialog.showConfirmDialog("¿Desea habilitar la categoría '" + tablaProveedores.getValueAt(tablaProveedores.getSelectedRow(), 1).toString() + "'?", "Habilitar Categoría", JOptionDialog.SI_NO_OPTION);
        if (opc == 0) {
            habilitarProveedor();
        }
        inhabilitarBotones();// TODO add your handling code here:
    }//GEN-LAST:event_btn_opc_habilitarActionPerformed

    private void btn_opc_inhabilitarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_opc_inhabilitarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_opc_inhabilitarMouseClicked

    private void btn_opc_inhabilitarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_opc_inhabilitarMouseEntered
        if (btn_opc_inhabilitar.isEnabled()) {
            btn_opc_inhabilitar.setBackground(new Color(174, 214, 241));
            btn_opc_inhabilitar.setOpaque(true);
        }// TODO add your handling code here:
    }//GEN-LAST:event_btn_opc_inhabilitarMouseEntered

    private void btn_opc_inhabilitarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_opc_inhabilitarMouseExited
        if (btn_opc_inhabilitar.isEnabled()) {
            btn_opc_inhabilitar.setOpaque(false);
        }// TODO add your handling code here:
    }//GEN-LAST:event_btn_opc_inhabilitarMouseExited

    private void btn_opc_inhabilitarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_opc_inhabilitarMouseReleased
        if (btn_opc_inhabilitar.isEnabled()) {
            btn_opc_inhabilitar.setBackground(new Color(174, 214, 241));
        } // TODO add your handling code here:
    }//GEN-LAST:event_btn_opc_inhabilitarMouseReleased

    private void btn_opc_inhabilitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_opc_inhabilitarActionPerformed
        btn_opc_inhabilitar.setOpaque(false);
        int opc = JOptionDialog.showConfirmDialog("¿Desea inhabilitar la categoría '" + tablaProveedores.getValueAt(tablaProveedores.getSelectedRow(), 1).toString() + "'?", "Inhabilitar Categoría", JOptionDialog.SI_NO_OPTION);
        if (opc == 0) {
            inhabilitarProveedor();
        }
        inhabilitarBotones();
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_opc_inhabilitarActionPerformed

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
        filtro(txtBuscar.getText());          // TODO add your handling code here:
    }//GEN-LAST:event_btn_opc_restaurarActionPerformed

    private void tablaProveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProveedoresMouseClicked
        validarSeleccionFila();        // TODO add your handling code here:
    }//GEN-LAST:event_tablaProveedoresMouseClicked

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
    filtro(txtBuscar.getText());// TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void tablaProveedoresPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tablaProveedoresPropertyChange
    if(tablaProveedores.getSelectedRow() == -1){
    inhabilitarBotones();
    }// TODO add your handling code here:
    }//GEN-LAST:event_tablaProveedoresPropertyChange

    private void btn_opc_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_opc_modificarActionPerformed
        btn_opc_modificar.setOpaque(false);
        Window parentWindow = SwingUtilities.windowForComponent(this);
        ModificarProveedor nuevo = new ModificarProveedor((Frame) parentWindow, true);
        nuevo.setVisible(true);
        inhabilitarBotones();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_opc_modificarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel_Botones_Proveedores;
    private javax.swing.JButton btn_opc_cerrar;
    private javax.swing.JButton btn_opc_habilitar;
    private javax.swing.JButton btn_opc_inhabilitar;
    private javax.swing.JButton btn_opc_modificar;
    private javax.swing.JButton btn_opc_nuevo;
    private javax.swing.JButton btn_opc_restaurar;
    private org.jdesktop.swingx.auth.JAASLoginService jAASLoginService1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pan_opc_6;
    public static javax.swing.JTable tablaProveedores;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
