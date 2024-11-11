package Excepciones;

public class DominioInvalidoException extends RuntimeException{
    public DominioInvalidoException(String message) {
        super(message);
    }
}
