package Gestores;

import Enumeraciones.ClasificacionEdad;
import Enumeraciones.GeneroPelicula;
import Enumeraciones.Idioma;
import Enumeraciones.Paises;
import Excepciones.NumeroFueraDelRangoException;
import Excepciones.StringEnBlancoException;
import Interfaces.ICRUD;
import Modelos.Cine.Pelicula;

import java.time.Duration;
import java.time.Year;
import java.util.HashMap;
import java.util.Scanner;

public class GestorPelicula {
    private HashMap<Integer, Pelicula> ListaPeliculas;

    public GestorPelicula(){
        ListaPeliculas = new HashMap<>();
    }

    //Metodo que carga 30 peliculas
    public void inicializarLista(){
        ListaPeliculas.put(1, new Pelicula(
                "El pollito que no volvió", Idioma.INGLES, Idioma.ESPAÑOL, Duration.ofMinutes(148),
                "Mario Neta", "John Johnson", 2010, Paises.ESTADOS_UNIDOS,
                ClasificacionEdad.MAS_18, GeneroPelicula.TERROR
        ));

        ListaPeliculas.put(2, new Pelicula(
                "Las Sombras que Olvidaron Su Dueño", Idioma.ITALIANO, Idioma.ESPAÑOL, Duration.ofMinutes(136),
                "Joel Silver", "Donato Macarroni", 1999, Paises.ITALIA,
                ClasificacionEdad.MAS_13, GeneroPelicula.SUSPENSO
        ));

        ListaPeliculas.put(3, new Pelicula(
                "El regreso de la milanesa perdida", Idioma.ESPAÑOL, Idioma.SIN, Duration.ofMinutes(132),
                "Sigrid Eltoya", "Debora Melocotón", 2019, Paises.ARGENTINA,
                ClasificacionEdad.ATP, GeneroPelicula.COMEDIA
        ));

        ListaPeliculas.put(4, new Pelicula(
                "La venganza del Wi-Fi", Idioma.ESPAÑOL, Idioma.SIN, Duration.ofMinutes(169),
                "George Peque", "David Miller", 2014, Paises.ESTADOS_UNIDOS,
                ClasificacionEdad.ATP, GeneroPelicula.COMEDIA
        ));

        ListaPeliculas.put(5, new Pelicula(
                "Mision imposible: Desenredar auriculares", Idioma.JAPONES, Idioma.ESPAÑOL, Duration.ofMinutes(181),
                "Hiroshi Yamazaki", "Sora Nakamura", 2019, Paises.JAPON,
                ClasificacionEdad.MAS_13, GeneroPelicula.ACCION
        ));

        ListaPeliculas.put(6, new Pelicula(
                "En busca del sandwich perdido", Idioma.INGLES, Idioma.ESPAÑOL, Duration.ofMinutes(118),
                "Taylor Lee", "Penny Truco", 2021, Paises.REINO_UNIDO,
                ClasificacionEdad.ATP, GeneroPelicula.DOCUMENTAL
        ));

        ListaPeliculas.put(7, new Pelicula(
                "Los zombis no saben bailar", Idioma.ITALIANO, Idioma.ESPAÑOL, Duration.ofMinutes(122),
                "Gino Longaniza", "Franco Tortellini", 2018, Paises.ITALIA,
                ClasificacionEdad.MAS_16, GeneroPelicula.ROMANCE
        ));

        ListaPeliculas.put(8, new Pelicula(
                "El gato que queria ser perro", Idioma.JAPONES, Idioma.ESPAÑOL, Duration.ofMinutes(100),
                "Aiko Fujimura", "Riku Tanaka", 2020, Paises.JAPON,
                ClasificacionEdad.ATP, GeneroPelicula.AVENTURA
        ));

        ListaPeliculas.put(9, new Pelicula(
                "Yo Nunca Vi Televisión (Y Luego Sí Pero Después No)", Idioma.ESPAÑOL, Idioma.ESPAÑOL, Duration.ofMinutes(192),
                "Juanín", "Tulio Triviño", 2003, Paises.CHILE,
                ClasificacionEdad.ATP, GeneroPelicula.COMEDIA
        ));

        ListaPeliculas.put(10, new Pelicula(
                " Pizza vs hamburguesa: El duelo final", Idioma.INGLES, Idioma.ESPAÑOL,Duration.ofMinutes(152),
                "Francisco Dientes Largos", "Lola Mento", 2017, Paises.ESTADOS_UNIDOS,
                ClasificacionEdad.MAS_13, GeneroPelicula.ACCION
        ));

        ListaPeliculas.put(11, new Pelicula(
                "El enigma del cuchillo sin filo", Idioma.ITALIANO, Idioma.ESPAÑOL, Duration.ofMinutes(134),
                "Björn Ymirgo", "Anita Pasta", 2018, Paises.ITALIA,
                ClasificacionEdad.MAS_16, GeneroPelicula.SUSPENSO
        ));

        ListaPeliculas.put(12, new Pelicula(
                "Harry Potter y el horno microondas", Idioma.INGLES, Idioma.ESPAÑOL, Duration.ofMinutes(102),
                "Henry Patter", "J.M. Rowlind", 2013, Paises.REINO_UNIDO,
                ClasificacionEdad.ATP, GeneroPelicula.AVENTURA
        ));

        ListaPeliculas.put(13, new Pelicula(
                "El drama del zapato izquierdo", Idioma.ESPAÑOL, Idioma.SIN, Duration.ofMinutes(90),
                "Odin Metido", "Yoselin García", 2001, Paises.MEXICO,
                ClasificacionEdad.ATP, GeneroPelicula.DOCUMENTAL
        ));

        ListaPeliculas.put(14, new Pelicula(
                "Me cortaron mal el pelo", Idioma.ESPAÑOL, Idioma.ESPAÑOL, Duration.ofMinutes(128),
                "Chascoberto", "Tulio Triviño", 2003, Paises.CHILE,
                ClasificacionEdad.ATP, GeneroPelicula.COMEDIA
        ));

        ListaPeliculas.put(15, new Pelicula(
                "La revancha del cucaracho volador", Idioma.ESPAÑOL, Idioma.SIN, Duration.ofMinutes(175),
                "Benito Camelon", "John Nieto", 1972, Paises.MEXICO,
                ClasificacionEdad.MAS_13, GeneroPelicula.COMEDIA
        ));

        ListaPeliculas.put(16, new Pelicula(
                "60 sombras de atún", Idioma.INGLES, Idioma.ESPAÑOL, Duration.ofMinutes(154),
                " Queen Bender", "Arthur Misato", 1994, Paises.ESTADOS_UNIDOS,
                ClasificacionEdad.MAS_16, GeneroPelicula.DOCUMENTAL
        ));

        ListaPeliculas.put(17, new Pelicula(
                "El arrepentimiento de Juan Carlos Bodoque", Idioma.ESPAÑOL, Idioma.ESPAÑOL, Duration.ofMinutes(142),
                "Juan Carlos Bodoque", "Tulio Triviño", 2005, Paises.CHILE,
                ClasificacionEdad.ATP, GeneroPelicula.SUSPENSO
        ));

        ListaPeliculas.put(18, new Pelicula(
                "El susurro del papel higienico", Idioma.INGLES, Idioma.ESPAÑOL, Duration.ofMinutes(139),
                "Sven Tirrin", "Elizabeth Moore", 1999, Paises.ESTADOS_UNIDOS,
                ClasificacionEdad.MAS_18, GeneroPelicula.ACCION
        ));

        ListaPeliculas.put(19, new Pelicula(
                "El Laberinto de las Incongruencias Ontológicas", Idioma.RUSO, Idioma.ESPAÑOL, Duration.ofMinutes(152),
                "Ingrid Tulotodo", "Svetlana Romanova", 2008, Paises.RUSIA,
                ClasificacionEdad.MAS_18, GeneroPelicula.DOCUMENTAL
        ));

        ListaPeliculas.put(20, new Pelicula(
                "La Epistemología de la Nostalgia Futurista", Idioma.RUSO, Idioma.ESPAÑOL, Duration.ofMinutes(155),
                "Daichi Watanabe", "Alexei Petrov", 1999, Paises.RUSIA,
                ClasificacionEdad.MAS_18, GeneroPelicula.DOCUMENTAL
        ));
        ListaPeliculas.put(21, new Pelicula(
                "La Conspiración de las Sombras Anacrónicas", Idioma.RUSO, Idioma.ESPAÑOL, Duration.ofMinutes(99),
                "Klaus Trofobia", "Anastasia Ivanova", 2023, Paises.RUSIA,
                ClasificacionEdad.MAS_16, GeneroPelicula.ACCION
        ));

        ListaPeliculas.put(22, new Pelicula(
                "El Desvanecimiento de la Realidad Perceptual", Idioma.ITALIANO, Idioma.ESPAÑOL, Duration.ofMinutes(123),
                "Gaston Porrò", "Piero Zoso", 1999, Paises.ITALIA,
                ClasificacionEdad.MAS_18, GeneroPelicula.TERROR
        ));

        ListaPeliculas.put(23, new Pelicula(
                "El Arte de la Persuasión Paradojal", Idioma.FRANCES, Idioma.ESPAÑOL, Duration.ofMinutes(200),
                "François Tostado", "Jacques Marrón", 2000, Paises.FRANCIA,
                ClasificacionEdad.MAS_18, GeneroPelicula.ACCION
        ));

        ListaPeliculas.put(24, new Pelicula(
                "La Sinfonía de las Voces Silenciadas", Idioma.FRANCES, Idioma.ESPAÑOL, Duration.ofMinutes(188),
                "Anastasia Ivanova", "François Tostado", 2001, Paises.FRANCIA,
                ClasificacionEdad.MAS_13, GeneroPelicula.SUSPENSO
        ));

        ListaPeliculas.put(25, new Pelicula(
                "Psicodelia del subdesarrollo", Idioma.ALEMAN, Idioma.ESPAÑOL, Duration.ofMinutes(196),
                "Gunter Fuertez", "Helga Fant",2000, Paises.ALEMANIA,
                ClasificacionEdad.MAS_18, GeneroPelicula.DOCUMENTAL
        ));

        ListaPeliculas.put(26, new Pelicula(
                "Radicalismo doméstico", Idioma.RUSO, Idioma.ESPAÑOL, Duration.ofMinutes(136),
                "Heidi Misplatos", "Anastasia Ivanova", 2005, Paises.RUSIA,
                ClasificacionEdad.ATP, GeneroPelicula.COMEDIA
        ));

        ListaPeliculas.put(27, new Pelicula(
                "Surrealismo casero", Idioma.FRANCES, Idioma.ESPAÑOL, Duration.ofMinutes(86),
                "François Tostado", "Pierre Nodoyuna", 2000, Paises.FRANCIA,
                ClasificacionEdad.ATP, GeneroPelicula.AVENTURA
        ));

        ListaPeliculas.put(28, new Pelicula(
                "El Ornitorrinco que Sabía Demasiado", Idioma.JAPONES, Idioma.ESPAÑOL, Duration.ofMinutes(99),
                "Hideo Kojima", "Riku Tanaka", 2022, Paises.JAPON,
                ClasificacionEdad.MAS_13, GeneroPelicula.ROMANCE
        ));

        ListaPeliculas.put(29, new Pelicula(
                "Donde Dejé las Llaves, Dejé Mi Alma", Idioma.INGLES, Idioma.ESPAÑOL, Duration.ofMinutes(110),
                "Morgan Walker", "Casey Allen", 2024, Paises.REINO_UNIDO,
                ClasificacionEdad.MAS_16, GeneroPelicula.SUSPENSO
        ));

        ListaPeliculas.put(30, new Pelicula(
                "El Jardín Donde Florecen los Relojes", Idioma.FRANCES, Idioma.ESPAÑOL, Duration.ofMinutes(120),
                "Björn Ymirgo", "Amélie Degueto", 2015, Paises.FRANCIA,
                ClasificacionEdad.MAS_16, GeneroPelicula.DOCUMENTAL
        ));
    }









}
