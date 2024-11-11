package Gestores;


import Excepciones.ButacaEnEstadoActualException;
import Excepciones.ButacaInexistenteException;
import Modelos.Cine.Butaca;

import java.util.TreeMap;


public class GestorButacas {

    //Atributos:

    private TreeMap<Integer, Butaca> butacas;
    // Map: la más eficiente para insertar, buscar y borrar.
    // TreeMap para mantener el orden por numero de butaca.

    //Constructor:

    public GestorButacas(int totalButacas){

        butacas = new TreeMap<>();

        for(int i = 1; i <= totalButacas; i++){
            butacas.put(i, new Butaca(i));
        }
    }

    /* Métodos: */

    //Reservar:


    public void reservarButaca(int numButaca, String dniCliente) throws ButacaInexistenteException, ButacaEnEstadoActualException {

        //Primero, comprueba que el numero no sea negativo.
        if(numButaca < 0)
            throw new ButacaInexistenteException("El número: " + numButaca +
                    " no es válido. Éste no puede ser negativo.");

        //Segundo, comprueba que el número no se pase de la colección.
        if(!butacas.containsKey(numButaca))
            throw new ButacaInexistenteException(numButaca);

        Butaca butaca = butacas.get(numButaca);

        //Tercero, verifica que la butaca no se encuentre reservada.
        if(butaca.getDniCliente() != null)
            throw new ButacaEnEstadoActualException("La butaca " + numButaca +
                    " ya se encuentra reservada, por el cliente de DNI: " +
                    butaca.getDniCliente() + ".");

        //Si no lanza ninguna excepción, hacemos la reserva y notificamos:

        butaca.setDniCliente(dniCliente);
        System.out.println("Butaca " + numButaca + " reservada con éxito, " +
                "para el cliente con DNI: " + butaca.getDniCliente() + ".");

    }

    //Cancelar reserva:


    public void liberarButaca(int numButaca) throws ButacaInexistenteException, ButacaEnEstadoActualException{

        if(!butacas.containsKey(numButaca)){
            throw new ButacaInexistenteException(numButaca);
        }
        Butaca butaca = butacas.get(numButaca);
        if(butaca.getDniCliente() == null){

            throw new ButacaEnEstadoActualException("La butaca ya se encuentra libre.");

        }
        else{
            butaca.setDniCliente(null);
        }
    }

    //Imprimir el estado de todas las butacas:

    public void imprimirButacas() {
        for (Butaca butaca : butacas.values()) {
            System.out.println(butaca);
        }
    }

}
