import Gestores.Personas.GestorEmpleados;
import Modelos.Personas.Empleado;

public class Main {
    public static void main(String[] args) {

        GestorEmpleados empleados = new GestorEmpleados();

        Empleado empleado = empleados.cargarNuevoEmpleado();
        empleados.agregarNuevoEmpleado(empleado);
    }
}