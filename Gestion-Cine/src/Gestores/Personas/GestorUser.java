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

    private Set<User> usuarios;
    private int lastId = 0;

    public GestorUser() {
        this.usuarios = new HashSet<>();
        cargarUsuarios(); //Cargar los usuarios desde el archivo JSON al iniciar
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
        User newUser = new User(lastId++, username, password);

        if (usuarios.add(newUser)) {
            guardarUsuarios();//Guardo los cambios en JSON
            System.out.println("Usuario creado: " + newUser.getUsername());

        } else {
            throw new UsuarioYaExisteException("El usuario ya existe en el sistema.");
        }
    }

    /// MODIFICAR USUARIO ----------------------------------------------------------------------

    public void reemplazarUsuario(int idUsuario, String nuevoUsername, String nuevaPassword, EstadoUsuario nuevoEstado) {
        User usuario;

        try {
            usuario = buscarPorId(idUsuario);

            usuario.setUsername(nuevoUsername);
            usuario.setPassword(nuevaPassword);
            usuario.setEstadoUsuario(nuevoEstado);

            guardarUsuarios();
            System.out.println("Usuario con ID " + idUsuario + " modificado con exito.");

        } catch (UserNoEncontradoException e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }

    public void modificarUsuario(int idUsuario, Scanner scanner) {
        try {
            User usuario = buscarPorId(idUsuario);
            String username = usuario.getUsername();
            String password = usuario.getPassword();
            EstadoUsuario estadoUsuario = usuario.getEstadoUsuario();
            int opcion;

            do {
                System.out.println("¿Qué atributo desea modificar del usuario?");
                System.out.println("1. Modificar Username");
                System.out.println("2. Modificar Password");
                System.out.println("3. Dar de BAJA/ALTA el Usuario");
                System.out.println("4. Modificar todos los atributos");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");

                opcion = scanner.nextInt();
                scanner.nextLine();

            } while (opcion < 0 || opcion > 4);

            switch (opcion) {
                case 1:
                    username = capturarUsername();
                    break;

                case 2:
                    password = capturarPassword();
                    break;

                case 3:
                    System.out.print("Ingrese el nuevo estado del usuario (ACTIVO/INACTIVO): ");
                    estadoUsuario = GestorConsola.leerEnum(Arrays.asList(EstadoUsuario.values()));
                    break;

                case 4:
                    username = capturarUsername();
                    password = capturarPassword();
                    System.out.print("Ingrese el nuevo estado del usuario (ACTIVO/INACTIVO): ");
                    estadoUsuario = GestorConsola.leerEnum(Arrays.asList(EstadoUsuario.values()));
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

            reemplazarUsuario(idUsuario, username, password, estadoUsuario);

        } catch (UserNoEncontradoException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
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
                valido = GestorConsola.isValidUsername(username);
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
                valido = GestorConsola.isValidPassword(password);
                if (valido) {
                    System.out.println("Contraseña valida.");
                }
            } catch (PasswordNoValidaException e) {
                System.err.println("Error: " + e.getMessage());
                System.out.println("Intentelo nuevamente.");
            }
        }

        return GestorContraseña.encriptadorContraseña(password);
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
        System.out.println("Usuarios cargados en el sistema: ");

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
            throws CredencialesInvalidasException {

        try {
            User usuario = buscarPorUsername(username);

            if (usuario.getPassword().equals(password)) {
                if (usuario.getEstadoUsuario() == EstadoUsuario.INACTIVO) {
                    throw new CredencialesInvalidasException("El usuario esta desactivado (Dado de baja).");
                }

                return usuario;
            } else {
                throw new CredencialesInvalidasException("Credenciales incorrectas.");
            }

        } catch (UserNoEncontradoException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        return null;
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

/*
 * Type setType = new TypeToken<HashSet<Usuario>>() {}.getType();
 * TypeToken<HashSet<Usuario>>: TypeToken es una clase de Gson que nos ayuda a especificar tipos complejos en tiempo
 * de ejecución, como HashSet<Usuario>.
 * new TypeToken<HashSet<Usuario>>() {}: Se crea una instancia anónima de TypeToken<HashSet<Usuario>>. Gson necesita
 * esta instancia para entender el tipo exacto con el que debe trabajar, ya que Java pierde la información de tipo
 * genérico en tiempo de ejecución (debido a type erasure o eliminación de tipos). En otras palabras, Java no sabe
 * que estamos trabajando con HashSet<Usuario> en lugar de simplemente HashSet.
 * .getType(): Este metodo devuelve un objeto Type que representa el tipo HashSet<Usuario>. Gson usa este Type para
 * deserializar correctamente el JSON en un HashSet<Usuario>.
 */
