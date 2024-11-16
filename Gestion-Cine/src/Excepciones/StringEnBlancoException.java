package Excepciones;

public class StringEnBlancoException extends RuntimeException {
    public StringEnBlancoException(){
        super("ERROR: No pueden haber campos en blanco");
    }

    public StringEnBlancoException(String message) {
        super(message);
    }
}
