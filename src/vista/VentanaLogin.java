/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import modelo.DatosMemoria;
import modelo.Empleado;
import controlador.LoginControlador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaLogin extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JLabel lblMensaje;

    public VentanaLogin() {
        setTitle("Sistema de Restaurante - Login");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        JPanel panelIzquierdo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon(getClass().getResource("/resources/restaurante.jpg"));
                Image image = icon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panelIzquierdo.setPreferredSize(new Dimension(450, 500));

        JPanel panelDerecho = new JPanel(new GridBagLayout());
        panelDerecho.setBackground(new Color(255, 248, 220));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitulo = new JLabel("INICIO DE SESIÓN");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(139, 69, 19));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panelDerecho.add(lblTitulo, gbc);

        gbc.gridwidth = 1;

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelDerecho.add(lblUsuario, gbc);

        txtUsuario = new JTextField(20);
        gbc.gridx = 1;
        panelDerecho.add(txtUsuario, gbc);

        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelDerecho.add(lblContrasena, gbc);

        txtContrasena = new JPasswordField(20);
        gbc.gridx = 1;
        panelDerecho.add(txtContrasena, gbc);

        JButton btnIngresar = new JButton("Iniciar Sesión");
        btnIngresar.setBackground(new Color(205, 92, 92));
        btnIngresar.setForeground(Color.WHITE);
        btnIngresar.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panelDerecho.add(btnIngresar, gbc);

        lblMensaje = new JLabel("", SwingConstants.CENTER);
        lblMensaje.setForeground(Color.RED);
        gbc.gridy = 4;
        panelDerecho.add(lblMensaje, gbc);

        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtUsuario.getText().trim();
                String contrasena = new String(txtContrasena.getPassword());

                if (usuario.isEmpty() || contrasena.isEmpty()) {
                    lblMensaje.setText("Debe ingresar todos los campos.");
                    return;
                }

                Empleado u = DatosMemoria.validarUsuario(usuario, contrasena);
                if (u != null) {
                    LoginControlador.empleadoActual = u;
                    lblMensaje.setText("");
                    JOptionPane.showMessageDialog(null, "Bienvenido, " + u.getNombre());
                    dispose();

                    switch (u.getRol()) {
                        case "Gerente":
                            new VentanaPrincipalGerente().setVisible(true);
                            break;
                        case "Mesero":
                            new VentanaPrincipalMesero().setVisible(true);
                            break;
                        case "Cocina":
                            new VentanaPrincipalCocina().setVisible(true);
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Rol desconocido: " + u.getRol());
                    }
                } else {
                    lblMensaje.setText("Usuario o contraseña incorrectos.");
                }
            }
        });

        add(panelIzquierdo, BorderLayout.WEST);
        add(panelDerecho, BorderLayout.CENTER);
    }
}
