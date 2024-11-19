package Modelos.Personas;

import Interfaces.IVisualizable;

import java.time.LocalDate;

public class Cliente extends Persona implements IVisualizable {


     public Cliente(String nombre, String apellido, String dni, String email, LocalDate fechaNacimiento) {
          super(nombre, apellido, dni, email, fechaNacimiento);
     }

     @Override
     public void verCartelera() {

     }

     @Override
     public void verReservas() {

     }

     @Override
     public void verFunciones() {

     }
}
