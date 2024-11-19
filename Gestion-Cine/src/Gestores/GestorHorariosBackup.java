package Gestores;

import Enumeraciones.DiasSemana;
import Modelos.Cine.Horario;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GestorHorariosBackup {
    private HashMap<DiasSemana, Horario> horarios1 = new HashMap<>();
    private ArrayList<LocalTime> horarios = new ArrayList<>();
    private LocalTime horarioApertura;
    private LocalTime horarioCierre;
    private GestorPelicula listaPeliculas = new GestorPelicula();

    public GestorHorariosBackup() {
        GestorDatos datos = new GestorDatos();
        Scanner entrada = new Scanner(System.in);
        LocalTime aux;
        listaPeliculas.inicializarLista(); //Igual lo ideal sería traiga esto desde un JSON


        System.out.println("Indique el horario de apertura del cine: ");
        aux = this.horarioApertura = datos.ingresarHora(entrada);

        System.out.println("Indique el horario de cierre del cine: ");
        this.horarioCierre = datos.ingresarHora(entrada);

                        //-------------------------------------------------------------
                        ///FALTA AJUSTAR LA CONDICION DE CORTE Y QUE REDONDEE LA HORA|
                        //-------------------------------------------------------------

        //while(aux.isBefore(horarioCierre) && Duration.between(aux, horarioCierre).isPositive()){
        while(datos.confirmacion(entrada)){
            horarios.add(aux);
            System.out.println("\nSeleccione una pelicula de la cartelera para proyectar a las " + aux);
            listaPeliculas.listarCartelera();
            Duration tiempoProyeccion = listaPeliculas.buscarID(entrada).getDuracion();
            LocalTime horarioProyectado = aux.plus(tiempoProyeccion.plus(Duration.ofMinutes(30)));

            if (horarioProyectado.isAfter(horarioCierre)) {
                System.out.println("La película seleccionada no puede ser proyectada porque excede el horario de cierre (" + horarioCierre + ").");
                continue; // Vuelve al inicio del bucle para seleccionar otra película
            }
            // Actualizar aux si el horario es válido
            aux = horarioProyectado;
        }
    }
    public void listarHorarios(){
        for (LocalTime horario : horarios){
            System.out.println(horario);
        }
    }
}
