package Gestores.Cine;

import Enumeraciones.EstadoSala;
import Enumeraciones.TipoColeccion;
import Enumeraciones.TipoSala;
import Excepciones.KevPF98.Flexibles.*;
import Modelos.Cine.Sala;
import Modelos.General.Crud;

import java.util.List;
import java.util.Scanner;

public class GestorSalas {

    // Atributos:

    private Crud<Integer, Sala> salasCrud;
    /// TreeMap:
    /// Ordenamiento por Id (ya que número de sala puede no ser único,
    /// debido a las bajas y altas).

    // Constructor:

    public GestorSalas() throws ColeccionInvalidaException {
        salasCrud = new Crud<>(TipoColeccion.TREEMAP);
    }

    // Inserción:

    /// ANTES DE LLAMAR A ESTE MÉTODO: validar que el número de sala no se encuentre entre las habilitadas de
    /// sala.
    public void agregarSala(Sala sala) throws ElementoEnColeccionException {
        salasCrud.agregar(sala.getIdSala(), sala, false);
    }

    // Eliminación total de los datos de una sala en el sistema:

    public boolean eliminarPermanentemente(int idSala) throws ColeccionInvalidaException, ColeccionVaciaException, ElementoNoEncontradoException, ProhibidoEstadoActualException {

        Sala sala = salasCrud.buscarEnMapPorClave(idSala);

        if(sala != null){

            /// Como medida de seguridad: no se podrán eliminar los datos de una sala, sin que antes
            /// esté "dada de baja".

            if (sala.getEstado() == EstadoSala.BAJA){

                /// Confirmación, por seguridad: ///

                System.out.println("La información de su sala es:");
                System.out.println(sala);

                Scanner scanner = new Scanner(System.in);
                String respuesta;
                boolean escape = false;

                /// Ahora le pedimos que confirme si quiere darla de baja. ///

                /*String mensaje = "ALERTA: esta operación borrará permanentemente los datos de la sala seleccionada.\n¿Está seguro/a que desea continuar?";

                if (salasCrud.pedirConfirmacion(mensaje)){
                    salasCrud.eliminarEnMap(idSala);
                    System.out.println("Datos de la sala eliminados exitosamente del sistema.");
                }
                else{
                    System.out.println("Operación cancelada.");
                }*/

                do {
                    System.out.println("ALERTA: esta operación borrará permanentemente los datos de la sala seleccionada.");
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
                    salasCrud.eliminarEnMap(idSala);
                    System.out.println("Datos de la sala eliminados exitosamente del sistema.");
                    return true;
                }
                else{
                    System.out.println("Operación cancelada.");
                    return false;
                }

            }
            else {
                throw new ProhibidoEstadoActualException("Para eliminar permanentemente los datos de una sala, primero debe estar dada de baja.");
            }

        }
        else{
            throw new ElementoNoEncontradoException();
        }

    }
    /// Sólo retorna true cuando logra la operación. Así que, cuando se desee usar este método para corroborar, simplemente
    /// crear un booleano = false. Tratar de asignarle el valor que devuelva este método a ese booleano en el try.
    /// Si lanza excepción; el flujo seguirá por el catch y en el booleano permancerá el valor anterior (false).
    /// "if(esteBoolean==false)" querrá decir que la sala no se eliminó, sea pq no se encontró, pq se canceló la operación
    /// o pq la sala no cumplía con la condición de estar de baja antes.


    // Modificar estado de sala:

    // Dar de baja sala (inhabilitar, sin perder los datos del sistema):

