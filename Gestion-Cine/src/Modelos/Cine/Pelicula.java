package Modelos.Cine;

import Enumeraciones.ClasificacionEdad;
import Enumeraciones.GeneroPelicula;
import Enumeraciones.Idioma;
import Enumeraciones.TipoSala;

import java.time.Duration;

public class Pelicula {
    //Atributos
    private String titulo;
    private Idioma idioma;
    private Idioma idiomaSubtitulos;
    private Duration duracion;
    private TipoSala  tipoSala;
    private String productor;
    private String director;
    private String añoLanzamiento;
    private String pais;
    private ClasificacionEdad clasificacionEdad;
    private GeneroPelicula generoPelicula;

    //Constructor
    public Pelicula(String titulo,Idioma idioma,Idioma idiomaSubtitulos,Duration duracion,TipoSala tipoSala, String productor,
                    String director,String añoLanzamiento,String pais,ClasificacionEdad clasificacionEdad,
                    GeneroPelicula generoPelicula){
        this.titulo = titulo;
        this.idioma = idioma;
        this.idiomaSubtitulos = idiomaSubtitulos;
        this.duracion = duracion;
        this.tipoSala = tipoSala; //Debe recibir el tipo de sala a usar para verificar con la asignada en funcion si corresponde
        this.productor = productor;
        this.director = director;
        this.añoLanzamiento = añoLanzamiento;
        this.pais = pais;
        this.clasificacionEdad = clasificacionEdad;
        this.generoPelicula = generoPelicula;
    }

    //Getters y Setters

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

    public TipoSala getTipoSala() {
        return tipoSala;
    }

    public String getProductor() {
        return productor;
    }

    public String getDirector() {
        return director;
    }

    public String getAñoLanzamiento() {
        return añoLanzamiento;
    }

    public String getPais() {
        return pais;
    }

    public ClasificacionEdad getClasificacionEdad() {
        return clasificacionEdad;
    }

    public GeneroPelicula getGeneroPelicula() {
        return generoPelicula;
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

    public void setTipoSala(TipoSala tipoSala) {
        this.tipoSala = tipoSala;
    }

    public void setProductor(String productor) {
        this.productor = productor;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setAñoLanzamiento(String añoLanzamiento) {
        this.añoLanzamiento = añoLanzamiento;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setClasificacionEdad(ClasificacionEdad clasificacionEdad) {
        this.clasificacionEdad = clasificacionEdad;
    }

    public void setGeneroPelicula(GeneroPelicula generoPelicula) {
        this.generoPelicula = generoPelicula;
    }
}
