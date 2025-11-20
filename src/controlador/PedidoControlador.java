/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.*;
import vista.PedidoVista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PedidoControlador {

    private int platilloSeleccionado = -1;
    private PedidoVista vista;
    private int idPedido = DatosMemoria.listaPedidos.size() + 1;
    private List<PedidoPlatillo> detallePedido = new ArrayList<>();

    public PedidoControlador(PedidoVista vista) {
        this.vista = vista;
        cargarClientes();
        visualizarPedidos();

        // Cargar categorías
        vista.comboCategorias.removeAllItems();
        vista.comboCategorias.addItem("Todas");
        for (Categoria c : DatosMemoria.listaCategorias) {
            vista.comboCategorias.addItem(c.getId() + " - " + c.getNombre());
        }

        // Cargar platillos de todas las categorías al inicio
        cargarPlatillos("Todas");

        // Evento para cambiar los platillos al seleccionar categoría
        vista.comboCategorias.addActionListener(e -> {
            String seleccion = (String) vista.comboCategorias.getSelectedItem();
            if (seleccion != null) {
                cargarPlatillos(seleccion);
            }
        });

        vista.btnAgregarPlatillo.addActionListener(e -> agregarPlatilloAlDetalle());
        vista.btnRegistrar.addActionListener(e -> registrarPedido());
        vista.btnModificar.addActionListener(e -> actualizarPedido());
        vista.btnEliminar.addActionListener(e -> eliminarPedido());
        vista.btnEliminarPlatillo.addActionListener(e -> eliminarPlatilloSeleccionado());
        vista.btnVisualizar.addActionListener(e -> visualizarPedidos());
        vista.btnVolver.addActionListener(e -> vista.dispose());

        vista.tablaPedidos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) cargarPedidoSeleccionado();
        });

        vista.tablaDetalle.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) cargarDetalleSeleccionado();
            vista.btnEliminarPlatillo.setEnabled(true);
        });

        vista.btnModificarPlatillo.addActionListener(evt -> modificarPlatillo());
    }

    private void cargarClientes() {
        vista.comboClientes.removeAllItems();
        for (Cliente c : DatosMemoria.listaClientes) {
            vista.comboClientes.addItem(c.getId() + " - " + c.getNombre());
        }
    }

    private void cargarPlatillos(String categoriaSeleccionada) {
        vista.comboPlatillos.removeAllItems();
        if (categoriaSeleccionada.equals("Todas")) {
            for (Platillo p : DatosMemoria.listaPlatillos) {
                vista.comboPlatillos.addItem(p.getId() + " - " + p.getNombre());
            }
        } else {
            try {
                int categoriaId = Integer.parseInt(categoriaSeleccionada.split(" - ")[0]);
                for (Platillo p : DatosMemoria.listaPlatillos) {
                    if (p.getCategoria_id().getId() == categoriaId) {
                vista.comboPlatillos.addItem(p.getId() + " - " + p.getNombre());
                }
            }

            } catch (Exception ex) {
                vista.lblMensaje.setText("ERROR: No se pudo filtrar por categoría.");
            }
        }
    }

    private void agregarPlatilloAlDetalle() {
        String seleccion = (String) vista.comboPlatillos.getSelectedItem();
        String cantidadStr = vista.txtCantidad.getText().trim();

        if (seleccion == null || cantidadStr.isEmpty()) {
            vista.lblMensaje.setText("ERROR: Debe seleccionar un platillo y una cantidad.");
            return;
        }

        try {
            int cantidad = Integer.parseInt(cantidadStr);
            if (cantidad <= 0) {
                vista.lblMensaje.setText("ERROR: CANTIDAD INVÁLIDA PARA EL PLATILLO.");
                return;
            }

            int idPlatillo = Integer.parseInt(seleccion.split(" - ")[0]);
            Platillo platillo = DatosMemoria.listaPlatillos.stream()
                    .filter(p -> p.getId() == idPlatillo)
                    .findFirst().orElse(null);

            if (platillo == null) return;

            double precioUnitario = platillo.getPrecio();
            detallePedido.add(new PedidoPlatillo(idPedido, platillo.getId(), cantidad, precioUnitario));
            vista.lblMensaje.setText("Platillo añadido correctamente.");
            actualizarDetalleVisual();
            vista.txtCantidad.setText("");

        } catch (NumberFormatException e) {
            vista.lblMensaje.setText("ERROR: Cantidad debe ser numérica.");
        }
    }
    
    private void eliminarPlatilloSeleccionado() {
    int fila = vista.tablaDetalle.getSelectedRow();
    if (fila == -1) {
        vista.lblMensaje.setText("ERROR: DEBE SELECCIONAR UN PLATILLO PARA ELIMINAR.");
        return;
    }

    detallePedido.remove(fila);
    actualizarDetalleVisual();
    vista.lblMensaje.setText("Platillo eliminado correctamente.");
    platilloSeleccionado = -1;
    vista.btnEliminarPlatillo.setEnabled(false);
    vista.btnModificarPlatillo.setEnabled(false);
}


    private void modificarPlatillo() {
        if (platilloSeleccionado == -1) return;

        String seleccion = (String) vista.comboPlatillos.getSelectedItem();
        String cantidadStr = vista.txtCantidad.getText().trim();

        if (seleccion == null || cantidadStr.isEmpty()) {
            vista.lblMensaje.setText("ERROR: Debe seleccionar un platillo y una cantidad.");
            return;
        }

        try {
            int cantidad = Integer.parseInt(cantidadStr);
            if (cantidad <= 0) {
                vista.lblMensaje.setText("ERROR: Cantidad inválida.");
                return;
            }

            int idPlatillo = Integer.parseInt(seleccion.split(" - ")[0]);
            PedidoPlatillo original = detallePedido.get(platilloSeleccionado);

            original.setPlatillo_id(idPlatillo);
            original.setCantidad(cantidad);

            Platillo platillo = DatosMemoria.listaPlatillos.stream()
                    .filter(p -> p.getId() == idPlatillo)
                    .findFirst().orElse(null);
            if (platillo != null) {
                original.setPrecio_unitario(platillo.getPrecio());
            }

            actualizarDetalleVisual();
            vista.lblMensaje.setText("Platillo modificado correctamente.");
            vista.btnModificarPlatillo.setEnabled(false);
            platilloSeleccionado = -1;
        } catch (NumberFormatException ex) {
            vista.lblMensaje.setText("ERROR: Cantidad debe ser numérica.");
        }
    }

    private void actualizarDetalleVisual() {
        DefaultTableModel modelo = (DefaultTableModel) vista.tablaDetalle.getModel();
        modelo.setRowCount(0);
        double subtotal = 0;

        for (PedidoPlatillo pp : detallePedido) {
            Platillo platillo = DatosMemoria.listaPlatillos.stream()
                    .filter(p -> p.getId() == pp.getPlatillo_id())
                    .findFirst().orElse(null);
            if (platillo != null) {
                double total = pp.getCantidad() * pp.getPrecio_unitario();
                subtotal += total;
                modelo.addRow(new Object[]{platillo.getNombre(), pp.getCantidad(), pp.getPrecio_unitario(), total});
            }
        }

        vista.lblMensaje.setText("Subtotal: $" + String.format("%.2f", subtotal));
    }

    private void registrarPedido() {
        if (detallePedido.isEmpty()) {
            vista.lblMensaje.setText("ERROR: UN PEDIDO DEBE TENER AL MENOS UN PLATILLO.");
            return;
        }

        String clienteSel = (String) vista.comboClientes.getSelectedItem();
        if (clienteSel == null) {
            vista.lblMensaje.setText("ERROR: DEBE SELECCIONAR UN CLIENTE.");
            return;
        }

        int clienteId = Integer.parseInt(clienteSel.split(" - ")[0]);
        int empleadoId = LoginControlador.empleadoActual != null ? LoginControlador.empleadoActual.getId() : 1;
        LocalDateTime ahora = LocalDateTime.now();
        double subtotal = detallePedido.stream()
                .mapToDouble(p -> p.getCantidad() * p.getPrecio_unitario()).sum();

        Pedido pedido = new Pedido(idPedido, clienteId, empleadoId, ahora, subtotal);
        DatosMemoria.listaPedidos.add(pedido);
        DatosMemoria.listaPedidoPlatillos.addAll(detallePedido);

        vista.lblMensaje.setText("Pedido registrado correctamente.");
        idPedido++;
        detallePedido.clear();
        actualizarDetalleVisual();
        visualizarPedidos();
        limpiarCampos();
    }

    private void visualizarPedidos() {
        DefaultTableModel modelo = (DefaultTableModel) vista.tablaPedidos.getModel();
        modelo.setRowCount(0);

        for (Pedido p : DatosMemoria.listaPedidos) {
            String cliente = DatosMemoria.listaClientes.stream()
                    .filter(c -> c.getId() == p.getCliente_id())
                    .map(Cliente::getNombre)
                    .findFirst().orElse("?");

            String empleado = DatosMemoria.listaUsuarios.stream()
                    .filter(e -> e.getId() == p.getEmpleado_id())
                    .map(Empleado::getNombre)
                    .findFirst().orElse("?");

            modelo.addRow(new Object[]{p.getId(), cliente,
                    p.getFecha_hora().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                    "$" + String.format("%.2f", p.getSubtotal()),
                    empleado});
        }
    }

