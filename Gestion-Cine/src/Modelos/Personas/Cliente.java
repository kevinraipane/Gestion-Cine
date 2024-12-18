package Modelos.Personas;

import Excepciones.CodSeguridadInvalidoException;
import Interfaces.IVisualizable;
//import Modelos.Cine.Entrada;

import java.time.LocalDate;
import java.util.ArrayList;

public class Cliente extends Persona implements IVisualizable {

    private ArrayList<TarjetaBanco> tarjetasRegistradas;
    private ArrayList<Direccion> direcciones;

    public Cliente(int idUsuario, String nombre, String apellido, String dni, String email, LocalDate fechaNacimiento) {
        super(idUsuario,nombre, apellido, dni, email, fechaNacimiento);
    }

    public ArrayList<TarjetaBanco> getTarjetasRegistradas() {
        return tarjetasRegistradas;
    }

    public ArrayList<Direccion> getDirecciones() {
        return direcciones;
    }

    public void agregarTarjeta(TarjetaBanco tarjetaBanco) {
        this.tarjetasRegistradas.add(tarjetaBanco);
    }

    public void eliminarTarjeta(TarjetaBanco tarjetaBanco) {
        this.tarjetasRegistradas.remove(tarjetaBanco);
    }

    public void agregarDireccion(Direccion direccion) {
        this.direcciones.add(direccion);
    }

    public void eliminarDireccion(Direccion direccion) {
        this.direcciones.remove(direccion);
    }

    public TarjetaBanco usarTarjeta(String ultimosCuatro, int codSeguridad) throws CodSeguridadInvalidoException {
        for (TarjetaBanco tarjeta : tarjetasRegistradas) {
            if (tarjeta.getNumeros().substring(14).equals(ultimosCuatro)) {
                if (tarjeta.getCodSeguridad() == codSeguridad) {
                    return tarjeta;
                } else {
                    throw new CodSeguridadInvalidoException(codSeguridad);
                }
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return super.toString() +
                "direcciones=" + direcciones +
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
