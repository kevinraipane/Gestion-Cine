package Gestores;

import Enumeraciones.*;

import Excepciones.NumeroFueraDelRangoException;
import Excepciones.SoloNumerosException;
import Modelos.Cine.Pelicula;

import java.time.Duration;
import java.util.HashMap;
import java.util.Scanner;

public class GestorPelicula {
    private HashMap<Integer, Pelicula> listaPeliculas;

    public GestorPelicula(){
        listaPeliculas = new HashMap<>();
    }

    //Metodo que carga 30 peliculas
    public void inicializarLista(){
        listaPeliculas.put(1, new Pelicula(
                "El pollito que no volvió", Idioma.INGLES, Idioma.ESPAÑOL, Duration.ofMinutes(148),
                "Mario Neta", "John Johnson", 2010, Paises.ESTADOS_UNIDOS,
                ClasificacionEdad.MAS_18, GeneroPelicula.TERROR, EstadoPelicula.EN_CARTELERA
        ));

        listaPeliculas.put(2, new Pelicula(
                "Las Sombras que Olvidaron Su Dueño", Idioma.ITALIANO, Idioma.ESPAÑOL, Duration.ofMinutes(136),
                "Joel Silver", "Donato Macarroni", 1999, Paises.ITALIA,
                ClasificacionEdad.MAS_13, GeneroPelicula.SUSPENSO, EstadoPelicula.NO_DISPONIBLE
        ));

        listaPeliculas.put(3, new Pelicula(
                "El regreso de la milanesa perdida", Idioma.ESPAÑOL, Idioma.SIN, Duration.ofMinutes(132),
                "Sigrid Eltoya", "Debora Melocotón", 2019, Paises.ARGENTINA,
                ClasificacionEdad.ATP, GeneroPelicula.COMEDIA, EstadoPelicula.NO_DISPONIBLE
        ));

        listaPeliculas.put(4, new Pelicula(
                "La venganza del Wi-Fi", Idioma.ESPAÑOL, Idioma.SIN, Duration.ofMinutes(169),
                "George Peque", "David Miller", 2014, Paises.ESTADOS_UNIDOS,
                ClasificacionEdad.ATP, GeneroPelicula.COMEDIA, EstadoPelicula.NO_DISPONIBLE
        ));

        listaPeliculas.put(5, new Pelicula(
                "Mision imposible: Desenredar auriculares", Idioma.JAPONES, Idioma.ESPAÑOL, Duration.ofMinutes(181),
                "Hiroshi Yamazaki", "Sora Nakamura", 2019, Paises.JAPON,
                ClasificacionEdad.MAS_13, GeneroPelicula.ACCION, EstadoPelicula.EN_CARTELERA
        ));

        listaPeliculas.put(6, new Pelicula(
                "En busca del sandwich perdido", Idioma.INGLES, Idioma.ESPAÑOL, Duration.ofMinutes(118),
                "Taylor Lee", "Penny Truco", 2021, Paises.REINO_UNIDO,
                ClasificacionEdad.ATP, GeneroPelicula.DOCUMENTAL, EstadoPelicula.NO_DISPONIBLE
        ));

        listaPeliculas.put(7, new Pelicula(
                "Los zombis no saben bailar", Idioma.ITALIANO, Idioma.ESPAÑOL, Duration.ofMinutes(122),
                "Gino Longaniza", "Franco Tortellini", 2018, Paises.ITALIA,
                ClasificacionEdad.MAS_16, GeneroPelicula.ROMANCE, EstadoPelicula.EN_CARTELERA
        ));

        listaPeliculas.put(8, new Pelicula(
                "El gato que queria ser perro", Idioma.JAPONES, Idioma.ESPAÑOL, Duration.ofMinutes(100),
                "Aiko Fujimura", "Riku Tanaka", 2025, Paises.JAPON,
                ClasificacionEdad.ATP, GeneroPelicula.AVENTURA, EstadoPelicula.PROXIMAMENTE
        ));

        listaPeliculas.put(9, new Pelicula(
                "Yo Nunca Vi Televisión (Y Luego Sí Pero Después No)", Idioma.ESPAÑOL, Idioma.ESPAÑOL, Duration.ofMinutes(192),
                "Juanín", "Tulio Triviño", 2003, Paises.CHILE,
                ClasificacionEdad.ATP, GeneroPelicula.COMEDIA, EstadoPelicula.EN_CARTELERA
        ));

        listaPeliculas.put(10, new Pelicula(
                " Pizza vs hamburguesa: El duelo final", Idioma.INGLES, Idioma.ESPAÑOL,Duration.ofMinutes(152),
                "Francisco Dientes Largos", "Lola Mento", 2017, Paises.ESTADOS_UNIDOS,
                ClasificacionEdad.MAS_13, GeneroPelicula.ACCION, EstadoPelicula.NO_DISPONIBLE
        ));

        listaPeliculas.put(11, new Pelicula(
                "El enigma del cuchillo sin filo", Idioma.ITALIANO, Idioma.ESPAÑOL, Duration.ofMinutes(134),
                "Björn Ymirgo", "Anita Pasta", 2018, Paises.ITALIA,
                ClasificacionEdad.MAS_16, GeneroPelicula.SUSPENSO, EstadoPelicula.NO_DISPONIBLE
        ));

        listaPeliculas.put(12, new Pelicula(
                "Harry Potter y el horno microondas", Idioma.INGLES, Idioma.ESPAÑOL, Duration.ofMinutes(102),
                "Henry Patter", "J.M. Rowlind", 2026, Paises.REINO_UNIDO,
                ClasificacionEdad.ATP, GeneroPelicula.AVENTURA, EstadoPelicula.PROXIMAMENTE
        ));

        listaPeliculas.put(13, new Pelicula(
                "El drama del zapato izquierdo", Idioma.ESPAÑOL, Idioma.SIN, Duration.ofMinutes(90),
                "Odin Metido", "Yoselin García", 2001, Paises.MEXICO,
                ClasificacionEdad.ATP, GeneroPelicula.DOCUMENTAL, EstadoPelicula.NO_DISPONIBLE
        ));

        listaPeliculas.put(14, new Pelicula(
                "Me cortaron mal el pelo", Idioma.ESPAÑOL, Idioma.ESPAÑOL, Duration.ofMinutes(128),
                "Chascoberto", "Tulio Triviño", 2003, Paises.CHILE,
                ClasificacionEdad.ATP, GeneroPelicula.COMEDIA, EstadoPelicula.EN_CARTELERA
        ));

        listaPeliculas.put(15, new Pelicula(
                "La revancha del cucaracho volador", Idioma.ESPAÑOL, Idioma.SIN, Duration.ofMinutes(175),
                "Benito Camelon", "John Nieto", 2025, Paises.MEXICO,
                ClasificacionEdad.MAS_13, GeneroPelicula.COMEDIA, EstadoPelicula.PROXIMAMENTE
        ));

        listaPeliculas.put(16, new Pelicula(
                "60 sombras de atún", Idioma.INGLES, Idioma.ESPAÑOL, Duration.ofMinutes(154),
                " Queen Bender", "Arthur Misato", 1994, Paises.ESTADOS_UNIDOS,
                ClasificacionEdad.MAS_16, GeneroPelicula.DOCUMENTAL, EstadoPelicula.NO_DISPONIBLE
        ));

        listaPeliculas.put(17, new Pelicula(
                "El arrepentimiento de Juan Carlos Bodoque", Idioma.ESPAÑOL, Idioma.ESPAÑOL, Duration.ofMinutes(142),
                "Juan Carlos Bodoque", "Tulio Triviño", 2005, Paises.CHILE,
                ClasificacionEdad.ATP, GeneroPelicula.SUSPENSO, EstadoPelicula.EN_CARTELERA
        ));

        listaPeliculas.put(18, new Pelicula(
                "El susurro del papel higienico", Idioma.INGLES, Idioma.ESPAÑOL, Duration.ofMinutes(139),
                "Sven Tirrin", "Elizabeth Moore", 1999, Paises.ESTADOS_UNIDOS,
                ClasificacionEdad.MAS_18, GeneroPelicula.ACCION, EstadoPelicula.NO_DISPONIBLE
        ));

        listaPeliculas.put(19, new Pelicula(
                "El Laberinto de las Incongruencias Ontológicas", Idioma.RUSO, Idioma.ESPAÑOL, Duration.ofMinutes(152),
                "Ingrid Tulotodo", "Svetlana Romanova", 2008, Paises.RUSIA,
                ClasificacionEdad.MAS_18, GeneroPelicula.DOCUMENTAL, EstadoPelicula.NO_DISPONIBLE
        ));

        listaPeliculas.put(20, new Pelicula(
                "La Epistemología de la Nostalgia Futurista", Idioma.RUSO, Idioma.ESPAÑOL, Duration.ofMinutes(155),
                "Daichi Watanabe", "Alexei Petrov", 1999, Paises.RUSIA,
                ClasificacionEdad.MAS_18, GeneroPelicula.DOCUMENTAL, EstadoPelicula.EN_CARTELERA
        ));
        listaPeliculas.put(21, new Pelicula(
                "La Conspiración de las Sombras Anacrónicas", Idioma.RUSO, Idioma.ESPAÑOL, Duration.ofMinutes(99),
                "Klaus Trofobia", "Anastasia Ivanova", 2026, Paises.RUSIA,
                ClasificacionEdad.MAS_16, GeneroPelicula.ACCION, EstadoPelicula.PROXIMAMENTE
        ));

        listaPeliculas.put(22, new Pelicula(
                "El Desvanecimiento de la Realidad Perceptual", Idioma.ITALIANO, Idioma.ESPAÑOL, Duration.ofMinutes(123),
                "Gaston Porrò", "Piero Zoso", 1999, Paises.ITALIA,
                ClasificacionEdad.MAS_18, GeneroPelicula.TERROR, EstadoPelicula.EN_CARTELERA
        ));

        listaPeliculas.put(23, new Pelicula(
                "El Arte de la Persuasión Paradojal", Idioma.FRANCES, Idioma.ESPAÑOL, Duration.ofMinutes(200),
                "François Tostado", "Jacques Marrón", 2000, Paises.FRANCIA,
                ClasificacionEdad.MAS_18, GeneroPelicula.ACCION, EstadoPelicula.NO_DISPONIBLE
        ));

        listaPeliculas.put(24, new Pelicula(
                "La Sinfonía de las Voces Silenciadas", Idioma.FRANCES, Idioma.ESPAÑOL, Duration.ofMinutes(188),
                "Anastasia Ivanova", "François Tostado", 2001, Paises.FRANCIA,
                ClasificacionEdad.MAS_13, GeneroPelicula.SUSPENSO, EstadoPelicula.EN_CARTELERA
        ));

        listaPeliculas.put(25, new Pelicula(
                "Psicodelia del subdesarrollo", Idioma.ALEMAN, Idioma.ESPAÑOL, Duration.ofMinutes(196),
                "Gunter Fuertez", "Helga Fant",2000, Paises.ALEMANIA,
                ClasificacionEdad.MAS_18, GeneroPelicula.DOCUMENTAL, EstadoPelicula.NO_DISPONIBLE
        ));

        listaPeliculas.put(26, new Pelicula(
                "Radicalismo doméstico", Idioma.RUSO, Idioma.ESPAÑOL, Duration.ofMinutes(136),
                "Heidi Misplatos", "Anastasia Ivanova", 2005, Paises.RUSIA,
                ClasificacionEdad.ATP, GeneroPelicula.COMEDIA, EstadoPelicula.NO_DISPONIBLE
        ));

        listaPeliculas.put(27, new Pelicula(
                "Surrealismo casero", Idioma.FRANCES, Idioma.ESPAÑOL, Duration.ofMinutes(86),
                "François Tostado", "Pierre Nodoyuna", 2000, Paises.FRANCIA,
                ClasificacionEdad.ATP, GeneroPelicula.AVENTURA, EstadoPelicula.NO_DISPONIBLE
        ));

        listaPeliculas.put(28, new Pelicula(
                "El Ornitorrinco que Sabía Demasiado", Idioma.JAPONES, Idioma.ESPAÑOL, Duration.ofMinutes(99),
                "Hideo Kojima", "Riku Tanaka", 2022, Paises.JAPON,
                ClasificacionEdad.MAS_13, GeneroPelicula.ROMANCE, EstadoPelicula.EN_CARTELERA
        ));

        listaPeliculas.put(29, new Pelicula(
                "Donde Dejé las Llaves, Dejé Mi Alma", Idioma.INGLES, Idioma.ESPAÑOL, Duration.ofMinutes(110),
                "Morgan Walker", "Casey Allen", 2024, Paises.REINO_UNIDO,
                ClasificacionEdad.MAS_16, GeneroPelicula.SUSPENSO, EstadoPelicula.PROXIMAMENTE
        ));

        listaPeliculas.put(30, new Pelicula(
                "El Jardín Donde Florecen los Relojes", Idioma.FRANCES, Idioma.ESPAÑOL, Duration.ofMinutes(120),
                "Björn Ymirgo", "Amélie Degueto", 2025, Paises.FRANCIA,
                ClasificacionEdad.MAS_16, GeneroPelicula.DOCUMENTAL, EstadoPelicula.PROXIMAMENTE
        ));
    }

