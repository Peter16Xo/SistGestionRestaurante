/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.*;
import vista.VisualizarPedidosVista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.format.DateTimeFormatter;

public class VisualizarPedidosControlador {

    private VisualizarPedidosVista vista;

    public VisualizarPedidosControlador(VisualizarPedidosVista vista) {
        this.vista = vista;
        cargarClientes();
        cargarPedidos("Todos", "");

        vista.btnAplicarFiltro.addActionListener(e -> aplicarFiltro());
        vista.btnCerrar.addActionListener(e -> vista.dispose());
    }

    //metodo cargarClientes
    private void cargarClientes() {
        vista.comboFiltroCliente.removeAllItems();
        vista.comboFiltroCliente.addItem("Todos");
        for (Cliente c : DatosMemoria.listaClientes) {
            vista.comboFiltroCliente.addItem(c.getId() + " - " + c.getNombre());
        }
    }
    
    //metodo aplicarFiltro
    private void aplicarFiltro() {
        String filtroCliente = (String) vista.comboFiltroCliente.getSelectedItem();
        String filtroFecha = vista.txtFiltroFecha.getText();
        cargarPedidos(filtroCliente, filtroFecha);
    }

    //metodo cargarPedidos
    private void cargarPedidos(String filtroCliente, String filtroFecha) {
        DefaultTableModel modelo = vista.modeloTabla;
        modelo.setRowCount(0);

        for (Pedido p : DatosMemoria.listaPedidos) {
            boolean pasaFiltro = true;

            if (filtroCliente != null && !filtroCliente.equals("Todos")) {
                int id = Integer.parseInt(filtroCliente.split(" - ")[0]);
                if (p.getCliente_id() != id) pasaFiltro = false;
            }

            if (filtroFecha != null && !filtroFecha.isEmpty()) {
                String fechaPedido = p.getFecha_hora().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                if (!fechaPedido.equals(filtroFecha)) pasaFiltro = false;
            }

            if (pasaFiltro) {
                String cliente = DatosMemoria.listaClientes.stream()
                        .filter(c -> c.getId() == p.getCliente_id())
                        .map(Cliente::getNombre)
                        .findFirst().orElse("?");

                String platillos = DatosMemoria.listaPedidoPlatillos.stream()
                        .filter(pp -> pp.getPedido_id() == p.getId())
                        .map(pp -> {
                            Platillo platillo = DatosMemoria.listaPlatillos.stream()
                                    .filter(pl -> pl.getId() == pp.getPlatillo_id())
                                    .findFirst().orElse(null);
                            return platillo != null ? platillo.getNombre() + " x" + pp.getCantidad() : "?";
                        })
                        .reduce("", (a, b) -> a.isEmpty() ? b : a + ", " + b);

                String empleado = DatosMemoria.listaUsuarios.stream()
                        .filter(u -> u.getRol().equals("Mesero") && u.getUsuario().equalsIgnoreCase("mesero"))
                        .map(Empleado::getNombre)
                        .findFirst().orElse("?");

                modelo.addRow(new Object[]{
                        p.getId(), cliente, platillos,
                        "$" + String.format("%.2f", p.getSubtotal()),
                        p.getFecha_hora().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                        empleado
                });
            }
        }

        vista.lblMensaje.setText("");
    }
}
