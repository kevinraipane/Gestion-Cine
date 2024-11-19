package Excepciones.KevPF98.Butacas;

public class ButacaInhabilitadaException extends Exception{

    // Constructor:

    public ButacaInhabilitadaException(){
        super("La butaca no se encuentra en condiciones de ser reservada.");
    }

}
