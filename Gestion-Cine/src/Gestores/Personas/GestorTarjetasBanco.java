package Gestores.Personas;

import Enumeraciones.TipoTarjeta;
import Gestores.Funcionales.GestorConsola;
import Modelos.Personas.TarjetaBanco;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class GestorTarjetasBanco {

    Scanner scanner = new Scanner(System.in);

    public TarjetaBanco cargarNuevaTarjeta() {
        String nombre = leerNombreTarjeta();

        System.out.println("Tipo de tarjeta");
        TipoTarjeta tipoTarjeta = GestorConsola.leerEnum(Arrays.asList(TipoTarjeta.values()));

        String numeros = leerNumeroTarjeta();
        int codSeguridad = leerCodigoSeguridad();
        LocalDate vencimiento = leerFechaVencimiento();

        return new TarjetaBanco(nombre, tipoTarjeta, numeros, codSeguridad, vencimiento);
    }

    public String leerNombreTarjeta() {
        String nombre;
        boolean valido;

        do {
            System.out.println("Ingrese el nombre que figura en la tarjeta:");
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

            if (!GestorConsola.esLargoValido(nombre, 15, 40)) {
                System.out.println("El nombre debe tener entre 15 (quince) y 40 (cuarenta) caracteres");
                valido = false;
            }

            if (GestorConsola.contieneCaracteresNoValidos(nombre)) {
                System.out.println("El nombre solo puede contener caracteres de a-z, A-Z y ñ");
                valido = false;
            }

        } while (!valido);

        return nombre;
    }

    public String leerNumeroTarjeta() {
        String numeros;
        boolean valido;

        do {
            System.out.println("Ingrese los dieciseis digitos de la tarjeta:");
            numeros = scanner.nextLine().trim();
            valido = true;

            if (!GestorConsola.esLargoValido(numeros, 16)) {
                System.out.println("La tarjeta debe contener 16 (dieciseis) digitos");
                valido = false;
            }

            try {
                GestorConsola.contieneSoloNumeros(numeros);
            } catch (NumberFormatException e) {
                System.out.println("Solo puede contener numeros");
                valido = false;
            }

        } while (!valido);

        return numeros;
    }

    public int leerCodigoSeguridad() {
        int codigo;
        boolean valido;

        do {
            System.out.println("Ingrese el codigo de seguridad");
            codigo = scanner.nextInt();
            valido = true;

            if(!GestorConsola.perteneceAlRango(codigo, 0,999)) {
                System.out.println("El numero ingresado no es valido");
                valido = false;
            }

        } while (!valido);

        return codigo;
    }

    public LocalDate leerFechaVencimiento() {
        LocalDate fechaVencimiento = null;
        boolean valido;

        do {
            System.out.println("Ingrese la fecha de vencimiento (mm/yy):");
            String fecha = scanner.nextLine().trim();
            valido = true;

            if (GestorConsola.esFormatoDeFechaValido(fecha, "mm/yy")) {
                fechaVencimiento = GestorConsola.parsearFecha(fecha, "mm/yy");

                assert fechaVencimiento != null : "La fecha de nacimiento no puede ser nula";
                if (GestorConsola.esAnteriorAHoy(fechaVencimiento)) {
                    System.out.println("La tarjeta está vencida.");
                    valido = false;
                }

            } else {
                System.out.println("Ingrese un formato de fecha valido (mm/yy)");
                valido = false;
            }

        } while (!valido);

        return fechaVencimiento;
    }

}
