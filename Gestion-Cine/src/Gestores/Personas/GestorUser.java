package Gestores.Personas;

import Enumeraciones.EstadoUsuario;
import Excepciones.*;
import Gestores.Funcionales.GestorConsola;
import Modelos.Personas.Sesion;
import Modelos.Personas.User;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class GestorUser {
    private static final String USUARIOS_FILE_PATH = "usuarios.json";

    Scanner scanner = new Scanner(System.in);
    GestorConsola gestorConsola = new GestorConsola();

    private Set<User> usuarios;
    private int lastId = 0;

    public GestorUser() {
        this.usuarios = new HashSet<>();
        cargarUsuarios();//Cargar los usuarios desde el archivo JSON al iniciar
    }


    /// AGREGAR NUEVO USUARIO ------------------------------------------------------------------

    public void crearUsuario(String username, String password)
            throws UsuarioYaExisteException {

        try {
            usernameDisponible(username);
        } catch (UsuarioYaExisteException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        //Si no existe, se crea un nuevo usuario con el ultimo ID
        int newId = (++lastId);//Incremento el id que tengo leido desde el json
        User newUser = new User(newId, username, password);

        if (usuarios.add(newUser)) {
            guardarUsuarios();//Guardo los cambios en JSON
            System.out.println("Usuario creado:" + newUser.getUsername());

        } else {
            throw new UsuarioYaExisteException("El usuario ya existe en el sistema.");
        }
    }

    /// MODIFICAR USUARIO ----------------------------------------------------------------------

    public void reemplazarUsuario(int idUsuario, String nuevoUsername, String nuevaPassword, EstadoUsuario nuevoEstadoUsuario)
            throws UserNoEncontradoException {

        User usuario = buscarPorId(idUsuario);

        if (usuario == null) {
            throw new UserNoEncontradoException("No se encontro el usuario con ID: " + idUsuario);
        }

        //Modifico solo los atributos que no sean nulos
        if (nuevoUsername != null && !nuevoUsername.isEmpty()) {
            usuario.setUsername(nuevoUsername);
        }

        if (nuevaPassword != null && !nuevaPassword.isEmpty()) {
            usuario.setPassword(GestorContraseña.encriptadorContraseña(nuevaPassword));
        }

        if (nuevoEstadoUsuario != null) {
            usuario.setEstadoUsuario(nuevoEstadoUsuario);
        }

        guardarUsuarios();
        System.out.println("Usuario con ID " + idUsuario + " modificado con exito.");
    }

    public void modificarUsuario(int idUsuario, Scanner scanner) {
        User usuario = buscarPorId(idUsuario);

        if (usuario == null) {
            System.out.println("No se encontro el usuario con ID: " + idUsuario);
            return;
        }

        System.out.println("¿Qué atributo desea modificar del usuario?");
        System.out.println("1. Modificar Username");
        System.out.println("2. Modificar Password");
        System.out.println("3. Dar de BAJA/ALTA el Usuario");
        System.out.println("4. Modificar todos los atributos");
        System.out.print("Seleccione una opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                System.out.print("Ingrese el nuevo username: ");
                String nuevoUsername = capturarUsername();
                reemplazarUsuario(idUsuario, nuevoUsername, usuario.getPassword(), usuario.getEstadoUsuario());
                break;

            case 2:
                System.out.print("Ingrese la nueva contraseña: ");
                String nuevaPassword = capturarPassword();
                reemplazarUsuario(idUsuario, usuario.getUsername(), nuevaPassword, usuario.getEstadoUsuario());
                break;

            case 3:
                System.out.print("Ingrese el nuevo estado del usuario (ACTIVO/INACTIVO): ");
                EstadoUsuario nuevoEstado = gestorConsola.leerEnum(Arrays.asList(EstadoUsuario.values()));
                reemplazarUsuario(idUsuario, usuario.getUsername(), usuario.getPassword(), nuevoEstado);
                break;

            case 4:
                System.out.print("Ingrese el nuevo username: ");
                String username = capturarUsername();
                System.out.print("Ingrese la nueva contraseña: ");
                String password = capturarPassword();
                System.out.print("Ingrese el nuevo estado del usuario (ACTIVO/INACTIVO): ");
                EstadoUsuario estado = gestorConsola.leerEnum(Arrays.asList(EstadoUsuario.values()));
                reemplazarUsuario(idUsuario, username, password, estado);
                break;

            default:
                System.out.println("Opción no válida.");
        }
    }

    public void modificarUsuarioPorId(Scanner scanner, int idUsuario) {
        modificarUsuario(idUsuario, scanner);
    }

    /// INGRESO DE DATOS --------------------------------------------------------------

    public String capturarUsername() {
        Scanner scanner = new Scanner(System.in);
        String username = null;
        boolean valido = false;

        while (!valido) {
            System.out.println("Por favor, ingrese un nombre de usuario: ");
            username = scanner.nextLine();

            try {
                valido = gestorConsola.isValidUsername(username);
                if (valido) {
                    System.out.println("Nombre de usuario valido.");
                }
            } catch (UsernameNoValidoException e) {
                System.err.println("Error: " + e.getMessage());
                System.out.println("Intentelo nuevamente.");
            }
        }

        return username;
    }

    public String capturarPassword() {
        Scanner scanner = new Scanner(System.in);
        String password = null;
        boolean valido = false;

        while (!valido) {
            System.out.println("Por favor, ingrese una contraseña: ");
            password = scanner.nextLine();

            try {
                valido = gestorConsola.isValidPassword(password);
                if (valido) {
                    System.out.println("Contraseña valida.");
                }
            } catch (PasswordNoValidaException e) {
                System.err.println("Error: " + e.getMessage());
                System.out.println("Intentelo nuevamente.");
            }
        }

        return password;
    }


    //Actualizar un usuario, recibe un id, para modificar los campos. SOLO DEL ADMIN.
    //Forma para que vea el id del usuario que quiere modificar, y no lo tengo que llevar de memoria por mucho tiempo
    //Que obtenga el dato antes de modificar


    /// BUSCAR USUARIOS ---------------------------------------------------------------------------------------------

    // por ID
    public User buscarPorId(int idUsuario)
            throws UserNoEncontradoException {
        for (User u : usuarios) {
            if (u.getIdUsuario() == idUsuario) {
                return u;
            }
        }
        throw new UserNoEncontradoException("El usuario con ID: " + idUsuario + " no existe en el sistema.");
    }

    // por username
    public User buscarPorUsername(String username)
            throws UserNoEncontradoException {
        for (User u : usuarios) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        throw new UserNoEncontradoException("El usuario " + username + " no existe en el sistema.");
    }

    /// LISTAR USUARIOS ------------------------------------------------------------------------------------------

    public void listarUsuarios()
            throws ColeccionVaciaException {
        if (!usuarios.isEmpty()) {
            for (User u : usuarios) {
                System.out.println(u);
                System.out.println("--------------------------------------------------------");
            }
        } else {
            throw new ColeccionVaciaException("La coleccion se encuentra vacia.");
        }
    }

    /// ELIMINAR USUARIOS -----------------------------------------------------------------------------------------

    public void eliminarUsuario(String username)
            throws UserNoEncontradoException {
        User usuarioAEliminar = buscarPorUsername(username);
        if (usuarioAEliminar != null) {
            usuarioAEliminar.setEstadoUsuario(EstadoUsuario.INACTIVO);
            guardarUsuarios(); //Guardo los cambios despues de la eliminacion.
            System.out.println("Usuario eliminado: " + username);
        } else {
            throw new UserNoEncontradoException("El usuario " + username + " no pudo eliminar porque no existe.");
        }
    }

    /// INICIO DE SESION --------------------------------------------------------------------------------------------

    public void validarInicioSesion(Sesion sesion, GestorUser gestorUser) {
        while (!sesion.haySesionActiva()) {
            System.out.println("Bienvenido. Por favor, ingrese sus credenciales.");

            String username = gestorUser.capturarUsername();
            String password = gestorUser.capturarPassword();

            try {
                User usuario = gestorUser.iniciarSesion(username, password);
                sesion.iniciarSesion(usuario);
                System.out.println("Sesión iniciada como: " + usuario.getUsername());

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());

            }
        }
    }

    public User iniciarSesion(String username, String password)
            throws UserNoEncontradoException, CredencialesInvalidasException {

        User usuario = buscarPorUsername(username);

        if (usuario != null && usuario.getPassword().equals(GestorContraseña.encriptadorContraseña(password))) {//Valido la contraseña
            if (usuario.getEstadoUsuario() == EstadoUsuario.INACTIVO) {
                throw new CredencialesInvalidasException("El usuario esta desactivado (Dado de baja).");
            }
            return usuario; //Retorno el usuario sin las credenciales son correctas y esta activo.

        } else {
            throw new CredencialesInvalidasException("Credenciales incorrectas.");
        }
    }


    /// ARCHIVOS: JSON -----------------------------------------------------------------------------------------

    // Cargar usuarios desde JSON
    private void cargarUsuarios() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(USUARIOS_FILE_PATH)) {
            JsonObject data = gson.fromJson(reader, JsonObject.class);

            if (data != null) {
                //Cargo el ultimo id del json
                if (data.has("lastId")) {
                    this.lastId = data.get("lastId").getAsInt();
                }
                //Leo los usuarios
                if (data.has("usuarios")) {
                    Type setType = new TypeToken<HashSet<User>>() {
                    }.getType();
                    Set<User> usuariosCargados = gson.fromJson(data.get("usuarios"), setType);
                    if (usuariosCargados != null) {
                        this.usuarios = usuariosCargados;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("No se puedo cargar el archivo JSON: " + e.getMessage());
        }
    }


    // Guardar usuarios en un archivo JSON
    private void guardarUsuarios() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(USUARIOS_FILE_PATH)) {
            JsonObject data = new JsonObject();

            //Guardo el ultimo id
            data.addProperty("lastId", this.lastId);

            //Guardo los usuarios
            data.add("usuarios", gson.toJsonTree(this.usuarios));

            //Escribo los usuarios en el archivo
            gson.toJson(data, writer);
        } catch (IOException e) {
            System.out.println("No se pudo guardar el archivo JSON: " + e.getMessage());
        }
    }

    /// VALIDACIONES -----------------------------------------------------------------

    private boolean usernameDisponible(String username) throws UsuarioYaExisteException {
        for (User user : usuarios) {
            if (user.getUsername().equals(username)) {
                throw new UsuarioYaExisteException("El usuario con nombre " + username + " ya existe en el sistema.");
            }
        }

        return true;
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
