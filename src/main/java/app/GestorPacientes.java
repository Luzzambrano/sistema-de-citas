package app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

        public String generaLineaCSV() {
            return String.format("%s;%s", identificadorUnico, nombre);
        }
    }

    private Map<String, Paciente> pacientes = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public void nuevoPaciente(){
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Identificador: ");
        String identificadorUnico = scanner.nextLine();

        pacientes.put(identificadorUnico, new Paciente(identificadorUnico, nombre));
    }

    public Paciente getPaciente(String idPaciente) {
        return pacientes.get(idPaciente);
    }

    public void modificarPaciente(){
        System.out.print("Identificador: ");
        String identificadorUnico = scanner.nextLine();
        Paciente paciente = pacientes.get(identificadorUnico);
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        paciente.modificar(nombre);
    }

    public void borrarPaciente(){
        System.out.print("Identificador: ");
        String identificadorUnico = scanner.nextLine();
        Paciente paciente = pacientes.remove(identificadorUnico);
    }

    public void crearCSV(){
        File archivo = new File("\\db\\pacientes.csv");
        try {
            if(!archivo.exists()){
                File carpeta = archivo.getParentFile();
                carpeta.mkdirs();
                archivo.createNewFile();
            }else{
                //borrar el contenido del archivo
                new FileWriter(archivo, false).close();
            }
            FileWriter escritor = new FileWriter(archivo, true);
            escritor.append("#ID;Nombre completo\n");
            int ultimo = pacientes.size()-1;
            pacientes.forEach((id, paciente) -> {
                try {
                    escritor.append(paciente.generaLineaCSV());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            escritor.close();
        } catch (IOException e) {
            System.out.println(String.format("Error con manejo del archivo: %s", archivo.getAbsolutePath()));
        }
    }

    public void mostrarPacientes(){
        pacientes.forEach((id, paciente) -> paciente.imprimir());
    }
}
