/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obed77.views.compras;

import core.controlador.principal.ErroresMap;
import core.controlador.principal.Utilidades;
import core.controlador.session.CompraSession;
import core.logger.LogService;
import core.modelo.to.CompraTo;
import core.modelo.to.DetalleCompraTo;
import core.modelo.to.ProductoTo;
import core.modelo.to.ProveedorTo;
import java.awt.MouseInfo;
import java.awt.Point;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import obed77.Principal;
import obed77.views.dialogosComunes.JOptionDialog;
import obed77.views.producto.AgregarProductoCompra;
import obed77.views.proveedor.BuscarProveedor;



/**
 *
 * @author Saito
 */
public class NuevaCompra extends javax.swing.JDialog {

    int x, y;
    static DefaultTableModel tableModelProdComp;
    public ProveedorTo proveedorSeleccionado;
    AgregarProductoCompra bus;

    public NuevaCompra(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        tableModelProdComp = (DefaultTableModel) tablaProductosCompra.getModel();
        tablaProductosCompra.removeColumn(tablaProductosCompra.getColumn("det"));
        bus = new AgregarProductoCompra(this, true);

        limpiar();

    }

    private void limpiar() {
        tableModelProdComp.setRowCount(0);
        tablaProductosCompra.setModel(tableModelProdComp);
        cboComprobante.setSelectedIndex(0);
        datFecha.setDate(null);
        txtReferencia.setText("");
        txtProveedorNuevaCompra.setText("");
        btnCrear.setEnabled(false);
        proveedorSeleccionado = new ProveedorTo();
    }

    private void validarCrear() {
        if (txtProveedorNuevaCompra.getText().isEmpty() || datFecha.getDate() == null
            || txtReferencia.getText().isEmpty()
            || tablaProductosCompra.getRowCount() <= 0 || proveedorSeleccionado == null) {
            btnCrear.setEnabled(false);
        } else {
            btnCrear.setEnabled(true);
        }
    }

    private void crearCompra() {
        CompraTo to = new CompraTo();
        try {
            to.setProveedor(proveedorSeleccionado);
            to.setFecha(datFecha.getDate());
            to.setDocumento(cboComprobante.getSelectedItem().toString());
            to.setReferencia(txtReferencia.getText());
            ArrayList<DetalleCompraTo> listDet = new ArrayList<>();
            
            for(int i = 0; i<tablaProductosCompra.getRowCount(); i++){
                DetalleCompraTo det = (DetalleCompraTo) tablaProductosCompra.getModel().getValueAt(i, 5);
                listDet.add(det);
            }
            to.setDetalles(listDet);
            to.setUsuario(Principal.getUsuarioPrincipal().getUser());
            CompraSession session = new CompraSession();
            session.insertarCompra(to);
            JOptionDialog.showMessageDialog(this, "Compra creada correctamente", "Compras", JOptionDialog.INFORMACION_ICON);
            PanelCompras.cargar();
            this.dispose();
        } catch (SQLException ex) {
            LogService.logger.error(Principal.getUsuarioPrincipal().getUser(), "ERROR");
            JOptionDialog.showMessageDialog(this, ErroresMap.MessageError(ex.getErrorCode(), to.getProveedor().toString()), "Compras", JOptionDialog.INFORMACION_ICON);
        } catch (Exception ex) {
            LogService.logger.error(Principal.getUsuarioPrincipal().getUser(), "ERROR: " + ex.getMessage());
            JOptionDialog.showMessageDialog(this, ErroresMap.MessageError(9999, ex.getMessage()), "Productos", JOptionDialog.INFORMACION_ICON);
        }
    }

    private void buscarProveedor() {
        BuscarProveedor bus = new BuscarProveedor(this, true);
        bus.setVisible(true);
        proveedorSeleccionado = bus.getProveedor();
        if(proveedorSeleccionado != null){
        txtProveedorNuevaCompra.setText(proveedorSeleccionado.getDocumento()+" "+proveedorSeleccionado.toString());
        }
    }
    
