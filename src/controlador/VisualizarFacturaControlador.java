/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.*;
import vista.VisualizarFacturaVista;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.time.format.DateTimeFormatter;

public class VisualizarFacturaControlador {

    private VisualizarFacturaVista vista;

    public VisualizarFacturaControlador(VisualizarFacturaVista vista) {
        this.vista = vista;
        cargarFacturas();

        vista.tablaFacturas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) mostrarFacturaSeleccionada();
            }
        });

        vista.btnEliminarFactura.addActionListener(e -> eliminarFactura());
        vista.btnVolver.addActionListener(e -> vista.dispose());
    }


    //metodo cargarFacturas
    private void cargarFacturas() {
        DefaultTableModel modelo = vista.modeloFacturas;
        modelo.setRowCount(0);

        for (Factura f : DatosMemoria.listaFacturas) {
            modelo.addRow(new Object[]{
                f.getId(), f.getPedido_id(),
                "$" + String.format("%.2f", f.getSubtotal()),
                "$" + String.format("%.2f", f.getIva()),
                "$" + String.format("%.2f", f.getTotal())
            });
        }

        vista.lblMensaje.setText("");
        vista.areaDetalleFactura.setText("");
    }

    //metodo mostrarFacturaSeleccionada
    private void mostrarFacturaSeleccionada() {
        int fila = vista.tablaFacturas.getSelectedRow();
        if (fila == -1) return;

        Object valor = vista.tablaFacturas.getValueAt(fila, 0);
        int facturaId = (valor instanceof Integer)
                ? (int) valor
                : Integer.parseInt(valor.toString());

        Factura factura = DatosMemoria.listaFacturas.stream()
                .filter(f -> f.getId() == facturaId)
                .findFirst().orElse(null);

        if (factura == null) {
            vista.lblMensaje.setText("ERROR: NO SE PUDO CARGAR LA FACTURA.");
            return;
        }

        Pedido pedido = DatosMemoria.listaPedidos.stream()
                .filter(p -> p.getId() == factura.getPedido_id())
                .findFirst().orElse(null);

        if (pedido == null) {
            vista.lblMensaje.setText("ERROR: NO SE PUDO CARGAR EL PEDIDO.");
            return;
        }

        String cliente = DatosMemoria.listaClientes.stream()
                .filter(c -> c.getId() == pedido.getCliente_id())
                .map(Cliente::getNombre)
                .findFirst().orElse("?");

        String empleado = DatosMemoria.listaUsuarios.stream()
                .filter(e -> e.getId() == factura.getEmpleado_id())
                .map(Empleado::getNombre)
                .findFirst().orElse("?");

        StringBuilder detalle = new StringBuilder();
        detalle.append("---------------- FACTURA ----------------\n");
        detalle.append("Factura ID: ").append(factura.getId()).append("\n");
        detalle.append("Pedido ID: ").append(pedido.getId()).append("\n");
        detalle.append("Cliente: ").append(cliente).append("\n");
        detalle.append("Empleado: ").append(empleado).append("\n");
        detalle.append("Fecha: ").append(pedido.getFecha_hora().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))).append("\n\n");

        detalle.append(String.format("%-25s %-10s %-10s\n", "Platillo", "Cant", "Precio"));
        detalle.append("------------------------------------------------\n");

        for (PedidoPlatillo pp : DatosMemoria.listaPedidoPlatillos) {
            if (pp.getPedido_id() == pedido.getId()) {
                Platillo platillo = DatosMemoria.listaPlatillos.stream()
                        .filter(p -> p.getId() == pp.getPlatillo_id())
                        .findFirst().orElse(null);
                if (platillo != null) {
                    detalle.append(String.format("%-25s %-10d $%-9.2f\n",
                            platillo.getNombre(), pp.getCantidad(), pp.getPrecio_unitario()));
                }
            }
        }

        detalle.append("\n----------------------------------------------\n");
        detalle.append(String.format("Subtotal: $%.2f\n", factura.getSubtotal()));
        detalle.append(String.format("IVA (15%%): $%.2f\n", factura.getIva()));
        detalle.append(String.format("TOTAL A PAGAR: $%.2f\n", factura.getTotal()));
        detalle.append("----------------------------------------------\n");

        vista.areaDetalleFactura.setText(detalle.toString());
        vista.lblMensaje.setText("");
    }

    //metodo eliminarFactura
    private void eliminarFactura() {
        int fila = vista.tablaFacturas.getSelectedRow();
        if (fila == -1) {
            vista.lblMensaje.setText("ERROR: DEBE SELECCIONAR UNA FACTURA PARA ELIMINAR.");
            return;
        }

        int facturaId = Integer.parseInt(vista.tablaFacturas.getValueAt(fila, 0).toString());
        int opcion = JOptionPane.showConfirmDialog(vista,
                "¿Está seguro que desea eliminar la factura ID " + facturaId + "?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            DatosMemoria.listaFacturas.removeIf(f -> f.getId() == facturaId);
            vista.lblMensaje.setText("Factura eliminada correctamente.");
            cargarFacturas();
        }
    }
}
