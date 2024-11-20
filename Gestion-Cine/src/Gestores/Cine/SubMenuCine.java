package Gestores.Cine;

import java.util.Scanner;

public class SubMenuCine {
    private int salida;
    private String opcion;
    private static Scanner entrada = new Scanner(System.in);
    private boolean salirPrograma = false;

    public SubMenuCine(){
    }

    public void ejecutar(){
        do{
            System.out.println("Seleccione una opcion:" +
                    "\n[0] Salir." +
                    "\n[1] Gestión de salas." +
                    "\n[2] Gestión horarios." +
                    "\n[3] Gestión de peliculas" +
                    "\n[4] Gestión de funciones");

            opcion= entrada.nextLine();
            switch (opcion){
                case "0":
                    System.out.println("Saliendo del programa...");
                    salirPrograma = true;
                    break;
                case "1":
                    mostrarMenuSala(entrada);
                    pausa();
                    break;
                case "2":
                    mostrarMenuHorarios(entrada);
                    pausa();
                    break;
                case "3":
                    mostrarMenuPeliculas();
                    pausa();
                    break;
                case "4":
                    mostrarMenuFunciones();
                    pausa();
                    break;
                default:
                System.out.println("\nOpcion invalida, vuelva a intentar.");
                    pausa();
                    break;
            }
        }while (true);
    }

    ///---------------------------------------------Gestion de salas----------------------------------------------------

    public void mostrarMenuSala(Scanner entrada){
        do{
            System.out.println("Seleccione una opcion:" +
                    "\n[0] Salir." +
                    "\n[1] Agregar/Eliminar" +
                    "\n[2] Modificar" +
                    "\n[3] Buscar");

            opcion= entrada.nextLine();
            switch (opcion){
                case "0":
                    System.out.println("Volviendo al menu anterior...");
                    return;
                case "1":
                    mostrarMenuSalaAgregarElminar(entrada);
                    pausa();
                    break;
                case "2":
                    System.out.println("Aca va la funcion de modificar una sala");
                    pausa();
                    break;
                case "3":
                    System.out.println("Aca va la funcion de buscar una sala");
                    pausa();
                    break;
                default:
                    System.out.println("\nOpcion invalida, vuelva a intentar.");
                    pausa();
                    break;
            }
        }while (true);
    }

    public void mostrarMenuSalaAgregarElminar(Scanner entrada){
        do{
            System.out.println("Seleccione una opcion:" +
                    "\n[0] Salir." +
                    "\n[1] Agregar" +
                    "\n[2] Eliminar");

            opcion= entrada.nextLine();
            switch (opcion){
                case "0":
                    System.out.println("Saliendo del programa...");
                    return;
                case "1":
                    System.out.println("Aca va la funcion de agregar salas");
                    pausa();
                    break;
                case "2":
                    System.out.println("Aca va la funcion de eliminar una sala");
                    pausa();
                    break;
                default:
                    System.out.println("\nOpcion invalida, vuelva a intentar.");
                    pausa();
                    break;
            }
        }while (true);
    }

    public void mostrarMenuHorarios(Scanner entrada){
        do{
            System.out.println("Bienvenido, seleccione una opcion:" +
                    "\n[0] Salir." +
                    "\n[1] Mostrar horarios" +
                    "\n[2] Cargar horarios" +
                    "\n[3] Gestión de peliculas" +
                    "\n[4] Gestión de funciones");

            opcion= entrada.nextLine();
            switch (opcion){
                case "0":
                    System.out.println("Volviendo al menu anterior...");
                    return;
                case "1":
                    System.out.println("Hola, soy la gestion de salas");
                    pausa();
                    break;
                case "2":
                    System.out.println("Hola, soy la gestion de horarios");
                    pausa();
                    break;
                default:
                    System.out.println("\nOpcion invalida, vuelva a intentar.");
                    pausa();
                    break;
            }
        }while(true);
    }

    ///-------------------------------------------Gestion de peliculas--------------------------------------------------

    public void mostrarMenuPeliculas(){
        GestorPelicula peliculas = new GestorPelicula();
        peliculas.inicializarLista();
        do{
            System.out.println("Bienvenido, seleccione una opcion:" +
                    "\n[0] Salir." +
                    "\n[1] Gestión de salas." +
                    "\n[2] Gestión horarios." +
                    "\n[3] Gestión de peliculas" +
                    "\n[4] Gestión de funciones");

            opcion= entrada.nextLine();
            switch (opcion){
                case "0":
                    System.out.println("Saliendo del programa...");
                    return;
                case "1":
                    System.out.println("Hola, soy la gestion de salas");
                    pausa();
                    break;
                case "2":
                    System.out.println("Hola, soy la gestion de horarios");
                    pausa();
                    break;
                case "3":
                    System.out.println("Hola, soy la gestion de peliculas");
                    pausa();
                    break;
                case "4":
                    System.out.println("Hola, soy la gestion de funciones");
                    pausa();
                    break;
                default:
                    System.out.println("\nOpcion invalida, vuelva a intentar.");
                    pausa();
                    break;
            }
        }while(true);
    }
///----------------------------------------Gestion de funciones------------------------------------------------------------
    public void mostrarMenuFunciones(){
        do{
            System.out.println("Bienvenido, seleccione una opcion:" +
                    "\n[0] Salir." +
                    "\n[1] Gestión de salas." +
                    "\n[2] Gestión horarios." +
                    "\n[3] Gestión de peliculas" +
                    "\n[4] Gestión de funciones");

            opcion= entrada.nextLine();
            switch (opcion){
                case "0":
                    System.out.println("Saliendo del programa...");
                    return;
                case "1":
                    System.out.println("Hola, soy la gestion de salas");
                    pausa();
                    break;
                case "2":
                    System.out.println("Hola, soy la gestion de horarios");
                    pausa();
                    break;
                case "3":
                    System.out.println("Hola, soy la gestion de peliculas");
                    pausa();
                    break;
                case "4":
                    System.out.println("Hola, soy la gestion de funciones");
                    pausa();
                    break;
                default:
                    System.out.println("\nOpcion invalida, vuelva a intentar.");
                    pausa();
                    break;
            }
        }while(true);
    }

    public static void pausa() {
        int continuar = 0;
        do{
            System.out.println("Presione Enter para continuar...");
            String aux = entrada.nextLine();  // Esto espera hasta que el usuario presione Enter
            if(!aux.isBlank()){


                continuar=1;
            }
            if (aux.isBlank()){
                continuar=0;
            }
        }while (continuar!=0);
    }
}