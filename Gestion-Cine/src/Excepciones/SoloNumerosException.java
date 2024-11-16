package Excepciones;

public class SoloNumerosException extends RuntimeException {
    public SoloNumerosException(){
        super("ERROR: Solo se permiten números");
    }

    public SoloNumerosException(String message) {
        super(message);
    }
}
