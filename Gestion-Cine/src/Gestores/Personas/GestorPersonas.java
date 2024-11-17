package Gestores.Personas;

import Excepciones.EmailInvalidoException;
import Gestores.Funcionales.GestorConsola;

import java.time.LocalDate;
import java.util.Scanner;

public class GestorPersonas {

    GestorConsola gestorConsola = new GestorConsola();
    Scanner scanner = new Scanner(System.in);

    public String leerNombre() {
        String nombre = "";
        boolean valido = false;

        do {
            System.out.println("Ingrese el nombre:");
            nombre = scanner.nextLine().trim();
            valido = true;

            if (gestorConsola.estaVacio(nombre)) {
                System.out.println("El nombre no puede estar vacio");
                valido = false;
            }

            if (gestorConsola.repiteCaracteres(nombre)) {
                System.out.println("El nombre no puede tener más de tres caracteres consecutivos iguales");
                valido = false;
            }

            if (!gestorConsola.esLargoValido(nombre, 3, 25)) {
                System.out.println("El nombre debe tener entre 3 (tres) y 25 (veinticinco) caracteres");
                valido = false;
            }

            if (gestorConsola.contieneCaracteresNoValidos(nombre)) {
                System.out.println("El nombre solo puede contener caracteres de a-z, A-Z y ñ");
                valido = false;
            }

        } while (!valido);

        return nombre;
    }

    public String leerApellido() {
        String apellido = "";
        boolean valido = false;

        do {
            System.out.println("Ingrese el apellido:");
            apellido = scanner.nextLine().trim();
            valido = true;

            if (gestorConsola.estaVacio(apellido)) {
                System.out.println("El apellido no puede estar vacio");
                valido = false;
            }

            if (gestorConsola.repiteCaracteres(apellido)) {
                System.out.println("El apellido no puede tener más de tres caracteres consecutivos iguales");
                valido = false;
            }

            if (!gestorConsola.esLargoValido(apellido, 3, 25)) {
                System.out.println("El apellido debe tener entre 3 (tres) y 25 (veinticinco) caracteres");
                valido = false;
            }

            if (gestorConsola.contieneCaracteresNoValidos(apellido)) {
                System.out.println("El apellido solo puede contener caracteres de a-z, A-Z y ñ");
                valido = false;
            }

        } while (!valido);

        return apellido;
    }

    public String leerEmail() {
        String email = "";
        boolean valido = false;

        do {
            System.out.println("Ingrese el email:");
            email = scanner.nextLine().trim();
            valido = true;

            try {
                gestorConsola.esEmailValido(email);
            } catch (EmailInvalidoException e) {
                System.out.println(e.getMessage());
                valido = false;
            }

        } while (!valido);

        return email;
    }

    public LocalDate leerFechaNacimiento() {
        LocalDate fechaNacimiento = null;
        boolean valido = false;

        do {
            System.out.println("Ingrese la fecha de nacimiento (dd/mm/yyyy):");
            String fecha = scanner.nextLine().trim();
            valido = true;

            if (gestorConsola.esFormatoDeFechaValido(fecha, "dd/MM/yyyy")) {
                fechaNacimiento = gestorConsola.parsearFecha(fecha);

                if (!gestorConsola.esAnteriorAHoy(fechaNacimiento)) {
                    System.out.println("Esta intentando ingresar una fecha posterior a hoy.");
                    valido = false;
                }

                if (!gestorConsola.esEdadValida(fechaNacimiento)) {
                    System.out.println("La fecha de nacimiento que desea ingresar no corresponde con una edad valida.");
                    valido = false;
                }

            } else {
                System.out.println("Ingrese un formato de fecha valido (dd/mm/yyyy)");
                valido = false;
            }

        } while (!valido);

        return fechaNacimiento;
    }

}