package Modelos.Cine;

import Enumeraciones.*;
import Excepciones.NumeroFueraDelRangoException;
import Excepciones.SeleccionInvalidaException;
import Excepciones.SoloNumerosException;
import Excepciones.StringEnBlancoException;
import Gestores.GestorDatos;
import Gestores.GestorEnums;
import Gestores.GestorPelicula;

import java.time.Duration;
import java.time.Year;
import java.util.Scanner;

public class Pelicula{
    //Atributos
    public static int contador = 0;
    public int id;
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
        this.id = ++contador;
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
        Scanner entrada = new Scanner(System.in);
        int datosValidos = 0;
        this.id = ++contador;

        while(datosValidos==0){ //Ingresar el titulo
            try{
                System.out.println("Ingrese el titulo de la película\n");
                setTitulo(new GestorDatos().ingresarTexto(entrada));
                datosValidos++;
            }catch (StringEnBlancoException e){
                System.out.println(e.getMessage());
            }
        }

        while(datosValidos==1){ //Seleccionar el idioma
            try{
                System.out.println("Seleccione el idioma de la pelicula: ");
                this.setIdioma(new GestorEnums<>(Idioma.class).seleccionarElemento(entrada));
                datosValidos++;
            }catch (NumeroFueraDelRangoException e){
                System.out.println(e.getMessage());
            }catch (SoloNumerosException e){
                System.out.println(e.getMessage());
                entrada.nextLine(); //Es necesario poque sino el buffer nunca se actualiza, creando un bucle infinito
            }
        }

        while(datosValidos==2){ //Seleccionar el idioma de los subtitulos
            try{
                System.out.println("\nSeleccione el idioma de los subtitulos: ");
                this.setIdiomaSubtitulos(new GestorEnums<>(Idioma.class).seleccionarElemento(entrada));
                datosValidos++;
            }catch (NumeroFueraDelRangoException e){
                System.out.println(e.getMessage());
            }catch (SoloNumerosException e){
                System.out.println(e.getMessage());
                entrada.nextLine(); //Es necesario poque sino el buffer nunca se actualiza, creando un bucle infinito
            }
        }

        while(datosValidos==3){ //Ingresar la duracion
            try{
                this.setDuracion(new GestorDatos().ingresarDuracion(entrada));
                datosValidos++;
            }catch (NumeroFueraDelRangoException e){
                System.out.println(e.getMessage());
            }catch (SoloNumerosException e){
                System.out.println(e.getMessage());
                entrada.nextLine(); //Es necesario poque sino el buffer nunca se actualiza, creando un bucle infinito
            }
        }

        entrada.nextLine(); //Consume el salto de línea que sobra, sino se saltea el ingreso del nombre en la proxima funcion

        while(datosValidos==4){ //Ingresar el productor
            try{
                System.out.println("Productor:");
                this.setProductor(new GestorDatos().ingresarNombreCompleto(entrada));
                datosValidos++;
            }catch (StringEnBlancoException e){
                System.out.println(e.getMessage());
            }
        }

        while(datosValidos==5){ //Ingresar el director
            try{
                System.out.println("Director:");
                this.setDirector(new GestorDatos().ingresarNombreCompleto(entrada));
                datosValidos++;
            }catch (StringEnBlancoException e){
                System.out.println(e.getMessage());
            }
        }

        while(datosValidos==6){ //Ingresar el año
            try{
                this.setAñoLanzamiento(new GestorDatos().ingresarAño(entrada));
                datosValidos++;
            }catch (NumeroFueraDelRangoException e){
                System.out.println(e.getMessage());
            }catch (SoloNumerosException e){
                System.out.println(e.getMessage());
                entrada.nextLine(); //Es necesario poque sino el buffer nunca se actualiza, creando un bucle infinito
            }
        }

        while(datosValidos==7){ //Ingresar el pais
            try{
                System.out.println("Seleccione el pais de origen:\n");
                this.setPais(new GestorEnums<>(Paises.class).seleccionarElemento(entrada));
                datosValidos++;
            }catch (NumeroFueraDelRangoException e){
                System.out.println(e.getMessage());
            }catch (SoloNumerosException e){
                System.out.println(e.getMessage());
                entrada.nextLine(); //Es necesario poque sino el buffer nunca se actualiza, creando un bucle infinito
            }
        }

