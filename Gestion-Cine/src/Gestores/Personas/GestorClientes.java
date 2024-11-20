package Gestores.Personas;

import Excepciones.DniExistenteException;
import Excepciones.DniInexistenteException;
import Gestores.Funcionales.GestorConsola;
//import Modelos.Cine.Entrada;
import Modelos.Personas.Cliente;
import Modelos.Personas.Direccion;
import Modelos.Personas.Empleado;
import Modelos.Personas.TarjetaBanco;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

public class GestorClientes {
    private static final String LAST_ID_FILE_PATH = "usuarios.json";
    private static final String CLIENTES_FILE_PATH = "clientes.json";
    int lastId = 0;

    GestorPersonas gestorPersonas = new GestorPersonas();
    GestorConsola gestorConsola = new GestorConsola();
    Scanner scanner = new Scanner(System.in);

    HashMap<String, Cliente> clientes;

    public GestorClientes() {
        clientes = new HashMap<>();
        cargarClientes();
    }

    /// CREAR CLIENTE NUEVO ----------------------------------------------------------------

    public Cliente cargarNuevoCliente() {
        int newId = (++lastId);
        String dni = leerDniCliente();
        String nombre = gestorPersonas.leerNombre();
        String apellido = gestorPersonas.leerApellido();
        String email = gestorPersonas.leerEmail();
        LocalDate fechaNacimiento = gestorPersonas.leerFechaNacimiento();

        return new Cliente(newId, nombre, apellido, dni, email, fechaNacimiento);
    }

    /// AGREGAR CLIENTE A LA LISTA -----------------------------------------------------------------------------------

    public void agregarNuevoCliente(Cliente cliente) {
        try {
            clientes.put(cliente.getDni(), cliente);
            guardarClientes();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /// ELIMINAR CLIENTE ---------------------------------------------------------------------------------------------

    public void eliminarCliente(String dni) throws DniInexistenteException {
        if (clientes.containsKey(dni)) {
            clientes.remove(dni);
        } else {
            throw new DniInexistenteException(dni);
        }
    }

    /// BUSCAR CLIENTE ---------------------------------------------------------------------------------------

    public Cliente buscarClientePorDNI(String dni) throws DniInexistenteException {
        if (clientes.containsKey(dni)) {
            return clientes.get(dni);
        } else {
            throw new DniInexistenteException(dni);
        }
    }

    /// LISTAR CLIENTES ------------------------------------------------------------------------------------

    public void listarClientes() {
        for (Cliente cliente : clientes.values()) {
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
        } catch (DniInexistenteException e) {
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

    public void reemplazarCliente(String dni, Cliente clienteModificado) {
        try {
            Cliente clienteActual = buscarClientePorDNI(dni);
            clienteActual = clienteModificado;
            System.out.println("Cliente modificado con exito");

            guardarClientes();

        } catch (DniInexistenteException e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }

    /// AGREGAR DATOS A CLIENTE -------------------------------------------------------------------------

    // tarjeta
    public void agregarTarjetaACliente(String dni, TarjetaBanco tarjeta) throws DniInexistenteException {
        if (clientes.containsKey(dni)) {
            clientes.get(dni).agregarTarjeta(tarjeta);
        } else throw new DniInexistenteException(dni);
    }

    public void eliminarTarjetaACliente(String dni, TarjetaBanco tarjeta) throws DniInexistenteException {
        if (clientes.containsKey(dni)) {
            clientes.get(dni).eliminarTarjeta(tarjeta);
        } else throw new DniInexistenteException(dni);
    }

    // direccion
    public void agregarDireccionACliente(String dni, Direccion direccion) throws DniInexistenteException {
        if (clientes.containsKey(dni)) {
            clientes.get(dni).agregarDireccion(direccion);
        } else throw new DniInexistenteException(dni);
    }

    public void eliminarDireccionACliente(String dni, Direccion direccion) throws DniInexistenteException {
        if (clientes.containsKey(dni)) {
            clientes.get(dni).eliminarDireccion(direccion);
        } else throw new DniInexistenteException(dni);
    }

    /// ARCHIVOS: JSON -------------------------------------------------------------------------

    // Cargar desde JSON

    private void cargarClientes() {
        Gson gson = new Gson();

        //Leo el lastID del archivo de usuarios.
        try (FileReader lastIdReader = new FileReader(LAST_ID_FILE_PATH)) {
            JsonObject lastIdData = gson.fromJson(lastIdReader, JsonObject.class);
            if (lastIdData != null && lastIdData.has("lastId")) {
                this.lastId = lastIdData.get("lastId").getAsInt();
            }
        } catch (IOException e) {
            System.out.printf("No se pudo cargar el archivo que contiene el ultimo ID: " + e.getMessage());
        }

        //Leer el archivo de usuarios
        try (FileReader clientesReader = new FileReader(CLIENTES_FILE_PATH)) {
            JsonObject data = gson.fromJson(clientesReader, JsonObject.class);

            if (data != null) {
                if (data.has("clientes")) {
                    Type mapType = new TypeToken<HashMap<String, Cliente>>() {
                    }.getType();
                    HashMap<String, Cliente> clientesCargados = gson.fromJson(data.get("clientes"), mapType);
                    if (clientesCargados != null) {
                        this.clientes = clientesCargados;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("No se pudo cargar el archivo de clientes: " + e.getMessage());
        }
    }

    // Guardar a JSON

    private void guardarClientes() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter clientesWriter = new FileWriter(CLIENTES_FILE_PATH)) {
            JsonObject data = new JsonObject();

            //Guardo los clientes
            data.add("clientes", gson.toJsonTree(this.clientes));

            //Escribo los clientes en el archivo
            gson.toJson(data, clientesWriter);
        } catch (IOException e) {
            System.out.println("No se pudo guardar el archivo JSON: " + e.getMessage());
        }
    }


    /// -----------------------------------------------------------------------------------------------
    /// VALIDACIONES

    private boolean dniExiste(String dni) throws DniExistenteException {

        if (!clientes.containsKey(dni)) {
            return true;
        }

        throw new DniExistenteException(dni);
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
            } catch (DniExistenteException e) {
                System.out.println(e.getMessage());
            }


        } while (!valido);

        return dni;
    }
}
