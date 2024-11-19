package Gestores;

import Enumeraciones.DiasSemana;
import Modelos.Cine.Horario;
import Modelos.Cine.Pelicula;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GestorHorarios {
    private HashMap<DiasSemana, ArrayList<LocalTime>> horariosSemana = new HashMap<>();
    private ArrayList<LocalTime> horariosPorDia = new ArrayList<>();
    private LocalTime horarioApertura;
    private LocalTime horarioCierre;
    private GestorPelicula listaPeliculas = new GestorPelicula();

    public GestorHorarios() {
        GestorDatos datos = new GestorDatos();
        Scanner entrada = new Scanner(System.in);

        LocalTime aux;

        System.out.println("Indique el horario de apertura del cine: ");
        aux = this.horarioApertura = datos.ingresarHora(entrada);

        System.out.println("Indique el horario de cierre del cine: ");
        this.horarioCierre = datos.ingresarHora(entrada);
    }

    public void cargarMap(){
        GestorDatos datos = new GestorDatos();
        Scanner entrada = new Scanner(System.in);
        System.out.println("Seleccione el dia a cargar:");
        DiasSemana dia = new GestorEnums<>(DiasSemana.class).seleccionarElemento(entrada);
        horariosSemana.put(dia, cargarHorarios(datos, entrada));
    }

    public ArrayList<LocalTime> cargarHorarios(GestorDatos datos, Scanner entrada) {
        LocalTime aux = horarioApertura;
        listaPeliculas.inicializarLista(); //Igual lo ideal sería traiga esto desde un JSON
        listaPeliculas.listarCartelera();
        //Hay que verificar que solo pueda seleccionar las que estan ahi, necesito agregar un metodo que devuelva
        //un arraylist con la cartelera (esta funcion solo imprime)

        while(aux.isBefore(horarioCierre)){
            horariosPorDia.add(aux);
            System.out.println("\nSeleccione una pelicula de la cartelera para proyectar a las " + aux);

            Duration tiempoProyeccion = listaPeliculas.buscarID(entrada).getDuracion();
            LocalTime horarioProyectado = aux.plus(tiempoProyeccion.plus(Duration.ofMinutes(30)));

            int resto = aux.getMinute() % 10;
            if(resto !=0){
                int redondeo = 10-resto;
                aux = aux.plus(Duration.ofMinutes(resto));
            }//Esta comprobacion lo que hace, en teoria, es que se fija si el tiempo de proyeccion es divisible por 10
            // y si no lo es, le suma la diferencia para que llegue (asi no hay funciones que empiecen a las 16:03, por ejemplo)

            ///Update: no estaria funcionando

            if (horarioProyectado.isAfter(horarioCierre)) {
                System.out.println("La película seleccionada no puede ser proyectada porque excede el horario de cierre (" + horarioCierre + ").");
                if(!datos.confirmacion(entrada)){ //La idea de que pregunte continuar es ver si quiere agregar una pelicula mas corta
                    aux = horarioCierre.plus(Duration.ofMinutes(5));

                    //break; //por las dudas????
                    //En teoria lo que hace es que si le decis que no,
                    //modifica el aux para que ya no cumpla la condicion del while
                    //(Estoy en un IDE online y no pude importar el proyecto, asi que todo es inchequeable)
                }
            }
            // Actualizar aux si el horario es válido
            aux = horarioProyectado;
        }
        return horariosPorDia;
    }


    public void listarHorarios(Scanner entrada){
        System.out.println("Seleccione el dia a mostrar:");
        DiasSemana dia = new GestorEnums<>(DiasSemana.class).seleccionarElemento(entrada);
        int contador = 0;
        for (LocalTime horario : horariosSemana.get(dia)){
            ++contador;
            System.out.println("Funcion " + contador + ": " + horario);
        }
    }
}
