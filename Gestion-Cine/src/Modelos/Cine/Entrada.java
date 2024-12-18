package Modelos.Cine;

import Enumeraciones.EstadoFuncion;
import Enumeraciones.TipoTicket;
import Excepciones.ButacaEnEstadoActualException;
import Excepciones.ButacaInexistenteException;
//import Modelos.General.BaseEntity;

import java.util.Objects;

public class Entrada /*extends BaseEntity<Integer>*/ {

    //Atributos:

    private int idBoleta;
    public static int contador = 0;
    private Funcion funcion;
    private TipoTicket tipoTicket;
    private EstadoFuncion estadoFuncion;

    //Constructor:

    public Entrada(Funcion funcion, TipoTicket tipoTicket, EstadoFuncion estadoFuncion) {
        this.idBoleta = ++contador;
        //super(++contador);
        this.funcion = funcion;
        this.tipoTicket = tipoTicket;
        this.estadoFuncion = estadoFuncion;
    }

    //Métodos:

    public void reservarButaca(int numero, String cliente) throws ButacaInexistenteException, ButacaEnEstadoActualException { //delegamos
        funcion.getSala().getTotalButacas().reservarButaca(numero, cliente);
        //manejaremos la excepción en menu.
    }


    public void liberarButaca(int numero) throws ButacaInexistenteException, ButacaEnEstadoActualException { //delegamos
        funcion.getSala().getTotalButacas().liberarButaca(numero);
        //manejaremos la excepción en menu.
    }

    //Getters y setters:

    public int getIdBoleta() {
        return idBoleta;
        //return getAtributoIdentificador();
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }

    public TipoTicket getTipoTicket() {
        return tipoTicket;
    }

    public void setTipoTicket(TipoTicket tipoTicket) {
        this.tipoTicket = tipoTicket;
    }

    public EstadoFuncion getEstadoFuncion() {
        return estadoFuncion;
    }

    public void setEstadoFuncion(EstadoFuncion estadoFuncion) {
        this.estadoFuncion = estadoFuncion;
    }

    //Equals y HashCode:

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Entrada entrada = (Entrada) object;
        return idBoleta == entrada.idBoleta;
        //return Objects.equals(getIdBoleta(), entrada.getIdBoleta());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idBoleta);
        //return Objects.hash(super.hashCode());
    }

    //toString:

    @Override
    public String toString() {
        return  "\n-----------------\n" +
                "Id de la boleta: " + /*idBoleta*/ getIdBoleta() + ".\n" +
                "Función: " + funcion + ".\n" +
                "Tipo de entrada: " + tipoTicket + ".\n" +
                "Estado de la función: " + estadoFuncion +
                ".\n" +
                "\n-----------------\n";
    }

}
