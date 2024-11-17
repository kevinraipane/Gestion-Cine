package Excepciones;

public class UsernameNoValidoException extends RuntimeException {
    public UsernameNoValidoException(String message) {
        super(message);
    }
}
