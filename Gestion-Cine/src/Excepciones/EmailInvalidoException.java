package Excepciones;

public class EmailInvalidoException extends Exception {
    public EmailInvalidoException(String message) {
        super(message);
    }
}