    public boolean darDeBajaSala(int idSala) throws ColeccionInvalidaException, ColeccionVaciaException, ElementoNoEncontradoException, ProhibidoEstadoActualException {

        Sala sala = salasCrud.buscarEnMapPorClave(idSala);

        if(sala != null){

            if (sala.getEstado() != EstadoSala.BAJA){

                /// /// Problema: si no verificara que en la sala no haya gente o no esté siendo utilizada
                /// /// en alguna función (ocupada), ¿cómo puedo darla de baja de forma segura?

                if (sala.getEstado() != EstadoSala.OCUPADA){

                    /// Confirmación, por seguridad:

                    System.out.println("Los datos de la sala seleccionada son:");
                    System.out.println(sala);

                    Scanner scanner = new Scanner(System.in);
                    String respuesta;
                    boolean escape = false;

                    /// Ahora le pedimos que confirme si quiere darla de baja.

                    do {
                        System.out.println("¿Está seguro/a que desea dar de baja esta sala? S/N");
                        respuesta = scanner.nextLine();
                        if (respuesta.equalsIgnoreCase("S") || respuesta.equalsIgnoreCase("N")){
                            escape = true;
                        }
                        else {
                            System.out.println("Entrada inválida.");
                        }
                    } while (!escape);

                    if (respuesta.equalsIgnoreCase("S")){
                        sala.setEstado(EstadoSala.BAJA);
                        System.out.println("Sala dada de baja.");
                        return true;
                    }
                    else{
                        System.out.println("Operación cancelada.");
                        return false;
                    }

                }
                else{
                    throw new ProhibidoEstadoActualException("Error: no se puede dar de baja una sala que está siendo ocupada.");
                }
            }
            else {
                throw new ProhibidoEstadoActualException("Error: la sala ya se encuentra dada de baja.");
            }
        }
        else {
            throw new ElementoNoEncontradoException();
        }

        /// Sólo retorna true cuando logra la operación. Así que, cuando se desee usar este método para corroborar, simplemente
        /// crear un booleano = false. Tratar de asignarle el valor que devuelva este método a ese booleano en el try.
        /// Si lanza excepción; el flujo seguirá por el catch y en el booleano permancerá el valor anterior (false).
        /// "if(esteBoolean==false)" querrá decir que la sala no se dio de baja, sea pq no se encontró, pq se canceló la operación
        /// o pq la sala no cumplía con la condición de no estar ocupada antes.

    }

    // Rehabilitar sala:

    public boolean darDeAlta(int idSala) throws ColeccionInvalidaException, ColeccionVaciaException, ElementoNoEncontradoException, ProhibidoEstadoActualException {

        // Verificar acá que la sala que se desea restablecer no tenga un número de sala que coincida con
        // alguna de las salas que están actualmente de alta.

        /// MEJOR NO. Directamente que se verifique esto sólo antes de llamar al constructor para una nueva sala (¿o función?), así nunca se agregarán con números repetidos:
        /// (inicializamos una variable "salaAuxiliar" apuntando a null, y otra "salaAReservar" con los datos que queremos, fuera del try.
        /// Dentro del, try le asignamos a "salaAuxiliar" el valor que retorne "buscarNumeroEntreHabilitadas" de este mismo gestor.
        /// Si se pasó el try, la sala fue encontrada, el número no está disponible y la instancia de salaAuxiliar tendrá un valor,
        /// si no, se saltará al catch con el mensaje de que no se pudo encontrar. Luego, verificamos dentro de un
        /// "if(salaAuxiliar == null)", lo que significaría que la sala no se encontró, por ende; ninguna sala tiene
        /// ya este número asignado, así que hacemos el constructor de Funcion con "salaAReservar" de parámetro)

        Sala sala = salasCrud.buscarEnMapPorClave(idSala);

        if(sala != null){

            if(sala.getEstado() == EstadoSala.BAJA || sala.getEstado() == EstadoSala.REPARACION){

                /// Confirmación:

                System.out.println("La información de su sala es:");
                System.out.println(sala);

                Scanner scanner = new Scanner(System.in);
                String respuesta;
                boolean escape = false;

                /// Ahora le pedimos que confirme si quiere darla de alta.

                do {
                    System.out.println("¿Está seguro/a que desea rehabilitar esta sala? S/N");
                    respuesta = scanner.nextLine();
                    if (respuesta.equalsIgnoreCase("S") || respuesta.equalsIgnoreCase("N")){
                        escape = true;
                    }
                    else {
                        System.out.println("Entrada inválida.");
                    }
                } while (!escape);

                if (respuesta.equalsIgnoreCase("S")){
                    sala.setEstado(EstadoSala.DISPONIBLE);
                    System.out.println("Esta sala vuelve a estar disponible.");
                    return true;
                }
                else{
                    System.out.println("Operación cancelada.");
                    return false;
                }

            }
            else {
                throw new ProhibidoEstadoActualException("Error: la sala ya se encuentra habilitada.");
            }
        }
        else {
            throw new ElementoNoEncontradoException();
        }
    }
    /// Sólo retorna true cuando logra la operación. Así que, cuando se desee usar este método para corroborar, simplemente
    /// crear un booleano = false. Tratar de asignarle el valor que devuelva este método a ese booleano en el try.
    /// Si lanza excepción; el flujo seguirá por el catch y en el booleano permancerá el valor anterior (false).
    /// "if(esteBoolean==false)" querrá decir que la sala no se volvió a dar de alta, sea pq no se encontró, pq se canceló la operación
    /// o pq la sala no cumplía con la condición de estar de baja o en reparación antes.

