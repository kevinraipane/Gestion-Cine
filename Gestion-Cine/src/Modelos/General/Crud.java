package Modelos.General;

import Enumeraciones.CollectionOrMap;
import Enumeraciones.TipoColeccion;
import Excepciones.ColeccionInvalidaException;
import Excepciones.ElementoExisteEnColeccionException;
import Modelos.Cine.Butaca;
import Modelos.Cine.Entrada;

import java.util.*;

public class Crud <T extends BaseEntity, K>{

    //Atributos:

    private Collection<T> collection;
    private Map<K, T> map;
    private CollectionOrMap heredaDe;

    //Constructor:

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
                heredaDe = CollectionOrMap.COLLECTION;
                break;
            case HASHMAP:
            case LINKEDHASHMAP:
            case TREEMAP:
                map = crearMap(eleccionColeccion);
                heredaDe = CollectionOrMap.MAP;
                break;
            default: //Sé que no es requerido por trabajar con enums... pero da mayor flexibilidad para
                     //posibles cambios futuros.
                     throw new ColeccionInvalidaException("Colección no soportada.");
        }
    }

    //Métodos:

    //Distinción para el constructor:

    // -------------------------------------------------------------------------- //

    public Collection<T> crearCollection(TipoColeccion tipo) throws ColeccionInvalidaException {
        switch (tipo) {
            case ARRAYLIST: return new ArrayList<>();
            case LINKEDLIST: return new LinkedList<>();
            case VECTOR: return new Vector<>();
            case HASHSET: return new HashSet<>();
            case LINKEDHASHSET: return new LinkedHashSet<>();
            case TREESET: return new TreeSet<>();
            default: throw new ColeccionInvalidaException("Colección no soportada.");
        }
    }

    public Map<K, T> crearMap(TipoColeccion tipo) throws ColeccionInvalidaException {
        switch (tipo) {
            case HASHMAP: return new HashMap<>();
            case LINKEDHASHMAP: return new LinkedHashMap<>();
            case TREEMAP: return new TreeMap<>();
            default: throw new ColeccionInvalidaException("Colección no soportada.");
        }
    }

    // -------------------------------------------------------------------------- //

    //Métodos de inserción:

    // -------------------------------------------------------------------------- //

    public void agregar(K key, T objeto, boolean permitirDuplicados) throws ElementoExisteEnColeccionException {
        if(heredaDe == CollectionOrMap.COLLECTION){
            if(permitirDuplicados || !collection.contains(objeto)){
                collection.add(objeto);
            }
            else{
                throw new ElementoExisteEnColeccionException("Error: La colección ya contiene ese elemento.");
            }
        }
        else if(heredaDe == CollectionOrMap.MAP){
            if (!map.containsKey(key)){
                map.put(key, objeto);
            }
            throw new ElementoExisteEnColeccionException("Error: La colección ya contiene este objeto.");
        }
    }

    // -------------------------------------------------------------------------- //

    //Métodos de búsqueda:

    // -------------------------------------------------------------------------- //

    public T buscar(K id) { // Usamos K porque es el tipo de la clave en el map
        if (heredaDe == CollectionOrMap.COLLECTION) {
            if (!collection.isEmpty()){
                return buscarEnCollection(id);
            }
        } else if (heredaDe == CollectionOrMap.MAP) {
            if (!map.isEmpty()){
                return buscarEnMap(id);
            }
        }
        return null;
    }

    public T buscarEnCollection(K atributoIdentificador) {
        for (T item : collection) {
            if (item.getAtributoIdentificador().equals(atributoIdentificador)) {
                return item;
            }
        }
        return null;
    }

    public T buscarEnMap(K key) {
        return map.get(key);
    }

    // -------------------------------------------------------------------------- //

    //Métodos de eliminación:

    // -------------------------------------------------------------------------- //

    public void eliminar(K identificador /*o Clave para maps*/){
        if(heredaDe == CollectionOrMap.COLLECTION){
            T objeto = buscarEnCollection(identificador);
            if(objeto != null){
                collection.remove(objeto);
                System.out.println("Elemento eliminado con éxito.");
            }
            else{
                System.out.println("Elemento no encontrado.");
            }
        }
        if (heredaDe == CollectionOrMap.MAP){
            if (map.containsKey(identificador)){
                map.remove(identificador);
            }
            else {
                System.out.println("Elemento no encontrado.");
            }
        }
    }

    // -------------------------------------------------------------------------- //

    //Métodos de listar:

    public void listar(){ //RECORDAR HACER TO STRING EN CADA CLASE MODELO.
        if(heredaDe == CollectionOrMap.COLLECTION){
            for(T elemento : collection){
                System.out.println(elemento);
            }
        }
        else if(heredaDe == CollectionOrMap.MAP){
            for(Map.Entry<K, T> entry : map.entrySet()){
                System.out.println("Clave: " + entry.getKey() + ", Valor: " + entry.getValue());
            }
        }
    }

    // -------------------------------------------------------------------------- //

    // -------------------------------------------------------------------------- //

    //Métodos de modificación:

    // -------------------------------------------------------------------------- //

    //Investigando, descubrí que para modificar campos individuales de un atributo genérico (el cual
    //yo no voy a saber de antemano qué atributos tendrá, ni cuántos, ni con qué nombre, ni de qué tipo
    //de dato) se necesita usar algo llamado "REFLEXIÓN"... pero me parece algo complejo de entender,
    //aprender y utilizar, con el poco tiempo que tenemos. Así que, bueno, en lugar de eso, le pediremos
    //al usuario que reingrese un dato de todos los posible dados los atributos de la clase.
    //Así podrá modificar el que desee. Sé que sería inviable en proyectos grandes pero, al menos,
    //cumple con su función en éste, con el conocimiento que tenemos.


    public void modificar(K identificador /*o Clave para maps*/){
        if(heredaDe == CollectionOrMap.COLLECTION){
            T objeto = buscarEnCollection(identificador);
            if(objeto != null){

                //Los else if de cada clase
                //sólo funcionarán una vez las clases modelos hereden de Mother.

                if(objeto instanceof Butaca){
                    //Funcion modificar butaca: llama un método que contiene un switch y te da a elegir
                    //qué atributo querés modificar en cada case.
                }
                else if(objeto instanceof Entrada){
                    //Funcion modificar butaca: llama un método que contiene un switch y te da a elegir
                    //qué atributo querés modificar en cada case.
                }

                /* Etc. */

            }
            else{
                System.out.println("Elemento no encontrado.");
            }
        }
        if (heredaDe == CollectionOrMap.MAP){
            T objeto = buscarEnMap(identificador);
            if (objeto != null){

                if(objeto instanceof Butaca){
                    //Funcion modificar butaca
                }
                else if(objeto instanceof Entrada){
                    //Funcion modificar entrada
                }
            }
            else {
                System.out.println("Elemento no encontrado.");
            }
        }
    }


    // -------------------------------------------------------------------------- //

}
