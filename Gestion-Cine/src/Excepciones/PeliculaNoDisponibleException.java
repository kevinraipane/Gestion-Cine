package Excepciones;

public class PeliculaNoDisponibleException extends RuntimeException {
    public PeliculaNoDisponibleException(String message) {
        super(message);
    }

    public PeliculaNoDisponibleException() {
        super("ERROR: La película seleccionada no está disponible");

    }
}
