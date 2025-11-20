/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.ClienteControlador;
import javax.swing.*;
import java.awt.*;

public class VentanaPrincipalMesero extends JFrame {

    public VentanaPrincipalMesero() {
        setTitle("Panel del Mesero - Sistema de Restaurante");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 248, 220)); 
        panel.setLayout(null);

        JLabel lblTitulo = new JLabel("MENÚ PRINCIPAL - MESERO");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setBounds(160, 20, 350, 30);
        lblTitulo.setForeground(new Color(139, 69, 19));
        panel.add(lblTitulo);

        JButton btnClientes = new JButton("Gestionar Clientes");
        btnClientes.setBounds(180, 80, 240, 35);
        panel.add(btnClientes);
        btnClientes.addActionListener(e -> {
        ClienteVista vista = new ClienteVista();
        new ClienteControlador(vista);
        vista.setVisible(true);
        });
        

 

        JButton btnPedidos = new JButton("Registrar Pedido");
        btnPedidos.setBounds(180, 130, 240, 35);
        panel.add(btnPedidos);

        btnPedidos.addActionListener(e -> {
        vista.PedidoVista vista = new vista.PedidoVista();
        new controlador.PedidoControlador(vista);
        vista.setVisible(true);
        });


        JButton btnVerPedidos = new JButton("Visualizar Pedidos");
        btnVerPedidos.setBounds(180, 180, 240, 35);
        panel.add(btnVerPedidos);

        btnVerPedidos.addActionListener(e -> {
        vista.VisualizarPedidosVista vista = new vista.VisualizarPedidosVista();
        new controlador.VisualizarPedidosControlador(vista);
        vista.setVisible(true);
        });


        JButton btnFacturas = new JButton("Gestionar Facturas");
        btnFacturas.setBounds(180, 230, 240, 35);
        panel.add(btnFacturas);

        btnFacturas.addActionListener(e -> {
        vista.FacturaVista vista = new vista.FacturaVista();
        new controlador.FacturaControlador(vista);
        vista.setVisible(true);
        });


        JButton btnCerrar = new JButton("Cerrar Sesión");
        btnCerrar.setBounds(180, 280, 240, 35);
        btnCerrar.setBackground(new Color(205, 92, 92));
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setFocusPainted(false);
        panel.add(btnCerrar);

        btnCerrar.addActionListener(e -> {
            dispose();
            new VentanaLogin().setVisible(true);
        });

        add(panel);
    }
}
