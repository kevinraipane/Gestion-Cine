package Gestores.Personas;

import Enumeraciones.EstadoUsuario;
import Excepciones.ColeccionVaciaException;
import Excepciones.CredencialesInvalidasException;
import Excepciones.UserNoEncontradoException;
import Excepciones.UsuarioYaExisteException;
import Modelos.Personas.User;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class GestorUser {
    private static final String FILE_PATH = "usuarios.json";
    Scanner scanner = new Scanner(System.in);

    private Set<User> usuarios;
    private int lastId = 0;

    public GestorUser(){
        this.usuarios = new HashSet<>();
        cargarUsuarios();//Cargar los usuarios desde el archivo JSON al iniciar
    }


    //Agregar un nuevo user
    public void create(String username,String password)
        throws UsuarioYaExisteException {
        for(User user : usuarios){
            if(user.getUsername().equals(username)){
                throw new UsuarioYaExisteException("El usuario con nombre " +username+ " ya existe en el sistema.");
            }
        }

        //Si no existe, se crea un nuevo usuario con el ultimo ID
        int newId = (++lastId);//Incremento el id que tengo leido desde el json
        User newUser = new User(newId,username,password);
        if(usuarios.add(newUser)){
            guardarUsuarios();//Guardo los cambios en JSON
            System.out.println("Usuario creado:" + newUser.getUsername());
        }else{
            throw new UsuarioYaExisteException("El usuario ya existe en el sistema.");
        }
    }

    //Actualizar usuario, le paso el tempUser, para modificar los datos del usuario logueado
    public void modificarUsuario(int idUsuario,String nuevoUsername,String nuevaPassword)
            throws UserNoEncontradoException{

        User usuario = buscarPorId(idUsuario);

        if(usuario == null){
            throw new UserNoEncontradoException("No se encontro el usuario con ID: " +idUsuario);
        }

        usuario.setUsername(nuevoUsername);
        usuario.setPassword(GestorContraseña.encriptadorContraseña(nuevaPassword));
        guardarUsuarios();
        System.out.println("Usuario con ID " +idUsuario+ " modificado con exito.");
    }

    //Actualizar un usuario, recibe un id, para modificar los campos. SOLO DEL ADMIN.
    //Forma para que vea el id del usuario que quiere modificar, y no lo tengo que llevar de memoria por mucho tiempo
    //Que obtenga el dato antes de modificar


    //Buscar un usuario por id
    public User buscarPorId(int idUsuario)
        throws UserNoEncontradoException{
        for(User u : usuarios){
            if(u.getIdUsuario() == idUsuario){
                return u;
            }
        }
        throw new UserNoEncontradoException("El usuario con ID: " +idUsuario+ " no existe en el sistema.");
    }

    //Buscar usuario por username
    public User buscarPorUsername(String username)
            throws UserNoEncontradoException{
        for (User u : usuarios){
            if(u.getUsername().equals(username)){
                return u;
            }
        }
        throw new UserNoEncontradoException("El usuario " +username+ " no existe en el sistema.");
    }

    //Mostrar todos los usuarios
    public void listarUsuarios()
        throws ColeccionVaciaException {
        if(!usuarios.isEmpty()){
            for(User u : usuarios){
                System.out.println(u);
                System.out.println("--------------------------------------------------------");
            }
        }else{
            throw new ColeccionVaciaException("La coleccion se encuentra vacia.");
        }

    }

    //Eliminar usuario por username
    public void eliminarUsuario(String username)
            throws UserNoEncontradoException{
        User usuarioAEliminar = buscarPorUsername(username);
        if(usuarioAEliminar != null){
            usuarioAEliminar.setEstadoUsuario(EstadoUsuario.BAJA);
            guardarUsuarios(); //Guardo los cambios despues de la eliminacion.
            System.out.println("Usuario eliminado: " +username);
        }else{
            throw new UserNoEncontradoException("El usuario " +username+ " no pudo eliminar porque no existe.");
        }
    }

    //Metodo para verificar si la contraseña ingresada es correcta
    public boolean validarPassword(String username,String inputPassword){
        User user = buscarPorUsername(username);
        return user.verificarPassword(inputPassword);
    }

    //Metodo para iniciar sesion
    public User iniciarSesion(String username,String password)
        throws UserNoEncontradoException, CredencialesInvalidasException{
        User usuario = buscarPorUsername(username);
        if(usuario != null && usuario.getPassword().equals(GestorContraseña.encriptadorContraseña(password))){//Valido la contraseña
            if(usuario.getEstadoUsuario() == EstadoUsuario.BAJA){
                throw new CredencialesInvalidasException("El usuario esta desactivado (Dado de baja).");
            }
            return usuario; //Retorno el usuario sin las credenciales son correctas y esta activo.
        } else {
            throw new CredencialesInvalidasException("Credenciales incorrectas.");
        }
    }

    //Cargar usuarios desde el archivo JSON
    private void cargarUsuarios(){
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(FILE_PATH)){
            JsonObject data = gson.fromJson(reader, JsonObject.class);

            if(data != null){
                //Cargo el ultimo id del json
                if(data.has("lastId")){
                    this.lastId = data.get("lastId").getAsInt();
                }
                //Leo los usuarios
                if(data.has("usuarios")){
                    Type setType = new TypeToken<HashSet<User>>() {}.getType();
                    Set<User> usuariosCargados = gson.fromJson(data.get("usuarios"),setType);
                    if(usuariosCargados != null){
                        this.usuarios = usuariosCargados;
                    }
                }
            }
        }catch (IOException e){
            System.out.println("No se puedo cargar el archivo JSON: " +e.getMessage());
        }
    }


    //Guardar usuarios en un archivo JSON
    private void guardarUsuarios(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(FILE_PATH)){
            JsonObject data = new JsonObject();

            //Guardo el ultimo id
            data.addProperty("lastId",this.lastId);

            //Guardo los usuarios
            data.add("usuarios",gson.toJsonTree(this.usuarios));

            //Escribo los usuarios en el archivo
            gson.toJson(data,writer);
        }catch (IOException e){
            System.out.println("No se pudo guardar el archivo JSON: " +e.getMessage());
        }
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
