package Gestores;

import Enumeraciones.Idioma;
import Excepciones.NumeroFueraDelRangoException;
import Excepciones.SeleccionInvalidaException;
import Excepciones.SoloNumerosException;
import Excepciones.StringEnBlancoException;

import java.time.Duration;
import java.time.Year;
import java.util.Scanner;

public class GestorDatos {
    public String ingresarTexto(Scanner entrada) throws StringEnBlancoException {
        String aux = entrada.nextLine();
        if(aux.isBlank()){
            throw new StringEnBlancoException("ERROR: No se ha ingresado texto");
        }else{
            return aux;
        }
    }

    public int ingresarNumero(Scanner entrada) throws NumeroFueraDelRangoException, SoloNumerosException{
        int aux;
        if(entrada.hasNextInt()){
            aux = entrada.nextInt();
            if(aux<0){
                throw new NumeroFueraDelRangoException();
            }else {
                return aux;
            }
        }else{
            throw new SoloNumerosException("ERROR: Solo se aceptan numeros\n");
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
            throw new StringEnBlancoException();
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

    public boolean confirmacion(Scanner entrada) throws StringEnBlancoException, SeleccionInvalidaException {
        //Como se puede usar en varios contextos, el mensaje se debe printear antes
        System.out.println(" (S/N)");
        String respuesta = entrada.nextLine();
        if (respuesta.equalsIgnoreCase("S")){
            return true;
        } else if (respuesta.equalsIgnoreCase("N")) {
            return false;
        } else if (respuesta.isBlank()) {
            throw new StringEnBlancoException();
        }else{
            throw new SeleccionInvalidaException();
        }
    }




}
