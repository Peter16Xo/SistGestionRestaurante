/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.JerarquiaPlatillo;

/**
 *
 * @author Pedro
 */
public class PlatilloGourmetAplanado {
    // Atributos heredados desde Producto
    protected String nombre;
    protected double precio;

    // Atributo heredado desde Platillo
    protected String tipo;

    // Atributo propio de PlatilloGourmet
    private boolean conDecoracion;

    // Constructor que incluye toda la jerarquía
    public PlatilloGourmetAplanado(String nombre, double precio, String tipo, boolean conDecoracion) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
        this.conDecoracion = conDecoracion;
    }

    // Método heredado desde Producto
    public double getPrecioConIVA() {
        return precio * 1.12;
    }

    // Método heredado desde Platillo
    public String getDescripcion() {
        return nombre + " (" + tipo + ")";
    }

    // Método propio de PlatilloGourmet
    public String descripcionCompleta() {
        return getDescripcion() + (conDecoracion ? " decorado" : " simple");
    }

    // Métodos get/set opcionales para testing si se desea acceso externo
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public String getTipo() { return tipo; }
    public boolean isConDecoracion() { return conDecoracion; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPrecio(double precio) { this.precio = precio; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setConDecoracion(boolean conDecoracion) { this.conDecoracion = conDecoracion; }
}
