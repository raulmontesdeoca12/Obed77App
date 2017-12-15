/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obed77;

import core.controlador.hilos.HiloCerrando;
import core.controlador.hilos.HiloIniciando;
import core.controlador.principal.Utilidades;
import core.controlador.session.Comunes;
import core.logger.LogService;
import core.modelo.helper.Configuraciones;
import core.modelo.to.UsuarioTo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import obed77.views.cliente.PanelCliente;
import obed77.views.compras.PanelCompras;
import obed77.views.dialogosComunes.JOptionDialog;
import obed77.views.producto.PanelCategoria;
import obed77.views.producto.PanelProducto;
import obed77.views.proveedor.PanelProveedor;
import org.apache.commons.codec.digest.DigestUtils;



/**
 *
 * @author Saito
 */
public class Principal extends javax.swing.JFrame {

    public static UsuarioTo usuarioTo = new UsuarioTo();
    int x, y;

    /**
     * Creates new form Principal
     */
    public Principal() {
        LogService.logger.info(Comunes.USER, "Ejecutando Ventana Principal");
        initComponents();
        setConfig();

    }


    public static UsuarioTo getUsuarioPrincipal() {
        return usuarioTo;
    }

    private void setConfig() {
        this.setTitle("Principal - " + Configuraciones.nombreApp);
        panelBody.add(panelInicial, BorderLayout.SOUTH, 1);
        pVentas.setVisible(false);
        pProductos.setVisible(false);
        pClientes.setVisible(false);
        pCompras.setVisible(false);
        pProveedores.setVisible(false);
        pEmpleados.setVisible(false);
        pConfiguracion.setVisible(false);
        txtUser.grabFocus();
        String pass = DigestUtils.md5Hex("*1234Abcd");
        HiloIniciando hilo = new HiloIniciando("admin", pass);
        hilo.start();

    }

    private void Salir() {
        int opc = JOptionDialog.showConfirmDialog(this, "¿Seguro que desea cerrar la aplicación?", "Salir", JOptionDialog.SI_NO_OPTION);
        if (opc == 0) {
            LogService.logger.info(getUsuarioPrincipal().getUser(), "Aplicación Cerrada");
            System.exit(0);
        }
    }

    private void validarBotoniniciar() {
        String user = txtUser.getText();
        char[] arrayc = txtPass.getPassword();
        String pass = new String(arrayc);
        if (pass.isEmpty() || user.isEmpty() || user.length() < 5 || pass.length() < 6) {
            btnIniciarSesion.setEnabled(false);
        } else {
            btnIniciarSesion.setEnabled(true);

        }

    }

    private void ValidarInicioSesion() {
        LogService.logger.info(Comunes.USER, "Validando Inicio de Sesión");
        String user = txtUser.getText();
        char[] arrayc = txtPass.getPassword();
        String passsine = new String(arrayc);
        String pass = DigestUtils.md5Hex(passsine);
        String input = txtUser.getText();
        Pattern p = Pattern.compile("[^A-Za-z0-9.@_-~#*]+");
        Matcher m = p.matcher(input);
        StringBuffer sb = new StringBuffer();
        boolean resultado = m.find();
        boolean caracteresIlegales = false;

        while (resultado) {
            caracteresIlegales = true;
            m.appendReplacement(sb, "");
            resultado = m.find();
        }
        m.appendTail(sb);

        if (caracteresIlegales) {
            LogService.logger.error(Comunes.USER, "Uso de Caracteres Ilegales");
            JOptionDialog.showMessageDialog(this, "Error: \n   Uso de Caracteres Ilegales.", "Error", JOptionDialog.INFORMACION_ICON);
        } else {
            LogService.logger.info(Comunes.USER, "Iniciando Sesión");
            HiloIniciando hilo = new HiloIniciando(user, pass);
            hilo.start();
        }
    }

    private void doCerrarSesion(String user) {
        HiloCerrando hilo = new HiloCerrando(user);
        hilo.start();
    }




    /**
     * ********************* Metodos para la creación de los paneles
     * *********************
     */

    public void CrearPanelProducto() {
        LogService.logger.info(getUsuarioPrincipal().getUser(), "doCrearPanelProducto");
        String titulo = "   Productos   ";
        int index = multiPanel.indexOfTab(titulo);
        if (index == -1) {
            PanelProducto panel = new PanelProducto();
            multiPanel.addTab(titulo, panel);
            int i = multiPanel.indexOfTab(titulo);
            multiPanel.setSelectedIndex(i);
        } else {
            JOptionDialog.showMessageDialog(this, "Ya existe una pestaña de \"" + titulo + "\" abierta", "Principal", JOptionDialog.INFORMACION_ICON);
        }
    }

    public void CrearPanelCategorias() {
        LogService.logger.info(getUsuarioPrincipal().getUser(), "doCrearPanelCategorias");
        String titulo = "   Categorias   ";
        int index = multiPanel.indexOfTab(titulo);
        if (index == -1) {
            PanelCategoria panel = new PanelCategoria();
            multiPanel.addTab(titulo, panel);
            int i = multiPanel.indexOfTab(titulo);
            multiPanel.setSelectedIndex(i);
        } else {
            JOptionDialog.showMessageDialog(this, "Ya existe una pestaña de \"" + titulo + "\" abierta", "Principal", JOptionDialog.INFORMACION_ICON);
        }
    }

    public void CrearPanelProveedores() {
        LogService.logger.info(getUsuarioPrincipal().getUser(), "doCrearPanelProveedores");
        String titulo = "   Proveedores   ";
        int index = multiPanel.indexOfTab(titulo);
        if (index == -1) {
            PanelProveedor panel = new PanelProveedor();
            multiPanel.addTab(titulo, panel);
            int i = multiPanel.indexOfTab(titulo);
            multiPanel.setSelectedIndex(i);
        } else {
            JOptionDialog.showMessageDialog(this, "Ya existe una pestaña de \"" + titulo + "\" abierta", "Principal", JOptionDialog.INFORMACION_ICON);
        }
    }

    public void CrearPanelCompras() {
        LogService.logger.info(getUsuarioPrincipal().getUser(), "doCrearPanelCompras");
        String titulo = "   Compras   ";
        int index = multiPanel.indexOfTab(titulo);
        if (index == -1) {
            PanelCompras panel = new PanelCompras();
            multiPanel.addTab(titulo, panel);
            int i = multiPanel.indexOfTab(titulo);
            multiPanel.setSelectedIndex(i);
        } else {
            JOptionDialog.showMessageDialog(this, "Ya existe una pestaña de \"" + titulo + "\" abierta", "Principal", JOptionDialog.INFORMACION_ICON);
        }

    }

