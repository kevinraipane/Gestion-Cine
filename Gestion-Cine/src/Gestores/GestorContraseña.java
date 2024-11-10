package Gestores;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GestorContraseña {
    //Agregar al informa como encriptamos la contraseña y como la utilizamos
    public static String encriptadorContraseña(String contraseña){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(contraseña.getBytes());
            StringBuilder hexString = new StringBuilder();

            for(byte b : hash){
                String hex = Integer.toHexString(0xff & b);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }catch (NoSuchAlgorithmException e){
            throw new RuntimeException("Error al encriptar la contraseña.",e);
        }
    }
}
/**
 * --Obtener una instancia del algoritmo SHA-256: MessageDigest digest = MessageDigest.getInstance("SHA-256");
 * MessageDigest es una clase en Java que proporciona implementaciones para
 * algoritmos de resumen de mensajes, como SHA-256. Aquí, obtenemos una instancia configurada para el algoritmo
 * "SHA-256".
 *
 * --Convertir la contraseña a un arreglo de bytes y aplicar el hash: byte[] hash = digest.digest(contraseña.getBytes());
 * El método digest toma el array de bytes de la contraseña y calcula el resumen (hash) aplicando SHA-256, devolviendo
 * un array de bytes que representa el hash.
 *
 * --Crear un StringBuilder para almacenar el resultado en hexadecimal:StringBuilder hexString = new StringBuilder();
 * Vamos a construir la cadena del hash en formato hexadecimal, por lo que StringBuilder es útil aquí para concatenar
 * eficientemente cada valor hexadecimal.
 *
 * --Convertir cada byte en hexadecimal y formatearlo:
 * for (byte b : hash) {
 *     String hex = Integer.toHexString(0xff & b);
 *     if (hex.length() == 1) hexString.append('0');
 *     hexString.append(hex);
 * }
 *Aquí, iteramos sobre cada byte en el array hash. En cada iteración:
 * 0xff & b convierte el byte en un entero positivo.
 * Integer.toHexString(...) convierte ese valor en una cadena hexadecimal.
 * hexString.append(hex) añade el valor hexadecimal a hexString. Si el valor hexadecimal tiene una sola cifra, añadimos
 * un 0 al inicio para mantener la uniformidad de longitud (dos caracteres por byte).
 *
 *--Convertir StringBuilder en una cadena y devolverla:return hexString.toString();
 * Esta línea devuelve el hash final como una cadena de texto en formato hexadecimal.
 *
 * --Capturar excepciones de NoSuchAlgorithmException:
 * catch (NoSuchAlgorithmException e) {
 *     throw new RuntimeException("Error al encriptar la contraseña", e);
 * }
 * Si el entorno de ejecución no soporta SHA-256, se lanza una NoSuchAlgorithmException. Aquí, la capturamos y lanzamos
 * una RuntimeException para notificar al usuario de un error en la encriptación de la contraseña.
 */