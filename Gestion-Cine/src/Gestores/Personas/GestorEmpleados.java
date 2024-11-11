package Gestores.Personas;

import Enumeraciones.CargoEmpleado;
import Excepciones.DNIExistenteException;
import Excepciones.DNIInexistenteException;
import Gestores.Funcionales.GestorConsola;
import Modelos.Personas.Empleado;

import java.time.LocalDate;
import java.util.*;

public class GestorEmpleados {

    HashMap<String, Empleado> empleados;
    GestorConsola gestorConsola = new GestorConsola();
    GestorPersonas gestorPersonas = new GestorPersonas();
    Scanner scanner = new Scanner(System.in);

    public GestorEmpleados() {
        this.empleados = new HashMap<>();
    }


    /// AGREGAR EMPLEADO MANUALMENTE ----------------------------------------------------------------------------------

    public Empleado cargarNuevoEmpleado() {
        String dni = leerDniEmpleado();

        String nombre = gestorPersonas.leerNombre();
        String apellido = gestorPersonas.leerApellido();
        String email = gestorPersonas.leerEmail();
        LocalDate fechaNacimiento = gestorPersonas.leerFechaNacimiento();

        System.out.println("Cargo del empleado");
        CargoEmpleado cargo = gestorConsola.leerEnum(Arrays.asList(CargoEmpleado.values()));

        return new Empleado(nombre, apellido, dni, email, fechaNacimiento, cargo);
    }

    /// AGREGAR EMPLEADO A LA LISTA -----------------------------------------------------------------------------------

    public void agregarNuevoEmpleado(Empleado empleado) {
        empleados.put(empleado.getDni(), empleado);
    }

    /// ELIMINAR EMPLEADO ---------------------------------------------------------------------------------------------

    public void eliminarEmpleado(String dni) throws DNIInexistenteException {
        if (empleados.containsKey(dni)) {
            empleados.remove(dni);
        } else {
            throw new DNIInexistenteException(dni);
        }
    }

    /// BUSCAR EMPLEADO ---------------------------------------------------------------------------------------

    public Empleado buscarEmpleadoPorDNI(String dni) throws DNIInexistenteException {
        if (empleados.containsKey(dni)) {
            return empleados.get(dni);
        } else {
            throw new DNIInexistenteException(dni);
        }
    }

    /// LISTAR EMPLEADOS ------------------------------------------------------------------------------------

    // por cargo
    public List<Empleado> listarEmpleadosPorCargo(String cargo) {
        List<Empleado> filtrados = new ArrayList<>();

        // IMPLEMENTAR LOGICA

        return filtrados;
    }

    // por estado
    public List<Empleado> listarEmpleadosPorEstado(String estado) {
        List<Empleado> filtrados = new ArrayList<>();

        // IMPLEMENTAR LOGICA

        return filtrados;
    }


    /// -----------------------------------------------------------------------------------------------
    /// VALIDACIONES

    private void dniExiste(String dni) throws DNIExistenteException {

        if(!empleados.containsKey(dni)) {
            return;
        }

        throw new DNIExistenteException(dni);
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
            } catch (DNIExistenteException e) {
                System.out.println(e.getMessage());
            }


        } while (!valido);

        return dni;
    }

}
