package Excepciones;

import Enumeraciones.CollectionOrMap;

public class ElementoEnColeccionException extends Exception{

    //Constructor:

    public ElementoEnColeccionException(String mensaje){
        super(mensaje);
    }

    public ElementoEnColeccionException(CollectionOrMap implementa){
        if (implementa == CollectionOrMap.COLLECTION){
            super("Error: La colección ya contiene ese elemento.");
        }
        else{
            super("Error: La colección ya contiene esta clave.");
        }
    }

}
