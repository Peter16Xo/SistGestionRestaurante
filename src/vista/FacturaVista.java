/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FacturaVista extends JFrame {

    public JTable tablaPedidos, tablaFacturas;
    public DefaultTableModel modeloPedidos, modeloFacturas;
    public JTextArea areaFactura;
    public JButton btnGenerarFactura, btnVolver;
    public JLabel lblMensaje;

    public FacturaVista() {
        setTitle("Gestión de Factura");
        setSize(900, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(255, 248, 220));

        JLabel lblTitulo = new JLabel("GESTIÓN DE FACTURAS", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setBounds(250, 10, 400, 30);
        lblTitulo.setForeground(new Color(139, 69, 19));
        panel.add(lblTitulo);

        modeloPedidos = new DefaultTableModel(new Object[]{"ID Pedido", "Cliente", "Subtotal", "Fecha"}, 0);
        tablaPedidos = new JTable(modeloPedidos);
        JScrollPane scrollTablaPedidos = new JScrollPane(tablaPedidos);
        scrollTablaPedidos.setBounds(30, 50, 820, 120);
        panel.add(scrollTablaPedidos);

        btnGenerarFactura = new JButton("Generar Factura");
        btnGenerarFactura.setBounds(250, 180, 180, 30);
        panel.add(btnGenerarFactura);

        btnVolver = new JButton("Volver");
        btnVolver.setBounds(460, 180, 180, 30);
        btnVolver.setBackground(new Color(205, 92, 92));
        btnVolver.setForeground(Color.WHITE);
        panel.add(btnVolver);

        JLabel lblSubtitulo = new JLabel("FACTURAS GENERADAS", SwingConstants.CENTER);
        lblSubtitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblSubtitulo.setBounds(250, 220, 400, 25);
        lblSubtitulo.setForeground(new Color(139, 69, 19));
        panel.add(lblSubtitulo);

        modeloFacturas = new DefaultTableModel(new Object[]{"ID Factura", "ID Pedido", "Subtotal", "IVA", "Total"}, 0);
        tablaFacturas = new JTable(modeloFacturas);
        JScrollPane scrollTablaFacturas = new JScrollPane(tablaFacturas);
        scrollTablaFacturas.setBounds(30, 250, 820, 130);
        panel.add(scrollTablaFacturas);

        areaFactura = new JTextArea();
        areaFactura.setEditable(false);
        areaFactura.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollArea = new JScrollPane(areaFactura);
        scrollArea.setBounds(30, 390, 820, 200);
        panel.add(scrollArea);

        lblMensaje = new JLabel("", SwingConstants.CENTER);
        lblMensaje.setBounds(30, 600, 820, 20);
        lblMensaje.setForeground(Color.RED);
        panel.add(lblMensaje);

        add(panel);
    }
}
