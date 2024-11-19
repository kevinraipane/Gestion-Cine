package Excepciones.KevPF98.Butacas;

public class ButacaInexistenteException extends Exception{

    public ButacaInexistenteException(int numeroButaca){
        super("El número de butaca: " + numeroButaca + " no es válido.");
    }

    public ButacaInexistenteException(String mensaje){
        super(mensaje);
    }

}
