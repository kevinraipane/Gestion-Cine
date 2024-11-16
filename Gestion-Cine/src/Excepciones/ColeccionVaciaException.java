package Excepciones;

public class ColeccionVaciaException extends Exception{

    //Constructor:

    public ColeccionVaciaException(String mensaje){
        super(mensaje);
    }

    public ColeccionVaciaException(){
        super("Colección vacía.");
    }

}
