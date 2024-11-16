package Excepciones;

public class ElementoNoEncontradoException extends Exception{

    //Constructor:

    public ElementoNoEncontradoException(String mensaje){
        super(mensaje);
    }

    public ElementoNoEncontradoException(){
        super("No se hallaron coincidencias.");
    }

}