    // Modificar a estado en reparación:

    public boolean marcarSalaEnReparacion(int idSala) throws ColeccionInvalidaException, ColeccionVaciaException, ElementoNoEncontradoException, ProhibidoEstadoActualException {

        Sala sala = salasCrud.buscarEnMapPorClave(idSala);

        if(sala != null){

            if (sala.getEstado() != EstadoSala.REPARACION){

                if (sala.getEstado() != EstadoSala.OCUPADA){

                    System.out.println("La información de su sala es:");
                    System.out.println(sala);

                    Scanner scanner = new Scanner(System.in);
                    String respuesta;
                    boolean escape = false;

                    do {
                        System.out.println("¿Está seguro/a que desea marcar esta sala como en estado de reparación? S/N");
                        respuesta = scanner.nextLine();
                        if (respuesta.equalsIgnoreCase("S") || respuesta.equalsIgnoreCase("N")){
                            escape = true;
                        }
                        else {
                            System.out.println("Entrada inválida.");
                        }
                    } while (!escape);

                    if (respuesta.equalsIgnoreCase("S")){
                        sala.setEstado(EstadoSala.REPARACION);
                        System.out.println("La sala fue marcada como en estado de reparación.");
                        return true;
                    }
                    else{
                        System.out.println("Operación cancelada.");
                        return false;
                    }

                }
                else {
                    throw new ProhibidoEstadoActualException("Error: no se puede marcar como en reparación una sala ocupada.");
                }
            }
            else {
                throw new ProhibidoEstadoActualException("Error: la sala ya se encuentra en estado de reparación.");
            }
        }
        else{
            throw new ElementoNoEncontradoException();
        }
    }
    /// Sólo retorna true cuando logra la operación. Así que, cuando se desee usar este método para corroborar, simplemente
    /// crear un booleano = false. Tratar de asignarle el valor que devuelva este método a ese booleano en el try.
    /// Si lanza excepción; el flujo seguirá por el catch y en el booleano permancerá el valor anterior (false).
    /// "if(esteBoolean==false)" querrá decir que la sala no se marcó como en reparación, sea pq no se encontró, pq se canceló la operación
    /// o pq la sala no cumplía con la condición de estar de no estar ya en reparación o de no estar ocupada.


    //// CREAR OCUPAR SALA: se llama autom. cuando se use para una Función. Que no se pueda ocupar sala si está: en
    //// reparación, de baja o ya ocupada.


    public boolean ocuparSala(int idSala) throws ColeccionInvalidaException, ColeccionVaciaException, ElementoNoEncontradoException, ProhibidoEstadoActualException {

        /// Se verifica por horario, en función.

        Sala sala = salasCrud.buscarEnMapPorClave(idSala);

        if(sala != null){

            if(sala.getEstado() == EstadoSala.DISPONIBLE){

                /// Confirmación:

                System.out.println("La información de su sala es:");
                System.out.println(sala);

                Scanner scanner = new Scanner(System.in);
                String respuesta;
                boolean escape = false;

                /// Ahora le pedimos que confirme si quiere reservarla para una funcion.

                do {
                    System.out.println("¿Está seguro/a que desea ocupar esta sala? S/N");
                    respuesta = scanner.nextLine();
                    if (respuesta.equalsIgnoreCase("S") || respuesta.equalsIgnoreCase("N")){
                        escape = true;
                    }
                    else {
                        System.out.println("Entrada inválida.");
                    }
                } while (!escape);

                if (respuesta.equalsIgnoreCase("S")){
                    sala.setEstado(EstadoSala.OCUPADA);
                    System.out.println("La sala ha sido reservada exitosamente.");
                    return true;
                }
                else{
                    System.out.println("Operación cancelada.");
                    return false;
                }

            } else if (sala.getEstado() == EstadoSala.OCUPADA) {
                throw new ProhibidoEstadoActualException("Error: la sala ya se encuentra ocupada.");
            } else if (sala.getEstado() == EstadoSala.REPARACION) {
                throw new ProhibidoEstadoActualException("Error: no se puede ocupar una sala en reparación.");
            } else  { // Baja es la única que queda.
                throw new ProhibidoEstadoActualException("Error: no se puede ocupar una sala que está dada de baja.");
            }
        }
        else {
            throw new ElementoNoEncontradoException();
        }
    }
    /// Sólo retorna true cuando logra la operación. Así que, cuando se desee usar este método para corroborar, simplemente
    /// crear un booleano = false. Tratar de asignarle el valor que devuelva este método a ese booleano en el try.
    /// Si lanza excepción; el flujo seguirá por el catch y en el booleano permancerá el valor anterior (false).
    /// "if(esteBoolean==false)" querrá decir que la sala no se ocupó, sea pq no se encontró, pq se canceló la operación
    /// o pq la sala no cumplía con la condición de estar de no estar disponible ni ya ocupada.


