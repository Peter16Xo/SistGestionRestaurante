/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CategoriaVista extends JFrame {
    public JTextField txtNombre, txtBuscar;
    public JButton btnRegistrar, btnActualizar, btnEliminar, btnBuscar, btnVolver;
    public JTable tablaCategorias;
    public DefaultTableModel modeloCategorias;

    public CategoriaVista() {
        setTitle("Gestión de Categorías");
        setSize(800, 550);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(255, 248, 220));

        JLabel lblTitulo = new JLabel("GESTIÓN DE CATEGORÍAS");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(139, 69, 19));
        lblTitulo.setBounds(250, 20, 400, 30);
        panel.add(lblTitulo);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(50, 80, 100, 25);
        panel.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(150, 80, 200, 25);
        panel.add(txtNombre);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(400, 80, 100, 30);
        panel.add(btnRegistrar);

        btnActualizar = new JButton("Modificar");
        btnActualizar.setBounds(510, 80, 100, 30);
        panel.add(btnActualizar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(620, 80, 100, 30);
        panel.add(btnEliminar);

        JLabel lblBuscar = new JLabel("Buscar:");
        lblBuscar.setBounds(50, 130, 60, 25);
        panel.add(lblBuscar);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(110, 130, 200, 25);
        panel.add(txtBuscar);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(320, 130, 100, 25);
        panel.add(btnBuscar);

        modeloCategorias = new DefaultTableModel(new Object[]{"ID", "Nombre"}, 0);
        tablaCategorias = new JTable(modeloCategorias);
        JScrollPane scroll = new JScrollPane(tablaCategorias);
        scroll.setBounds(50, 180, 670, 250);
        panel.add(scroll);

        btnVolver = new JButton("Volver");
        btnVolver.setBounds(330, 450, 120, 30);
        btnVolver.setBackground(new Color(205, 92, 92));
        btnVolver.setForeground(Color.WHITE);
        panel.add(btnVolver);

        add(panel);
    }
}
