package Excepciones;

public class CodSeguridadInvalidoException extends RuntimeException {
    private int codSeguridad;

    public CodSeguridadInvalidoException(String message) {
        super(message);
    }

    public CodSeguridadInvalidoException(int codSeguridad) {
        super("El codigo de seguridad ingresado es incorrecto");
        this.codSeguridad = codSeguridad;
    }
}
