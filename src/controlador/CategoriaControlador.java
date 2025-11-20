/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controlador;

import modelo.Categoria;
import modelo.DatosMemoria;
import modelo.Platillo;
import vista.CategoriaVista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class CategoriaControlador {
    private CategoriaVista vista;
    private int idActual;

    public CategoriaControlador(CategoriaVista vista) {
        this.vista = vista;
        this.idActual = calcularSiguienteId();
        this.vista.btnRegistrar.addActionListener(e -> registrarCategoria());
        this.vista.btnActualizar.addActionListener(e -> actualizarCategoria());
        this.vista.btnEliminar.addActionListener(e -> eliminarCategoria());
        this.vista.btnBuscar.addActionListener(e -> buscarCategorias());
        this.vista.btnVolver.addActionListener(e -> vista.dispose());
        this.vista.tablaCategorias.getSelectionModel().addListSelectionListener(e -> cargarSeleccionado());
        visualizarCategorias();
    }

    private int calcularSiguienteId() {
        int max = 0;
        for (Categoria c : DatosMemoria.listaCategorias) {
            if (c.getId() > max) max = c.getId();
        }
        return max + 1;
    }

    private void registrarCategoria() {
        String nombre = vista.txtNombre.getText().trim();
        if (nombre.isEmpty()) {
            mostrarMensaje("ERROR: EL NOMBRE DE LA CATEGORÍA ES OBLIGATORIO.");
            return;
        }

        Categoria nueva = new Categoria(idActual++, nombre);
        DatosMemoria.listaCategorias.add(nueva);
        mostrarMensaje("Categoría registrada exitosamente.");
        limpiarCampos();
        visualizarCategorias();
    }

    private void actualizarCategoria() {
        int fila = vista.tablaCategorias.getSelectedRow();
        String nuevoNombre = vista.txtNombre.getText().trim();

        if (fila == -1 || nuevoNombre.isEmpty()) {
            mostrarMensaje("ERROR: SELECCIONE UNA CATEGORÍA Y ESCRIBA UN NUEVO NOMBRE.");
            return;
        }

        try {
            Categoria categoria = DatosMemoria.listaCategorias.get(fila);
            categoria.setNombre(nuevoNombre);
            mostrarMensaje("Categoría actualizada correctamente.");
            visualizarCategorias();
            limpiarCampos();
        } catch (Exception e) {
            mostrarMensaje("ERROR: NO SE PUDO MODIFICAR LA CATEGORÍA.");
        }
    }

    private void eliminarCategoria() {
        int fila = vista.tablaCategorias.getSelectedRow();
        if (fila == -1) {
            mostrarMensaje("ERROR: DEBE SELECCIONAR UNA CATEGORÍA PARA ELIMINAR.");
            return;
        }

        Categoria categoria = DatosMemoria.listaCategorias.get(fila);

        boolean tienePlatillos = DatosMemoria.listaPlatillos.stream()
                .anyMatch(p -> p.getCategoria_id().getId() == categoria.getId());

        if (tienePlatillos) {
            int opcion = JOptionPane.showConfirmDialog(null,
                    "Esta categoría tiene platillos asociados. ¿Desea eliminarlos también?",
                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                DatosMemoria.listaPlatillos.removeIf(p -> p.getCategoria_id().getId() == categoria.getId());
            } else {
                return;
            }
        }

        DatosMemoria.listaCategorias.remove(fila);
        mostrarMensaje("Categoría eliminada exitosamente.");
        visualizarCategorias();
        limpiarCampos();
    }

    private void buscarCategorias() {
        String filtro = vista.txtBuscar.getText().trim().toLowerCase();
        DefaultTableModel modelo = vista.modeloCategorias;
        modelo.setRowCount(0);

        for (Categoria c : DatosMemoria.listaCategorias) {
            if (c.getNombre().toLowerCase().contains(filtro)) {
                modelo.addRow(new Object[]{c.getId(), c.getNombre()});
            }
        }

        if (modelo.getRowCount() == 0) {
            mostrarMensaje("AVISO: NO SE ENCONTRARON CATEGORÍAS EN EL SISTEMA.");
        }
    }

    private void visualizarCategorias() { //cargarTablaCategorias
        DefaultTableModel modelo = vista.modeloCategorias;
        modelo.setRowCount(0);

        for (Categoria c : DatosMemoria.listaCategorias) {
            modelo.addRow(new Object[]{c.getId(), c.getNombre()});
        }
    }

    private void cargarSeleccionado() {
        int fila = vista.tablaCategorias.getSelectedRow();
        if (fila != -1) {
            Categoria c = DatosMemoria.listaCategorias.get(fila);
            vista.txtNombre.setText(c.getNombre());
        }
    }

    private void limpiarCampos() {
        vista.txtNombre.setText("");
        vista.txtBuscar.setText("");
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
}
