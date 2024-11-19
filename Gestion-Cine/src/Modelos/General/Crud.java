package Modelos.General;

import Enumeraciones.CollectionOrMap;
import Enumeraciones.TipoColeccion;
import Excepciones.KevPF98.Flexibles.ColeccionInvalidaException;
import Excepciones.KevPF98.Flexibles.ColeccionVaciaException;
import Excepciones.KevPF98.Flexibles.ElementoEnColeccionException;
import Excepciones.KevPF98.Flexibles.ElementoNoEncontradoException;
import java.util.*;
import java.util.function.Predicate;


public class Crud <K, T>{
    //Si no trabajan con par clave-valor le pasan Void de primer parametro, pq clave:vacia.
    //Ej: Crud<Void, Persona> crudGestorPersonas;
    //En este caso, cuando llamen a agregar sólo necesitan pasarle un atributo (el objeto a agregar
    //en la lista, sin claves).

    //La clase recibe "K" para las claves de maps.
    //Y "T" para las collections y los valores de maps.

    //Atributos:

    private Collection<T> collection;
    private Map<K, T> map;
    private CollectionOrMap implementa;//Para verificar si se creó
                                       //una colección de clave-valor
                                       //o una de un solo valor en el
                                       //constructor. Ya que el comportamiento
                                       //varía entre un tipo y otro.

    //Constructor (recibe un enum de los tipos de colecciones vistas):

    public Crud(TipoColeccion eleccionColeccion) throws ColeccionInvalidaException{ //manejar excepcionmes
                                                                                    //cuando se llame
                                                                                    //o propagarlas
                                                                                    //al menu princ.
        switch (eleccionColeccion){
            case ARRAYLIST:
            case LINKEDLIST:
            case VECTOR:
            case HASHSET:
            case LINKEDHASHSET:
            case TREESET:
                collection = crearCollection(eleccionColeccion);
                implementa = CollectionOrMap.COLLECTION;
                break;
            case HASHMAP:
            case LINKEDHASHMAP:
            case TREEMAP:
                map = crearMap(eleccionColeccion);
                implementa = CollectionOrMap.MAP;
                break;
            default: //Sé que no es requerido por trabajar con enums... pero da mayor flexibilidad para
                     //posibles cambios futuros.
                     throw new ColeccionInvalidaException();
        }
    }

    //Métodos:

    //Distinción para el constructor:

    // -------------------------------------------------------------------------- //

    public Collection<T> crearCollection(TipoColeccion tipo) throws ColeccionInvalidaException {
        return switch (tipo) {
            case ARRAYLIST -> new ArrayList<>();
            //Equivalente a:
            //case ARRAYLIST: return new ArrayList<>();
            case LINKEDLIST -> new LinkedList<>();
            case VECTOR -> new Vector<>();
            case HASHSET -> new HashSet<>();
            case LINKEDHASHSET -> new LinkedHashSet<>();
            case TREESET -> new TreeSet<>();
            default -> throw new ColeccionInvalidaException();
        };
    }

    public Map<K, T> crearMap(TipoColeccion tipo) throws ColeccionInvalidaException {
        return switch (tipo) {
            case HASHMAP -> new HashMap<>();
            case LINKEDHASHMAP -> new LinkedHashMap<>();
            case TREEMAP -> new TreeMap<>();
            default -> throw new ColeccionInvalidaException();
        };
    }

    /// -------------------------------------------------------------------------- ///

    ///Método de inserción:

    /// -------------------------------------------------------------------------- ///

    public void agregar(K key /*solo si es map*/, T objeto, boolean permitirDuplicados/*, boolean mostrarMensajeExito*/ /*En el caso de agregar 100 butacas automaticamente, no me interesa ver mensaje de exito 100 veces.*/) throws ElementoEnColeccionException {
        if(implementa == CollectionOrMap.COLLECTION){
            if(permitirDuplicados || !collection.contains(objeto)){
                collection.add(objeto);
                System.out.println("Elemento agregado con éxito.");
            }
            else{
                throw new ElementoEnColeccionException(CollectionOrMap.COLLECTION);
            }
        }
        else if(implementa == CollectionOrMap.MAP){
            if (!map.containsKey(key)){
                map.put(key, objeto);
                /*if (mostrarMensajeExito){*/
                    System.out.println("Elemento agregado con éxito.");
                //}
            }else{
                throw new ElementoEnColeccionException(CollectionOrMap.MAP);
            }
        }
    }

