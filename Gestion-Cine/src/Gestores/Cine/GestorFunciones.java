package Gestores.Cine;

import Enumeraciones.EstadoFuncion;
import Enumeraciones.EstadoSala;
import Enumeraciones.TipoColeccion;
import Enumeraciones.TipoSala;
import Excepciones.KevPF98.Flexibles.*;
import Modelos.Cine.Butaca;
import Modelos.Cine.Funcion;
import Modelos.Cine.Horario;
import Modelos.Cine.Sala;
import Modelos.General.Crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorFunciones {

    // Atributos:

    private Crud<Integer, Funcion> funcionesCrud; // HashMap: No me importa el orden. Eficiente
                                                  // para búsqueda, inserción, eliminación.

    // Constructor:

    public GestorFunciones() throws ColeccionInvalidaException {
        this.funcionesCrud = new Crud<>(TipoColeccion.HASHMAP);
    }

    // Métodos:

    /// Inserción:

    // Agregar nueva función:

    public void agregarFuncion(Funcion funcion) throws ElementoEnColeccionException {
        funcionesCrud.agregar(funcion.getIdFuncion(), funcion, false);
    }

    /// Eliminación:

    // Eliminación total de los datos de una función en el sistema:

    public boolean eliminarPermanentemente(int idFuncion) throws ColeccionInvalidaException, ColeccionVaciaException, ElementoNoEncontradoException, ProhibidoEstadoActualException {

        Funcion funcion = funcionesCrud.buscarEnMapPorClave(idFuncion);

        if(funcion != null){

            /// Como medida de seguridad: no se podrán eliminar los datos de una función, sin que antes
            /// esté "dada de baja".

            if (funcion.getEstadoFuncion() != EstadoFuncion.CANCELADA){

                /// Confirmación, por seguridad:

                System.out.println("La información de su función es:");
                System.out.println(funcion);

                Scanner scanner = new Scanner(System.in);
                String respuesta;
                boolean escape = false;

                /// Ahora le pedimos que confirme si quiere darla de baja.

                do {
                    System.out.println("ALERTA: esta operación eliminará permanentemente los datos de la función seleccionada.");
                    System.out.println("¿Está seguro/a que desea continuar? S/N");
                    respuesta = scanner.nextLine();
                    if (respuesta.equalsIgnoreCase("S") || respuesta.equalsIgnoreCase("N")){
                        escape = true;
                    }
                    else {
                        System.out.println("Entrada inválida.");
                    }
                } while (!escape);

                if (respuesta.equalsIgnoreCase("S")){
                    funcionesCrud.eliminarEnMap(idFuncion);
                    System.out.println("Datos de la función eliminados exitosamente del sistema.");
                    return true;
                }
                else{
                    System.out.println("Operación cancelada.");
                    return false;
                }

            }
            else {
                throw new ProhibidoEstadoActualException("Para eliminar permanentemente los datos de una función, primero debe ser cancelada.");
            }

        }
        else{
            throw new ElementoNoEncontradoException();
        }

    }
    /// Sólo retorna true cuando logra la operación. Así que, cuando se desee usar este método para corroborar, simplemente
    /// crear un booleano = false. Tratar de asignarle el valor que devuelva este método a ese booleano en el try.
    /// Si lanza excepción; el flujo seguirá por el catch y en el booleano permancerá el valor anterior (false).
    /// "if(esteBoolean==false)" querrá decir que la función no se eliminó, sea pq no se encontró, pq se canceló la operación
    /// o pq la función no cumplía con la condición de estar dada de baja primero.


    /// Modificar estado de función:

    // Dar de baja función:

    public boolean cancelarFuncion(int idFuncion) throws ColeccionInvalidaException, ColeccionVaciaException, ElementoNoEncontradoException, ProhibidoEstadoActualException {

        /// Si se cancela, podríamos mostrarle al admin un listado de las personas que iban a asistir
        /// a esta función (justo después de invocar este método y que retorne true, desde gestor de
        /// entradas). Misma lógica para reprogramar.

        Funcion funcion = funcionesCrud.buscarEnMapPorClave(idFuncion);

        if(funcion != null){

            if (funcion.getEstadoFuncion() != EstadoFuncion.CANCELADA){

                    /// Confirmación, por seguridad:

                    System.out.println("La información de su función es:");
                    System.out.println(funcion);

                    Scanner scanner = new Scanner(System.in);
                    String respuesta;
                    boolean escape = false;

                    /// Ahora le pedimos que confirme si quiere darla de baja.

                    do {
                        System.out.println("¿Está seguro/a que desea cancelar esta función? S/N");
                        respuesta = scanner.nextLine();
                        if (respuesta.equalsIgnoreCase("S") || respuesta.equalsIgnoreCase("N")){
                            escape = true;
                        }
                        else {
                            System.out.println("Entrada inválida.");
                        }
                    } while (!escape);

                    if (respuesta.equalsIgnoreCase("S")){
                        funcion.setEstadoFuncion(EstadoFuncion.CANCELADA);
                        System.out.println("Función cancelada.");
                        return true;
                    }
                    else{
                        System.out.println("Operación cancelada.");
                        return false;
                    }
            }
            else {
                throw new ProhibidoEstadoActualException("Error: la función ya se encuentra cancelada.");
            }
        }
        else {
            throw new ElementoNoEncontradoException();
        }

    }
    /// Sólo retorna true cuando logra la operación. Así que, cuando se desee usar este método para corroborar, simplemente
    /// crear un booleano = false. Tratar de asignarle el valor que devuelva este método a ese booleano en el try.
    /// Si lanza excepción; el flujo seguirá por el catch y en el booleano permancerá el valor anterior (false).
    /// "if(esteBoolean==false)" querrá decir que la función no se canceló, sea pq no se encontró, pq se canceló la operación
    /// o pq la función no cumplía con la condición de estar no estar ya cancelada .



    /// ----- PENDIENTE ----- ///

    // Rehabilitar función (te debería obligar siempre a seleccionar día y hora, supongo,
    // para evitar errores, así que: ¿los cases, para rehabilitar y reprogramar, podrían compartir
    // llamada al mismo metodo? Sólo que "reprogramar" puede darse en más estados de la función (en sold out y disponible) que
    // "rehabilitar", que sólo se permite cuando una sala esté dada de baja.)


    /// POR HACER:


    // Rehabilitar función:





    /// POR HACER:


    // Reprogramar función:


   /* public boolean reprogramarFuncion(int idFuncion, int idNuevoHorario) throws ColeccionInvalidaException, ColeccionVaciaException {

        /// Si se reprograma, podríamos mostrarle al admin un listado de las personas que iban a asistir
        /// a esta función (justo después de invocar este método y que retorne true, desde gestor de
        /// entradas). Misma lógica para cancelar.

        Funcion funcion = funcionesCrud.buscarEnMapPorClave(idFuncion);

        if(funcion != null){

            if (funcion.getEstadoFuncion() != EstadoFuncion.CANCELADA){

                /// Confirmación, por seguridad:

                System.out.println("La información de su función es:");
                System.out.println(funcion);

                Scanner scanner = new Scanner(System.in);
                String respuesta;
                boolean escape = false;

                /// Ahora le pedimos que confirme si quiere darla de baja.

                do {
                    System.out.println("¿Está seguro/a que desea cancelar esta función? S/N");
                    respuesta = scanner.nextLine();
                    if (respuesta.equalsIgnoreCase("S") || respuesta.equalsIgnoreCase("N")){
                        escape = true;
                    }
                    else {
                        System.out.println("Entrada inválida.");
                    }
                } while (!escape);

                if (respuesta.equalsIgnoreCase("S")){
                    funcion.setEstadoFuncion(EstadoFuncion.CANCELADA);
                    System.out.println("Función cancelada.");
                    return true;
                }
                else{
                    System.out.println("Operación cancelada.");
                    return false;
                }
            }
            else {
                throw new ProhibidoEstadoActualException("Error: la función ya se encuentra cancelada.");
            }
        }
        else {
            throw new ElementoNoEncontradoException();
        }

    }*/


    // Marcar función como Sold-Out (Solamente pueden pasar a este estado las que estén "disponibles" o "reprogramadas"):
    /// Se llamará automáticamente desde Entrada o Factura, cuando se haga la reserva para la sala
    /// Y SE VERIFIQUE que a la misma ya no le quedan más butacas liberadas.


    public boolean marcarSoldOut(int idFuncion) throws ColeccionInvalidaException, ColeccionVaciaException, ElementoNoEncontradoException, ProhibidoEstadoActualException {

        Funcion funcion = funcionesCrud.buscarEnMapPorClave(idFuncion);

        if(funcion != null){

            if (funcion.getEstadoFuncion() != EstadoFuncion.SOLD_OUT){

                if (funcion.getEstadoFuncion() != EstadoFuncion.CANCELADA){

                    /// Confirmación, por seguridad:

                    System.out.println("La información de su función es:");
                    System.out.println(funcion);

                    Scanner scanner = new Scanner(System.in);
                    String respuesta;
                    boolean escape = false;

                    /// Ahora le pedimos que confirme si quiere darla de baja.

                    do {
                        System.out.println("¿Está seguro/a que desea marcarla con entradas agotadas? S/N");
                        respuesta = scanner.nextLine();
                        if (respuesta.equalsIgnoreCase("S") || respuesta.equalsIgnoreCase("N")){
                            escape = true;
                        }
                        else {
                            System.out.println("Entrada inválida.");
                        }
                    } while (!escape);

                    if (respuesta.equalsIgnoreCase("S")){
                        funcion.setEstadoFuncion(EstadoFuncion.SOLD_OUT);
                        System.out.println("Función marcada como sold out.");
                        return true;
                    }
                    else{
                        System.out.println("Operación cancelada.");
                        return false;
                    }

                }
                else{
                    throw new ProhibidoEstadoActualException("Error: no se puede marcar con entradas agotadas una función cancelada.");
                }
            }
            else {
                throw new ProhibidoEstadoActualException("Error: la función ya se encuentra marcada con entradas agotadas.");
            }
        }
        else {
            throw new ElementoNoEncontradoException();
        }

    }



    /// Filtros:

    public List<Funcion> filtrarFuncionesPorEstado(EstadoFuncion estadoFuncion) throws ColeccionInvalidaException, ColeccionVaciaException {

        List<Funcion> funcionesCoincidentes = funcionesCrud.buscarPorAtributoEnValores(funcion -> funcion.getEstadoFuncion() == estadoFuncion);

        return funcionesCoincidentes;

    }

    ///
    ///
    ///
    /// --------- ¡ FALTARÍA FILTRAR FUNCIONES POR DIA ! --------- ///
    ///
    /// public void listarHorarios(Scanner entrada){
    ///         System.out.println("Seleccione el dia a mostrar:");
    ///         DiasSemana dia = new GestorEnums<>(DiasSemana.class).seleccionarElemento(entrada);
    ///         int contador = 0;
    ///         for (Horario horario : horariosSemana.get(dia)){
    ///             ++contador;
    ///             System.out.println("Funcion " + contador + ": " + horario);
    ///         }
    ///     }
    ///
    ///
    ///

    public List<Funcion> filtrarFuncionesPorSala(int idSala) throws ColeccionInvalidaException, ColeccionVaciaException {

        List<Funcion> funcionesCoincidentes = funcionesCrud.buscarPorAtributoEnValores(funcion -> funcion.getSala().getIdSala() == idSala);

        return funcionesCoincidentes;

    }

    public List<Funcion> filtrarFuncionesPorPelicula(int idPelicula) throws ColeccionInvalidaException, ColeccionVaciaException {

        List<Funcion> funcionesCoincidentes = funcionesCrud.buscarPorAtributoEnValores(funcion -> funcion.getPelicula().getIdPelicula() == idPelicula);

        return funcionesCoincidentes;

    }

    public List<Funcion> filtrarFuncionesPorCapacidad(int capacidad) throws ColeccionInvalidaException, ColeccionVaciaException {

        List<Funcion> funcionessCoincidentes = funcionesCrud.buscarPorAtributoEnValores(funcion -> funcion.getSala().getTotalButacas() >= capacidad);

        return funcionessCoincidentes;

    }



    /// Búsquedas:


    // Retornar y mostrar 1 función, buscado por idPelicula:

    public Funcion retornarFuncionPorId(int id) throws ColeccionInvalidaException, ColeccionVaciaException, ElementoNoEncontradoException {

        Funcion funcion = funcionesCrud.buscarEnMapPorClave(id);

        if (funcion != null){
            return funcion;
        }
        else {
            throw new ElementoNoEncontradoException();
        }

    }

    public void mostrarFuncionPorId(int id) throws ColeccionInvalidaException, ColeccionVaciaException, ElementoNoEncontradoException {

        Funcion funcion = funcionesCrud.buscarEnMapPorClave(id);

        if (funcion != null){
            System.out.println(funcion);
        }
        else{
            throw new ElementoNoEncontradoException();
        }

    }


    // Retornar y listar colección de funciones disponibles:

    public List<Funcion> retornarDisponibles() throws ColeccionInvalidaException, ColeccionVaciaException, ElementoNoEncontradoException {

        List<Funcion> disponibles = filtrarFuncionesPorEstado(EstadoFuncion.DISPONIBLE);

        if (disponibles.isEmpty()){
            throw new ElementoNoEncontradoException();
        }

        return disponibles;
    }

    public void listarDisponibles() throws ColeccionInvalidaException, ColeccionVaciaException, ElementoNoEncontradoException {

        List<Funcion> disponibles = retornarDisponibles();

        for (Funcion funcion : disponibles){
            System.out.println(funcion);
        }

    }


    // Retornar y listar colección de funciones dadas de baja:

    public List<Funcion> retornarCanceladas() throws ColeccionInvalidaException, ColeccionVaciaException, ElementoNoEncontradoException {

        List<Funcion> canceladas = filtrarFuncionesPorEstado(EstadoFuncion.CANCELADA);

        if (canceladas.isEmpty()){
            throw new ElementoNoEncontradoException();
        }

        return canceladas;
    }

    public void listarCanceladas() throws ColeccionInvalidaException, ColeccionVaciaException, ElementoNoEncontradoException {

        List<Funcion> canceladas = retornarCanceladas();

        for (Funcion funcion : canceladas){
            System.out.println(funcion);
        }

    }


    // Retornar y listar colección de funciones Sold-Out:

    public List<Funcion> retornarSoldOut() throws ColeccionInvalidaException, ColeccionVaciaException, ElementoNoEncontradoException {

        List<Funcion> agotadas = filtrarFuncionesPorEstado(EstadoFuncion.SOLD_OUT);

        if (agotadas.isEmpty()){
            throw new ElementoNoEncontradoException();
        }

        return agotadas;
    }

    public void listarSoldOut() throws ColeccionInvalidaException, ColeccionVaciaException, ElementoNoEncontradoException {

        List<Funcion> agotadas = retornarSoldOut();

        for (Funcion funcion : agotadas){
            System.out.println(funcion);
        }

    }


    // Retornar y listar colección de funciones reprogramadas:

    public List<Funcion> retornarReprogramadas() throws ColeccionInvalidaException, ColeccionVaciaException, ElementoNoEncontradoException {

        List<Funcion> reprogramadas = filtrarFuncionesPorEstado(EstadoFuncion.REPROGRAMADA);

        if (reprogramadas.isEmpty()){
            throw new ElementoNoEncontradoException();
        }

        return reprogramadas;
    }

    public void listarReprogramadas() throws ColeccionInvalidaException, ColeccionVaciaException, ElementoNoEncontradoException {

        List<Funcion> reprogramadas = retornarReprogramadas();

        for (Funcion funcion : reprogramadas){
            System.out.println(funcion);
        }

    }


    /// --- POR HACER --- ///


    // Retornar y listar todas las funciones de determinado día:





    // Retornar y listar todas las funciones de determinada sala:

    public List<Funcion> retornarPorSala(int idSala) throws ColeccionInvalidaException, ColeccionVaciaException, ElementoNoEncontradoException {

        List<Funcion> coincidentes = filtrarFuncionesPorSala(idSala);

        if (coincidentes.isEmpty()){
            throw new ElementoNoEncontradoException();
        }

        return coincidentes;
    }

    public void listarPorSala(int idSala) throws ColeccionInvalidaException, ColeccionVaciaException, ElementoNoEncontradoException {

        List<Funcion> coincidentes = retornarPorSala(idSala);

        for (Funcion funcion : coincidentes){
            System.out.println(funcion);
        }

    }


    // Retornar y listar todas las butacas correspondientes a determinada función:

    public List<Butaca> retornarButacasPorFuncion(int idFuncion){

        Funcion funcion = null;
        List<Butaca> butacas = new ArrayList<>();

        try{
            funcion = retornarFuncionPorId(idFuncion);
            butacas = funcion.getLugaresDisponibles().retornarButacasValues();
        } catch (ColeccionInvalidaException e) {
            System.out.println(e.getMessage());
        } catch (ColeccionVaciaException e) {
            System.out.println(e.getMessage());
        } catch (ElementoNoEncontradoException e) {
            System.out.println(e.getMessage());
        }

        return butacas; //Verificar: si método retorna colección vacía es que no se encontró.
    }

    public void imprimirButacasPorFuncion(int idFuncion) throws ColeccionVaciaException {
        List<Butaca> butacas = retornarButacasPorFuncion(idFuncion);
        if (butacas.isEmpty()) {
            throw new ColeccionVaciaException();
        } else {
            System.out.println("Butacas de la función con ID " + idFuncion + ":");
            for (Butaca butaca : butacas){
                System.out.println(butaca);
            }
        }
    }


    // Retornar y mostrar 1 butaca dentro de todas las funciones, buscada por idPelicula:


    // Retornar y mostrar 1 butaca dentro de una funcion, buscada por numero de butaca e idPelicula de funcion:


    // Retornar y mostrar todas las butacas liberadas de una función:

    public List<Butaca> retornarButacasLiberadasPorFuncion(int idFuncion) throws ElementoNoEncontradoException {

        List<Butaca> butacas = retornarButacasPorFuncion(idFuncion);
        List<Butaca> liberadas = new ArrayList<>();

        for(Butaca butaca : butacas){
            liberadas.add(butaca);
        }

        if (liberadas.isEmpty()){
            throw new ElementoNoEncontradoException();
        }

        return butacas; //Verificar: si método retorna colección vacía es que no se encontró.
    }




    // Retornar y mostrar todas las butacas ocupadas de una función:


    // Retornar y mostrar todas las películas pertenecientes a determinado día:


    // Retornar y mostrar todas las funciones, en las que se pase tal película (buscada por idPelicula o título):


    // Retornar y mostrar todas las funciones, en las que se pase una película en tal idioma:


    // Retornar y mostrar todas las funciones, en las que se pase una película apta para todo publico:


    // Retornar y mostrar todas las funciones, en las que se pase una película de determinado género:


    // Retornar y mostrar todas las funciones, en las que su sala de cierto tipo (3D, 2D, Atmos):


    // Retornar y listar una colección de TODAS las funciones en el sistema:


}
