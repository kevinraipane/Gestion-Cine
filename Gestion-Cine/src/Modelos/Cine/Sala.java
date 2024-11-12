package Modelos.Cine;

import Enumeraciones.EstadoSala;
import Enumeraciones.TipoSala;
import Gestores.Cine.GestorButacas;

import java.util.Objects;

public class Sala {

    //Atributos:

    private String numeroSala;
    private EstadoSala estado;
    private TipoSala tipo;
    private int totalButacas;
    private GestorButacas butacasDisponibles; //Solamente informa la cantidad de butacas
    //por ser una propiedad de sala.

    //Constructor:

    public Sala(String numeroSala, TipoSala tipo, int totalButacas){
        this.numeroSala = numeroSala;
        this.estado = EstadoSala.DISPONIBLE;
        this.tipo = tipo;
        this.totalButacas = totalButacas;
        this.butacasDisponibles = new GestorButacas(totalButacas);
    }

    //Métodos:

    //Ver sus butacas disponibles y reservadas:

    public void verButacas(){
        butacasDisponibles.imprimirButacas();
    }

    //Getters y Setters:

    public String getNumeroSala() {
        return numeroSala;
    }

    public EstadoSala getEstado() {
        return estado;
    }

    public TipoSala getTipo() {
        return tipo;
    }

    public void setNumeroSala(String numeroSala) {
        this.numeroSala = numeroSala;
    }

    public void setTipo(TipoSala tipo) {
        this.tipo = tipo;
    }

    public void setEstado(EstadoSala estado) {
        this.estado = estado;
    }

    public void setTotalButacas(int totalButacas) {
        this.totalButacas = totalButacas;
        this.butacasDisponibles = new GestorButacas(totalButacas);
    }

    public GestorButacas getTotalButacas() {
        return butacasDisponibles;
    }

    //Equals y HashCode:

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Sala sala = (Sala) object;
        return Objects.equals(numeroSala, sala.numeroSala);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numeroSala);
    }

    //toString:

    @Override
    public String toString() {
        return "\n-----------------\n" +
                "Numero de sala: " + numeroSala + ".\n" +
                "Estado actual de la sala: " + estado + ".\n" +
                "Tipo de sala: " + tipo + ".\n" +
                "Capacidad máxima de personas: " + totalButacas +
                ".\n" +
                "\n-----------------\n";
    }
}
