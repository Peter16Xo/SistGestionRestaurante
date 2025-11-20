/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controlador;

import modelo.DatosMemoria;
import modelo.Empleado;
import vista.EmpleadoVista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EmpleadoControlador {
    private EmpleadoVista vista;

    public EmpleadoControlador(EmpleadoVista vista) {
        this.vista = vista;
        this.vista.btnRegistrar.addActionListener(e -> registrarEmpleado());
        this.vista.btnModificar.addActionListener(e -> actualizarEmpleado());
        this.vista.btnEliminar.addActionListener(e -> eliminarEmpleado());
        this.vista.btnCambiarRol.addActionListener(e -> gestionarRoles());
        this.vista.btnBuscar.addActionListener(e -> buscarEmpleado());
        this.vista.btnVolver.addActionListener(e -> vista.dispose());
        this.vista.tablaEmpleados.getSelectionModel().addListSelectionListener(e -> cargarSeleccionado());
        visualizarEmpleados();
    }

    private void registrarEmpleado() {
        String nombre = vista.txtNombre.getText().trim();
        String usuario = vista.txtUsuario.getText().trim();
        String contrasena = vista.txtContrasena.getText().trim();
        String rol = (String) vista.comboRol.getSelectedItem();

        if (nombre.isEmpty() || usuario.isEmpty() || contrasena.isEmpty()) {
            mostrarMensaje("ERROR: TODOS LOS CAMPOS SON OBLIGATORIOS.");
            return;
        }

        if (!contrasena.matches("(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,15}")) {
            mostrarMensaje("ERROR: CONTRASEÑA DEBE TENER ENTRE 6 Y 15 CARACTERES, INCLUIR LETRAS Y NÚMEROS.");
            return;
        }

        for (Empleado emp : DatosMemoria.listaUsuarios) {
            if (emp.getUsuario().equalsIgnoreCase(usuario)) {
                mostrarMensaje("ERROR: NOMBRE DE USUARIO YA EXISTE.");
                return;
            }
        }

        DatosMemoria.listaUsuarios.add(new Empleado(nombre, usuario, contrasena, rol));
        mostrarMensaje("Empleado registrado correctamente.");
        limpiarCampos();
        visualizarEmpleados();
    }

    private void actualizarEmpleado() {
        int fila = vista.tablaEmpleados.getSelectedRow();
        if (fila == -1) {
            mostrarMensaje("ERROR: DEBE SELECCIONAR UN EMPLEADO PARA MODIFICAR.");
            return;
        }

        String nombre = vista.txtNombre.getText().trim();
        String usuario = vista.txtUsuario.getText().trim();
        String contrasena = vista.txtContrasena.getText().trim();
        String rol = (String) vista.comboRol.getSelectedItem();

        if (nombre.isEmpty() || usuario.isEmpty() || contrasena.isEmpty()) {
            mostrarMensaje("ERROR: CAMPOS INCOMPLETOS.");
            return;
        }

        if (!contrasena.matches("(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,15}")) {
            mostrarMensaje("ERROR: CONTRASEÑA INVÁLIDA.");
            return;
        }

        Empleado emp = DatosMemoria.listaUsuarios.get(fila);

        for (Empleado u : DatosMemoria.listaUsuarios) {
            if (!u.equals(emp) && u.getUsuario().equalsIgnoreCase(usuario)) {
                mostrarMensaje("ERROR: NOMBRE DE USUARIO YA EXISTE.");
                return;
            }
        }

        emp.setNombre(nombre);
        emp.setUsuario(usuario);
        emp.setContrasena(contrasena);
        emp.setRol(rol);

        mostrarMensaje("Empleado actualizado exitosamente.");
        visualizarEmpleados();
        limpiarCampos();
    }

    private void eliminarEmpleado() {
        int fila = vista.tablaEmpleados.getSelectedRow();
        if (fila == -1) {
            mostrarMensaje("ERROR: DEBE SELECCIONAR UN EMPLEADO PARA ELIMINAR.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar este empleado?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            DatosMemoria.listaUsuarios.remove(fila);
            mostrarMensaje("Empleado eliminado correctamente.");
            visualizarEmpleados();
            limpiarCampos();
        }
    }

    private void gestionarRoles() {
        int fila = vista.tablaEmpleados.getSelectedRow();
        if (fila == -1) {
            mostrarMensaje("ERROR: DEBE SELECCIONAR UN EMPLEADO PARA MODIFICAR SU ROL.");
            return;
        }

        String nuevoRol = (String) vista.comboRol.getSelectedItem();
        if (!nuevoRol.equalsIgnoreCase("Mesero") && !nuevoRol.equalsIgnoreCase("Cocina") && !nuevoRol.equalsIgnoreCase("Gerente")) {
            mostrarMensaje("ERROR: ROL SELECCIONADO NO VÁLIDO.");
            return;
        }

        Empleado emp = DatosMemoria.listaUsuarios.get(fila);
        emp.setRol(nuevoRol);
        mostrarMensaje("Rol actualizado correctamente.");
        visualizarEmpleados();
    }

    private void buscarEmpleado() {
        String filtro = vista.txtBuscar.getText().trim().toLowerCase();
        DefaultTableModel modelo = vista.modeloEmpleados;
        modelo.setRowCount(0);

        for (Empleado emp : DatosMemoria.listaUsuarios) {
            if (emp.getNombre().toLowerCase().contains(filtro) || emp.getUsuario().toLowerCase().contains(filtro)) {
                modelo.addRow(new Object[]{emp.getNombre(), emp.getUsuario(), emp.getRol()});
            }
        }

        if (modelo.getRowCount() == 0) {
            mostrarMensaje("AVISO: NO SE ENCONTRARON EMPLEADOS CON LOS DATOS INGRESADOS.");
        }
    }

    private void visualizarEmpleados() { //cargarTablaEmpleados
        DefaultTableModel modelo = vista.modeloEmpleados;
        modelo.setRowCount(0);

        if (DatosMemoria.listaUsuarios.isEmpty()) {
            mostrarMensaje("AVISO: NO EXISTEN EMPLEADOS EN EL SISTEMA.");
            return;
        }

        for (Empleado emp : DatosMemoria.listaUsuarios) {
            modelo.addRow(new Object[]{emp.getNombre(), emp.getUsuario(), emp.getRol()});
        }
    }

    private void cargarSeleccionado() {
        int fila = vista.tablaEmpleados.getSelectedRow();
        if (fila != -1) {
            Empleado emp = DatosMemoria.listaUsuarios.get(fila);
            vista.txtNombre.setText(emp.getNombre());
            vista.txtUsuario.setText(emp.getUsuario());
            vista.txtContrasena.setText(emp.getContrasena());
            vista.comboRol.setSelectedItem(emp.getRol());
        }
    }

    private void limpiarCampos() {
        vista.txtNombre.setText("");
        vista.txtUsuario.setText("");
        vista.txtContrasena.setText("");
        vista.comboRol.setSelectedIndex(0);
        vista.txtBuscar.setText("");
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
}
