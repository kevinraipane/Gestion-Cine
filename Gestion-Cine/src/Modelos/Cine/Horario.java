package Modelos.Cine;

import Enumeraciones.*;
import Excepciones.NumeroFueraDelRangoException;
import Excepciones.SoloNumerosException;
import Excepciones.StringEnBlancoException;
import Gestores.GestorDatos;
import Gestores.GestorEnums;
import Gestores.GestorPelicula;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Horario {
    //Atributos
    private int idHorario;
    private static int contadorId = 0;
    private Pelicula pelicula;
    private Duration limpieza = Duration.ofMinutes(30);
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private DiasSemana dia;
    private GestorPelicula listaPeliculas = new GestorPelicula();

    //Constructor
    public Horario(LocalTime horaInicio, Duration duracionPelicula, DiasSemana dia){
        this.idHorario = contadorId++;
        this.horaInicio = horaInicio;
        this.horaFin = horaInicio.plus(pelicula.getDuracion()).plus(limpieza);
        this.dia = dia;
    }
/*
    public Horario(){
        Scanner entrada = new Scanner(System.in);
        int datosValidos = 0;
        this.idHorario = ++contadorId;
        listaPeliculas.inicializarLista(); //Igual lo ideal sería traiga esto desde un JSON

        while(datosValidos==0){ //Ingresar el titulo
            try{
                System.out.println("Seleccione la película a proyectar:\n");
                listaPeliculas.listarCartelera();
                setPelicula(listaPeliculas.buscarID(entrada));
                datosValidos++;
            }catch (NumeroFueraDelRangoException | SoloNumerosException e){
                System.out.println(e.getMessage());
            }
        this.horaInicio
        }

        System.out.println("Estos son los datos ingresados" + this);

    }
*/

    @Override
    public String toString() {
        return "Horario ID" + idHorario + ":" +
                "\nDía: " + dia +
                "\nHora de inicio: " + horaInicio +
                "\nHora de finalización: " + horaFin;
    }


    //-----------------------------------------------------Getters y Setters-------------------------------------------------


    public int getIdHorario() {
        return idHorario;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
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

    public DiasSemana getDia() {
        return dia;
    }

    public void setDia(DiasSemana dia) {
        this.dia = dia;
    }
}
