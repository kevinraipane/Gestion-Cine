package Modelos.Personas;

import Enumeraciones.CargoEmpleado;
import Interfaces.IVisualizable;

import java.time.LocalDate;

public class Empleado extends Persona implements IVisualizable {
    //Atributos
    private int idEmpleado;
    private static int contadorId = 0;
    private CargoEmpleado cargo;

    //Constructor, el constructor deberia recibir el rol no asignarlo por defecto
    public Empleado(String nombre, String apeliido, String dni, String email, LocalDate fechaNacimiento,CargoEmpleado cargo){
        super(nombre, apeliido, dni, email, fechaNacimiento);
        this.idEmpleado = contadorId++;
        this.cargo = cargo;
    }

    //Gettets y Setters
    public CargoEmpleado getCargo() {
        return cargo;
    }

    public void setCargo(CargoEmpleado cargo) {
        this.cargo = cargo;
    }

    //Metodos interfaz
    /**
     * void verCartelera();
     * void verReservas(String dni, LocalDate dia);
     * void verFunciones(LocalDate dia);
     */
}
