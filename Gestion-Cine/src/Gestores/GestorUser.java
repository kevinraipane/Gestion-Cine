package Gestores;

import Excepciones.UserNoEncontradoException;
import Excepciones.UsuarioYaExisteException;
import Interfaces.ICRUD;
import Modelos.Personas.User;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

public class GestorUser implements ICRUD<User> {
    private Set<User> usuarios;
    private static final String FILE_PATH = "usuarios.json";

    public GestorUser(){
        this.usuarios = new HashSet<>();
        cargarUsuarios();//Cargar los usuarios desde el archivo JSON al iniciar
    }


    //Agregar un nuevo user
    @Override
    public void create(User user)
        throws UsuarioYaExisteException {
        if(usuarios.add(user)){
            guardarUsuarios();//Guardo los cambios en JSON
            System.out.println("Usuario creado:" + user.getUsername());
        }else{
            throw new UsuarioYaExisteException("El usuario ya existe en el sistema.");
        }
    }

    @Override
    public void update(int idUsuario)
        throws UserNoEncontradoException{
        User usuario = buscarPorId(idUsuario);
        if(usuario != null){

        }
    }

    //Buscar un usuario
    @Override
    public User buscarPorId(int idUsuario)
        throws UserNoEncontradoException{
        for(User u : usuarios){
            if(u.getId_usuario() == idUsuario){
                return u;
            }
        }
        throw new UserNoEncontradoException("El usuario no existe en el sistema.");
    }

    //Eliminar usuario


    //Cargar usuarios desde el archivo JSON
    private void cargarUsuarios(){
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(FILE_PATH)){
            Type setType = new TypeToken<HashSet<User>>() {}.getType();
            Set<User> usuariosCargados = gson.fromJson(reader,setType);

            if(usuariosCargados != null){
                this.usuarios = usuariosCargados;
            }
        }catch (IOException e){
            System.out.println("No se puedo cargar el archivo JSON: " +e.getMessage());
        }
    }
    /**
     * Type setType = new TypeToken<HashSet<Usuario>>() {}.getType();
     * TypeToken<HashSet<Usuario>>: TypeToken es una clase de Gson que nos ayuda a especificar tipos complejos en tiempo
     * de ejecución, como HashSet<Usuario>.
     * new TypeToken<HashSet<Usuario>>() {}: Se crea una instancia anónima de TypeToken<HashSet<Usuario>>. Gson necesita
     * esta instancia para entender el tipo exacto con el que debe trabajar, ya que Java pierde la información de tipo
     * genérico en tiempo de ejecución (debido a type erasure o eliminación de tipos). En otras palabras, Java no sabe
     * que estamos trabajando con HashSet<Usuario> en lugar de simplemente HashSet.
     * .getType(): Este método devuelve un objeto Type que representa el tipo HashSet<Usuario>. Gson usa este Type para
     * deserializar correctamente el JSON en un HashSet<Usuario>.
     */

    //Guardar usuarios en un archivo JSON
    private void guardarUsuarios(){
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(FILE_PATH)){
            gson.toJson(this.usuarios,writer);
        }catch (IOException e){
            System.out.println("No se pudo cargar el archivo JSON: " +e.getMessage());
        }
    }
}
