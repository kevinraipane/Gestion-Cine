package Gestores.Personas;

import Gestores.Funcionales.GestorConsola;
import Modelos.Personas.Direccion;

import java.util.Scanner;

public class GestorDirecciones {

    Scanner scanner = new Scanner(System.in);
    GestorConsola gestorConsola = new GestorConsola();

    public Direccion cargarDireccion () {
        String calle = leerCalle();
        int numero = leerNumero();
        String localidad = leerLocalidad();

        return new Direccion(calle, numero, localidad);
    }

    public String leerCalle() {
        String calle = "";
        boolean valido = false;

        do {
            System.out.println("Ingrese el nombre de la calle: ");
            calle = scanner.next();
            scanner.nextLine();
            valido = true;

            if (gestorConsola.estaVacio(calle)) {
                System.out.println("La calle no puede estar vacia");
                valido = false;
            }

            if (gestorConsola.contieneCaracteresNoValidos(calle)) {
                System.out.println("La calle solo puede contener letras o numeros");
                valido = false;
            }

            if (gestorConsola.esLargoValido(calle, 0, 50)) {
                System.out.println("La calle debe tener menos de 50 (cincuenta) caracteres");
                valido = false;
            }

        } while (!valido);

        return calle;
    }

    public int leerNumero() {
        int numero = 0;
        boolean valido = false;

        do {
            System.out.println("Ingrese el numero: ");
            numero = scanner.nextInt();
            valido = true;

            if (gestorConsola.perteneceAlRango(numero, 0 , 10000)) {
                System.out.println("El numero debe estar entre 0 (cero) y 10000 (diez mil)");
                valido = false;
            }

        } while (!valido);

        return numero;
    }

    public String leerLocalidad() {
        String localidad = "";
        boolean valido = false;

        do {
            System.out.println("Ingrese el nombre de la localidad: ");
            localidad = scanner.next();
            scanner.nextLine();
            valido = true;

            if (gestorConsola.estaVacio(localidad)) {
                System.out.println("La calle no puede estar vacia");
                valido = false;
            }

            if (gestorConsola.contieneCaracteresNoValidos(localidad)) {
                System.out.println("La calle solo puede contener letras o numeros");
                valido = false;
            }

            if (gestorConsola.esLargoValido(localidad, 0, 50)) {
                System.out.println("La localidad debe tener menos de 50 (cincuenta) caracteres");
                valido = false;
            }

        } while (!valido);

        return localidad;
    }



}
