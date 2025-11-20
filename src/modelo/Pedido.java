/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDateTime;

public class Pedido {
    private int id;
    private int cliente_id;
    private int empleado_id;
    private LocalDateTime fecha_hora;
    private double subtotal;

    public Pedido(int id, int cliente_id, int empleado_id, LocalDateTime fecha_hora, double subtotal) {
        this.id = id;
        this.cliente_id = cliente_id;
        this.empleado_id = empleado_id;
        this.fecha_hora = fecha_hora;
        this.subtotal = subtotal;
    }

    public int getId() {
        return id;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public int getEmpleado_id() {
        return empleado_id;
    }

    public LocalDateTime getFecha_hora() {
        return fecha_hora;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public void setEmpleado_id(int empleado_id) {
        this.empleado_id = empleado_id;
    }

    public void setFecha_hora(LocalDateTime fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public Object getFecha() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
