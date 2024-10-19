package Modelos.Personas;

import Enumeraciones.EstadoUsuario;

import java.time.LocalDate;

public abstract class Persona {
    //Atributos
    private String nombre;
    private String apeliido;
    private User user;
    private String dni;
    private String email;
    private LocalDate fechaNacimiento;
    private EstadoUsuario estado;

    //Constructor //FALTAN VERIFICACIONES
    public Persona(String nombre,String apeliido,String dni,String email,LocalDate fechaNacimiento){
        this.nombre = nombre;
        this.apeliido = apeliido;
        this.user = new User(dni,dni);
        this.dni = dni;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.estado = EstadoUsuario.ALTA;
    }

    //Getters y Setters //FALTAN VERIFICACIONES
    public String getNombre() {
        return nombre;
    }

    public String getApeliido() {
        return apeliido;
    }

    public String getDni() {
        return dni;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public EstadoUsuario getEstado() {
        return estado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApeliido(String apeliido) {
        this.apeliido = apeliido;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

}
