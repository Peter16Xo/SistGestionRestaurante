/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Proveedor;
import modelo.DatosMemoria;
import vista.ProveedorVistaCocina;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ProveedorControladorCocina {
    private ProveedorVistaCocina vista;

    public ProveedorControladorCocina(ProveedorVistaCocina vista) {
        this.vista = vista;
        this.vista.btnActualizar.addActionListener(e -> actualizarProveedor());
        this.vista.btnBuscar.addActionListener(e -> filtrarProveedores());
        this.vista.btnVolver.addActionListener(e -> vista.dispose());
        this.vista.tablaProveedores.getSelectionModel().addListSelectionListener(e -> cargarDatosSeleccionados());
        cargarTablaProveedores();
    }

    private void actualizarProveedor() {
        int fila = vista.tablaProveedores.getSelectedRow();
        if (fila == -1) {
            mostrarMensaje("ERROR: SELECCIONE UN PROVEEDOR.");
            return;
        }

        String nombre = vista.txtNombre.getText().trim();
        String contacto = vista.txtContacto.getText().trim();
        String telefono = vista.txtTelefono.getText().trim();
        String direccion = vista.txtDireccion.getText().trim();
        String email = vista.txtEmail.getText().trim();

        if (nombre.isEmpty() || contacto.isEmpty() || telefono.isEmpty() || direccion.isEmpty() || email.isEmpty()) {
            mostrarMensaje("ERROR: DATOS INVÁLIDOS.");
            return;
        }

        Proveedor proveedor = DatosMemoria.listaProveedores.get(fila);

        for (Proveedor p : DatosMemoria.listaProveedores) {
            if (!p.equals(proveedor) && p.getEmail().equalsIgnoreCase(email)) {
                mostrarMensaje("ERROR: EL CORREO YA ESTÁ ASOCIADO A OTRO PROVEEDOR.");
                return;
            }
        }

        proveedor.setNombre(nombre);
        proveedor.setContacto(contacto);
        proveedor.setTelefono(telefono);
        proveedor.setDireccion(direccion);
        proveedor.setEmail(email);

        mostrarMensaje("Proveedor actualizado correctamente.");
        cargarTablaProveedores();
    }

    private void filtrarProveedores() {
        String filtro = vista.txtBuscar.getText().trim().toLowerCase();
        DefaultTableModel modelo = vista.modeloProveedores;
        modelo.setRowCount(0);

        for (Proveedor p : DatosMemoria.listaProveedores) {
            if (p.getNombre().toLowerCase().contains(filtro) || p.getContacto().toLowerCase().contains(filtro)) {
                modelo.addRow(new Object[]{p.getId(), p.getNombre(), p.getContacto(), p.getTelefono(), p.getDireccion(), p.getEmail()});
            }
        }

        if (modelo.getRowCount() == 0) {
            mostrarMensaje("AVISO: NO SE ENCONTRARON RESULTADOS.");
        }
    }

    private void cargarTablaProveedores() {
        DefaultTableModel modelo = vista.modeloProveedores;
        modelo.setRowCount(0);
        List<Proveedor> lista = DatosMemoria.listaProveedores;

        if (lista.isEmpty()) {
            mostrarMensaje("AVISO: NO EXISTEN PROVEEDORES REGISTRADOS.");
        }

        for (Proveedor p : lista) {
            modelo.addRow(new Object[]{p.getId(), p.getNombre(), p.getContacto(), p.getTelefono(), p.getDireccion(), p.getEmail()});
        }
    }

    private void cargarDatosSeleccionados() {
        int fila = vista.tablaProveedores.getSelectedRow();
        if (fila != -1) {
            Proveedor p = DatosMemoria.listaProveedores.get(fila);
            vista.txtNombre.setText(p.getNombre());
            vista.txtContacto.setText(p.getContacto());
            vista.txtTelefono.setText(p.getTelefono());
            vista.txtDireccion.setText(p.getDireccion());
            vista.txtEmail.setText(p.getEmail());
        }
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
}
