/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PlatilloVista extends JFrame {

    public JTextField txtNombre, txtPrecio;
    public JComboBox<String> comboCategoria;
    public JButton btnRegistrar, btnActualizar, btnEliminar, btnBuscar, btnVolver;
    public JTable tablaPlatillos;
    public DefaultTableModel modeloPlatillos;
    public JTextField txtBuscar;

    public PlatilloVista() {
        setTitle("Gestión de Platillos");
        setSize(850, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(255, 248, 220));

        JLabel lblTitulo = new JLabel("GESTIÓN DE PLATILLOS");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(139, 69, 19));
        lblTitulo.setBounds(300, 20, 400, 30);
        panel.add(lblTitulo);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(50, 80, 100, 25);
        panel.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(150, 80, 200, 25);
        panel.add(txtNombre);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(400, 80, 100, 25);
        panel.add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(500, 80, 200, 25);
        panel.add(txtPrecio);

        JLabel lblCategoria = new JLabel("Categoría:");
        lblCategoria.setBounds(50, 120, 100, 25);
        panel.add(lblCategoria);

        comboCategoria = new JComboBox<>();
        comboCategoria.setBounds(150, 120, 200, 25);
        panel.add(comboCategoria);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(400, 120, 100, 30);
        panel.add(btnRegistrar);

        btnActualizar = new JButton("Modificar");
        btnActualizar.setBounds(510, 120, 100, 30);
        panel.add(btnActualizar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(620, 120, 100, 30);
        panel.add(btnEliminar);

        JLabel lblBuscar = new JLabel("Filtrar por categoría:");
        lblBuscar.setBounds(50, 170, 150, 25);
        panel.add(lblBuscar);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(200, 170, 200, 25);
        panel.add(txtBuscar);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(420, 170, 100, 25);
        panel.add(btnBuscar);

        modeloPlatillos = new DefaultTableModel(new Object[]{"ID", "Nombre", "Precio", "Categoría"}, 0);
        tablaPlatillos = new JTable(modeloPlatillos);
        JScrollPane scroll = new JScrollPane(tablaPlatillos);
        scroll.setBounds(50, 210, 720, 250);
        panel.add(scroll);

        btnVolver = new JButton("Volver");
        btnVolver.setBounds(350, 500, 120, 30);
        btnVolver.setBackground(new Color(205, 92, 92));
        btnVolver.setForeground(Color.WHITE);
        panel.add(btnVolver);

        add(panel);
    }
}