    public void agregarPelicula(Pelicula pelicula){
        listaPeliculas.put(listaPeliculas.size()+1, pelicula);
    }

    public void eliminarPelicula(int index){
        listaPeliculas.get(index).setEstadoPelicula(EstadoPelicula.NO_DISPONIBLE);
    }

    public void listarTodo(){
        for (Pelicula pelicula : listaPeliculas.values()){
            System.out.println(pelicula);
        }
    }

    public void listarCartelera(){
        for (Pelicula pelicula : listaPeliculas.values()){
            if (pelicula.getEstadoPelicula() == EstadoPelicula.EN_CARTELERA){
                System.out.println(pelicula.fichaTecnicaResumen());
            }
        }
    }

    public void listarProximosEstrenos(){
        for (Pelicula pelicula : listaPeliculas.values()){
            if (pelicula.getEstadoPelicula() == EstadoPelicula.PROXIMAMENTE){
                System.out.println(pelicula.fichaTecnicaResumen());
            }
        }
    }


    public void buscar(Scanner entrada){
        System.out.println("Seleccione el dato a buscar:" +
                "\n[0] ID");
        //System.out.println(menuDatos());
        int opcion;

        if (entrada.hasNextInt()){
            opcion = entrada.nextInt();
            entrada.nextLine();
            if (opcion<0 || opcion>11){
                throw new NumeroFueraDelRangoException();
            }else{
                switch (opcion){

                }
            }

        }else{
            throw new SoloNumerosException();
        }

    }





}
