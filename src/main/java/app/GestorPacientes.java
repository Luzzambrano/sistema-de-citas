package app;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GestorPacientes {
    class Paciente{
        public String identificadorUnico;
        public String nombre;

        Paciente(String identificadorUnico, String nombre) {
            this.identificadorUnico = identificadorUnico;
            this.nombre = nombre;
        }
        protected void modificar(String nombre){ this.nombre = nombre;  }
        protected void imprimir(){
            System.out.println(this.toString());
        }
        @Override
        public String toString(){
            return String.format("{Identificador: %s, Nombre: %s}", identificadorUnico, nombre);
        }
    }

    private Map<String, Paciente> pacientes = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public void nuevoPaciente(){
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Identificador: ");
        String identificadorUnico = scanner.nextLine();

        pacientes.put(identificadorUnico, new Paciente(nombre, identificadorUnico));
    }

    public void modificarPaciente(){
        System.out.print("Identificador: ");
        String identificadorUnico = scanner.nextLine();
        Paciente paciente = pacientes.get(identificadorUnico);
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        paciente.modificar(nombre);
    }

    public void mostrarPacientes(){
        pacientes.forEach((id, paciente) -> paciente.imprimir());
    }
}
