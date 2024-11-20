package Menu;

import Excepciones.ColeccionVaciaException;
import Gestores.Personas.GestorUser;
import Modelos.Personas.Sesion;

import java.util.Scanner;

public class MenuUsuarios {

    public void modificarUsuarioPorLista(GestorUser gestorUser, Scanner scanner) {
        listarUsuarios(gestorUser);

        int id = scanner.nextInt();
        gestorUser.modificarUsuario(id, scanner);
    }

    public void modificarUsuarioPorDni(GestorUser gestorUser, Scanner scanner) {
        listarUsuarios(gestorUser);

        /// ver no anda

        int id = scanner.nextInt();
        gestorUser.modificarUsuario(id, scanner);
    }

    public void buscarUsuarioPorId(Scanner scanner, GestorUser gestorUser) {
        System.out.print("Ingrese el ID del usuario: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            System.out.println(gestorUser.buscarPorId(id));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void buscarUsuarioPorUsername(GestorUser gestorUser) {
        System.out.print("Ingrese el nombre de usuario: ");
        String username = gestorUser.capturarUsername();
        try {
            System.out.println(gestorUser.buscarPorUsername(username));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void listarUsuarios(GestorUser gestorUser) {
        try {
            System.out.println("Usuarios cargados en el sistema: ");
            System.out.println("--------------------------------------------------------");
            gestorUser.listarUsuarios();
        } catch (ColeccionVaciaException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public void menuUsuarios(GestorUser gestorUser, Sesion sesion, Scanner scanner) {
        boolean regresar = false;

        while (!regresar) {
            System.out.println("\n--- GESTIÓN DE USUARIOS ---");
            System.out.println("2. Modificar un Usuario");
            System.out.println("3. Buscar Usuario por ID");
            System.out.println("4. Buscar Usuario por Nombre de Usuario");
            System.out.println("5. Listar todos los usuarios");
            System.out.println("0. Regresar");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    gestorUser.modificarUsuario(sesion.getUsuarioActual().getIdUsuario(), scanner);
                    break;
                case 2:
                    modificarUsuarioPorLista(gestorUser, scanner);
                    break;
                case 3:
                    buscarUsuarioPorId(scanner, gestorUser);
                    break;
                case 4:
                    buscarUsuarioPorUsername(gestorUser);
                    break;
                case 5:
                    listarUsuarios(gestorUser);
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