    ///Sobrecarga (más sencillo para trabajar con collections de 1 solo valor):

    public void agregar(T objeto, boolean permitirDuplicados/*, boolean mostrarMensajeExito*/) throws ElementoEnColeccionException {
        agregar(null, objeto, permitirDuplicados/*, mostrarMensajeExito*/);
    }

    ///Para agregar muchos elementos iterando, maps (lo comenté pq agregaba + complejidad en 1 detalle):

    /*
    public void agregarSinMensajeExito(K key, T objeto, boolean permitirDuplicados) throws ElementoEnColeccionException {
        agregar(key, objeto, permitirDuplicados, false);
    }*/

    ///Para agregar muchos elementos iterando, collections:

    /*
    public void agregarSinMensajeExito(T objeto, boolean permitirDuplicados) throws ElementoEnColeccionException{
        agregar(objeto, permitirDuplicados, false);
    }*/

    ///Para agregar 1 elemento, maps:

    /*
    public void agregarConMensajeExito(K key, T objeto, boolean permitirDuplicados) throws ElementoEnColeccionException {
        agregar(key, objeto, permitirDuplicados, true);
    }*/

    ///Para agregar 1 elemento, collections:

    /*
    public void agregarConMensajeExito(T objeto, boolean permitirDuplicados) throws ElementoEnColeccionException{
        agregar(objeto, permitirDuplicados, true);
    }
    */




    // -------------------------------------------------------------------------- //

    //Métodos de búsqueda:

    // -------------------------------------------------------------------------- //

    //Recorrer una collection y esperar de retorno 1 elemento:

    public T buscarUnicoElementoCollection(Predicate<T> condicion) throws ColeccionVaciaException, ColeccionInvalidaException {
        if(implementa == CollectionOrMap.COLLECTION){
            if (!collection.isEmpty()){
                for(T elemento : collection){
                    if(condicion.test(elemento)){
                        return elemento;
                    }
                }
            }
            else {
                throw new ColeccionVaciaException();
            }
        }
        else{
            throw new ColeccionInvalidaException(CollectionOrMap.MAP);
        }

        return null; //No se encontró. Validar tras la invocación del método.
    }

    //Recorrer una collection y esperar de retorno 1 o más elementos coincidentes:

    public List<T> buscarConjuntoCollection(Predicate<T> condicion) throws ColeccionVaciaException, ColeccionInvalidaException{

        List<T> resultados = new ArrayList<>();

        if(implementa == CollectionOrMap.COLLECTION){
            if (!collection.isEmpty()){
                for(T elemento : collection){
                    if(condicion.test(elemento)){
                        resultados.add(elemento);
                    }
                }
            }
            else {
                throw new ColeccionVaciaException();
            }
        }
        else{
            throw new ColeccionInvalidaException(CollectionOrMap.MAP);
        }

        return resultados; //cuando llamen a este método, controlen si la colección no quedó vacía
        // (lo que indicaría que no se encontró ningún elemento).
    }

    //Recorrer un map y esperar de retorno el único elemento correspondiente con la clave:

    public T buscarEnMapPorClave(K key) throws ColeccionVaciaException, ColeccionInvalidaException{
        if (implementa == CollectionOrMap.MAP){
            if(!map.isEmpty()){
                return map.get(key); //Null si no se encontró, validar en invocación de método.
            }
            else{
                throw new ColeccionVaciaException();
            }
        }
        else{
            throw new ColeccionInvalidaException(CollectionOrMap.COLLECTION);
        }
    }

    //Recibir un atributo de la clave (ej: atributo DniCliente, de: clave Butaca) y devolver una lista
    //de coincidencias (ej: todos los asientos reservados de X persona).

