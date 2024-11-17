import Excepciones.SeleccionInvalidaException;
import Excepciones.StringEnBlancoException;
import Gestores.GestorPelicula;
import Modelos.Cine.Pelicula;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        GestorPelicula peliculas = new GestorPelicula();
        peliculas.inicializarLista();
        peliculas.buscar(new Scanner(System.in));
///FALTA HACER EL BUCLE EN EL MENU DE GESTORPELICULA
        //Pelicula peliculaNueva = new Pelicula((Pelicula) null);
        //Pelicula peliculaModificada = new Pelicula(peliculas.);


        /*Pelicula aux = new Pelicula("default");
        Pelicula aux2 = new Pelicula("default");
        peliculas.agregarPelicula(aux);
        peliculas.agregarPelicula(aux2);
        peliculas.listarProximosEstrenos();
*/

        //System.out.println(new GestorEnums<>(EstadoPelicula.class).formatearEnum(EstadoPelicula.EN_CARTELERA));


    }
}