/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obed77.views.dialogosComunes;

import core.controlador.principal.Utilidades;
import java.awt.MouseInfo;
import java.awt.Point;



/**
 *
 * @author Saito
 */
public class dialogWarnF extends javax.swing.JDialog {
    int x,y,resultado;
    /**
     * Creates new form dialogYesNo
     */
    public dialogWarnF(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public void setTitulo(String titulo){
        this.labTitulo.setText(titulo);
    }
    
    public void setTexto(String texto){
        this.labTexto.setText("<html>"+texto+"</html>");
    }
    
    public void setBotones(int botones){
        switch(botones){
            case 0: 
                this.btnAceptar.setVisible(false);
                this.btnCancelar.setVisible(false);
                this.btnNo.setVisible(true);
                this.btnSi.setVisible(true);
                break;
            case 1: 
                this.btnAceptar.setVisible(false);
                this.btnCancelar.setVisible(true);
                this.btnNo.setVisible(true);
                this.btnSi.setVisible(true);
                break;
            case 2: 
                this.btnAceptar.setVisible(true);
                this.btnCancelar.setVisible(true);
                this.btnNo.setVisible(false);
                this.btnSi.setVisible(false);
                break;
            case 3: 
                this.btnAceptar.setVisible(true);
                this.btnCancelar.setVisible(false);
                this.btnNo.setVisible(false);
                this.btnSi.setVisible(false);
                break;
            case 4: 
                this.btnAceptar.setVisible(false);
                this.btnCancelar.setVisible(true);
                this.btnNo.setVisible(false);
                this.btnSi.setVisible(false);
                break;    
            default: 
                this.btnAceptar.setVisible(true);
                this.btnCancelar.setVisible(false);
                this.btnNo.setVisible(false);
                this.btnSi.setVisible(false);
                break;    
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

        jPanel1 = new javax.swing.JPanel();
        panelTop = new javax.swing.JPanel();
        labTitulo = new javax.swing.JLabel();
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        panelIcono = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        panelTexto = new javax.swing.JPanel();
        labTexto = new javax.swing.JLabel();
        panelBotones = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnSi = new javax.swing.JButton();
        btnNo = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(220, 220, 220));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        panelTop.setBackground(new java.awt.Color(220, 220, 220));
        panelTop.setPreferredSize(new java.awt.Dimension(701, 25));

        labTitulo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labTitulo.setText("LABEL TITULO");
        labTitulo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        labTitulo.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                labTituloMouseDragged(evt);
            }
        });
        labTitulo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                labTituloMousePressed(evt);
            }
        });

        javax.swing.GroupLayout panelTopLayout = new javax.swing.GroupLayout(panelTop);
        panelTop.setLayout(panelTopLayout);
        panelTopLayout.setHorizontalGroup(
            panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelTopLayout.setVerticalGroup(
            panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTopLayout.createSequentialGroup()
                .addComponent(labTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/FondoApp.jpg"))); // NOI18N

        panelIcono.setMaximumSize(new java.awt.Dimension(55, 55));
        panelIcono.setMinimumSize(new java.awt.Dimension(55, 55));
        panelIcono.setOpaque(false);
        panelIcono.setPreferredSize(new java.awt.Dimension(55, 55));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Icono_Warn_55.png"))); // NOI18N

        javax.swing.GroupLayout panelIconoLayout = new javax.swing.GroupLayout(panelIcono);
        panelIcono.setLayout(panelIconoLayout);
        panelIconoLayout.setHorizontalGroup(
            panelIconoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelIconoLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelIconoLayout.setVerticalGroup(
            panelIconoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelIconoLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panelTexto.setOpaque(false);

        labTexto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labTexto.setForeground(new java.awt.Color(255, 255, 255));
        labTexto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labTexto.setText("LABEL TEXTO");

        javax.swing.GroupLayout panelTextoLayout = new javax.swing.GroupLayout(panelTexto);
        panelTexto.setLayout(panelTextoLayout);
        panelTextoLayout.setHorizontalGroup(
            panelTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelTextoLayout.setVerticalGroup(
            panelTextoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTextoLayout.createSequentialGroup()
                .addComponent(labTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panelBotones.setOpaque(false);

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

        btnSi.setBackground(new java.awt.Color(255, 255, 255));
        btnSi.setText("Si");
        btnSi.setBorderPainted(false);
        btnSi.setContentAreaFilled(false);
        btnSi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSi.setMaximumSize(new java.awt.Dimension(110, 25));
        btnSi.setMinimumSize(new java.awt.Dimension(110, 25));
        btnSi.setOpaque(true);
        btnSi.setPreferredSize(new java.awt.Dimension(110, 25));
        btnSi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSiMouseExited(evt);
            }
        });
        btnSi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiActionPerformed(evt);
            }
        });

        btnNo.setBackground(new java.awt.Color(255, 255, 255));
        btnNo.setText("No");
        btnNo.setBorderPainted(false);
        btnNo.setContentAreaFilled(false);
        btnNo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNo.setMaximumSize(new java.awt.Dimension(110, 25));
        btnNo.setMinimumSize(new java.awt.Dimension(110, 25));
        btnNo.setOpaque(true);
        btnNo.setPreferredSize(new java.awt.Dimension(110, 25));
        btnNo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNoMouseExited(evt);
            }
        });
        btnNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNoActionPerformed(evt);
            }
        });

        btnAceptar.setBackground(new java.awt.Color(255, 255, 255));
        btnAceptar.setText("Aceptar");
        btnAceptar.setBorderPainted(false);
        btnAceptar.setContentAreaFilled(false);
        btnAceptar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAceptar.setMaximumSize(new java.awt.Dimension(110, 25));
        btnAceptar.setMinimumSize(new java.awt.Dimension(110, 25));
        btnAceptar.setOpaque(true);
        btnAceptar.setPreferredSize(new java.awt.Dimension(110, 25));
        btnAceptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAceptarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAceptarMouseExited(evt);
            }
        });
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBotonesLayout = new javax.swing.GroupLayout(panelBotones);
        panelBotones.setLayout(panelBotonesLayout);
        panelBotonesLayout.setHorizontalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBotonesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSi, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNo, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        panelBotonesLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAceptar, btnCancelar, btnNo, btnSi});

        panelBotonesLayout.setVerticalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotonesLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelBotonesLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCancelar, btnNo, btnSi});

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(panelIcono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(panelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelIcono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTop, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(panelTop, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void labTituloMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labTituloMouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - x, point.y - y);         // TODO add your handling code here:
    }//GEN-LAST:event_labTituloMouseDragged

    private void labTituloMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labTituloMousePressed
        x = evt.getX();
        y = evt.getY();         // TODO add your handling code here:
    }//GEN-LAST:event_labTituloMousePressed

    private void btnCancelarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseEntered
        btnCancelar.setBackground(Utilidades.getColorEntered());        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarMouseEntered

    private void btnCancelarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseExited
    btnCancelar.setBackground(Utilidades.getColorNormalMenu()); // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarMouseExited

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
    resultado = 3;
    this.dispose();    // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSiMouseEntered
    btnSi.setBackground(Utilidades.getColorEntered());     // TODO add your handling code here:
    }//GEN-LAST:event_btnSiMouseEntered

    private void btnSiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSiMouseExited
    btnSi.setBackground(Utilidades.getColorNormalMenu());        // TODO add your handling code here:
    }//GEN-LAST:event_btnSiMouseExited

    private void btnSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiActionPerformed
    resultado = 0;
    this.dispose();// TODO add your handling code here:
    }//GEN-LAST:event_btnSiActionPerformed

    private void btnNoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNoMouseEntered
    btnNo.setBackground(Utilidades.getColorEntered());        // TODO add your handling code here:
    }//GEN-LAST:event_btnNoMouseEntered

    private void btnNoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNoMouseExited
    btnNo.setBackground(Utilidades.getColorNormalMenu());        // TODO add your handling code here:
    }//GEN-LAST:event_btnNoMouseExited

    private void btnNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoActionPerformed
    resultado = 1;
    this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnNoActionPerformed

    private void btnAceptarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAceptarMouseEntered
    btnAceptar.setBackground(Utilidades.getColorEntered());        // TODO add your handling code here:
    }//GEN-LAST:event_btnAceptarMouseEntered

    private void btnAceptarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAceptarMouseExited
    btnAceptar.setBackground(Utilidades.getColorNormalMenu());        // TODO add your handling code here:
    }//GEN-LAST:event_btnAceptarMouseExited

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
    resultado = 2;
    this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnAceptarActionPerformed

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
            java.util.logging.Logger.getLogger(dialogWarnF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dialogWarnF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dialogWarnF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dialogWarnF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
            public void run() {
                dialogWarnF dialog = new dialogWarnF(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnNo;
    private javax.swing.JButton btnSi;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labTexto;
    public javax.swing.JLabel labTitulo;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelIcono;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private javax.swing.JPanel panelTexto;
    private javax.swing.JPanel panelTop;
    // End of variables declaration//GEN-END:variables

}
