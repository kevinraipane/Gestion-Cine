package Excepciones.KevPF98.Flexibles;


import Enumeraciones.CollectionOrMap;

public class ColeccionInvalidaException extends Exception{

    /// Excepción que, posiblemente, sólo se le lance al programador. Para asegurarse de no estar
    /// haciendo operaciones inválidas, al trabajar con la clase CRUD.

    //Constructor:

    public ColeccionInvalidaException(){
        super("Colección no soportada.");
    }

    public ColeccionInvalidaException(String mensaje){
        super(mensaje);
    }

    public ColeccionInvalidaException(CollectionOrMap implementa){
        /*if (implementa == CollectionOrMap.COLLECTION){
            super("Operación inválida para colecciones que no son del tipo par clave-valor.");
        }
        else{
            super("Operación inválida para colecciones de tipo par clave-valor.");
        }*/

        /// Tiraba error porque el super en un constructor DEBE SER la primera línea.
        /// La solución fue usar ternario; su síntaxis me permite dejar super arriba, a diferencia
        /// del if else.

        super(implementa == CollectionOrMap.COLLECTION
        /*? = if*/    ? "Operación inválida para colecciones que no son del tipo par clave-valor."
        /*: = else*/  : "Operación inválida para colecciones de tipo par clave-valor.");
    }

}
