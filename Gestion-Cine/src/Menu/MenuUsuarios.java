package Menu;

import Excepciones.ColeccionVaciaException;
import Gestores.Personas.GestorUser;
import Modelos.Personas.Sesion;

import java.util.Scanner;

public class MenuUsuarios {

    public void menuUsuarios(GestorUser gestorUser, Sesion sesion, Scanner scanner) {
        boolean regresar = false;

        while (!regresar) {
            System.out.print("""
                    --- GESTIÓN DE USUARIOS ---
                    
                    [1] Modificar usuario
                    
                    Buscar usuarios
                    [2] Por dni
                    [3] Por username
                    [4] Ver todos
                    
                    [0] Salir
                    
                    Seleccione una opcion:
                    """);

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    modificarUsuarioPorLista(gestorUser, scanner);
                    break;
                case 2:
                    buscarUsuarioPorId(scanner, gestorUser);
                    break;
                case 3:
                    buscarUsuarioPorUsername(gestorUser);
                    break;
                case 4:
                    try {
                        gestorUser.listarUsuarios();
                    } catch (ColeccionVaciaException e) {
                        System.err.println(e.getMessage());
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

    /// ACCIONES ------------------------------------------------------------

    public void modificarUsuarioPorLista(GestorUser gestorUser, Scanner scanner) {
        try {
            gestorUser.listarUsuarios();
        } catch (ColeccionVaciaException e) {
            System.err.println("ERROR: " + e.getMessage());
        }

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

}