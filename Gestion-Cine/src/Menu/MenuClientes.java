package Menu;

import Excepciones.DniInexistenteException;
import Gestores.Personas.GestorClientes;
import Gestores.Personas.GestorUser;
import Modelos.Personas.Cliente;

import java.util.Scanner;

public class MenuClientes {

    private int opcion;

    public void crearCliente(GestorClientes gestorClientes, GestorUser gestorUser) {
        Cliente nuevoCliente = gestorClientes.cargarNuevoCliente();
        gestorClientes.agregarNuevoCliente(nuevoCliente);
        System.out.println("Cliente creado: " + nuevoCliente);

        boolean usuarioCreado = false;
        do {
            // Crear un usuario automáticamente para el cliente
            System.out.println("Ahora debe crear un usuario para este cliente.");

            String username = gestorUser.capturarUsername();
            String password = gestorUser.capturarPassword();

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
        try {
            String dni = gestorClientes.leerDniCliente();
            System.out.println(gestorClientes.buscarClientePorDNI(dni));

        } catch (DniInexistenteException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void eliminarCliente(GestorClientes gestorClientes) {
        String dni = gestorClientes.leerDniCliente();

        try {
            gestorClientes.eliminarCliente(dni);
            System.out.println("Cliente eliminado.");

        } catch (DniInexistenteException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void modificarCliente(GestorClientes gestorClientes) {
        try {
            String dni = gestorClientes.leerDniCliente();
            Cliente clienteModificado = gestorClientes.modificarCliente(dni);
            gestorClientes.reemplazarCliente(dni, clienteModificado);

        } catch (DniInexistenteException e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }

    public void menuClientes(GestorClientes gestorClientes, GestorUser gestorUser, Scanner scanner) {
        boolean regresar = false;
        int opcion = -1;

        while (!regresar) {
            do {
                System.out.println("\n--- GESTIÓN DE CLIENTES ---");
                System.out.println("1. Crear Cliente");
                System.out.println("2. Listar Clientes");
                System.out.println("3. Buscar Cliente por DNI");
                System.out.println("4. Eliminar Cliente");
                System.out.println("5. Modificar Cliente");

                System.out.println("0. Regresar");
                System.out.print("Seleccione una opción: ");

                opcion = scanner.nextInt();
                scanner.nextLine();
            } while (opcion < 0 || opcion > 4);

            switch (opcion) {
                case 1:
                    crearCliente(gestorClientes, gestorUser);
                    break;
                case 2:
                    gestorClientes.listarClientes();
                    break;
                case 3:
                    buscarCliente(gestorClientes);
                    break;
                case 4:
                    eliminarCliente(gestorClientes);
                    break;
                case 5:
                    modificarCliente(gestorClientes);
                case 0:
                    regresar = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}
