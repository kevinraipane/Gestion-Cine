package Menu;

import Excepciones.DniInexistenteException;
import Gestores.Personas.GestorEmpleados;
import Gestores.Personas.GestorUser;
import Modelos.Personas.Empleado;
import Modelos.Personas.Sesion;

import java.util.Scanner;

public class MenuEmpleados {

    public void crearEmpleado(GestorEmpleados gestorEmpleados, GestorUser gestorUser) {
        Empleado nuevoEmpleado = gestorEmpleados.cargarNuevoEmpleado();
        gestorEmpleados.agregarNuevoEmpleado(nuevoEmpleado);
        System.out.println("Empleado creado: " + nuevoEmpleado);

        boolean usuarioCreado = false;
        do{
            System.out.println("Ahora debe crear un usuario para este empleado.");

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

    public void buscarEmpleado(GestorEmpleados gestorEmpleados) {
        String dni = gestorEmpleados.leerDniEmpleado();

        try {
            System.out.println(gestorEmpleados.buscarEmpleadoPorDNI(dni));
        } catch (DniInexistenteException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void eliminarEmpleado(GestorEmpleados gestorEmpleados) {
        String dni = gestorEmpleados.leerDniEmpleado();

        try {
            gestorEmpleados.bajaEmpleado(dni);
            System.out.println("Empleado eliminado.");
        } catch (DniInexistenteException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void modificarEmpleado(GestorEmpleados gestorEmpleados) {
        try {
            String dni = gestorEmpleados.leerDniEmpleado();
            Empleado empleadoModificado = gestorEmpleados.modificarEmpleado(dni);
            gestorEmpleados.reemplazarEmpleado(dni, empleadoModificado);

        } catch (DniInexistenteException e) {
            System.err.println("ERROR: " + e.getMessage());
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
            System.out.println("5. Modificar datos de Empleado");
            System.out.println("0. Regresar");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    crearEmpleado(gestorEmpleados, gestorUser);
                    break;
                case 2:
                    gestorEmpleados.listarEmpleados();
                    break;
                case 3:
                    buscarEmpleado(gestorEmpleados);
                    break;
                case 4:
                    eliminarEmpleado(gestorEmpleados);
                    break;
                case 5:
                    modificarEmpleado(gestorEmpleados);
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