    public List<T> buscarPorAtributoEnValores(Predicate<T> condicion) throws ColeccionVaciaException, ColeccionInvalidaException{

        List<T> elementosCoincidentes = new ArrayList<>();

        if (implementa == CollectionOrMap.MAP){
            if(!map.isEmpty()){
                for (T elemento : map.values()) {
                    if (condicion.test(elemento)) {
                        elementosCoincidentes.add(elemento);
                    }
                }
            }
            else{
                throw new ColeccionVaciaException();
            }
        }
        else{
            throw new ColeccionInvalidaException();
        }

        return elementosCoincidentes; //Comprobar si devuelve empty y validar.
    }


/*
Ejemplo:

Métodos de búsqueda en peli, usando filtrado:

        private Crud<Pelicula, Integer> crudPeliculas; //Atributo Crud en peli.

        public List<Pelicula> buscarPorDirector(String director) {
            return crudPeliculas.buscarConjuntoCollection(pelicula -> pelicula.getDirector().equalsIgnoreCase(director));
        }

// Básicamente le decís buscame cada peli donde su director tenga X nombre. PDTA: No hace falta definir
una instancia "pelicula" ni nada usando lambda, se define como una variable de referencia en el
momento; similar a como hacés con un for each (Pelicula pelicula : peliculas), para referenciar al objeto dentro del for.

        public List<Pelicula> buscarPorTitulo(String titulo) {
            return crudPeliculas.filtrarConjuntoCollection(pelicula -> pelicula.getTitulo().equalsIgnoreCase(titulo));
        }

        public List<Pelicula> buscarPorFechaLanzamiento(int anio) {
            return crudPeliculas.filtrarConjuntoCollection(pelicula -> pelicula.getFechaLanzamiento().getYear() == anio);
        }

Llamada del método desde gestor general:

*El usuario quiere buscar pelis por director, escribe "Tim Burton"*

        List<Pelicula> peliculasBurton = gestorPeliculas.buscarPorDirector("Tim Burton");

        for (Pelicula pelicula : peliculasBurton) {
            System.out.println(pelicula.getTitulo());
        }

        */


    // -------------------------------------------------------------------------- //

    //Métodos de eliminación:

    // -------------------------------------------------------------------------- //

    public void eliminarEnCollection(Predicate<T> condicion) throws ColeccionVaciaException, ColeccionInvalidaException, ElementoNoEncontradoException {
        T elementoPorEliminar = buscarUnicoElementoCollection(condicion);
        if(elementoPorEliminar == null){
            throw new ElementoNoEncontradoException();
        }
        else{
            collection.remove(elementoPorEliminar);
            System.out.println("Eliminado.");
        }
    }

    public void eliminarEnMap(K key) throws ColeccionVaciaException, ColeccionInvalidaException, ElementoNoEncontradoException{
        if(!map.containsKey(key)){
            throw new ElementoNoEncontradoException();
        }
        else{
            map.remove(key);
            System.out.println("Eliminado.");
        }
    }

    // -------------------------------------------------------------------------- //

    //Métodos de mostrar:

    // -------------------------------------------------------------------------- //

    public void listarTodos() throws ColeccionVaciaException{ //RECORDAR HACER TO STRING EN CADA CLASE MODELO.
        if(implementa == CollectionOrMap.COLLECTION){
            if (!collection.isEmpty()){
                for(T elemento : collection){
                    System.out.println(elemento);
                }
            }
            else{
                throw new ColeccionVaciaException();
            }

        }

        else if(implementa == CollectionOrMap.MAP){
            if (!map.isEmpty()){
                for(Map.Entry<K, T> entry : map.entrySet()){
                    System.out.println("Clave: " + entry.getKey() + ", Valor: " + entry.getValue());
                }
            }
            else {
                throw new ColeccionVaciaException();
            }
        }

    }

    public void listarEnCollectionPorCondicion(Predicate<T> condicion) throws ColeccionVaciaException, ColeccionInvalidaException, ElementoNoEncontradoException{
        List<T> coincidencias = buscarConjuntoCollection(condicion);
        if (!coincidencias.isEmpty()){
            for (T elemento : coincidencias){
                System.out.println(elemento);
            }
        }
        else{
            throw new ElementoNoEncontradoException();
        }
    }

    public void mostrarUnicoElementoEnCollection(Predicate<T> condicion) throws ColeccionVaciaException, ColeccionInvalidaException, ElementoNoEncontradoException{
        T elementoEncontrado = buscarUnicoElementoCollection(condicion);
        if(elementoEncontrado != null){
            System.out.println(elementoEncontrado);
        }
        else{
            throw new ElementoNoEncontradoException();
        }
    }

