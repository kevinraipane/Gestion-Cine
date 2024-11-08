package Gestores;

import java.util.Scanner;

public class GestorPersonas {

    //Agregar coleccion

    //Metodos estaticos para la verificacion de los datos cargados, luego mover a otro lugar de ser
    //necesario

    //Metodo para cargar el nombre y verificar sus datos
    //Luego pasar las verificaciones al gestor de consola
    public static String cargarNombre(){
        Scanner scanner = new Scanner(System.in);
        String nombre = "";//La inicializo como nula.
        boolean valido = false;

        while (!valido){
            System.out.println("Introduzca su nombre: ");
            nombre = scanner.nextLine().trim();//Nota al final

            //Verificacion: no puede ser nulo ni estar vacio.
            if(nombre == null  || nombre.isEmpty()){
                System.out.println("El nombre no puede estar vacio.Intentelo nuevamente.");
                continue;
            }

            //Verificacion: no puede comenzar con espacio.
            if(nombre.startsWith(" ")){
                System.out.println("El nombre no puede empezar con un espacio.Intentelo nuevamante.");
                continue;
            }

            //Verificacion: solo puede contener letras y espacios (incluida la ñ). //Nota al final
            if(!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")){
                System.out.println("El nombre solo puede contener letras. Intentelo nuevamente.");
                continue;
            }

            //Verificacion: que no sea una sola letra
            if(nombre.length() == 1){
                System.out.println("El nombre no puede ser una sola letra. Intentelo nuevamente.");
                continue;
            }

            //Verificacion: no puede tener la misma letra 3 o mas veces seguidas
            if(nombre.matches(".*([a-zA-ZáéíóúÁÉÍÓÚñÑ])\\1\\1.*")){
                System.out.println("El nombre no puede tener la misma letra tres veces o mas seguidas.Intentelo nuevamente.");
                continue;
            }

            System.out.println("Nombre valido: " +nombre);
            valido = true;
        }
        scanner.close();
        return nombre;
    }

    //Acomodar para apellido lo mismo, al separar las verificaciones.

    //Metodo para cargar dni, se debe agregar un metodo a consolo que reciba un minimo maximo de caracteres el string

    public static String cargarDni(){
        Scanner scanner = new Scanner(System.in);
        String dni = "";
        boolean valido = false;

        while (!valido){
            System.out.println("Ingrese su DNI: ");
            dni = scanner.nextLine().trim();

            //Verificacion: el dni debe tener entre 7 y 8 caracteres
            if(dni.length() < 7 || dni.length() > 8){
                System.out.println("El dni debe tener entre 7 y 8 caracteres. Intentelo nuevamente.");
                continue;
            }

            //Verificacion: el dni no debe empezar con espacio
            if(dni.startsWith(" ")){
                System.out.println("El DNI no puede comenzar con espacio.");
                continue;
            }

            //Verificacion: el dni debe contener solo numeros
            if(!dni.matches("\\d+")){
                System.out.println("El DNI solo debe contener numeros. Intentelo nuevamente.");
                continue;
            }

            //Verificacion: el rango del dni debe tener sentido (entre 1.000.000 y 99.999.999)
            long dniLong = Long.parseLong(dni);
            if(dniLong < 1_000_000 || dniLong > 99_999_999){
                System.out.println("El dni debe estar en el rango de 1.000.000 y 99.999.999. Intentelo nuevamente.");
                continue;
            }

            System.out.println("DNI valido: " +dni);
            valido = true;
        }
        scanner.close();
        return dni;
    }
}

/**
 * El trim elimina los espacios al principio y final.
 */

/**
 * [a-zA-Z]: Permite letras del alfabeto en mayúsculas y minúsculas.
 * [áéíóúÁÉÍÓÚñÑ]: Permite letras con acentos (tildes) y la letra "ñ" en mayúscula y minúscula.
 * \\s: Permite los espacios en blanco (aunque no puede haber más de un espacio seguido, como lo validas en otra parte).
 * +: Indica que puede haber una o más de las letras o espacios mencionados, pero no números ni otros caracteres.
 */

/**
 * .*: Permite cualquier cantidad de caracteres al principio y al final del patrón. Esto es para que las letras
 * repetidas puedan estar en cualquier posición del nombre (al principio, en el medio o al final).
 *
 * ([a-zA-ZáéíóúÁÉÍÓÚñÑ]): Este grupo define cualquier letra válida que se puede encontrar en el nombre, capturando una
 * de ellas. La letra capturada se guarda como referencia para poder volver a usarla.
 *
 * \\1\\1: Esto indica que la misma letra capturada en el grupo anterior debe repetirse dos veces más. En total, esto
 * busca tres letras iguales seguidas.
 */

/**
 * \\d+ es una expresión regular (regex) que significa:
 * \\d: representa cualquier dígito (del 0 al 9).
 * +: indica que debe haber al menos un dígito, pero pueden ser más.
 */