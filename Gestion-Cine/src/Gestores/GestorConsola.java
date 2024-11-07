package Gestores;

import java.util.IllegalFormatException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class GestorConsola {

    // Esta clase busca simplificar las interacciones basicas con la consola
    // La idea es que valide de una forma un poco mas general el ingreso de datos simples
    // Y tambien no tener que instanciar el scanner en todos lados

    Scanner scanner = new Scanner(System.in);

    // METODO LECTURA DE INTEGER

    public int leerEntero(String message) {
        System.out.println(message + ": ");
        return scanner.nextInt();
    }

    public int leerEntero(String message, int min, int max) {
        System.out.println(message + " (Desde " + min + " hasta " + max + "): ");
        int entero = scanner.nextInt();

        while (entero < 0 && entero > max) {
            System.out.println("Numero invalido. Ingrese un numero mayor a " + min + " y menor a " + max + ": ");
            entero = scanner.nextInt();
        }

        return entero;
    }

    public int leerEntero(String message, int min) {
        System.out.println(message + " (Mayor a " + min + "): ");
        int entero = scanner.nextInt();

        while (entero < min) {
            System.out.println("Numero invalido. Ingrese un numero mayor a " + min + ": ");
            entero = scanner.nextInt();
        }

        return entero;
    }

    public int leerEnteroPositivo(String message) {
        System.out.println(message + ": ");
        int entero = scanner.nextInt();

        while (entero < 0) {
            System.out.println("Ingrese un numero mayor a cero: ");
            entero = scanner.nextInt();
        }

        return entero;
    }

    public int leerEnteroPositivo(String message, int max) {
        System.out.println(message + " (Hasta " + max + "): ");
        int entero = scanner.nextInt();

        while (entero < 0 && entero > max) {
            System.out.println("Numero invalido. Ingrese un numero mayor a cero y menor a " + max + ": ");
            entero = scanner.nextInt();
        }

        return entero;
    }


    // METODOS LECTURA DE CADENAS

    public String leerCadena(String message) {
        System.out.println(message + ": ");
        String cadena = scanner.next();
        scanner.nextLine();

        return cadena;
    }

    public String leerCadena(String message, int largo) {
        System.out.println(message + " (" + largo + " caracteres): ");
        String cadena = scanner.next();
        scanner.nextLine();

        while (cadena.length() != largo) {
            System.out.println("La cadena debe ser de " + largo + " caracteres. Intente nuevamente: ");
            cadena = scanner.next();
            scanner.nextLine();
        }

        return cadena;
    }

    public String leerCadenaMax(String message, int maxLong) {
        System.out.println(message + " (max " + maxLong + " caracteres): ");
        String cadena = scanner.next();
        scanner.nextLine();

        while (cadena.length() > maxLong) {
            System.out.println("La cadena puede ser de máximo " + maxLong + " caracteres. Intente nuevamente: ");
            cadena = scanner.next();
            scanner.nextLine();
        }

        return cadena;
    }

    public String leerCadenaMin(String message, int minLong) {
        System.out.println(message + " (min " + minLong + " caracteres): ");
        String cadena = scanner.next();
        scanner.nextLine();

        while (cadena.length() > minLong) {
            System.out.println("La cadena debe ser de mínimo " + minLong + " caracteres. Intente nuevamente: ");
            cadena = scanner.next();
            scanner.nextLine();
        }

        return cadena;
    }

    // LEER DATO EN ENUMERACION

    public <T> T leerEnum(List<T> opciones) {
        int opcion = 0;
        System.out.println("Seleccione una opcion: ");

        for (int i = 1; i < opciones.size() + 1; i++) {
            System.out.println("[" + i + "] " + opciones.get(i - 1));
        }

        while (true) {
            try {
                opcion = scanner.nextInt();

                if (opcion < 1 || opcion > opciones.size()) {
                    System.out.println("Opcion invalida.");
                } else {
                    return opciones.get(opcion - 1);
                }
            } catch (InputMismatchException e) {
                System.out.println("Ingrese valores numericos");
                scanner.next(); // limpio el buffer
            }

        }
    }
    // METODOS PARA LEER FECHAS VALIDAS POR TECLADO


}
