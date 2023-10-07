package app;

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
}
