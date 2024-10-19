package Modelos.Cine;

import Enumeraciones.EstadoSala;
import Enumeraciones.TipoSala;

public class Sala {
    //Atributos
    private String numeroSala;
    private int totalButacas;
    private EstadoSala estado;
    private TipoSala tipo;

    //Constructor
    public Sala(String numeroSala,int totalButacas,TipoSala tipo){
        this.numeroSala = numeroSala;
        this.totalButacas = totalButacas;
        this.estado = EstadoSala.DISPONIBLE;
        this.tipo = tipo;
    }

    //Getters y Setters
    public String getNumeroSala() {
        return numeroSala;
    }

    public int getTotalButacas() {
        return totalButacas;
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

    public void setTotalButacas(int totalButacas) {
        this.totalButacas = totalButacas;
    }

    public void setTipo(TipoSala tipo) {
        this.tipo = tipo;
    }

}
