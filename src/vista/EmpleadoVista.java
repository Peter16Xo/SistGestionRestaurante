/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EmpleadoVista extends JFrame {
    public JTextField txtNombre, txtUsuario, txtContrasena, txtBuscar;
    public JComboBox<String> comboRol;
    public JButton btnRegistrar, btnModificar, btnEliminar, btnCambiarRol, btnBuscar, btnVolver;
    public JTable tablaEmpleados;
    public DefaultTableModel modeloEmpleados;

    public EmpleadoVista() {
        setTitle("Gestión de Empleados");
        setSize(800, 580);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(255, 248, 220));

        JLabel lblTitulo = new JLabel("GESTIÓN DE EMPLEADOS");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(139, 69, 19));
        lblTitulo.setBounds(270, 20, 400, 30);
        panel.add(lblTitulo);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(50, 80, 100, 25);
        panel.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(150, 80, 200, 25);
        panel.add(txtNombre);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(50, 120, 100, 25);
        panel.add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(150, 120, 200, 25);
        panel.add(txtUsuario);

        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setBounds(50, 160, 100, 25);
        panel.add(lblContrasena);

        txtContrasena = new JTextField();
        txtContrasena.setBounds(150, 160, 200, 25);
        panel.add(txtContrasena);

        JLabel lblRol = new JLabel("Rol:");
        lblRol.setBounds(50, 200, 100, 25);
        panel.add(lblRol);

        comboRol = new JComboBox<>(new String[]{"Mesero", "Cocina", "Gerente"});
        comboRol.setBounds(150, 200, 200, 25);
        panel.add(comboRol);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(400, 80, 120, 30);
        panel.add(btnRegistrar);

        btnModificar = new JButton("Modificar");
        btnModificar.setBounds(530, 80, 120, 30);
        panel.add(btnModificar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(400, 120, 120, 30);
        panel.add(btnEliminar);

        btnCambiarRol = new JButton("Cambiar Rol");
        btnCambiarRol.setBounds(530, 120, 120, 30);
        panel.add(btnCambiarRol);

        JLabel lblBuscar = new JLabel("Buscar:");
        lblBuscar.setBounds(50, 250, 100, 25);
        panel.add(lblBuscar);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(150, 250, 200, 25);
        panel.add(txtBuscar);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(370, 250, 120, 25);
        panel.add(btnBuscar);

        modeloEmpleados = new DefaultTableModel(new Object[]{"Nombre", "Usuario", "Rol"}, 0);
        tablaEmpleados = new JTable(modeloEmpleados);
        JScrollPane scroll = new JScrollPane(tablaEmpleados);
        scroll.setBounds(50, 290, 680, 200);
        panel.add(scroll);

        btnVolver = new JButton("Volver");
        btnVolver.setBounds(330, 500, 120, 30);
        btnVolver.setBackground(new Color(205, 92, 92));
        btnVolver.setForeground(Color.WHITE);
        panel.add(btnVolver);

        add(panel);
    }
}
