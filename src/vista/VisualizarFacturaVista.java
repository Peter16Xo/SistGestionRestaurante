/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VisualizarFacturaVista extends JFrame {

    public JTable tablaFacturas;
    public DefaultTableModel modeloFacturas;
    public JTextArea areaDetalleFactura;
    public JButton btnEliminarFactura, btnVolver;
    public JLabel lblMensaje;

    public VisualizarFacturaVista() {
        setTitle("Visualización y Eliminación de Facturas");
        setSize(850, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(255, 248, 220));

        JLabel lblTitulo = new JLabel("FACTURAS GENERADAS", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setBounds(230, 10, 400, 30);
        lblTitulo.setForeground(new Color(139, 69, 19));
        panel.add(lblTitulo);

        modeloFacturas = new DefaultTableModel(new Object[]{"ID Factura", "ID Pedido", "Subtotal", "IVA", "Total"}, 0);
        tablaFacturas = new JTable(modeloFacturas);
        JScrollPane scrollTabla = new JScrollPane(tablaFacturas);
        scrollTabla.setBounds(30, 60, 780, 150);
        panel.add(scrollTabla);

        areaDetalleFactura = new JTextArea();
        areaDetalleFactura.setEditable(false);
        areaDetalleFactura.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollArea = new JScrollPane(areaDetalleFactura);
        scrollArea.setBounds(30, 220, 780, 200);
        panel.add(scrollArea);

        btnEliminarFactura = new JButton("Eliminar Factura");
        btnEliminarFactura.setBounds(240, 440, 180, 30);
        panel.add(btnEliminarFactura);

        btnVolver = new JButton("Volver");
        btnVolver.setBounds(440, 440, 180, 30);
        btnVolver.setBackground(new Color(205, 92, 92));
        btnVolver.setForeground(Color.WHITE);
        panel.add(btnVolver);

        lblMensaje = new JLabel("", SwingConstants.CENTER);
        lblMensaje.setBounds(30, 490, 780, 20);
        lblMensaje.setForeground(Color.RED);
        panel.add(lblMensaje);

        add(panel);
    }
}
