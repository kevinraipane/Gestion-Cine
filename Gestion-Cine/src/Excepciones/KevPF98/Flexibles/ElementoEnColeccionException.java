package Excepciones.KevPF98.Flexibles;

import Enumeraciones.CollectionOrMap;

public class ElementoEnColeccionException extends Exception{

    /// Evitar repetir cualquier elemento en cualquier tipo de colección.

    // Constructor:

    public ElementoEnColeccionException(String mensaje){
        super(mensaje);
    }

    public ElementoEnColeccionException(CollectionOrMap implementa){
        /*super (implementa == CollectionOrMap.COLLECTION){
            super("Error: La colección ya contiene ese elemento.");
        }
        else{
            super("Error: La colección ya contiene esta clave.");
        }*/

        /// Tiraba error porque el super en un constructor DEBE SER la primera línea.
        /// La solución fue usar ternario; su síntaxis me permite dejar super arriba, a diferencia
        /// del if else.

        super (implementa == CollectionOrMap.COLLECTION
                ? "Error: La colección ya contiene ese elemento."
                : "Error: La colección ya contiene esta clave.");
    }

}
