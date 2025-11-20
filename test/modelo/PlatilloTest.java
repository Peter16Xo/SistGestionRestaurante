/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package modelo;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

// Prueba final de Webhook para la Fase 3 - Intento 3
public class PlatilloTest {

    @Test
    public void testCreacionPlatillo() {
        // 1. Preparacion: Creamos una categoria ficticia
        Categoria categoria = new Categoria(1, "Bebidas");
        
        // 2. Ejecucion: Creamos un platillo
        Platillo p = new Platillo(10, "Jugo Natural", 2.50, categoria);
        
        // 3. Verificacion (Asserts): Comprobamos que guardo bien los datos
        assertEquals("El ID debe ser 10", 10, p.getId());
        assertEquals("El nombre debe ser Jugo Natural", "Jugo Natural", p.getNombre());
        assertEquals("El precio debe ser 2.50", 2.50, p.getPrecio(), 0.01);
        assertEquals("La categoria debe ser Bebidas", "Bebidas", p.getCategoria_id().getNombre());
    }
    
    @Test
    public void testModificarPrecio() {
        Categoria categoria = new Categoria(2, "Postres");
        Platillo p = new Platillo(20, "Helado", 1.00, categoria);
        
        // Cambiamos el precio
        p.setPrecio(1.50);
        
        assertEquals(1.50, p.getPrecio(), 0.01);
    }
}