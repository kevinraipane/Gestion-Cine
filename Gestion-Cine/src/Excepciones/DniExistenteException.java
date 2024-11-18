package Excepciones;

public class DniExistenteException extends Exception {
    String dni;

    public DniExistenteException(String dni) {
        super("El DNI ingresado ya se encuentra en la base de datos.");
        this.dni = dni;
    }

    public DniExistenteException(String dni, String message) {
        super(message);
        this.dni = dni;
    }
}
