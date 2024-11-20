package Gestores.Personas;

import Enumeraciones.CargoEmpleado;
import Excepciones.DniExistenteException;
import Excepciones.DniInexistenteException;
import Gestores.Funcionales.GestorConsola;
import Modelos.Personas.Empleado;
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

public class GestorEmpleados {

    private static final String LAST_ID_FILE_PATH = "usuarios.json";
    private static final String EMPLEADOS_FILE_PATH = "empleados.json";
    int lastId = 0;
    HashMap<String, Empleado> empleados;

    GestorConsola gestorConsola = new GestorConsola();
    GestorPersonas gestorPersonas = new GestorPersonas();
    GestorUser gestorUser = new GestorUser();
    Scanner scanner = new Scanner(System.in);

    public GestorEmpleados() {
        this.empleados = new HashMap<>();
        cargarEmpleados();
    }


    /// AGREGAR EMPLEADO MANUALMENTE ----------------------------------------------------------------------------------

    public Empleado cargarNuevoEmpleado() {
        int idUsuario = gestorPersonas.recibirIdUsuario();
        String dni = leerDniEmpleado();
        String nombre = gestorPersonas.leerNombre();
        String apellido = gestorPersonas.leerApellido();
        String email = gestorPersonas.leerEmail();
        LocalDate fechaNacimiento = gestorPersonas.leerFechaNacimiento();

        System.out.println("Cargo del empleado");
        CargoEmpleado cargo = gestorConsola.leerEnum(Arrays.asList(CargoEmpleado.values()));

        return new Empleado(idUsuario,nombre, apellido, dni, email, fechaNacimiento, cargo);
    }

    /// AGREGAR ADMIN POR DEFECTO ----------------------------------------------------------------------------------

    public Empleado crearEmpleadoAdministradorPorDefecto() {
        // Valores predeterminados para el administrador
        int idUsuario = 1; // ID predeterminado
        String dni = "00000000"; // DNI predeterminado
        String nombre = "Admin"; // Nombre del administrador
        String apellido = "Default"; // Apellido del administrador
        String email = "admin@default.com"; // Email predeterminado
        LocalDate fechaNacimiento = LocalDate.of(1980, 1, 1); // Fecha de nacimiento predeterminada

        // Asumimos que el cargo del empleado es 'Administrador'
        CargoEmpleado cargo = CargoEmpleado.ADMIN;

        // Crear y devolver el nuevo empleado administrador
        return new Empleado(idUsuario, nombre, apellido, dni, email, fechaNacimiento, cargo);
    }


    /// AGREGAR EMPLEADO A LA LISTA -----------------------------------------------------------------------------------

