package Modelos.Personas;

import Enumeraciones.CargoEmpleado;
import Enumeraciones.EstadoEmpleado;
import Interfaces.IVisualizable;

import java.time.LocalDate;

public class Empleado extends Persona implements IVisualizable {

    /// ATRIBUTOS

    private CargoEmpleado cargo;
    private EstadoEmpleado estado;
    private LocalDate fechaIngreso;

    final static int mesesDePrueba = 6;


    /// CONSTRUCTOR

    public Empleado(int idUsuario,String nombre, String apellido, String dni, String email, LocalDate fechaNacimiento, CargoEmpleado cargo){
        super(idUsuario,nombre, apellido, dni, email, fechaNacimiento);
        this.cargo = cargo;
        this.estado = EstadoEmpleado.A_PRUEBA;
        this.fechaIngreso = LocalDate.now();
    }

    ///GETTERS Y SETTERS

    public CargoEmpleado getCargo() {
        return cargo;
    }

    public void setCargo(CargoEmpleado cargo) {
        this.cargo = cargo;
    }

    public EstadoEmpleado getEstado() {return estado; }

    public void setEstado(EstadoEmpleado estado) { this.estado = estado; }


    /// METODOS

    public boolean estaActivo() {
        return (estado.equals(EstadoEmpleado.ALTA) || (estado.equals(EstadoEmpleado.A_PRUEBA)));
    }

    public void aPlantaPermanente() {
        if (LocalDate.now().minusMonths(mesesDePrueba).isAfter(this.fechaIngreso)) {
            this.estado = EstadoEmpleado.ALTA;
        }

    }

    @Override
    public String toString() {
        return super.toString() +
                "cargo=" + cargo +
                ", estado empleado=" + estado +
                ", fechaIngreso=" + fechaIngreso +
                '}';
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
