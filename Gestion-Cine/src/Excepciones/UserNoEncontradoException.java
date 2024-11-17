package Excepciones;

public class UserNoEncontradoException extends RuntimeException
{
    public UserNoEncontradoException(String message) {
        super(message);
    }
}
