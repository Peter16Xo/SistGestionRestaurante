/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.*;
import vista.ReporteVista;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class ReporteControlador {

    private ReporteVista vista;

    public ReporteControlador(ReporteVista vista) {
        this.vista = vista;

        vista.btnGenerarDiario.addActionListener(e -> generarReporteDiario());
        vista.btnFiltrarFecha.addActionListener(e -> filtrarPorFecha());
        vista.btnVolver.addActionListener(e -> vista.dispose());
    }

    private void generarReporteDiario() {
        LocalDate hoy = LocalDate.now();
        List<Factura> facturasHoy = DatosMemoria.listaFacturas.stream()
                .filter(f -> f.getFecha_hora() != null && f.getFecha_hora().toLocalDate().equals(hoy))
                .collect(Collectors.toList());

        if (facturasHoy.isEmpty()) {
            vista.areaResultado.setText("AVISO: NO EXISTEN DATOS PARA GENERAR EL REPORTE DIARIO.");
            return;
        }

        int totalPedidos = facturasHoy.size();
        double totalFacturado = facturasHoy.stream().mapToDouble(Factura::getTotal).sum();

        Map<Integer, Double> consumoPorCliente = new HashMap<>();
        for (Factura f : facturasHoy) {
            Pedido pedido = DatosMemoria.listaPedidos.stream()
                    .filter(p -> p.getId() == f.getPedido_id())
                    .findFirst().orElse(null);
            if (pedido != null) {
                consumoPorCliente.put(pedido.getCliente_id(),
                        consumoPorCliente.getOrDefault(pedido.getCliente_id(), 0.0) + f.getTotal());
            }
        }

        Optional<Map.Entry<Integer, Double>> clienteTop = consumoPorCliente.entrySet().stream()
                .max(Map.Entry.comparingByValue());

        String nombreClienteTop = clienteTop.map(entry -> DatosMemoria.listaClientes.stream()
                .filter(c -> c.getId() == entry.getKey())
                .map(Cliente::getNombre)
                .findFirst().orElse("Desconocido")).orElse("No disponible");

        StringBuilder sb = new StringBuilder();
        sb.append("REPORTE DIARIO - ").append(hoy.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).append("\n\n");
        sb.append("Total de Facturas: ").append(totalPedidos).append("\n");
        sb.append("Total Facturado: $").append(String.format("%.2f", totalFacturado)).append("\n");
        sb.append("Cliente con mayor consumo: ").append(nombreClienteTop).append("\n");

        vista.areaResultado.setText(sb.toString());
    }

    private void filtrarPorFecha() {
        String fechaTexto = vista.txtFecha.getText().trim();

        try {
            LocalDate fecha = LocalDate.parse(fechaTexto);
            List<Factura> facturas = DatosMemoria.listaFacturas.stream()
                    .filter(f -> f.getFecha_hora() != null && f.getFecha_hora().toLocalDate().equals(fecha))
                    .collect(Collectors.toList());

            if (facturas.isEmpty()) {
                vista.areaResultado.setText("AVISO: NO SE ENCONTRARON DATOS PARA ESA FECHA.");
                return;
            }

            double totalFacturado = facturas.stream().mapToDouble(Factura::getTotal).sum();
            int totalPedidos = facturas.size();

            Map<Integer, Double> consumoPorCliente = new HashMap<>();
            for (Factura f : facturas) {
                Pedido pedido = DatosMemoria.listaPedidos.stream()
                        .filter(p -> p.getId() == f.getPedido_id())
                        .findFirst().orElse(null);
                if (pedido != null) {
                    consumoPorCliente.put(pedido.getCliente_id(),
                            consumoPorCliente.getOrDefault(pedido.getCliente_id(), 0.0) + f.getTotal());
                }
            }

            Optional<Map.Entry<Integer, Double>> clienteTop = consumoPorCliente.entrySet().stream()
                    .max(Map.Entry.comparingByValue());

            String nombreClienteTop = clienteTop.map(entry -> DatosMemoria.listaClientes.stream()
                    .filter(c -> c.getId() == entry.getKey())
                    .map(Cliente::getNombre)
                    .findFirst().orElse("Desconocido")).orElse("No disponible");

            StringBuilder sb = new StringBuilder();
            sb.append("REPORTE FILTRADO - ").append(fechaTexto).append("\n\n");
            sb.append("Total de Facturas: ").append(totalPedidos).append("\n");
            sb.append("Total Facturado: $").append(String.format("%.2f", totalFacturado)).append("\n");
            sb.append("Cliente con mayor consumo: ").append(nombreClienteTop).append("\n");

            vista.areaResultado.setText(sb.toString());

        } catch (Exception e) {
            vista.areaResultado.setText("ERROR: FORMATO DE FECHA INVÁLIDO. USE yyyy-MM-dd.");
        }
    }
}
