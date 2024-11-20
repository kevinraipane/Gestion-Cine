package Gestores.Cine;

import Enumeraciones.DiasSemana;
import Gestores.Funcionales.GestorDatos;
import Gestores.Funcionales.GestorEnums;
import Modelos.Cine.Horario;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GestorHorarios {
    Scanner entrada = new Scanner(System.in);
    GestorDatos datos = new GestorDatos();

    private HashMap<DiasSemana, ArrayList<Horario>> horariosSemana = new HashMap<>();
    private LocalTime horarioApertura;
    private LocalTime horarioCierre;
    private GestorPelicula listaPeliculas = new GestorPelicula();



    public GestorHorarios() {
        System.out.println("Indique el horario de apertura del cine: ");
        this.horarioApertura = datos.ingresarHora(entrada);

        System.out.println("Indique el horario de cierre del cine: ");
        this.horarioCierre = datos.ingresarHora(entrada);

        if(horarioApertura.isAfter(horarioCierre)){
            throw new IllegalArgumentException("El horario de apertura debe ser antes del cierre");
        }

        for(DiasSemana dia : DiasSemana.values()){
            horariosSemana.put(dia,new ArrayList<>()); //Se inicializa el map con todos los dias de la semana
        }
    }

    public Horario crearNuevoHorario(DiasSemana dia,Duration duracionPelicula) {
        try {
            // Solicitar al usuario la hora de inicio
            System.out.println("Ingrese la hora de inicio (HH:mm):");
            String horaInicioStr = entrada.nextLine();
            LocalTime horaInicio = LocalTime.parse(horaInicioStr);

            // Validar que esté dentro del horario de apertura y cierre
            if (horaInicio.isBefore(horarioApertura) || horaInicio.isAfter(horarioCierre)) {
                throw new IllegalArgumentException("La hora de inicio debe estar entre " + horarioApertura + " y " + horarioCierre);
            }

            // Crear el horario
            Horario nuevoHorario = new Horario(horaInicio, duracionPelicula);

            // Validar que el horario no exceda la hora de cierre
            if (nuevoHorario.getHoraFin().isAfter(horarioCierre)) {
                throw new IllegalArgumentException("El horario supera la hora de cierre: " + horarioCierre);
            }

            // Validar que el horario no se solape con otros horarios existentes
            ArrayList<Horario> horariosDia = obtenerHorarios(dia);
            for (Horario horarioExistente : horariosDia) {
                if (solapanHorarios(horarioExistente, nuevoHorario)) {
                    System.out.println("El horario se solapa con otro existente.");
                    return null; // Si se solapa, retorna null
                }
            }

            System.out.println("Horario creado exitosamente: " + nuevoHorario.getHoraInicio() + " - " + nuevoHorario.getHoraFin());
            return nuevoHorario;

        } catch (Exception e) {
            System.out.println("Error al crear el horario: " + e.getMessage());
            return null; // Retorna null si hubo un error
        }
    }

    /// AGREGAR UN NUEVO HORARIO A UN DIA ESPECIFICO ----------------------------------------------------

    public void agregarHorario(DiasSemana dia,Horario nuevoHorario){
        ArrayList<Horario> horariosDia = obtenerHorarios(dia);
        horariosDia.add(nuevoHorario);
    }

    /// VERIFICAR SI DOS HORARIOS SE SOLAPAN ------------------------------------------------------------

    private boolean solapanHorarios(Horario h1, Horario h2) {
        return !(h1.getHoraFin().isBefore(h2.getHoraInicio()) ||
                h2.getHoraFin().isBefore(h1.getHoraInicio()));
    }

    /// OBTENER HORARIOS PARA UN DIA ESPECIFICO

    public ArrayList<Horario> obtenerHorarios(DiasSemana dia){
        return horariosSemana.get(dia);
    }



    public void listarHorarios(Scanner entrada){
        System.out.println("Seleccione el dia a mostrar:");
        DiasSemana dia = new GestorEnums<>(DiasSemana.class).seleccionarElemento(entrada);
        int contador = 0;
        for (Horario horario : horariosSemana.get(dia)){
            ++contador;
            System.out.println("Funcion " + contador + ": " + horario);
        }
    }
}
