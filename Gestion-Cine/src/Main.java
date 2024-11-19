import Excepciones.SeleccionInvalidaException;
import Excepciones.StringEnBlancoException;
import Gestores.GestorHorarios;
import Gestores.GestorPelicula;
import Modelos.Cine.Pelicula;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        GestorHorarios horarios = new GestorHorarios();
        horarios.cargarMap();
        horarios.listarHorarios(new Scanner(System.in));
        /*
        Scanner entrada = new Scanner(System.in);
        GestorPelicula peliculas = new GestorPelicula();
        peliculas.inicializarLista();
        //peliculas.agregarPelicula(new Pelicula());


        Pelicula peli = peliculas.buscarID(entrada);
        peli.modificarPelicula(entrada);

        //peliculas.buscar(new Scanner(System.in));
*/

    }
}