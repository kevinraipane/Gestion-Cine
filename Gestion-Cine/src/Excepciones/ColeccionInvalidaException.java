package Excepciones;


import Enumeraciones.CollectionOrMap;

public class ColeccionInvalidaException extends Exception{

    //Constructor:

    public ColeccionInvalidaException(){
        super("Colección no soportada.");
    }

    public ColeccionInvalidaException(String mensaje){
        super(mensaje);
    }

    public ColeccionInvalidaException(CollectionOrMap implementa){
        if (implementa == CollectionOrMap.COLLECTION){
            super("Operación inválida para colecciones que no son del tipo par clave-valor.");
        }
        else{
            super("Operación inválida para colecciones de tipo par clave-valor.");
        }
    }

}
