package Menu;


import Gestores.Personas.*;
import Modelos.Personas.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuMiPerfil {

    private Persona propietario;

    public MenuMiPerfil(Cliente cliente) {
        this.propietario = cliente;
    }

    public MenuMiPerfil(Empleado empleado) {
        this.propietario = empleado;
    }

    /// MENU ----------------------------------------------------------------------------------

    public void menuPerfilEmpleado(Scanner scanner) {
        verPerfil();

        GestorEmpleados gestorEmpleados = new GestorEmpleados();
        GestorUser gestorUser = new GestorUser();

        int opcion = mostrarOpcionesEmpleado(scanner);

        switch (opcion) {
            case 1:
                Empleado empleadoModificado = gestorEmpleados.modificarEmpleado(propietario.getDni());
                gestorEmpleados.reemplazarEmpleado(propietario.getDni(), empleadoModificado);
                break;
            case 2:
                gestorUser.modificarUsuario(propietario.getIdUsuario(), scanner);
                break;
            case 3:
                // salir
                break;
        }
    }

    public void menuPerfilCliente(Scanner scanner) {
        verPerfil();

        GestorClientes gestorClientes = new GestorClientes();
        GestorUser gestorUser = new GestorUser();

        Cliente cliente = gestorClientes.buscarClientePorDNI(propietario.getDni());

        int opcion = mostrarOpcionesCliente(scanner);

        switch (opcion) {
            case 1:
                Cliente clienteModificado = gestorClientes.modificarCliente(cliente.getDni());
                gestorClientes.reemplazarCliente(cliente.getDni(), clienteModificado);
                break;

            case 2:
                gestorUser.modificarUsuario(cliente.getIdUsuario(), scanner);
                break;

            case 3:
                System.out.println(cliente.getTarjetasRegistradas());
                GestorTarjetasBanco gestorTarjetasBanco = new GestorTarjetasBanco();
                TarjetaBanco tarjeta = gestorTarjetasBanco.cargarNuevaTarjeta();
                gestorClientes.agregarTarjetaACliente(cliente.getDni(), tarjeta);
                break;

            case 4:
                System.out.println(cliente.getDirecciones());
                GestorDirecciones gestorDirecciones = new GestorDirecciones();
                Direccion direccion = gestorDirecciones.cargarDireccion();
                gestorClientes.agregarDireccionACliente(cliente.getDni(), direccion);
                break;

            case 5:
                // salir
                break;
        }
    }

    public int mostrarOpcionesCliente(Scanner scanner) {
        int opcion = -1;
        do {
            try {
                System.out.print("Seleccione una opcion:\n" +
                        "[1] Modificar mis datos\n" +
                        "[2] Modificar mis credenciales\n" +
                        "[3] Agregar medio de pago\n" +
                        "[4] Agregar direccion de facturacion\n" +
                        "[0] Salir\n");
                opcion = scanner.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                opcion = -1;
            }
        } while (opcion < 0 || opcion > 7);

        return opcion;
    }

    public int mostrarOpcionesEmpleado(Scanner scanner) {
        int opcion = -1;
        do {
            try {
                System.out.print("Seleccione una opcion:\n" +
                        "[1] Modificar mis datos\n" +
                        "[2] Modificar mis credenciales\n" +
                        "[0] Salir\n");
                opcion = scanner.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                opcion = -1;
            }

        } while (opcion < 0 || opcion > 2);

        return opcion;
    }


    /// FUNCIONALIDADES -----------------------------------------------------------------------
    public void verPerfil() {
        System.out.println("-- BIENVENIDO, " + propietario.getNombre() + " --");

        System.out.println("Mis datos");
        System.out.println("---------");
        System.out.println("Nombre: " + propietario.getNombre() + " " + propietario.getApellido());
        System.out.println("DNI: " + propietario.getDni());
        System.out.println("Email: " + propietario.getEmail());
        System.out.println("Cumpleaños: " + propietario.getFechaNacimiento());
    }
}

