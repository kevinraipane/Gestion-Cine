import Excepciones.ColeccionVaciaException;
import Gestores.Personas.*;
import Menu.MenuClientes;
import Menu.MenuEmpleados;
import Menu.MenuUsuarios;
import Modelos.Personas.Cliente;
import Modelos.Personas.Empleado;
import Modelos.Personas.Sesion;
import Modelos.Personas.User;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        GestorUser gestorUser = new GestorUser();
        GestorClientes gestorClientes = new GestorClientes();
        GestorEmpleados gestorEmpleados = new GestorEmpleados();

        MenuEmpleados menuEmpleados = new MenuEmpleados();
        MenuUsuarios menuUsuarios = new MenuUsuarios();
        MenuClientes menuClientes = new MenuClientes();

        Sesion sesion = new Sesion();
        Scanner scanner = new Scanner(System.in);

        //Verifico si existe el archivo JSON de usuarios
        primerInicioDeSesion(gestorUser, gestorEmpleados, sesion, scanner);

        //Iniciar sesion
        gestorUser.validarInicioSesion(sesion, gestorUser);


    }

    public static void primerInicioDeSesion(GestorUser gestorUser, GestorEmpleados gestorEmpleados, Sesion sesion, Scanner scanner) {
        String archivoUsuarios = "usuarios.json";
        File archivo = new File(archivoUsuarios);

        if (!archivo.exists() || archivo.length() == 0) {
            System.out.println("No se encontro el archivo de usuarios.");
            System.out.println("Creando administrador por defecto...");

            //Creo un empleado generico del tipo admin
            Empleado admin = gestorEmpleados.crearEmpleadoAdministradorPorDefecto();
            try {
                gestorEmpleados.agregarNuevoEmpleado(admin);

                //Crear usuario asociado al admin generico
                gestorUser.crearUsuario("admin", "adminadmin");
                System.out.println("Administrador creado con usuario: admin / Contraseña: adminadmin");
                gestorUser.modificarUsuario(1, scanner);

            } catch (Exception e) {
                System.err.println("Error al crear el administrador generico por defecto: " + e.getMessage());
            }
        }
    }
}

