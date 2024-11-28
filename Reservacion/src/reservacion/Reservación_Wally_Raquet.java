/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservacion;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Conexion.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Familia
 */
public class Reservación_Wally_Raquet extends javax.swing.JFrame {

    Conexion armd=new Conexion();
    public Reservación_Wally_Raquet() {
        initComponents();
    }

    void registrar()
    {
        try
            (
                Connection conectador=DriverManager.getConnection(armd.direccion,armd.usuario,armd.base);
                CallableStatement estado=conectador.prepareCall("{call regjugador(?,?,?,?)}")
                )
        {
            estado.setString(1,txtcodigo.getText());
            estado.setString(2,txtnombre.getText());
            estado.setString(3,txtjuego.getText());
            estado.setString(4,txthora.getText());
            estado.execute();
            estado.close();
            JOptionPane.showMessageDialog(null,"Registro Adicionado Correctamente");
            limpiar();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    void modificar(){
        try
            (
                Connection conectador=DriverManager.getConnection(armd.direccion,armd.usuario,armd.base);
                CallableStatement estado=conectador.prepareCall("{call modjugador2(?,?,?,?)}")
                
                )
        {
            estado.setString(1,txtcodigo.getText());
            estado.setString(2,txtnombre.getText());
            estado.setString(3,txtjuego.getText());
            estado.setString(4,txthora.getText());
            estado.execute();
            estado.close();
            JOptionPane.showMessageDialog(null,"Registro Actualizado Correctamente");
            limpiar();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    
    void Eliminar(){
        try
            (
                Connection conectador=DriverManager.getConnection(armd.direccion,armd.usuario,armd.base);
                CallableStatement estado=conectador.prepareCall("{call elijugador1(?)}")
                )
        {
            estado.setString(1,txtcodigo.getText());
            estado.execute();
            estado.close();
            JOptionPane.showMessageDialog(null,"Registro Eliminado Correctamente");
            limpiar();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    
    void limpiar(){
        txtcodigo.setText("");
        txtnombre.setText("");
        txtjuego.setText("");
        txthora.setText("");
    }
    
    void CargarDatosTabla()
    {
        DefaultTableModel tablita=new DefaultTableModel();
        try
            (
                Connection conectador=DriverManager.getConnection(armd.direccion,armd.usuario,armd.base);
                CallableStatement estado=conectador.prepareCall("{call mosjugador()}")
                )
        {
            tablita.addColumn("codigo");
            tablita.addColumn("nombre");
            tablita.addColumn("Juego");
            tablita.addColumn("Hora");
            armd.registro=estado.executeQuery();
            while(armd.registro.next())
            {
                Object dato[]=new Object[2];
                for(int i=0;i<2;i++)
                {
                    dato[i]=armd.registro.getString(i+1);
                }
                tablita.addRow(dato);
            }
            Tblreserva.setModel(tablita);
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    void seleccionarRegistro()
    {
        txtcodigo.setText(Tblreserva.getValueAt(Tblreserva.getSelectedRow(),0).toString());
        txtnombre.setText(Tblreserva.getValueAt(Tblreserva.getSelectedRow(),1).toString());
        txtjuego.setText(Tblreserva.getValueAt(Tblreserva.getSelectedRow(),2).toString());
        txthora.setText(Tblreserva.getValueAt(Tblreserva.getSelectedRow(),3).toString());
    }
    void BuscarJugador(){
        try
            (
                Connection conectador=DriverManager.getConnection(armd.direccion,armd.usuario,armd.base);
                )
        {
            DefaultTableModel tablita=new DefaultTableModel();
            PreparedStatement proceso=null;
            armd.registrillo=null;
            String consulta="SELECT * FROM `jugador` WHERE 1 like '%"+txtbuscar.getText()+"%';";
            proceso=conectador.prepareStatement(consulta);
            armd.registrillo=proceso.executeQuery();
            ResultSetMetaData datitos=(ResultSetMetaData)armd.registrillo.getMetaData();
            
            tablita.addColumn("codigo");
            tablita.addColumn("nombre");
            tablita.addColumn("Juego");
            tablita.addColumn("Hora");
            
            int col=datitos.getColumnCount();
            
            while(armd.registrillo.next())
            {
                Object[] fila=new Object[col];
                for(int i=0;i<col;i++)
                {
                    fila[i]=armd.registrillo.getObject(i+1);
                }
                tablita.addRow(fila);
            }
            Tblreserva.setModel(tablita);
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtcodigo = new javax.swing.JTextField();
        txtnombre = new javax.swing.JTextField();
        txthora = new javax.swing.JTextField();
        btnReservar = new javax.swing.JButton();
        BtnModificar = new javax.swing.JButton();
        BtnEliminar = new javax.swing.JButton();
        BtnNuevo = new javax.swing.JButton();
        BtnSalir = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtbuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tblreserva = new javax.swing.JTable();
        txtjuego = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel1.setText("Reservación(Wally-Raquet)");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel2.setText("Codigo");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 50, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel3.setText("Nombre");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 90, 50, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel4.setText("Juego");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 40, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel5.setText("Hora");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 160, 30, -1));
        getContentPane().add(txtcodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 100, -1));
        getContentPane().add(txtnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 120, 100, -1));
        getContentPane().add(txthora, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 190, 100, -1));

        btnReservar.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        btnReservar.setText("Reservar");
        btnReservar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservarActionPerformed(evt);
            }
        });
        getContentPane().add(btnReservar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));

        BtnModificar.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        BtnModificar.setText("Modificar");
        BtnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnModificarActionPerformed(evt);
            }
        });
        getContentPane().add(BtnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 240, -1, -1));

        BtnEliminar.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        BtnEliminar.setText("Eliminar");
        BtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(BtnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 240, -1, -1));

        BtnNuevo.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        BtnNuevo.setText("Nuevo");
        BtnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNuevoActionPerformed(evt);
            }
        });
        getContentPane().add(BtnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 240, -1, -1));

        BtnSalir.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        BtnSalir.setText("Salir");
        BtnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalirActionPerformed(evt);
            }
        });
        getContentPane().add(BtnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 240, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel6.setText("Buscar");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 290, 50, -1));

        txtbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbuscarKeyTyped(evt);
            }
        });
        getContentPane().add(txtbuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 320, 270, -1));

        Tblreserva.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        Tblreserva.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Codigo", "Nombre", "Juego", "Hora"
            }
        ));
        Tblreserva.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblreservaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Tblreserva);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 490, 90));
        getContentPane().add(txtjuego, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 100, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReservarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservarActionPerformed
        // TODO add your handling code here:
        registrar();
    }//GEN-LAST:event_btnReservarActionPerformed

    private void BtnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnModificarActionPerformed
        // TODO add your handling code here:
        modificar();
    }//GEN-LAST:event_BtnModificarActionPerformed

    private void BtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarActionPerformed
        // TODO add your handling code here:
        Eliminar();
    }//GEN-LAST:event_BtnEliminarActionPerformed

    private void BtnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNuevoActionPerformed
        // TODO add your handling code here:
        limpiar();
    }//GEN-LAST:event_BtnNuevoActionPerformed

    private void BtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_BtnSalirActionPerformed

    private void TblreservaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblreservaMouseClicked
        // TODO add your handling code here:
        seleccionarRegistro();
    }//GEN-LAST:event_TblreservaMouseClicked

    private void txtbuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyTyped
        // TODO add your handling code here:
        BuscarJugador();
    }//GEN-LAST:event_txtbuscarKeyTyped

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
            java.util.logging.Logger.getLogger(Reservación_Wally_Raquet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reservación_Wally_Raquet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reservación_Wally_Raquet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reservación_Wally_Raquet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Reservación_Wally_Raquet().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnEliminar;
    private javax.swing.JButton BtnModificar;
    private javax.swing.JButton BtnNuevo;
    private javax.swing.JButton BtnSalir;
    private javax.swing.JTable Tblreserva;
    private javax.swing.JButton btnReservar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtbuscar;
    private javax.swing.JTextField txtcodigo;
    private javax.swing.JTextField txthora;
    private javax.swing.JTextField txtjuego;
    private javax.swing.JTextField txtnombre;
    // End of variables declaration//GEN-END:variables
}
