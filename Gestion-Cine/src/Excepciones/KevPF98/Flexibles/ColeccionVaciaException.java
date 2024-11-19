package Excepciones.KevPF98.Flexibles;

public class ColeccionVaciaException extends Exception{

    /// Suele usarse para indicar que una colección de coincidencias, en una búsqueda, quedó vacía.
    /// Lógica similar a ElementoNoEncontrado, pero para manejarse en conjuntos de datos.

    // Constructor:

    public ColeccionVaciaException(String mensaje){
        super(mensaje);
    }

    public ColeccionVaciaException(){
        super("Colección vacía.");
    }

}
