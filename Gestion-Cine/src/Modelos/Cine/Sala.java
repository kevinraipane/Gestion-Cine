package Modelos.Cine;

import Enumeraciones.EstadoSala;
import Enumeraciones.TipoSala;
import Excepciones.ButacaInexistenteException;
import Gestores.GestorButacas;

public class Sala {

    //Atributos:

    private String numeroSala;
    private EstadoSala estado;
    private TipoSala tipo;
    private GestorButacas butacas;

    //Constructor:

    public Sala(String numeroSala, TipoSala tipo, int totalButacas){
        this.numeroSala = numeroSala;
        this.estado = EstadoSala.DISPONIBLE;
        this.tipo = tipo;
        butacas = new GestorButacas(totalButacas);
    }

    //Métodos:

    public void reservarButaca(int numero, String cliente) throws ButacaInexistenteException { //delegamos
        butacas.reservarButaca(numero, cliente); //manejaremos la excepción en menu.
    }

    public void liberarButaca(int numero) throws ButacaInexistenteException { //delegamos
        butacas.liberarButaca(numero); //manejaremos la excepción en menu.
    }

    public void imprimirButacas(){
        butacas.imprimirButacas();
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

}
