/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import javax.swing.*;
import java.awt.*;

public class ReporteVista extends JFrame {

    public JButton btnGenerarDiario, btnFiltrarFecha, btnVolver;
    public JTextField txtFecha;
    public JTextArea areaResultado;
    public JLabel lblMensaje;

    public ReporteVista() {
        setTitle("Reporte de Ventas");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(255, 248, 220));

        JLabel lblTitulo = new JLabel("GENERACIÓN DE REPORTES", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setBounds(150, 10, 400, 30);
        lblTitulo.setForeground(new Color(139, 69, 19));
        panel.add(lblTitulo);

        JLabel lblFecha = new JLabel("Fecha (yyyy-MM-dd):");
        lblFecha.setBounds(30, 60, 150, 25);
        panel.add(lblFecha);

        txtFecha = new JTextField();
        txtFecha.setBounds(180, 60, 150, 25);
        panel.add(txtFecha);

        btnGenerarDiario = new JButton("Generar Reporte Diario");
        btnGenerarDiario.setBounds(350, 60, 180, 25);
        panel.add(btnGenerarDiario);

        btnFiltrarFecha = new JButton("Filtrar por Fecha");
        btnFiltrarFecha.setBounds(540, 60, 130, 25);
        panel.add(btnFiltrarFecha);

        areaResultado = new JTextArea();
        areaResultado.setEditable(false);
        areaResultado.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(areaResultado);
        scroll.setBounds(30, 100, 640, 300);
        panel.add(scroll);

        lblMensaje = new JLabel("", SwingConstants.CENTER);
        lblMensaje.setBounds(30, 410, 640, 20);
        lblMensaje.setForeground(Color.RED);
        panel.add(lblMensaje);

        btnVolver = new JButton("Volver");
        btnVolver.setBounds(270, 440, 160, 30);
        btnVolver.setBackground(new Color(205, 92, 92));
        btnVolver.setForeground(Color.WHITE);
        panel.add(btnVolver);

        add(panel);
    }
}
