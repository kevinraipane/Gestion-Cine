package Gestores.Personas;

import Excepciones.EmailInvalidoException;
import Gestores.Funcionales.GestorConsola;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class GestorPersonas {
    private static final String FILE_PATH = "usuarios.json";//Archivo desde donde leo el ultimo id

    Scanner scanner = new Scanner(System.in);
    private int lastId = 0;

    public int recibirIdUsuario() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(FILE_PATH)) {
            JsonObject data = gson.fromJson(reader, JsonObject.class);

            if (data != null) {
                //Leo el ultimo id del json de usuarios
                if (data.has("lastId")) {
                    this.lastId = data.get("lastId").getAsInt();
                }
            }
        } catch (IOException e) {
            System.out.println("No se puedo cargar el archivo JSON: " + e.getMessage());
        }
        return lastId;
    }

    public String leerNombre() {
        String nombre;
        boolean valido;

        do {
            System.out.println("Ingrese el nombre:");
            nombre = scanner.nextLine().trim();
            valido = true;

            if (GestorConsola.estaVacio(nombre)) {
                System.out.println("El nombre no puede estar vacio");
                valido = false;
            }

            if (GestorConsola.repiteCaracteres(nombre)) {
                System.out.println("El nombre no puede tener más de tres caracteres consecutivos iguales");
                valido = false;
            }

            if (!GestorConsola.esLargoValido(nombre, 3, 25)) {
                System.out.println("El nombre debe tener entre 3 (tres) y 25 (veinticinco) caracteres");
                valido = false;
            }

            if (GestorConsola.contieneCaracteresNoValidos(nombre)) {
                System.out.println("El nombre solo puede contener caracteres de a-z, A-Z y ñ");
                valido = false;
            }

        } while (!valido);

        return nombre;
    }

    public String leerApellido() {
        String apellido;
        boolean valido;

        do {
            System.out.println("Ingrese el apellido:");
            apellido = scanner.nextLine().trim();
            valido = true;

            if (GestorConsola.estaVacio(apellido)) {
                System.out.println("El apellido no puede estar vacio");
                valido = false;
            }

            if (GestorConsola.repiteCaracteres(apellido)) {
                System.out.println("El apellido no puede tener más de tres caracteres consecutivos iguales");
                valido = false;
            }

            if (!GestorConsola.esLargoValido(apellido, 3, 25)) {
                System.out.println("El apellido debe tener entre 3 (tres) y 25 (veinticinco) caracteres");
                valido = false;
            }

            if (GestorConsola.contieneCaracteresNoValidos(apellido)) {
                System.out.println("El apellido solo puede contener caracteres de a-z, A-Z y ñ");
                valido = false;
            }

        } while (!valido);

        return apellido;
    }

    public String leerEmail() {
        String email;
        boolean valido;

        do {
            System.out.println("Ingrese el email:");
            email = scanner.nextLine().trim();
            valido = true;

            try {
                GestorConsola.esEmailValido(email);
            } catch (EmailInvalidoException e) {
                System.out.println(e.getMessage());
                valido = false;
            }

        } while (!valido);

        return email;
    }

    public LocalDate leerFechaNacimiento() {
        LocalDate fechaNacimiento = null;
        boolean valido;

        do {
            System.out.println("Ingrese la fecha de nacimiento (dd/mm/yyyy):");
            String fecha = scanner.nextLine().trim();
            valido = true;

            if (GestorConsola.esFormatoDeFechaValido(fecha, "dd/MM/yyyy")) {
                fechaNacimiento = GestorConsola.parsearFecha(fecha, "dd/MM/yyyy");

                assert fechaNacimiento != null : "La fecha de nacimiento no puede ser nula";
                if (!GestorConsola.esAnteriorAHoy(fechaNacimiento)) {
                    System.out.println("Esta intentando ingresar una fecha posterior a hoy.");
                    valido = false;
                }

                if (!GestorConsola.esEdadValida(fechaNacimiento)) {
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