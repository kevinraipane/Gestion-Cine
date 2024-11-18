package Excepciones;

public class DniInexistenteException extends RuntimeException {
    public DniInexistenteException(String message) {
        super(message);
    }
}
