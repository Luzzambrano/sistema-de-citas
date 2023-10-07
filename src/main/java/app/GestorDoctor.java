package app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class GestorDoctor {
    class Doctor{
        public String identificadorUnico;
        public String nombre;
        public String especialidad;

        Doctor(String nombre, String identificadorUnico, String especialidad){
            this.nombre = nombre;
            this.identificadorUnico = identificadorUnico;
            this.especialidad = especialidad;
        }

        protected void imprimir(){
            System.out.println(this.toString());
        }

        protected void modificar(String nombre, String especialidad){
            this.nombre = nombre;
            this.especialidad = especialidad;
        }
        @Override
        public String toString(){
            return String.format("{Identificador: %s, Nombre: %s, Especialidad: %s}", identificadorUnico, nombre, especialidad);
        }

        public String generaLineaCSV() {
            return String.format("%s;%s;%s", identificadorUnico, nombre, especialidad);
        }
    }

    private Map<String, Doctor> doctores = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public void nuevoDoctor(){
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Especialidad: ");
        String especialidad = scanner.nextLine();
        System.out.print("Identificador: ");
        String identificadorUnico = scanner.nextLine();

        doctores.put(identificadorUnico, new Doctor(nombre, identificadorUnico, especialidad));
    }

    Doctor getDoctor(String id){
        return doctores.get(id);
    }

    public void modificarDoctor(){
        System.out.print("Identificador: ");
        String identificadorUnico = scanner.nextLine();
        Doctor doctor = doctores.get(identificadorUnico);
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Especialidad: ");
        String especialidad = scanner.nextLine();
        doctor.modificar(nombre, especialidad);
    }

    public void mostrarDoctores(){
        doctores.forEach((id, doctor) -> doctor.imprimir());
    }

    public void crearCSV(){
        File archivo = new File("\\db\\doctores.csv");
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
            escritor.append("#ID;Nombre completo;Especialidad\n");
            doctores.forEach((id, doctor) -> {
                try {
                    escritor.append(doctor.generaLineaCSV());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            escritor.close();
        } catch (IOException e) {
            System.out.println(String.format("Error con manejo del archivo: %s", archivo.getAbsolutePath()));
        }
    }
}