    public void mostrarCoincidenciasEnMap(Predicate<T> condicion) throws ColeccionInvalidaException, ColeccionVaciaException, ElementoNoEncontradoException {
        List<T> coincidencias = buscarPorAtributoEnValores(condicion);
        //Lista de values, que tuvieron el parámetro solicitado.
        if (!coincidencias.isEmpty()){
            for (T elemento : coincidencias){
                System.out.println(elemento);
            }
        }
        else{
            throw new ElementoNoEncontradoException();
        }
    }


    // -------------------------------------------------------------------------- //

    // -------------------------------------------------------------------------- //

    //Get colecciones:

    public Collection<T> getCollection() /*throws ColeccionInvalidaException*/ {

        // ¿Con o sin excepcion? Creo que con excepcion, cuando quieran verificar, en vez
        //de hacerlo en un if, se haría en un try-with-resources (lo que usamos con archivos).

        /*if(implementa == CollectionOrMap.COLLECTION){
            return collection;
        }
        else{
            throw new ColeccionInvalidaException(CollectionOrMap.MAP);
        }*/
        return collection;
    }

    public Map<K, T> getMap() /*throws ColeccionInvalidaException*/{
        /*if(implementa == CollectionOrMap.MAP){
            return map;
        }
        else{
            throw new ColeccionInvalidaException(CollectionOrMap.COLLECTION);
        }*/
        return map;
    }

    // -------------------------------------------------------------------------- //

    // -------------------------------------------------------------------------- //

    //Métodos de modificación:

    // -------------------------------------------------------------------------- //

    //Investigando, descubrí que para modificarEnCollection campos individuales de un atributo genérico (el cual
    //yo no voy a saber de antemano qué atributos tendrá, ni cuántos, ni con qué nombre, ni de qué tipo
    //de dato) se necesita usar algo llamado "REFLEXIÓN"... pero me parece algo complejo de entender,
    //aprender y utilizar, con el poco tiempo que tenemos. Así que, bueno, en lugar de eso, le pediremos
    //al usuario que reingrese un dato de todos los posible dados los atributos de la clase.
    //Así podrá modificarEnCollection el que desee. Sé que sería inviable en proyectos grandes pero, al menos,
    //cumple con su función en éste, con el conocimiento que tenemos.


    //Conclusión: Que cada gestor haga su Modificar, pq no pienso meterme ahí. (?)

    /*public void modificarEnCollection(Predicate<T> condicion){
        if(implementa == CollectionOrMap.COLLECTION){
            T objeto = buscarEnCollection(identificador);
            if(objeto != null){

                //Los else if de cada clase
                //sólo funcionarán una vez las clases modelos hereden de Mother.

                if(objeto instanceof Butaca){
                    //Funcion modificarEnCollection butaca: llama un método que contiene un switch y te da a elegir
                    //qué atributo querés modificarEnCollection en cada case.
                }
                else if(objeto instanceof Entrada){
                    //Funcion modificarEnCollection butaca: llama un método que contiene un switch y te da a elegir
                    //qué atributo querés modificarEnCollection en cada case.
                }

                /* Etc. */

    /*        }
            else{
                System.out.println("Elemento no encontrado.");
            }
        }
        if (implementa == CollectionOrMap.MAP){
            T objeto = buscarEnMapPorClave(identificador);
            if (objeto != null){

                if(objeto instanceof Butaca){
                    //Funcion modificarEnCollection butaca
                }
                else if(objeto instanceof Entrada){
                    //Funcion modificarEnCollection entrada
                }
            }
            else {
                System.out.println("Elemento no encontrado.");
            }
        }
    }*/


    // -------------------------------------------------------------------------- //

    // -------------------------------------------------------------------------- //

    // Método extra (confirmación que se repite en métodos de cada gestor):

    // -------------------------------------------------------------------------- //


    /// Pedir confirmación (será reemplazado por el método de Mati dsps):

    /*public boolean pedirConfirmacion(String mensaje) {
        Scanner scanner = new Scanner(System.in);
        String respuesta;
        do {
            System.out.println(mensaje + " S/N");
            respuesta = scanner.nextLine();
            if (respuesta.equalsIgnoreCase("S")) return true;
            if (respuesta.equalsIgnoreCase("N")) return false;
            System.out.println("Entrada inválida.");
        } while (true);
    }*/

}