        while(datosValidos==8){ //Ingresar la clasificacion por Edad
            try{
                System.out.println("Seleccione la clasificacion por edad:\n");
                this.setClasificacionEdad(new GestorEnums<>(ClasificacionEdad.class).seleccionarElemento(entrada));
                datosValidos++;
            }catch (NumeroFueraDelRangoException e){
                System.out.println(e.getMessage());
            }catch (SoloNumerosException e){
                System.out.println(e.getMessage());
                entrada.nextLine(); //Es necesario poque sino el buffer nunca se actualiza, creando un bucle infinito
            }
        }

        while(datosValidos==9){ //Ingresar el generoPelicula
            try{
                System.out.println("Seleccione el genero de la pelicula:\n");
                this.setGeneroPelicula(new GestorEnums<>(GeneroPelicula.class).seleccionarElemento(entrada));
                datosValidos++;
            }catch (NumeroFueraDelRangoException e){
                System.out.println(e.getMessage());
            }catch (SoloNumerosException e){
                System.out.println(e.getMessage());
                entrada.nextLine(); //Es necesario poque sino el buffer nunca se actualiza, creando un bucle infinito
            }
        }

        while(datosValidos==10){ //Ingresar el EstadoPelicula
            try{
                System.out.println("Seleccione el estado de la pelicula:\n");
                this.setEstadoPelicula(new GestorEnums<>(EstadoPelicula.class).seleccionarElemento(entrada));
                datosValidos++;
            }catch (NumeroFueraDelRangoException e){
                System.out.println(e.getMessage());
            }catch (SoloNumerosException e){
                System.out.println(e.getMessage());
                entrada.nextLine(); //Es necesario poque sino el buffer nunca se actualiza, creando un bucle infinito
            }
        }

