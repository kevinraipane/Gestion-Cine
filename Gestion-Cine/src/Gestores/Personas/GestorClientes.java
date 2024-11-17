package Gestores.Personas;

import Enumeraciones.CargoEmpleado;
import Excepciones.DNIExistenteException;
import Excepciones.DNIInexistenteException;
import Gestores.Funcionales.GestorConsola;
import Modelos.Cine.Entrada;
import Modelos.Personas.Cliente;
import Modelos.Personas.Direccion;
import Modelos.Personas.Empleado;
import Modelos.Personas.TarjetaBanco;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

public class GestorClientes {
    GestorPersonas gestorPersonas = new GestorPersonas();
    GestorConsola gestorConsola = new GestorConsola();
    Scanner scanner = new Scanner(System.in);

    HashMap<String, Cliente> clientes;

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

    public void listarClientes() {
        for(Cliente cliente : clientes.values()) {
            System.out.println(cliente.toString());
        }
    }

    /// FILTRAR CLIENTES ------------------------------------------------------------------------------------

    public void filtrarClientes(Predicate<Cliente> criterio) {
        for (Cliente cliente : clientes.values()) {
            if (criterio.test(cliente)) {
                System.out.println(cliente.toString());
            }
        }
    }

    /// MODIFICAR CLIENTE --------------------------------------------------------------------------------
    public Cliente modificarCliente(String dni) {
        byte opcion = 0;
        Cliente cliente = null;

        try {
            cliente = buscarClientePorDNI(dni);
        } catch (DNIInexistenteException e) {
            System.out.println(e.getMessage());
        }

        do {
            System.out.print("Seleccione el dato que desea modificar:" +
                    "[1] Nombre" +
                    "[2] Apellido" +
                    "[3] Fecha de Nacimiento" +
                    "[4] Email" +
                    "[5] Modificar todos los campos" +
                    "[0] Salir");

            opcion = scanner.nextByte();
        } while (opcion > 6 || opcion < 0);

        switch (opcion) {
            case 1:
                String nombre = gestorPersonas.leerNombre();
                cliente.setNombre(nombre);
                break;
            case 2:
                String apellido = gestorPersonas.leerApellido();
                cliente.setApellido(apellido);
                break;
            case 3:
                LocalDate fechaNacimiento = gestorPersonas.leerFechaNacimiento();
                cliente.setFechaNacimiento(fechaNacimiento);
                break;
            case 4:
                String email = gestorPersonas.leerEmail();
                cliente.setEmail(email);
                break;
            case 5:
                cliente = cargarNuevoCliente();
                break;
            case 0: // salir

        }

        return cliente;
    }

    /// AGREGAR DATOS A CLIENTE -------------------------------------------------------------------------

    // tarjeta
    public void agregarTarjetaACliente(String dni, TarjetaBanco tarjeta) throws DNIInexistenteException {
        if (clientes.containsKey(dni)) {
            clientes.get(dni).agregarTarjeta(tarjeta);
        } else throw new DNIInexistenteException(dni);
    }

    public void eliminarTarjetaACliente(String dni, TarjetaBanco tarjeta) throws DNIInexistenteException {
        if (clientes.containsKey(dni)) {
            clientes.get(dni).eliminarTarjeta(tarjeta);
        } else throw new DNIInexistenteException(dni);
    }

    // direccion
    public void agregarDireccionACliente(String dni, Direccion direccion) throws DNIInexistenteException {
        if (clientes.containsKey(dni)) {
            clientes.get(dni).agregarDireccion(direccion);
        } else throw new DNIInexistenteException(dni);
    }

    public void eliminarDireccionACliente(String dni, Direccion direccion) throws DNIInexistenteException {
        if (clientes.containsKey(dni)) {
            clientes.get(dni).eliminarDireccion(direccion);
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
