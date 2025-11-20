/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.JerarquiaPlatillo;

/**
 *
 * @author Pedro
 */
// Nivel 2 - Subclase
public class Platillo extends Producto {
    protected String tipo;

    public Platillo(String nombre, double precio, String tipo) {
        super(nombre, precio);
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return nombre + " (" + tipo + ")";
    }
}
