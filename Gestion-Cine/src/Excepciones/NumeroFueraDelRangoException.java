package Excepciones;

public class NumeroFueraDelRangoException extends RuntimeException {


    public NumeroFueraDelRangoException() {
        super("ERROR: Ha ingresado un número fuera del rango de opciones");
    }

    public NumeroFueraDelRangoException(String message) {
        super(message);
    }
}
