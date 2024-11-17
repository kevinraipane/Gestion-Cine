package Excepciones;

public class PasswordNoValidaException extends RuntimeException {
    public PasswordNoValidaException(String message) {
        super(message);
    }
}
