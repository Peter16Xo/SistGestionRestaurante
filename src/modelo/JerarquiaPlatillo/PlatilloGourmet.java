/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.JerarquiaPlatillo;

/**
 *
 * @author Pedro
 */
// Nivel 3 - Sub-subclase
public class PlatilloGourmet extends Platillo {
    private boolean conDecoracion;

    public PlatilloGourmet(String nombre, double precio, String tipo, boolean conDecoracion) {
        super(nombre, precio, tipo);
        this.conDecoracion = conDecoracion;
    }

    public String descripcionCompleta() {
        return getDescripcion() + (conDecoracion ? " decorado" : " simple");
    }
    
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public String getTipo() { return tipo; }
    public boolean isConDecoracion() { return conDecoracion; }

}