    public void agregarNuevoEmpleado(Empleado empleado) {
        try {
            empleados.put(empleado.getDni(), empleado);
            guardarEmpleados();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /// ELIMINAR EMPLEADO ---------------------------------------------------------------------------------------------

    public void bajaEmpleado(String dni) throws DniInexistenteException {
        if (empleados.containsKey(dni)) {
            Empleado empleadoActual = empleados.get(dni);

            if (!empleadoActual.dadoDeBaja()) {
                int cantAdmins = 0;
                for (Empleado empleado : empleados.values()) {
                    if (empleado.esAdmin()) {
                        cantAdmins++;
                    }
                }

                if (empleadoActual.esAdmin() && cantAdmins > 1) {
                    empleadoActual.darDeBaja();
                    int idUsuario = empleadoActual.getIdUsuario();

                    gestorUser.eliminarUsuario(gestorUser.buscarPorId(idUsuario).getUsername());

                } else {
                    System.out.print("El empleado con DNI: " + dni + " es el unico ADMIN del sistema. " +
                            "No puede ser dado de baja.");
                }

            }
        } else {
            throw new DniInexistenteException(dni);
        }
    }

    /// BUSCAR EMPLEADO ---------------------------------------------------------------------------------------

    public Empleado buscarEmpleadoPorDNI(String dni) throws DniInexistenteException {
        if (empleados.containsKey(dni)) {
            return empleados.get(dni);
        } else {
            throw new DniInexistenteException(dni);
        }
    }

    /// LISTAR EMPLEADOS ------------------------------------------------------------------------------------

    public void listarEmpleados() {
        for (Empleado empleado : empleados.values()) {
            System.out.println(empleado.toString());
        }
    }

    /// FILTRAR EMPLEADOS ------------------------------------------------------------------------------------

    public void filtrarEmpleados(Predicate<Empleado> criterio) {
        for (Empleado empleado : empleados.values()) {
            if (criterio.test(empleado)) {
                System.out.println(empleado.toString());
            }
        }
    }

    /// MODIFICAR EMPLEADOS ----------------------------------------------------------------------------------

    public Empleado modificarEmpleado(String dni) {
        byte opcion = 0;
        Empleado empleado = null;
        boolean repetir = false;

        try {
            empleado = buscarEmpleadoPorDNI(dni);
        } catch (DniInexistenteException e) {
            System.out.println(e.getMessage());
        }

        do {
            do {
                System.out.print("Seleccione el dato que desea modificar:" +
                        "[1] Nombre" +
                        "[2] Apellido" +
                        "[3] Fecha de Nacimiento" +
                        "[4] Email" +
                        "[5] Cargo" +
                        "[6] Modificar todos los campos" +
                        "[0] Salir");

                opcion = scanner.nextByte();

                if (opcion > 6 || opcion < 0) {
                    System.out.println("Opcion invalida, intente nuevamente");
                }

            } while (opcion > 6 || opcion < 0);

            int modificar;
            do {
                System.out.println("Desea modificar otro dato?");
                System.out.println("[1] SI");
                System.out.println("[2] NO");

                modificar = scanner.nextInt();

                if (modificar != 1 && modificar != 2) {
                    System.out.println("Opcion invalida");
                }

            } while (modificar != 1 && modificar != 2);

            if (modificar == 1) {
                repetir = true;
            }

        } while (repetir);

        switch (opcion) {
            case 1:
                String nombre = gestorPersonas.leerNombre();
                empleado.setNombre(nombre);
                break;
            case 2:
                String apellido = gestorPersonas.leerApellido();
                empleado.setApellido(apellido);
                break;
            case 3:
                LocalDate fechaNacimiento = gestorPersonas.leerFechaNacimiento();
                empleado.setFechaNacimiento(fechaNacimiento);
                break;
            case 4:
                String email = gestorPersonas.leerEmail();
                empleado.setEmail(email);
                break;
            case 5:
                System.out.println("Cargo del empleado");
                CargoEmpleado cargo = gestorConsola.leerEnum(Arrays.asList(CargoEmpleado.values()));
                empleado.setCargo(cargo);
                break;
            case 6:
                empleado = cargarNuevoEmpleado();
                break;
            case 0: // salir
        }

        return empleado;
    }

    public void reemplazarEmpleado(String dni, Empleado empleadoModificado) {
        try {
            Empleado empleadoActual = buscarEmpleadoPorDNI(dni);
            empleadoActual = empleadoModificado;
            System.out.println("Empleado modificado con exito");

        } catch (DniInexistenteException e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }

    /// ARCHIVOS: JSON -------------------------------------------------------------------------

    // Cargar desde JSON
    private void cargarEmpleados() {
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
        try (FileReader empleadosReader = new FileReader(EMPLEADOS_FILE_PATH)) {
            JsonObject data = gson.fromJson(empleadosReader, JsonObject.class);

            if (data != null) {
                if (data.has("empleados")) {
                    Type mapType = new TypeToken<HashMap<String, Empleado>>() {
                    }.getType();
                    HashMap<String, Empleado> empleadosCargados = gson.fromJson(data.get("empleados"), mapType);
                    if (empleadosCargados != null) {
                        this.empleados = empleadosCargados;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("No se pudo cargar el archivo de empleados: " + e.getMessage());
        }
    }

    // Guardar a JSON

    private void guardarEmpleados() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter empleadosWriter = new FileWriter(EMPLEADOS_FILE_PATH)) {
            JsonObject data = new JsonObject();

            //Guardo los empleados
            data.add("empleados", gson.toJsonTree(this.empleados));

            //Escribo los empleados en el archivo
            gson.toJson(data, empleadosWriter);
        } catch (IOException e) {
            System.out.println("No se pudo guardar el archivo JSON: " + e.getMessage());
        }
    }

    /// -----------------------------------------------------------------------------------------------
    /// VALIDACIONES

    private void dniExiste(String dni) throws DniExistenteException {

        if (!empleados.containsKey(dni)) {
            return;
        }

        throw new DniExistenteException(dni);
    }

    public String leerDniEmpleado() {
        String dni;
        boolean valido;

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
