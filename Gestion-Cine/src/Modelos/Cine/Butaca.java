package Modelos.Cine;

import java.util.Objects;

public class Butaca {

    /* Butaca tendrá su propia clase; así el código es más flexible y permite el día de
    mañana extender info de butacas (como tipo de butaca, precio según su ubicación, o
    cualquier cosa que se desee modificar el día de mañana; principio abierto cerrado),
    además así tenemos una clase que se encargue de gestionar butacas (su gestor, que será
    instanciado en Sala), mientras Sala sólo se encarga de ella misma (principio de
    responsabilidad única). */

    //Atributos:

    private int numero;
    private String dniCliente; //null cuando esté disponible.

    //Constructor:

    public Butaca(int numero){
        this.numero = numero;
        this.dniCliente = null;
    }

    //Getters y setters:

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    //toString:

    @Override
    public String toString() {
        if(dniCliente == null){
            return  "\n-----------------\n" +
                    "Butaca número: " + numero + " disponible." +
                    "." +
                    "\n-----------------\n";
        }
        return  "\n-----------------\n" +
                "Butaca número: " + numero + " reservada por cliente con DNI: " + dniCliente +
                "." +
                "\n-----------------\n";
    }

    //Equals y HashCode:

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Butaca butaca = (Butaca) object;
        return numero == butaca.numero && Objects.equals(dniCliente, butaca.dniCliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, dniCliente);
    }

}
