/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.ProveedorControladorCocina;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipalCocina extends JFrame {

    public VentanaPrincipalCocina() {
        setTitle("Panel de Cocina - Sistema de Restaurante");
        setSize(600, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 248, 220));
        panel.setLayout(null);

        JLabel lblTitulo = new JLabel("MENÚ PRINCIPAL - COCINA");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setBounds(160, 20, 350, 30);
        lblTitulo.setForeground(new Color(139, 69, 19));
        panel.add(lblTitulo);

        JButton btnPedidos = new JButton("Visualizar Pedidos"); 
        btnPedidos.setBounds(180, 80, 240, 35);
        panel.add(btnPedidos);
        btnPedidos.addActionListener(e -> {
            VisualizarPedidosVista vista = new VisualizarPedidosVista();
            new controlador.VisualizarPedidosControlador(vista);
            vista.setVisible(true);
        });

        JButton btnPlatillos = new JButton("Gestionar Platillos");
        btnPlatillos.setBounds(180, 130, 240, 35);
        panel.add(btnPlatillos);
        btnPlatillos.addActionListener(e -> {
            PlatilloVista vista = new PlatilloVista();
            new controlador.PlatilloControlador(vista);
            vista.setVisible(true);
        });

        JButton btnCategorias = new JButton("Gestionar Categorías");
        btnCategorias.setBounds(180, 180, 240, 35);
        panel.add(btnCategorias);
        btnCategorias.addActionListener(e -> {
            CategoriaVista vista = new CategoriaVista();
            new controlador.CategoriaControlador(vista);
            vista.setVisible(true);
        });

        // NUEVO BOTÓN: Consultar Proveedores
        JButton btnProveedores = new JButton("Consultar Proveedores");
        btnProveedores.setBounds(180, 230, 240, 35);
        panel.add(btnProveedores);
        btnProveedores.addActionListener(e -> {
            ProveedorVistaCocina vista = new ProveedorVistaCocina();
            new ProveedorControladorCocina(vista);
            vista.setVisible(true);
        });

        JButton btnCerrar = new JButton("Cerrar Sesión");
        btnCerrar.setBounds(180, 290, 240, 35);
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