    // Filtros:

    public List<Sala> filtrarSalasPorEstado(EstadoSala estadoSala) throws ColeccionInvalidaException, ColeccionVaciaException {

        List<Sala> salasCoincidentes = salasCrud.buscarPorAtributoEnValores(sala -> sala.getEstado() == estadoSala);

        return salasCoincidentes;

    }

    public List<Sala> filtrarSalasPorTipo(TipoSala tipoSala) throws ColeccionInvalidaException, ColeccionVaciaException {

        List<Sala> salasCoincidentes = salasCrud.buscarPorAtributoEnValores(sala -> sala.getTipo() == tipoSala);

        return salasCoincidentes;

    }

    public List<Sala> filtrarSalasPorCapacidad(int capacidad) throws ColeccionInvalidaException, ColeccionVaciaException {

        List<Sala> salasCoincidentes = salasCrud.buscarPorAtributoEnValores(sala -> sala.getTotalButacas() >= capacidad);

        return salasCoincidentes;

    }


    // Métodos de listar/mostrar::

    // Buscar por id y mostrar 1 elemento entre todas las salas:

    public Sala retornarSalaPorId(int id) throws ColeccionInvalidaException, ColeccionVaciaException, ElementoNoEncontradoException {

        Sala sala = salasCrud.buscarEnMapPorClave(id);

        if (sala != null){
            return sala;
        }
        else {
            throw new ElementoNoEncontradoException();
        }

    }


    public void mostrarSalaPorId(int id) throws ColeccionInvalidaException, ColeccionVaciaException, ElementoNoEncontradoException {

        Sala sala = salasCrud.buscarEnMapPorClave(id);

        if (sala != null){
            System.out.println(sala);
        }
        else{
            throw new ElementoNoEncontradoException();
        }

    }

    // Buscar, obtener y mostrar lista, entre salas habilitadas:

    public List<Sala> retornarHabilitadas() throws ColeccionInvalidaException, ColeccionVaciaException, ElementoNoEncontradoException {

        List<Sala> habilitadas = filtrarSalasPorEstado(EstadoSala.DISPONIBLE);

        if (habilitadas.isEmpty()){
            throw new ElementoNoEncontradoException();
        }

        return habilitadas;
    }

    public void listarHabilitadas() throws ColeccionInvalidaException, ColeccionVaciaException, ElementoNoEncontradoException {

        List<Sala> habilitadas = retornarHabilitadas();

        for (Sala sala : habilitadas){
            System.out.println(sala);
        }

    }

    // Buscar, obtener y mostrar lista, entre salas dadas de baja:

    public List<Sala> retornarBajas() throws ColeccionInvalidaException, ColeccionVaciaException, ElementoNoEncontradoException {

        List<Sala> bajas = filtrarSalasPorEstado(EstadoSala.BAJA);

        if (bajas.isEmpty()){
            throw new ElementoNoEncontradoException();
        }

        return bajas;
    }

    public void listarBajas() throws ColeccionInvalidaException, ColeccionVaciaException, ElementoNoEncontradoException {

        List<Sala> bajas = retornarBajas();

        for (Sala sala : bajas){
            System.out.println(sala);
        }

    }

