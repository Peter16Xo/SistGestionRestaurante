/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */


package controlador;

import modelo.Categoria;
import modelo.DatosMemoria;
import modelo.Platillo;
import org.junit.Before;
import org.junit.Test;
import vista.PlatilloVista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlatilloControladorTest {

    private PlatilloControlador controlador;
    private PlatilloVista vista;

    @Before
    public void setUp() {
        // Simulamos los datos de prueba
        DatosMemoria.listaCategorias = new ArrayList<>();
        DatosMemoria.listaPlatillos = new ArrayList<>();

        DatosMemoria.listaCategorias.add(new Categoria(1, "Bebidas"));
        DatosMemoria.listaCategorias.add(new Categoria(2, "Sopas"));

        // Inicializamos componentes visuales simulados
        vista = new PlatilloVista();
        vista.txtNombre = new JTextField();
        vista.txtPrecio = new JTextField();
        vista.comboCategoria = new JComboBox<>();
        vista.modeloPlatillos = new DefaultTableModel(new Object[]{"ID", "Nombre", "Precio", "Categoría"}, 0);
        vista.tablaPlatillos = new JTable(vista.modeloPlatillos);
        vista.txtBuscar = new JTextField();

        vista.btnRegistrar = new JButton();
        vista.btnActualizar = new JButton();
        vista.btnEliminar = new JButton();
        vista.btnBuscar = new JButton();
        vista.btnVolver = new JButton();

        controlador = new PlatilloControlador(vista);
    }

    // ✅ Prueba válida para registrar un platillo
    @Test
    public void testRegistrarPlatilloValido() {
        vista.txtNombre.setText("Ceviche");
        vista.txtPrecio.setText("4.5");
        vista.comboCategoria.setSelectedItem("Bebidas");

        controlador.registrarPlatillo();
        assertEquals(1, DatosMemoria.listaPlatillos.size());
    }

    // ❌ Prueba inválida: precio incorrecto
    @Test
    public void testRegistrarPlatilloConPrecioInvalido() {
        vista.txtNombre.setText("Ceviche");
        vista.txtPrecio.setText("precio");
        vista.comboCategoria.setSelectedItem("Bebidas");

        controlador.registrarPlatillo();
        assertEquals(0, DatosMemoria.listaPlatillos.size());
    }

    // ✅ Prueba válida para actualizar
    @Test
    public void testActualizarPlatilloValido() {
        DatosMemoria.listaPlatillos.add(new Platillo(1, "Sopa", 3.0, DatosMemoria.listaCategorias.get(0)));
        vista.modeloPlatillos.addRow(new Object[]{1, "Sopa", 3.0, "Bebidas"});

        vista.tablaPlatillos.setRowSelectionInterval(0, 0);
        vista.txtNombre.setText("Sopa de mariscos");
        vista.txtPrecio.setText("4.0");
        vista.comboCategoria.setSelectedItem("Bebidas");

        controlador.actualizarPlatillo();
        assertEquals("Sopa de mariscos", DatosMemoria.listaPlatillos.get(0).getNombre());
    }

    // ❌ Prueba inválida: sin selección
    @Test
    public void testActualizarPlatilloSinSeleccion() {
        controlador.actualizarPlatillo(); // No hay selección
        assertTrue(true); // Solo se verifica que no explota
    }

    // ✅ Prueba válida para eliminar
    @Test
    public void testEliminarPlatilloValido() {
        DatosMemoria.listaPlatillos.add(new Platillo(1, "Tamal", 2.0, DatosMemoria.listaCategorias.get(0)));
        vista.modeloPlatillos.addRow(new Object[]{1, "Tamal", 2.0, "Bebidas"});
        vista.tablaPlatillos.setRowSelectionInterval(0, 0);

        JOptionPane.setDefaultLocale(java.util.Locale.ENGLISH);
        controlador.eliminarPlatillo(); // Simula eliminación con YES
        assertEquals(0, DatosMemoria.listaPlatillos.size());
    }

    // ❌ Prueba inválida: no hay selección
    @Test
    public void testEliminarPlatilloSinSeleccion() {
        controlador.eliminarPlatillo();
        assertTrue(true); // Se espera que maneje el error sin explotar
    }

    // ✅ Prueba válida: cargar seleccionado
    @Test
    public void testCargarSeleccionado() {
        Platillo p = new Platillo(1, "Tigrillo", 3.5, DatosMemoria.listaCategorias.get(0));
        DatosMemoria.listaPlatillos.add(p);
        vista.modeloPlatillos.addRow(new Object[]{1, "Tigrillo", 3.5, "Bebidas"});
        vista.tablaPlatillos.setRowSelectionInterval(0, 0);

        controlador.cargarSeleccionado();
        assertEquals("Tigrillo", vista.txtNombre.getText());
    }

    // ❌ Prueba inválida: fila no seleccionada
    @Test
    public void testCargarSeleccionadoSinFila() {
        controlador.cargarSeleccionado(); // No hay fila
        assertTrue(true); // Debe ejecutarse sin error
    }

    // ✅ Prueba válida: visualizar platillos
    @Test
    public void testVisualizarPlatillos() {
        DatosMemoria.listaPlatillos.add(new Platillo(1, "Bolón", 1.5, DatosMemoria.listaCategorias.get(0)));
        controlador.visualizarPlatillos();
        assertEquals(1, vista.modeloPlatillos.getRowCount());
    }
    // ✅ Prueba válida: categoría exacta
@Test
public void testBuscarCategoriaPorNombreExistente() {
    Categoria c = controlador.buscarCategoriaPorNombre("Bebidas");
    assertNotNull(c);
    assertEquals("Bebidas", c.getNombre());
}

// ✅ Prueba válida: categoría escrita en minúscula
@Test
public void testBuscarCategoriaPorNombreMinuscula() {
    Categoria c = controlador.buscarCategoriaPorNombre("sopas");
    assertNotNull(c);
    assertEquals("Sopas", c.getNombre());
}

// ❌ Prueba inválida: categoría inexistente
@Test
public void testBuscarCategoriaPorNombreInexistente() {
    Categoria c = controlador.buscarCategoriaPorNombre("Carnes");
    assertNull(c); // No existe
}

}