    private void agregarProducto() {
        
        bus.setVisible(true);
        DetalleCompraTo det = bus.getDetalle();
        Object[] fila = new Object[tableModelProdComp.getColumnCount()];
        if(det != null){
            if(!containProduct(det.getProducto())){
                fila[0] = det.getProducto();
                fila[1] = det.getCantidad();
                fila[2] = det.getCosto();
                fila[3] = det.getPrecio();
                fila[4] = det.getSubTotal();
                fila[5] = det;
                tableModelProdComp.addRow(fila);
                LogService.logger.info(Principal.getUsuarioPrincipal().getUser(), "Agregando producto");
                calcularTotal();
                validarCrear();
            }else{
                 JOptionDialog.showMessageDialog(this, "El producto ya está seleccionado", "Compras", JOptionDialog.INFORMACION_ICON);
            }
        }
    }
    
    private void calcularTotal(){
        double total = 0;
        for(int i = 0; i<tablaProductosCompra.getRowCount(); i++){
            double sub = Double.parseDouble(tablaProductosCompra.getValueAt(i, 4).toString());
            total = total+sub;
        }
        txtTotal.setText(total+"");
    }
    
    private boolean containProduct(ProductoTo producto){
        boolean res = false;
        for(int i = 0; i<tablaProductosCompra.getRowCount(); i++){
            ProductoTo prod = (ProductoTo) tablaProductosCompra.getValueAt(i, 0);
            if (prod.equals(producto)) {
                res=true ;
            }
        }
        return res;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Panel = new javax.swing.JPanel();
        panelTop = new javax.swing.JPanel();
        header = new javax.swing.JLabel();
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        btnCrear = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnBuscarProv = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProductosCompra = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnQuitar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        txtProveedorNuevaCompra = new javax.swing.JTextField();
        datFecha = new com.toedter.calendar.JDateChooser();
        cboComprobante = new javax.swing.JComboBox<>();
        txtReferencia = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setUndecorated(true);

        Panel.setBackground(new java.awt.Color(220, 220, 220));
        Panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        panelTop.setBackground(new java.awt.Color(220, 220, 220));
        panelTop.setPreferredSize(new java.awt.Dimension(701, 25));

        header.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        header.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        header.setText("Nueva Compra");
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
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelTopLayout.setVerticalGroup(
            panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTopLayout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoApp.jpg"))); // NOI18N

        btnCrear.setBackground(new java.awt.Color(255, 255, 255));
        btnCrear.setText("Crear");
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

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Proveedor");

        btnBuscarProv.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscarProv.setText("Buscar");
        btnBuscarProv.setBorderPainted(false);
        btnBuscarProv.setContentAreaFilled(false);
        btnBuscarProv.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarProv.setMaximumSize(new java.awt.Dimension(110, 25));
        btnBuscarProv.setMinimumSize(new java.awt.Dimension(110, 25));
        btnBuscarProv.setOpaque(true);
        btnBuscarProv.setPreferredSize(new java.awt.Dimension(110, 25));
        btnBuscarProv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBuscarProvMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBuscarProvMouseExited(evt);
            }
        });
        btnBuscarProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProvActionPerformed(evt);
            }
        });

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Fecha");

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Tipo Comprobante");

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Referencia de Comprobante");

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Productos");

        jScrollPane1.setAutoscrolls(true);

        tablaProductosCompra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Producto", "Cantidad", "Precio Compra", "Precio Venta", "Sub Total", "det"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaProductosCompra.setOpaque(false);
        tablaProductosCompra.getTableHeader().setReorderingAllowed(false);
        tablaProductosCompra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProductosCompraMouseClicked(evt);
            }
        });
        tablaProductosCompra.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tablaProductosCompraPropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(tablaProductosCompra);
        if (tablaProductosCompra.getColumnModel().getColumnCount() > 0) {
            tablaProductosCompra.getColumnModel().getColumn(0).setMinWidth(120);
            tablaProductosCompra.getColumnModel().getColumn(1).setMaxWidth(65);
            tablaProductosCompra.getColumnModel().getColumn(2).setMinWidth(50);
            tablaProductosCompra.getColumnModel().getColumn(3).setMinWidth(50);
            tablaProductosCompra.getColumnModel().getColumn(4).setMinWidth(50);
        }

        btnAgregar.setBackground(new java.awt.Color(255, 255, 255));
        btnAgregar.setText("Agregar");
        btnAgregar.setBorderPainted(false);
        btnAgregar.setContentAreaFilled(false);
        btnAgregar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregar.setMaximumSize(new java.awt.Dimension(110, 25));
        btnAgregar.setMinimumSize(new java.awt.Dimension(110, 25));
        btnAgregar.setOpaque(true);
        btnAgregar.setPreferredSize(new java.awt.Dimension(110, 25));
        btnAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAgregarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAgregarMouseExited(evt);
            }
        });
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnQuitar.setBackground(new java.awt.Color(255, 255, 255));
        btnQuitar.setText("Quitar");
        btnQuitar.setBorderPainted(false);
        btnQuitar.setContentAreaFilled(false);
        btnQuitar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnQuitar.setMaximumSize(new java.awt.Dimension(110, 25));
        btnQuitar.setMinimumSize(new java.awt.Dimension(110, 25));
        btnQuitar.setOpaque(true);
        btnQuitar.setPreferredSize(new java.awt.Dimension(110, 25));
        btnQuitar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnQuitarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnQuitarMouseExited(evt);
            }
        });
        btnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });

        btnLimpiar.setBackground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setBorderPainted(false);
        btnLimpiar.setContentAreaFilled(false);
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiar.setMaximumSize(new java.awt.Dimension(110, 25));
        btnLimpiar.setMinimumSize(new java.awt.Dimension(110, 25));
        btnLimpiar.setOpaque(true);
        btnLimpiar.setPreferredSize(new java.awt.Dimension(110, 25));
        btnLimpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLimpiarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLimpiarMouseExited(evt);
            }
        });
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        txtProveedorNuevaCompra.setFocusable(false);

        datFecha.setOpaque(false);
        datFecha.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                datFechaPropertyChange(evt);
            }
        });

        cboComprobante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BOLETA", "FACTURA", "TICKET" }));
        cboComprobante.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboComprobanteItemStateChanged(evt);
            }
        });

        txtReferencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtReferenciaKeyReleased(evt);
            }
        });

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Total S/.: ");

        txtTotal.setText("0.0");
        txtTotal.setFocusable(false);

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelImage1Layout.createSequentialGroup()
                        .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelImage1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelImage1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(panelImage1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(413, 413, 413)
                                .addComponent(jLabel6)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(panelImage1Layout.createSequentialGroup()
                                .addComponent(cboComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelImage1Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(0, 370, Short.MAX_VALUE))
                                    .addComponent(txtReferencia))))
                        .addContainerGap())
                    .addGroup(panelImage1Layout.createSequentialGroup()
                        .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelImage1Layout.createSequentialGroup()
                                .addComponent(txtProveedorNuevaCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscarProv, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(datFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(panelImage1Layout.createSequentialGroup()
                                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addGroup(panelImage1Layout.createSequentialGroup()
                                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(10, 10, 10))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelImage1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelImage1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCancelar, btnCrear});

        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBuscarProv, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txtProveedorNuevaCompra, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(datFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(7, 7, 7)
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(8, 8, 8)
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(10, 10, 10)
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
            .addComponent(panelTop, javax.swing.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
            .addGroup(PanelLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        PanelLayout.setVerticalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLayout.createSequentialGroup()
                .addComponent(panelTop, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void btnCrearMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCrearMouseEntered
        btnCrear.setBackground(Utilidades.getColorEntered());        // TODO add your handling code here:
    }//GEN-LAST:event_btnCrearMouseEntered

    private void btnCrearMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCrearMouseExited
        btnCrear.setBackground(Utilidades.getColorNormalMenu()); // TODO add your handling code here:
    }//GEN-LAST:event_btnCrearMouseExited

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        crearCompra();           // TODO add your handling code here:
    }//GEN-LAST:event_btnCrearActionPerformed

    private void btnCancelarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseEntered
        btnCancelar.setBackground(Utilidades.getColorEntered());// TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarMouseEntered

    private void btnCancelarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseExited
        btnCancelar.setBackground(Utilidades.getColorNormalMenu()); // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarMouseExited

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();       // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnBuscarProvMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarProvMouseEntered
        btnBuscarProv.setBackground(Utilidades.getColorEntered());        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarProvMouseEntered

    private void btnBuscarProvMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarProvMouseExited
        btnBuscarProv.setBackground(Utilidades.getColorNormalMenu());  // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarProvMouseExited

    private void btnBuscarProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProvActionPerformed
        buscarProveedor();
        validarCrear();        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarProvActionPerformed

    private void tablaProductosCompraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductosCompraMouseClicked
        validarCrear();        // TODO add your handling code here:
    }//GEN-LAST:event_tablaProductosCompraMouseClicked

    private void tablaProductosCompraPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tablaProductosCompraPropertyChange
        if (tablaProductosCompra.getSelectedRow() == -1) {
             validarCrear();
        }// TODO add your handling code here:
    }//GEN-LAST:event_tablaProductosCompraPropertyChange

    private void btnAgregarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarMouseEntered
btnAgregar.setBackground(Utilidades.getColorEntered());        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarMouseEntered

    private void btnAgregarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarMouseExited
btnAgregar.setBackground(Utilidades.getColorNormalMenu());         // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarMouseExited

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
    btnAgregar.setBackground(Utilidades.getColorNormalMenu());   
        agregarProducto();        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnQuitarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQuitarMouseEntered
btnQuitar.setBackground(Utilidades.getColorEntered());        // TODO add your handling code here:
    }//GEN-LAST:event_btnQuitarMouseEntered

    private void btnQuitarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQuitarMouseExited
