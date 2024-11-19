package Excepciones.KevPF98.Flexibles;

public class ProhibidoEstadoActualException extends Exception{

    /// El objeto tiene prohibido cambiar su atributo al deseado, sea porque ya tiene asignado ese
    /// estado, o porque no debería poder cambiar desde el actual al deseado (Ej: de sala ocupada a sala
    /// dada de baja).

    public ProhibidoEstadoActualException(String mensaje){
        super(mensaje);
    }

}