    // Buscar y obtener lista, entre salas en reparación:

    public List<Sala> retornarEnReparacion() throws ColeccionInvalidaException, ColeccionVaciaException, ElementoNoEncontradoException {

        List<Sala> enReparacion = filtrarSalasPorEstado(EstadoSala.REPARACION);

        if (enReparacion.isEmpty()){
            throw new ElementoNoEncontradoException();
        }

        return enReparacion;
    }

    public void listarEnReparacion() throws ColeccionInvalidaException, ColeccionVaciaException, ElementoNoEncontradoException {

        List<Sala> enReparacion = retornarEnReparacion();

        for (Sala sala : enReparacion){
            System.out.println(sala);
        }

    }

    // Buscar y obtener info por 1 número de sala, entre las salas habilitadas:

    public Sala buscarNumeroEntreHabilitadas(int numSala) throws ColeccionInvalidaException, ColeccionVaciaException, ElementoNoEncontradoException {

        List<Sala> habilitadas = retornarHabilitadas();

        for (Sala sala : habilitadas){
            if(sala.getNumeroSala() == numSala){
                return sala;
            }
        }

        throw new ElementoNoEncontradoException();

    }

    // Buscar y obtener lista de salas por tipo:

    // Atmos:

    public List<Sala> retornarAtmos() throws ColeccionInvalidaException, ColeccionVaciaException, ElementoNoEncontradoException {

        List<Sala> Atmos = filtrarSalasPorTipo(TipoSala.ATMOS);

        if (Atmos.isEmpty()){
            throw new ElementoNoEncontradoException();
        }

        return Atmos;
    }

    public void listarAtmos() throws ColeccionInvalidaException, ColeccionVaciaException, ElementoNoEncontradoException {

        List<Sala> Atmos = retornarAtmos();

        for (Sala sala : Atmos){
            System.out.println(sala);
        }

    }

    // 2D:

    public List<Sala> retornar2D() throws ColeccionInvalidaException, ColeccionVaciaException, ElementoNoEncontradoException {

        List<Sala> DosD = filtrarSalasPorTipo(TipoSala.DOS_D);

        if (DosD.isEmpty()){
            throw new ElementoNoEncontradoException();
        }

        return DosD;
    }

    public void listarDosD() throws ColeccionInvalidaException, ColeccionVaciaException, ElementoNoEncontradoException {

        List<Sala> DosD = retornar2D();

        for (Sala sala : DosD){
            System.out.println(sala);
        }

    }

    // 3D:

    public List<Sala> retornar3D() throws ColeccionInvalidaException, ColeccionVaciaException, ElementoNoEncontradoException {

        List<Sala> tresD = filtrarSalasPorTipo(TipoSala.TRES_D);

        if (tresD.isEmpty()){
            throw new ElementoNoEncontradoException();
        }

        return tresD;
    }

    public void listar3D() throws ColeccionInvalidaException, ColeccionVaciaException, ElementoNoEncontradoException {

        List<Sala> tresDs = retornar3D();

        for (Sala sala : tresDs){
            System.out.println(sala);
        }

    }

    // Buscar, obtener y listar salas por capacidad:

    public List<Sala> retornarSalasCapacidad(int capacidad) throws ColeccionInvalidaException, ColeccionVaciaException, ElementoNoEncontradoException {

        List<Sala> salasConCapacidad = filtrarSalasPorCapacidad(capacidad);

        if (salasConCapacidad.isEmpty()){
            throw new ElementoNoEncontradoException();
        }

        return salasConCapacidad;
    }

    public void listarPorCapacidad(int capacidad) throws ColeccionInvalidaException, ColeccionVaciaException, ElementoNoEncontradoException {

        List<Sala> salasConCapacidad = retornarSalasCapacidad(capacidad);

        for (Sala sala : salasConCapacidad){
            System.out.println(sala);
        }

    }

    // Listar todas las salas:

    public void listarSalas() throws ColeccionVaciaException {
        if (!salasCrud.getMap().isEmpty()){
            for (Sala sala : salasCrud.getMap().values()){
                System.out.println(sala);
            }
        }
        else {
            throw new ColeccionVaciaException();
        }
    }

}
