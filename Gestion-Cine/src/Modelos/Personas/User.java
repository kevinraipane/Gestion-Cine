package Modelos.Personas;

import java.util.Objects;

public class User {
    //Atributos
    private String username;
    private String password; //Revisar como encriptarla y usar hashcode

    //Constructor
    public User(String usuario,String contraseña){
        this.username = usuario;
        this.password = contraseña;
    }

    //Getters y Setters (Faltan verificaciones)
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
