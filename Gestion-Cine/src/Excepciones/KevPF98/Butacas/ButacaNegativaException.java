package Excepciones.KevPF98.Butacas;

public class ButacaNegativaException extends ButacaInexistenteException {

    public ButacaNegativaException(){
        super("El número de butaca no puede ser negativo.");
    }

    public ButacaNegativaException(int numeroButaca) {
        super("El número de butaca no puede ser negativo: " + numeroButaca + ".");
    }

}
