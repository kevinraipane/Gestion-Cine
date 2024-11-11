package Gestores.Personas;

import Excepciones.DNIExistenteException;
import Excepciones.DNIInexistenteException;
import Gestores.Funcionales.GestorConsola;
import Modelos.Cine.Entrada;
import Modelos.Personas.Cliente;
import Modelos.Personas.Direccion;
import Modelos.Personas.TarjetaBanco;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class GestorClientes {
    HashMap<String, Cliente> clientes;
    GestorPersonas gestorPersonas = new GestorPersonas();
    GestorConsola gestorConsola = new GestorConsola();
    Scanner scanner = new Scanner(System.in);

    public GestorClientes() {
        clientes = new HashMap<>();
    }

    public Cliente cargarNuevoCliente() {
        String dni = leerDniCliente();

        String nombre = gestorPersonas.leerNombre();
        String apellido = gestorPersonas.leerApellido();
        String email = gestorPersonas.leerEmail();
        LocalDate fechaNacimiento = gestorPersonas.leerFechaNacimiento();

        return new Cliente(nombre, apellido, dni, email, fechaNacimiento);
    }

    /// AGREGAR CLIENTE A LA LISTA -----------------------------------------------------------------------------------

    public void agregarNuevoCliente(Cliente cliente) {
        clientes.put(cliente.getDni(), cliente);
    }

    /// ELIMINAR CLIENTE ---------------------------------------------------------------------------------------------

    public void eliminarCliente(String dni) throws DNIInexistenteException {
        if (clientes.containsKey(dni)) {
            clientes.remove(dni);
        } else {
            throw new DNIInexistenteException(dni);
        }
    }

    /// BUSCAR CLIENTE ---------------------------------------------------------------------------------------

    public Cliente buscarClientePorDNI(String dni) throws DNIInexistenteException {
        if (clientes.containsKey(dni)) {
            return clientes.get(dni);
        } else {
            throw new DNIInexistenteException(dni);
        }
    }

    /// LISTAR CLIENTES ------------------------------------------------------------------------------------

    // por cantidad de compras
    public List<Cliente> listarClientesPorEstado(int cantidad) {
        List<Cliente> filtrados = new ArrayList<>();

        // IMPLEMENTAR LOGICA

        return filtrados;
    }

    /// AGREGAR DATOS A CLIENTE -------------------------------------------------------------------------

    // tarjeta
    public void agregarTarjetaACliente(String dni, TarjetaBanco tarjeta) throws DNIInexistenteException {
        if (clientes.containsKey(dni)) {
            clientes.get(dni).agregarTarjeta(tarjeta);
        } else throw new DNIInexistenteException(dni);
    }

    // direccion
    public void agregarDireccionACliente(String dni, Direccion direccion) throws DNIInexistenteException {
        if (clientes.containsKey(dni)) {
            clientes.get(dni).agregarDireccion(direccion);
        } else throw new DNIInexistenteException(dni);
    }

    // entrada
    public void agregarEntradaACliente(String dni, Entrada entrada) throws DNIInexistenteException {
        if (clientes.containsKey(dni)) {
            clientes.get(dni).agregarEntrada(entrada);
        } else throw new DNIInexistenteException(dni);
    }


    /// -----------------------------------------------------------------------------------------------
    /// VALIDACIONES

    private boolean dniExiste(String dni) throws DNIExistenteException {

        if (!clientes.containsKey(dni)) {
            return true;
        }

        throw new DNIExistenteException(dni);
    }

    public String leerDniCliente() {
        String dni = "";
        boolean valido = false;

        do {
            System.out.println("Ingrese su dni:");
            dni = scanner.nextLine().trim();
            valido = true;

            if (!gestorConsola.esLargoValido(dni, 7, 8)) {
                System.out.println("El dni debe contener entre 7 (siete) y 8 (ocho) caracteres");
                valido = false;
            }

            try {
                gestorConsola.contieneSoloNumeros(dni);
            } catch (NumberFormatException e) {
                System.out.println("El dni solo puede contener numeros");
                valido = false;
            }

            try {
                dniExiste(dni);
                valido = true;
            } catch (DNIExistenteException e) {
                System.out.println(e.getMessage());
            }


        } while (!valido);

        return dni;
    }
}
