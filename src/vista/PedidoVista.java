/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PedidoVista extends JFrame {

    public JComboBox<String> comboClientes, comboPlatillos, comboPedidos, comboCategorias;
    public JTextField txtCantidad;
    public JButton btnAgregarPlatillo, btnEliminarPlatillo, btnModificarPlatillo, btnRegistrar, btnModificar, btnEliminar, btnVisualizar, btnVolver;
    public JTable tablaDetalle, tablaPedidos;
    public JLabel lblMensaje;

    public PedidoVista() {
        setTitle("Gestión de Pedidos");
        setSize(900, 620);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(255, 248, 220));

        JLabel lblTitulo = new JLabel("GESTIÓN DE PEDIDOS");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setBounds(300, 10, 300, 30);
        lblTitulo.setForeground(new Color(139, 69, 19));
        panel.add(lblTitulo);

        JLabel lblCliente = new JLabel("Cliente:");
        lblCliente.setBounds(30, 50, 100, 25);
        panel.add(lblCliente);

        comboClientes = new JComboBox<>();
        comboClientes.setBounds(100, 50, 200, 25);
        panel.add(comboClientes);

        JLabel lblCategoria = new JLabel("Categoría:");
        lblCategoria.setBounds(320, 50, 100, 25);
        panel.add(lblCategoria);

        comboCategorias = new JComboBox<>();
        comboCategorias.setBounds(400, 50, 200, 25);
        panel.add(comboCategorias);

        JLabel lblPlatillo = new JLabel("Platillo:");
        lblPlatillo.setBounds(30, 90, 100, 25);
        panel.add(lblPlatillo);

        comboPlatillos = new JComboBox<>();
        comboPlatillos.setBounds(100, 90, 200, 25);
        panel.add(comboPlatillos);

        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setBounds(320, 90, 100, 25);
        panel.add(lblCantidad);

        txtCantidad = new JTextField();
        txtCantidad.setBounds(400, 90, 100, 25);
        panel.add(txtCantidad);

        btnAgregarPlatillo = new JButton("Agregar Platillo");
        btnAgregarPlatillo.setBounds(520, 90, 180, 25);
        panel.add(btnAgregarPlatillo);

        btnEliminarPlatillo = new JButton("Eliminar Platillo");
        btnEliminarPlatillo.setBounds(520, 120, 180, 25);
        panel.add(btnEliminarPlatillo);

        btnModificarPlatillo = new JButton("Modificar Platillo");
        btnModificarPlatillo.setBounds(520, 150, 180, 25);
        btnModificarPlatillo.setEnabled(false);
        panel.add(btnModificarPlatillo);

        DefaultTableModel modeloDetalle = new DefaultTableModel(new Object[]{"Platillo", "Cantidad", "P. Unitario", "Total"}, 0);
        tablaDetalle = new JTable(modeloDetalle);
        JScrollPane scrollDetalle = new JScrollPane(tablaDetalle);
        scrollDetalle.setBounds(30, 190, 820, 120);
        panel.add(scrollDetalle);

        btnRegistrar = new JButton("Registrar Pedido");
        btnRegistrar.setBounds(30, 330, 180, 25);
        panel.add(btnRegistrar);

        btnModificar = new JButton("Modificar Pedido");
        btnModificar.setBounds(220, 330, 180, 25);
        panel.add(btnModificar);

        btnEliminar = new JButton("Eliminar Pedido");
        btnEliminar.setBounds(410, 330, 180, 25);
        panel.add(btnEliminar);

        btnVisualizar = new JButton("Visualizar Pedidos");
        btnVisualizar.setBounds(600, 330, 180, 25);
        panel.add(btnVisualizar);

        btnVisualizar.addActionListener(e -> {
            vista.VisualizarPedidosVista vista = new vista.VisualizarPedidosVista();
            new controlador.VisualizarPedidosControlador(vista);
            vista.setVisible(true);
        });

        DefaultTableModel modeloPedidos = new DefaultTableModel(new Object[]{"ID", "Cliente", "Fecha/Hora", "Subtotal", "Empleado"}, 0);
        tablaPedidos = new JTable(modeloPedidos);
        JScrollPane scrollPedidos = new JScrollPane(tablaPedidos);
        scrollPedidos.setBounds(30, 370, 820, 150);
        panel.add(scrollPedidos);

        lblMensaje = new JLabel("", SwingConstants.CENTER);
        lblMensaje.setBounds(30, 530, 820, 20);
        lblMensaje.setForeground(Color.RED);
        panel.add(lblMensaje);

        btnVolver = new JButton("Volver al Menú");
        btnVolver.setBounds(350, 560, 200, 25);
        btnVolver.setBackground(new Color(205, 92, 92));
        btnVolver.setForeground(Color.WHITE);
        panel.add(btnVolver);

        add(panel);
    }
}
