/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    public JLabel lblBienvenida;
    public JButton btnCerrarSesion;

    public VentanaPrincipal(String rol) {
        setTitle("Sistema de Restaurante - Menú Principal");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 248, 220));
        panel.setLayout(null);

        lblBienvenida = new JLabel("Bienvenido al sistema, Rol: " + rol);
        lblBienvenida.setBounds(50, 40, 300, 25);
        lblBienvenida.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblBienvenida.setForeground(new Color(139, 69, 19));
        panel.add(lblBienvenida);

        btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setBounds(120, 90, 150, 30);
        btnCerrarSesion.setBackground(new Color(205, 92, 92));
        btnCerrarSesion.setForeground(Color.WHITE);
        panel.add(btnCerrarSesion);

        add(panel);
    }
}
