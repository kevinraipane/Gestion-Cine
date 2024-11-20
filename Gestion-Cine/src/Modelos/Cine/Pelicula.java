package Modelos.Cine;

import Enumeraciones.*;
import Excepciones.NumeroFueraDelRangoException;
import Excepciones.SeleccionInvalidaException;
import Excepciones.SoloNumerosException;
import Excepciones.StringEnBlancoException;
import Gestores.Funcionales.GestorDatos;
import Gestores.Funcionales.GestorEnums;

import java.time.Duration;
import java.util.Scanner;

public class Pelicula{
    //Atributos
    public static int contador = 0;
    public int idPelicula;
    private String titulo;
    private Idioma idioma;
    private Idioma idiomaSubtitulos;
    private Duration duracion;
    private String productor;
    private String director;
    private int añoLanzamiento;
    private Paises pais;
    private ClasificacionEdad clasificacionEdad;
    private GeneroPelicula generoPelicula;
    private EstadoPelicula estadoPelicula;

    //Constructor
    public Pelicula(String titulo,Idioma idioma,Idioma idiomaSubtitulos,Duration duracion, String productor,
                    String director,int añoLanzamiento,Paises pais,ClasificacionEdad clasificacionEdad,
                    GeneroPelicula generoPelicula, EstadoPelicula estadoPelicula){
        this.idPelicula = ++contador;
        this.titulo = titulo;
        this.idioma = idioma;
        this.idiomaSubtitulos = idiomaSubtitulos;
        this.duracion = duracion;
        this.productor = productor;
        this.director = director;
        this.añoLanzamiento = añoLanzamiento;
        this.pais = pais;
        this.clasificacionEdad = clasificacionEdad;
        this.generoPelicula = generoPelicula;
        this.estadoPelicula = estadoPelicula;
    }

    //------------------------------------------Constructor que no recibe parametros-------------------------------------
    public Pelicula(){
        this.idPelicula = ++contador;
    }

    //-----------------------Constructor que recibe solo título (para pruebas de coleccion)---------------------------

    public Pelicula(String titulo){
        this.idPelicula = ++contador;
        this.titulo = titulo;
        this.idioma = Idioma.INGLES;
        this.idiomaSubtitulos = Idioma.ESPAÑOL;
        this.duracion = Duration.ofMinutes(123);
        this.productor = "productor";
        this.director = "director";
        this.añoLanzamiento = 2024;
        this.pais = Paises.ESTADOS_UNIDOS;
        this.clasificacionEdad = ClasificacionEdad.MAS_13;
        this.generoPelicula = GeneroPelicula.ACCION;
        this.estadoPelicula = EstadoPelicula.EN_CARTELERA;
    }

    //-----------------------------------------------------Metodos que printean-----------------------------------------
    @Override
    public String toString() {
        return  "---------------------------------------------------------------" +
                "\nID: " + idPelicula +
                "\nTitulo: " + titulo +
                "\nIdioma: " + new GestorEnums<>(Idioma.class).formatearEnum(idioma)   +
                "\nSubtitulos: " + new GestorEnums<>(Idioma.class).formatearEnum(idiomaSubtitulos)   +
                "\nDuracion: " + new GestorDatos().convertirDuracion(duracion)  +
                "\nProductor: " + productor +
                "\nDirector: " + director +
                "\nAño De Lanzamiento: " + añoLanzamiento +
                "\nPais: " + new GestorEnums<>(Paises.class).formatearEnum(pais) +
                "\nClasificacion Por Edad: " + new GestorEnums<>(ClasificacionEdad.class).formatearEnum(clasificacionEdad) +
                "\nGenero: " + new GestorEnums<>(GeneroPelicula.class).formatearEnum(generoPelicula) +
                "\nEstado: " + new GestorEnums<>(EstadoPelicula.class).formatearEnum(estadoPelicula);
    }

    public String fichaTecnicaResumen() {
        return "---------------------------------------------------------------" +
                "\nTitulo: " + titulo +
                "\nIdioma: " + new GestorEnums<>(Idioma.class).formatearEnum(idioma)   +
                "\nSubtitulos: " + new GestorEnums<>(Idioma.class).formatearEnum(idiomaSubtitulos)   +
                "\nDuracion: " + new GestorDatos().convertirDuracion(duracion) +
                "\nClasificacion Por Edad:" + new GestorEnums<>(ClasificacionEdad.class).formatearEnum(clasificacionEdad) +
                "\nGenero: " + new GestorEnums<>(GeneroPelicula.class).formatearEnum(generoPelicula) +
                "\nEstado: " + new GestorEnums<>(EstadoPelicula.class).formatearEnum(estadoPelicula);
    }

    public String menuDatos(){
        return
                "\n[1] Titulo" +
                "\n[2] Idioma" +
                "\n[3] Idioma de los subtitulos" +
                "\n[4] Duracion" +
                "\n[5] Productor" +
                "\n[6] Director" +
                "\n[7] Año de lanzamiento" +
                "\n[8] Pais" +
                "\n[9] Clasificacion por edad" +
                "\n[10] Genero" +
                "\n[11] Estado";

    }

    //-----------------------------------------------------Getters y Setters--------------------------------------------

    public int getIdPelicula() {
        return idPelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public Idioma getIdiomaSubtitulos() {
        return idiomaSubtitulos;
    }

    public Duration getDuracion() {
        return duracion;
    }

    public String getProductor() {
        return productor;
    }

    public String getDirector() {
        return director;
    }

    public int getAñoLanzamiento() {
        return añoLanzamiento;
    }

    public Paises getPais() {
        return pais;
    }

    public ClasificacionEdad getClasificacionEdad() {
        return clasificacionEdad;
    }

    public GeneroPelicula getGeneroPelicula() {
        return generoPelicula;
    }

    public EstadoPelicula getEstadoPelicula() {
        return estadoPelicula;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public void setIdiomaSubtitulos(Idioma idiomaSubtitulos) {
        this.idiomaSubtitulos = idiomaSubtitulos;
    }

    public void setDuracion(Duration duracion) {
        this.duracion = duracion;
    }

    public void setProductor(String productor) {
        this.productor = productor;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setAñoLanzamiento(int añoLanzamiento) {
        this.añoLanzamiento = añoLanzamiento;
    }

    public void setPais(Paises pais) {
        this.pais = pais;
    }

    public void setClasificacionEdad(ClasificacionEdad clasificacionEdad) {
        this.clasificacionEdad = clasificacionEdad;
    }

    public void setGeneroPelicula(GeneroPelicula generoPelicula) {
        this.generoPelicula = generoPelicula;
    }

    public void setEstadoPelicula(EstadoPelicula estadoPelicula) {
        this.estadoPelicula = estadoPelicula;
    }
}
