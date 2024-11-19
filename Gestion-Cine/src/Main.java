import Enumeraciones.TipoSala;
import Excepciones.KevPF98.Flexibles.*;
import Gestores.Cine.GestorSalas;
import Modelos.Cine.Sala;

public class Main {
    public static void main(String[] args) {

        ///Butacas:

        /*GestorButacas butacas = null;

        try{
            butacas = new GestorButacas(100);
        }
        catch (ColeccionInvalidaException e){
            System.out.println(e.getMessage());
        }
        catch (ElementoEnColeccionException e){
            System.out.println(e.getMessage());
        }

        butacas.imprimirButacas();*/

        GestorSalas gestorSalas = null;

        try {
            gestorSalas = new GestorSalas();
        } catch (ColeccionInvalidaException e) {
            System.out.println(e.getMessage());
        }

        if(gestorSalas != null){
            Sala sala1 = new Sala(1, TipoSala.DOS_D, 150);
            Sala sala2 = new Sala(2, TipoSala.TRES_D, 80);
            try {
                gestorSalas.agregarSala(sala1);
                gestorSalas.agregarSala(sala2);
                gestorSalas.listar3D();
                gestorSalas.darDeAlta(2);
                gestorSalas.darDeBajaSala(1);
                gestorSalas.eliminarPermanentemente(1);
            }
            catch (ElementoEnColeccionException e) {
                System.out.println(e.getMessage());
            } catch (ColeccionInvalidaException e) {
                System.out.println(e.getMessage());
            } catch (ColeccionVaciaException e) {
                System.out.println(e.getMessage());
            } catch (ProhibidoEstadoActualException e) {
                System.out.println(e.getMessage());
            } catch (ElementoNoEncontradoException e) {
                System.out.println(e.getMessage());
            }
            try {
                gestorSalas.listarSalas();
            } catch (ColeccionVaciaException e) {
                System.out.println(e.getMessage());
            }
        }

        /*
        if(butacas != null){
            try {
                butacas.reservarButaca(2, "41568898");
                butacas.reservarButaca(4, "41568898");
                butacas.listarAsientosMismaPersona("41568898");
            } catch (ButacaInexistenteException e) {
                System.out.println(e.getMessage());
            } catch (ProhibidoEstadoActualException e) {
                System.out.println(e.getMessage());
            } catch (ColeccionInvalidaException e) {
                System.out.println(e.getMessage());
            } catch (ColeccionVaciaException e) {
                System.out.println(e.getMessage());
            } catch (ElementoNoEncontradoException e) {
                System.out.println(e.getMessage());
            }
        }

        try {
            butacas.liberarVariasButacas("41568898");
        } catch (ColeccionInvalidaException e) {
            System.out.println(e.getMessage());
        } catch (ColeccionVaciaException e) {
            System.out.println(e.getMessage());
        } catch (ElementoNoEncontradoException e) {
            System.out.println(e.getMessage());
        } catch (ProhibidoEstadoActualException e) {
            System.out.println(e.getMessage());
        } catch (ButacaInexistenteException e) {
            System.out.println(e.getMessage());
        }*/


        /*GestorEmpleados empleados = new GestorEmpleados();

        Empleado empleado = empleados.cargarNuevoEmpleado();
        empleados.agregarNuevoEmpleado(empleado);*/
    }
}