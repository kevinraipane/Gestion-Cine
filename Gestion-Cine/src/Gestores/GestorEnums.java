package Gestores;

import Enumeraciones.Idioma;
import Excepciones.NumeroFueraDelRangoException;
import Excepciones.SoloNumerosException;

import java.util.Scanner;

public class GestorEnums <T extends Enum<T>>{
    private Class<T> enumClass;

    // El tipo de enum se especifica al crear la clase
    public GestorEnums(Class<T> enumClass) {
        this.enumClass = enumClass;
    }

    // Método para listar todos los valores del enum
    public void listarEnum() {
        // Obtener los valores del enum usando Enum.values()
        int contador=0;
        T[] valores = enumClass.getEnumConstants();
        for (T valor : valores) {
            contador++;
            System.out.println("[" + contador + "] " + valor);
        }
    }

    //Muestra todos los elementos y permite seleccionar una opcion
    public T seleccionarElemento(Scanner entrada) throws NumeroFueraDelRangoException, SoloNumerosException {
        listarEnum();
        int seleccion;
        if(entrada.hasNextInt()){
            seleccion = entrada.nextInt();
            if(seleccion<0 || seleccion>enumClass.getEnumConstants().length){
                throw new NumeroFueraDelRangoException("ERROR: Opción no valida\n");
            }else {
                return enumClass.getEnumConstants()[seleccion - 1];
            }
        }else {
            throw new SoloNumerosException("ERROR: Solo se aceptan numeros\n");
        }
    }

}