private void actualizarPedido() {
    int fila = vista.tablaPedidos.getSelectedRow();
    if (fila == -1) {
        vista.lblMensaje.setText("ERROR: DEBE SELECCIONAR UN PEDIDO A MODIFICAR.");
        return;
    }

    if (detallePedido.isEmpty()) {
        vista.lblMensaje.setText("ERROR: UN PEDIDO DEBE TENER AL MENOS UN PLATILLO.");
        return;
    }

    int pedidoId = (int) vista.tablaPedidos.getValueAt(fila, 0);
    Pedido pedido = DatosMemoria.listaPedidos.stream()
            .filter(p -> p.getId() == pedidoId)
            .findFirst().orElse(null);

    if (pedido == null) return;

    double subtotal = detallePedido.stream()
            .mapToDouble(p -> p.getCantidad() * p.getPrecio_unitario()).sum();

    pedido.setSubtotal(subtotal);

    DatosMemoria.listaPedidoPlatillos.removeIf(pp -> pp.getPedido_id() == pedido.getId());

    for (PedidoPlatillo pp : detallePedido) {
        pp.setPedido_id(pedido.getId());
    }

    DatosMemoria.listaPedidoPlatillos.addAll(detallePedido);

    vista.lblMensaje.setText("Pedido modificado correctamente.");
    detallePedido.clear();
    actualizarDetalleVisual();
    visualizarPedidos();
    limpiarCampos();
}


    private void eliminarPedido() {
        int fila = vista.tablaPedidos.getSelectedRow();
        if (fila == -1) {
            vista.lblMensaje.setText("ERROR: DEBE SELECCIONAR UN PEDIDO A ELIMINAR.");
            return;
        }

        int pedidoId = (int) vista.tablaPedidos.getValueAt(fila, 0);
        DatosMemoria.listaPedidos.removeIf(p -> p.getId() == pedidoId);
        DatosMemoria.listaPedidoPlatillos.removeIf(pp -> pp.getPedido_id() == pedidoId);

        vista.lblMensaje.setText("Pedido eliminado correctamente.");
        visualizarPedidos();
        actualizarDetalleVisual();
        limpiarCampos();
    }

    private void cargarPedidoSeleccionado() {
        int fila = vista.tablaPedidos.getSelectedRow();
        if (fila == -1) return;

        int pedidoId = (int) vista.tablaPedidos.getValueAt(fila, 0);
        Pedido pedido = DatosMemoria.listaPedidos.stream()
                .filter(p -> p.getId() == pedidoId)
                .findFirst().orElse(null);

        if (pedido == null) return;

        String cliente = DatosMemoria.listaClientes.stream()
                .filter(c -> c.getId() == pedido.getCliente_id())
                .map(c -> c.getId() + " - " + c.getNombre())
                .findFirst().orElse(null);
        vista.comboClientes.setSelectedItem(cliente);

        detallePedido.clear();
        for (PedidoPlatillo pp : DatosMemoria.listaPedidoPlatillos) {
            if (pp.getPedido_id() == pedidoId) {
                detallePedido.add(new PedidoPlatillo(pedidoId, pp.getPlatillo_id(), pp.getCantidad(), pp.getPrecio_unitario()));
            }
        }
        actualizarDetalleVisual();
    }

    private void cargarDetalleSeleccionado() {
        int fila = vista.tablaDetalle.getSelectedRow();
        if (fila == -1) {
            vista.btnEliminarPlatillo.setEnabled(false);
            vista.btnModificarPlatillo.setEnabled(false);
            return;
        }

        String nombrePlatillo = (String) vista.tablaDetalle.getValueAt(fila, 0);
        int cantidad = (int) vista.tablaDetalle.getValueAt(fila, 1);
        vista.txtCantidad.setText(String.valueOf(cantidad));

        for (int i = 0; i < vista.comboPlatillos.getItemCount(); i++) {
            if (vista.comboPlatillos.getItemAt(i).contains(nombrePlatillo)) {
                vista.comboPlatillos.setSelectedIndex(i);
                break;
            }
        }

        platilloSeleccionado = fila;
        vista.btnEliminarPlatillo.setEnabled(true);
        vista.btnModificarPlatillo.setEnabled(true);
    }

    private void limpiarCampos() {
        vista.comboClientes.setSelectedIndex(0);
        vista.comboPlatillos.setSelectedIndex(0);
        vista.txtCantidad.setText("");
    }
}
