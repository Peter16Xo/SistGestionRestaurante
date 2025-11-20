/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ProveedorVistaCocina extends JFrame {

    public JTextField txtNombre, txtContacto, txtTelefono, txtDireccion, txtEmail;
    public JButton btnActualizar, btnVolver, btnBuscar;
    public JTable tablaProveedores;
    public DefaultTableModel modeloProveedores;
    public JTextField txtBuscar;

    public ProveedorVistaCocina() {
        setTitle("Consulta de Proveedores - Cocina");
        setSize(850, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(255, 248, 220));

        JLabel lblTitulo = new JLabel("CONSULTA Y EDICIÓN BÁSICA DE PROVEEDORES");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(139, 69, 19));
        lblTitulo.setBounds(200, 20, 500, 30);
        panel.add(lblTitulo);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(50, 80, 100, 25);
        panel.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(150, 80, 200, 25);
        panel.add(txtNombre);

        JLabel lblContacto = new JLabel("Contacto:");
        lblContacto.setBounds(400, 80, 100, 25);
        panel.add(lblContacto);

        txtContacto = new JTextField();
        txtContacto.setBounds(500, 80, 200, 25);
        panel.add(txtContacto);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(50, 120, 100, 25);
        panel.add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(150, 120, 200, 25);
        panel.add(txtTelefono);

        JLabel lblDireccion = new JLabel("Dirección:");
        lblDireccion.setBounds(400, 120, 100, 25);
        panel.add(lblDireccion);

        txtDireccion = new JTextField();
        txtDireccion.setBounds(500, 120, 200, 25);
        panel.add(txtDireccion);

        JLabel lblEmail = new JLabel("Correo:");
        lblEmail.setBounds(50, 160, 100, 25);
        panel.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(150, 160, 200, 25);
        panel.add(txtEmail);

        btnActualizar = new JButton("Modificar");
        btnActualizar.setBounds(500, 160, 100, 30);
        panel.add(btnActualizar);

        JLabel lblBuscar = new JLabel("Buscar:");
        lblBuscar.setBounds(50, 210, 60, 25);
        panel.add(lblBuscar);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(110, 210, 200, 25);
        panel.add(txtBuscar);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(320, 210, 100, 25);
        panel.add(btnBuscar);

        modeloProveedores = new DefaultTableModel(new Object[]{"ID", "Nombre", "Contacto", "Teléfono", "Dirección", "Correo"}, 0);
        tablaProveedores = new JTable(modeloProveedores);
        JScrollPane scroll = new JScrollPane(tablaProveedores);
        scroll.setBounds(50, 250, 720, 250);
        panel.add(scroll);

        btnVolver = new JButton("Volver");
        btnVolver.setBounds(350, 520, 120, 30);
        btnVolver.setBackground(new Color(205, 92, 92));
        btnVolver.setForeground(Color.WHITE);
        panel.add(btnVolver);

        add(panel);
    }
}
