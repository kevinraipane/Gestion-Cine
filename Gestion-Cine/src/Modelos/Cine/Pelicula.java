package Modelos.Cine;

import Enumeraciones.*;
import Excepciones.NumeroFueraDelRangoException;
import Excepciones.SoloNumerosException;
import Excepciones.StringEnBlancoException;
import Gestores.GestorEnums;
import Gestores.GestorPelicula;

import java.time.Duration;
import java.time.Year;
import java.util.Scanner;

public class Pelicula{
    //Atributos
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

    //Constructor
    public Pelicula(String titulo,Idioma idioma,Idioma idiomaSubtitulos,Duration duracion, String productor,
                    String director,int añoLanzamiento,Paises pais,ClasificacionEdad clasificacionEdad,
                    GeneroPelicula generoPelicula){

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
    }

    //------------------------------------------Contructor que no recibe parametros-------------------------------------
    public Pelicula(){
        Scanner entrada = new Scanner(System.in);
        int datosValidos = 0;

        while(datosValidos==0){ //Ingresar el titulo
            try{
                this.setTitulo(ingresarTitulo(entrada));
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
                this.setIdiomaSubtitulos(seleccionarIdioma(entrada));
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
                this.setDuracion(ingresarDuracion(entrada));
                datosValidos++;
            }catch (NumeroFueraDelRangoException e){
                System.out.println(e.getMessage());
            }catch (SoloNumerosException e){
                System.out.println(e.getMessage());
                entrada.nextLine(); //Es necesario poque sino el buffer nunca se actualiza, creando un bucle infinito
            }
        }

        entrada.nextLine(); //Consume el salto de linea que sobra, sino se saltea el ingreso del nombre en la proxima funcion

        while(datosValidos==4){ //Ingresar el productor
            try{
                System.out.println("Productor:");
                this.setProductor(ingresarNombreCompleto(entrada));
                datosValidos++;
            }catch (StringEnBlancoException e){
                System.out.println(e.getMessage());
            }
        }

        while(datosValidos==5){ //Ingresar el director
            try{
                System.out.println("Director:");
                this.setDirector(ingresarNombreCompleto(entrada));
                datosValidos++;
            }catch (StringEnBlancoException e){
                System.out.println(e.getMessage());
            }
        }

        while(datosValidos==6){ //Ingresar el año
            try{
                this.setAñoLanzamiento(ingresarAño(entrada));
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

        System.out.println("Estos son los datos ingresados" + this);

    }

    ///-----------------------------------------Metodos que permiten el ingreso de datos--------------------------------
    //                  (reciben Scanner por parametro porque es mas eficiente que iniciarlo en cada funcion)
    public String ingresarTitulo(Scanner entrada) throws StringEnBlancoException {
        System.out.println("Ingrese el titulo de la pelicula:");
        String aux = entrada.nextLine();
        if(aux.isBlank()){
            throw new StringEnBlancoException("ERROR: No se ha ingresado texto");
        }else{
            return aux;
        }
    }



    public Idioma seleccionarIdioma(Scanner entrada) throws NumeroFueraDelRangoException, SoloNumerosException {
        new GestorEnums<>(Idioma.class).listarEnum();
        int seleccion;
        if(entrada.hasNextInt()){
            seleccion = entrada.nextInt();
            //entrada.nextLine();
            if(seleccion<0 || seleccion>Idioma.values().length){
                throw new NumeroFueraDelRangoException("ERROR: Opción no valida\n");
            }else {
                return Idioma.values()[seleccion-1];
            }
        }else {
            throw new SoloNumerosException("ERROR: Solo se aceptan numeros\n");
        }
    }

    public Duration ingresarDuracion(Scanner entrada) throws NumeroFueraDelRangoException, SoloNumerosException{
        System.out.println("Ingrese la duracion (en minutos) de la pelicula: \n");
        int seleccion;
        if(entrada.hasNextInt()){
            seleccion = entrada.nextInt();
            if(seleccion<1 || seleccion>999){
                throw new NumeroFueraDelRangoException("ERROR: La duracion no puede ser menor a 1 minuto ni mayor a 999\n");
            }else {
                return Duration.ofMinutes(seleccion);
            }
        }else {
            throw new SoloNumerosException("ERROR: Solo se aceptan numeros\n");
        }
    }

    public String ingresarNombreCompleto(Scanner entrada) throws StringEnBlancoException {
        String nombre = "";
        String apellido = "";

        System.out.println("Ingrese el nombre de la persona: ");
        nombre = entrada.nextLine();

        System.out.println("Ingrese el apellido de la persona: ");
        apellido = entrada.nextLine();

        if (nombre.isBlank() || apellido.isBlank()){
            throw new StringEnBlancoException("ERROR: No pueden haber campos en blanco");
        }else{
            return nombre + " " + apellido;
            }
        }

    public int ingresarAño(Scanner entrada) throws NumeroFueraDelRangoException, SoloNumerosException{
        System.out.println("Ingrese el año de lanzamiento de la pelicula:");
        int año;

        if(entrada.hasNextInt()){
            año = entrada.nextInt();
            entrada.nextLine();
            if(año<1902){
                throw new NumeroFueraDelRangoException("La primera película de la historia fue Le Voyage dans la Lune (titulada Viaje a la Luna en español), " +
                            "una película francesa de 1902, en blanco y negro, muda y de ciencia ficción dirigida por Georges Méliès.\n");
            }else if (año> Year.now().getValue() + 3){
                throw new NumeroFueraDelRangoException("ERROR: El año de lanzamiento de la pelicula no puede superar los tres años en el futuro\n");
            }else {
                return año;
            }
        }else{
            throw new SoloNumerosException("ERROR: Solo se aceptan numeros\n");
        }
    }



    //-----------------------------------------------------Metodos que printean-----------------------------------------
    @Override
    public String toString() {
        return "\n------------------" +
                "\nTitulo: " + titulo +
                "\nIdioma: " + idioma +
                "\nIdioma Subtitulos: " + idiomaSubtitulos +
                "\nDuracion: " + convertirDuracion(duracion)  +
                "\nProductor: " + productor +
                "\nDirector: " + director +
                "\nAño De Lanzamiento: " + añoLanzamiento +
                "\nPais: " + pais +
                "\nClasificacion Por Edad:" + clasificacionEdad +
                "\nGenero: " + generoPelicula;
    }

    public String fichaTecnicaResumen() {
        return "\n------------------" +
                "\nTitulo: " + titulo +
                "\nIdioma: " + idioma +
                "\nIdioma Subtitulos: " + idiomaSubtitulos +
                "\nDuracion: " + duracion +
                "\nClasificacion Por Edad:" + clasificacionEdad +
                "\nGenero: " + generoPelicula;
    }

    public String convertirDuracion(Duration tiempo) {
        long horas = tiempo.toHours();
        long minutos = tiempo.toMinutesPart();

        return horas + "hs " + minutos + "mins (" + tiempo.toMinutes() + " mins en total)";
    }



    //-----------------------------------------------------Getters y Setters--------------------------------------------

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


}
