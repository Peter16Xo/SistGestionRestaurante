/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package controlador;

import modelo.Cliente;
import modelo.DatosMemoria;
import modelo.Factura;
import modelo.Pedido;
import vista.ClienteVista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ClienteControlador {
    private ClienteVista vista;
    private int idActual;

    public ClienteControlador(ClienteVista vista) {
        this.vista = vista;
        this.idActual = calcularSiguienteId();
        this.vista.btnRegistrar.addActionListener(e -> registrarCliente());
        this.vista.btnActualizar.addActionListener(e -> actualizarCliente());
        this.vista.btnEliminar.addActionListener(e -> eliminarCliente());
        this.vista.btnBuscar.addActionListener(e -> buscarCliente());
        this.vista.btnVolver.addActionListener(e -> vista.dispose());
        this.vista.tablaClientes.getSelectionModel().addListSelectionListener(e -> cargarSeleccionado());
        visualizarClientes();
    }

    private int calcularSiguienteId() {
        int max = 0;
        for (Cliente c : DatosMemoria.listaClientes) {
            if (c.getId() > max) max = c.getId();
        }
        return max + 1;
    }

    private void registrarCliente() {
        String nombre = vista.txtNombre.getText().trim();
        String correo = vista.txtCorreo.getText().trim();
        String telefono = vista.txtTelefono.getText().trim();
        String direccion = vista.txtDireccion.getText().trim();

        if (nombre.isEmpty() || telefono.isEmpty() || direccion.isEmpty()) {
            mostrarMensaje("ERROR: TODOS LOS CAMPOS SON OBLIGATORIOS.");
            return;
        }

        if (!telefono.matches("\\d{10}")) {
            mostrarMensaje("ERROR: TELÉFONO INVÁLIDO.");
            return;
        }

        Cliente nuevo = new Cliente(idActual++, nombre, correo, telefono, direccion);
        DatosMemoria.listaClientes.add(nuevo);
        mostrarMensaje("Cliente registrado exitosamente.");
        limpiarCampos();
        visualizarClientes();
    }

    private void actualizarCliente() {
        int fila = vista.tablaClientes.getSelectedRow();
        if (fila == -1) {
            mostrarMensaje("ERROR: DEBE SELECCIONAR UN CLIENTE PARA MODIFICAR.");
            return;
        }

        String nombre = vista.txtNombre.getText().trim();
        String correo = vista.txtCorreo.getText().trim();
        String telefono = vista.txtTelefono.getText().trim();
        String direccion = vista.txtDireccion.getText().trim();

        if (nombre.isEmpty() || telefono.isEmpty() || direccion.isEmpty()) {
            mostrarMensaje("ERROR: TODOS LOS CAMPOS SON OBLIGATORIOS.");
            return;
        }

        if (!telefono.matches("\\d{10}")) {
            mostrarMensaje("ERROR: TELÉFONO INVÁLIDO.");
            return;
        }

        try {
            Cliente cliente = DatosMemoria.listaClientes.get(fila);
            cliente.setNombre(nombre);
            cliente.setCorreo(correo);
            cliente.setTelefono(telefono);
            cliente.setDireccion(direccion);
            mostrarMensaje("Cliente actualizado correctamente.");
            visualizarClientes();
            limpiarCampos();
        } catch (Exception e) {
            mostrarMensaje("ERROR: CLIENTE NO ENCONTRADO PARA ACTUALIZAR.");
        }
    }

    private void eliminarCliente() {
        int fila = vista.tablaClientes.getSelectedRow();
        if (fila == -1) {
            mostrarMensaje("ERROR: DEBE SELECCIONAR UN CLIENTE PARA ELIMINAR.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar este cliente?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        try {
            Cliente cliente = DatosMemoria.listaClientes.get(fila);
            int clienteId = cliente.getId();

            // Eliminar pedidos del cliente
            DatosMemoria.listaPedidos.removeIf(p -> p.getCliente_id() == clienteId);

            // Eliminar facturas asociadas a pedidos del cliente
            DatosMemoria.listaFacturas.removeIf(f -> {
                for (Pedido p : DatosMemoria.listaPedidos) {
                    if (p.getId() == f.getPedido_id() && p.getCliente_id() == clienteId) {
                        return true;
                    }
                }
                return false;
            });

            DatosMemoria.listaClientes.remove(fila);
            mostrarMensaje("Cliente eliminado correctamente.");
            visualizarClientes();
            limpiarCampos();
        } catch (Exception e) {
            mostrarMensaje("ERROR: NO SE PUDO ELIMINAR EL CLIENTE.");
        }
    }

    private void buscarCliente() {
        String filtro = vista.txtBuscar.getText().trim().toLowerCase();
        DefaultTableModel modelo = vista.modeloClientes;
        modelo.setRowCount(0);

        for (Cliente c : DatosMemoria.listaClientes) {
            if (c.getNombre().toLowerCase().contains(filtro) || c.getTelefono().contains(filtro)) {
                modelo.addRow(new Object[]{c.getId(), c.getNombre(), c.getCorreo(), c.getTelefono(), c.getDireccion()});
            }
        }

        if (modelo.getRowCount() == 0) {
            mostrarMensaje("AVISO: NO SE ENCONTRARON RESULTADOS.");
        }
    }

    private void visualizarClientes() {  //cargarTablaClientes
        DefaultTableModel modelo = vista.modeloClientes;
        modelo.setRowCount(0);

        if (DatosMemoria.listaClientes.isEmpty()) {
            mostrarMensaje("AVISO: NO EXISTEN CLIENTES REGISTRADOS.");
            return;
        }

        for (Cliente c : DatosMemoria.listaClientes) {
            modelo.addRow(new Object[]{c.getId(), c.getNombre(), c.getCorreo(), c.getTelefono(), c.getDireccion()});
        }
    }

    private void cargarSeleccionado() {
        int fila = vista.tablaClientes.getSelectedRow();
        if (fila != -1) {
            Cliente c = DatosMemoria.listaClientes.get(fila);
            vista.txtNombre.setText(c.getNombre());
            vista.txtCorreo.setText(c.getCorreo());
            vista.txtTelefono.setText(c.getTelefono());
            vista.txtDireccion.setText(c.getDireccion());
        }
    }

    private void limpiarCampos() {
        vista.txtNombre.setText("");
        vista.txtCorreo.setText("");
        vista.txtTelefono.setText("");
        vista.txtDireccion.setText("");
        vista.txtBuscar.setText("");
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
}
