package Excepciones;

public class NumButacaNegException extends ButacaInexistenteException{

    public NumButacaNegException(){
        super("El número de butaca no puede ser negativo.");
    }

    public NumButacaNegException(int numeroButaca) {
        super("El número de butaca no puede ser negativo: " + numeroButaca + ".");
    }

}
