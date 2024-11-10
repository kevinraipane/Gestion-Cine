package Modelos.Personas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Pattern;

public abstract class Persona {
    //Atributos
    private String nombre;
    private String apellido;
    private int id_usuario;//Recibe un id autoincremental que se enlace con el idUsuario del archivo de usuarios
    private String dni;
    private String email;
    private LocalDate fechaNacimiento;


    //Constructor //FALTAN VERIFICACIONES
    public Persona(){
        this.nombre = cargarNombre();
        this.apellido = cargarNombre();
        //this.id_usuario =
        this.dni = cargarDni();
        this.email = cargarEmail();
        this.fechaNacimiento = cargarFechaNacimiento();
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

    public void setNombre() {
        this.nombre = cargarNombre();
    }

    public void setApeliido() {
        this.apellido = cargarNombre();
    }

    public void setDni() {
        this.dni = cargarDni();
    }

    public void setEmail() {
        this.email = cargarEmail();
    }

    public void setFechaNacimiento() {
        this.fechaNacimiento = cargarFechaNacimiento();
    }

    //Metodo para cargar el nombre y verificar sus datos
    //Luego pasar las verificaciones al gestor de consola
    public String cargarNombre(){
        Scanner scanner = new Scanner(System.in);
        String nombre = "";//La inicializo como nula.
        boolean valido = false;

        while (!valido){
            System.out.println("Introduzca su nombre: ");
            nombre = scanner.nextLine().trim();//Nota al final

            //Verificacion: no puede ser nulo ni estar vacio.
            if(nombre == null  || nombre.isEmpty()){
                System.out.println("El nombre no puede estar vacio.Intentelo nuevamente.");
                continue;
            }

            //Verificacion: no puede comenzar con espacio.
            if(nombre.startsWith(" ")){
                System.out.println("El nombre no puede empezar con un espacio.Intentelo nuevamante.");
                continue;
            }

            //Verificacion: solo puede contener letras y espacios (incluida la ñ). //Nota al final
            if(!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")){
                System.out.println("El nombre solo puede contener letras. Intentelo nuevamente.");
                continue;
            }

            //Verificacion: que no sea una sola letra
            if(nombre.length() == 1){
                System.out.println("El nombre no puede ser una sola letra. Intentelo nuevamente.");
                continue;
            }

            //Verificacion: no puede tener la misma letra 3 o mas veces seguidas
            if(nombre.matches(".*([a-zA-ZáéíóúÁÉÍÓÚñÑ])\\1\\1.*")){
                System.out.println("El nombre no puede tener la misma letra tres veces o mas seguidas.Intentelo nuevamente.");
                continue;
            }

            System.out.println("Nombre valido: " +nombre);
            valido = true;
        }
        scanner.close();
        return nombre;
    }

    //Metodo para cargar dni, verificar que el dni sea unico en la coleccion
    public String cargarDni(){
        Scanner scanner = new Scanner(System.in);
        String dni = "";
        boolean valido = false;

        while (!valido){
            System.out.println("Ingrese su DNI: ");
            dni = scanner.nextLine().trim();

            //Verificacion: el dni debe tener entre 7 y 8 caracteres
            if(dni.length() < 7 || dni.length() > 8){
                System.out.println("El dni debe tener entre 7 y 8 caracteres. Intentelo nuevamente.");
                continue;
            }

            //Verificacion: el dni no debe empezar con espacio
            if(dni.startsWith(" ")){
                System.out.println("El DNI no puede comenzar con espacio.");
                continue;
            }

            //Verificacion: el dni debe contener solo numeros
            if(!dni.matches("\\d+")){
                System.out.println("El DNI solo debe contener numeros. Intentelo nuevamente.");
                continue;
            }

            //Verificacion: el rango del dni debe tener sentido (entre 1.000.000 y 99.999.999)
            long dniLong = Long.parseLong(dni);
            if(dniLong < 1_000_000 || dniLong > 99_999_999){
                System.out.println("El dni debe estar en el rango de 1.000.000 y 99.999.999. Intentelo nuevamente.");
                continue;
            }

            System.out.println("DNI valido: " +dni);
            valido = true;
        }
        scanner.close();
        return dni;
    }

    //Metodo para cargar un email, y verificar el mismo
    public String cargarEmail(){
        Scanner scanner = new Scanner(System.in);
        String email;

        Pattern pattern = Pattern.compile("^[\\w.-]+@[\\w-]+\\.[a-z]{2,}$");

        String[] dominiosValidos = {"@gmail.com","@hotmail.com","@outlook.com","@yahoo.com","@live.com"};

        while (true){
            System.out.println("Introduzca su correo electronico: ");
            email = scanner.nextLine().trim();

            //Valido formato del correo
            if(!pattern.matcher(email).matches()){
                System.out.println("El formato del correo no es valido. Ingreselo nuevamente.");
                continue;
            }

            //Valido el dominio
            boolean dominioValido =  false;
            for (String dominio : dominiosValidos){
                if(email.endsWith(dominio)){
                    dominioValido = true;
                    break;
                }
            }

            //Si no es valido, muestro los dominios disponibles
            if(dominioValido){
               break; //Salgo del while
            }else {
                System.out.println("El correo debe terminar con uno de los siguientes dominios: ");
                for(String dominio : dominiosValidos){
                    System.out.println(dominio);
                }
            }
        }

        scanner.close();
        return email;
    }

    public LocalDate cargarFechaNacimiento(){
        Scanner scanner = new Scanner(System.in);
        LocalDate fechaNacimiento = null;
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaActual = LocalDate.now();
        int edadMaxima = 130;
        int edadMinima = 18;

        while (true){
            System.out.println("Introduzca su fecha de nacimiento (dd/MM/yyyy): ");
            String fechaInput = scanner.nextLine().trim();

            try {
                //Intento parsear la fecha, sino lanzo la runtimeException DateTimeParse
                fechaNacimiento = LocalDate.parse(fechaInput,formatoFecha);

                //Verificacion: fecha no sea en el futuro
                if(fechaNacimiento.isAfter(fechaActual)){
                    System.out.println("La fecha de nacimiento no puede ser posterior a la fecha actual.");
                    continue;
                }

                //Verificacion: que se corresponda a una persona vida (maximo 130 años)
                LocalDate fechaLimite = fechaActual.minusYears(edadMaxima);
                if(fechaNacimiento.isBefore(fechaLimite)){
                    System.out.println("La fecha de nacimiento debe ser de una persona viva (maximo " +edadMaxima+ " años");
                    continue;
                }

                //Verificacion: la persona sea mayor de 18 años
                LocalDate fechaMayorDeEdad = fechaActual.minusYears(edadMinima);
                if(fechaNacimiento.isAfter(fechaMayorDeEdad)){
                    System.out.println("La persona debe ser mayor de 18 años.");
                    continue;
                }

                //Si pasa las validaciones
                break;
            }catch (DateTimeParseException e){
                System.err.println("Formato de fecha invalido. El formato correcto es dd/MM/yyyy");
            }
        }

        scanner.close();
        return fechaNacimiento;
    }

}

/**
 * El trim elimina los espacios al principio y final.
 */

/**
 * [a-zA-Z]: Permite letras del alfabeto en mayúsculas y minúsculas.
 * [áéíóúÁÉÍÓÚñÑ]: Permite letras con acentos (tildes) y la letra "ñ" en mayúscula y minúscula.
 * \\s: Permite los espacios en blanco (aunque no puede haber más de un espacio seguido, como lo validas en otra parte).
 * +: Indica que puede haber una o más de las letras o espacios mencionados, pero no números ni otros caracteres.
 */

/**
 * .*: Permite cualquier cantidad de caracteres al principio y al final del patrón. Esto es para que las letras
 * repetidas puedan estar en cualquier posición del nombre (al principio, en el medio o al final).
 *
 * ([a-zA-ZáéíóúÁÉÍÓÚñÑ]): Este grupo define cualquier letra válida que se puede encontrar en el nombre, capturando una
 * de ellas. La letra capturada se guarda como referencia para poder volver a usarla.
 *
 * \\1\\1: Esto indica que la misma letra capturada en el grupo anterior debe repetirse dos veces más. En total, esto
 * busca tres letras iguales seguidas.
 */

/**
 * \\d+ es una expresión regular (regex) que significa:
 * \\d: representa cualquier dígito (del 0 al 9).
 * +: indica que debe haber al menos un dígito, pero pueden ser más.
 */

/**
 * Pattern pattern = Pattern.compile("^[\\w.-]+@[\\w-]+\\.[a-z]{2,}$");
 * crea un patrón de expresión regular en Java para validar la estructura de un correo electrónico. Aquí está el
 * desglose de la expresión:
 *
 * ^: Marca el inicio de la cadena.
 *
 * [\\w.-]+: Busca uno o más caracteres alfanuméricos, guiones (-), puntos (.) o guiones bajos (_). Esto representa
 * la parte del nombre de usuario del correo (antes del @).
 *
 * \\w representa cualquier carácter alfanumérico o guion bajo (equivalente a [A-Za-z0-9_]).
 * . y - están permitidos, ya que son comunes en los nombres de usuario de correos electrónicos.
 * @: Verifica que el carácter @ esté presente en la posición correcta.
 *
 * [\\w-]+: Busca uno o más caracteres alfanuméricos o guiones (-) para el nombre del dominio (la parte entre @ y el
 * punto final).
 *
 * \\.: Verifica la presencia de un punto (.) antes del dominio superior, como .com.
 *
 * [a-z]{2,}: Busca dos o más letras minúsculas para el dominio de nivel superior (como com, org, net), especificando
 * que la extensión debe tener al menos dos letras.
 *
 * $: Marca el final de la cadena, asegurando que no haya caracteres adicionales después del dominio superior.
 */