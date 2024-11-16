package Modelos.Cine;

import Enumeraciones.EstadoFuncion;
//import Modelos.General.BaseEntity;

import java.util.Objects;

public class Funcion /*extends BaseEntity<Integer>*/ {

    //Atributos:

    private int idFuncion;
    public static int contador = 0;
    private Pelicula pelicula;
    private Sala sala;
    private Horario horario;
    private double valor;
    private EstadoFuncion estadoFuncion;

    //Constructor:

    public Funcion(Pelicula pelicula, Sala sala, Horario horario, double valor, EstadoFuncion estadoFuncion) {
        this.idFuncion = ++contador;
        //super(++contador);
        this.pelicula = pelicula;
        this.sala = sala;
        this.horario = horario;
        this.valor = valor;
        this.estadoFuncion = estadoFuncion;
    }

    //Getters y setters:

    public int getIdFuncion() {
        return idFuncion;
        //return getAtributoIdentificador();
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public EstadoFuncion getEstadoFuncion() {
        return estadoFuncion;
    }

    public void setEstadoFuncion(EstadoFuncion estadoFuncion) {
        this.estadoFuncion = estadoFuncion;
    }

    //Equals y HashCode:

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Funcion funcion = (Funcion) object;
        return idFuncion == funcion.idFuncion;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idFuncion);
    }

    /*@Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Funcion funcion = (Funcion) object;
        return Objects.equals(getIdFuncion(), funcion.getIdFuncion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }*/

    //toString:

    public String toString() {
        return  "\n-----------------\n" +
                "Id de la función: " + /*idFuncion*/ getIdFuncion() + ".\n" +
                "Película: " + pelicula + ".\n" +
                "Sala: " + sala + ".\n" +
                "Horario: " + horario + ".\n" +
                "Valor: " + valor + ".\n" +
                "Estado actual de la función: " + estadoFuncion + ".\n" +
                "\n-----------------\n";
    }

}