btnQuitar.setBackground(Utilidades.getColorNormalMenu());         // TODO add your handling code here:
    }//GEN-LAST:event_btnQuitarMouseExited

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
  btnQuitar.setBackground(Utilidades.getColorNormalMenu()); 
  if(tablaProductosCompra.getSelectedRow()!=-1){
      LogService.logger.info(Principal.getUsuarioPrincipal().getUser(), "Quitando Producto");
      tableModelProdComp.removeRow(tablaProductosCompra.getSelectedRow());
      calcularTotal();
  }
  
  // TODO add your handling code here:
    }//GEN-LAST:event_btnQuitarActionPerformed

    private void btnLimpiarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarMouseEntered
btnLimpiar.setBackground(Utilidades.getColorEntered());        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiarMouseEntered

    private void btnLimpiarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarMouseExited
     btnLimpiar.setBackground(Utilidades.getColorNormalMenu());    // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiarMouseExited

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        LogService.logger.info(Principal.getUsuarioPrincipal().getUser(), "Limpiando Tabla"); 
        btnLimpiar.setBackground(Utilidades.getColorNormalMenu()); 
        tableModelProdComp.setRowCount(0);
        calcularTotal();// TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void datFechaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_datFechaPropertyChange
        validarCrear();        // TODO add your handling code here:
    }//GEN-LAST:event_datFechaPropertyChange

    private void cboComprobanteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboComprobanteItemStateChanged
validarCrear();        // TODO add your handling code here:
    }//GEN-LAST:event_cboComprobanteItemStateChanged

    private void txtReferenciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReferenciaKeyReleased
validarCrear();        // TODO add your handling code here:
    }//GEN-LAST:event_txtReferenciaKeyReleased

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
            java.util.logging.Logger.getLogger(NuevaCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NuevaCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NuevaCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NuevaCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                NuevaCompra dialog = new NuevaCompra(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscarProv;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JComboBox<String> cboComprobante;
    private com.toedter.calendar.JDateChooser datFecha;
    private javax.swing.JLabel header;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private javax.swing.JPanel panelTop;
    public static javax.swing.JTable tablaProductosCompra;
    private javax.swing.JTextField txtProveedorNuevaCompra;
    private javax.swing.JTextField txtReferencia;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables

}
