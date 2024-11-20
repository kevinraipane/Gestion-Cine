package Menu;

import Gestores.Personas.GestorClientes;
import Gestores.Personas.GestorUser;
import Modelos.Personas.Cliente;

import java.util.Scanner;

public class MenuClientes {

    private int opcion;

    public void crearCliente(GestorClientes gestorClientes, GestorUser gestorUser, Scanner scanner) {
        Cliente nuevoCliente = gestorClientes.cargarNuevoCliente();
        gestorClientes.agregarNuevoCliente(nuevoCliente);
        System.out.println("Cliente creado: " + nuevoCliente);

        boolean usuarioCreado = false;
        do{
            // Crear un usuario automáticamente para el cliente
            System.out.println("Ahora debe crear un usuario para este cliente.");
            System.out.print("Ingrese el nombre de usuario: ");
            String username = scanner.nextLine();
            System.out.print("Ingrese la contraseña: ");
            String password = scanner.nextLine();

            try {
                gestorUser.crearUsuario(username, password);
                System.out.println("Usuario creado exitosamente para el cliente.");
                usuarioCreado = true;
            } catch (Exception e) {
                System.out.println("Error al crear el usuario: " + e.getMessage());
                System.out.println("Intentelo nuevamente.");
            }
        } while (!usuarioCreado);
    }

    public void buscarCliente(GestorClientes gestorClientes) {
        System.out.print("Ingrese el DNI del cliente: ");
        String dni = gestorClientes.leerDniCliente();
        try {
            System.out.println(gestorClientes.buscarClientePorDNI(dni));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void eliminarCliente(Scanner scanner, GestorClientes gestorClientes) {
        System.out.print("Ingrese el DNI del cliente a eliminar: ");
        String dni = gestorClientes.leerDniCliente();

        try {
            gestorClientes.eliminarCliente(dni);
            System.out.println("Cliente eliminado.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void menuClientes(GestorClientes gestorClientes, GestorUser gestorUser, Scanner scanner) {
        boolean regresar = false;

        while (!regresar) {
            System.out.println("\n--- GESTIÓN DE CLIENTES ---");
            System.out.println("1. Crear Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Buscar Cliente por DNI");
            System.out.println("4. Eliminar Cliente");
            System.out.println("0. Regresar");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    crearCliente(gestorClientes, gestorUser, scanner);
                    break;
                case 2:
                    gestorClientes.listarClientes();
                    break;
                case 3:
                    buscarCliente(gestorClientes);
                    break;
                case 4:
                    eliminarCliente(scanner, gestorClientes);
                    break;

                case 0:
                    regresar = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}
