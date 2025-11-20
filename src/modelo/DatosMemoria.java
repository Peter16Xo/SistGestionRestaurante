/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

import java.util.ArrayList;
import java.util.List;

public class DatosMemoria {

    public static List<Empleado> listaUsuarios = new ArrayList<>();
    public static List<Cliente> listaClientes = new ArrayList<>();
    public static List<Categoria> listaCategorias = new ArrayList<>();
    public static List<Platillo> listaPlatillos = new ArrayList<>();
    public static List<Pedido> listaPedidos = new ArrayList<>();
    public static List<PedidoPlatillo> listaPedidoPlatillos = new ArrayList<>();
    public static List<Factura> listaFacturas = new ArrayList<>();
    public static List<Proveedor> listaProveedores = new ArrayList<>();

    static {
        // Usuarios Empleados predefinidos
        listaUsuarios.add(new Empleado("Carlos León", "gerente", "admin123", "Gerente"));
        listaUsuarios.add(new Empleado("Laura Infante", "mesero", "mesero123", "Mesero"));
        listaUsuarios.add(new Empleado("Juan Avilés", "cocina", "cocina123", "Cocina"));

        // Clientes predefinidos
        listaClientes.add(new Cliente(1, "Pedro Pérez", "pedro@mail.com", "0960051111", "Av. Quito"));
        listaClientes.add(new Cliente(2, "Ana Torres", "ana@mail.com", "0960052222", "Cdla. Los Ceibos"));
        listaClientes.add(new Cliente(3, "Luis Gómez", "luis@mail.com", "0960053333", "Via a Daule"));

        // Categorías predefinidas
        Categoria entradas = new Categoria(1, "Entradas");
        Categoria fuertes = new Categoria(2, "Platos Fuertes");
        Categoria postres = new Categoria(3, "Postres");
        Categoria bebidas = new Categoria(4, "Bebidas");

        listaCategorias.add(entradas);
        listaCategorias.add(fuertes);
        listaCategorias.add(postres);
        listaCategorias.add(bebidas);

        // Platillos predefinidos
        listaPlatillos.add(new Platillo(1, "Empanadas de Queso", 2.00, entradas));
        listaPlatillos.add(new Platillo(2, "Arroz con Menestra y Carne", 4.50, fuertes));
        listaPlatillos.add(new Platillo(3, "Flan de Vainilla", 2.50, postres));
        listaPlatillos.add(new Platillo(4, "Jugo de Naranja", 1.50, bebidas));

        // Proveedores predefinidos para prueba
        listaProveedores.add(new Proveedor(1, "Alimentos Don Mario", "Mario Ruiz", "0987654321", "Av. Central y Bolívar", "donmario@proveedor.com"));
        listaProveedores.add(new Proveedor(2, "Carnes Finas", "Lucía Andrade", "0976543210", "Calle 10 y Rocafuerte", "carnesfinas@proveedor.com"));
        listaProveedores.add(new Proveedor(3, "Verduras Express", "Carlos Mina", "0961234567", "Via Daule km 10", "verduras@express.com"));
    }

    public static Empleado validarUsuario(String usuario, String contrasena) {
        for (Empleado u : listaUsuarios) {
            if (u.getUsuario().equalsIgnoreCase(usuario) && u.getContrasena().equals(contrasena)) {
                return u;
            }
        }
        return null;
    }
}
