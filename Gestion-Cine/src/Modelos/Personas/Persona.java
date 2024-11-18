package Modelos.Personas;

import Enumeraciones.EstadoUsuario;

import java.time.LocalDate;

public abstract class Persona {
    //Atributos
    private int idUsuario;//Recibe un id que se enlace con el idUsuario del archivo de usuarios
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private LocalDate fechaNacimiento;
    private EstadoUsuario estadoUsuario;

    public Persona(int idUsuario,String nombre, String apellido, String dni, String email, LocalDate fechaNacimiento) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.estadoUsuario = EstadoUsuario.ALTA;
    }

    //Getters y Setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
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

    public EstadoUsuario getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFechaNacimiento(LocalDate fecha) {
        this.fechaNacimiento = fecha;
    }

    public void setEstadoUsuario(EstadoUsuario estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + idUsuario + '\'' +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", email='" + email + '\'' +
                ", estado usuario='" + estadoUsuario + '\'' +
                ", fechaNacimiento=" + fechaNacimiento;
    }
}