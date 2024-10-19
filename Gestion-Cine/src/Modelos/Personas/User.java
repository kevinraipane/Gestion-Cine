package Modelos.Personas;

public class User {
    //Atributos
    //private String rol;
    private String usuario;
    private String contraseña; //Revisar como encriptarla y usar hashcode

    //Constructor
    //public User(String rol,String usuario,String contraseña){
    public User(String usuario,String contraseña){
        //this.rol = rol;
        this.usuario = usuario;
        this.contraseña  = contraseña;
    }

    //Getters y Setters (Faltan verificaciones y definir de donde sacamos el rol)
    /**public void setRol(String rol) {
        this.rol = rol;
    }
     */

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    /**
    public String getRol() {
        return rol;
    }
    */
    public String getUsuario() {
        return usuario;
    }

    public String getContraseña(){
        return contraseña;
    }


}
