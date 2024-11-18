package Gestores.Funcionales;

import Excepciones.DominioInvalidoException;
import Excepciones.EmailInvalidoException;
import Excepciones.PasswordNoValidaException;
import Excepciones.UsernameNoValidoException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class GestorConsola {


    // -------------------------------------------------------------------------------------------------
    // ENUMERACIONES

    public <T> T leerEnum(List<T> opciones) {
        int opcion = 0;
        Scanner scanner = new Scanner(System.in);
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
                scanner.next();
            }
        }
    }

    // -------------------------------------------------------------------------------------------------
    // VALIDACIONES FRECUENTES PARA CADENAS DE TEXTO

    public boolean estaVacio(String cadena) {
        return cadena.trim().isEmpty();
    }

    public boolean contieneCaracteresNoValidos(String cadena) {
        return !cadena.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+");
    }

    public boolean repiteCaracteres(String cadena) {
        return cadena.matches(".*([a-zA-ZáéíóúÁÉÍÓÚñÑ])\\1\\1.*");
    }

    public boolean esLargoValido(String cadena, int min, int max) {
        return (cadena.length() <= max) && (cadena.length() >= min);
    }

    public boolean esLargoValido(String cadena, int largo) {
        return cadena.length() == largo;
    }

    public boolean contieneSoloNumeros(String cadena) throws NumberFormatException {
        return cadena.matches("\\d+");
    }

    public boolean esEmailValido(String email)
            throws EmailInvalidoException {
        Pattern pattern = Pattern.compile("^[\\w.-]+@[\\w-]+\\.[a-z]{2,}$");

        String[] dominiosValidos = {"@gmail.com", "@hotmail.com", "@outlook.com", "@yahoo.com", "@live.com"};

        //Valido formato del correo
        if (pattern.matcher(email).matches()) {
            for (String dominio : dominiosValidos) {
                if (email.endsWith(dominio)) {
                    return true;
                }
            }
            throw new DominioInvalidoException(email);

        } else {
            throw new EmailInvalidoException(email);
        }
    }

    public boolean isValidUsername(String username){
        if(estaVacio(username)){
            throw new UsernameNoValidoException("El nombre de usuario no puede estar vacio.");
        }

        if(!esLargoValido(username,3,20)){
            throw new UsernameNoValidoException("El nombre de usuario debe contener entre 3 y 20 caracteres.");
        }

        if(contieneCaracteresNoValidos(username)){
            throw new UsernameNoValidoException("El nombre de usuario contiene caracteres no validos.");
            //Podriamos mostrar los caracteres validos???a
        }

        return true;
    }

    public boolean isValidPassword(String password){
        if(estaVacio(password)){
            throw new PasswordNoValidaException("La contraseña no puede estar vacia.");
        }

        if(!esLargoValido(password,8,20)){
            throw new PasswordNoValidaException("La contraseña debe contener entre 8 y 20 caracteres.");
        }

        if(contieneCaracteresNoValidos(password)){
            throw new PasswordNoValidaException("La contraseña contiene caracteres no validos.");
        }

        return true;
    }

    // --------------------------------------------------------------------------------------------------
    // VALIDACIONES FRECUENTES PARA FECHAS

    public boolean esFormatoDeFechaValido(String fecha, String formato) {
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern(formato);

        try {
            LocalDate.parse(fecha, formatoFecha);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public LocalDate parsearFecha(String fecha) {
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            return LocalDate.parse(fecha, formatoFecha);
        } catch (DateTimeParseException e) {
            System.out.println("No se pudo parsear la fecha, el formato es incorrecto");
            return null;
        }
    }

    public boolean esAnteriorAHoy(LocalDate fecha) {
        return fecha.isBefore(LocalDate.now());
    }

    public boolean esEdadValida(LocalDate fecha) {
        LocalDate ahora = LocalDate.now();
        return fecha.isAfter(ahora.minusYears(130)) && fecha.isBefore(ahora.minusYears(18));
    }


    // --------------------------------------------------------------------------------------------------
    // VALIDACIONES FRECUENTES PARA NUMEROS

    public boolean perteneceAlRango(int numero, int min, int max) {
        return numero >= min && min <= max;
    }

    public boolean esMayor(int numero, int min) {
        return numero > min;
    }

    public boolean esMenor(int numero, int max) {
        return numero < max;
    }

    public boolean esPositivo(int numero) {
        return numero > 0;
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

/**
 * Pattern pattern = Pattern.compile("^[\\w.-]+@[\\w-]+\\.[a-z]{2,}$");
 * crea un patrón de expresión regular en Java para validar la estructura de un correo electrónico. Aquí está el
 * desglose de la expresión:
 *
 * ^: Marca el inicio de la cadena.
 *
 * [\\w.-]+: Busca uno o más caracteres alfanuméricos, guiones (-), puntos (.) o guiones bajos (_). Esto representa
 * la parte del nombre de usuario del correo (antes del @).
 *
 * \\w representa cualquier carácter alfanumérico o guion bajo (equivalente a [A-Za-z0-9_]).
 * . y - están permitidos, ya que son comunes en los nombres de usuario de correos electrónicos.
 * @: Verifica que el carácter @ esté presente en la posición correcta.
 *
 * [\\w-]+: Busca uno o más caracteres alfanuméricos o guiones (-) para el nombre del dominio (la parte entre @ y el
 * punto final).
 *
 * \\.: Verifica la presencia de un punto (.) antes del dominio superior, como .com.
 *
 * [a-z]{2,}: Busca dos o más letras minúsculas para el dominio de nivel superior (como com, org, net), especificando
 * que la extensión debe tener al menos dos letras.
 *
 * $: Marca el final de la cadena, asegurando que no haya caracteres adicionales después del dominio superior.
 */

}
