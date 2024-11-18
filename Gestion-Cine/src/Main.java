import Excepciones.ColeccionVaciaException;
import Gestores.Personas.*;
import Modelos.Personas.Cliente;
import Modelos.Personas.Empleado;
import Modelos.Personas.Sesion;
import Modelos.Personas.User;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        GestorUser gestorUser = new GestorUser();
        GestorClientes gestorClientes = new GestorClientes();
        GestorEmpleados gestorEmpleados = new GestorEmpleados();
        Sesion sesion = new Sesion();
        Scanner scanner = new Scanner(System.in);

        //Iniciar sesion
        iniciarSesion(sesion, gestorUser, scanner);

        // Menú principal
        menuPrincipal(gestorUser,gestorClientes,gestorEmpleados,sesion,scanner);

        scanner.close();
    }

    private static void iniciarSesion(Sesion sesion, GestorUser gestorUser, Scanner scanner) {
        //Meter do while
        while (!sesion.haySesionActiva()) {
            System.out.println("Bienvenido. Por favor, ingrese sus credenciales.");
            System.out.print("Nombre de usuario: ");
            String username = scanner.nextLine();
            System.out.print("Contraseña: ");
            String password = scanner.nextLine();

            try {
                User usuario = gestorUser.iniciarSesion(username, password);
                sesion.iniciarSesion(usuario);
                System.out.println("Sesión iniciada como: " + usuario.getUsername());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void menuPrincipal(GestorUser gestorUser, GestorClientes gestorClientes, GestorEmpleados gestorEmpleados, Sesion sesion, Scanner scanner) {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Gestión de Usuarios");
            System.out.println("2. Gestión de Clientes");
            System.out.println("3. Gestión de Empleados");
            System.out.println("4. Modificar mi Usuario");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    menuUsuarios(gestorUser, sesion, scanner);
                    break;
                case 2:
                    menuClientes(gestorClientes, sesion, gestorUser, scanner);
                    break;
                case 3:
                    menuEmpleados(gestorEmpleados, sesion, gestorUser, scanner);
                    break;
                case 4:
                    modificarMiUsuario(gestorUser, sesion, scanner);
                    break;
                case 0:
                    salir = true;
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida, intente nuevamente.");
            }
        }
    }

    private static void menuUsuarios(GestorUser gestorUser, Sesion sesion, Scanner scanner) {
        boolean regresar = false;

        while (!regresar) {
            System.out.println("\n--- GESTIÓN DE USUARIOS ---");
            System.out.println("1. Modificar mi usuario");
            System.out.println("2. Modificar un usuario (Admin)");
            //AGREGAR A LA OPCION 2 PODER CAMBIAR EL ESTADO DEL USUARIO, ALTA O BAJA
            //SI LO DOY DE BAJA, TAMBIEN TIENE QUE
            System.out.println("3. Buscar Usuario por ID");
            System.out.println("4. Buscar Usuario por Nombre de Usuario");
            System.out.println("5. Listar todos los usuarios");
            System.out.println("0. Regresar");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    modificarMiUsuario(gestorUser, sesion, scanner);
                    break;
                case 2:
                    modificarUsuarioPorId(gestorUser, scanner);
                    break;
                case 3:
                    System.out.print("Ingrese el ID del usuario: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        System.out.println(gestorUser.buscarPorId(id));
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.print("Ingrese el nombre de usuario: ");
                    String username = scanner.nextLine();
                    try {
                        System.out.println(gestorUser.buscarPorUsername(username));
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 5:
                    try {
                        System.out.println("Usuarios cargados en el sistema: ");
                        System.out.println("--------------------------------------------------------");
                        gestorUser.listarUsuarios();
                    } catch (ColeccionVaciaException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 0:
                    regresar = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private static void menuClientes(GestorClientes gestorClientes, Sesion sesion, GestorUser gestorUser, Scanner scanner) {
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
                            gestorUser.create(username, password);
                            System.out.println("Usuario creado exitosamente para el cliente.");
                            usuarioCreado = true;
                        } catch (Exception e) {
                            System.out.println("Error al crear el usuario: " + e.getMessage());
                            System.out.println("Intentelo nuevamente.");
                        }
                    } while (!usuarioCreado);
                    break;
                case 2:
                    gestorClientes.listarClientes();
                    break;
                case 3:
                    System.out.print("Ingrese el DNI del cliente: ");
                    String dni = scanner.nextLine();
                    try {
                        System.out.println(gestorClientes.buscarClientePorDNI(dni));
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.print("Ingrese el DNI del cliente a eliminar: ");
                    dni = scanner.nextLine();
                    try {
                        gestorClientes.eliminarCliente(dni);
                        System.out.println("Cliente eliminado.");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 0:
                    regresar = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private static void menuEmpleados(GestorEmpleados gestorEmpleados, Sesion sesion, GestorUser gestorUser, Scanner scanner) {
        boolean regresar = false;

        while (!regresar) {
            System.out.println("\n--- GESTIÓN DE EMPLEADOS ---");
            System.out.println("1. Crear Empleado");
            System.out.println("2. Listar Empleados");
            System.out.println("3. Buscar Empleado por DNI");
            System.out.println("4. Eliminar Empleado");
            //FALTA METODO PARA CAMBIAR CARGO
            //FALTA METODO PARA CAMBIAR ESTADO DEL EMPLEADO
            System.out.println("0. Regresar");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    Empleado nuevoEmpleado = gestorEmpleados.cargarNuevoEmpleado();
                    gestorEmpleados.agregarNuevoEmpleado(nuevoEmpleado);
                    System.out.println("Empleado creado: " + nuevoEmpleado);

                    boolean usuarioCreado = false;
                    do{
                        System.out.println("Ahora debe crear un usuario para este cliente.");
                        System.out.print("Ingrese el nombre de usuario: ");
                        String username = scanner.nextLine();
                        System.out.print("Ingrese la contraseña: ");
                        String password = scanner.nextLine();

                        try {
                            gestorUser.create(username, password);
                            System.out.println("Usuario creado exitosamente para el cliente.");
                            usuarioCreado = true;
                        } catch (Exception e) {
                            System.out.println("Error al crear el usuario: " + e.getMessage());
                            System.out.println("Intentelo nuevamente.");
                        }
                    } while (!usuarioCreado);
                    break;
                case 2:
                    gestorEmpleados.listarEmpleados();
                    break;
                case 3:
                    System.out.print("Ingrese el DNI del empleado: ");
                    String dni = scanner.nextLine();
                    try {
                        System.out.println(gestorEmpleados.buscarEmpleadoPorDNI(dni));
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.print("Ingrese el DNI del empleado a eliminar: ");
                    dni = scanner.nextLine();
                    try {
                        gestorEmpleados.eliminarEmpleado(dni);
                        System.out.println("Empleado eliminado.");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 0:
                    regresar = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
    private static void modificarMiUsuario(GestorUser gestorUser, Sesion sesion, Scanner scanner) {
        User usuarioActual = sesion.getUsuarioActual();

        System.out.println("Modificando mi usuario:");
        System.out.println("Nombre de usuario actual: " + usuarioActual.getUsername());
        System.out.print("Ingrese el nuevo nombre de usuario: ");
        String nuevoUsername = scanner.nextLine();
        System.out.print("Ingrese la nueva contraseña: ");
        String nuevaPassword = scanner.nextLine();

        try {
            gestorUser.modificarUsuario(usuarioActual.getIdUsuario(), nuevoUsername, nuevaPassword);
            System.out.println("Usuario modificado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al modificar el usuario: " + e.getMessage());
        }
    }

    private static void modificarUsuarioPorId(GestorUser gestorUser, Scanner scanner) {
        System.out.print("Ingrese el ID del usuario que desea modificar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir salto de línea

        System.out.print("Ingrese el nuevo nombre de usuario: ");
        String nuevoUsername = scanner.nextLine();
        System.out.print("Ingrese la nueva contraseña: ");
        String nuevaPassword = scanner.nextLine();

        try {
            gestorUser.modificarUsuario(id, nuevoUsername, nuevaPassword);
            System.out.println("Usuario modificado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al modificar el usuario: " + e.getMessage());
        }
    }
}

