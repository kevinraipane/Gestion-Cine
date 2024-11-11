package Modelos.Personas;

import java.util.Objects;
import java.util.Scanner;

public class User {
    //Atributos
    private static int contadorID = 0;
    private final int id_usuario;//Asi nunca puede ser modificado
    private String username;
    private String password; //Revisar como encriptarla y usar hashcode

    //Constructor
    public User(String username, String password){
        this.id_usuario = contadorID++;
        this.username = username;
        this.password = password;
    }

    //Getters y Setters (Faltan verificaciones)
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword(){
        return password;
    }

    //Metodo equals, comparo solo los username
    @Override
    public boolean equals(Object object){
        if(this == object) return true;
        if(object == null || getClass() != object.getClass()) return false;
        User user = (User) object;
        return Objects.equals(username,user.username);
    }

    //Metodo hashCode
    @Override
    public int hashCode(){
        return Objects.hash(username);
    }

    //Metodo para recibir un username, y verificarla
    public String capturarUsername(){
        Scanner scanner = new Scanner(System.in);
        boolean valido = false;

        while (!valido){
            System.out.println("Ingrese su username: ");
            String aux = scanner.nextLine();

        }


        return "";
    }

    //Metodo para recibir una contraña, y verificarla
    public String capturarPassword(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la contraseña: ");
        return scanner.nextLine();
    }

}
