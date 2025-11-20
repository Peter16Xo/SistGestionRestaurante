/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.time.LocalDateTime;
import modelo.*;
import vista.FacturaVista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.format.DateTimeFormatter;

public class FacturaControlador {

    private FacturaVista vista;

    public FacturaControlador(FacturaVista vista) {
        this.vista = vista;
        cargarPedidos();
        cargarFacturas();

        vista.btnVolver.addActionListener(e -> vista.dispose());
        vista.btnGenerarFactura.addActionListener(e -> generarFactura());

        vista.tablaFacturas.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) visualizarDetallesFactura();
        });
    }

    //metodo cargarPedidos
    private void cargarPedidos() {
        DefaultTableModel modelo = vista.modeloPedidos;
        modelo.setRowCount(0);

        for (Pedido pedido : DatosMemoria.listaPedidos) {
            boolean yaFacturado = DatosMemoria.listaFacturas.stream()
                    .anyMatch(f -> f.getPedido_id() == pedido.getId());

            if (!yaFacturado) {
                String cliente = DatosMemoria.listaClientes.stream()
                        .filter(c -> c.getId() == pedido.getCliente_id())
                        .map(Cliente::getNombre)
                        .findFirst().orElse("?");

                modelo.addRow(new Object[]{
                        pedido.getId(), cliente,
                        "$" + String.format("%.2f", pedido.getSubtotal()),
                        pedido.getFecha_hora().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                });
            }
        }
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
    }

    //metodo generarFactura
    private void generarFactura() {
        int fila = vista.tablaPedidos.getSelectedRow();
        if (fila == -1) {
            vista.lblMensaje.setText("ERROR: DEBE SELECCIONAR UN PEDIDO PARA FACTURAR.");
            return;
        }

        int pedidoId = (int) vista.tablaPedidos.getValueAt(fila, 0);
        Pedido pedido = DatosMemoria.listaPedidos.stream()
                .filter(p -> p.getId() == pedidoId)
                .findFirst().orElse(null);

        if (pedido == null) {
            vista.lblMensaje.setText("ERROR: NO SE PUDO CARGAR EL PEDIDO.");
            return;
        }

        boolean yaFacturado = DatosMemoria.listaFacturas.stream()
                .anyMatch(f -> f.getPedido_id() == pedidoId);

        if (yaFacturado) {
            vista.lblMensaje.setText("ERROR: ESTE PEDIDO YA TIENE UNA FACTURA ASOCIADA.");
            return;
        }

        double subtotal = pedido.getSubtotal();
        double iva = subtotal * 0.15;
        double total = subtotal + iva;
        int empleadoId = pedido.getEmpleado_id();
        int idFactura = DatosMemoria.listaFacturas.stream()
                .mapToInt(Factura::getId)
                .max()
                .orElse(0) + 1;

        LocalDateTime ahora = LocalDateTime.now();
        Factura factura = new Factura(idFactura, pedido.getId(), empleadoId, subtotal, iva, total, ahora);

        DatosMemoria.listaFacturas.add(factura);

        cargarPedidos();
        cargarFacturas();
        mostrarFactura(factura);
        vista.lblMensaje.setText("Factura generada exitosamente.");
    }

    //metodo visualizarDetallesFactura  es metodo visualizarFactura
    private void visualizarDetallesFactura() {
        int fila = vista.tablaFacturas.getSelectedRow();
        if (fila == -1) return;

        Object valorId = vista.tablaFacturas.getValueAt(fila, 0);
        int facturaId = (valorId instanceof Integer)
                ? (int) valorId
                : Integer.parseInt(valorId.toString());

        Factura factura = DatosMemoria.listaFacturas.stream()
                .filter(f -> f.getId() == facturaId)
                .findFirst().orElse(null);

        if (factura != null) {
            mostrarFactura(factura);
        } else {
            vista.areaFactura.setText("ERROR: NO SE PUDO CARGAR LA FACTURA.");
        }
    }

    //metodo mostrarFactura
    private void mostrarFactura(Factura factura) {
        Pedido pedido = DatosMemoria.listaPedidos.stream()
                .filter(p -> p.getId() == factura.getPedido_id())
                .findFirst().orElse(null);

        if (pedido == null) {
            vista.areaFactura.setText("ERROR: PEDIDO NO ENCONTRADO.");
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

        vista.areaFactura.setText(detalle.toString());
    }
}
