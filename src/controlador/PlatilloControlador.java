/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controlador;

import modelo.Categoria;
import modelo.DatosMemoria;
import modelo.Platillo;
import vista.PlatilloVista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PlatilloControlador {
    private PlatilloVista vista;
    private int idActual;

    public PlatilloControlador(PlatilloVista vista) {
        this.vista = vista;
        this.idActual = calcularSiguienteId();
        this.vista.btnRegistrar.addActionListener(e -> registrarPlatillo());
        this.vista.btnActualizar.addActionListener(e -> actualizarPlatillo());
        this.vista.btnEliminar.addActionListener(e -> eliminarPlatillo());
        this.vista.btnBuscar.addActionListener(e -> buscarPlatillos());
        this.vista.btnVolver.addActionListener(e -> vista.dispose());
        this.vista.tablaPlatillos.getSelectionModel().addListSelectionListener(e -> cargarSeleccionado());
        cargarCategorias();
        visualizarPlatillos();
    }

    public int calcularSiguienteId() {
        int max = 0;
        for (Platillo p : DatosMemoria.listaPlatillos) {
            if (p.getId() > max) max = p.getId();
        }
        return max + 1;
    }

    public void registrarPlatillo() {
        String nombre = vista.txtNombre.getText().trim();
        String precioTexto = vista.txtPrecio.getText().trim();
        String categoriaNombre = (String) vista.comboCategoria.getSelectedItem();

        if (nombre.isEmpty() || precioTexto.isEmpty() || categoriaNombre == null) {
            mostrarMensaje("ERROR: Todos los campos son obligatorios.");
            return;
        }

        double precio;
        try {
            precio = Double.parseDouble(precioTexto);
        } catch (NumberFormatException e) {
            mostrarMensaje("ERROR: FORMATO DE PRECIO INVÁLIDO.");
            return;
        }

        Categoria categoria = buscarCategoriaPorNombre(categoriaNombre);
        if (categoria == null) {
            mostrarMensaje("ERROR: La categoría seleccionada no existe.");
            return;
        }

        Platillo nuevo = new Platillo(idActual++, nombre, precio, categoria);
        DatosMemoria.listaPlatillos.add(nuevo);
        mostrarMensaje("Platillo registrado exitosamente.");
        limpiarCampos();
        visualizarPlatillos();
    }

    public void actualizarPlatillo() {
        int fila = vista.tablaPlatillos.getSelectedRow();
        if (fila == -1) {
            mostrarMensaje("ERROR: Debe seleccionar un platillo para modificar.");
            return;
        }

        String nombre = vista.txtNombre.getText().trim();
        String precioTexto = vista.txtPrecio.getText().trim();
        String categoriaNombre = (String) vista.comboCategoria.getSelectedItem();

        if (nombre.isEmpty() || precioTexto.isEmpty() || categoriaNombre == null) {
            mostrarMensaje("ERROR: Todos los campos son obligatorios.");
            return;
        }

        double precio;
        try {
            precio = Double.parseDouble(precioTexto);
        } catch (NumberFormatException e) {
            mostrarMensaje("ERROR: Formato de precio inválido.");
            return;
        }

        Categoria categoria = buscarCategoriaPorNombre(categoriaNombre);
        if (categoria == null) {
            mostrarMensaje("ERROR: La categoría seleccionada no existe.");
            return;
        }

        Platillo platillo = DatosMemoria.listaPlatillos.get(fila);
        platillo.setNombre(nombre);
        platillo.setPrecio(precio);
        platillo.setCategoria_id(categoria);

        mostrarMensaje("Platillo actualizado correctamente.");
        limpiarCampos();
        visualizarPlatillos();
    }

    public void eliminarPlatillo() {
        int fila = vista.tablaPlatillos.getSelectedRow();
        if (fila == -1) {
            mostrarMensaje("ERROR: DEBE SELECCIONAR UN PLATILLO PARA ELIMINAR.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar este platillo?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                DatosMemoria.listaPlatillos.remove(fila);
                mostrarMensaje("Platillo eliminado correctamente.");
                visualizarPlatillos();
                limpiarCampos();
            } catch (Exception e) {
                mostrarMensaje("ERROR: No se pudo eliminar el platillo.");
            }
        }
    }

    private void buscarPlatillos() {
        String filtro = vista.txtBuscar.getText().trim().toLowerCase();
        DefaultTableModel modelo = vista.modeloPlatillos;
        modelo.setRowCount(0);

        for (Platillo p : DatosMemoria.listaPlatillos) {
            String nombreCategoria = p.getCategoria_id().getNombre();
            if (nombreCategoria.toLowerCase().contains(filtro)) {
                modelo.addRow(new Object[]{p.getId(), p.getNombre(), p.getPrecio(), nombreCategoria});
            }
        }

        if (modelo.getRowCount() == 0) {
            mostrarMensaje("AVISO: NO HAY PLATILLOS DISPONIBLES EN EL SISTEMA.");
        }
    }

    private void cargarCategorias() {
        vista.comboCategoria.removeAllItems();
            vista.comboCategoria.addItem(""); 
        for (Categoria c : DatosMemoria.listaCategorias) {
            vista.comboCategoria.addItem(c.getNombre());
        }
    }

    public void visualizarPlatillos() { //cargarTablaPlatillos
        DefaultTableModel modelo = vista.modeloPlatillos;
        modelo.setRowCount(0);

        for (Platillo p : DatosMemoria.listaPlatillos) {
            String categoria = p.getCategoria_id().getNombre();
            modelo.addRow(new Object[]{p.getId(), p.getNombre(), p.getPrecio(), categoria});
        }
    }

    public void cargarSeleccionado() {
        int fila = vista.tablaPlatillos.getSelectedRow();
        if (fila != -1) {
            Platillo p = DatosMemoria.listaPlatillos.get(fila);
            vista.txtNombre.setText(p.getNombre());
            vista.txtPrecio.setText(String.valueOf(p.getPrecio()));
            vista.comboCategoria.setSelectedItem(p.getCategoria_id().getNombre());
        }
    }

    public Categoria buscarCategoriaPorNombre(String nombre) {
        for (Categoria c : DatosMemoria.listaCategorias) {
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                return c;
            }
        }
        return null;
    }

    private void limpiarCampos() {
        vista.txtNombre.setText("");
        vista.txtPrecio.setText("");
        vista.comboCategoria.setSelectedIndex(0);
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
}
