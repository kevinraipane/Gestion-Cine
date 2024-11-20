package Menu;


import Gestores.Funcionales.GestorConsola;
import Gestores.Personas.*;
import Modelos.Personas.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuMiPerfil {

    private final Persona propietario;

    public MenuMiPerfil(Cliente cliente) {
        this.propietario = cliente;
    }

    public MenuMiPerfil(Empleado empleado) {
        this.propietario = empleado;
    }


    GestorTarjetasBanco gestorTarjetasBanco = new GestorTarjetasBanco();
    GestorDirecciones gestorDirecciones = new GestorDirecciones();


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
                TarjetaBanco tarjeta = gestorTarjetasBanco.cargarNuevaTarjeta();
                gestorClientes.agregarTarjetaACliente(cliente.getDni(), tarjeta);
                break;

            case 4:
                TarjetaBanco tarjetaEliminar = eliminarDatos(cliente.getTarjetasRegistradas(), scanner);
                gestorClientes.eliminarTarjetaACliente(cliente.getDni(), tarjetaEliminar);
                System.out.println("El dato se elimino con exito");
                break;

            case 5:
                System.out.println(cliente.getDirecciones());
                Direccion direccion = gestorDirecciones.cargarDireccion();
                gestorClientes.agregarDireccionACliente(cliente.getDni(), direccion);
                break;

            case 6:
                Direccion direccionEliminar = eliminarDatos(cliente.getDirecciones(), scanner);
                gestorClientes.eliminarDireccionACliente(cliente.getDni(), direccionEliminar);
                System.out.println("El dato se elimino con exito");
                break;

            case 7:
                // salir
                break;
        }
    }

    /// MENU DE OPCIONES --------------------------------------------------------------------

    public int mostrarOpcionesCliente(Scanner scanner) {
        int opcion;
        do {
            try {
                System.out.print("""
                        Seleccione una opcion:
                        [1] Modificar mis datos
                        [2] Modificar mis credenciales
                        
                        Medios de pago
                        [3] Agregar
                        [4] Eliminar
                        
                        Direcciones de facturacion
                        [5] Agregar
                        [6] Eliminar
                        
                        [0] Salir
                        """);

                opcion = scanner.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                opcion = -1;
            }
        } while (opcion < 0 || opcion > 7);

        return opcion;
    }

    public int mostrarOpcionesEmpleado(Scanner scanner) {
        int opcion;
        do {
            try {
                System.out.print("""
                        Seleccione una opcion:
                        [1] Modificar mis datos
                        [2] Modificar mis credenciales
                        
                        [0] Salir
                        """);
                opcion = scanner.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                opcion = -1;
            }

        } while (opcion < 0 || opcion > 2);

        return opcion;
    }

    /// FUNCIONALIDADES -----------------------------------------------------------------------

    public <T> T eliminarDatos(ArrayList<T> lista, Scanner scanner) {
        System.out.println("Seleccione el registro que desea eliminar: ");
        int i = 1; int opcion;

        for (T dato : lista) {
            System.out.println("[" + i + "] " + dato.toString());
        }

        do {
            opcion = scanner.nextInt();
        }  while (!GestorConsola.perteneceAlRango(opcion, 0, lista.size()));

        return lista.get(opcion - 1);
    }

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

