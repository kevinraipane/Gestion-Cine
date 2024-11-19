package Modelos.Cine;

//import Modelos.General.BaseEntity;

//import Enumeraciones.EstadoButaca;

import java.util.Objects;

public class Butaca{

    /* Butaca tendrá su propia clase; así el código es más flexible y permite el día de
    mañana extender info de butacas (como tipo de butaca, precio según su ubicación, o
    cualquier cosa que se desee modificar el día de mañana (principio abierto cerrado),
    además así tenemos una clase que se encargue de gestionar butacas (su gestor, que será
    instanciado en Sala), mientras Sala sólo se encarga de ella misma (principio de
    responsabilidad única). */

    //Atributos:

    private int idButaca; //¿Hace falta? Cada coleccion de butacas pertenece a una clase Sala
    // diferente. Y dentro de cada colección, el numero de butaca nunca se repite; ¿funciona como
    // identificador único?

    // ¿Capaz si una persona sacó entrada para diferentes butacas de diferentes salas, y quiero
    // saber todos los asientos reservados por esa persona? Sea para cancelar sus reservas o para
    // listárselas.

    //Claro; si usara numero de butaca como identificador, si yo recorro cada sala buscando las butacas
    //donde coincida el dni de la persona y retorno la butaca, podría tener numeros repetidos de buta-
    //cas de diferentes salas, además, ¿con sólo un número de butaca como sé las butacas de qué salas
    //reservó? Si hago por id, después puedo traerme toda la info de esa butaca y saber dónde pertenece.

    public static int contador = 0;
    private int numero;
    //private String dniCliente; //null cuando esté disponible.
    private boolean alta;
    private boolean ocupada; //fusionar ambos booleans en enum:
    // RESERVADA, LIBERADA, FUERA_DE_SERVICIO.
    //EstadoButaca estadoButaca;



    //Constructor:

    public Butaca(int numero){
        this.idButaca = ++contador;
        this.numero = numero;
        //this.dniCliente = null;
        this.alta = true;
        this.ocupada = false;
        //this.estadoButaca = EstadoButaca.LIBERADA;
    }

    //Getters y setters:

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    /*public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }*/

    public boolean isAlta() {
        return alta;
    }

    public void setAlta(boolean alta) {
        this.alta = alta;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    /*public EstadoButaca getEstadoButaca() {
        return estadoButaca;
    }

    public void setEstadoButaca(EstadoButaca estadoButaca) {
        this.estadoButaca = estadoButaca;
    }*/

    //Equals y HashCode:

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Butaca butaca = (Butaca) object;
        return idButaca == butaca.idButaca && numero == butaca.numero;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idButaca, numero);
    }

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

    /*@Override
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
    }*/

    //toString:

    @Override
    public String toString() {

        String estado = alta ? "Habilitada" : "Inhabilitada"; /*estadoToString();*/

        if(/*dniCliente == null*/ !ocupada){
            return  "\n-----------------\n" +
                    "Id de butaca: " + idButaca + "." +
                    "Butaca número: " + /*numero*/ getNumero() + ", sin reservar." +
                    "\nEstado actual: " + estado + "." +
                    "\n-----------------\n";
        }
        return      "\n-----------------\n" +
                    "Id de butaca: " + idButaca + "." +
                    "Butaca número: " + numero /*getNumero()*/ + " reservada."/*" reservada por cliente con DNI: " + dniCliente +
                    "."*/ +
                    "\nEstado actual: " + estado + "." +
                    "\n-----------------\n";

        /*return "\n-----------------\n" +
                "Butaca número: " + numero + "." +
                "\nEstado actual: " + estado + "." +
                "\n-----------------\n";*/
    }

    /*
    public String estadoToString() {
        switch (estadoButaca) {
            case LIBERADA:
                return "Liberada";
            case RESERVADA:
                return "Reservada";
            case FUERA_DE_SERVICIO:
                return "Fuera de servicio";
            default:
                return "Desconocido";
        }
    }
    */

}
