package Modelos.Cine;

import Enumeraciones.DiasSemana;

import java.time.Duration;
import java.time.LocalTime;

public class Horario {
    //Atributos
    private int idHorario;
    private static int contadorId = 0;
    private Pelicula pelicula;
    private Duration limpieza;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private DiasSemana dia;

    //Revisar como calcular horaFin
    //Constructor
    public Horario(Pelicula pelicula,LocalTime horaInicio,DiasSemana dia,Duration limpieza){
        this.idHorario = contadorId++;
        this.pelicula = pelicula;
        this.horaInicio = horaInicio;
        this.horaFin = horaInicio.plus(pelicula.getDuracion()).plus(limpieza);//20 minutos de limpieza
        this.dia = dia;
    }

    //Getters y Setters
    public Pelicula getPelicula() {
        return pelicula;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public DiasSemana getDia() {
        return dia;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setDia(DiasSemana dia) {
        this.dia = dia;
    }

    public void setIdHorario(int idHorario) {//Modificar, para que valide el nuevo horario
        this.idHorario = idHorario;
    }

    public void setHoraFin(LocalTime horaFin) {//Modificar, para que valide el nuevo horario
        this.horaFin = horaFin;
    }
}
