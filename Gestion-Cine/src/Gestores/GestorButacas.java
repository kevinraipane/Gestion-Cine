package Gestores;

import Excepciones.ButacaInexistenteException;
import Excepciones.ButacaNegativaException;
import Modelos.Cine.Butaca;

import java.util.HashMap;

public class GestorButacas {

    //Atributos:

    private HashMap<Integer, Butaca> butacas; // La más eficiente para insertar, buscar
                                              // y borrar. Mayor rendimiento que Lists.

    //Constructor:

    public GestorButacas(int totalButacas){
        butacas = new HashMap<>();
        for(int i = 1; i <= totalButacas; i++){
            butacas.put(i, new Butaca(i));
        }
    }

    /* Métodos: */

    //Reservar:

    public void reservarButaca(int numButaca, String dniCliente) throws ButacaInexistenteException {
        if(numButaca < 0){
            throw new ButacaNegativaException(numButaca);
            //Primero, comprueba que el numero no sea negativo.
        }
        if(!butacas.containsKey(numButaca)){
            throw new ButacaInexistenteException(numButaca);
            //Segundo, comprueba que el número no se pase de la colección.
        }
        Butaca butaca = butacas.get(numButaca);
        if(butaca.getDniCliente() != null){
            System.out.println("La butaca " + numButaca + " ya se encuentra reservada, por " +
                    "el cliente de DNI: " + butaca.getDniCliente() + ".");
            //Tercero, verifica que la butaca no se encuentre reservada.
        }
        else{
            butaca.setDniCliente(dniCliente);
            System.out.println("Butaca " + numButaca + " reservada con éxito, para el cliente" +
                    " con DNI: " + butaca.getDniCliente() + ".");
        }
    }

    //Cancelar reserva:

    public void liberarButaca(int numButaca) throws ButacaInexistenteException{
        if(!butacas.containsKey(numButaca)){
            throw new ButacaInexistenteException(numButaca);
        }
        Butaca butaca = butacas.get(numButaca);
        if(butaca.getDniCliente() == null){
            System.out.println("La butaca ya se encuentra libre.");
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
