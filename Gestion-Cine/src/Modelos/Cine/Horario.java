package Modelos.Cine;

import Enumeraciones.*;
import Gestores.Cine.GestorPelicula;
import java.time.Duration;
import java.time.LocalTime;

public class Horario {
    //Atributos
    private int idHorario;
    private static int contadorId = 0;
    private Duration limpieza = Duration.ofMinutes(30);
    private LocalTime horaInicio;
    private LocalTime horaFin;

    //Constructor
    public Horario(LocalTime horaInicio, Duration duracionPelicula){
        this.idHorario = contadorId++;
        this.horaInicio = horaInicio;
        this.horaFin = horaInicio.plus(duracionPelicula.plus(limpieza));
    }

    @Override
    public String toString() {
        return "Horario ID" + idHorario + ":" +
                "\nDía: " +
                "\nHora de inicio: " + horaInicio +
                "\nHora de finalización: " + horaFin;
    }


    //-----------------------------------------------------Getters y Setters-------------------------------------------------


    public int getIdHorario() {
        return idHorario;
    }

    public Duration getLimpieza() {
        return limpieza;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

}
