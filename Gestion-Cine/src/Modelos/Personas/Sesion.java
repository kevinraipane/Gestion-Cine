package Modelos.Personas;

public class Sesion {
    private User usuarioActual;

    public void iniciarSesion(User usuario){
        this.usuarioActual = usuario;
    }

    public void cerrarSesion(){
        this.usuarioActual = null;
    }

    public User getUsuarioActual() {
        return usuarioActual;
    }

    public boolean haySesionActiva(){
        return usuarioActual != null;
    }
}
