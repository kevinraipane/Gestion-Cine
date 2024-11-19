package Gestores.Cine;


import Enumeraciones.TipoColeccion;
import Excepciones.KevPF98.Butacas.ButacaInexistenteException;
import Excepciones.KevPF98.Butacas.ButacaInhabilitadaException;
import Excepciones.KevPF98.Flexibles.*;
import Modelos.Cine.Butaca;
import Modelos.General.Crud;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class GestorButacas {

    //Sala ya no sabrá nada de butacas; todo será independiente hasta llegar a función o entrada.

    //Atributos:

    //private TreeMap<Integer, Butaca> butacas;
    private Crud<Integer, Butaca> butacasCrud;
    /// Map: eficiente para insertar, buscar y borrar.
    /// Y TreeMap para mantener el orden natural por número de butaca.
    //public int lastIdButaca = 0;

    //newId = ++LastId;
    //Y se lo pasamos de parametro al constructor de la instancia nueva.

    //Constructor:

    public GestorButacas(int totalButacas) throws ColeccionInvalidaException {

        //butacas = new TreeMap<>();
        butacasCrud = new Crud<>(TipoColeccion.TREEMAP);

        /*for(int i = 1; i <= totalButacas; i++){
            butacas.put(i, new Butaca(i));
        }*/

        /*for(int i = 1; i <= totalButacas; i++){
            butacasCrud.agregarSinMensajeExito(i, new Butaca(i), false);
        }*/

        /// Ocasionaba que se repitiera el mensaje de agregado con éxito, planeado para agregados individuales.
        /// Y mi idea agregaba aún más complejidad solamente para 1 detalle.
        /// Así que, acá opté por la forma clásica del map:

        for(int i = 1; i <= totalButacas; i++){
            butacasCrud.getMap().put(i, new Butaca(i));
        }
    }

    /// Métodos: ///

    /// Los métodos que operan con DNI: desde el menú, siempre validaremos que el dni ingresado por
    /// usuario pertenezca a un cliente. De lo contrario, nunca se accederá a estos métodos.

    /// Métodos lógicos de baja y alta de butacas (las ihabilita por posibles roturas en las mismas):

    public boolean darDeBajaButaca(int numeroButaca) throws ColeccionInvalidaException, ColeccionVaciaException, ElementoNoEncontradoException, ProhibidoEstadoActualException {
        Butaca butaca = butacasCrud.buscarEnMapPorClave(numeroButaca);
        if(butaca != null){
            if (butaca.isAlta()){
                butaca.setAlta(false);
                System.out.println("Butaca numero: " + numeroButaca + " dada de baja exitosamente.");
                return true;
            }
            else {
                throw new ProhibidoEstadoActualException("Error: la butaca ya se encuentra dada de baja.");
            }
        }
        else {
            throw new ElementoNoEncontradoException();
        }
        /// Sólo retorna true cuando logra la operación. Así que, cuando se desee usar este método para corroborar, simplemente
        /// crear un booleano = false. Tratar de asignarle el valor que devuelva este método a ese booleano en el try.
        /// Si lanza excepción; el flujo seguirá por el catch y en el booleano permancerá el valor anterior (false).
        /// "if(esteBoolean==false)" querrá decir que la butaca no se dio de baja, sea pq no se encontró o pq ya estaba de baja.
    }


    public boolean darDeAltaButaca(int numeroButaca) throws ColeccionInvalidaException, ColeccionVaciaException, ElementoNoEncontradoException, ProhibidoEstadoActualException {
        Butaca butaca = butacasCrud.buscarEnMapPorClave(numeroButaca);
        if(butaca != null){
            if (!butaca.isAlta()){
                butaca.setAlta(true);
                System.out.println("Butaca numero: " + numeroButaca + " dada de alta exitosamente.");
                return true;
            }
            else {
                throw new ProhibidoEstadoActualException("Error: la butaca ya se encuentra dada de alta.");
            }
        }
        else {
            throw new ElementoNoEncontradoException();
        }
        /// Sólo retorna true cuando logra la operación. Así que, cuando se desee usar este método para corroborar, simplemente
        /// crear un booleano = false. Tratar de asignarle el valor que devuelva este método a ese booleano en el try.
        /// Si lanza excepción; el flujo seguirá por el catch y en el booleano permancerá el valor anterior (false).
        /// "if(esteBoolean==false)" querrá decir que la butaca no se dio de alta, sea pq no se encontró o pq ya estaba habilitada.
    }


    /// Método de reservar butaca (no es agregar 1 butaca a la sala):
    /// Todo lo que tenga DNI: lo comenté. Trasladar a Reservas/Facutras. Ya que, para buscar por DNI acá,
    /// tendría que estar accediendo a otro gestor... en cambio, Reservas/Facturas es el gestor de una clase
    /// que vincula butacas con clientes.


    public boolean ocuparButaca(int numButaca)  throws ButacaInexistenteException, ProhibidoEstadoActualException, ButacaInhabilitadaException {

        /// Primero, comprueba que el numero no sea negativo (para un mensaje de excepción más específico).

        if(numButaca < 0)
            throw new ButacaInexistenteException("El número: " + numButaca +
                                                 " no es válido. Éste no puede ser negativo.");

        /// Segundo, comprueba que el número de butaca no exceda la colección.

        if(!butacasCrud.getMap().containsKey(numButaca))
            throw new ButacaInexistenteException(numButaca);

        Butaca butaca = butacasCrud.getMap().get(numButaca);

        /// Tercero, nos aseguramos que la butaca esté dada de alta y bien sanita, para evitar juicios.

        if(!butaca.isAlta()){
            throw new ButacaInhabilitadaException();
        }

        /// Cuarto, verifica que la butaca no se encuentre reservada.

        if(butaca.isOcupada())
            throw new ProhibidoEstadoActualException("Error: la butaca " + numButaca +
                    " ya se encuentra reservada.");

        /// Si no lanza ninguna excepción, hacemos la reserva y notificamos:

        butaca.setOcupada(true);
        System.out.println("Butaca " + numButaca + " reservada con éxito.");
        return true;

        /// Sólo retorna true cuando logra la operación. Así que, cuando se desee usar este método para corroborar, simplemente
        /// crear un booleano = false. Tratar de asignarle el valor que devuelva este método a ese booleano en el try.
        /// Si lanza excepción; el flujo seguirá por el catch y en el booleano permancerá el valor anterior (false).
        /// "if(esteBoolean==false)" querrá decir que la butaca no se ocupó, sea pq no se encontró o pq ya estaba reservada.

    }


    public boolean liberarButaca(int numButaca) throws ButacaInexistenteException, ProhibidoEstadoActualException {

        /// Primero, comprueba que el numero no sea negativo (para un mensaje de excepción más específico).

        if(numButaca < 0)
            throw new ButacaInexistenteException("El número: " + numButaca +
                    " no es válido. Éste no puede ser negativo.");

        /// Segundo, comprueba que el número no se pase de la colección.

        if(!butacasCrud.getMap().containsKey(numButaca)){
            throw new ButacaInexistenteException(numButaca);
        }

        /// Tercero, buscamos la butaca.

        Butaca butaca = butacasCrud.getMap().get(numButaca);

        /// Comprobamos que efectivamente esté ocupada.

        if(!butaca.isOcupada()){
            throw new ProhibidoEstadoActualException("Error: la butaca ya se encuentra libre.");
        }
        else{
            butaca.setOcupada(false);
        }

        return true;

    } /// Cuando se llame, informar (desde el case) que la butaca fue liberada con éxito.


    /*
    public void reservarButaca(int numButaca, String dniCliente) throws ButacaInexistenteException, ProhibidoEstadoActualException, ButacaInhabilitadaException {

        /// Primero, comprueba que el numero no sea negativo.

        if(numButaca < 0)
            throw new ButacaInexistenteException("El número: " + numButaca +
                    " no es válido. Éste no puede ser negativo.");

        /// Segundo, comprueba que el número no se pase de la colección.

        if(!butacasCrud.getMap().containsKey(numButaca))
            throw new ButacaInexistenteException(numButaca);

        Butaca butaca = butacasCrud.getMap().get(numButaca);

        /// Tercero, nos aseguramos que la butaca esté dada de alta y bien sanita, para evitar un juicio.

        if(!butaca.isAlta()){
            throw new ButacaInhabilitadaException();
        }

        /// Cuarto, verifica que la butaca no se encuentre reservada.

        if(butaca.getDniCliente() != null)
            throw new ProhibidoEstadoActualException("La butaca " + numButaca +
                    " ya se encuentra reservada, por el cliente de DNI: " +
                    butaca.getDniCliente() + ".");

        /// Si no lanza ninguna excepción, hacemos la reserva y notificamos:

        butaca.setDniCliente(dniCliente);
        System.out.println("Butaca " + numButaca + " reservada con éxito, " +
                "para el cliente con DNI: " + butaca.getDniCliente() + ".");

    }*/

    /// Método de cancelar reserva (no es borrar 1 butaca de la sala):

    /*
    public boolean liberarButaca(int numButaca) throws ButacaInexistenteException, ProhibidoEstadoActualException{

        if(!butacasCrud.getMap().containsKey(numButaca)){
            throw new ButacaInexistenteException(numButaca);
        }
        Butaca butaca = butacasCrud.getMap().get(numButaca);
        if(butaca.getDniCliente() == null){
            throw new ProhibidoEstadoActualException("La butaca ya se encuentra libre.");
        }
        else{
            butaca.setDniCliente(null);
        }
        return true;
    } /// Cuando se llame, informar (desde el case) que la butaca fue liberada con éxito.
     */


    /// Cancelar la reserva de todas las butacas coincidentes con determinado dni: ///

    /*
    public void liberarVariasButacas(String dni) throws ColeccionInvalidaException, ColeccionVaciaException, ElementoNoEncontradoException, ProhibidoEstadoActualException, ButacaInexistenteException {
        List<Butaca> butacasCoincidentes = asientosMismaPersona(dni);

        /// Traemos una lista de butacas con reserva correspondiente al DNI.
        /// Antes que nada, nos aseguramos que no esté vacía (o sea que haya coincidencias) para
        /// no hacer innecesariamente todo lo demás.

        if(!butacasCoincidentes.isEmpty()){

            ///Como medida de seguridad, le mostramos al usuario todas las butacas que serán liberadas.

            System.out.println("Las butacas correspondientes al DNI: " + dni + " son: ");
            System.out.println("\n-----------------------------------------\n");
            for (Butaca butaca : butacasCoincidentes){
                System.out.println("La butaca número: " + butaca.getNumero());
            }
            System.out.println("\n-----------------------------------------\n");
            Scanner scanner = new Scanner(System.in);
            String respuesta;
            boolean escape = false;

            ///Ahora le pedimos que confirme si quiere levantar esas reservas.

            do {
                System.out.println("¿Está seguro/a que desea cancelar la reserva de esta(s) butaca(s)? S/N");
                respuesta = scanner.nextLine();
                if (respuesta.equalsIgnoreCase("S") || respuesta.equalsIgnoreCase("N")){
                    escape = true;
                }
                else {
                    System.out.println("Entrada inválida.");
                }
            } while (!escape);
            if (respuesta.equalsIgnoreCase("S")){

                ///Recorremos la lista de asientos que pertenecen al dni buscado y levantamos reservas.

                for (Butaca butaca : butacasCoincidentes){
                    liberarButaca(butaca.getNumero());
                }
                System.out.println("Reservas canceladas con éxito.");
            }
            else{
                System.out.println("Operación cancelada.");
            }
        }
        else{
            throw new ElementoNoEncontradoException();
        }
    }*/

    /// Del mismo modo, puede venir 1 persona y querer varias butacas:

    /*
    public void reservarVariasButacas(String dni, List<Integer> numerosDeButacas) throws ColeccionInvalidaException, ColeccionVaciaException, ProhibidoEstadoActualException, ButacaInexistenteException, ButacaInhabilitadaException {
        /// Asumimos que antes de llegar a este punto, el cliente/admin ya pudo ver/se le informó
        /// qué butacas están disponibles (en el mismo case, justo antes de llamar a este método).
        /// Y hubo una validación, para que NINGUNA de las butacas elegidas esté reservada (de modo
        /// que no se le reserve unas butacas sí y otras no...).
        /// Por lo que el printeado de las disponibles serían innecesario.

        /// También antes debe haber validaciones para: llegar al método sin una lista vacía,
        /// sin un número de butaca negativo, ni que exceda el limite de butacas en sala (se saldría
        /// del hashmap.

        /// /// BASICAMENTE: TODAS LAS RESERVAS DEL MÉTODO RESERVARBUTACA DEBEN MANEJARSE ANTES DE LLEGAR
        /// /// A ESTE MÉTODO.

        for (Integer numButaca : numerosDeButacas){
            reservarButaca(numButaca, dni);
        }

    }*/

    /// Métodos de búsqueda:

    /// Buscar butacas pertenecientes a una misma persona:

    /*
    public List<Butaca> asientosMismaPersona(String dni) throws ColeccionInvalidaException, ColeccionVaciaException {

        /// Necesité asegurarme que al ir recorriéndolo, antes de hacer la comparación, el value (dni) no sea nulo.
        /// De lo contrario, me tiraba excepción nullpointer:

        List<Butaca> butacasCoincidentes = butacasCrud.buscarPorAtributoEnValores(butaca -> butaca.getDniCliente() != null && butaca.getDniCliente().equals(dni));

        return butacasCoincidentes;
    }*/


    //Métodos de listar/mostrar:

    //Listar butacas pertenecientes a una misma persona:

    /*
    public void listarAsientosMismaPersona(String dni) throws ColeccionInvalidaException, ColeccionVaciaException, ElementoNoEncontradoException {
        butacasCrud.mostrarCoincidenciasEnMap(butaca -> butaca.getDniCliente() != null && butaca.getDniCliente().equals(dni));
    }*/


    /// Imprimir el estado de TODAS las butacas:

    public void imprimirButacas() throws ColeccionVaciaException {
        if (!butacasCrud.getMap().isEmpty()){
            for (Butaca butaca : butacasCrud.getMap().values()) {
                System.out.println(butaca);
            }
        }
        else {
            throw new ColeccionVaciaException();
        }
    }

    public List<Butaca> retornarButacasValues() throws ColeccionVaciaException {
        List<Butaca> butacas = new ArrayList<>();
        if (!butacasCrud.getMap().isEmpty()){
            for (Butaca butaca : butacasCrud.getMap().values()) {
                butacas.add(butaca);
            }
        }
        else {
            throw new ColeccionVaciaException();
        }
        return butacas;
    }

    public int contarButacas(){
        return butacasCrud.getMap().size();
    }

    //Lista de butacas y los correspondientes clientes que las reservaron (descartado;
    // tecnicamente hace lo mismo que el método de arriba):

    /*public void mostrarButacasDuenios() throws ColeccionVaciaException {
        butacasCrud.listarTodos();
    }*/

}