        System.out.println("Estos son los datos ingresados" + this);

    }

    //-----------------------Constructor que recibe solo título (para pruebas de coleccion)---------------------------

    public Pelicula(String titulo){
        this.id = ++contador;
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

    public void modificarPelicula(Scanner entrada) {
        int opcion = 0;
        boolean salirBucle = true;
        boolean salirSwitch = true;
        GestorDatos datos = new GestorDatos();
        System.out.println("Estos son los datos actuales de la película:\n" + this);
        while (salirSwitch) {
            System.out.println("\nSeleccione el dato a modificar:\n" + menuDatos());
            try {
                opcion = datos.ingresarNumero(entrada, 11);
                if (opcion >= 1 && opcion <= 11) {
                    switch (opcion) {
                        case 1://Modificar el titulo
                            do {
                                try {
                                    System.out.println("Ingrese el título nuevo:");
                                    this.setTitulo(datos.ingresarTexto(entrada));
                                    salirBucle = false;
                                } catch (StringEnBlancoException e) {
                                    System.out.println(e.getMessage());
                                }
                            } while (salirBucle);
                            System.out.println("Estos son los datos de la película:\n" + this + "\n¿Desea continuar modificando?");
                            salirSwitch = datos.confirmacion(entrada);
                            break;
                        case 2: //Cambiar idioma
                            do {
                                try {
                                    System.out.println("Seleccione el nuevo idioma:");
                                    this.setIdioma(new GestorEnums<>(Idioma.class).seleccionarElemento(entrada));
                                    entrada.nextLine();
                                    salirBucle = false;
                                } catch (StringEnBlancoException e) {
                                    System.out.println(e.getMessage());
                                }
                            } while (salirBucle);
                            System.out.println("Estos son los datos de la película:\n" + this + "\n¿Desea continuar modificando?");
                            salirSwitch = datos.confirmacion(entrada);
                            break;
                        case 3: //Cambiar idioma de subtitulos
                            do {
                                try {
                                    System.out.println("Seleccione el nuevo idioma de subtitulos:");
                                    this.setIdiomaSubtitulos(new GestorEnums<>(Idioma.class).seleccionarElemento(entrada));
                                    entrada.nextLine();
                                    salirBucle = false;
                                } catch (StringEnBlancoException e) {
                                    System.out.println(e.getMessage());
                                }
                            } while (salirBucle);
                            System.out.println("Estos son los datos de la película:\n" + this + "\n¿Desea continuar modificando?");
                            salirSwitch = datos.confirmacion(entrada);
                            break;
                        case 4: //Cambiar duracion
                            do {
                                try {
                                    this.setDuracion(datos.ingresarDuracion(entrada));
                                    entrada.nextLine();
                                    salirBucle = false;
                                } catch (StringEnBlancoException e) {
                                    System.out.println(e.getMessage());
                                }
                            } while (salirBucle);
                            System.out.println("Estos son los datos de la película:\n" + this + "\n¿Desea continuar modificando?");
                            salirSwitch = datos.confirmacion(entrada);
                            break;
                        case 5: //Cambiar Productor
                            do {
                                try {
                                    System.out.println("Productor:");
                                    this.setProductor(datos.ingresarNombreCompleto(entrada));
                                    salirBucle = false;
                                } catch (StringEnBlancoException e) {
                                    System.out.println(e.getMessage());
                                }
                            } while (salirBucle);
                            System.out.println("Estos son los datos de la película:\n" + this + "\n¿Desea continuar modificando?");
                            salirSwitch = datos.confirmacion(entrada);
                            break;
                        case 6: //Cambiar Director
                            do {
                                try {
                                    System.out.println("Director:");
                                    this.setDirector(datos.ingresarNombreCompleto(entrada));
                                    salirBucle = false;
                                } catch (StringEnBlancoException e) {
                                    System.out.println(e.getMessage());
                                }
                            } while (salirBucle);
                            System.out.println("Estos son los datos de la película:\n" + this + "\n¿Desea continuar modificando?");
                            salirSwitch = datos.confirmacion(entrada);
                            break;
                        case 7: //Cambiar año
                            do {
                                try {
                                    this.setAñoLanzamiento(datos.ingresarAño(entrada));
                                    salirBucle = false;
                                } catch (StringEnBlancoException e) {
                                    System.out.println(e.getMessage());
                                }
                            } while (salirBucle);
                            System.out.println("Estos son los datos de la película:\n" + this + "\n¿Desea continuar modificando?");
                            salirSwitch = datos.confirmacion(entrada);
                            break;
                        case 8: //Cambiar pais de origen
                            do {
                                try {
                                    this.setPais(new GestorEnums<>(Paises.class).seleccionarElemento(entrada));
                                    entrada.nextLine();
                                    salirBucle = false;
                                } catch (StringEnBlancoException e) {
                                    System.out.println(e.getMessage());
                                }
                            } while (salirBucle);
                            System.out.println("Estos son los datos de la película:\n" + this + "\n¿Desea continuar modificando?");
                            salirSwitch = datos.confirmacion(entrada);
                            break;
                        case 9: //Cambiar clasificacion
                            do {
                                try {
                                    this.setClasificacionEdad(new GestorEnums<>(ClasificacionEdad.class).seleccionarElemento(entrada));
                                    entrada.nextLine();
                                    salirBucle = false;
                                } catch (StringEnBlancoException e) {
                                    System.out.println(e.getMessage());
                                }
                            } while (salirBucle);
                            System.out.println("Estos son los datos de la película:\n" + this + "\n¿Desea continuar modificando?");
                            salirSwitch = datos.confirmacion(entrada);
                            break;
                        case 10: //Cambiar clasificacion
                            do {
                                try {
                                    this.setGeneroPelicula(new GestorEnums<>(GeneroPelicula.class).seleccionarElemento(entrada));
                                    entrada.nextLine();
                                    salirBucle = false;
                                } catch (StringEnBlancoException e) {
                                    System.out.println(e.getMessage());
                                }
                            } while (salirBucle);
                            System.out.println("Estos son los datos de la película:\n" + this + "\n¿Desea continuar modificando?");
                            salirSwitch = datos.confirmacion(entrada);
                            break;
                        case 11: //Cambiar clasificacion
                            do {
                                try {
                                    this.setEstadoPelicula(new GestorEnums<>(EstadoPelicula.class).seleccionarElemento(entrada));
                                    entrada.nextLine();
                                    salirBucle = false;
                                } catch (StringEnBlancoException e) {
                                    System.out.println(e.getMessage());
                                }
                            } while (salirBucle);
                            System.out.println("Estos son los datos de la película:\n" + this + "\n¿Desea continuar modificando?");
                            salirSwitch = datos.confirmacion(entrada);
                            break;
                    }
                }
            } catch (NumeroFueraDelRangoException e) {
                throw new RuntimeException(e);
            } catch (SoloNumerosException e) {
                throw new RuntimeException(e);
            } catch (StringEnBlancoException e) {
                throw new RuntimeException(e);
            } catch (SeleccionInvalidaException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //-----------------------------------------------------Metodos que printean-----------------------------------------
    @Override
    public String toString() {

        return
                "\nID: " + id +
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
                "\nID: " + id +
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

    public int getId() {
        return id;
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
