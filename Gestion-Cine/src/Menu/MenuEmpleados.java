package Menu;

import Gestores.Personas.GestorEmpleados;
import Gestores.Personas.GestorUser;
import Modelos.Personas.Empleado;
import Modelos.Personas.Sesion;

import java.util.Scanner;

public class MenuEmpleados {

    public void crearEmpleado(GestorEmpleados gestorEmpleados, Scanner scanner, GestorUser gestorUser) {
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
                gestorUser.crearUsuario(username, password);
                System.out.println("Usuario creado exitosamente para el cliente.");
                usuarioCreado = true;
            } catch (Exception e) {
                System.out.println("Error al crear el usuario: " + e.getMessage());
                System.out.println("Intentelo nuevamente.");
            }
        } while (!usuarioCreado);
    }

    public void buscarEmpleado(Scanner scanner, GestorEmpleados gestorEmpleados) {
        System.out.print("Ingrese el DNI del empleado: ");
        String dni = scanner.nextLine();
        try {
            System.out.println(gestorEmpleados.buscarEmpleadoPorDNI(dni));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void eliminarEmpleado(GestorEmpleados gestorEmpleados) {
        System.out.print("Ingrese el DNI del empleado a eliminar: ");
        String dni = gestorEmpleados.leerDniEmpleado();
        try {
            gestorEmpleados.bajaEmpleado(dni);
            System.out.println("Empleado eliminado.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void menuEmpleados(GestorEmpleados gestorEmpleados, Sesion sesion, GestorUser gestorUser, Scanner scanner) {
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
                    crearEmpleado(gestorEmpleados, scanner, gestorUser);
                    break;
                case 2:
                    gestorEmpleados.listarEmpleados();
                    break;
                case 3:
                    buscarEmpleado(scanner, gestorEmpleados);
                    break;
                case 4:
                    eliminarEmpleado(gestorEmpleados);
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
