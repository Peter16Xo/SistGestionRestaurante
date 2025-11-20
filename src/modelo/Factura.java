/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDateTime;

public class Factura {
    private int id;
    private int pedido_id;
    private int empleado_id;
    private double subtotal;
    private double iva;
    private double total;
    private LocalDateTime fecha_hora; // ✅ NUEVO

    public Factura(int id, int pedido_id, int empleado_id, double subtotal, double iva, double total, LocalDateTime fecha_hora) {
        this.id = id;
        this.pedido_id = pedido_id;
        this.empleado_id = empleado_id;
        this.subtotal = subtotal;
        this.iva = iva;
        this.total = total;
        this.fecha_hora = fecha_hora; // ✅
    }

    public int getId() {
        return id;
    }

    public int getPedido_id() {
        return pedido_id;
    }

    public int getEmpleado_id() {
        return empleado_id;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getIva() {
        return iva;
    }

    public double getTotal() {
        return total;
    }

    public LocalDateTime getFecha_hora() {
        return fecha_hora; 
    }

    public void setFecha_hora(LocalDateTime fecha_hora) {
        this.fecha_hora = fecha_hora; 
    }
}
