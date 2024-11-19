package Gestores;

import Enumeraciones.CargoEmpleado;
import Excepciones.DNIExistenteException;
import Modelos.Personas.Empleado;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GestorEmpleados {

    List<Empleado> empleados;
    GestorConsola gestorConsola = new GestorConsola();

    GestorEmpleados() {
        this.empleados = new ArrayList<>();
    }

    /// AGREGAR EMPLEADO MANUALMENTE ----------------------------------------------------------------------------------

    public Empleado cargarNuevoEmpleado() {
        String dni = "";
        boolean dniValido = false;

        do {
            try {
                dni = gestorConsola.leerCadena("Ingrese el DNI del empleado", 8);
                dniValido = !dniExiste(dni);
            } catch (DNIExistenteException e) {
                System.out.println(e);
            }
        } while (!dniValido);

        String nombre = gestorConsola.leerCadenaMax("Ingrese el nombre del empleado", 25);
        String apellido = gestorConsola.leerCadenaMax("Ingrese el apellido del empleado", 25);
        String email = gestorConsola.leerCadenaMax("Ingrese el email del empleado", 100);

        // paso por parametro el arreglo de los valores del enum pero convertidos en una lista:
        CargoEmpleado cargo = gestorConsola.leerEnum(Arrays.asList(CargoEmpleado.values()));

        // falta leer la fecha de nacimiento

        return new Empleado(nombre, apellido, dni, email, LocalDate.now(), cargo);
    }

    /// AGREGAR EMPLEADO A LA LISTA -----------------------------------------------------------------------------------


    /// ELIMINAR EMPLEADO ---------------------------------------------------------------------------------------------


    /// BUSCAR EMPLEADO POR DNI ---------------------------------------------------------------------------------------


    /// LISTAR EMPLEADOS POR CARGO ------------------------------------------------------------------------------------


    /// LISTAR EMPLEADOS ACTIVOS --------------------------------------------------------------------------------------





    /// -----------------------------------------------------------------------------------------------
    /// VALIDACIONES

    private boolean dniExiste(String dni) throws DNIExistenteException {

        for (Empleado empleado : empleados) {
            if (empleado.getDni().equals(dni)) {
                throw new DNIExistenteException(dni);
            }
        }

        return false;
    }

}
