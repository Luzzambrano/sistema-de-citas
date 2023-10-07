package app;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App 
{
    private static ArrayList<Administrador> administradores = new ArrayList<>();
    private static Scanner teclado = new Scanner(System.in);
    static GestorPacientes pacientes = new GestorPacientes();
    static GestorCita citas = new GestorCita();
    static GestorDoctor doctores = new GestorDoctor();

    static void crearAdmin (){
    }


    public static void main( String[] args )
    {

    }
}
