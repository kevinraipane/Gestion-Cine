package Interfaces;

public interface ICRUD <T extends Comparable<T>> {

    //recibe un elemento generico y lo carga, hay que implementar una funcion que pida los datos a cargar
    void create(T elemento) throws Exception;;

    //recibe un elemento y lo modifica
    void update(int id)throws Exception;;

    //deberia devolver una excepcion en caso de no encontrar el elemento
    T buscarPorId(int id)throws Exception;;

    //llama a la funcion de busqueda. si se encontro, se elimino, si no se encuentra, la propia funcion de
    // busqueda deberia abortar la mision
    void delete(T elemento)throws Exception;;

    //trabaja con la coleccion propia, no la recibe como parametro
    void listar()throws Exception;;

}
