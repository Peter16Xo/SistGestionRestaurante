/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

public class PedidoPlatillo {
    private int pedido_id;
    private int platillo_id;
    private int cantidad;
    private double precio_unitario;

    public PedidoPlatillo(int pedido_id, int platillo_id, int cantidad, double precio_unitario) {
        this.pedido_id = pedido_id;
        this.platillo_id = platillo_id;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
    }

    public int getPedido_id() {
        return pedido_id;
    }

    public void setPedido_id(int pedido_id) {
        this.pedido_id = pedido_id;
    }

    public int getPlatillo_id() {
        return platillo_id;
    }

    public void setPlatillo_id(int platillo_id) {
        this.platillo_id = platillo_id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }
}
