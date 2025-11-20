/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ClienteVista extends JFrame {
    public JTextField txtNombre, txtCorreo, txtTelefono, txtDireccion, txtBuscar;
    public JButton btnRegistrar, btnActualizar, btnEliminar, btnBuscar, btnVolver;
    public JTable tablaClientes;
    public DefaultTableModel modeloClientes;

    public ClienteVista() {
        setTitle("Gestión de Clientes");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(255, 248, 220));

        JLabel lblTitulo = new JLabel("GESTIÓN DE CLIENTES");
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

        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(50, 120, 100, 25);
        panel.add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(150, 120, 200, 25);
        panel.add(txtCorreo);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(50, 160, 100, 25);
        panel.add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(150, 160, 200, 25);
        panel.add(txtTelefono);

        JLabel lblDireccion = new JLabel("Dirección:");
        lblDireccion.setBounds(50, 200, 100, 25);
        panel.add(lblDireccion);

        txtDireccion = new JTextField();
        txtDireccion.setBounds(150, 200, 200, 25);
        panel.add(txtDireccion);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(400, 80, 120, 30);
        panel.add(btnRegistrar);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(530, 80, 120, 30);
        panel.add(btnActualizar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(400, 120, 120, 30);
        panel.add(btnEliminar);

        btnVolver = new JButton("Volver");
        btnVolver.setBounds(530, 120, 120, 30);
        btnVolver.setBackground(new Color(205, 92, 92));
        btnVolver.setForeground(Color.WHITE);
        panel.add(btnVolver);

        JLabel lblBuscar = new JLabel("Buscar:");
        lblBuscar.setBounds(50, 260, 100, 25);
        panel.add(lblBuscar);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(150, 260, 200, 25);
        panel.add(txtBuscar);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(370, 260, 100, 25);
        panel.add(btnBuscar);

        modeloClientes = new DefaultTableModel(new Object[]{"ID", "Nombre", "Correo", "Teléfono", "Dirección"}, 0);
        tablaClientes = new JTable(modeloClientes);
        JScrollPane scroll = new JScrollPane(tablaClientes);
        scroll.setBounds(50, 300, 680, 200);
        panel.add(scroll);

        add(panel);
    }
}
