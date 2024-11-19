package Excepciones;

public class DNIExistenteException extends Exception {
    String dni;

    public DNIExistenteException(String dni) {
        super("El DNI ingresado ya se encuentra en la base de datos.");
        this.dni = dni;
    }

    public DNIExistenteException(String dni, String message) {
        super(message);
        this.dni = dni;
    }
}
