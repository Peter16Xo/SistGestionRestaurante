/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VisualizarPedidosVista extends JFrame {

    public JTable tablaResumen;
    public DefaultTableModel modeloTabla;
    public JComboBox<String> comboFiltroCliente;
    public JTextField txtFiltroFecha;
    public JButton btnAplicarFiltro, btnCerrar;
    public JLabel lblMensaje;

    public VisualizarPedidosVista() {
        setTitle("Visualización de Pedidos");
        setSize(850, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(255, 248, 220));

        JLabel lblTitulo = new JLabel("LISTADO DE PEDIDOS REGISTRADOS", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setBounds(200, 10, 450, 30);
        lblTitulo.setForeground(new Color(139, 69, 19));
        panel.add(lblTitulo);

        JLabel lblCliente = new JLabel("Filtrar por Cliente:");
        lblCliente.setBounds(30, 60, 140, 25);
        panel.add(lblCliente);

        comboFiltroCliente = new JComboBox<>();
        comboFiltroCliente.setBounds(160, 60, 220, 25);
        panel.add(comboFiltroCliente);

        JLabel lblFecha = new JLabel("Filtrar por Fecha (yyyy-MM-dd):");
        lblFecha.setBounds(400, 60, 200, 25);
        panel.add(lblFecha);

        txtFiltroFecha = new JTextField();
        txtFiltroFecha.setBounds(610, 60, 150, 25);
        panel.add(txtFiltroFecha);

        btnAplicarFiltro = new JButton("Aplicar Filtro");
        btnAplicarFiltro.setBounds(320, 100, 200, 25);
        panel.add(btnAplicarFiltro);

        modeloTabla = new DefaultTableModel(new Object[]{"ID Pedido", "Cliente", "Platillos", "Subtotal", "Fecha"}, 0);
        tablaResumen = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaResumen);
        scroll.setBounds(30, 140, 780, 250);
        panel.add(scroll);

        lblMensaje = new JLabel("", SwingConstants.CENTER);
        lblMensaje.setBounds(30, 400, 780, 25);
        lblMensaje.setForeground(Color.RED);
        panel.add(lblMensaje);

        btnCerrar = new JButton("Cerrar");
        btnCerrar.setBounds(340, 430, 160, 30);
        btnCerrar.setBackground(new Color(205, 92, 92));
        btnCerrar.setForeground(Color.WHITE);
        panel.add(btnCerrar);

        add(panel);
    }
}
