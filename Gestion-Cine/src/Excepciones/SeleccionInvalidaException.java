package Excepciones;

public class SeleccionInvalidaException extends RuntimeException {
    public SeleccionInvalidaException(){
        super("ERROR: Opción no válida");
    }

    public SeleccionInvalidaException(String message) {
        super(message);
    }
}
