/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.ResultSet;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Familia
 */
public class Conexion {
    
    public static Connection con;
    
    public final String base="jugador";
    public String usuario="root@";
    public final String direccion="jdbc:mysql://localhost:3307/"+base;
    public ResultSet registro=null;
    public ResultSet registrillo=null;
    public String clave;
    
}
