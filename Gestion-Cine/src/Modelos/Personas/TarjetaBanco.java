package Modelos.Personas;

import Enumeraciones.TipoTarjeta;

import java.time.LocalDate;

public class TarjetaBanco {

    private int idTarjeta;
    private static int cantTarjetas;

    private String nombre;
    private TipoTarjeta tipoTarjeta;
    private String numeros;
    private int codSeguridad;
    private LocalDate fechaVencimiento;

    public TarjetaBanco(String nombre, TipoTarjeta tipoTarjeta, String numeros, int codSeguridad, LocalDate fechaVencimiento) {
        this.idTarjeta = cantTarjetas++;

        this.nombre = nombre;
        this.tipoTarjeta = tipoTarjeta;
        this.numeros = numeros;
        this.codSeguridad = codSeguridad;
        this.fechaVencimiento = fechaVencimiento;
    }

    public int getIdTarjeta() {
        return idTarjeta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoTarjeta getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(TipoTarjeta tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public String getNumeros() {
        return numeros;
    }

    public void setNumeros(String numeros) {
        this.numeros = numeros;
    }

    public int getCodSeguridad() {
        return codSeguridad;
    }

    public void setCodSeguridad(int codSeguridad) {
        this.codSeguridad = codSeguridad;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String mostrarDatos() {
        return "**** **** **** " + getNumeros().substring(getNumeros().length() - 4);
    }

}
