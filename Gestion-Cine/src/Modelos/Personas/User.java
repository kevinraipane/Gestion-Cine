package Modelos.Personas;

import Enumeraciones.EstadoUsuario;
import Excepciones.PasswordNoValidaException;
import Excepciones.UsernameNoValidoException;
import Gestores.Funcionales.GestorConsola;
import Gestores.Personas.GestorContraseña;

import java.util.Objects;
import java.util.Scanner;

public class User {

    GestorConsola gestorConsola = new GestorConsola();

    //Atributos
    private int idUsuario;
    private String username;
    private String password;
    private EstadoUsuario estadoUsuario;

    //Constructor
    public User(int idUsuario,String username, String password){
        this.idUsuario = idUsuario;
        this.username = username;
        this.password = GestorContraseña.encriptadorContraseña(password);
        this.estadoUsuario = EstadoUsuario.ACTIVO;
    }

    //Getters y Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEstadoUsuario(EstadoUsuario estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword(){
        return password;
    }

    public EstadoUsuario getEstadoUsuario() {
        return estadoUsuario;
    }

    //Metodo equals, comparo solo los username
    @Override
    public boolean equals(Object object){
        if(this == object) return true;
        if(object == null || getClass() != object.getClass()) return false;
        User user = (User) object;
        return Objects.equals(username,user.username);
    }

    // Metodo to String
    @Override
    public String toString() {
        return "{" +
                "Username: '" + username + '\'' +
                ", ID: '" + idUsuario + '\'' +
                ", Estado del usuario: '" + estadoUsuario + '\'' +
                '}';
    }


    //Metodo hashCode
    @Override
    public int hashCode(){
        return Objects.hash(username);
    }

    //Metodo para recibir un username, y verificarla
    public String capturarUsername(){
        Scanner scanner = new Scanner(System.in);
        String username = null;
        boolean valido = false;

        while (!valido){
            System.out.println("Por favor, ingrese un nombre de usuario: ");
            username = scanner.nextLine();

            try {
                valido = gestorConsola.isValidUsername(username);
                if(valido){
                    System.out.println("Nombre de usuario valido.");
                }
            }catch (UsernameNoValidoException e){
                System.err.println("Error: " + e.getMessage());
                System.out.println("Intentelo nuevamente.");
            }
        }

        return username;
    }

    //Metodo para recibir una contraña, y verificarla
    public String capturarPassword(){
        Scanner scanner = new Scanner(System.in);
        String password = null;
        boolean valido = false;

        while (!valido){
            System.out.println("Por favor, ingrese una contraseña: ");
            password = scanner.nextLine();

            try{
                valido = gestorConsola.isValidPassword(password);
                if(valido){
                    System.out.println("Contraseña valida.");
                }
            }catch (PasswordNoValidaException e){
                System.err.println("Error: " + e.getMessage());
                System.out.println("Intentelo nuevamente.");
            }
        }

        return password;
    }

    //Verificacion de Password
    public boolean verificarPassword(String inputPassword) {
        String hashInput = GestorContraseña.encriptadorContraseña(inputPassword);
        return this.password.equals(hashInput); // Compara el hash de la contraseña
    }

}
