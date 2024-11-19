package Gestores.Cine;

import Enumeraciones.TipoColeccion;
import Excepciones.KevPF98.Flexibles.ColeccionInvalidaException;
import Excepciones.KevPF98.Flexibles.ColeccionVaciaException;
import Excepciones.KevPF98.Flexibles.ElementoEnColeccionException;
import Excepciones.KevPF98.Flexibles.ElementoNoEncontradoException;
import Modelos.Cine.Entrada;
import Modelos.Cine.Funcion;
import Modelos.General.Crud;

import java.util.List;

public class GestorEntradas {

    //Atributos:

    //private List<Entrada> entradasCrud; //List porque me importa el orden de inserción;
                                          //o sea, el orden en el que se vendieron.
    private Crud<Void, Entrada> entradasCrud;

    //Constructor:

    public GestorEntradas() throws ColeccionInvalidaException {
        this.entradasCrud = new Crud<>(TipoColeccion.ARRAYLIST);
    }

    //Métodos:

    public void registrarNuevaEntrada(Entrada entrada) throws ElementoEnColeccionException {
        entradasCrud.agregar (entrada, false);
    }

    //¿Obtener entradasCrud vendidas en un día?

    ///   Registro entradasCrud correspondientes a 1 función.
    ///                   Lógica:
    /// 1. En un case del menu, primero se buscaría la función que el admin desee, pasándole id,
    /// en el gestor de funciones.
    /// 2. Después, almacenamos esa función en un objeto.
    /// 3. Por ultimo le pasamos ese objeto de parámetro a esta función.

    public List<Entrada> obtenerEntradasVendidasParaUnaFuncion(Funcion funcion) throws ColeccionInvalidaException, ColeccionVaciaException {
        List<Entrada> entradasDeLaFuncion = entradasCrud.buscarConjuntoCollection(entrada -> entrada.getFuncion().equals(funcion));
        return entradasDeLaFuncion;
    }

    public void listarEntradasVendidasParaUnaFuncion(Funcion funcion) throws ColeccionInvalidaException, ColeccionVaciaException, ElementoNoEncontradoException {
        //Misma lógica que en el ejercicio de arriba.
        entradasCrud.listarEnCollectionPorCondicion(entrada -> entrada.getFuncion().equals(funcion));
    }

    public int contarEntradasVendidasParaUnaFuncion(Funcion funcion) throws ColeccionInvalidaException, ColeccionVaciaException {
        List<Entrada> entradasDeLaFuncion = obtenerEntradasVendidasParaUnaFuncion(funcion);
        return entradasDeLaFuncion.size();
    }

}
