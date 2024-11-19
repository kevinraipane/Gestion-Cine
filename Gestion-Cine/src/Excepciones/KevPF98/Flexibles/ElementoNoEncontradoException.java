package Excepciones.KevPF98.Flexibles;

public class ElementoNoEncontradoException extends Exception{

    /// No hubo coincidencias en la colección.

    //Constructor:

    public ElementoNoEncontradoException(String mensaje){
        super(mensaje);
    }

    public ElementoNoEncontradoException(){
        super("No se hallaron coincidencias.");
    }

}
