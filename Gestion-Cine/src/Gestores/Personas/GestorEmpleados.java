package Gestores.Personas;

import Enumeraciones.CargoEmpleado;
import Excepciones.DNIExistenteException;
import Excepciones.DNIInexistenteException;
import Gestores.Funcionales.GestorConsola;
import Modelos.Personas.Empleado;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

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

        try {
            empleado = buscarEmpleadoPorDNI(dni);
        } catch (DNIInexistenteException e) {
            System.out.println(e.getMessage());
        }

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

    /// -----------------------------------------------------------------------------------------------
    /// VALIDACIONES

    private void dniExiste(String dni) throws DNIExistenteException {

        if (!empleados.containsKey(dni)) {
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