    public void CrearPanelCliente() {
        LogService.logger.info(getUsuarioPrincipal().getUser(), "doCrearPanelCliente");
        String titulo = "   Clientes   ";
        int index = multiPanel.indexOfTab(titulo);
        if (index == -1) {
            PanelCliente panel = new PanelCliente();
            multiPanel.addTab(titulo, panel);
            int i = multiPanel.indexOfTab(titulo);
            multiPanel.setSelectedIndex(i);
        } else {
            JOptionDialog.showMessageDialog(this, "Ya existe una pestaña de \"" + titulo + "\" abierta", "Principal", JOptionDialog.INFORMACION_ICON);
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

        panelInicial = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txtPass = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        btnIniciarSesion = new javax.swing.JButton();
        btnInicialSalir = new javax.swing.JButton();
        panelIniciado = new javax.swing.JPanel();
        btnGestionarUsuario = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        clockDigital1 = new org.edisoncor.gui.varios.ClockDigital();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lab_5 = new javax.swing.JLabel();
        labNombreEmpleado = new javax.swing.JLabel();
        labCargo = new javax.swing.JLabel();
        lab_6 = new javax.swing.JLabel();
        labUltimaConexion = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnCerrarSesion = new javax.swing.JButton();
        btnIniciadoSalir = new javax.swing.JButton();
        panelPrincipalBotones = new javax.swing.JPanel();
        btnPrincipalProductos = new javax.swing.JButton();
        btnPrincipalProveedores = new javax.swing.JButton();
        btnPrincipalClientes = new javax.swing.JButton();
        btnPrincipalCompras = new javax.swing.JButton();
        btnPrincipalVentas = new javax.swing.JButton();
        grupoMenu = new javax.swing.ButtonGroup();
        menuVentas = new javax.swing.JPopupMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        menuListarVentas = new javax.swing.JMenuItem();
        subMenReportesVentas = new javax.swing.JMenu();
        menuReporte1Ventas = new javax.swing.JMenuItem();
        menuReporte2Ventas = new javax.swing.JMenuItem();
        menuProductos = new javax.swing.JPopupMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        menuListarProductos = new javax.swing.JMenuItem();
        menuCategoriasProducto = new javax.swing.JMenuItem();
        menuClientes = new javax.swing.JPopupMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        menuListarClientes = new javax.swing.JMenuItem();
        menuCompras = new javax.swing.JPopupMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        menuListarCompras = new javax.swing.JMenuItem();
        menuProveedores = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuListarProveedores = new javax.swing.JMenuItem();
        menuEmpleados = new javax.swing.JPopupMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        menuListarEmpleados = new javax.swing.JMenuItem();
        menuAgregarEmpleado = new javax.swing.JMenuItem();
        subMenReportesEmpleados = new javax.swing.JMenu();
        menuReporte1Empleados = new javax.swing.JMenuItem();
        menuReporte2Empleados = new javax.swing.JMenuItem();
        menuConfiguracion = new javax.swing.JPopupMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        menuConfig1 = new javax.swing.JMenuItem();
        menuConfig2 = new javax.swing.JMenuItem();
        menuConfig3 = new javax.swing.JMenuItem();
        Panel = new javax.swing.JPanel();
        panelTop = new javax.swing.JPanel();
        header = new javax.swing.JLabel();
        btnMaximizar = new javax.swing.JButton();
        btnMinimizar = new javax.swing.JButton();
        panelMenu = new javax.swing.JPanel();
        pProductos = new javax.swing.JPanel();
        menProductos = new javax.swing.JButton();
        pProveedores = new javax.swing.JPanel();
        menProveedores = new javax.swing.JButton();
        pClientes = new javax.swing.JPanel();
        menClientes = new javax.swing.JButton();
        pCompras = new javax.swing.JPanel();
        menCompras = new javax.swing.JButton();
        pVentas = new javax.swing.JPanel();
        menVentas = new javax.swing.JButton();
        pEmpleados = new javax.swing.JPanel();
        menEmpleados = new javax.swing.JButton();
        pConfiguracion = new javax.swing.JPanel();
        menConfiguracion = new javax.swing.JButton();
        jSeparator8 = new javax.swing.JSeparator();
        panelContenedorBody = new javax.swing.JPanel();
        panelBody = new org.edisoncor.gui.panel.PanelImage();
        multiPanel = new org.matrix.CustomTabbedPane();

        panelInicial.setMinimumSize(new java.awt.Dimension(800, 60));
        panelInicial.setOpaque(false);

        jPanel5.setMaximumSize(new java.awt.Dimension(32767, 80));
        jPanel5.setMinimumSize(new java.awt.Dimension(100, 80));
        jPanel5.setOpaque(false);
        jPanel5.setPreferredSize(new java.awt.Dimension(752, 80));

        txtPass.setToolTipText("Contraseña de Usuario");
        txtPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPassKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPassKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPassKeyTyped(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Contraseña: ");

        txtUser.setToolTipText("Cuenta de Usuario");
        txtUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUserKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUserKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUserKeyTyped(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Usuario: ");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Por favor Inicie Sesión.");
        jLabel3.setMinimumSize(new java.awt.Dimension(0, 0));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Sesión No Iniciada...");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 23, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jPanel6.setMaximumSize(new java.awt.Dimension(32767, 80));
        jPanel6.setMinimumSize(new java.awt.Dimension(120, 80));
        jPanel6.setOpaque(false);
        jPanel6.setPreferredSize(new java.awt.Dimension(120, 80));

        btnIniciarSesion.setBackground(new java.awt.Color(255, 255, 255));
        btnIniciarSesion.setText("Iniciar Sesión");
        btnIniciarSesion.setToolTipText("Iniciar Sesión");
        btnIniciarSesion.setBorderPainted(false);
        btnIniciarSesion.setContentAreaFilled(false);
        btnIniciarSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIniciarSesion.setEnabled(false);
        btnIniciarSesion.setMaximumSize(new java.awt.Dimension(110, 25));
        btnIniciarSesion.setMinimumSize(new java.awt.Dimension(110, 25));
        btnIniciarSesion.setOpaque(true);
        btnIniciarSesion.setPreferredSize(new java.awt.Dimension(110, 25));
        btnIniciarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnIniciarSesionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnIniciarSesionMouseExited(evt);
            }
        });
        btnIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesionActionPerformed(evt);
            }
        });

        btnInicialSalir.setBackground(new java.awt.Color(255, 255, 255));
        btnInicialSalir.setText("Salir");
        btnInicialSalir.setToolTipText("Salir de la Aplicación");
        btnInicialSalir.setBorderPainted(false);
        btnInicialSalir.setContentAreaFilled(false);
        btnInicialSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInicialSalir.setMaximumSize(new java.awt.Dimension(110, 25));
        btnInicialSalir.setMinimumSize(new java.awt.Dimension(110, 25));
        btnInicialSalir.setOpaque(true);
        btnInicialSalir.setPreferredSize(new java.awt.Dimension(110, 25));
        btnInicialSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnInicialSalirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnInicialSalirMouseExited(evt);
            }
        });
        btnInicialSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicialSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnIniciarSesion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInicialSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnIniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnInicialSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelInicialLayout = new javax.swing.GroupLayout(panelInicial);
        panelInicial.setLayout(panelInicialLayout);
        panelInicialLayout.setHorizontalGroup(
            panelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInicialLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        panelInicialLayout.setVerticalGroup(
            panelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInicialLayout.createSequentialGroup()
                .addGroup(panelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        panelIniciado.setFocusCycleRoot(true);
        panelIniciado.setMinimumSize(new java.awt.Dimension(800, 60));
        panelIniciado.setOpaque(false);
        panelIniciado.setPreferredSize(new java.awt.Dimension(857, 80));

        btnGestionarUsuario.setToolTipText("Gestionar Usuarios");
        btnGestionarUsuario.setBorder(null);
        btnGestionarUsuario.setBorderPainted(false);
        btnGestionarUsuario.setContentAreaFilled(false);
        btnGestionarUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGestionarUsuario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGestionarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGestionarUsuarioActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setMaximumSize(new java.awt.Dimension(32767, 80));
        jPanel1.setMinimumSize(new java.awt.Dimension(100, 80));
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(784, 80));

        clockDigital1.setForeground(new java.awt.Color(255, 255, 255));

        jPanel2.setMaximumSize(new java.awt.Dimension(60, 60));
        jPanel2.setMinimumSize(new java.awt.Dimension(60, 60));
        jPanel2.setPreferredSize(new java.awt.Dimension(60, 60));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(0, 102, 204));
        jPanel3.setMaximumSize(new java.awt.Dimension(60, 60));
        jPanel3.setMinimumSize(new java.awt.Dimension(60, 60));
        jPanel3.setOpaque(false);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Nombre :");

        lab_5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lab_5.setForeground(new java.awt.Color(255, 255, 255));
        lab_5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lab_5.setText("Cargo:");

        labNombreEmpleado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labNombreEmpleado.setForeground(new java.awt.Color(255, 255, 255));

        labCargo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labCargo.setForeground(new java.awt.Color(255, 255, 255));

        lab_6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lab_6.setForeground(new java.awt.Color(255, 255, 255));
        lab_6.setText("Última Conexión");

        labUltimaConexion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labUltimaConexion.setForeground(new java.awt.Color(255, 255, 255));
        labUltimaConexion.setPreferredSize(new java.awt.Dimension(165, 57));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lab_5)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labNombreEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lab_6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(labUltimaConexion, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {labCargo, labNombreEmpleado});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel7, lab_5});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lab_6)
                        .addGap(6, 6, 6)
                        .addComponent(labUltimaConexion, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(labNombreEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lab_5, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, 0))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {labCargo, labNombreEmpleado});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel7, lab_5});

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(clockDigital1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(clockDigital1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setMaximumSize(new java.awt.Dimension(130, 80));
        jPanel4.setMinimumSize(new java.awt.Dimension(130, 80));
        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new java.awt.Dimension(130, 80));

        btnCerrarSesion.setBackground(new java.awt.Color(255, 255, 255));
        btnCerrarSesion.setText("Cerrar Sesión");
        btnCerrarSesion.setBorderPainted(false);
        btnCerrarSesion.setContentAreaFilled(false);
        btnCerrarSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCerrarSesion.setMaximumSize(new java.awt.Dimension(110, 25));
        btnCerrarSesion.setMinimumSize(new java.awt.Dimension(110, 25));
        btnCerrarSesion.setOpaque(true);
        btnCerrarSesion.setPreferredSize(new java.awt.Dimension(110, 25));
        btnCerrarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCerrarSesionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCerrarSesionMouseExited(evt);
            }
        });
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        btnIniciadoSalir.setBackground(new java.awt.Color(255, 255, 255));
        btnIniciadoSalir.setText("Salir");
        btnIniciadoSalir.setBorderPainted(false);
        btnIniciadoSalir.setContentAreaFilled(false);
        btnIniciadoSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIniciadoSalir.setMaximumSize(new java.awt.Dimension(110, 25));
        btnIniciadoSalir.setMinimumSize(new java.awt.Dimension(110, 25));
        btnIniciadoSalir.setOpaque(true);
        btnIniciadoSalir.setPreferredSize(new java.awt.Dimension(110, 25));
        btnIniciadoSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnIniciadoSalirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnIniciadoSalirMouseExited(evt);
            }
        });
        btnIniciadoSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciadoSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIniciadoSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCerrarSesion, btnIniciadoSalir});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnIniciadoSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCerrarSesion, btnIniciadoSalir});

        javax.swing.GroupLayout panelIniciadoLayout = new javax.swing.GroupLayout(panelIniciado);
        panelIniciado.setLayout(panelIniciadoLayout);
        panelIniciadoLayout.setHorizontalGroup(
            panelIniciadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelIniciadoLayout.createSequentialGroup()
                .addGap(206, 206, 206)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGestionarUsuario)
                .addGap(110, 110, 110))
            .addGroup(panelIniciadoLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 711, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        panelIniciadoLayout.setVerticalGroup(
            panelIniciadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelIniciadoLayout.createSequentialGroup()
                .addGroup(panelIniciadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelIniciadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGestionarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelPrincipalBotones.setBackground(new java.awt.Color(229, 232, 232));
        panelPrincipalBotones.setMinimumSize(new java.awt.Dimension(900, 50));
        panelPrincipalBotones.setOpaque(false);

        btnPrincipalProductos.setBackground(new java.awt.Color(174, 214, 241));
        btnPrincipalProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icono_producto_40.png"))); // NOI18N
        btnPrincipalProductos.setText("Productos");
        btnPrincipalProductos.setBorderPainted(false);
        btnPrincipalProductos.setContentAreaFilled(false);
        btnPrincipalProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrincipalProductos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPrincipalProductos.setIconTextGap(1);
        btnPrincipalProductos.setMaximumSize(new java.awt.Dimension(110, 75));
        btnPrincipalProductos.setMinimumSize(new java.awt.Dimension(110, 75));
        btnPrincipalProductos.setPreferredSize(new java.awt.Dimension(110, 75));
        btnPrincipalProductos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPrincipalProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPrincipalProductosMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnPrincipalProductosMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnPrincipalProductosMouseReleased(evt);
            }
        });
        btnPrincipalProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrincipalProductosActionPerformed(evt);
            }
        });

        btnPrincipalProveedores.setBackground(new java.awt.Color(174, 214, 241));
        btnPrincipalProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icono_proveedores_40.png"))); // NOI18N
        btnPrincipalProveedores.setText("Proveedores");
        btnPrincipalProveedores.setBorderPainted(false);
        btnPrincipalProveedores.setContentAreaFilled(false);
        btnPrincipalProveedores.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrincipalProveedores.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPrincipalProveedores.setIconTextGap(1);
        btnPrincipalProveedores.setMaximumSize(new java.awt.Dimension(110, 75));
        btnPrincipalProveedores.setMinimumSize(new java.awt.Dimension(110, 75));
        btnPrincipalProveedores.setPreferredSize(new java.awt.Dimension(110, 75));
        btnPrincipalProveedores.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPrincipalProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPrincipalProveedoresMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnPrincipalProveedoresMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnPrincipalProveedoresMouseReleased(evt);
            }
        });
        btnPrincipalProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrincipalProveedoresActionPerformed(evt);
            }
        });

        btnPrincipalClientes.setBackground(new java.awt.Color(174, 214, 241));
        btnPrincipalClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icono_clientes.png"))); // NOI18N
        btnPrincipalClientes.setText("Clientes");
        btnPrincipalClientes.setBorderPainted(false);
        btnPrincipalClientes.setContentAreaFilled(false);
        btnPrincipalClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrincipalClientes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPrincipalClientes.setIconTextGap(1);
        btnPrincipalClientes.setMaximumSize(new java.awt.Dimension(110, 75));
        btnPrincipalClientes.setMinimumSize(new java.awt.Dimension(110, 75));
        btnPrincipalClientes.setPreferredSize(new java.awt.Dimension(110, 75));
        btnPrincipalClientes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPrincipalClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPrincipalClientesMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnPrincipalClientesMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnPrincipalClientesMouseReleased(evt);
            }
        });
        btnPrincipalClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrincipalClientesActionPerformed(evt);
            }
        });

        btnPrincipalCompras.setBackground(new java.awt.Color(174, 214, 241));
        btnPrincipalCompras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icono_compras.png"))); // NOI18N
        btnPrincipalCompras.setText("Compras");
        btnPrincipalCompras.setBorderPainted(false);
        btnPrincipalCompras.setContentAreaFilled(false);
        btnPrincipalCompras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrincipalCompras.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPrincipalCompras.setIconTextGap(1);
        btnPrincipalCompras.setMaximumSize(new java.awt.Dimension(110, 75));
        btnPrincipalCompras.setMinimumSize(new java.awt.Dimension(110, 75));
        btnPrincipalCompras.setPreferredSize(new java.awt.Dimension(110, 75));
        btnPrincipalCompras.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPrincipalCompras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPrincipalComprasMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnPrincipalComprasMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnPrincipalComprasMouseReleased(evt);
            }
        });
        btnPrincipalCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrincipalComprasActionPerformed(evt);
            }
        });

        btnPrincipalVentas.setBackground(new java.awt.Color(174, 214, 241));
        btnPrincipalVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icono_ventas.png"))); // NOI18N
        btnPrincipalVentas.setText("Ventas");
        btnPrincipalVentas.setBorderPainted(false);
        btnPrincipalVentas.setContentAreaFilled(false);
        btnPrincipalVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrincipalVentas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPrincipalVentas.setIconTextGap(1);
        btnPrincipalVentas.setMaximumSize(new java.awt.Dimension(110, 75));
        btnPrincipalVentas.setMinimumSize(new java.awt.Dimension(110, 75));
        btnPrincipalVentas.setPreferredSize(new java.awt.Dimension(110, 75));
        btnPrincipalVentas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPrincipalVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPrincipalVentasMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnPrincipalVentasMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnPrincipalVentasMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout panelPrincipalBotonesLayout = new javax.swing.GroupLayout(panelPrincipalBotones);
        panelPrincipalBotones.setLayout(panelPrincipalBotonesLayout);
        panelPrincipalBotonesLayout.setHorizontalGroup(
            panelPrincipalBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalBotonesLayout.createSequentialGroup()
                .addComponent(btnPrincipalVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnPrincipalProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnPrincipalClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnPrincipalCompras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnPrincipalProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(277, 277, 277))
        );
        panelPrincipalBotonesLayout.setVerticalGroup(
            panelPrincipalBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalBotonesLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panelPrincipalBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnPrincipalVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrincipalCompras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrincipalClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrincipalProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrincipalProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        jMenuItem3.setText("Ventas");
        jMenuItem3.setEnabled(false);
        jMenuItem3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jMenuItem3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jMenuItem3.setIconTextGap(0);
        jMenuItem3.setMaximumSize(new java.awt.Dimension(100, 25));
        jMenuItem3.setMinimumSize(new java.awt.Dimension(100, 25));
        jMenuItem3.setPreferredSize(new java.awt.Dimension(100, 25));
        menuVentas.add(jMenuItem3);
        menuVentas.add(jSeparator3);

        menuListarVentas.setText("Gestionar");
        menuListarVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuVentas.add(menuListarVentas);

        subMenReportesVentas.setText("Reportes");
        subMenReportesVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        subMenReportesVentas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        subMenReportesVentas.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        subMenReportesVentas.setMaximumSize(null);
        subMenReportesVentas.setMinimumSize(new java.awt.Dimension(110, 25));
        subMenReportesVentas.setPreferredSize(new java.awt.Dimension(110, 25));

        menuReporte1Ventas.setText("Por Mes");
        menuReporte1Ventas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuReporte1Ventas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        menuReporte1Ventas.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        menuReporte1Ventas.setMaximumSize(null);
        menuReporte1Ventas.setMinimumSize(new java.awt.Dimension(110, 25));
        menuReporte1Ventas.setPreferredSize(new java.awt.Dimension(110, 25));
        subMenReportesVentas.add(menuReporte1Ventas);

        menuReporte2Ventas.setText("Por Semana");
        menuReporte2Ventas.setToolTipText("");
        menuReporte2Ventas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuReporte2Ventas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        menuReporte2Ventas.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        menuReporte2Ventas.setMaximumSize(null);
        menuReporte2Ventas.setMinimumSize(new java.awt.Dimension(110, 25));
        menuReporte2Ventas.setPreferredSize(new java.awt.Dimension(110, 25));
        subMenReportesVentas.add(menuReporte2Ventas);

        menuVentas.add(subMenReportesVentas);

        jMenuItem4.setText("Productos");
        jMenuItem4.setEnabled(false);
        jMenuItem4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jMenuItem4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jMenuItem4.setIconTextGap(0);
        jMenuItem4.setMaximumSize(new java.awt.Dimension(100, 25));
        jMenuItem4.setMinimumSize(new java.awt.Dimension(100, 25));
        jMenuItem4.setPreferredSize(new java.awt.Dimension(100, 25));
        menuProductos.add(jMenuItem4);
        menuProductos.add(jSeparator4);

        menuListarProductos.setText("Gestionar");
        menuListarProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuListarProductos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        menuListarProductos.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        menuListarProductos.setMaximumSize(null);
        menuListarProductos.setMinimumSize(new java.awt.Dimension(110, 25));
        menuListarProductos.setPreferredSize(new java.awt.Dimension(110, 25));
        menuListarProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuListarProductosActionPerformed(evt);
            }
        });
        menuProductos.add(menuListarProductos);

        menuCategoriasProducto.setText("Categorias");
        menuCategoriasProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuCategoriasProducto.setMinimumSize(new java.awt.Dimension(110, 25));
        menuCategoriasProducto.setPreferredSize(new java.awt.Dimension(110, 25));
        menuCategoriasProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCategoriasProductoActionPerformed(evt);
            }
        });
        menuProductos.add(menuCategoriasProducto);

        jMenuItem5.setText("Clientes");
        jMenuItem5.setEnabled(false);
        jMenuItem5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jMenuItem5.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jMenuItem5.setIconTextGap(0);
        jMenuItem5.setMaximumSize(new java.awt.Dimension(100, 25));
        jMenuItem5.setMinimumSize(new java.awt.Dimension(100, 25));
        jMenuItem5.setPreferredSize(new java.awt.Dimension(100, 25));
        menuClientes.add(jMenuItem5);
        menuClientes.add(jSeparator5);

        menuListarClientes.setText("Gestionar");
        menuListarClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuListarClientes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        menuListarClientes.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        menuListarClientes.setMaximumSize(null);
        menuListarClientes.setMinimumSize(new java.awt.Dimension(110, 25));
        menuListarClientes.setPreferredSize(new java.awt.Dimension(110, 25));
        menuListarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuListarClientesActionPerformed(evt);
            }
        });
        menuClientes.add(menuListarClientes);

        jMenuItem6.setText("Compras");
        jMenuItem6.setEnabled(false);
        jMenuItem6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jMenuItem6.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jMenuItem6.setIconTextGap(0);
        jMenuItem6.setMaximumSize(new java.awt.Dimension(100, 25));
        jMenuItem6.setMinimumSize(new java.awt.Dimension(100, 25));
        jMenuItem6.setPreferredSize(new java.awt.Dimension(100, 25));
        menuCompras.add(jMenuItem6);
        menuCompras.add(jSeparator6);

        menuListarCompras.setText("Gestionar");
        menuListarCompras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuListarCompras.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        menuListarCompras.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        menuListarCompras.setMaximumSize(null);
        menuListarCompras.setMinimumSize(new java.awt.Dimension(110, 25));
        menuListarCompras.setPreferredSize(new java.awt.Dimension(110, 25));
        menuListarCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuListarComprasActionPerformed(evt);
            }
        });
        menuCompras.add(menuListarCompras);

        jMenuItem1.setText("Proveedores");
        jMenuItem1.setEnabled(false);
        jMenuItem1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jMenuItem1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jMenuItem1.setIconTextGap(0);
        jMenuItem1.setMaximumSize(new java.awt.Dimension(100, 25));
        jMenuItem1.setMinimumSize(new java.awt.Dimension(100, 25));
        jMenuItem1.setPreferredSize(new java.awt.Dimension(100, 25));
        menuProveedores.add(jMenuItem1);
        menuProveedores.add(jSeparator1);

        menuListarProveedores.setText("Gestionar");
        menuListarProveedores.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuListarProveedores.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        menuListarProveedores.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        menuListarProveedores.setMaximumSize(null);
        menuListarProveedores.setMinimumSize(new java.awt.Dimension(110, 25));
        menuListarProveedores.setPreferredSize(new java.awt.Dimension(110, 25));
        menuListarProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuListarProveedoresActionPerformed(evt);
            }
        });
        menuProveedores.add(menuListarProveedores);

        jMenuItem2.setText("Empleados");
        jMenuItem2.setEnabled(false);
        jMenuItem2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jMenuItem2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jMenuItem2.setIconTextGap(0);
        jMenuItem2.setMaximumSize(new java.awt.Dimension(100, 25));
        jMenuItem2.setMinimumSize(new java.awt.Dimension(100, 25));
        jMenuItem2.setPreferredSize(new java.awt.Dimension(100, 25));
        menuEmpleados.add(jMenuItem2);
        menuEmpleados.add(jSeparator2);

        menuListarEmpleados.setText("Gestionar");
        menuListarEmpleados.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuListarEmpleados.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        menuListarEmpleados.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        menuListarEmpleados.setMaximumSize(null);
        menuListarEmpleados.setMinimumSize(new java.awt.Dimension(110, 25));
        menuListarEmpleados.setPreferredSize(new java.awt.Dimension(110, 25));
        menuEmpleados.add(menuListarEmpleados);

        menuAgregarEmpleado.setText("Registrar");
        menuAgregarEmpleado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuAgregarEmpleado.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        menuAgregarEmpleado.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        menuAgregarEmpleado.setMaximumSize(null);
        menuAgregarEmpleado.setMinimumSize(new java.awt.Dimension(110, 25));
        menuAgregarEmpleado.setPreferredSize(new java.awt.Dimension(110, 25));
        menuEmpleados.add(menuAgregarEmpleado);

        subMenReportesEmpleados.setText("Reportes");
        subMenReportesEmpleados.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        subMenReportesEmpleados.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        subMenReportesEmpleados.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        subMenReportesEmpleados.setMaximumSize(null);
        subMenReportesEmpleados.setMinimumSize(new java.awt.Dimension(110, 25));
        subMenReportesEmpleados.setPreferredSize(new java.awt.Dimension(110, 25));

        menuReporte1Empleados.setText("Reporte 1");
        menuReporte1Empleados.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuReporte1Empleados.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        menuReporte1Empleados.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        menuReporte1Empleados.setMaximumSize(null);
        menuReporte1Empleados.setMinimumSize(new java.awt.Dimension(110, 25));
        menuReporte1Empleados.setPreferredSize(new java.awt.Dimension(110, 25));
        subMenReportesEmpleados.add(menuReporte1Empleados);

        menuReporte2Empleados.setText("Reporte 2");
        menuReporte2Empleados.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuReporte2Empleados.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        menuReporte2Empleados.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        menuReporte2Empleados.setMaximumSize(null);
        menuReporte2Empleados.setMinimumSize(new java.awt.Dimension(110, 25));
        menuReporte2Empleados.setPreferredSize(new java.awt.Dimension(110, 25));
        subMenReportesEmpleados.add(menuReporte2Empleados);

        menuEmpleados.add(subMenReportesEmpleados);

        jMenuItem7.setText("Configuración");
        jMenuItem7.setEnabled(false);
        jMenuItem7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jMenuItem7.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jMenuItem7.setIconTextGap(0);
        jMenuItem7.setMaximumSize(new java.awt.Dimension(100, 25));
        jMenuItem7.setMinimumSize(new java.awt.Dimension(100, 25));
        jMenuItem7.setPreferredSize(new java.awt.Dimension(100, 25));
        menuConfiguracion.add(jMenuItem7);
        menuConfiguracion.add(jSeparator7);

        menuConfig1.setText("Config1");
        menuConfig1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuConfig1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        menuConfig1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        menuConfig1.setMaximumSize(null);
        menuConfig1.setMinimumSize(new java.awt.Dimension(110, 25));
        menuConfig1.setPreferredSize(new java.awt.Dimension(110, 25));
        menuConfiguracion.add(menuConfig1);

        menuConfig2.setText("Config2");
        menuConfig2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuConfig2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        menuConfig2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        menuConfig2.setMaximumSize(null);
        menuConfig2.setMinimumSize(new java.awt.Dimension(110, 25));
        menuConfig2.setPreferredSize(new java.awt.Dimension(110, 25));
        menuConfiguracion.add(menuConfig2);

        menuConfig3.setText("Config3");
        menuConfig3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuConfig3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        menuConfig3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        menuConfig3.setMaximumSize(null);
        menuConfig3.setMinimumSize(new java.awt.Dimension(110, 25));
        menuConfig3.setPreferredSize(new java.awt.Dimension(110, 25));
        menuConfiguracion.add(menuConfig3);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 700));
        setUndecorated(true);

        Panel.setBackground(new java.awt.Color(220, 220, 220));
        Panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        panelTop.setBackground(new java.awt.Color(220, 220, 220));
        panelTop.setPreferredSize(new java.awt.Dimension(701, 25));

        header.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        header.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        header.setText("Principal");
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

        btnMaximizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icono_maximizar.png"))); // NOI18N
        btnMaximizar.setToolTipText("Maximizar");
        btnMaximizar.setBorder(null);
        btnMaximizar.setContentAreaFilled(false);
        btnMaximizar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icono_maximizar2.png"))); // NOI18N
        btnMaximizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMaximizarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMaximizarMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnMaximizarMouseReleased(evt);
            }
        });
        btnMaximizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaximizarActionPerformed(evt);
            }
        });

        btnMinimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icono_minimizar.png"))); // NOI18N
        btnMinimizar.setContentAreaFilled(false);
        btnMinimizar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnMinimizar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icono_minimizar2.png"))); // NOI18N
        btnMinimizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMinimizarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMinimizarMouseExited(evt);
            }
        });
        btnMinimizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinimizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTopLayout = new javax.swing.GroupLayout(panelTop);
        panelTop.setLayout(panelTopLayout);
        panelTopLayout.setHorizontalGroup(
            panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTopLayout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMinimizar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMaximizar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        panelTopLayout.setVerticalGroup(
            panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTopLayout.createSequentialGroup()
                .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnMaximizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnMinimizar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panelMenu.setBackground(new java.awt.Color(255, 255, 255));

        pProductos.setBackground(new java.awt.Color(255, 255, 255));
        pProductos.setMaximumSize(new java.awt.Dimension(110, 28));
        pProductos.setMinimumSize(new java.awt.Dimension(110, 28));
        pProductos.setPreferredSize(new java.awt.Dimension(110, 28));

        menProductos.setBackground(new java.awt.Color(255, 255, 255));
        menProductos.setText("Productos");
        menProductos.setBorder(null);
        menProductos.setBorderPainted(false);
        menProductos.setContentAreaFilled(false);
        menProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menProductos.setFocusable(false);
        menProductos.setMaximumSize(new java.awt.Dimension(110, 28));
        menProductos.setMinimumSize(new java.awt.Dimension(110, 28));
        menProductos.setOpaque(true);
        menProductos.setPreferredSize(new java.awt.Dimension(110, 28));
        menProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menProductosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menProductosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menProductosMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pProductosLayout = new javax.swing.GroupLayout(pProductos);
        pProductos.setLayout(pProductosLayout);
        pProductosLayout.setHorizontalGroup(
            pProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pProductosLayout.setVerticalGroup(
            pProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pProveedores.setBackground(new java.awt.Color(255, 255, 255));
        pProveedores.setMaximumSize(new java.awt.Dimension(110, 28));
        pProveedores.setMinimumSize(new java.awt.Dimension(110, 28));
        pProveedores.setPreferredSize(new java.awt.Dimension(110, 28));

        menProveedores.setBackground(new java.awt.Color(255, 255, 255));
        menProveedores.setText("Proveedores");
        menProveedores.setBorder(null);
        menProveedores.setBorderPainted(false);
        menProveedores.setContentAreaFilled(false);
        menProveedores.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menProveedores.setFocusable(false);
        menProveedores.setMaximumSize(new java.awt.Dimension(110, 28));
        menProveedores.setMinimumSize(new java.awt.Dimension(110, 28));
        menProveedores.setOpaque(true);
        menProveedores.setPreferredSize(new java.awt.Dimension(110, 28));
        menProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menProveedoresMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menProveedoresMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menProveedoresMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pProveedoresLayout = new javax.swing.GroupLayout(pProveedores);
        pProveedores.setLayout(pProveedoresLayout);
        pProveedoresLayout.setHorizontalGroup(
            pProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menProveedores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pProveedoresLayout.setVerticalGroup(
            pProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menProveedores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pClientes.setBackground(new java.awt.Color(255, 255, 255));
        pClientes.setMaximumSize(new java.awt.Dimension(110, 28));
        pClientes.setMinimumSize(new java.awt.Dimension(110, 28));
        pClientes.setPreferredSize(new java.awt.Dimension(110, 28));

        menClientes.setBackground(new java.awt.Color(255, 255, 255));
        menClientes.setText("Clientes");
        menClientes.setBorder(null);
        menClientes.setBorderPainted(false);
        menClientes.setContentAreaFilled(false);
        menClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menClientes.setFocusable(false);
        menClientes.setMaximumSize(new java.awt.Dimension(110, 28));
        menClientes.setMinimumSize(new java.awt.Dimension(110, 28));
        menClientes.setOpaque(true);
        menClientes.setPreferredSize(new java.awt.Dimension(110, 28));
        menClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menClientesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menClientesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menClientesMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pClientesLayout = new javax.swing.GroupLayout(pClientes);
        pClientes.setLayout(pClientesLayout);
        pClientesLayout.setHorizontalGroup(
            pClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pClientesLayout.setVerticalGroup(
            pClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pCompras.setBackground(new java.awt.Color(255, 255, 255));
        pCompras.setMaximumSize(new java.awt.Dimension(110, 28));
        pCompras.setMinimumSize(new java.awt.Dimension(110, 28));
        pCompras.setPreferredSize(new java.awt.Dimension(110, 28));

        menCompras.setBackground(new java.awt.Color(255, 255, 255));
        menCompras.setText("Compras");
        menCompras.setBorder(null);
        menCompras.setBorderPainted(false);
        menCompras.setContentAreaFilled(false);
        menCompras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menCompras.setFocusable(false);
        menCompras.setMaximumSize(new java.awt.Dimension(110, 28));
        menCompras.setMinimumSize(new java.awt.Dimension(110, 28));
        menCompras.setOpaque(true);
        menCompras.setPreferredSize(new java.awt.Dimension(110, 28));
        menCompras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menComprasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menComprasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menComprasMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pComprasLayout = new javax.swing.GroupLayout(pCompras);
        pCompras.setLayout(pComprasLayout);
        pComprasLayout.setHorizontalGroup(
            pComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menCompras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pComprasLayout.setVerticalGroup(
            pComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menCompras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pVentas.setBackground(new java.awt.Color(255, 255, 255));
        pVentas.setMaximumSize(new java.awt.Dimension(110, 28));
        pVentas.setMinimumSize(new java.awt.Dimension(110, 28));
        pVentas.setPreferredSize(new java.awt.Dimension(110, 28));

        menVentas.setBackground(new java.awt.Color(255, 255, 255));
        menVentas.setText("Ventas");
        menVentas.setBorder(null);
        menVentas.setBorderPainted(false);
        menVentas.setContentAreaFilled(false);
        menVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menVentas.setFocusable(false);
        menVentas.setMaximumSize(new java.awt.Dimension(110, 28));
        menVentas.setMinimumSize(new java.awt.Dimension(110, 28));
        menVentas.setOpaque(true);
        menVentas.setPreferredSize(new java.awt.Dimension(110, 28));
        menVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menVentasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menVentasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menVentasMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pVentasLayout = new javax.swing.GroupLayout(pVentas);
        pVentas.setLayout(pVentasLayout);
        pVentasLayout.setHorizontalGroup(
            pVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pVentasLayout.setVerticalGroup(
            pVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pEmpleados.setBackground(new java.awt.Color(255, 255, 255));
        pEmpleados.setMaximumSize(new java.awt.Dimension(110, 28));
        pEmpleados.setMinimumSize(new java.awt.Dimension(110, 28));
        pEmpleados.setPreferredSize(new java.awt.Dimension(110, 28));

        menEmpleados.setBackground(new java.awt.Color(255, 255, 255));
        menEmpleados.setText("Empleados");
        menEmpleados.setBorder(null);
        menEmpleados.setBorderPainted(false);
        menEmpleados.setContentAreaFilled(false);
        menEmpleados.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menEmpleados.setFocusable(false);
        menEmpleados.setMaximumSize(new java.awt.Dimension(110, 28));
        menEmpleados.setMinimumSize(new java.awt.Dimension(110, 28));
        menEmpleados.setOpaque(true);
        menEmpleados.setPreferredSize(new java.awt.Dimension(110, 28));
        menEmpleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menEmpleadosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menEmpleadosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menEmpleadosMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pEmpleadosLayout = new javax.swing.GroupLayout(pEmpleados);
        pEmpleados.setLayout(pEmpleadosLayout);
        pEmpleadosLayout.setHorizontalGroup(
            pEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menEmpleados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pEmpleadosLayout.setVerticalGroup(
            pEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menEmpleados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pConfiguracion.setBackground(new java.awt.Color(255, 255, 255));
        pConfiguracion.setMaximumSize(new java.awt.Dimension(110, 28));
        pConfiguracion.setMinimumSize(new java.awt.Dimension(110, 28));
        pConfiguracion.setPreferredSize(new java.awt.Dimension(110, 28));

        menConfiguracion.setBackground(new java.awt.Color(255, 255, 255));
        menConfiguracion.setText("Configuración");
        menConfiguracion.setBorder(null);
        menConfiguracion.setBorderPainted(false);
        menConfiguracion.setContentAreaFilled(false);
        menConfiguracion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menConfiguracion.setFocusable(false);
        menConfiguracion.setMaximumSize(new java.awt.Dimension(110, 28));
        menConfiguracion.setMinimumSize(new java.awt.Dimension(110, 28));
        menConfiguracion.setOpaque(true);
        menConfiguracion.setPreferredSize(new java.awt.Dimension(110, 28));
        menConfiguracion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menConfiguracionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menConfiguracionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menConfiguracionMouseExited(evt);
            }
        });

        jSeparator8.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout pConfiguracionLayout = new javax.swing.GroupLayout(pConfiguracion);
        pConfiguracion.setLayout(pConfiguracionLayout);
        pConfiguracionLayout.setHorizontalGroup(
            pConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pConfiguracionLayout.createSequentialGroup()
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(menConfiguracion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pConfiguracionLayout.setVerticalGroup(
            pConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menConfiguracion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator8)
        );

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addComponent(pVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pCompras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pConfiguracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(299, Short.MAX_VALUE))
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pCompras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pConfiguracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        panelBody.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoApp.jpg"))); // NOI18N
        panelBody.setLayout(new java.awt.BorderLayout());

        multiPanel.setTabUnselectedColor(new java.awt.Color(245, 245, 245));
        panelBody.add(multiPanel, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout panelContenedorBodyLayout = new javax.swing.GroupLayout(panelContenedorBody);
        panelContenedorBody.setLayout(panelContenedorBodyLayout);
        panelContenedorBodyLayout.setHorizontalGroup(
            panelContenedorBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(panelContenedorBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelBody, javax.swing.GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE))
        );
        panelContenedorBodyLayout.setVerticalGroup(
            panelContenedorBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 507, Short.MAX_VALUE)
            .addGroup(panelContenedorBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelBody, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PanelLayout = new javax.swing.GroupLayout(Panel);
        Panel.setLayout(PanelLayout);
        PanelLayout.setHorizontalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelContenedorBody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelTop, javax.swing.GroupLayout.DEFAULT_SIZE, 1069, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );
        PanelLayout.setVerticalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLayout.createSequentialGroup()
                .addComponent(panelTop, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelContenedorBody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnMinimizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizarMouseEntered
        Border borde = BorderFactory.createLineBorder(new Color(86, 86, 86));
        btnMinimizar.setBorder(borde);

        // TODO add your handling code here:
    }//GEN-LAST:event_btnMinimizarMouseEntered

    private void btnMinimizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizarMouseExited
        Border borde = BorderFactory.createEmptyBorder();
        btnMinimizar.setBorder(borde);

        // TODO add your handling code here:
    }//GEN-LAST:event_btnMinimizarMouseExited

    private void btnMinimizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinimizarActionPerformed
        setExtendedState(ICONIFIED);         // TODO add your handling code here:
    }//GEN-LAST:event_btnMinimizarActionPerformed

    private void btnMaximizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMaximizarMouseEntered
        Border borde = BorderFactory.createLineBorder(new Color(86, 86, 86));
        btnMaximizar.setBorder(borde);            // TODO add your handling code here:
    }//GEN-LAST:event_btnMaximizarMouseEntered

    private void btnMaximizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMaximizarMouseExited
        Border borde = BorderFactory.createEmptyBorder();
        btnMaximizar.setBorder(borde);        // TODO add your handling code here:
    }//GEN-LAST:event_btnMaximizarMouseExited

    private void btnMaximizarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMaximizarMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMaximizarMouseReleased

    private void btnMaximizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaximizarActionPerformed
        String estado = btnMaximizar.getToolTipText();
        if (estado.equals("Maximizar")) {
            this.setExtendedState(this.MAXIMIZED_BOTH);
            btnMaximizar.setToolTipText("Restaurar");
        } else {
            this.setExtendedState(this.NORMAL);
            btnMaximizar.setToolTipText("Maximizar");
        }

        // TODO add your handling code here:

    }//GEN-LAST:event_btnMaximizarActionPerformed

    private void headerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMousePressed
        x = evt.getX();
        y = evt.getY();         // TODO add your handling code here:
    }//GEN-LAST:event_headerMousePressed

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - x, point.y - y);         // TODO add your handling code here:
    }//GEN-LAST:event_headerMouseDragged

    private void txtUserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserKeyPressed
        validarBotoniniciar();
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String user = txtUser.getText();
            char[] arrayc = txtPass.getPassword();
            String pass = new String(arrayc);
            if (pass.isEmpty() || user.isEmpty() || user.length() < 5 || pass.length() < 6) {
            } else {
                ValidarInicioSesion();

            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_txtUserKeyPressed

    private void txtUserKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserKeyReleased
        validarBotoniniciar();
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String user = txtUser.getText();
            char[] arrayc = txtPass.getPassword();
            String pass = new String(arrayc);
            if (pass.isEmpty() || user.isEmpty() || user.length() < 5 || pass.length() < 6) {
            } else {
                ValidarInicioSesion();

            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserKeyReleased

    private void txtUserKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserKeyTyped

        if (txtUser.getText().length() >= 25) {
            evt.consume();
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserKeyTyped

    private void txtPassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassKeyPressed
        validarBotoniniciar();
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String user = txtUser.getText();
            char[] arrayc = txtPass.getPassword();
            String pass = new String(arrayc);
            if (pass.isEmpty() || user.isEmpty() || user.length() < 5 || pass.length() < 6) {
            } else {
                ValidarInicioSesion();

            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_txtPassKeyPressed

    private void txtPassKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassKeyReleased
        validarBotoniniciar();
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String user = txtUser.getText();
            char[] arrayc = txtPass.getPassword();
            String pass = new String(arrayc);
            if (pass.isEmpty() || user.isEmpty() || user.length() < 5 || pass.length() < 6) {
            } else {
                ValidarInicioSesion();

            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_txtPassKeyReleased

    private void txtPassKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassKeyTyped
        char[] arrayc = txtPass.getPassword();
        String pass = new String(arrayc);
        if (pass.length() >= 20) {
            evt.consume();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPassKeyTyped

    private void btnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionActionPerformed
        btnIniciarSesion.setBackground(Utilidades.getColorNormalMenu());
        ValidarInicioSesion();        // TODO add your handling code here:
    }//GEN-LAST:event_btnIniciarSesionActionPerformed

    private void btnInicialSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicialSalirActionPerformed
        btnInicialSalir.setBackground(Utilidades.getColorNormalMenu());
        Salir();        // TODO add your handling code here:
    }//GEN-LAST:event_btnInicialSalirActionPerformed

    private void btnGestionarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGestionarUsuarioActionPerformed
        //CrearPanelCuentas();        // TODO add your handling code here:
    }//GEN-LAST:event_btnGestionarUsuarioActionPerformed

    private void btnIniciadoSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciadoSalirActionPerformed
        btnIniciadoSalir.setBackground(Utilidades.getColorNormalMenu());
        Salir();        // TODO add your handling code here:
    }//GEN-LAST:event_btnIniciadoSalirActionPerformed

    private void btnPrincipalClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrincipalClientesActionPerformed
        CrearPanelCliente();        // TODO add your handling code here:
    }//GEN-LAST:event_btnPrincipalClientesActionPerformed

    private void btnPrincipalProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrincipalProveedoresActionPerformed
        CrearPanelProveedores();// TODO add your handling code here:
    }//GEN-LAST:event_btnPrincipalProveedoresActionPerformed

    private void btnPrincipalProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrincipalProductosActionPerformed
        CrearPanelProducto();
    }//GEN-LAST:event_btnPrincipalProductosActionPerformed

    private void menVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menVentasMouseClicked
        if (evt.getButton() == 1) {
            menuVentas.show(menVentas, menVentas.getX(), menVentas.getY());
        }    // TODO add your handling code here:
    }//GEN-LAST:event_menVentasMouseClicked

    private void menProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menProductosMouseClicked
        if (evt.getButton() == 1) {
            menuProductos.show(menProductos, menProductos.getX(), menProductos.getY());
        }  // TODO add your handling code here:
    }//GEN-LAST:event_menProductosMouseClicked

    private void menClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menClientesMouseClicked
        if (evt.getButton() == 1) {
            menuClientes.show(menClientes, menClientes.getX(), menClientes.getY());
        }            // TODO add your handling code here:
    }//GEN-LAST:event_menClientesMouseClicked

    private void menProveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menProveedoresMouseClicked
        if (evt.getButton() == 1) {
            menuProveedores.show(menProveedores, menProveedores.getX(), menProveedores.getY());
        }         // TODO add your handling code here:
    }//GEN-LAST:event_menProveedoresMouseClicked

    private void menEmpleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menEmpleadosMouseClicked
        if (evt.getButton() == 1) {
            menuEmpleados.show(menEmpleados, menEmpleados.getX(), menEmpleados.getY());
        }        // TODO add your handling code here:
    }//GEN-LAST:event_menEmpleadosMouseClicked

    private void menConfiguracionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menConfiguracionMouseClicked
        if (evt.getButton() == 1) {
            menuConfiguracion.show(menConfiguracion, menConfiguracion.getX(), menConfiguracion.getY());
        }       // TODO add your handling code here:
    }//GEN-LAST:event_menConfiguracionMouseClicked

    private void menComprasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menComprasMouseClicked
        if (evt.getButton() == 1) {
            menuCompras.show(menCompras, menCompras.getX(), menCompras.getY());
        }         // TODO add your handling code here:
    }//GEN-LAST:event_menComprasMouseClicked

    private void menVentasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menVentasMouseEntered
        menVentas.setBackground(Utilidades.getColorEntered());        // TODO add your handling code here:
    }//GEN-LAST:event_menVentasMouseEntered

    private void menProductosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menProductosMouseEntered
        menProductos.setBackground(Utilidades.getColorEntered());    // TODO add your handling code here:
    }//GEN-LAST:event_menProductosMouseEntered

    private void menClientesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menClientesMouseEntered
        menClientes.setBackground(Utilidades.getColorEntered());// TODO add your handling code here:
    }//GEN-LAST:event_menClientesMouseEntered

    private void menComprasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menComprasMouseEntered
        menCompras.setBackground(Utilidades.getColorEntered());        // TODO add your handling code here:
    }//GEN-LAST:event_menComprasMouseEntered

    private void menProveedoresMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menProveedoresMouseEntered
        menProveedores.setBackground(Utilidades.getColorEntered());        // TODO add your handling code here:
    }//GEN-LAST:event_menProveedoresMouseEntered

    private void menEmpleadosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menEmpleadosMouseEntered
        menEmpleados.setBackground(Utilidades.getColorEntered());        // TODO add your handling code here:
    }//GEN-LAST:event_menEmpleadosMouseEntered

    private void menConfiguracionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menConfiguracionMouseEntered
        menConfiguracion.setBackground(Utilidades.getColorEntered());        // TODO add your handling code here:
    }//GEN-LAST:event_menConfiguracionMouseEntered

    private void menVentasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menVentasMouseExited
        menVentas.setBackground(Utilidades.getColorNormalMenu());        // TODO add your handling code here:
    }//GEN-LAST:event_menVentasMouseExited

    private void menProductosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menProductosMouseExited
        menProductos.setBackground(Utilidades.getColorNormalMenu());         // TODO add your handling code here:
    }//GEN-LAST:event_menProductosMouseExited

    private void menClientesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menClientesMouseExited
        menClientes.setBackground(Utilidades.getColorNormalMenu());     // TODO add your handling code here:
    }//GEN-LAST:event_menClientesMouseExited

    private void menComprasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menComprasMouseExited
        menCompras.setBackground(Utilidades.getColorNormalMenu());     // TODO add your handling code here:
    }//GEN-LAST:event_menComprasMouseExited

    private void menProveedoresMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menProveedoresMouseExited
        menProveedores.setBackground(Utilidades.getColorNormalMenu());     // TODO add your handling code here:
    }//GEN-LAST:event_menProveedoresMouseExited

    private void menEmpleadosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menEmpleadosMouseExited
        menEmpleados.setBackground(Utilidades.getColorNormalMenu());     // TODO add your handling code here:
    }//GEN-LAST:event_menEmpleadosMouseExited

    private void menConfiguracionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menConfiguracionMouseExited
        menConfiguracion.setBackground(Utilidades.getColorNormalMenu());     // TODO add your handling code here:
    }//GEN-LAST:event_menConfiguracionMouseExited

    private void btnPrincipalProductosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrincipalProductosMousePressed
        btnPrincipalProductos.setOpaque(true);        // TODO add your handling code here:
    }//GEN-LAST:event_btnPrincipalProductosMousePressed

    private void btnPrincipalClientesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrincipalClientesMousePressed
        btnPrincipalClientes.setOpaque(true); // TODO add your handling code here:
    }//GEN-LAST:event_btnPrincipalClientesMousePressed

    private void btnPrincipalProveedoresMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrincipalProveedoresMousePressed
        btnPrincipalProveedores.setOpaque(true);        // TODO add your handling code here:
    }//GEN-LAST:event_btnPrincipalProveedoresMousePressed

    private void btnPrincipalProductosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrincipalProductosMouseReleased
        btnPrincipalProductos.setOpaque(false);   // TODO add your handling code here:
    }//GEN-LAST:event_btnPrincipalProductosMouseReleased

    private void btnPrincipalClientesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrincipalClientesMouseReleased
        btnPrincipalClientes.setOpaque(false);   // TODO add your handling code here:
    }//GEN-LAST:event_btnPrincipalClientesMouseReleased

    private void btnPrincipalProveedoresMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrincipalProveedoresMouseReleased
        btnPrincipalProveedores.setOpaque(false);   // TODO add your handling code here:
    }//GEN-LAST:event_btnPrincipalProveedoresMouseReleased

    private void btnPrincipalProductosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrincipalProductosMouseExited
        btnPrincipalProductos.setOpaque(false);// TODO add your handling code here:
    }//GEN-LAST:event_btnPrincipalProductosMouseExited

    private void btnPrincipalClientesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrincipalClientesMouseExited
        btnPrincipalClientes.setOpaque(false);// TODO add your handling code here:
    }//GEN-LAST:event_btnPrincipalClientesMouseExited

    private void btnPrincipalProveedoresMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrincipalProveedoresMouseExited
        btnPrincipalProveedores.setOpaque(false);// TODO add your handling code here:
    }//GEN-LAST:event_btnPrincipalProveedoresMouseExited

    private void btnCerrarSesionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarSesionMouseEntered
        btnCerrarSesion.setBackground(Utilidades.getColorEntered());        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrarSesionMouseEntered

    private void btnIniciadoSalirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIniciadoSalirMouseEntered
        btnIniciadoSalir.setBackground(Utilidades.getColorEntered());// TODO add your handling code here:
    }//GEN-LAST:event_btnIniciadoSalirMouseEntered

    private void btnCerrarSesionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarSesionMouseExited
        btnCerrarSesion.setBackground(Utilidades.getColorNormalMenu()); // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrarSesionMouseExited

    private void btnIniciadoSalirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIniciadoSalirMouseExited
        btnIniciadoSalir.setBackground(Utilidades.getColorNormalMenu()); // TODO add your handling code here:
    }//GEN-LAST:event_btnIniciadoSalirMouseExited

    private void btnIniciarSesionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIniciarSesionMouseEntered
        btnIniciarSesion.setBackground(Utilidades.getColorEntered());// TODO add your handling code here:
    }//GEN-LAST:event_btnIniciarSesionMouseEntered

    private void btnInicialSalirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicialSalirMouseEntered
        btnInicialSalir.setBackground(Utilidades.getColorEntered()); // TODO add your handling code here:
    }//GEN-LAST:event_btnInicialSalirMouseEntered

    private void btnIniciarSesionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIniciarSesionMouseExited
        btnIniciarSesion.setBackground(Utilidades.getColorNormalMenu());// TODO add your handling code here:
    }//GEN-LAST:event_btnIniciarSesionMouseExited

    private void btnInicialSalirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicialSalirMouseExited
        btnInicialSalir.setBackground(Utilidades.getColorNormalMenu());// TODO add your handling code here:
    }//GEN-LAST:event_btnInicialSalirMouseExited

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
         btnCerrarSesion.setBackground(Utilidades.getColorNormalMenu());
        int opc = JOptionDialog.showConfirmDialog(this, "¿Seguro que desea cerrar la sesión?", "Cerrar Sesión", JOptionDialog.SI_NO_OPTION);
        if (opc == 0) {

            LogService.logger.info(getUsuarioPrincipal().getUser(), "Sesion Cerrada");
            doCerrarSesion(getUsuarioPrincipal().getUser());
        }       // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void menuListarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuListarProductosActionPerformed
        CrearPanelProducto();        // TODO add your handling code here:
    }//GEN-LAST:event_menuListarProductosActionPerformed

    private void menuCategoriasProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCategoriasProductoActionPerformed
        CrearPanelCategorias();       // TODO add your handling code here:
    }//GEN-LAST:event_menuCategoriasProductoActionPerformed

    private void menuListarProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuListarProveedoresActionPerformed
        CrearPanelProveedores();        // TODO add your handling code here:
    }//GEN-LAST:event_menuListarProveedoresActionPerformed

    private void menuListarComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuListarComprasActionPerformed
        CrearPanelCompras();        // TODO add your handling code here:
    }//GEN-LAST:event_menuListarComprasActionPerformed

    private void btnPrincipalComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrincipalComprasActionPerformed
        CrearPanelCompras();        // TODO add your handling code here:
    }//GEN-LAST:event_btnPrincipalComprasActionPerformed

    private void btnPrincipalComprasMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrincipalComprasMouseReleased
        btnPrincipalCompras.setOpaque(false);    // TODO add your handling code here:
    }//GEN-LAST:event_btnPrincipalComprasMouseReleased

    private void btnPrincipalComprasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrincipalComprasMousePressed
        btnPrincipalCompras.setOpaque(true);        // TODO add your handling code here:
    }//GEN-LAST:event_btnPrincipalComprasMousePressed

    private void btnPrincipalComprasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrincipalComprasMouseExited
        btnPrincipalCompras.setOpaque(false);// TODO add your handling code here:
    }//GEN-LAST:event_btnPrincipalComprasMouseExited

    private void btnPrincipalVentasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrincipalVentasMouseExited
        btnPrincipalVentas.setOpaque(false);        // TODO add your handling code here:
    }//GEN-LAST:event_btnPrincipalVentasMouseExited

    private void btnPrincipalVentasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrincipalVentasMousePressed
        btnPrincipalVentas.setOpaque(true);          // TODO add your handling code here:
    }//GEN-LAST:event_btnPrincipalVentasMousePressed

    private void btnPrincipalVentasMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrincipalVentasMouseReleased
        btnPrincipalVentas.setOpaque(false);          // TODO add your handling code here:
    }//GEN-LAST:event_btnPrincipalVentasMouseReleased

    private void menuListarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuListarClientesActionPerformed
        CrearPanelCliente();        // TODO add your handling code here:
    }//GEN-LAST:event_menuListarClientesActionPerformed

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel;
    private javax.swing.JButton btnCerrarSesion;
    public static javax.swing.JButton btnGestionarUsuario;
    private javax.swing.JButton btnIniciadoSalir;
    private javax.swing.JButton btnInicialSalir;
    public static javax.swing.JButton btnIniciarSesion;
    public static javax.swing.JButton btnMaximizar;
    private javax.swing.JButton btnMinimizar;
    public static javax.swing.JButton btnPrincipalClientes;
    public static javax.swing.JButton btnPrincipalCompras;
    public static javax.swing.JButton btnPrincipalProductos;
    public static javax.swing.JButton btnPrincipalProveedores;
    public static javax.swing.JButton btnPrincipalVentas;
    private org.edisoncor.gui.varios.ClockDigital clockDigital1;
    private javax.swing.ButtonGroup grupoMenu;
    private javax.swing.JLabel header;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    public static javax.swing.JLabel labCargo;
    public static javax.swing.JLabel labNombreEmpleado;
    public static javax.swing.JLabel labUltimaConexion;
    private javax.swing.JLabel lab_5;
    private javax.swing.JLabel lab_6;
    public static javax.swing.JButton menClientes;
    public static javax.swing.JButton menCompras;
    public static javax.swing.JButton menConfiguracion;
    public static javax.swing.JButton menEmpleados;
    public static javax.swing.JButton menProductos;
    public static javax.swing.JButton menProveedores;
    public static javax.swing.JButton menVentas;
    private javax.swing.JMenuItem menuAgregarEmpleado;
    private javax.swing.JMenuItem menuCategoriasProducto;
    private javax.swing.JPopupMenu menuClientes;
    private javax.swing.JPopupMenu menuCompras;
    private javax.swing.JMenuItem menuConfig1;
    private javax.swing.JMenuItem menuConfig2;
    private javax.swing.JMenuItem menuConfig3;
    private javax.swing.JPopupMenu menuConfiguracion;
    private javax.swing.JPopupMenu menuEmpleados;
    private javax.swing.JMenuItem menuListarClientes;
    private javax.swing.JMenuItem menuListarCompras;
    private javax.swing.JMenuItem menuListarEmpleados;
    private javax.swing.JMenuItem menuListarProductos;
    private javax.swing.JMenuItem menuListarProveedores;
    private javax.swing.JMenuItem menuListarVentas;
    private javax.swing.JPopupMenu menuProductos;
    private javax.swing.JPopupMenu menuProveedores;
    private javax.swing.JMenuItem menuReporte1Empleados;
    private javax.swing.JMenuItem menuReporte1Ventas;
    private javax.swing.JMenuItem menuReporte2Empleados;
    private javax.swing.JMenuItem menuReporte2Ventas;
    private javax.swing.JPopupMenu menuVentas;
    public static org.matrix.CustomTabbedPane multiPanel;
    public static javax.swing.JPanel pClientes;
    public static javax.swing.JPanel pCompras;
    public static javax.swing.JPanel pConfiguracion;
    public static javax.swing.JPanel pEmpleados;
    public static javax.swing.JPanel pProductos;
    public static javax.swing.JPanel pProveedores;
    public static javax.swing.JPanel pVentas;
    public static org.edisoncor.gui.panel.PanelImage panelBody;
    private javax.swing.JPanel panelContenedorBody;
    public static javax.swing.JPanel panelIniciado;
    public static javax.swing.JPanel panelInicial;
    private javax.swing.JPanel panelMenu;
    public static javax.swing.JPanel panelPrincipalBotones;
    private javax.swing.JPanel panelTop;
    private javax.swing.JMenu subMenReportesEmpleados;
    private javax.swing.JMenu subMenReportesVentas;
    public static javax.swing.JPasswordField txtPass;
    public static javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables

}
