package Modelos.Personas;

import Enumeraciones.CargoEmpleado;
import Enumeraciones.EstadoEmpleado;
import Interfaces.IVisualizable;

import java.time.LocalDate;

public class Empleado extends Persona implements IVisualizable {

    //Atributos
    private int idEmpleado;
    private static int contadorId = 0;
    private CargoEmpleado cargo;
    private EstadoEmpleado estado;

    public Empleado(String nombre, String apellido, String dni, String email, LocalDate fechaNacimiento,CargoEmpleado cargo){
        super();
        this.idEmpleado = contadorId++;
        this.cargo = cargo;
    }

    //Getters y Setters
    public CargoEmpleado getCargo() {
        return cargo;
    }

    public void setCargo(CargoEmpleado cargo) {
        this.cargo = cargo;
    }

    public EstadoEmpleado getEstado() {return estado; }

    public void setEstado(EstadoEmpleado estado) { this.estado = estado; }

    // METODOS

    public boolean estaActivo() {
        return estado.equals(EstadoEmpleado.ALTA);
    }



    @Override
    public void verCartelera() {

    }

    @Override
    public void verReservas() {

    }

    @Override
    public void verFunciones() {

    }
}
