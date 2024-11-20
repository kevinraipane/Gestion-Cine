package Menu;

import Excepciones.DniInexistenteException;
import Gestores.Funcionales.GestorConsola;
import Gestores.Personas.GestorClientes;
import Gestores.Personas.GestorUser;
import Modelos.Personas.Cliente;

import java.util.Scanner;

public class MenuClientes {

    public void crearCliente(GestorClientes gestorClientes, GestorUser gestorUser) {
        Cliente nuevoCliente = gestorClientes.cargarNuevoCliente();
        gestorClientes.agregarNuevoCliente(nuevoCliente);
        System.out.println("Cliente creado: " + nuevoCliente);

        boolean usuarioCreado = false;
        do {
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
        int opcion;

        while (!regresar) {
            do {
                System.out.print("""
                        --- GESTIÓN DE CLIENTES ---
                        
                        [1] Crear cliente
                        [2] Eliminar cliente
                        [3] Modificar cliente
                        
                        Buscar clientes
                        [2] Por DNI
                        [3] Ver todos
                        
                        [0] Volver
                        
                        Seleccione una opcion:
                        
                        """);

                opcion = scanner.nextInt();
                scanner.nextLine();
            } while (GestorConsola.perteneceAlRango(opcion, 0, 5));

            switch (opcion) {
                case 1:
                    crearCliente(gestorClientes, gestorUser);
                    break;
                case 2:
                    eliminarCliente(gestorClientes);
                    break;
                case 3:
                    modificarCliente(gestorClientes);
                    break;
                case 4:
                    buscarCliente(gestorClientes);
                    break;
                case 5:
                    gestorClientes.listarClientes();
                case 0:
                    regresar = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}
