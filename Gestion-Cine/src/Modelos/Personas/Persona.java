package Modelos.Personas;

import Enumeraciones.EstadoUsuario;

import java.time.LocalDate;

public abstract class Persona {
    //Atributos
    private String nombre;
    private String apellido;
    private User user;
    private String dni;
    private String email;
    private LocalDate fechaNacimiento;


    //Constructor //FALTAN VERIFICACIONES
    public Persona(String nombre,String apellido,String dni,String email,LocalDate fechaNacimiento){
        this.nombre = nombre;
        this.apellido = apellido;
        this.user = new User(dni,dni);
        this.dni = dni;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
    }

    //Getters y Setters //FALTAN VERIFICACIONES
    public String getNombre() {
        return nombre;
    }

    public String getApeliido() {
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApeliido(String apeliido) {
        this.apellido = apeliido;
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
