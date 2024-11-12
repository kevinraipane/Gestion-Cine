package Gestores.Cine;

import Modelos.Cine.Funcion;

import java.util.HashMap;

public class GestorFunciones {

    //Atributos:

    private HashMap<Integer, Funcion> funciones; //No me importa el orden. Eficiente
                                         //para búsqueda, inserción, eliminación.

    //Constructor:

    public GestorFunciones(){
        this.funciones = new HashMap<>();
    }

}
