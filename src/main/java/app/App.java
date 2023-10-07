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
    static GestorDoctor doctores = new GestorDoctor();
    static GestorCita citas = new GestorCita(doctores, pacientes);
    static void crearAdmin (){
    }

    static boolean validarAccesso(){
        return true;
    }

    public static void main( String[] args )
    {
        crearAdmin();

        if(validarAccesso()){
            System.out.println("\nUsuario Autorizado\n");

            int opc = 0;
            do{
                System.out.println("---- Menu ----");
                System.out.println();
                System.out.println("1. Nuevo Doctor");
                System.out.println("2. Modificar Doctor");
                System.out.println("3. Mostrar Doctores");
                System.out.println("4. Nuevo Paciente");
                System.out.println("5. Mostrar Pacientes");
                System.out.println("6. Modificar Pacientes");
                System.out.println("7. Borrar Paciente");
                System.out.println("8. Nueva Cita");
                System.out.println("9. Mostrar Citas");
                System.out.println();
                System.out.print("Opcion: ");
                opc = Integer.parseInt(teclado.nextLine());

                switch (opc) {
                    default:
                        System.out.println("Opcion invalida");
                        break;
                    case 0:
                        pacientes.crearCSV();
                        doctores.crearCSV();
                        citas.crearCSV();
                        break;
                    case 1:
                        doctores.nuevoDoctor();
                        break;
                    case 2:
                        doctores.modificarDoctor();
                        break;
                    case 3:
                        doctores.mostrarDoctores();
                        break;
                    case 4:
                        pacientes.nuevoPaciente();
                        break;
                    case 5:
                        pacientes.mostrarPacientes();
                        break;
                    case 6:
                        pacientes.modificarPaciente();
                        break;
                    case 7:
                        pacientes.borrarPaciente();
                        break;
                    case 8:
                        citas.nuevaCita();
                        break;
                    case 9:
                        citas.mostrarCitas();
                        break;
                }

            }while (opc != 0);
        }else{
            System.out.println("\nUsuario no autorizado.");
        }
        System.out.println("\n\t\tFIN DE PROGRAMA");
    }
}
