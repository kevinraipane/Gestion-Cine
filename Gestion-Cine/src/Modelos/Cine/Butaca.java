package Modelos.Cine;

import Modelos.General.BaseEntity;

import java.util.Objects;

public class Butaca extends BaseEntity<Integer> { //Recordar que generics no trabaja con primitivos
                                              //por eso le paso la envolvente del int en este caso

    /* Butaca tendrá su propia clase; así el código es más flexible y permite el día de
    mañana extender info de butacas (como tipo de butaca, precio según su ubicación, o
    cualquier cosa que se desee modificar el día de mañana; principio abierto cerrado),
    además así tenemos una clase que se encargue de gestionar butacas (su gestor, que será
    instanciado en Sala), mientras Sala sólo se encarga de ella misma (principio de
    responsabilidad única). */

    //Atributos:

    //private int numero;
    private String dniCliente; //null cuando esté disponible.

    //Constructor:

    public Butaca(int numero){
        super(numero);
        //this.numero = numero;
        this.dniCliente = null;
    }

    //Getters y setters:

    public int getNumero() {
        //return numero;
        return getAtributoIdentificador();
    }

    public void setNumero(int numero) {
        //this.numero = numero;
        setAtributoIdentificador(numero);
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    //Equals y HashCode:

    /*@Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Butaca butaca = (Butaca) object;
        return numero == butaca.numero && Objects.equals(dniCliente, butaca.dniCliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, dniCliente);
    }*/

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Butaca butaca = (Butaca) object;
        return Objects.equals(dniCliente, butaca.dniCliente) && Objects.equals(getNumero(), butaca.getNumero());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dniCliente);
    }

    //toString:

    @Override
    public String toString() {
        if(dniCliente == null){
            return  "\n-----------------\n" +
                    "Butaca número: " + /*numero*/ getNumero() + " disponible." +
                    "." +
                    "\n-----------------\n";
        }
        return  "\n-----------------\n" +
                "Butaca número: " + /*numero*/ getNumero() + " reservada por cliente con DNI: " + dniCliente +
                "." +
                "\n-----------------\n";
    }

}
