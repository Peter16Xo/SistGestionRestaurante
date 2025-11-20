/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.JerarquiaPlatillo;

/**
 *
 * @author Pedro
 */
public class PlatilloGourmetDriver extends PlatilloGourmet {

    public PlatilloGourmetDriver(String nombre, double precio, String tipo, boolean decorado) {
        super(nombre, precio, tipo, decorado);
    }

    // Método de prueba 1: validar que el precio con IVA sea correcto
    public void testPrecioConIVA() {
        double esperado = getPrecio() * 1.12;
        double real = getPrecioConIVA();
        System.out.println("Test Precio con IVA: " + (esperado == real ? " PASO" : " FALLO"));
    }

    // Método de prueba 2: validar descripción básica
    public void testDescripcion() {
        String desc = getDescripcion();
        System.out.println("Test Descripcion: " + desc);
    }

    // Método de prueba 3: validar descripción completa
    public void testDescripcionCompleta() {
        String resultado = descripcionCompleta();
        System.out.println("Test Descripcion Completa: " + resultado);
    }

    // Método para ejecutar todos los test
    public void ejecutarTests() {
        System.out.println("===== INICIO TEST DRIVER =====");
        testPrecioConIVA();
        testDescripcion();
        testDescripcionCompleta();
        System.out.println("===== FIN TEST DRIVER =====");
    }
}
